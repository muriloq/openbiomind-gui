/**
 * ClusterizeWizard.java
 *
 * The file ClusterizeWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;

/**
 * The class ClusterizeWizard.
 *
 * @author bsanghvi
 * @since Jul 18, 2008
 * @version Aug 9, 2008
 */
public class ClusterizeWizard extends AbstractTaskWizard {

   /** The clusterize wizard page. */
   private ClusterizeWizardPage clusterizeWizardPage = null;

   /**
    * Instantiates a new clusterize wizard.
    */
   public ClusterizeWizard() {
      super(Messages.ClustWiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(getClusterizeWizardPage());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      return new AbstractTaskData[] { getClusterizeWizardPage().prepareTaskData() };
   }

   /**
    * Gets the clusterize wizard page.
    *
    * @return the clusterize wizard page
    */
   private ClusterizeWizardPage getClusterizeWizardPage() {
      if (this.clusterizeWizardPage == null) {
         this.clusterizeWizardPage = new ClusterizeWizardPage(Messages.ClustWiz_Name, Messages.ClustWiz_Desc);
      }

      return this.clusterizeWizardPage;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getWizardPage() {
      return getClusterizeWizardPage();
   }

}
