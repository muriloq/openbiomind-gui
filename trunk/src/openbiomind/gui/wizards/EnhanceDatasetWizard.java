/**
 * EnhanceDatasetWizard.java
 *
 * The file EnhanceDatasetWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.tasks.EnhanceDatasetTaskData;


/**
 * The class EnhanceDatasetWizard.
 *
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Jun 13, 2008
 */
public class EnhanceDatasetWizard extends AbstractTaskWizard {

   /** The constant for page title (value = <code>Enhance Dataset Task</code>). */
   public static final String WIZARD_TITLE = "Enhance Dataset Task"; //$NON-NLS-1$

   /** The enhance dataset task data. */
   private EnhanceDatasetTaskData enhanceDatasetTaskData = null;

   /** The enhance dataset wizard page. */
   private EnhanceDatasetWizardPage enhanceDatasetWizardPage = null;

   /**
    * Instantiates a new enhance dataset wizard.
    */
   public EnhanceDatasetWizard() {
      super(WIZARD_TITLE);
      this.enhanceDatasetTaskData = new EnhanceDatasetTaskData();
   }

   @Override
   public void addPages() {
      this.enhanceDatasetWizardPage = new EnhanceDatasetWizardPage(WIZARD_TITLE, this.enhanceDatasetTaskData.getSyntax());

      addPage(this.enhanceDatasetWizardPage);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#performFinish()
    */
   @Override
   public boolean performFinish() {
      return true;
   }

}
