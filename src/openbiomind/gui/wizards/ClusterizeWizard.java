/**
 * ClusterizeWizard.java
 *
 * The file ClusterizeWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.tasks.AbstractTaskData;
import openbiomind.gui.tasks.ClusterizeTaskData;

/**
 * The class ClusterizeWizard.
 *
 * @author bsanghvi
 * @since Jul 18, 2008
 * @version Jul 18, 2008
 */
public class ClusterizeWizard extends AbstractTaskWizard {

   /** The clusterize task data. */
   private ClusterizeTaskData clusterizeTaskData = null;

   /** The clusterize wizard page. */
   private ClusterizeWizardPage clusterizeWizardPage = null;

   /**
    * Instantiates a new clusterize wizard.
    */
   public ClusterizeWizard() {
      super(WizardMessages.ClusterizeWizard_Name);
      this.clusterizeTaskData = new ClusterizeTaskData();
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(getClusterizeWizardPage());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#prepareTaskData()
    */
   @Override
   protected void prepareTaskData() {
      getClusterizeTaskData().setProjectName(getClusterizeWizardPage().getProjectName());
      getClusterizeTaskData().setClusteringDatasetFile(getClusterizeWizardPage().getClusteringDatasetFile());
      getClusterizeTaskData().setOutputFile(getClusterizeWizardPage().getOutputFile());
      getClusterizeTaskData().setDatasetClusteringMetric(getClusterizeWizardPage().getDatasetClusteringMetric());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData getTaskData() {
      return getClusterizeTaskData();
   }

   /**
    * Gets the clusterize task data.
    *
    * @return the clusterize task data
    */
   private ClusterizeTaskData getClusterizeTaskData() {
      return this.clusterizeTaskData;
   }

   /**
    * Gets the clusterize wizard page.
    *
    * @return the clusterize wizard page
    */
   private ClusterizeWizardPage getClusterizeWizardPage() {
      if (this.clusterizeWizardPage == null) {
         this.clusterizeWizardPage = new ClusterizeWizardPage(WizardMessages.ClusterizeWizard_Name,
               WizardMessages.ClusterizeWizard_Description);
      }

      return this.clusterizeWizardPage;
   }

}
