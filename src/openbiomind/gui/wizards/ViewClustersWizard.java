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
 * @version Aug 10, 2008
 */
public class ViewClustersWizard extends AbstractTaskWizard {

   /** The view clusters wizard page. */
   private final ViewClustersWizardPage VIEW_CLUSTERS_WIZ_PAGE = new ViewClustersWizardPage();

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
      addPage(this.VIEW_CLUSTERS_WIZ_PAGE);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      return new AbstractTaskData[] { this.VIEW_CLUSTERS_WIZ_PAGE.prepareTaskData() };
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getFirstWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getFirstWizardPage() {
      return this.VIEW_CLUSTERS_WIZ_PAGE;
   }

}
