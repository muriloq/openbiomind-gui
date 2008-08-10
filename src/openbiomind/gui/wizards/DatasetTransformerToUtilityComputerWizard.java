/**
 * DatasetTransformerToUtilityComputerWizard.java
 *
 * The file DatasetTransformerToUtilityComputerWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;

/**
 * The class DatasetTransformerToUtilityComputerWizard.
 *
 * @author bsanghvi
 * @since Aug 10, 2008
 * @version Aug 10, 2008
 */
public class DatasetTransformerToUtilityComputerWizard extends AbstractTaskWizard {

   /** The dataset transformer wizard page. */
   private final DatasetTransformerWizardPage DATASET_TRANSFORMER_WIZ_PAGE = new DatasetTransformerWizardPage();

   /** The utility computer wizard page. */
   private final UtilityComputerWizardPage UTILITY_COMPUTER_WIZ_PAGE = new UtilityComputerWizardPage();

   /**
    * Instantiates a new utility computer from dataset transformer wizard.
    */
   public DatasetTransformerToUtilityComputerWizard() {
      super(Messages.DataTrans_UtilComp_Wiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(this.DATASET_TRANSFORMER_WIZ_PAGE);
      addPage(this.UTILITY_COMPUTER_WIZ_PAGE);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      if (this.UTILITY_COMPUTER_WIZ_PAGE.isCurrentPage()) {
         return new AbstractTaskData[] { this.DATASET_TRANSFORMER_WIZ_PAGE.prepareTaskData(),
               this.UTILITY_COMPUTER_WIZ_PAGE.prepareTaskData() };
      } else {
         return new AbstractTaskData[] { this.DATASET_TRANSFORMER_WIZ_PAGE.prepareTaskData() };
      }
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getFirstWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getFirstWizardPage() {
      return this.DATASET_TRANSFORMER_WIZ_PAGE;
   }

}
