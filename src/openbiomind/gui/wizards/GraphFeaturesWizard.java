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

   /** The graph features wizard page. */
   private GraphFeaturesWizardPage graphFeaturesWizardPage = null;

   /** The image path. */
   private String imagePath = null;

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
      addPage(getGraphFeaturesWizardPage());
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizard#getTaskData()
    */
   @Override
   protected AbstractTaskData[] getTaskData() {
      final GraphFeaturesTaskData graphFeaturesTaskData = getGraphFeaturesWizardPage().prepareTaskData();
      graphFeaturesTaskData.setGraphImagePath(getImagePath());
      return new AbstractTaskData[] { graphFeaturesTaskData };
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
         final String sourceDotPath = getGraphFeaturesWizardPage().getOutputFile();

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

}
