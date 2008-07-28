/**
 * ViewClustersTaskData.java
 *
 * The file ViewClustersTaskData.java.
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
 * The class ViewClustersTaskData is used by the ViewClusters task. The syntax of this task is as follows:
 *
 * <pre>
 * task.ViewClusters &lt;-d clustering dataset&gt; &lt;-r clustering result&gt; &lt;-o image file&gt; [-clusteringColors traditional|mono]
 * </pre>
 *
 * @author bsanghvi
 * @since Jul 20, 2008
 * @version Jul 27, 2008
 */
public class ViewClustersTaskData extends AbstractTaskData {

   /**
    * Name of this task is <code>task.ViewClusters</code>.
    */
   private static final String TASK_NAME = "task.ViewClusters"; //$NON-NLS-1$

   /** Argument <code>r</code> for specifying the clustering result. */
   private static final Argument ARG_R = new Argument("r", "Clustering Result"); //$NON-NLS-1$

   /**
    * Instantiates a new view clusters task data.
    */
   public ViewClustersTaskData() {
      super(TASK_NAME);
      setMemoryIntensive(true);
   }

   /**
    * Gets the clustering dataset (argument = <code>-d</code>).
    *
    * @return the clustering dataset
    */
   public String getClusteringDataset() {
      return getPairedArgument().get(ARG_D.argument());
   }

   /**
    * Sets the clustering dataset (argument = <code>-d</code>).
    *
    * @param clusteringDataset the clustering dataset
    */
   public void setClusteringDataset(final String clusteringDataset) {
      getPairedArgument().put(ARG_D.argument(), clusteringDataset);
   }

   /**
    * Gets the clustering result (argument = <code>-r</code>).
    *
    * @return the clustering result
    */
   public String getClusteringResult() {
      return getPairedArgument().get(ARG_R.argument());
   }

   /**
    * Sets the clustering result (argument = <code>-r</code>).
    *
    * @param clusteringResult the clustering result
    */
   public void setClusteringResult(final String clusteringResult) {
      getPairedArgument().put(ARG_R.argument(), clusteringResult);
   }

   /**
    * Gets the image file (argument = <code>-o</code>).
    *
    * @return the image file
    */
   public String getImageFile() {
      return getPairedArgument().get(ARG_O.argument());
   }

   /**
    * Sets the image file (argument = <code>-o</code>).
    *
    * @param imageFile the image file
    */
   public void setImageFile(final String imageFile) {
      getPairedArgument().put(ARG_O.argument(), imageFile);
   }

   /**
    * Gets the clustering colors (argument = <code>-targetCategory</code>).
    *
    * @return the clustering colors
    */
   public ClusteringColorsEnum getClusteringColors() {
      return getPairedArgument().getClusteringColorsEnum(ARG_CLUSTERING_COLORS.argument());
   }

   /**
    * Sets the clustering colors (argument = <code>-targetCategory</code>).
    *
    * @param clusteringColors the clustering colors
    */
   public void setClusteringColors(final ClusteringColorsEnum clusteringColors) {
      getPairedArgument().put(ARG_CLUSTERING_COLORS.argument(), clusteringColors);
   }

   /*
    * @see openbiomind.gui.tasks.AbstractTaskData#createTaskDataProject()
    */
   @Override
   public TaskDataProject createTaskDataProject() {
      final TaskDataProject taskDataProject = new TaskDataProject(getProjectName());
      taskDataProject.add(createTaskDataFolder(ARG_D.friendlyName(), getClusteringDataset(), false));
      taskDataProject.add(createTaskDataFolder(ARG_R.friendlyName(), getClusteringResult(), false));
      taskDataProject.add(createTaskDataFolder(ARG_O.friendlyName(), getImageFile(), true));
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
