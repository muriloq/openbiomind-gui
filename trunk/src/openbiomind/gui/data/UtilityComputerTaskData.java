/**
 * UtilityComputerTaskData.java
 *
 * The file UtilityComputerTaskData.java.
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
 * The class UtilityComputerTaskData is used by the UtilityComputer task. The syntax of this task is as follows:
 *
 * <pre>
 * task.UtilityComputer &lt;-r result dir&gt; &lt;-o output file&gt; &lt;-o base dataset&gt; [-targetCategory target category]
 * </pre>
 *
 * @author bsanghvi
 * @since Jul 9, 2008
 * @version Jul 27, 2008
 */
public class UtilityComputerTaskData extends AbstractTaskData {

   /**
    * Name of this task is <code>task.UtilityComputer</code>.
    */
   private static final String TASK_NAME = "task.UtilityComputer"; //$NON-NLS-1$

   /** Argument <code>-r</code> for specifying the name of meta task result directory. */
   private static final Argument ARG_R = new Argument("r", "MetaTask Result"); //$NON-NLS-1$

   /**
    * Instantiates a new utility computer task.
    */
   public UtilityComputerTaskData() {
      super(TASK_NAME);
   }

   /**
    * Gets the meta task result directory (argument = <code>-r</code>).
    *
    * @return the meta task result directory
    */
   public String getMetaTaskResultDir() {
      return getPairedArgument().get(ARG_R.argument());
   }

   /**
    * Sets the meta task result directory (argument = <code>-r</code>).
    *
    * @param metaTaskResultDir the meta task result directory
    */
   public void setMetaTaskResultDir(final String metaTaskResultDir) {
      getPairedArgument().put(ARG_R.argument(), metaTaskResultDir);
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
    * Gets the base dataset (argument = <code>-d</code>).
    *
    * @return the base dataset
    */
   public String getBaseDataset() {
      return getPairedArgument().get(ARG_D.argument());
   }

   /**
    * Sets the base dataset (argument = <code>-d</code>).
    *
    * @param baseDataset the base dataset
    */
   public void setBaseDataset(final String baseDataset) {
      getPairedArgument().put(ARG_D.argument(), baseDataset);
   }

   /**
    * Gets the target category (argument = <code>-targetCategory</code>).
    *
    * @return the target category
    */
   public String getTargetCategory() {
      return getPairedArgument().get(ARG_TARGET_CATEGORY.argument());
   }

   /**
    * Sets the target category (argument = <code>-targetCategory</code>).
    *
    * @param targetCategory the target category
    */
   public void setTargetCategory(final String targetCategory) {
      getPairedArgument().put(ARG_TARGET_CATEGORY.argument(), targetCategory);
   }

   /*
    * @see openbiomind.gui.tasks.AbstractTaskData#createTaskDataProject()
    */
   @Override
   public TaskDataProject createTaskDataProject() {
      final TaskDataProject taskDataProject = new TaskDataProject(getProjectName());
      taskDataProject.add(createResultDirTaskDataFolder(ARG_R.friendlyName(), getMetaTaskResultDir()));
      taskDataProject.add(createTaskDataFolder(ARG_O.friendlyName(), getOutputFile(), true));
      taskDataProject.add(createTaskDataFolder(ARG_D.friendlyName(), getBaseDataset(), false));
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
