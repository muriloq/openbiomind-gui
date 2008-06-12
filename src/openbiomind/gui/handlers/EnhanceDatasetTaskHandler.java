/**
 * EnhanceDatasetTaskHandler.java
 *
 * The file EnhanceDatasetTaskHandler.java.
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
      final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
//      System.out.println("pref: "
//            + Activator.getDefault().getPreferenceStore().getString(
//                  PreferenceConstants.OPENBIOMIND_JAR));
//      System.out.println("pref: "
//            + Activator.getDefault().getPreferenceStore().getString(
//                  PreferenceConstants.PIPELINE_PROPERTIES));
      MessageDialog.openInformation(window.getShell(), "OpenBiomind-GUI", "Enhance Dataset Task");
      return null;
   }

}
