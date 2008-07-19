/**
 * ClusterizeTaskHandler.java
 *
 * The file ClusterizeTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.wizards.ClusterizeWizard;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * The class ClusterizeTaskHandler.
 *
 * @author bsanghvi
 * @since Jun 27, 2008
 * @version Jun 18, 2008
 */
public class ClusterizeTaskHandler extends AbstractTaskHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      return execute(event, new ClusterizeWizard());
   }

}
