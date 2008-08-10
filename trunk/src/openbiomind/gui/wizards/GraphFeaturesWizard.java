/**
 * GraphFeaturesWizard.java
 *
 * The file GraphFeaturesWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;
import openbiomind.gui.data.GraphFeaturesTaskData;
import openbiomind.gui.main.GraphvizHelper;

/**
 * The class GraphFeaturesWizard.
 *
 * @author bsanghvi
 * @since Jul 20, 2008
 * @version Aug 10, 2008
 */
public class GraphFeaturesWizard extends AbstractTaskWizard {

   /** The graph features wizard page. */
   private final GraphFeaturesWizardPage GRAPH_FEATURES_WIZ_PAGE = new GraphFeaturesWizardPage();

   /** The graph features task data. */
   private GraphFeaturesTaskData graphFeaturesTaskData = null;

   /** The Graphviz helper. */
   private GraphvizHelper graphvizHelper = null;

   /**
    * Instantiates a new graph features wizard.
    */
   public GraphFeaturesWizard() {
      super(Messages.GraFeatureWiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(this.GRAPH_FEATURES_WIZ_PAGE);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      return new AbstractTaskData[] { getGraphFeaturesTaskData() };
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getFirstWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getFirstWizardPage() {
      return this.GRAPH_FEATURES_WIZ_PAGE;
   }

   /**
    * Gets the graph features task data.
    *
    * @return the graph features task data
    */
   private GraphFeaturesTaskData getGraphFeaturesTaskData() {
      if (this.graphFeaturesTaskData == null) {
         this.graphFeaturesTaskData = this.GRAPH_FEATURES_WIZ_PAGE.prepareTaskData();
      }
      return this.graphFeaturesTaskData;
   }

   /**
    * Gets the graphviz helper.
    *
    * @return the graphviz helper
    */
   private GraphvizHelper getGraphvizHelper() {
      if (this.graphvizHelper == null) {
         this.graphvizHelper = new GraphvizHelper(this.GRAPH_FEATURES_WIZ_PAGE.getOutputFile());
      }
      return this.graphvizHelper;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getPostSuccessfulExecutionProcess()
    */
   @Override
   protected Process getPostSuccessfulExecutionProcess() {
      final Process dotProcess = getGraphvizHelper().createDotProcess();
      if (dotProcess != null) {
         getGraphFeaturesTaskData().setGraphImagePath(getGraphvizHelper().getOutputFilePath());
         return dotProcess;
      } else {
         return super.getPostSuccessfulExecutionProcess();
      }
   }

}
