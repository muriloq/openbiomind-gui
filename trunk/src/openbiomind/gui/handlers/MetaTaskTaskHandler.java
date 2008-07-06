/**
 * MetaTaskTaskHandler.java
 *
 * The file MetaTaskTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.wizards.MetaTaskWizard;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The class MetaTaskTaskHandler.
 *
 * @author bsanghvi
 * @since Jun 27, 2008
 * @version Jul 6, 2008
 */
public class MetaTaskTaskHandler extends AbstractTaskHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      final Shell shell = HandlerUtil.getActiveWorkbenchWindowChecked(event).getShell();

      if (validate(shell)) {
         final MetaTaskWizard metaTaskWizard = new MetaTaskWizard();
         final WizardDialog wizardDialog = new WizardDialog(shell, metaTaskWizard);
         wizardDialog.open();
      }

      return null;
   }

}
