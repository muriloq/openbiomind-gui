/**
 * UtilityComputerFromMetaTaskTaskHandler.java
 *
 * The file UtilityComputerFromMetaTaskTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.wizards.UtilityComputerFromMetaTaskWizard;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * The class UtilityComputerFromMetaTaskTaskHandler.
 *
 * @author bsanghvi
 * @since Aug 9, 2008
 * @version Aug 9, 2008
 */
public class UtilityComputerFromMetaTaskTaskHandler extends AbstractTaskHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      return execute(event, new UtilityComputerFromMetaTaskWizard());
   }

}
