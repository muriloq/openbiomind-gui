/**
 * UtilityComputerTaskHandler.java
 *
 * The file UtilityComputerTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.wizards.UtilityComputerWizard;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * The class UtilityComputerTaskHandler.
 *
 * @author bsanghvi
 * @since Jun 27, 2008
 * @version Jul 10, 2008
 */
public class UtilityComputerTaskHandler extends AbstractTaskHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      return execute(event, new UtilityComputerWizard());
   }

}
