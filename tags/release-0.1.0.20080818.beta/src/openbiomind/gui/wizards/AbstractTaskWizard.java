/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (AbstractTaskWizard.java) is part of OpenBiomind-GUI.
 *
 *  OpenBiomind-GUI is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenBiomind-GUI is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenBiomind-GUI.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Please visit the following pages to contact the author(s):
 *  Homepage: http://code.google.com/p/openbiomind-gui/
 *  Mailing list: http://groups.google.com/group/openbiomind-gui/
 */

package openbiomind.gui.wizards;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.Set;

import openbiomind.gui.Application;
import openbiomind.gui.common.Constants;
import openbiomind.gui.console.Console;
import openbiomind.gui.data.AbstractTaskData;
import openbiomind.gui.main.TaskProcessBuider;
import openbiomind.gui.project.TaskDataFile;
import openbiomind.gui.project.TaskDataFolder;
import openbiomind.gui.project.TaskDataProject;
import openbiomind.gui.util.Utility;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * The class AbstractTaskWizard.
 * 
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Aug 18, 2008
 */
public abstract class AbstractTaskWizard extends Wizard implements Constants {

   /** The Constant DELAY. */
   private static final int DELAY = 5;

   /** The execution log file path. */
   private String executionLogFilePath = null;

   /**
    * Instantiates a new enhance dataset wizard.
    * 
    * @param wizardTitle the wizard title
    */
   protected AbstractTaskWizard(final String wizardTitle) {
      super();
      setWindowTitle(wizardTitle);
      setNeedsProgressMonitor(true);
   }

   /**
    * Gets the task data.
    * 
    * @return the task data
    */
   protected abstract AbstractTaskData[] getTaskData();

   /**
    * Gets the first wizard page.
    * 
    * @return the first wizard page
    */
   protected abstract AbstractTaskWizardPage getFirstWizardPage();

   /*
    * @see org.eclipse.jface.wizard.Wizard#performFinish()
    */
   @Override
   public boolean performFinish() {
      // create the process
      final IRunnableWithProgress runnableWithProgress = new IRunnableWithProgress() {

         @Override
         public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
            try {
               monitor.beginTask(Messages.AbsTaskWiz_ExecTask, 1);
               doFinish(monitor);
            } catch (final CoreException e) {
               throw new InvocationTargetException(e);
            } finally {
               monitor.done();
            }
         }

      };

      // run the process
      boolean success = false;
      try {
         getContainer().run(false, false, runnableWithProgress);
         success = true;
      } catch (final InterruptedException e) {
         success = false;
      } catch (final InvocationTargetException e) {
         Console.debug(e);
         MessageDialog.openError(getShell(), Resources.ERROR, e.getTargetException().getMessage());
         success = false;
      }

      return success;
   }

   /**
    * Do finish.
    * 
    * @param monitor the monitor
    * 
    * @throws CoreException the core exception
    */
   private void doFinish(final IProgressMonitor monitor) throws CoreException {
      final AbstractTaskData[] taskDataArray = getTaskData();
      final SubMonitor subMonitor = SubMonitor.convert(monitor, Messages.AbsTaskWiz_ExecTask,
            30 + (70 * taskDataArray.length));

      // execute all the processes
      subMonitor.subTask(Messages.AbsTaskWiz_PrepProc);
      PrintWriter executionLogWriter = null;
      final TaskDataProject taskDataProject = new TaskDataProject(getFirstWizardPage().getProjectName());
      try {
         executionLogWriter = new PrintWriter(getExecutionLogFilePath());

         for (final AbstractTaskData taskData : taskDataArray) {
            executionLogWriter.println(); // empty line
            executionLogWriter.flush();

            final TaskProcessBuider taskProcessBuider = new TaskProcessBuider(taskData);

            // write the command at the top
            final String executedCommand = taskData.toString();
            Console.info(executedCommand);
            executionLogWriter.println(executedCommand);
            executionLogWriter.flush();

            // execute the process
            final Process process = taskProcessBuider.start();
            executeTaskProcess(process, executionLogWriter, subMonitor.newChild(60, SubMonitor.SUPPRESS_SETTASKNAME));

            // process the error, if any
            processError(process.getErrorStream(), subMonitor.newChild(10, SubMonitor.SUPPRESS_SETTASKNAME));
            if (process.exitValue() != 0) {
               throw new CoreException(new Status(IStatus.ERROR, Application.PLUGIN_ID, NLS.bind(
                     Messages.Err_TaskExecutionFailed, taskData.getTaskName())));
            }

            // leave blanks before next command
            executionLogWriter.println(); // empty line
            executionLogWriter.flush();
         }

         // execute the post execution process
         subMonitor.setWorkRemaining(30);
         executeProcess(getPostSuccessfulExecutionProcess(), executionLogWriter, subMonitor.newChild(20,
               SubMonitor.SUPPRESS_SETTASKNAME));

         // create the project
         subMonitor.setWorkRemaining(10);
         // need to do this in separate loop since post process may add more resources
         for (final AbstractTaskData taskData : taskDataArray) {
            taskDataProject.add(taskData.createTaskDataFolder());
         }
         if (!createProject(taskDataProject, subMonitor.newChild(10, SubMonitor.SUPPRESS_SETTASKNAME))) {
            throw new CoreException(
                  new Status(IStatus.ERROR, Application.PLUGIN_ID, Messages.Err_UnableToCreateProject));
         }
      } catch (final IOException e) {
         throw new CoreException(new Status(IStatus.ERROR, Application.PLUGIN_ID, IStatus.OK, e.getMessage(), e));
      } finally {
         Utility.close(executionLogWriter);
      }

   }

   /**
    * Gets the post successful execution process. Override this method if you want to do some task after the command
    * execution and before project creation.
    * 
    * @return the post successful execution process
    */
   protected Process getPostSuccessfulExecutionProcess() {
      return null;
   }

   /**
    * Execute task.
    * 
    * @param process the process
    * @param executionLogWriter the execution log writer
    * @param monitor the monitor
    * 
    * @return true, if successful
    */
   private boolean executeTaskProcess(final Process process, final PrintWriter executionLogWriter,
         final IProgressMonitor monitor) {
      boolean successfulExecution = false;

      if (process != null) {
         final SubMonitor subMonitor = SubMonitor.convert(monitor, 100);

         // read the command output and store it
         successfulExecution = executeProcess(process, executionLogWriter, subMonitor.newChild(100,
               SubMonitor.SUPPRESS_SETTASKNAME));
      }

      return successfulExecution;
   }

   /**
    * Execute process.
    * 
    * @param process the process
    * @param executionLogWriter the execution log writer
    * @param monitor the monitor
    * 
    * @return true, if successful
    */
   private boolean executeProcess(final Process process, final PrintWriter executionLogWriter,
         final IProgressMonitor monitor) {
      boolean successfulExecution = false;

      if (process != null) {
         final SubMonitor subMonitor = SubMonitor.convert(monitor);
         subMonitor.setWorkRemaining(10000);

         // read the command output and store it
         final Scanner reader = new Scanner(new BufferedReader(new InputStreamReader(process.getInputStream())));
         String message = null;
         while (reader.hasNextLine()) {
            subMonitor.setWorkRemaining(10000);
            message = reader.nextLine();
            subMonitor.subTask(message);
            executionLogWriter.println(LOG_PREFIX + message);
            executionLogWriter.flush();
            subMonitor.worked(1);
            try {
               Thread.sleep(DELAY);
            } catch (final InterruptedException e) {
               Console.debug(e);
            }
         }

         successfulExecution = true;

         if (reader != null) {
            reader.close();
         }
      }

      return successfulExecution;
   }

   /**
    * Process error.
    * 
    * @param errorStream the error stream
    * @param monitor the monitor
    * 
    * @return true, if successful
    */
   private boolean processError(final InputStream errorStream, final IProgressMonitor monitor) {
      final SubMonitor subMonitor = SubMonitor.convert(monitor);
      subMonitor.setWorkRemaining(100);

      Scanner reader = null;

      try {
         reader = new Scanner(new BufferedReader(new InputStreamReader(errorStream)));
         while (reader.hasNextLine()) {
            subMonitor.setWorkRemaining(100);
            Console.error(reader.nextLine());
            subMonitor.worked(1);
         }
      } finally {
         if (reader != null) {
            reader.close();
         }
      }

      return true;
   }

   /**
    * Creates the project.
    * 
    * @param taskDataProject the task data project
    * @param monitor the monitor
    * 
    * @return true, if successful
    * 
    * @throws CoreException the core exception
    */
   private boolean createProject(final TaskDataProject taskDataProject, final IProgressMonitor monitor)
         throws CoreException {
      boolean created = false;
      if (taskDataProject != null) {
         // TODO Update to use resource validation
         final Set<TaskDataFolder> taskDataFolderSet = taskDataProject.getTaskDataFolderSet();
         final Set<TaskDataFile> taskDataFileSet = taskDataProject.getTaskDataFileSet();
         final String projectName = taskDataProject.getName();
         Assert.isNotNull(projectName);
         final SubMonitor subMonitor = SubMonitor.convert(monitor, projectName, 100).setWorkRemaining(
               3 + 2 * taskDataFolderSet.size() + taskDataFileSet.size());

         final IWorkspace workspace = ResourcesPlugin.getWorkspace();
         final IWorkspaceRoot workspaceRoot = workspace.getRoot();
         final IProject iProject = workspaceRoot.getProject(projectName);

         iProject.create(subMonitor.newChild(1, SubMonitor.SUPPRESS_ALL_LABELS));
         iProject.open(subMonitor.newChild(1, SubMonitor.SUPPRESS_ALL_LABELS));

         if (iProject.isOpen()) {
            // create all the folders
            for (final TaskDataFolder taskDataFolder : taskDataFolderSet) {
               createFolder(taskDataFolder, iProject, subMonitor.newChild(2, SubMonitor.SUPPRESS_SETTASKNAME));
            }

            // create all the files
            for (final TaskDataFile taskDataFile : taskDataFileSet) {
               createFile(taskDataFile, iProject, subMonitor.newChild(1, SubMonitor.SUPPRESS_SETTASKNAME));
            }

            // create execution.log file
            final TaskDataFile outputLogTaskDataFile = new TaskDataFile(Resources.EXECUTION_FILENAME);
            outputLogTaskDataFile.setAutoOpen(true);
            outputLogTaskDataFile.setLinked(false);
            outputLogTaskDataFile.setPath(getExecutionLogFilePath());
            createFile(outputLogTaskDataFile, iProject, subMonitor.newChild(1, SubMonitor.SUPPRESS_SETTASKNAME));
         }

         created = true;
      }

      return created;
   }

   /**
    * Creates the folder.
    * 
    * @param taskDataFolder the task data folder
    * @param container the container
    * @param monitor the monitor
    * 
    * @return true, if successful
    */
   private boolean createFolder(final TaskDataFolder taskDataFolder, final IContainer container,
         final IProgressMonitor monitor) {
      // TODO Update to use resource validation
      boolean created = false;
      final Set<TaskDataFile> taskDataFileSet = taskDataFolder.getTaskDataFileSet();
      final Set<TaskDataFolder> taskDataFolderSet = taskDataFolder.getTaskDataFolderSet();
      final String folderName = taskDataFolder.getName();
      final SubMonitor subMonitor = SubMonitor.convert(monitor, folderName, 100).setWorkRemaining(
            1 + 2 * taskDataFolderSet.size() + taskDataFileSet.size());

      try {
         // Create the folder resource
         final IFolder iFolder;
         if (container instanceof IFolder) {
            iFolder = ((IFolder) container).getFolder(folderName);
         } else if (container instanceof IProject) {
            iFolder = ((IProject) container).getFolder(folderName);
         } else {
            iFolder = null;
         }

         if (iFolder != null) {
            if (taskDataFolder.isLinked()) {
               iFolder.createLink((new File(taskDataFolder.getPath())).toURI(), 0, subMonitor.newChild(1,
                     SubMonitor.SUPPRESS_ALL_LABELS));
            } else {
               iFolder.create(true, true, subMonitor.newChild(1, SubMonitor.SUPPRESS_ALL_LABELS));
            }
            subMonitor.worked(1);

            created = true;

            // create all the files
            for (final TaskDataFile taskDataFile : taskDataFileSet) {
               createFile(taskDataFile, iFolder, subMonitor.newChild(1, SubMonitor.SUPPRESS_SETTASKNAME));
            }

            // create all the folders
            for (final TaskDataFolder taskDataFolderSub : taskDataFolderSet) {
               createFolder(taskDataFolderSub, iFolder, subMonitor.newChild(2, SubMonitor.SUPPRESS_SETTASKNAME));
            }
         }
      } catch (final CoreException e) {
         Console.debug(e);
         created = false;
      } catch (final Exception e) {
         Console.debug(e);
         created = false;
      }

      return created;
   }

   /**
    * Creates the file.
    * 
    * @param taskDataFile the task data file
    * @param container the container
    * @param monitor the monitor
    * 
    * @return true, if successful
    */
   private boolean createFile(final TaskDataFile taskDataFile, final IContainer container,
         final IProgressMonitor monitor) {
      // TODO Update to use resource validation
      boolean created = false;
      // Get file name
      final String fileName;
      final String filePath = taskDataFile.getPath();
      final File file = new File(filePath);

      if (Utility.isEmpty(taskDataFile.getName())) {
         fileName = file.getName();
      } else {
         fileName = taskDataFile.getName();
      }

      final SubMonitor subMonitor = SubMonitor.convert(monitor, fileName, 100);

      try {
         // Create the file resource
         final IFile iFile;
         if (container instanceof IFolder) {
            iFile = ((IFolder) container).getFile(fileName);
         } else if (container instanceof IProject) {
            iFile = ((IProject) container).getFile(fileName);
         } else {
            iFile = null;
         }

         if (iFile != null) {
            if (taskDataFile.isLinked()) {
               iFile.createLink(file.toURI(), 0, subMonitor.newChild(50, SubMonitor.SUPPRESS_ALL_LABELS));
            } else {
               iFile.create(new FileInputStream(file), false, subMonitor.newChild(50, SubMonitor.SUPPRESS_ALL_LABELS));
            }
            subMonitor.setWorkRemaining(50);

            created = true;

            // Open the file if needed
            if (taskDataFile.isAutoOpen()) {
               // FIXME Find out the file type and open the respective editor
               if (Utility.isEmpty(taskDataFile.getEditorId())) {
                  IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), iFile);
               } else {
                  IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), iFile,
                        taskDataFile.getEditorId());
               }
            }

            subMonitor.worked(50);
         }
      } catch (final CoreException e) {
         Console.debug(e);
         created = false;
      } catch (final Exception e) {
         Console.debug(e);
         created = false;
      }

      return created;
   }

   /**
    * Gets the execution log file path.
    * 
    * @return the execution log file path
    */
   private String getExecutionLogFilePath() {
      if (this.executionLogFilePath == null) {
         try {
            this.executionLogFilePath = File.createTempFile(Resources.EXECUTION_FILENAME, null).getAbsolutePath();
         } catch (final IOException e) {
            Console.error(e);
         }
      }

      return this.executionLogFilePath;
   }

}
