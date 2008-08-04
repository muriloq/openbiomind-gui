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
 * @version Aug 3, 2008
 */
public class CompletePipelineWizard extends AbstractTaskWizard {

   /** The complete pipeline task data. */
   private CompletePipelineTaskData completePipelineTaskData = null;

   /** The complete pipeline wizard page 1. */
   private CompletePipelineWizardPage1 completePipelineWizardPage1 = null;

   /** The complete pipeline wizard page 2. */
   private CompletePipelineWizardPage2 completePipelineWizardPage2 = null;

   /**
    * Instantiates a new complete pipeline wizard.
    */
   public CompletePipelineWizard() {
      super(Messages.CompPipeWiz_Name);
      this.completePipelineTaskData = new CompletePipelineTaskData();
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
    * @see openbiomind.gui.wizards.AbstractTaskWizard#prepareTaskData()
    */
   @Override
   protected void prepareTaskData() {
      // page 1
      getCompletePipelineTaskData().setProjectName(getCompletePipelineWizardPage1().getProjectName());
      getCompletePipelineTaskData().setInputDataset(getCompletePipelineWizardPage1().getOriginalDatasetFilePath());
      getCompletePipelineTaskData().setOutputDirectory(getCompletePipelineWizardPage1().getOutputDirectory());
      getCompletePipelineTaskData().setTestDataset(getCompletePipelineWizardPage1().getTestDataset());
      getCompletePipelineTaskData().setPropertyFile(getCompletePipelineWizardPage1().getPropertyFile());

      // page 2
      getCompletePipelineTaskData().setMaximumCoOccurrenceEdges(
            getCompletePipelineWizardPage2().getMaximumCoOccurrenceEdges());
      getCompletePipelineTaskData().setMaximumCoExpressionEdges(
            getCompletePipelineWizardPage2().getMaximumCoExpressionEdges());
      getCompletePipelineTaskData().setTopUsefulFeatures(getCompletePipelineWizardPage2().getTopUsefulFeatures());
      getCompletePipelineTaskData().setNumberOfFolds(getCompletePipelineWizardPage2().getNumberOfFolds());
      getCompletePipelineTaskData().setNumberOfSelectedFeatures(
            getCompletePipelineWizardPage2().getNumberOfSelectedFeatures());
      getCompletePipelineTaskData().setFeatureSelectionMethod(
            getCompletePipelineWizardPage2().getFeatureSelectionMethod());
      getCompletePipelineTaskData().setOntologyDescriptionFile(
            getCompletePipelineWizardPage2().getOntologyDescriptionFile());
      getCompletePipelineTaskData().setOntologyAssociationFile(
            getCompletePipelineWizardPage2().getOntologyAssociationFile());
      getCompletePipelineTaskData().setNumberOfTasks(getCompletePipelineWizardPage2().getNumberOfTasks());
      getCompletePipelineTaskData().setTargetCategory(getCompletePipelineWizardPage2().getTargetCategory());
      getCompletePipelineTaskData().setClassficationMethod(getCompletePipelineWizardPage2().getClassificationMethod());
      getCompletePipelineTaskData().setMetaTaskShuffling(getCompletePipelineWizardPage2().getMetaTaskShuffling());
      getCompletePipelineTaskData().setDatasetClusteringMetric(
            getCompletePipelineWizardPage2().getDatasetClusteringMetric());
      getCompletePipelineTaskData().setClusteringColors(getCompletePipelineWizardPage2().getClusteringColors());
      getCompletePipelineTaskData().setIsFeatureSelected(getCompletePipelineWizardPage2().getIsFeatureSelected());
      getCompletePipelineTaskData().setIsFolded(getCompletePipelineWizardPage2().getIsFolded());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData getTaskData() {
      return getCompletePipelineTaskData();
   }

   /**
    * Gets the complete pipeline task data.
    *
    * @return the complete pipeline task data
    */
   private CompletePipelineTaskData getCompletePipelineTaskData() {
      return this.completePipelineTaskData;
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
            final String imagePath = File.createTempFile(
                  Utility.extractFileName(sourceDotPath) + DOT + outputFormat + DOT, EMPTY).getAbsolutePath();

            getCompletePipelineTaskData().setGraphImagePath(imagePath);

            final List<String> dotCommandList = GraphvizHelper.createDotCommand(sourceDotPath, imagePath, outputFormat);
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
    * @see org.eclipse.jface.wizard.Wizard#canFinish()
    */
   @Override
   public boolean canFinish() {
      return (getCompletePipelineWizardPage1().isPageComplete() && getCompletePipelineWizardPage2().isPageComplete());
   }

}
