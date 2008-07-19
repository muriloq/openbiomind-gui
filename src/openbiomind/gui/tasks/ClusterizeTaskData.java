/**
 * ClusterizeTaskData.java
 *
 * The file ClusteringTransformerTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

import openbiomind.gui.util.Utility;

/**
 * The class ClusterizeTaskData is used by the ClusterizeTaskData task. The syntax of this task is as follows:
 *
 * <pre>
 * task.Clusterize &lt;-d clustering dataset&gt; &lt;-o output file&gt; [-datasetClusteringMetric Cosine|Euclidean|SNP]
 * </pre>
 *
 * @author bsanghvi
 * @since Jul 18, 2008
 * @version Jul 18, 2008
 */
public class ClusterizeTaskData extends AbstractTaskData {

   /**
    * Name of this task is <code>task.ClusteringTransformer</code>.
    */
   private static final String TASK_NAME = "task.Clusterize"; //$NON-NLS-1$

   /** Argument <code>-d</code> for specifying the clustering dataset file. */
   private static final String ARG_D = HYPHEN + "d"; //$NON-NLS-1$

   /** Argument <code>-o</code> for specifying the output file. */
   private static final String ARG_O = HYPHEN + "o"; //$NON-NLS-1$

   /** Argument <code>-t</code> for specifying the transform. */
   private static final String ARG_T = HYPHEN + "t"; //$NON-NLS-1$

   /**
    * Instantiates a new clusterize task data.
    */
   public ClusterizeTaskData() {
      super(TASK_NAME);
   }

   /**
    * Gets the clustering dataset file (argument = <code>-d</code>).
    *
    * @return the clustering dataset file
    */
   public String getClusteringDatasetFile() {
      return getArgument(ARG_D);
   }

   /**
    * Sets the clustering dataset file (argument = <code>-d</code>).
    *
    * @param clusteringDatasetFile the clustering dataset file
    */
   public void setClusteringDatasetFile(final String clusteringDatasetFile) {
      if (!Utility.isEmpty(clusteringDatasetFile)) {
         putArgument(ARG_D, clusteringDatasetFile);
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
    * Gets the dataset clustering metric (argument = <code>-t</code>).
    *
    * @return the dataset clustering metric
    */
   public DatasetClusteringMetricEnum getDatasetClusteringMetric() {
      return DatasetClusteringMetricEnum.parse(getArgument(ARG_T));
   }

   /**
    * Sets the dataset clustering metric (argument = <code>-t</code>).
    *
    * @param datasetClusteringMetric the dataset clustering metric
    */
   public void setDatasetClusteringMetric(final DatasetClusteringMetricEnum datasetClusteringMetric) {
      if (datasetClusteringMetric != null) {
         putArgument(ARG_T, datasetClusteringMetric.toString());
      }
   }

   /*
    * @see openbiomind.gui.tasks.AbstractTaskData#createTaskDataProject()
    */
   @Override
   public TaskDataProject createTaskDataProject() {
      final TaskDataProject taskDataProject = new TaskDataProject(getProjectName());
      taskDataProject.add(createTaskDataFolder(ARG_D, getClusteringDatasetFile(), false));
      taskDataProject.add(createTaskDataFolder(ARG_O, getOutputFile(), true));
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

}
