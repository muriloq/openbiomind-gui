/**
 * DatasetTransformerWizard.java
 *
 * The file DatasetTransformerWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.tasks.AbstractTaskData;
import openbiomind.gui.tasks.DatasetTransformerTaskData;

/**
 * The class DatasetTransformerWizard.
 *
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Jun 27, 2008
 */
public class DatasetTransformerWizard extends AbstractTaskWizard {

   /** The dataset transformer task data. */
   private DatasetTransformerTaskData datasetTransformerTaskData = null;

   /** The dataset transformer wizard page. */
   private DatasetTransformerWizardPage datasetTransformerWizardPage = null;

   /**
    * Instantiates a new enhance dataset wizard.
    */
   public DatasetTransformerWizard() {
      super(WizardMessages.DatasetTransformerWizard_Name);
      this.datasetTransformerTaskData = new DatasetTransformerTaskData();
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(getDatasetTransformerWizardPage());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#prepareTaskData()
    */
   @Override
   protected void prepareTaskData() {
      getDatasetTransformerTaskData().setProjectName(getDatasetTransformerWizardPage().getProjectName());
      getDatasetTransformerTaskData()
            .setOriginalDataset(getDatasetTransformerWizardPage().getOriginalDatasetFilePath());
      getDatasetTransformerTaskData().setOutputDirectory(getDatasetTransformerWizardPage().getOutputDirectory());
      getDatasetTransformerTaskData().setTargetCategory(getDatasetTransformerWizardPage().getTargetCategory());
      if (getDatasetTransformerWizardPage().specifiedNumberOfFolds()) {
         getDatasetTransformerTaskData().setNumberOfFolds(getDatasetTransformerWizardPage().getNumberOfFolds());
      } else if (getDatasetTransformerWizardPage().specifiedTestDataset()) {
         getDatasetTransformerTaskData().setTestDataset(getDatasetTransformerWizardPage().getTestDataset());
      }
      getDatasetTransformerTaskData().setNumberOfSelectedFeatures(
            getDatasetTransformerWizardPage().getNumberOfSelectedFeatures());
      getDatasetTransformerTaskData().setFeatureSelectionMethod(
            getDatasetTransformerWizardPage().getFeatureSelectionMethod());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData getTaskData() {
      return getDatasetTransformerTaskData();
   }

   /**
    * Gets the dataset transformer task data.
    *
    * @return the dataset transformer task data
    */
   private DatasetTransformerTaskData getDatasetTransformerTaskData() {
      return this.datasetTransformerTaskData;
   }

   /**
    * Gets the dataset transformer wizard page.
    *
    * @return the dataset transformer wizard page
    */
   private DatasetTransformerWizardPage getDatasetTransformerWizardPage() {
      if (this.datasetTransformerWizardPage == null) {
         this.datasetTransformerWizardPage = new DatasetTransformerWizardPage(
               WizardMessages.DatasetTransformerWizard_Name, WizardMessages.DatasetTransformerWizard_Desc);
      }

      return this.datasetTransformerWizardPage;
   }

}
