/**
 * ViewClustersTaskData.java
 *
 * The file ViewClustersTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

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
 * @version Jul 20, 2008
 */
public class ViewClustersTaskData extends AbstractTaskData {

   /**
    * Name of this task is <code>task.ViewClusters</code>.
    */
   private static final String TASK_NAME = "task.ViewClusters"; //$NON-NLS-1$

   /** Argument <code>-d</code> for specifying the clustering dataset. */
   private static final String ARG_D = HYPHEN + "d"; //$NON-NLS-1$

   /** Argument <code>-r</code> for specifying the clustering result. */
   private static final String ARG_R = HYPHEN + "r"; //$NON-NLS-1$

   /** Argument <code>-o</code> for specifying the image file. */
   private static final String ARG_O = HYPHEN + "o"; //$NON-NLS-1$

   /** Argument <code>-clusteringColors</code> for specifying the clustering colors. */
   private static final String ARG_CLUSTERING_COLORS = HYPHEN + "clusteringColors"; //$NON-NLS-1$

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
      return getArgument(ARG_D);
   }

   /**
    * Sets the clustering dataset (argument = <code>-d</code>).
    *
    * @param clusteringDataset the clustering dataset
    */
   public void setClusteringDataset(final String clusteringDataset) {
      if (!Utility.isEmpty(clusteringDataset)) {
         putArgument(ARG_D, clusteringDataset);
      }
   }

   /**
    * Gets the clustering result (argument = <code>-r</code>).
    *
    * @return the clustering result
    */
   public String getClusteringResult() {
      return getArgument(ARG_R);
   }

   /**
    * Sets the clustering result (argument = <code>-r</code>).
    *
    * @param clusteringResult the clustering result
    */
   public void setClusteringResult(final String clusteringResult) {
      if (!Utility.isEmpty(clusteringResult)) {
         putArgument(ARG_R, clusteringResult);
      }
   }

   /**
    * Gets the image file (argument = <code>-o</code>).
    *
    * @return the image file
    */
   public String getImageFile() {
      return getArgument(ARG_O);
   }

   /**
    * Sets the image file (argument = <code>-o</code>).
    *
    * @param imageFile the image file
    */
   public void setImageFile(final String imageFile) {
      if (!Utility.isEmpty(imageFile)) {
         putArgument(ARG_O, imageFile);
      }
   }

   /**
    * Gets the clustering colors (argument = <code>-targetCategory</code>).
    *
    * @return the clustering colors
    */
   public ClusteringColorsEnum getClusteringColors() {
      return ClusteringColorsEnum.parse(getArgument(ARG_CLUSTERING_COLORS));
   }

   /**
    * Sets the clustering colors (argument = <code>-targetCategory</code>).
    *
    * @param clusteringColors the clustering colors
    */
   public void setClusteringColors(final ClusteringColorsEnum clusteringColors) {
      if (clusteringColors != null) {
         putArgument(ARG_CLUSTERING_COLORS, clusteringColors.toString());
      }
   }

   /*
    * @see openbiomind.gui.tasks.AbstractTaskData#createTaskDataProject()
    */
   @Override
   public TaskDataProject createTaskDataProject() {
      final TaskDataProject taskDataProject = new TaskDataProject(getProjectName());
      taskDataProject.add(createTaskDataFolder(ARG_D, getClusteringDataset(), false));
      taskDataProject.add(createTaskDataFolder(ARG_R, getClusteringResult(), false));
      taskDataProject.add(createTaskDataFolder(ARG_O, getImageFile(), true));
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
