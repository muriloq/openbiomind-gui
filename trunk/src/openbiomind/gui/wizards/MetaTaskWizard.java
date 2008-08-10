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
   private final MetaTaskWizardPage META_TASK_WIZ_PAGE = new MetaTaskWizardPage();

   /**
    * Instantiates a new meta task wizard.
    */
   public MetaTaskWizard() {
      super(Messages.MetaWiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(this.META_TASK_WIZ_PAGE);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      return new AbstractTaskData[] { this.META_TASK_WIZ_PAGE.prepareTaskData() };
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getFirstWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getFirstWizardPage() {
      return this.META_TASK_WIZ_PAGE;
   }

}
