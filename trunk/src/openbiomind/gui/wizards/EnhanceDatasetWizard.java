/**
 * EnhanceDatasetWizard.java
 *
 * The file EnhanceDatasetWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;
import openbiomind.gui.data.EnhanceDatasetTaskData;

/**
 * The class EnhanceDatasetWizard.
 *
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Aug 9, 2008
 */
public class EnhanceDatasetWizard extends AbstractTaskWizard {

   /** The enhance dataset task data. */
   private EnhanceDatasetTaskData enhanceDatasetTaskData = null;

   /** The enhance dataset wizard page. */
   private EnhanceDatasetWizardPage enhanceDatasetWizardPage = null;

   /**
    * Instantiates a new enhance dataset wizard.
    */
   public EnhanceDatasetWizard() {
      super(Messages.EnhDataWiz_Name);
      this.enhanceDatasetTaskData = new EnhanceDatasetTaskData();
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(getEnhanceDatasetWizardPage());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#prepareTaskData()
    */
   @Override
   protected void prepareTaskData() {
      getEnhanceDatasetTaskData().setInputDataset(getEnhanceDatasetWizardPage().getInputDataset());
      getEnhanceDatasetTaskData().setEnhancedDataset(getEnhanceDatasetWizardPage().getEnhancedDataset());
      getEnhanceDatasetTaskData()
            .setOntologyDescriptionFile(getEnhanceDatasetWizardPage().getOntologyDescriptionFile());
      getEnhanceDatasetTaskData()
            .setOntologyAssociationFile(getEnhanceDatasetWizardPage().getOntologyAssociationFile());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      return new AbstractTaskData[] { getEnhanceDatasetTaskData() };
   }

   /**
    * Gets the enhance dataset task data.
    *
    * @return the enhance dataset task data
    */
   private EnhanceDatasetTaskData getEnhanceDatasetTaskData() {
      return this.enhanceDatasetTaskData;
   }

   /**
    * Gets the enhance dataset wizard page.
    *
    * @return the enhance dataset wizard page
    */
   private EnhanceDatasetWizardPage getEnhanceDatasetWizardPage() {
      if (this.enhanceDatasetWizardPage == null) {
         this.enhanceDatasetWizardPage = new EnhanceDatasetWizardPage(Messages.EnhDataWiz_Name,
               Messages.EnhDataWiz_Desc);
      }

      return this.enhanceDatasetWizardPage;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getWizardPage() {
      return getEnhanceDatasetWizardPage();
   }

}
