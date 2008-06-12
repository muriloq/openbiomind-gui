/**
 * DatasetTransformerTaskHandler.java
 *
 * The file DatasetTransformerTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The class DatasetTransformerHandler.
 *
 * @author bsanghvi
 * @since Jun 9, 2008
 * @version Jun 9, 2008
 */
public class DatasetTransformerTaskHandler extends AbstractHandler {

   /*
    * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(ExecutionEvent event) throws ExecutionException {
      final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
      MessageDialog.openInformation(window.getShell(), "OpenBiomind-GUI",
            "Dataset Transformer Task");
      return null;
   }

}
