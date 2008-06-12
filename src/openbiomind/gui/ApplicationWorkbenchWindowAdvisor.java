/**
 * ApplicationWorkbenchWindowAdvisor.java
 *
 * The file ApplicationWorkbenchWindowAdvisor.java.
 *
 * $Id$
 */
package openbiomind.gui;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/**
 * The class ApplicationWorkbenchWindowAdvisor.
 *
 * @author bsanghvi
 * @since Jun 1, 2008
 * @version Jun 1, 2008
 */
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

   /**
    * Instantiates a new application workbench window advisor.
    *
    * @param configurer the configurer
    */
   public ApplicationWorkbenchWindowAdvisor(final IWorkbenchWindowConfigurer configurer) {
      super(configurer);
   }

   /*
    * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#createActionBarAdvisor(org.eclipse.ui.
    * application.IActionBarConfigurer)
    */
   @Override
   public ActionBarAdvisor createActionBarAdvisor(final IActionBarConfigurer configurer) {
      return new ApplicationActionBarAdvisor(configurer);
   }

   /*
    * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#preWindowOpen()
    */
   @Override
   public void preWindowOpen() {
      final IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
      configurer.setInitialSize(new Point(800, 600));
      configurer.setShowMenuBar(true);
      configurer.setShowCoolBar(true);       // show the toolbar
      configurer.setShowStatusLine(true);
   }

}
