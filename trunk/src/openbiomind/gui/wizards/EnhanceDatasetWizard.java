/**
 * EnhanceDatasetWizard.java
 *
 * The file EnhanceDatasetWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.tasks.AbstractTaskData;
import openbiomind.gui.tasks.EnhanceDatasetTaskData;

/**
 * The class EnhanceDatasetWizard.
 *
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Jun 27, 2008
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
      super(WizardMessages.EnhanceDatasetWizard_Name);
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
      getEnhanceDatasetTaskData().setProjectName(getEnhanceDatasetWizardPage().getProjectName());
      getEnhanceDatasetTaskData().setOriginalDataset(getEnhanceDatasetWizardPage().getOriginalDatasetFilePath());
      getEnhanceDatasetTaskData().setEnhancedDataset(getEnhanceDatasetWizardPage().getEnhancedDatasetFilePath());
      getEnhanceDatasetTaskData().setOntologyDescriptionFile(
            getEnhanceDatasetWizardPage().getOntologyDescriptionFilePath());
      getEnhanceDatasetTaskData().setOntologyAssociationFile(
            getEnhanceDatasetWizardPage().getOntologyAssociationFilePath());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData getTaskData() {
      return getEnhanceDatasetTaskData();
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
         this.enhanceDatasetWizardPage = new EnhanceDatasetWizardPage(WizardMessages.EnhanceDatasetWizard_Name,
               WizardMessages.EnhanceDatasetWizard_Description);
      }

      return this.enhanceDatasetWizardPage;
   }

}
