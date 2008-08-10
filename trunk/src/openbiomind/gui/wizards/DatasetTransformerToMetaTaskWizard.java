/**
 * DatasetTransformerToMetaTaskWizard.java
 *
 * The file DatasetTransformerToMetaTaskWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;

/**
 * The class DatasetTransformerToMetaTaskWizard.
 *
 * @author bsanghvi
 * @since Aug 10, 2008
 * @version Aug 10, 2008
 */
public class DatasetTransformerToMetaTaskWizard extends AbstractTaskWizard {

   /** The dataset transformer wizard page. */
   private final DatasetTransformerWizardPage DATASET_TRANSFORMER_WIZ_PAGE = new DatasetTransformerWizardPage();

   /** The meta task wizard page. */
   private final MetaTaskWizardPage META_TASK_WIZ_PAGE = new MetaTaskWizardPage();

   /**
    * Instantiates a new meta task from dataset transformer wizard.
    */
   public DatasetTransformerToMetaTaskWizard() {
      super(Messages.DataTrans_Meta_Wiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(this.DATASET_TRANSFORMER_WIZ_PAGE);
      addPage(this.META_TASK_WIZ_PAGE);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      if (this.META_TASK_WIZ_PAGE.isCurrentPage()) {
         return new AbstractTaskData[] { this.DATASET_TRANSFORMER_WIZ_PAGE.prepareTaskData(),
               this.META_TASK_WIZ_PAGE.prepareTaskData() };
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
