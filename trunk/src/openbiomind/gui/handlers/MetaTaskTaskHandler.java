/**
 * MetaTaskTaskHandler.java
 *
 * The file MetaTaskTaskHandler.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import openbiomind.gui.wizards.MetaTaskWizard;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * The class MetaTaskTaskHandler.
 *
 * @author bsanghvi
 * @since Jun 27, 2008
 * @version Jul 9, 2008
 */
public class MetaTaskTaskHandler extends AbstractTaskHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      return execute(event, new MetaTaskWizard());
   }

}
