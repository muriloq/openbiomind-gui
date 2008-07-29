/**
 * GraphFeaturesTaskData.java
 *
 * The file GraphFeaturesTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.data;

import openbiomind.gui.common.Argument;
import openbiomind.gui.project.TaskDataFile;
import openbiomind.gui.project.TaskDataFolder;
import openbiomind.gui.project.TaskDataProject;
import openbiomind.gui.util.Utility;

/**
 * The class GraphFeaturesTaskData is used by the GraphFeatures task. The syntax of this task is as follows:
 *
 * <pre>
 * task.GraphFeatures &lt;-h horizontal dataset&gt; &lt;-m mobra dataset&gt; &lt;-u utility file&gt; &lt;-o output file&gt; [-topNUseful nu] [-topNCooc max co-occurence edges] [-topNCoex max co-expression edges]
 * </pre>
 *
 * @author bsanghvi
 * @since Jul 20, 2008
 * @version Jul 28, 2008
 */
public class GraphFeaturesTaskData extends AbstractTaskData {

   /**
    * Name of this task is <code>task.GraphFeatures</code>.
    */
   private static final String TASK_NAME = "task.GraphFeatures"; //$NON-NLS-1$

   /** Argument <code>-h</code> for specifying the horizontal dataset. */
   private static final Argument ARG_H = new Argument("h", Messages.GraphFeatures_ArgH_FName); //$NON-NLS-1$

   /** Argument <code>-m</code> for specifying the MOBRA result. */
   private static final Argument ARG_M = new Argument("m", Messages.GraphFeatures_ArgM_FName); //$NON-NLS-1$

   /** Argument <code>-u</code> for specifying the utility file. */
   private static final Argument ARG_U = new Argument("u", Messages.GraphFeatures_ArgU_FName); //$NON-NLS-1$

   /** The graph image path. */
   private String graphImagePath = null;

   /**
    * Instantiates a new view clusters task data.
    */
   public GraphFeaturesTaskData() {
      super(TASK_NAME);
   }

   /**
    * Gets the horizontal dataset (argument = <code>-d</code>).
    *
    * @return the horizontal dataset
    */
   public String getHorizontalDataset() {
      return getPairedArgument().get(ARG_H.argument());
   }

   /**
    * Sets the horizontal dataset (argument = <code>-h</code>).
    *
    * @param horizontalDataset the horizontal dataset
    */
   public void setHorizontalDataset(final String horizontalDataset) {
      getPairedArgument().put(ARG_H.argument(), horizontalDataset);
   }

   /**
    * Gets the MOBRA dataset (argument = <code>-m</code>).
    *
    * @return the MOBRA dataset
    */
   public String getMobraDataset() {
      return getPairedArgument().get(ARG_M.argument());
   }

   /**
    * Sets the MOBRA dataset (argument = <code>-r</code>).
    *
    * @param mobraDataset the MOBRA dataset
    */
   public void setMobraDataset(final String mobraDataset) {
      getPairedArgument().put(ARG_M.argument(), mobraDataset);
   }

   /**
    * Gets the utility file (argument = <code>-u</code>).
    *
    * @return the utility file
    */
   public String getUtilityFile() {
      return getPairedArgument().get(ARG_U.argument());
   }

   /**
    * Sets the utility file (argument = <code>-u</code>).
    *
    * @param utilityFile the new utility file
    */
   public void setUtilityFile(final String utilityFile) {
      getPairedArgument().put(ARG_U.argument(), utilityFile);
   }

   /**
    * Gets the output file (argument = <code>-o</code>).
    *
    * @return the output file
    */
   public String getOutputFile() {
      return getPairedArgument().get(ARG_O.argument());
   }

   /**
    * Sets the output file (argument = <code>-o</code>).
    *
    * @param outputFile the output file
    */
   public void setOutputFile(final String outputFile) {
      getPairedArgument().put(ARG_O.argument(), outputFile);
   }

   /**
    * Gets the number of top useful features (argument = <code>-topNUseful</code>).
    *
    * @return the top useful features
    */
   public Integer getTopUsefulFeatures() {
      return getPairedArgument().getInteger(ARG_TOP_N_USEFUL.argument());
   }

   /**
    * Sets the number of top useful features (argument = <code>-topNUseful</code>).
    *
    * @param topUsefulFeatures the top useful features
    */
   public void setTopUsefulFeatures(final Integer topUsefulFeatures) {
      getPairedArgument().put(ARG_TOP_N_USEFUL.argument(), topUsefulFeatures);
   }

   /**
    * Gets the number of maximum co-occurrence edges (argument = <code>-topNCooc</code>).
    *
    * @return the maximum co-occurrence edges
    */
   public Integer getMaximumCoOccurrenceEdges() {
      return getPairedArgument().getInteger(ARG_TOP_N_COOC.argument());
   }

   /**
    * Sets the number of maximum co-occurrence edges (argument = <code>-topNCooc</code>).
    *
    * @param maximumCoOccurrenceEdges the maximum co-occurrence edges
    */
   public void setMaximumCoOccurrenceEdges(final Integer maximumCoOccurrenceEdges) {
      getPairedArgument().put(ARG_TOP_N_COOC.argument(), maximumCoOccurrenceEdges);
   }

   /**
    * Gets the number of maximum co-expression edges (argument = <code>-topNCoex</code>).
    *
    * @return the maximum co-expression edges
    */
   public Integer getMaximumCoExpressionEdges() {
      return getPairedArgument().getInteger(ARG_TOP_N_COEX.argument());
   }

   /**
    * Sets the number of maximum co-expression edges (argument = <code>-topNCoex</code>).
    *
    * @param maximumCoExpressionEdges the maximum co-expression edges
    */
   public void setMaximumCoExpressionEdges(final Integer maximumCoExpressionEdges) {
      getPairedArgument().put(ARG_TOP_N_COEX.argument(), maximumCoExpressionEdges);
   }

   /**
    * Gets the graph image path.
    *
    * @return the graph image path
    */
   public String getGraphImagePath() {
      return this.graphImagePath;
   }

   /**
    * Sets the graph image path.
    *
    * @param graphImagePath the new graph image path
    */
   public void setGraphImagePath(final String graphImagePath) {
      this.graphImagePath = graphImagePath;
   }

   /*
    * @see openbiomind.gui.tasks.AbstractTaskData#createTaskDataProject()
    */
   @Override
   public TaskDataProject createTaskDataProject() {
      final TaskDataProject taskDataProject = new TaskDataProject(getProjectName());
      taskDataProject.add(createTaskDataFolder(ARG_H.friendlyName(), getHorizontalDataset(), false));
      taskDataProject.add(createTaskDataFolder(ARG_M.friendlyName(), getMobraDataset(), false));
      taskDataProject.add(createTaskDataFolder(ARG_U.friendlyName(), getUtilityFile(), false));
      taskDataProject.add(createOutputTaskDataFolder(ARG_O.friendlyName()));
      return taskDataProject;
   }

   /**
    * Creates the task data folder.
    *
    * @param folderName the folder name
    * @param filepath the file path
    * @param autoOpen the auto open
    *
    * @return the task data folder
    */
   private TaskDataFolder createTaskDataFolder(final String folderName, final String filepath, final boolean autoOpen) {
      if (Utility.isEmpty(filepath)) {
         return null;
      } else {
         final TaskDataFile taskDataFile = new TaskDataFile();
         taskDataFile.setPath(filepath);
         taskDataFile.setLinked(true);
         taskDataFile.setAutoOpen(autoOpen);

         final TaskDataFolder taskDataFolder = new TaskDataFolder(folderName);
         taskDataFolder.add(taskDataFile);

         return taskDataFolder;
      }
   }

   /**
    * Creates the output task data folder.
    *
    * @param folderName the folder name
    *
    * @return the task data folder
    */
   private TaskDataFolder createOutputTaskDataFolder(final String folderName) {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(folderName);

      final TaskDataFile dotTaskDataFile = new TaskDataFile();
      dotTaskDataFile.setPath(getOutputFile());
      dotTaskDataFile.setLinked(true);
      dotTaskDataFile.setAutoOpen(true);
      dotTaskDataFile.setEditorId(Properties.DEFAULT_TEXT_EDITOR_ID); // FIXME Temporary hack
      taskDataFolder.add(dotTaskDataFile);

      if (!Utility.isEmpty(getGraphImagePath())) {
         final TaskDataFile graphImageTaskDataFile = new TaskDataFile(Utility.extractFileName(getGraphImagePath()));
         graphImageTaskDataFile.setPath(getGraphImagePath());
         graphImageTaskDataFile.setLinked(false);
         graphImageTaskDataFile.setAutoOpen(true);
         taskDataFolder.add(graphImageTaskDataFile);
      }

      return taskDataFolder;
   }

}