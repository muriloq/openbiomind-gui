/**
 * GraphFeaturesWizard.java
 *
 * The file GraphFeaturesWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.tasks.AbstractTaskData;
import openbiomind.gui.tasks.GraphFeaturesTaskData;

/**
 * The class GraphFeaturesWizard.
 *
 * @author bsanghvi
 * @since Jul 20, 2008
 * @version Jul 20, 2008
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

}
