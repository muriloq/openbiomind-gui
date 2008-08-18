/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (VisitOpenBiomindGUIHandler.java) is part of OpenBiomind-GUI.
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

package openbiomind.gui.handlers;

import openbiomind.gui.common.Constants;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.program.Program;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The class VisitOpenBiomindGUIHandler is used to open OpenBiomind-GUI homepage in external browser.
 * 
 * @author bsanghvi
 * @since Jul 4, 2008
 * @version Aug 18, 2008
 */
public class VisitOpenBiomindGUIHandler extends AbstractHandler implements Constants {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      if (!Program.launch(Resources.OPENBIOMIND_GUI_HOMEPAGE)) {
         MessageDialog.openError(HandlerUtil.getActiveWorkbenchWindowChecked(event).getShell(), Resources.ERROR, NLS
               .bind(Messages.Err_UnableToOpen, Resources.OPENBIOMIND_GUI_HOMEPAGE));
      }
      return null;
   }

}
