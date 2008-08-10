/**
 * MetaTaskWizard.java
 *
 * The file MetaTaskWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;

/**
 * The class MetaTaskWizard.
 *
 * @author bsanghvi
 * @since Jul 5, 2008
 * @version Aug 9, 2008
 */
public class MetaTaskWizard extends AbstractTaskWizard {

   /** The meta task wizard page. */
   private MetaTaskWizardPage metaTaskWizardPage = null;

   /**
    * Instantiates a new enhance dataset wizard.
    */
   public MetaTaskWizard() {
      super(Messages.MetaWiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(getMetaTaskWizardPage());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      return new AbstractTaskData[] { getMetaTaskWizardPage().prepareTaskData() };
   }

   /**
    * Gets the meta task wizard page.
    *
    * @return the meta task wizard page
    */
   private MetaTaskWizardPage getMetaTaskWizardPage() {
      if (this.metaTaskWizardPage == null) {
         this.metaTaskWizardPage = new MetaTaskWizardPage(Messages.MetaWiz_Name, Messages.MetaWiz_Desc);
      }

      return this.metaTaskWizardPage;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getWizardPage() {
      return getMetaTaskWizardPage();
   }

}
