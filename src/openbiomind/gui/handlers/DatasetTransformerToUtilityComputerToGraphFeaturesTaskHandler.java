/**
 * DatasetTransformerToUtilityComputerToGraphFeaturesTaskHandler.java
 *
 * The file DatasetTransformerToUtilityComputerToGraphFeaturesTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.wizards.DatasetTransformerToUtilityComputerToGraphFeaturesWizard;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * The class DatasetTransformerToUtilityComputerToGraphFeaturesTaskHandler.
 *
 * @author bsanghvi
 * @since Aug 10, 2008
 * @version Aug 10, 2008
 */
public class DatasetTransformerToUtilityComputerToGraphFeaturesTaskHandler extends AbstractTaskHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      return execute(event, new DatasetTransformerToUtilityComputerToGraphFeaturesWizard());
   }

}
