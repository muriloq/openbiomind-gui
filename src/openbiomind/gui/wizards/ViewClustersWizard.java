/**
 * ViewClustersWizard.java
 *
 * The file ViewClustersWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;

/**
 * The class ViewClustersWizard.
 *
 * @author bsanghvi
 * @since Jul 20, 2008
 * @version Aug 9, 2008
 */
public class ViewClustersWizard extends AbstractTaskWizard {

   /** The view clusters wizard page. */
   private ViewClustersWizardPage viewClustersWizardPage = null;

   /**
    * Instantiates a new utility computer wizard.
    */
   public ViewClustersWizard() {
      super(Messages.ViewClustWiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(getViewClustersWizardPage());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      return new AbstractTaskData[] { getViewClustersWizardPage().prepareTaskData() };
   }

   /**
    * Gets the view clusters wizard page.
    *
    * @return the view clusters wizard page
    */
   private ViewClustersWizardPage getViewClustersWizardPage() {
      if (this.viewClustersWizardPage == null) {
         this.viewClustersWizardPage = new ViewClustersWizardPage(Messages.ViewClustWiz_Name,
               Messages.ViewClustWiz_Desc);
      }

      return this.viewClustersWizardPage;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getWizardPage() {
      return getViewClustersWizardPage();
   }

}
