/**
 * ClusteringTransformerToClusterizeToViewClustersWizard.java
 *
 * The file ClusteringTransformerToClusterizeToViewClustersWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;

/**
 * The class ClusteringTransformerToClusterizeToViewClustersWizard.
 *
 * @author bsanghvi
 * @since Aug 10, 2008
 * @version Aug 10, 2008
 */
public class ClusteringTransformerToClusterizeToViewClustersWizard extends AbstractTaskWizard {

   /** The clustering transformer wizard page. */
   private final ClusteringTransformerWizardPage CLUSTERING_TRANSFORMER_WIZ_PAGE = new ClusteringTransformerWizardPage();

   /** The clusterize wizard page. */
   private final ClusterizeWizardPage CLUSTERIZE_WIZ_PAGE = new ClusterizeWizardPage();

   /** The view clusters wizard page. */
   private final ViewClustersWizardPage VIEW_CLUSTERS_WIZ_PAGE = new ViewClustersWizardPage();

   /**
    * Instantiates a new clusterize from clustering transformer wizard.
    */
   public ClusteringTransformerToClusterizeToViewClustersWizard() {
      super(Messages.ClustTrans_Clust_ViewClust_Wiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(this.CLUSTERING_TRANSFORMER_WIZ_PAGE);
      addPage(this.CLUSTERIZE_WIZ_PAGE);
      addPage(this.VIEW_CLUSTERS_WIZ_PAGE);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      if (this.VIEW_CLUSTERS_WIZ_PAGE.isCurrentPage()) {
         return new AbstractTaskData[] { this.CLUSTERING_TRANSFORMER_WIZ_PAGE.prepareTaskData(),
               this.CLUSTERIZE_WIZ_PAGE.prepareTaskData(), this.VIEW_CLUSTERS_WIZ_PAGE.prepareTaskData() };
      } else if (this.CLUSTERIZE_WIZ_PAGE.isCurrentPage()) {
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
