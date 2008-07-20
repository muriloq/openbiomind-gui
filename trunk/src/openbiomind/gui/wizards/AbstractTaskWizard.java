/**
 * AbstractTaskWizard.java
 *
 * The file AbstractTaskWizard.java.
 *
 * TODO THIS CLASS AND ITS SUBCLASSES NEED TO BE REFACTORED AND REDESIGNED.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.Set;

import openbiomind.gui.Application;
import openbiomind.gui.console.Console;
import openbiomind.gui.main.TaskProcessBuider;
import openbiomind.gui.tasks.AbstractTaskData;
import openbiomind.gui.tasks.TaskDataFile;
import openbiomind.gui.tasks.TaskDataFolder;
import openbiomind.gui.tasks.TaskDataProject;
import openbiomind.gui.util.Constants;
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
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * The class AbstractTaskWizard.
 *
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Jul 20, 2008
 */
public abstract class AbstractTaskWizard extends Wizard implements Constants {

   /** The execution log file. */
   private File executionLogFile = null;

   /** The executed command. */
   private String executedCommand = null;

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
    * Prepare task data.
    */
   protected abstract void prepareTaskData();

   /**
    * Gets the task data.
    *
    * @return the task data
    */
   protected abstract AbstractTaskData getTaskData();

   /*
    * @see org.eclipse.jface.wizard.Wizard#performFinish()
    */
   @Override
   public boolean performFinish() {
      /*
       * create the process
       */
      final IRunnableWithProgress runnableWithProgress = new IRunnableWithProgress() {

         @Override
         public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
            try {
               monitor.beginTask(WizardMessages.AbstractTaskWizard_ExecutingTask, 1);
               doFinish(monitor);
            } catch (final CoreException e) {
               throw new InvocationTargetException(e);
            } finally {
               monitor.done();
            }
         }

      };

      /*
       * run the process
       */
      try {
         getContainer().run(false, false, runnableWithProgress);
      } catch (final InterruptedException e) {
         return false;
      } catch (final InvocationTargetException e) {
         MessageDialog.openError(getShell(), Resources.ERROR, e.getTargetException().getLocalizedMessage());
         return false;
      }
      return true;

   }

   /**
    * Do finish.
    *
    * @param monitor the monitor
    *
    * @throws CoreException the core exception
    */
   private void doFinish(final IProgressMonitor monitor) throws CoreException {
      final SubMonitor subMonitor = SubMonitor.convert(monitor, WizardMessages.AbstractTaskWizard_ExecutingTask, 100);
      subMonitor.subTask(WizardMessages.AbstractTaskWizard_PreparingTaskData);
      prepareTaskData();
      Console.info(getExecutedCommand());
      subMonitor.worked(5);

      try {
         subMonitor.subTask(WizardMessages.AbstractTaskWizard_PreparingProcess);
         final TaskProcessBuider taskProcessBuider = new TaskProcessBuider(getTaskData());
         subMonitor.worked(5);

         final Process process = taskProcessBuider.start();
         executeTask(process, subMonitor.newChild(50, SubMonitor.SUPPRESS_SETTASKNAME));

         subMonitor.setWorkRemaining(40);
         if (process.exitValue() == 0) {
            createProject(getTaskData().createTaskDataProject(), subMonitor.newChild(40,
                  SubMonitor.SUPPRESS_SETTASKNAME));
         } else {
            processError(process, subMonitor.newChild(40, SubMonitor.SUPPRESS_SETTASKNAME));
         }
      } catch (final IOException e) {
         throw new CoreException(new Status(IStatus.ERROR, Application.PLUGIN_ID, IStatus.OK, e.getLocalizedMessage(),
               e));
      }
   }

   /**
    * Execute task.
    *
    * @param process the process
    * @param monitor the monitor
    *
    * @return true, if successful
    */
   private boolean executeTask(final Process process, final IProgressMonitor monitor) {
      final SubMonitor subMonitor = SubMonitor.convert(monitor);
      subMonitor.setWorkRemaining(10000);

      final Scanner reader = new Scanner(new BufferedReader(new InputStreamReader(process.getInputStream())));
      PrintWriter executionLogWriter = null;
      String message = null;

      try {
         executionLogWriter = new PrintWriter(getExecutionLogFile());

         /*
          * write the command at the top
          */
         executionLogWriter.println(getExecutedCommand());
         executionLogWriter.println(); // empty line
         subMonitor.worked(1);

         /*
          * read the command output and store it
          */
         while (reader.hasNextLine()) {
            subMonitor.setWorkRemaining(10000);
            message = reader.nextLine();
            subMonitor.subTask(message);
            executionLogWriter.println(message);
            subMonitor.worked(1);
         }
      } catch (final IOException e) {
         Console.error(e);
      } finally {
         Utility.close(executionLogWriter);
         if (reader != null) {
            reader.close();
         }
      }

      return true;
   }

   /**
    * Process error.
    *
    * @param process the process
    * @param monitor the monitor
    *
    * @return true, if successful
    */
   private boolean processError(final Process process, final IProgressMonitor monitor) {
      final SubMonitor subMonitor = SubMonitor.convert(monitor);
      subMonitor.setWorkRemaining(100);

      Scanner reader = null;

      try {
         reader = new Scanner(new BufferedReader(new InputStreamReader(process.getErrorStream())));
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
    */
   private boolean createProject(final TaskDataProject taskDataProject, final IProgressMonitor monitor) {
      // TODO Update to use resource validation
      boolean created = false;
      final Set<TaskDataFolder> taskDataFolderSet = taskDataProject.getTaskDataFolderSet();
      final Set<TaskDataFile> taskDataFileSet = taskDataProject.getTaskDataFileSet();
      final String projectName = taskDataProject.getName();
      Assert.isNotNull(projectName);
      final SubMonitor subMonitor = SubMonitor.convert(monitor, projectName, 100).setWorkRemaining(
            3 + 2 * taskDataFolderSet.size() + taskDataFileSet.size());

      final IWorkspace workspace = ResourcesPlugin.getWorkspace();
      final IWorkspaceRoot workspaceRoot = workspace.getRoot();
      final IProject iProject = workspaceRoot.getProject(projectName);

      try {
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
            outputLogTaskDataFile.setPath(getExecutionLogFile().getAbsolutePath());
            createFile(outputLogTaskDataFile, iProject, subMonitor.newChild(1, SubMonitor.SUPPRESS_SETTASKNAME));
         }

         created = true;
      } catch (final CoreException e) {
         Console.error(e);
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
               // TODO Find out the file type and open the respective editor
               IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), iFile);
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
    * Gets the executed command.
    *
    * @return the executed command
    */
   private String getExecutedCommand() {
      if (this.executedCommand == null) {
         this.executedCommand = getTaskData().toString();
      }
      return this.executedCommand;
   }

   /**
    * Gets the execution log file.
    *
    * @return the execution log file
    */
   private File getExecutionLogFile() {
      if (this.executionLogFile == null) {
         try {
            this.executionLogFile = File.createTempFile(Resources.EXECUTION_FILENAME, Long.toString(System
                  .currentTimeMillis()));
         } catch (final IOException e) {
            Console.error(e);
         }
      }

      return this.executionLogFile;
   }

}
