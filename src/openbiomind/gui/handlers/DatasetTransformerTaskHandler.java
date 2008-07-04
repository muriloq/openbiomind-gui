/**
 * DatasetTransformerTaskHandler.java
 *
 * The file DatasetTransformerTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.wizards.DatasetTransformerWizard;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The class DatasetTransformerHandler.
 *
 * @author bsanghvi
 * @since Jun 9, 2008
 * @version Jun 9, 2008
 */
public class DatasetTransformerTaskHandler extends AbstractTaskHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      final Shell shell = HandlerUtil.getActiveWorkbenchWindowChecked(event).getShell();

      if (validate(shell)) {
         final DatasetTransformerWizard datasetTransformerWizard = new DatasetTransformerWizard();
         final WizardDialog wizardDialog = new WizardDialog(shell, datasetTransformerWizard);
         wizardDialog.open();
      }

      return null;
   }

}
