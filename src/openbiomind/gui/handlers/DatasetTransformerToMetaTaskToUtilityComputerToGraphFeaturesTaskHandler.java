/**
 * DatasetTransformerToMetaTaskToUtilityComputerToGraphFeaturesTaskHandler.java
 *
 * The file DatasetTransformerToMetaTaskToUtilityComputerToGraphFeaturesTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.wizards.DatasetTransformerToMetaTaskToUtilityComputerToGraphFeaturesWizard;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * The class DatasetTransformerToMetaTaskToUtilityComputerToGraphFeaturesTaskHandler.
 *
 * @author bsanghvi
 * @since Aug 10, 2008
 * @version Aug 10, 2008
 */
public class DatasetTransformerToMetaTaskToUtilityComputerToGraphFeaturesTaskHandler extends AbstractTaskHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      return execute(event, new DatasetTransformerToMetaTaskToUtilityComputerToGraphFeaturesWizard());
   }

}
