/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (OpenPipelineProperties.java) is part of OpenBiomind-GUI.
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

import java.io.File;

import openbiomind.gui.common.Constants;
import openbiomind.gui.console.Console;
import openbiomind.gui.preferences.Preference;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.ide.IDE;

/**
 * The class OpenPipelineProperties.
 * 
 * @author bsanghvi
 * @since Jul 8, 2008
 * @version Aug 18, 2008
 */
public class OpenPipelineProperties extends AbstractHandler {

   /*
    * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      // TODO Identify a way to open in Properties File Editor. The following editor must not be used, since it needs
      // dependency on JDT
      // Properties File Editor: org.eclipse.jdt.ui.PropertiesFileEditor
      final File file = new File(Preference.getPipelinePropertiesPath());
      try {
         IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), new FileStoreEditorInput(
               EFS.getLocalFileSystem().getStore(file.toURI())), Constants.Properties.DEFAULT_TEXT_EDITOR_ID);
      } catch (final PartInitException e) {
         Console.debug(e);
      }

      return null;
   }

   /*
    * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
    */
   @Override
   public boolean isEnabled() {
      return Preference.isPipelinePropertiesPreferenceValid();
   }

}
