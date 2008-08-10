/**
 * UtilityComputerWizard.java
 *
 * The file UtilityComputerWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;

/**
 * The class UtilityComputerWizard.
 *
 * @author bsanghvi
 * @since Jul 9, 2008
 * @version Aug 9, 2008
 */
public class UtilityComputerWizard extends AbstractTaskWizard {

   /** The utility computer wizard page. */
   private UtilityComputerWizardPage utilityComputerWizardPage = null;

   /**
    * Instantiates a new utility computer wizard.
    */
   public UtilityComputerWizard() {
      super(Messages.UtilCompWiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(getUtilityComputerWizardPage());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      return new AbstractTaskData[] { getUtilityComputerWizardPage().prepareTaskData() };
   }

   /**
    * Gets the utility computer wizard page.
    *
    * @return the utility computer wizard page
    */
   private UtilityComputerWizardPage getUtilityComputerWizardPage() {
      if (this.utilityComputerWizardPage == null) {
         this.utilityComputerWizardPage = new UtilityComputerWizardPage(Messages.UtilCompWiz_Name,
               Messages.UtilCompWiz_Desc);
      }

      return this.utilityComputerWizardPage;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getWizardPage() {
      return getUtilityComputerWizardPage();
   }

}
