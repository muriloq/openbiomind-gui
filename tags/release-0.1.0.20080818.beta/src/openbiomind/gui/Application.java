/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (Application.java) is part of OpenBiomind-GUI.
 *
 *  OpenBiomind-GUI is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenBiomind-GUI is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenBiomind-GUI.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Please visit the following pages to contact the author(s):
 *  Homepage: http://code.google.com/p/openbiomind-gui/
 *  Mailing list: http://groups.google.com/group/openbiomind-gui/
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
 * @version Aug 18, 2008
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
         final int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
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
