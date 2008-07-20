/**
 * ViewClustersTaskHandler.java
 *
 * The file ViewClustersTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.wizards.ViewClustersWizard;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * The class ViewClustersTaskHandler.
 *
 * @author bsanghvi
 * @since Jun 27, 2008
 * @version Jul 20, 2008
 */
public class ViewClustersTaskHandler extends AbstractTaskHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      return execute(event, new ViewClustersWizard());
   }

}
