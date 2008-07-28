/**
 * ClusterizeTaskData.java
 *
 * The file ClusteringTransformerTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.data;

import openbiomind.gui.project.TaskDataFile;
import openbiomind.gui.project.TaskDataFolder;
import openbiomind.gui.project.TaskDataProject;
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
 * @version Jul 27, 2008
 */
public class ClusterizeTaskData extends AbstractTaskData {

   /**
    * Name of this task is <code>task.ClusteringTransformer</code>.
    */
   private static final String TASK_NAME = "task.Clusterize"; //$NON-NLS-1$

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
      return getPairedArgument().get(ARG_D.argument());
   }

   /**
    * Sets the clustering dataset file (argument = <code>-d</code>).
    *
    * @param clusteringDatasetFile the clustering dataset file
    */
   public void setClusteringDatasetFile(final String clusteringDatasetFile) {
      getPairedArgument().put(ARG_D.argument(), clusteringDatasetFile);
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
    * Gets the dataset clustering metric (argument = <code>-datasetClusteringMetric</code>).
    *
    * @return the dataset clustering metric
    */
   public DatasetClusteringMetricEnum getDatasetClusteringMetric() {
      return getPairedArgument().getDatasetClusteringMetricEnum(ARG_DATASET_CLUSTERING_METRIC.argument());
   }

   /**
    * Sets the dataset clustering metric (argument = <code>-datasetClusteringMetric</code>).
    *
    * @param datasetClusteringMetric the dataset clustering metric
    */
   public void setDatasetClusteringMetric(final DatasetClusteringMetricEnum datasetClusteringMetric) {
      getPairedArgument().put(ARG_DATASET_CLUSTERING_METRIC.argument(), datasetClusteringMetric);
   }

   /*
    * @see openbiomind.gui.tasks.AbstractTaskData#createTaskDataProject()
    */
   @Override
   public TaskDataProject createTaskDataProject() {
      final TaskDataProject taskDataProject = new TaskDataProject(getProjectName());
      taskDataProject.add(createTaskDataFolder(ARG_D.friendlyName(), getClusteringDatasetFile(), false));
      taskDataProject.add(createTaskDataFolder(ARG_O.friendlyName(), getOutputFile(), true));
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
