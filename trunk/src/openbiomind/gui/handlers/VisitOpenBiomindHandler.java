/**
 * VisitOpenBiomindHandler.java
 *
 * The file VisitOpenBiomindHandler.java is used to open OpenBiomind homepage in external browser.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.common.Constants;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.program.Program;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The class VisitOpenBiomindHandler is used to open OpenBiomind home page in external browser.
 *
 * @author bsanghvi
 * @since Jul 4, 2008
 * @version Jul 28, 2008
 */
public class VisitOpenBiomindHandler extends AbstractHandler implements Constants {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      if (!Program.launch(Resources.OPENBIOMIND_HOMEPAGE)) {
         MessageDialog.openError(HandlerUtil.getActiveWorkbenchWindowChecked(event).getShell(), Resources.ERROR, NLS
               .bind(Messages.Err_UnableToOpen, Resources.OPENBIOMIND_HOMEPAGE));
      }
      return null;
   }

}
