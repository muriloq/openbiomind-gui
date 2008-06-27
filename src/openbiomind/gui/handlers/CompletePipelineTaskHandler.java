/**
 * CompletePipelineTaskHandler.java
 *
 * The file CompletePipelineTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The class CompletePipelineTaskHandler.
 *
 * @author bsanghvi
 * @since Jun 27, 2008
 * @version Jun 27, 2008
 */
public class CompletePipelineTaskHandler extends AbstractTaskHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
      MessageDialog.openInformation(window.getShell(), "OpenBiomind-GUI", "Complete Pipeline Task");
      return null;
   }

}
