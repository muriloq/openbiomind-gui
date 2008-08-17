/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (ApplicationWorkbenchWindowAdvisor.java) is part of OpenBiomind-GUI.
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
 * @version Aug 18, 2008
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
