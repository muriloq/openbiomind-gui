/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (ApplicationActionBarAdvisor.java) is part of OpenBiomind-GUI.
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

import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * The class ApplicationActionBarAdvisor. To add menus through programming override the protected method fillMenuBar()
 * of {@link ActionBarAdvisor} as shown below: <br /> <code>
 * protected void fillMenuBar(final IMenuManager menuBar) {<br />
 * &nbsp;&nbsp; // Menu that must appear before the action sets must be added here<br />
 * <br />
 * &nbsp;&nbsp; // Set marker for Action Sets to appear here onwards<br />
 * &nbsp;&nbsp; menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));<br />
 * <br />
 * &nbsp;&nbsp; // Menu that must appear after the action sets must be added here<br />
 * }</code>
 * 
 * @author bsanghvi
 * @since Jun 1, 2008
 * @version Aug 18, 2008
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

}
