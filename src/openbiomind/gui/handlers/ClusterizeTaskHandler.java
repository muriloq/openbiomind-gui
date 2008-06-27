/**
 * ClusterizeTaskHandler.java
 *
 * The file ClusterizeTaskHandler.java.
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
 * The class ClusterizeTaskHandler.
 *
 * @author bsanghvi
 * @since Jun 27, 2008
 * @version Jun 27, 2008
 */
public class ClusterizeTaskHandler extends AbstractTaskHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
      MessageDialog.openInformation(window.getShell(), "OpenBiomind-GUI", "Clusterize Task");
      return null;
   }

}
