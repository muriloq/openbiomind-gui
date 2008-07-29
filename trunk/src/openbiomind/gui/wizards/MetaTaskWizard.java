/**
 * MetaTaskWizard.java
 *
 * The file MetaTaskWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;
import openbiomind.gui.data.MetaTaskTaskData;

/**
 * The class MetaTaskWizard.
 *
 * @author bsanghvi
 * @since Jul 5, 2008
 * @version Jul 28, 2008
 */
public class MetaTaskWizard extends AbstractTaskWizard {

   /** The meta task task data. */
   private MetaTaskTaskData metaTaskTaskData = null;

   /** The meta task wizard page. */
   private MetaTaskWizardPage metaTaskWizardPage = null;

   /**
    * Instantiates a new enhance dataset wizard.
    */
   public MetaTaskWizard() {
      super(Messages.MetaTaskWizard_Name);
      this.metaTaskTaskData = new MetaTaskTaskData();
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(getMetaTaskWizardPage());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#prepareTaskData()
    */
   @Override
   protected void prepareTaskData() {
      getMetaTaskTaskData().setProjectName(getMetaTaskWizardPage().getProjectName());
      getMetaTaskTaskData().setDatasetDirectory(getMetaTaskWizardPage().getDatasetDirectoryPath());
      getMetaTaskTaskData().setOutputDirectory(getMetaTaskWizardPage().getOutputDirectory());
      getMetaTaskTaskData().setNumberOfTasks(getMetaTaskWizardPage().getNumberOfTasks());
      getMetaTaskTaskData().setTargetCategory(getMetaTaskWizardPage().getTargetCategory());
      getMetaTaskTaskData().setClassficationMethod(getMetaTaskWizardPage().getClassificationMethod());
      getMetaTaskTaskData().setMetaTaskShuffling(getMetaTaskWizardPage().getMetaTaskShuffling());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData getTaskData() {
      return getMetaTaskTaskData();
   }

   /**
    * Gets the meta task task data.
    *
    * @return the meta task task data
    */
   private MetaTaskTaskData getMetaTaskTaskData() {
      return this.metaTaskTaskData;
   }

   /**
    * Gets the meta task wizard page.
    *
    * @return the meta task wizard page
    */
   private MetaTaskWizardPage getMetaTaskWizardPage() {
      if (this.metaTaskWizardPage == null) {
         this.metaTaskWizardPage = new MetaTaskWizardPage(Messages.MetaTaskWizard_Name,
               Messages.MetaTaskWizard_Description);
      }

      return this.metaTaskWizardPage;
   }

}
