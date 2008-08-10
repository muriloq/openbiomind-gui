/**
 * UtilityComputerFromMetaTaskWizard.java
 *
 * The file UtilityComputerFromMetaTaskWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;

/**
 * The class UtilityComputerFromMetaTaskWizard.
 *
 * @author bsanghvi
 * @since Aug 9, 2008
 * @version Aug 9, 2008
 */
public class UtilityComputerFromMetaTaskWizard extends AbstractTaskWizard {

   /** The meta task wizard page. */
   private MetaTaskWizardPage metaTaskWizardPage = null;

   /** The utility computer wizard page. */
   private UtilityComputerWizardPage utilityComputerWizardPage = null;

   /**
    * Instantiates a new enhance dataset wizard.
    */
   public UtilityComputerFromMetaTaskWizard() {
      super(Messages.UtilCompMetaWiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(getMetaTaskWizardPage());
      addPage(getUtilityComputerWizardPage());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      if (getUtilityComputerWizardPage().isCurrentPage()) {
         return new AbstractTaskData[] { getMetaTaskWizardPage().prepareTaskData(),
               getUtilityComputerWizardPage().prepareTaskData() };
      } else {
         return new AbstractTaskData[] { getMetaTaskWizardPage().prepareTaskData() };
      }
   }

   /**
    * Gets the meta task wizard page.
    *
    * @return the meta task wizard page
    */
   private MetaTaskWizardPage getMetaTaskWizardPage() {
      if (this.metaTaskWizardPage == null) {
         this.metaTaskWizardPage = new MetaTaskWizardPage(Messages.MetaWiz_Name, Messages.MetaWiz_Desc);
      }

      return this.metaTaskWizardPage;
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
      return getMetaTaskWizardPage();
   }

}
