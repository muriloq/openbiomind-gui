/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (AbstractTaskHandler.java) is part of OpenBiomind-GUI.
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
import openbiomind.gui.preferences.Preference;
import openbiomind.gui.wizards.AbstractTaskWizard;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The class AbstractTaskHandler.
 * 
 * @author bsanghvi
 * @since Jun 14, 2008
 * @version Aug 18, 2008
 */
public abstract class AbstractTaskHandler extends AbstractHandler implements Constants {

   /**
    * Validate the initial settings.
    * 
    * @param shell the shell
    * 
    * @return true, if successful
    */
   protected boolean validate(final Shell shell) {
      if (!Preference.isRequiredPreferenceSet()) {
         MessageDialog.openError(shell, Resources.ERROR, Messages.Err_PrefNotSet);
         return false;
      }

      return true;
   }

   /**
    * Execute utility method.
    * 
    * @param event the event
    * @param abstractTaskWizard the abstract task wizard
    * 
    * @return the object
    * 
    * @throws ExecutionException the execution exception
    */
   protected Object execute(final ExecutionEvent event, final AbstractTaskWizard abstractTaskWizard)
         throws ExecutionException {
      final Shell shell = HandlerUtil.getActiveWorkbenchWindowChecked(event).getShell();
      if (validate(shell)) {
         new WizardDialog(shell, abstractTaskWizard).open();
      }

      return null;
   }

}
