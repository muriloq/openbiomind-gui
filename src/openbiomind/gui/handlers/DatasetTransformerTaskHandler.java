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

/**
 * The class DatasetTransformerHandler.
 *
 * @author bsanghvi
 * @since Jun 9, 2008
 * @version Jul 9, 2008
 */
public class DatasetTransformerTaskHandler extends AbstractTaskHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      return execute(event, new DatasetTransformerWizard());
   }

}
