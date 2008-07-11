/**
 * UtilityComputerWizard.java
 *
 * The file UtilityComputerWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.tasks.AbstractTaskData;
import openbiomind.gui.tasks.UtilityComputerTaskData;

/**
 * The class UtilityComputerWizard.
 *
 * @author bsanghvi
 * @since Jul 9, 2008
 * @version Jul 9, 2008
 */
public class UtilityComputerWizard extends AbstractTaskWizard {

   /** The utility computer task data. */
   private UtilityComputerTaskData utilityComputerTaskData = null;

   /** The utility computer wizard page. */
   private UtilityComputerWizardPage utilityComputerWizardPage = null;

   /**
    * Instantiates a new utility computer wizard.
    */
   public UtilityComputerWizard() {
      super(WizardMessages.UtilityComputerWizard_Name);
      this.utilityComputerTaskData = new UtilityComputerTaskData();
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(getUtilityComputerWizardPage());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#prepareTaskData()
    */
   @Override
   protected void prepareTaskData() {
      getUtilityComputerTaskData().setProjectName(getUtilityComputerWizardPage().getProjectName());
      getUtilityComputerTaskData().setResultDir(getUtilityComputerWizardPage().getResultDir());
      getUtilityComputerTaskData().setOutputFile(getUtilityComputerWizardPage().getOutputFile());
      getUtilityComputerTaskData().setBaseDataset(getUtilityComputerWizardPage().getBaseDataset());
      getUtilityComputerTaskData().setTargetCategory(getUtilityComputerWizardPage().getTargetCategory());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData getTaskData() {
      return getUtilityComputerTaskData();
   }

   /**
    * Gets the utility computer task data.
    *
    * @return the utility computer task data
    */
   private UtilityComputerTaskData getUtilityComputerTaskData() {
      return this.utilityComputerTaskData;
   }

   /**
    * Gets the utility computer wizard page.
    *
    * @return the utility computer wizard page
    */
   private UtilityComputerWizardPage getUtilityComputerWizardPage() {
      if (this.utilityComputerWizardPage == null) {
         this.utilityComputerWizardPage = new UtilityComputerWizardPage(WizardMessages.UtilityComputerWizard_Name,
               WizardMessages.UtilityComputerWizard_Description);
      }

      return this.utilityComputerWizardPage;
   }

}
