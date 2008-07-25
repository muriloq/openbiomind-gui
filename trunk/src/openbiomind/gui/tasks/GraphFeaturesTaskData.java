/**
 * GraphFeaturesTaskData.java
 *
 * The file GraphFeaturesTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

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
 * @version Jul 24, 2008
 */
public class GraphFeaturesTaskData extends AbstractTaskData {

   /**
    * Name of this task is <code>task.GraphFeatures</code>.
    */
   private static final String TASK_NAME = "task.GraphFeatures"; //$NON-NLS-1$

   /** Argument <code>-h</code> for specifying the horizontal dataset. */
   private static final String ARG_H = HYPHEN + "h"; //$NON-NLS-1$

   /** Argument <code>-m</code> for specifying the MOBRA result. */
   private static final String ARG_M = HYPHEN + "m"; //$NON-NLS-1$

   /** Argument <code>-u</code> for specifying the utility file. */
   private static final String ARG_U = HYPHEN + "u"; //$NON-NLS-1$

   /** Argument <code>-o</code> for specifying the output file. */
   private static final String ARG_O = HYPHEN + "o"; //$NON-NLS-1$

   /** Argument <code>-topNUseful</code> for specifying the number of top useful features. */
   private static final String ARG_TOP_N_USEFUL = HYPHEN + "topNUseful"; //$NON-NLS-1$

   /** Argument <code>-topNCooc</code> for specifying the number of maximum co-occurrence edges. */
   private static final String ARG_TOP_N_COOC = HYPHEN + "topNCooc"; //$NON-NLS-1$

   /** Argument <code>-topNCoex</code> for specifying the number of maximum co-expression edges. */
   private static final String ARG_TOP_N_COEX = HYPHEN + "topNCoex"; //$NON-NLS-1$

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
      return getArgument(ARG_H);
   }

   /**
    * Sets the horizontal dataset (argument = <code>-h</code>).
    *
    * @param horizontalDataset the horizontal dataset
    */
   public void setHorizontalDataset(final String horizontalDataset) {
      if (!Utility.isEmpty(horizontalDataset)) {
         putArgument(ARG_H, horizontalDataset);
      }
   }

   /**
    * Gets the MOBRA dataset (argument = <code>-m</code>).
    *
    * @return the MOBRA dataset
    */
   public String getMobraDataset() {
      return getArgument(ARG_M);
   }

   /**
    * Sets the MOBRA dataset (argument = <code>-r</code>).
    *
    * @param mobraDataset the MOBRA dataset
    */
   public void setMobraDataset(final String mobraDataset) {
      if (!Utility.isEmpty(mobraDataset)) {
         putArgument(ARG_M, mobraDataset);
      }
   }

   /**
    * Gets the utility file (argument = <code>-u</code>).
    *
    * @return the utility file
    */
   public String getUtilityFile() {
      return getArgument(ARG_U);
   }

   /**
    * Sets the utility file (argument = <code>-u</code>).
    *
    * @param utilityFile the new utility file
    */
   public void setUtilityFile(final String utilityFile) {
      if (!Utility.isEmpty(utilityFile)) {
         putArgument(ARG_U, utilityFile);
      }
   }

   /**
    * Gets the output file (argument = <code>-o</code>).
    *
    * @return the output file
    */
   public String getOutputFile() {
      return getArgument(ARG_O);
   }

   /**
    * Sets the output file (argument = <code>-o</code>).
    *
    * @param outputFile the output file
    */
   public void setOutputFile(final String outputFile) {
      if (!Utility.isEmpty(outputFile)) {
         putArgument(ARG_O, outputFile);
      }
   }

   /**
    * Gets the number of top useful features (argument = <code>-topNUseful</code>).
    *
    * @return the top useful features
    */
   public Integer getTopUsefulFeatures() {
      return Integer.valueOf(getArgument(ARG_TOP_N_USEFUL));
   }

   /**
    * Sets the number of top useful features (argument = <code>-topNUseful</code>).
    *
    * @param topUsefulFeatures the top useful features
    */
   public void setTopUsefulFeatures(final Integer topUsefulFeatures) {
      if (topUsefulFeatures != null) {
         putArgument(ARG_TOP_N_USEFUL, topUsefulFeatures.toString());
      }
   }

   /**
    * Gets the number of maximum co-occurrence edges (argument = <code>-topNCooc</code>).
    *
    * @return the maximum co-occurrence edges
    */
   public Integer getMaximumCoOccurrenceEdges() {
      return Integer.valueOf(getArgument(ARG_TOP_N_COOC));
   }

   /**
    * Sets the number of maximum co-occurrence edges (argument = <code>-topNCooc</code>).
    *
    * @param maximumCoOccurrenceEdges the maximum co-occurrence edges
    */
   public void setMaximumCoOccurrenceEdges(final Integer maximumCoOccurrenceEdges) {
      if (maximumCoOccurrenceEdges != null) {
         putArgument(ARG_TOP_N_COOC, maximumCoOccurrenceEdges.toString());
      }
   }

   /**
    * Gets the number of maximum co-expression edges (argument = <code>-topNCoex</code>).
    *
    * @return the maximum co-expression edges
    */
   public Integer getMaximumCoExpressionEdges() {
      return Integer.valueOf(getArgument(ARG_TOP_N_COEX));
   }

   /**
    * Sets the number of maximum co-expression edges (argument = <code>-topNCoex</code>).
    *
    * @param maximumCoExpressionEdges the maximum co-expression edges
    */
   public void setMaximumCoExpressionEdges(final Integer maximumCoExpressionEdges) {
      if (maximumCoExpressionEdges != null) {
         putArgument(ARG_TOP_N_COEX, maximumCoExpressionEdges.toString());
      }
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
      taskDataProject.add(createTaskDataFolder(ARG_H, getHorizontalDataset(), false));
      taskDataProject.add(createTaskDataFolder(ARG_M, getMobraDataset(), false));
      taskDataProject.add(createTaskDataFolder(ARG_U, getUtilityFile(), false));
      taskDataProject.add(createOutputTaskDataFolder(ARG_O));
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
