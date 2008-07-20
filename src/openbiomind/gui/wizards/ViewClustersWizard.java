/**
 * ViewClustersWizard.java
 *
 * The file ViewClustersWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.tasks.AbstractTaskData;
import openbiomind.gui.tasks.ViewClustersTaskData;

/**
 * The class ViewClustersWizard.
 *
 * @author bsanghvi
 * @since Jul 20, 2008
 * @version Jul 20, 2008
 */
public class ViewClustersWizard extends AbstractTaskWizard {

   /** The view clusters task data. */
   private ViewClustersTaskData viewClustersTaskData = null;

   /** The view clusters wizard page. */
   private ViewClustersWizardPage viewClustersWizardPage = null;

   /**
    * Instantiates a new utility computer wizard.
    */
   public ViewClustersWizard() {
      super(WizardMessages.ViewClustersWizard_Name);
      this.viewClustersTaskData = new ViewClustersTaskData();
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(getViewClustersWizardPage());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#prepareTaskData()
    */
   @Override
   protected void prepareTaskData() {
      getViewClustersTaskData().setProjectName(getViewClustersWizardPage().getProjectName());
      getViewClustersTaskData().setClusteringDataset(getViewClustersWizardPage().getClusteringDataset());
      getViewClustersTaskData().setClusteringResult(getViewClustersWizardPage().getClusteringResult());
      getViewClustersTaskData().setImageFile(getViewClustersWizardPage().getImageFile());
      getViewClustersTaskData().setClusteringColors(getViewClustersWizardPage().getClusteringColors());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData getTaskData() {
      return getViewClustersTaskData();
   }

   /**
    * Gets the view clusters task data.
    *
    * @return the view clusters task data
    */
   private ViewClustersTaskData getViewClustersTaskData() {
      return this.viewClustersTaskData;
   }

   /**
    * Gets the view clusters wizard page.
    *
    * @return the view clusters wizard page
    */
   private ViewClustersWizardPage getViewClustersWizardPage() {
      if (this.viewClustersWizardPage == null) {
         this.viewClustersWizardPage = new ViewClustersWizardPage(WizardMessages.ViewClustersWizard_Name,
               WizardMessages.ViewClustersWizard_Description);
      }

      return this.viewClustersWizardPage;
   }

}
