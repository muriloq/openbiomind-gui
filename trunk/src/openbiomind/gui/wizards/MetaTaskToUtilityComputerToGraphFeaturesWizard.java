/**
 * MetaTaskToUtilityComputerToGraphFeaturesWizard.java
 *
 * The file MetaTaskToUtilityComputerToGraphFeaturesWizard.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.data.AbstractTaskData;
import openbiomind.gui.data.GraphFeaturesTaskData;
import openbiomind.gui.main.GraphvizHelper;

/**
 * The class MetaTaskToUtilityComputerToGraphFeaturesWizard.
 *
 * @author bsanghvi
 * @since Aug 10, 2008
 * @version Aug 10, 2008
 */
public class MetaTaskToUtilityComputerToGraphFeaturesWizard extends AbstractTaskWizard {

   /** The meta task wizard page. */
   private final MetaTaskWizardPage META_TASK_WIZ_PAGE = new MetaTaskWizardPage();

   /** The utility computer wizard page. */
   private final UtilityComputerWizardPage UTILITY_COMPUTER_WIZ_PAGE = new UtilityComputerWizardPage();

   /** The graph features wizard page. */
   private final GraphFeaturesWizardPage GRAPH_FEATURES_WIZ_PAGE = new GraphFeaturesWizardPage();

   /** The graph features task data. */
   private GraphFeaturesTaskData graphFeaturesTaskData = null;

   /** The Graphviz helper. */
   private GraphvizHelper graphvizHelper = null;

   /**
    * Instantiates a new graph features from dataset transformer wizard.
    */
   public MetaTaskToUtilityComputerToGraphFeaturesWizard() {
      super(Messages.Meta_UtilComp_GraFeature_Wiz_Title);
   }

   /*
    * @see org.eclipse.jface.wizard.Wizard#addPages()
    */
   @Override
   public void addPages() {
      addPage(this.META_TASK_WIZ_PAGE);
      addPage(this.UTILITY_COMPUTER_WIZ_PAGE);
      addPage(this.GRAPH_FEATURES_WIZ_PAGE);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      if (this.GRAPH_FEATURES_WIZ_PAGE.isCurrentPage()) {
         return new AbstractTaskData[] { this.META_TASK_WIZ_PAGE.prepareTaskData(),
               this.UTILITY_COMPUTER_WIZ_PAGE.prepareTaskData(), getGraphFeaturesTaskData() };
      } else if (this.UTILITY_COMPUTER_WIZ_PAGE.isCurrentPage()) {
         return new AbstractTaskData[] { this.META_TASK_WIZ_PAGE.prepareTaskData(),
               this.UTILITY_COMPUTER_WIZ_PAGE.prepareTaskData() };
      } else {
         return new AbstractTaskData[] { this.META_TASK_WIZ_PAGE.prepareTaskData() };
      }
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getFirstWizardPage()
    */
   @Override
   protected AbstractTaskWizardPage getFirstWizardPage() {
      return this.META_TASK_WIZ_PAGE;
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
