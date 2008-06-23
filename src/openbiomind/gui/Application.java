/**
 * Application.java
 *
 * The file Application.java controls all aspects of the application's execution.
 *
 * $Id$
 */
package openbiomind.gui;

import openbiomind.gui.console.Console;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * This class Application controls all aspects of the application's execution.
 *
 * @author bsanghvi
 * @since Jun 1, 2008
 * @version Jun 1, 2008
 */
public class Application implements IApplication {

   /** The Constant Plug-in ID. */
   public static final String PLUGIN_ID = Activator.PLUGIN_ID;

   /*
    * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
    */
   @Override
   public Object start(final IApplicationContext context) throws Exception {
      // initialize the console
      Console.init();

      final Display display = PlatformUI.createDisplay();

      try {
         final int returnCode = PlatformUI.createAndRunWorkbench(display,
               new ApplicationWorkbenchAdvisor());
         if (returnCode == PlatformUI.RETURN_RESTART) {
            return IApplication.EXIT_RESTART;
         } else {
            return IApplication.EXIT_OK;
         }
      } finally {
         display.dispose();
      }
   }

   /*
    * @see org.eclipse.equinox.app.IApplication#stop()
    */
   @Override
   public void stop() {
      final IWorkbench workbench = PlatformUI.getWorkbench();
      if (workbench == null) {
         return;
      }

      final Display display = workbench.getDisplay();
      display.syncExec(new Runnable() {

         @Override
         public void run() {
            if (!display.isDisposed()) {
               workbench.close();
            }
         }

      });
   }

}
