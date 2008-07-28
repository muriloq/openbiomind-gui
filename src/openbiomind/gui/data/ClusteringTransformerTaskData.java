/**
 * ClusteringTransformerTaskData.java
 *
 * The file ClusteringTransformerTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.data;

import java.io.File;

import openbiomind.gui.common.Argument;
import openbiomind.gui.project.TaskDataFile;
import openbiomind.gui.project.TaskDataFolder;
import openbiomind.gui.project.TaskDataProject;
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
 * @version Jul 27, 2008
 */
public class ClusteringTransformerTaskData extends AbstractTaskData {

   /**
    * Name of this task is <code>task.ClusteringTransformer</code>.
    */
   private static final String TASK_NAME = "task.ClusteringTransformer"; //$NON-NLS-1$

   /** Argument <code>-t</code> for specifying the transform. */
   private static final Argument ARG_T = new Argument("t"); //$NON-NLS-1$

   /** Argument <code>-p</code> for specifying the meta task results directory. */
   private static final Argument ARG_P = new Argument("p", "MetaTask Result"); //$NON-NLS-1$

   /**
    * Instantiates a new clustering transformer task data.
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
      return getPairedArgument().get(ARG_D.argument());
   }

   /**
    * Sets the dataset file (argument = <code>-d</code>).
    *
    * @param datasetFile the dataset file
    */
   public void setDatasetFile(final String datasetFile) {
      getPairedArgument().put(ARG_D.argument(), datasetFile);
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
    * Gets the transform (argument = <code>-t</code>).
    *
    * @return the transform
    */
   public TransformEnum getTransform() {
      return getPairedArgument().getTransformEnum(ARG_T.argument());
   }

   /**
    * Sets the transform (argument = <code>-t</code>).
    *
    * @param transform the transform
    */
   public void setTransform(final TransformEnum transform) {
      getPairedArgument().put(ARG_T.argument(), transform);
   }

   /**
    * Gets the meta task result directory (argument = <code>-p</code>).
    *
    * @return the meta task result directory
    */
   public String getMetaTaskResultDir() {
      return getPairedArgument().get(ARG_P.argument());
   }

   /**
    * Sets the meta task result directory (argument = <code>-p</code>).
    *
    * @param metaTaskResultDir the meta task result directory
    */
   public void setMetaTaskResultDir(final String metaTaskResultDir) {
      getPairedArgument().put(ARG_P.argument(), metaTaskResultDir);
   }

   /*
    * @see openbiomind.gui.tasks.AbstractTaskData#createTaskDataProject()
    */
   @Override
   public TaskDataProject createTaskDataProject() {
      final TaskDataProject taskDataProject = new TaskDataProject(getProjectName());
      taskDataProject.add(createTaskDataFolder(ARG_D.friendlyName(), getDatasetFile(), false));
      taskDataProject.add(createTaskDataFolder(ARG_O.friendlyName(), getOutputFile(), true));
      taskDataProject.add(createResultDirTaskDataFolder(ARG_P.friendlyName(), getMetaTaskResultDir()));
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
