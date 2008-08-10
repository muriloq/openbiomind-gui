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
 * @version Aug 9, 2008
 */
public class ClusteringTransformerWizard extends AbstractTaskWizard {

   /** The clustering transformer wizard page. */
   private ClusteringTransformerWizardPage clusteringTransformerWizardPage = null;

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
      addPage(getClusteringTransformerWizardPage());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      return new AbstractTaskData[] { getClusteringTransformerWizardPage().prepareTaskData() };
   }

   /**
    * Gets the clustering transformer wizard page.
    *
    * @return the clustering transformer wizard page
    */
   private ClusteringTransformerWizardPage getClusteringTransformerWizardPage() {
      if (this.clusteringTransformerWizardPage == null) {
         this.clusteringTransformerWizardPage = new ClusteringTransformerWizardPage(Messages.ClustTransWiz_Name,
               Messages.ClustTransWiz_Desc);
      }

      return this.clusteringTransformerWizardPage;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getWizardPage() {
      return getClusteringTransformerWizardPage();
   }

}
