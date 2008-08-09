/**
 * GraphFeaturesWizard.java
 *
 * The file GraphFeaturesWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import java.io.File;
import java.io.IOException;
import java.util.List;

import openbiomind.gui.console.Console;
import openbiomind.gui.data.AbstractTaskData;
import openbiomind.gui.data.GraphFeaturesTaskData;
import openbiomind.gui.main.GraphvizHelper;
import openbiomind.gui.preferences.Preference;
import openbiomind.gui.util.Utility;

/**
 * The class GraphFeaturesWizard.
 *
 * @author bsanghvi
 * @since Jul 20, 2008
 * @version Aug 9, 2008
 */
public class GraphFeaturesWizard extends AbstractTaskWizard {

   /** The graph features task data. */
   private GraphFeaturesTaskData graphFeaturesTaskData = null;

   /** The graph features wizard page. */
   private GraphFeaturesWizardPage graphFeaturesWizardPage = null;

   /**
    * Instantiates a new graph features wizard.
    */
   public GraphFeaturesWizard() {
      super(Messages.GraFeatureWiz_Name);
      this.graphFeaturesTaskData = new GraphFeaturesTaskData();
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(getGraphFeaturesWizardPage());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#prepareTaskData()
    */
   @Override
   protected void prepareTaskData() {
      getGraphFeaturesTaskData().setHorizontalDataset(getGraphFeaturesWizardPage().getHorizontalDataset());
      getGraphFeaturesTaskData().setMobraDataset(getGraphFeaturesWizardPage().getMobraDataset());
      getGraphFeaturesTaskData().setUtilityFile(getGraphFeaturesWizardPage().getUtilityFile());
      getGraphFeaturesTaskData().setOutputFile(getGraphFeaturesWizardPage().getOutputFile());
      getGraphFeaturesTaskData()
            .setMaximumCoOccurrenceEdges(getGraphFeaturesWizardPage().getMaximumCoOccurrenceEdges());
      getGraphFeaturesTaskData()
            .setMaximumCoExpressionEdges(getGraphFeaturesWizardPage().getMaximumCoExpressionEdges());
      getGraphFeaturesTaskData().setTopUsefulFeatures(getGraphFeaturesWizardPage().getTopUsefulFeatures());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      return new AbstractTaskData[] { getGraphFeaturesTaskData() };
   }

   /**
    * Gets the graph features task data.
    *
    * @return the graph features task data
    */
   private GraphFeaturesTaskData getGraphFeaturesTaskData() {
      return this.graphFeaturesTaskData;
   }

   /**
    * Gets the graph features wizard page.
    *
    * @return the graph features wizard page
    */
   private GraphFeaturesWizardPage getGraphFeaturesWizardPage() {
      if (this.graphFeaturesWizardPage == null) {
         this.graphFeaturesWizardPage = new GraphFeaturesWizardPage(Messages.GraFeatureWiz_Name,
               Messages.GraFeatureWiz_Desc);
      }

      return this.graphFeaturesWizardPage;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getWizardPage() {
      return getGraphFeaturesWizardPage();
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getPostSuccessfulExecutionProcess()
    */
   @Override
   protected Process getPostSuccessfulExecutionProcess() {
      if (Preference.isGraphvizDotUtilityPreferenceSet()) {
         final String outputFormat = Resources.PNG_FORMAT;
         final String sourceDotPath = getGraphFeaturesWizardPage().getOutputFile();

         try {
            final String imagePath = File.createTempFile(
                  Utility.extractFileName(sourceDotPath) + DOT + outputFormat + DOT, EMPTY).getAbsolutePath();

            getGraphFeaturesTaskData().setGraphImagePath(imagePath);

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

}
