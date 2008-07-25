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
import java.util.ArrayList;
import java.util.List;

import openbiomind.gui.console.Console;
import openbiomind.gui.preferences.Preference;
import openbiomind.gui.tasks.AbstractTaskData;
import openbiomind.gui.tasks.GraphFeaturesTaskData;
import openbiomind.gui.util.Utility;

/**
 * The class GraphFeaturesWizard.
 *
 * @author bsanghvi
 * @since Jul 20, 2008
 * @version Jul 24, 2008
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
      super(WizardMessages.GraphFeaturesWizard_Name);
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
         this.graphFeaturesWizardPage = new GraphFeaturesWizardPage(WizardMessages.GraphFeaturesWizard_Name,
               WizardMessages.GraphFeaturesWizard_Description);
      }

      return this.graphFeaturesWizardPage;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getPostSuccessfulExecutionProcess()
    */
   @Override
   protected Process getPostSuccessfulExecutionProcess() {
      if (Preference.isGraphvizDotUtilityPreferenceSet()) {
         final String graphImageType = getGraphFeaturesWizardPage().getGraphImageType();
         final String outputDotFile = getGraphFeaturesWizardPage().getOutputFile();

         try {
            getGraphFeaturesTaskData().setGraphImagePath(
                  File.createTempFile(Utility.extractFileName(outputDotFile) + DOT + graphImageType + DOT, EMPTY)
                        .getAbsolutePath());

            final List<String> commandList = new ArrayList<String>();
            commandList.add(Preference.getGraphvizDotUtilityPath());
            commandList.add("-T" + graphImageType); //$NON-NLS-1$
            commandList.add(outputDotFile); // path of dot file
            commandList.add("-o" + getGraphFeaturesTaskData().getGraphImagePath()); //$NON-NLS-1$
            return (new ProcessBuilder(commandList)).start();
         } catch (final IOException e) {
            Console.debug(e);
         }
      } else {
         Console.info(WizardMessages.GraphFeaturesWizard_Info_GraphvizDotUtility);
      }

      return super.getPostSuccessfulExecutionProcess();
   }

}
