/**
 * AbstractTaskWizard.java
 *
 * The file AbstractTaskWizard.java.
 *
 * TODO THIS CLASS ANF ITS SUBCLASSES NEED TO BE REFACTORED AND REDESIGNED.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Set;

import openbiomind.gui.Application;
import openbiomind.gui.console.Console;
import openbiomind.gui.main.TaskProcessBuider;
import openbiomind.gui.tasks.AbstractTaskData;
import openbiomind.gui.tasks.TaskDataFile;
import openbiomind.gui.tasks.TaskDataFolder;
import openbiomind.gui.tasks.TaskDataProject;
import openbiomind.gui.util.Constants;
import openbiomind.gui.util.Messages;

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
 * @version Jun 27, 2008
 */
public abstract class AbstractTaskWizard extends Wizard implements Constants {

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
      monitor.beginTask(Messages.WizardProgress_PrepareTaskData, 7);
      prepareTaskData();
      Console.info(this.getTaskData().toString());
      monitor.worked(1);
      monitor.subTask(Messages.WizardProgress_PrepareProcess);

      try {
         final TaskProcessBuider taskProcessBuider = new TaskProcessBuider(getTaskData());
         monitor.worked(1);
         monitor.subTask(Messages.WizardProgress_ExecuteTask + getTaskData());
         final Process process = taskProcessBuider.start();
         final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
         Assert.isNotNull(reader);
         String message = null;

         try {
            while ((message = reader.readLine()) != null) {
               Console.output(message);
            }
         } catch (final IOException e) {
            Console.error(e);
         }
         reader.close();
         monitor.worked(2);
      } catch (final IOException e) {
         throw new CoreException(new Status(IStatus.ERROR, Application.PLUGIN_ID, IStatus.OK, e.getLocalizedMessage(),
               e));
      }

      monitor.subTask(Messages.WizardProgress_LoadFiles);
      /*
       * Create the project
       */
      createProject(getTaskData().createTaskDataProject(), monitor);

      monitor.worked(3);
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
      boolean created = false;

      final IWorkspace workspace = ResourcesPlugin.getWorkspace();
      final IWorkspaceRoot workspaceRoot = workspace.getRoot();
      final IProject project = workspaceRoot.getProject(taskDataProject.getName());

      try {
         project.create(monitor);
         project.open(monitor);

         if (project.isOpen()) {
            final Set<TaskDataFolder> taskDataFolderSet = taskDataProject.getTaskDataFolderSet();
            for (final TaskDataFolder taskDataFolder : taskDataFolderSet) {
               final IFolder iFolder = project.getFolder(taskDataFolder.getName());
               iFolder.create(true, true, monitor);
               final Set<TaskDataFile> taskDataFileSet = taskDataFolder.getTaskDataFileSet();
               for (final TaskDataFile taskDataFile : taskDataFileSet) {
                  final String pathname = taskDataFile.getPathname();
                  final File file = new File(pathname);
                  final IFile iFile = iFolder.getFile(file.getName());
                  final URI uri = file.toURI();
                  iFile.createLink(uri, 0, monitor);

                  if (taskDataFile.isAutoOpen()) {
                     IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), iFile,
                           Properties.DEFAULT_TEXT_EDITOR_ID);
                  }
               }
            }
         }

         created = true;

         // workspace.save(false, monitor);
      } catch (final CoreException e) {
         Console.error(e);
      }


      return created;
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

}
