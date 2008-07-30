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
 * @version Jul 30, 2008
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
      configurer.setShowCoolBar(true);
      configurer.setShowStatusLine(true);
   }

//   /*
//    * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#postWindowOpen()
//    */
//   @Override
//   public void postWindowOpen() {
//      // NOTE To write to status line, use following
//      // getWindowConfigurer().getActionBarConfigurer().getStatusLineManager().setMessage(...);
//   }

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
