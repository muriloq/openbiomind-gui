/**
 * ClusterizeToViewClustersTaskHandler.java
 *
 * The file ClusterizeToViewClustersTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.wizards.ClusterizeToViewClustersWizard;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * The class ClusterizeToViewClustersTaskHandler.
 *
 * @author bsanghvi
 * @since Aug 10, 2008
 * @version Aug 10, 2008
 */
public class ClusterizeToViewClustersTaskHandler extends AbstractTaskHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      return execute(event, new ClusterizeToViewClustersWizard());
   }

}
