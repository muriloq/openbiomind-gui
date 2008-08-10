/**
 * UtilityComputerToGraphFeaturesTaskHandler.java
 *
 * The file UtilityComputerToGraphFeaturesTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.wizards.UtilityComputerToGraphFeaturesWizard;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * The class UtilityComputerToGraphFeaturesTaskHandler.
 *
 * @author bsanghvi
 * @since Aug 10, 2008
 * @version Aug 10, 2008
 */
public class UtilityComputerToGraphFeaturesTaskHandler extends AbstractTaskHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      return execute(event, new UtilityComputerToGraphFeaturesWizard());
   }

}
