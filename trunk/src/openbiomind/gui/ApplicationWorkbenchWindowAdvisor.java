/**
 * ApplicationWorkbenchWindowAdvisor.java
 *
 * The file ApplicationWorkbenchWindowAdvisor.java.
 *
 * $Id$
 */
package openbiomind.gui;

import openbiomind.gui.console.Console;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
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
 * @version Aug 16, 2008
 */
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

   /** The constant for initial width of the window. */
   private static final int WIDTH = 800;

   /** The constant for initial height of the window. */
   private static final int HEIGHT = 600;

   /**
    * Instantiates a new application workbench window advisor.
    * 
    * @param configurer the configurer
    */
   public ApplicationWorkbenchWindowAdvisor(final IWorkbenchWindowConfigurer configurer) {
      super(configurer);
   }

   /*
    * @see WorkbenchWindowAdvisor#createActionBarAdvisor(IActionBarConfigurer)
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
      configurer.setInitialSize(new Point(WIDTH, HEIGHT));
      configurer.setShowMenuBar(true);
      configurer.setShowCoolBar(true);
      configurer.setShowStatusLine(true);
   }

   /*
    * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#preWindowShellClose()
    */
   @Override
   public boolean preWindowShellClose() {
      try {
         // save the full workspace before quit
         ResourcesPlugin.getWorkspace().save(true, null);
      } catch (final CoreException e) {
         Console.debug(e);
      }

      return true;
   }

}
