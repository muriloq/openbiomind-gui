/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (MetaTaskToUtilityComputerWizard.java) is part of OpenBiomind-GUI.
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

package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;

/**
 * The class MetaTaskToUtilityComputerWizard.
 * 
 * @author bsanghvi
 * @since Aug 10, 2008
 * @version Aug 18, 2008
 */
public class MetaTaskToUtilityComputerWizard extends AbstractTaskWizard {

   /** The meta task wizard page. */
   private final MetaTaskWizardPage META_TASK_WIZ_PAGE = new MetaTaskWizardPage();

   /** The utility computer wizard page. */
   private final UtilityComputerWizardPage UTILITY_COMPUTER_WIZ_PAGE = new UtilityComputerWizardPage();

   /**
    * Instantiates a new utility computer from meta task wizard.
    */
   public MetaTaskToUtilityComputerWizard() {
      super(Messages.Meta_UtilComp_Wiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(this.META_TASK_WIZ_PAGE);
      addPage(this.UTILITY_COMPUTER_WIZ_PAGE);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      if (this.UTILITY_COMPUTER_WIZ_PAGE.isCurrentPage()) {
         return new AbstractTaskData[] { this.META_TASK_WIZ_PAGE.prepareTaskData(),
               this.UTILITY_COMPUTER_WIZ_PAGE.prepareTaskData() };
      } else {
         return new AbstractTaskData[] { this.META_TASK_WIZ_PAGE.prepareTaskData() };
      }
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getFirstWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getFirstWizardPage() {
      return this.META_TASK_WIZ_PAGE;
   }

}
