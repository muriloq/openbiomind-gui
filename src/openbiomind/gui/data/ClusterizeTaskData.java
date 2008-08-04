/**
 * ClusterizeTaskData.java
 *
 * The file ClusteringTransformerTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.data;

import openbiomind.gui.project.TaskDataFolder;

/**
 * The class ClusterizeTaskData is used by the ClusterizeTaskData task. The syntax of this task is as follows:
 *
 * <pre>
 * task.Clusterize &lt;-d clustering dataset&gt; &lt;-o output file&gt; [-datasetClusteringMetric Cosine|Euclidean|SNP]
 * </pre>
 *
 * @author bsanghvi
 * @since Jul 18, 2008
 * @version Aug 3, 2008
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
    * @see openbiomind.gui.data.AbstractTaskData#createInputFolder()
    */
   @Override
   protected TaskDataFolder createInputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_In);
      taskDataFolder.add(createTaskDataFile(getClusteringDatasetFile(), true, false, Resources.TAB_EXTENSION));
      return taskDataFolder;
   }

   /*
    * @see openbiomind.gui.data.AbstractTaskData#createOutputFolder()
    */
   @Override
   protected TaskDataFolder createOutputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_Out);
      taskDataFolder.add(createTaskDataFile(getOutputFile(), true, true, Resources.TXT_EXTENSION));
      return taskDataFolder;
   }

}
