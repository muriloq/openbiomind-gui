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
 * @version Jul 28, 2008
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
      super(Messages.GraphFeaturesWizard_Name);
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
      getGraphFeaturesTaskData().setProjectName(getGraphFeaturesWizardPage().getProjectName());
      getGraphFeaturesTaskData().setHorizontalDataset(getGraphFeaturesWizardPage().getHorizontalDataset());
      getGraphFeaturesTaskData().setMobraDataset(getGraphFeaturesWizardPage().getMobraDataset());
      getGraphFeaturesTaskData().setUtilityFile(getGraphFeaturesWizardPage().getUtilityFile());
      getGraphFeaturesTaskData().setOutputFile(getGraphFeaturesWizardPage().getOutputFile());
      getGraphFeaturesTaskData().setTopUsefulFeatures(getGraphFeaturesWizardPage().getTopUsefulFeatures());
      getGraphFeaturesTaskData()
            .setMaximumCoOccurrenceEdges(getGraphFeaturesWizardPage().getMaximumCoOccurrenceEdges());
      getGraphFeaturesTaskData()
            .setMaximumCoExpressionEdges(getGraphFeaturesWizardPage().getMaximumCoExpressionEdges());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData getTaskData() {
      return getGraphFeaturesTaskData();
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
         this.graphFeaturesWizardPage = new GraphFeaturesWizardPage(Messages.GraphFeaturesWizard_Name,
               Messages.GraphFeaturesWizard_Description);
      }

      return this.graphFeaturesWizardPage;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getPostSuccessfulExecutionProcess()
    */
   @Override
   protected Process getPostSuccessfulExecutionProcess() {
      if (Preference.isGraphvizDotUtilityPreferenceSet()) {
         final String outputFormat = getGraphFeaturesWizardPage().getGraphImageType();
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

      Console.info(Messages.GraphFeaturesWizard_Info_GraphvizDotUtility);
      return super.getPostSuccessfulExecutionProcess();
   }

}
