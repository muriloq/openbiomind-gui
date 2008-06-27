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
import openbiomind.gui.util.Messages;

/**
 * The class EnhanceDatasetWizard.
 *
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Jun 27, 2008
 */
public class EnhanceDatasetWizard extends AbstractTaskWizard {

   /** The constant for page title (value = <code>Enhance Dataset Task</code>). */
   public static final String WIZARD_TITLE = "Enhance Dataset Task"; //$NON-NLS-1$

   /** The enhance dataset task data. */
   private EnhanceDatasetTaskData enhanceDatasetTaskData = null;

   /** The enhance dataset wizard page. */
   private EnhanceDatasetWizardPage enhanceDatasetWizardPage = null;

   /**
    * Instantiates a new enhance dataset wizard.
    */
   public EnhanceDatasetWizard() {
      super(WIZARD_TITLE);
      this.enhanceDatasetTaskData = new EnhanceDatasetTaskData();
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      this.enhanceDatasetWizardPage = new EnhanceDatasetWizardPage(WIZARD_TITLE, Messages.Desc_Wizard_EnhanceDataset);

      addPage(this.enhanceDatasetWizardPage);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#prepareTaskData()
    */
   @Override
   protected void prepareTaskData() {
      getEnhanceDatasetTaskData().setOriginalDataset(this.enhanceDatasetWizardPage.getOriginalDatasetFilePath());
      getEnhanceDatasetTaskData().setEnhancedDataset(this.enhanceDatasetWizardPage.getEnhancedDatasetFilePath());
      getEnhanceDatasetTaskData().setOntologyDescriptionFile(
            this.enhanceDatasetWizardPage.getOntologyDescriptionFilePath());
      getEnhanceDatasetTaskData().setOntologyAssociationFile(
            this.enhanceDatasetWizardPage.getOntologyAssociationFilePath());
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

}
