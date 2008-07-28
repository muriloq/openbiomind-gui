/**
 * AbstractTaskHandler.java
 *
 * The file AbstractTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.common.Constants;
import openbiomind.gui.preferences.Preference;
import openbiomind.gui.wizards.AbstractTaskWizard;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The class AbstractTaskHandler.
 *
 * @author bsanghvi
 * @since Jun 14, 2008
 * @version Jul 27, 2008
 */
public abstract class AbstractTaskHandler extends AbstractHandler implements Constants {

   /**
    * Validate the initial settings.
    *
    * @param shell the shell
    *
    * @return true, if successful
    */
   protected boolean validate(final Shell shell) {
      if (!Preference.isRequiredPreferenceSet()) {
         MessageDialog.openError(shell, Resources.ERROR, "Set the required preferences before executing a task.");
         return false;
      }

      return true;
   }

   /**
    * Execute utility method.
    *
    * @param event the event
    * @param abstractTaskWizard the abstract task wizard
    *
    * @return the object
    *
    * @throws ExecutionException the execution exception
    */
   protected Object execute(final ExecutionEvent event, final AbstractTaskWizard abstractTaskWizard)
         throws ExecutionException {
      final Shell shell = HandlerUtil.getActiveWorkbenchWindowChecked(event).getShell();
      if (validate(shell)) {
         new WizardDialog(shell, abstractTaskWizard).open();
      }

      return null;
   }

}
