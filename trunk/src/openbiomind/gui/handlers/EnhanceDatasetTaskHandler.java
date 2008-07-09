/**
 * EnhanceDatasetTaskHandler.java
 *
 * The file EnhanceDatasetTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.wizards.EnhanceDatasetWizard;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * The class EnhanceDatasetHandler.
 *
 * @author bsanghvi
 * @since Jun 9, 2008
 * @version Jul 9, 2008
 */
public class EnhanceDatasetTaskHandler extends AbstractTaskHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      return execute(event, new EnhanceDatasetWizard());
   }

}
