/**
 * ClusterizeToViewClustersWizard.java
 *
 * The file ClusterizeToViewClustersWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;

/**
 * The class ClusterizeToViewClustersWizard.
 *
 * @author bsanghvi
 * @since Aug 10, 2008
 * @version Aug 10, 2008
 */
public class ClusterizeToViewClustersWizard extends AbstractTaskWizard {

   /** The clusterize wizard page. */
   private final ClusterizeWizardPage CLUSTERIZE_WIZ_PAGE = new ClusterizeWizardPage();

   /** The view clusters wizard page. */
   private final ViewClustersWizardPage VIEW_CLUSTERS_WIZ_PAGE = new ViewClustersWizardPage();

   /**
    * Instantiates a new clusterize from clustering transformer wizard.
    */
   public ClusterizeToViewClustersWizard() {
      super(Messages.Clust_ViewClust_Wiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(this.CLUSTERIZE_WIZ_PAGE);
      addPage(this.VIEW_CLUSTERS_WIZ_PAGE);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      if (this.VIEW_CLUSTERS_WIZ_PAGE.isCurrentPage()) {
         return new AbstractTaskData[] { this.CLUSTERIZE_WIZ_PAGE.prepareTaskData(),
               this.VIEW_CLUSTERS_WIZ_PAGE.prepareTaskData() };
      } else {
         return new AbstractTaskData[] { this.CLUSTERIZE_WIZ_PAGE.prepareTaskData() };
      }
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getFirstWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getFirstWizardPage() {
      return this.CLUSTERIZE_WIZ_PAGE;
   }

}
