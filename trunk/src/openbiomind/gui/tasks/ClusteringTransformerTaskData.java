/**
 * ClusteringTransformerTaskData.java
 *
 * The file ClusteringTransformerTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

import java.io.File;

import openbiomind.gui.util.Utility;

/**
 * The class ClusteringTransformerTaskData is used by the ClusteringTransformer task. The syntax of this task is as
 * follows:
 *
 * <pre>
 * task.ClusteringTransformer &lt;-d dataset file&gt; &lt;-o output file&gt; &lt;-t transform&gt; [-p metatask results dir]
 * </pre>
 *
 * @author bsanghvi
 * @since Jul 13, 2008
 * @version Jul 16, 2008
 */
public class ClusteringTransformerTaskData extends AbstractTaskData {

   /**
    * Name of this task is <code>task.ClusteringTransformer</code>.
    */
   private static final String TASK_NAME = "task.ClusteringTransformer"; //$NON-NLS-1$

   /** Argument <code>-d</code> for specifying the dataset file. */
   private static final String ARG_D = HYPHEN + "d"; //$NON-NLS-1$

   /** Argument <code>-o</code> for specifying the output file. */
   private static final String ARG_O = HYPHEN + "o"; //$NON-NLS-1$

   /** Argument <code>-t</code> for specifying the transform. */
   private static final String ARG_T = HYPHEN + "t"; //$NON-NLS-1$

   /** Argument <code>-p</code> for specifying the meta task results directory. */
   private static final String ARG_P = HYPHEN + "p"; //$NON-NLS-1$

   /**
    * Instantiates a new utility computer task.
    */
   public ClusteringTransformerTaskData() {
      super(TASK_NAME);
   }

   /**
    * Gets the dataset file (argument = <code>-d</code>).
    *
    * @return the dataset file
    */
   public String getDatasetFile() {
      return getArgument(ARG_D);
   }

   /**
    * Sets the dataset file (argument = <code>-d</code>).
    *
    * @param datasetFile the dataset file
    */
   public void setDatasetFile(final String datasetFile) {
      if (!Utility.isEmpty(datasetFile)) {
         putArgument(ARG_D, datasetFile);
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
    * Gets the transform (argument = <code>-t</code>).
    *
    * @return the transform
    */
   public String getTransform() {
      return getArgument(ARG_T);
   }

   /**
    * Sets the transform (argument = <code>-t</code>).
    *
    * @param transform the transform
    */
   public void setTransform(final TransformEnum transform) {
      if (transform != null) {
         putArgument(ARG_T, transform.toString());
      }
   }

   /**
    * Gets the meta task result directory (argument = <code>-p</code>).
    *
    * @return the meta task result directory
    */
   public String getMetaTaskResultDir() {
      return getArgument(ARG_P);
   }

   /**
    * Sets the meta task result directory (argument = <code>-p</code>).
    *
    * @param metaTaskResultDir the meta task result directory
    */
   public void setMetaTaskResultDir(final String metaTaskResultDir) {
      if (!Utility.isEmpty(metaTaskResultDir)) {
         putArgument(ARG_P, metaTaskResultDir);
      }
   }

   /*
    * @see openbiomind.gui.tasks.AbstractTaskData#createTaskDataProject()
    */
   @Override
   public TaskDataProject createTaskDataProject() {
      final TaskDataProject taskDataProject = new TaskDataProject(getProjectName());
      taskDataProject.add(createTaskDataFolder(ARG_D, getDatasetFile(), false));
      taskDataProject.add(createTaskDataFolder(ARG_O, getOutputFile(), true));
      taskDataProject.add(createResultDirTaskDataFolder(ARG_P, getMetaTaskResultDir()));
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
    * Creates the result directory task data folder.
    *
    * @param folderName the folder name
    * @param folderPath the folder path
    *
    * @return the task data folder
    */
   private TaskDataFolder createResultDirTaskDataFolder(final String folderName, final String folderPath) {
      if (!Utility.isEmpty(folderPath)) {
         final File directory = new File(folderPath);
         final String directoryPath = directory.getAbsolutePath();
         final TaskDataFolder taskDataFolder = new TaskDataFolder(folderName);
         final String[] outFilesArray = Utility.listFileNames(directory, Resources.OUT_FILE_STARTS_WITH,
               Resources.TXT_EXTENSION);

         for (final String outFile : outFilesArray) {
            taskDataFolder.add(createTaskDataFile(outFile, directoryPath));
         }

         return taskDataFolder;
      }

      return null;
   }

   /**
    * Creates the task data file.
    *
    * @param fileName the file name
    * @param filePath the file path
    * @param autoOpen the auto open
    *
    * @return the task data file
    */
   private TaskDataFile createTaskDataFile(final String fileName, final String filePath) {
      final TaskDataFile taskDataFile = new TaskDataFile(fileName);
      taskDataFile.setPath(filePath + File.separator + fileName);
      taskDataFile.setLinked(true);
      taskDataFile.setAutoOpen(false);
      return taskDataFile;
   }

}
