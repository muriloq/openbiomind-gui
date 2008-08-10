/**
 * DatasetTransformerWizard.java
 *
 * The file DatasetTransformerWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;

/**
 * The class DatasetTransformerWizard.
 *
 * @author bsanghvi
 * @since Jul 2, 2008
 * @version Aug 9, 2008
 */
public class DatasetTransformerWizard extends AbstractTaskWizard {

   /** The dataset transformer wizard page. */
   private DatasetTransformerWizardPage datasetTransformerWizardPage = null;

   /**
    * Instantiates a new enhance dataset wizard.
    */
   public DatasetTransformerWizard() {
      super(Messages.DataTransWiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(getDatasetTransformerWizardPage());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      return new AbstractTaskData[] { getDatasetTransformerWizardPage().prepareTaskData() };
   }

   /**
    * Gets the dataset transformer wizard page.
    *
    * @return the dataset transformer wizard page
    */
   private DatasetTransformerWizardPage getDatasetTransformerWizardPage() {
      if (this.datasetTransformerWizardPage == null) {
         this.datasetTransformerWizardPage = new DatasetTransformerWizardPage(Messages.DataTransWiz_Name,
               Messages.DataTransWiz_Desc);
      }

      return this.datasetTransformerWizardPage;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getWizardPage() {
      return getDatasetTransformerWizardPage();
   }

}
