/**
 * EnhanceDatasetTaskHandler.java
 *
 * The file EnhanceDatasetTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.wizards.EnhanceDatasetWizard;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The class EnhanceDatasetHandler.
 *
 * @author bsanghvi
 * @since Jun 9, 2008
 * @version Jun 9, 2008
 *
 * @see org.eclipse.core.commands.IHandler
 */
public class EnhanceDatasetTaskHandler extends AbstractHandler {

   /*
    * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      final EnhanceDatasetWizard enhanceDatasetWizard = new EnhanceDatasetWizard();
      final WizardDialog wizardDialog = new WizardDialog(HandlerUtil
            .getActiveWorkbenchWindowChecked(event).getShell(), enhanceDatasetWizard);
      wizardDialog.open();
      return null;
   }

}
