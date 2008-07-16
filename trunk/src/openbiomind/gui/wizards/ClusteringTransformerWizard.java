/**
 * ClusteringTransformerWizard.java
 *
 * The file ClusteringTransformerWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.tasks.AbstractTaskData;
import openbiomind.gui.tasks.ClusteringTransformerTaskData;

/**
 * The class ClusteringTransformerWizard.
 *
 * @author bsanghvi
 * @since Jul 13, 2008
 * @version Jul 16, 2008
 */
public class ClusteringTransformerWizard extends AbstractTaskWizard {

   /** The clustering transformer task data. */
   private ClusteringTransformerTaskData clusteringTransformerTaskData = null;

   /** The clustering transformer wizard page. */
   private ClusteringTransformerWizardPage clusteringTransformerWizardPage = null;

   /**
    * Instantiates a new utility computer wizard.
    */
   public ClusteringTransformerWizard() {
      super(WizardMessages.ClusteringTransformerWizard_Name);
      this.clusteringTransformerTaskData = new ClusteringTransformerTaskData();
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(getClusteringTransformerWizardPage());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#prepareTaskData()
    */
   @Override
   protected void prepareTaskData() {
      getClusteringTransformerTaskData().setProjectName(getClusteringTransformerWizardPage().getProjectName());
      getClusteringTransformerTaskData().setDatasetFile(getClusteringTransformerWizardPage().getDatasetFile());
      getClusteringTransformerTaskData().setOutputFile(getClusteringTransformerWizardPage().getOutputFile());
      getClusteringTransformerTaskData().setTransform(getClusteringTransformerWizardPage().getTransform());
      getClusteringTransformerTaskData().setMetaTaskResultDir(
            getClusteringTransformerWizardPage().getMetaTaskResultDir());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData getTaskData() {
      return getClusteringTransformerTaskData();
   }

   /**
    * Gets the clustering transformer task data.
    *
    * @return the clustering transformer task data
    */
   private ClusteringTransformerTaskData getClusteringTransformerTaskData() {
      return this.clusteringTransformerTaskData;
   }

   /**
    * Gets the clustering transformer wizard page.
    *
    * @return the clustering transformer wizard page
    */
   private ClusteringTransformerWizardPage getClusteringTransformerWizardPage() {
      if (this.clusteringTransformerWizardPage == null) {
         this.clusteringTransformerWizardPage = new ClusteringTransformerWizardPage(
               WizardMessages.ClusteringTransformerWizard_Name, WizardMessages.ClusteringTransformerWizard_Description);
      }

      return this.clusteringTransformerWizardPage;
   }

}
