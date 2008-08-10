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
   private final ClusterizeWizardPage CLUSTERIZE_WIZ_PAGE = new ClusterizeWizardPage();

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
      addPage(this.CLUSTERIZE_WIZ_PAGE);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      return new AbstractTaskData[] { this.CLUSTERIZE_WIZ_PAGE.prepareTaskData() };
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getFirstWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getFirstWizardPage() {
      return this.CLUSTERIZE_WIZ_PAGE;
   }

}
