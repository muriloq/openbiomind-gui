/**
 * AbstractTaskHandler.java
 *
 * The file AbstractTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.preferences.Preference;
import openbiomind.gui.util.Constants;
import openbiomind.gui.util.Messages;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * The class AbstractTaskHandler.
 *
 * @author bsanghvi
 * @since Jun 14, 2008
 * @version Jun 27, 2008
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
      if (!Preference.isPreferenceValid()) {
         MessageDialog.openError(shell, Resources.ERROR, Messages.Error_Preference);
         return false;
      }

      return true;
   }

}
