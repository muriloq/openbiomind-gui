/**
 * ClusteringTransformerToClusterizeWizard.java
 *
 * The file ClusteringTransformerToClusterizeWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;

/**
 * The class ClusteringTransformerToClusterizeWizard.
 *
 * @author bsanghvi
 * @since Aug 10, 2008
 * @version Aug 10, 2008
 */
public class ClusteringTransformerToClusterizeWizard extends AbstractTaskWizard {

   /** The clustering transformer wizard page. */
   private final ClusteringTransformerWizardPage CLUSTERING_TRANSFORMER_WIZ_PAGE = new ClusteringTransformerWizardPage();

   /** The clusterize wizard page. */
   private final ClusterizeWizardPage CLUSTERIZE_WIZ_PAGE = new ClusterizeWizardPage();

   /**
    * Instantiates a new clusterize from clustering transformer wizard.
    */
   public ClusteringTransformerToClusterizeWizard() {
      super(Messages.ClustTrans_Clust_Wiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(this.CLUSTERING_TRANSFORMER_WIZ_PAGE);
      addPage(this.CLUSTERIZE_WIZ_PAGE);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      if (this.CLUSTERIZE_WIZ_PAGE.isCurrentPage()) {
         return new AbstractTaskData[] { this.CLUSTERING_TRANSFORMER_WIZ_PAGE.prepareTaskData(),
               this.CLUSTERIZE_WIZ_PAGE.prepareTaskData() };
      } else {
         return new AbstractTaskData[] { this.CLUSTERING_TRANSFORMER_WIZ_PAGE.prepareTaskData() };
      }
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getFirstWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getFirstWizardPage() {
      return this.CLUSTERING_TRANSFORMER_WIZ_PAGE;
   }

}
