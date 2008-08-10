/**
 * ClusteringTransformerWizard.java
 *
 * The file ClusteringTransformerWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;

/**
 * The class ClusteringTransformerWizard.
 *
 * @author bsanghvi
 * @since Jul 13, 2008
 * @version Aug 10, 2008
 */
public class ClusteringTransformerWizard extends AbstractTaskWizard {

   /** The clustering transformer wizard page. */
   private final ClusteringTransformerWizardPage CLUSTERING_TRANSFORMER_WIZ_PAGE = new ClusteringTransformerWizardPage();

   /**
    * Instantiates a new clustering transformer wizard.
    */
   public ClusteringTransformerWizard() {
      super(Messages.ClustTransWiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(this.CLUSTERING_TRANSFORMER_WIZ_PAGE);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      return new AbstractTaskData[] { this.CLUSTERING_TRANSFORMER_WIZ_PAGE.prepareTaskData() };
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getFirstWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getFirstWizardPage() {
      return this.CLUSTERING_TRANSFORMER_WIZ_PAGE;
   }

}
