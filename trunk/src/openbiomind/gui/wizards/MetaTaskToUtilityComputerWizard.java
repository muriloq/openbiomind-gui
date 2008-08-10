/**
 * MetaTaskToUtilityComputerWizard.java
 *
 * The file MetaTaskToUtilityComputerWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;

/**
 * The class MetaTaskToUtilityComputerWizard.
 *
 * @author bsanghvi
 * @since Aug 10, 2008
 * @version Aug 10, 2008
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
