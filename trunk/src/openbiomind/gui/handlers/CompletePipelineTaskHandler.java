/**
 * CompletePipelineTaskHandler.java
 *
 * The file CompletePipelineTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.wizards.CompletePipelineWizard;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * The class CompletePipelineTaskHandler.
 *
 * @author bsanghvi
 * @since Jun 27, 2008
 * @version Aug 3, 2008
 */
public class CompletePipelineTaskHandler extends AbstractTaskHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      return execute(event, new CompletePipelineWizard());
   }

}
