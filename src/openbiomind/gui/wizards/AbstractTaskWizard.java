/**
 * AbstractTaskWizard.java
 *
 * The file AbstractTaskWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

import openbiomind.gui.Application;
import openbiomind.gui.console.Console;
import openbiomind.gui.main.TaskProcessBuider;
import openbiomind.gui.tasks.AbstractTaskData;
import openbiomind.gui.util.Constants;
import openbiomind.gui.util.Messages;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

/**
 * The class AbstractTaskWizard.
 *
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Jun 13, 2008
 */
public abstract class AbstractTaskWizard extends Wizard {

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
         public void run(final IProgressMonitor monitor) throws InvocationTargetException,
               InterruptedException {
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
         MessageDialog.openError(getShell(), Constants.Error, e.getTargetException()
               .getLocalizedMessage());
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
         final BufferedReader reader = new BufferedReader(new InputStreamReader(process
               .getInputStream()));
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
         throw new CoreException(new Status(IStatus.ERROR, Application.PLUGIN_ID, IStatus.OK, e
               .getLocalizedMessage(), e));
      }

      monitor.subTask(Messages.WizardProgress_LoadFiles);
      /*
       * Load files here...
       */
      // final String[] filesArray = getTaskData().getFilesArray();
      // System.out.println("Files to open:");
      // for (String string : filesArray) {
      // System.out.println(string);
      // }
      monitor.worked(3);
   };

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
