/**
 * ApplicationActionBarAdvisor.java
 *
 * The file ApplicationActionBarAdvisor.java.
 *
 * $Id$
 */
package openbiomind.gui;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * The class ApplicationActionBarAdvisor.
 *
 * @author bsanghvi
 * @since Jun 1, 2008
 * @version Jun 1, 2008
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

   /**
    * Instantiates a new application action bar advisor.
    *
    * @param configurer the configurer
    */
   public ApplicationActionBarAdvisor(final IActionBarConfigurer configurer) {
      super(configurer);
   }

   /*
    * @see org.eclipse.ui.application.ActionBarAdvisor#makeActions(org.eclipse.ui.IWorkbenchWindow)
    */
   @Override
   protected void makeActions(final IWorkbenchWindow window) {
   }

   /*
    * @see
    * org.eclipse.ui.application.ActionBarAdvisor#fillMenuBar(org.eclipse.jface.action.IMenuManager)
    */
   @Override
   protected void fillMenuBar(final IMenuManager menuBar) {
      /*
       * Menu that must appear before the action sets must be added here
       */

      // Set marker for Action Sets to appear here onwards
      // menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

      /*
       * Menu that must appear after the action sets must be added here
       */
   }

}
