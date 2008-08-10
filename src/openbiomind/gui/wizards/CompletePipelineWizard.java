/**
 * CompletePipelineWizard.java
 *
 * The file CompletePipelineWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import java.io.File;
import java.io.IOException;
import java.util.List;

import openbiomind.gui.console.Console;
import openbiomind.gui.data.AbstractTaskData;
import openbiomind.gui.data.CompletePipelineTaskData;
import openbiomind.gui.main.GraphvizHelper;
import openbiomind.gui.preferences.Preference;
import openbiomind.gui.util.Utility;

/**
 * The class CompletePipelineWizard.
 *
 * @author bsanghvi
 * @since Jul 31, 2008
 * @version Aug 9, 2008
 */
public class CompletePipelineWizard extends AbstractTaskWizard {

   /** The complete pipeline wizard page 1. */
   private CompletePipelineWizardPage1 completePipelineWizardPage1 = null;

   /** The complete pipeline wizard page 2. */
   private CompletePipelineWizardPage2 completePipelineWizardPage2 = null;

   /** The image path. */
   private String imagePath = null;

   /**
    * Instantiates a new complete pipeline wizard.
    */
   public CompletePipelineWizard() {
      super(Messages.CompPipeWiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(getCompletePipelineWizardPage1());
      addPage(getCompletePipelineWizardPage2());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      final CompletePipelineTaskData page1TaskData = getCompletePipelineWizardPage1().prepareTaskData();

      final CompletePipelineTaskData completePipelineTaskData;
      if (getCompletePipelineWizardPage2().isCurrentPage()) {
         final CompletePipelineTaskData page2TaskData = getCompletePipelineWizardPage2().prepareTaskData();
         page2TaskData.setInputDataset(page1TaskData.getInputDataset());
         page2TaskData.setOutputDirectory(page1TaskData.getOutputDirectory());
         page2TaskData.setTestDataset(page1TaskData.getTestDataset());
         page2TaskData.setPropertyFile(page1TaskData.getPropertyFile());
         completePipelineTaskData = page2TaskData;
      } else {
         completePipelineTaskData = page1TaskData;
      }
      completePipelineTaskData.setGraphImagePath(getImagePath());

      return new AbstractTaskData[] { completePipelineTaskData };
   }

   /**
    * Gets the complete pipeline wizard page 1.
    *
    * @return the complete pipeline wizard page 1
    */
   private CompletePipelineWizardPage1 getCompletePipelineWizardPage1() {
      if (this.completePipelineWizardPage1 == null) {
         this.completePipelineWizardPage1 = new CompletePipelineWizardPage1(Messages.CompPipeWiz_P1_Name,
               Messages.CompPipeWiz_P1_Desc);
      }

      return this.completePipelineWizardPage1;
   }

   /**
    * Gets the complete pipeline wizard page 2.
    *
    * @return the complete pipeline wizard page 2
    */
   private CompletePipelineWizardPage2 getCompletePipelineWizardPage2() {
      if (this.completePipelineWizardPage2 == null) {
         this.completePipelineWizardPage2 = new CompletePipelineWizardPage2(Messages.CompPipeWiz_P2_Name, null);
      }

      return this.completePipelineWizardPage2;
   }

   /**
    * Gets the image path.
    *
    * @return the image path
    */
   private String getImagePath() {
      return this.imagePath;
   }

   /**
    * Sets the image path.
    *
    * @param imagePath the new image path
    */
   private void setImagePath(final String imagePath) {
      this.imagePath = imagePath;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getPostSuccessfulExecutionProcess()
    */
   @Override
   protected Process getPostSuccessfulExecutionProcess() {
      if (Preference.isGraphvizDotUtilityPreferenceSet()) {
         final String outputFormat = Resources.PNG_FORMAT;
         final String sourceDotPath = getCompletePipelineWizardPage1().getOutputDirectory() + File.separator
               + Resources.GRAPH_DOT_FILENAME;

         try {
            setImagePath(File.createTempFile(Utility.extractFileName(sourceDotPath) + DOT + outputFormat + DOT, EMPTY)
                  .getAbsolutePath());

            final List<String> dotCommandList = GraphvizHelper.createDotCommand(sourceDotPath, getImagePath(),
                  outputFormat);
            if (dotCommandList != null) {
               return (new ProcessBuilder(dotCommandList)).start();
            }
         } catch (final IOException e) {
            Console.debug(e);
         }
      }

      Console.info(Messages.Err_GraphvizDotUtility);
      return super.getPostSuccessfulExecutionProcess();
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getWizardPage() {
      return getCompletePipelineWizardPage1();
   }

}
