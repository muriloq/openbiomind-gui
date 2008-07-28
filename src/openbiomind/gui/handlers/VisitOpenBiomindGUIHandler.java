/**
 * VisitOpenBiomindGUIHandler.java
 *
 * The file VisitOpenBiomindGUIHandler.java is used to open OpenBiomind-GUI homepage in external browser.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.common.Constants;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.program.Program;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The class VisitOpenBiomindGUIHandler is used to open OpenBiomind-GUI homepage in external browser.
 *
 * @author bsanghvi
 * @since Jul 4, 2008
 * @version Jul 27, 2008
 */
public class VisitOpenBiomindGUIHandler extends AbstractHandler implements Constants {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      if (!Program.launch(Resources.OPENBIOMIND_GUI_HOMEPAGE)) {
         MessageDialog.openError(HandlerUtil.getActiveWorkbenchWindowChecked(event).getShell(), Resources.ERROR,
               "Unable to open" + SPACE + Resources.OPENBIOMIND_GUI_HOMEPAGE);
      }
      return null;
   }

}
