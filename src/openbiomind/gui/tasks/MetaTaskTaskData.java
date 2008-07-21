/**
 * MetaTaskTaskData.java
 *
 * The file MetaTaskTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

import java.io.File;

import openbiomind.gui.util.Utility;

/**
 * The class MetaTaskTaskData is used by the MetaTask task. The syntax of this task is as follows:
 *
 * <pre>
 * task.MetaTask &lt;-d dataset directory&gt; &lt;-o output&gt; [-numberOfTasks number of tasks] [-targetCategory case category] [-classificationMethod snpga|boolsimple|snplocal|snp|conventional|boolcomplex] [-metataskShuffling on|off]
 * </pre>
 *
 * @author bsanghvi
 * @since Jul 2, 2008
 * @version Jul 15, 2008
 */
public class MetaTaskTaskData extends AbstractTaskData {

   /** Name of this task is <code>task.DatasetTransformer</code>. */
   private static final String TASK_NAME = "task.MetaTask"; //$NON-NLS-1$

   /** Argument <code>-d</code> for specifying the name of original dataset (i.e. the input dataset). */
   private static final String ARG_D = HYPHEN + "d"; //$NON-NLS-1$

   /** Argument <code>-o</code> for specifying name of the name of the output directory. */
   private static final String ARG_O = HYPHEN + "o"; //$NON-NLS-1$

   /** Argument <code>-numberOfTasks</code> for specifying the number of tasks. */
   private static final String ARG_NUMBER_OF_TASKS = HYPHEN + "numberOfTasks"; //$NON-NLS-1$

   /** Argument <code>-targetCategory</code> for specifying the category. */
   private static final String ARG_TARGET_CATEGORY = HYPHEN + "targetCategory"; //$NON-NLS-1$

   /**
    * Argument <code>-classificationMethod</code> for specifying the feature selection method.
    *
    * @see ClassificationMethodEnum
    */
   private static final String ARG_CLASSIFICATION_METHOD = HYPHEN + "classificationMethod"; //$NON-NLS-1$

   /**
    * Argument <code>-metataskShuffling</code> for specifying if permutation analysis much be used or not?
    *
    * @see MetaTaskShufflingEnum
    */
   private static final String ARG_METATASK_SHUFFLING = HYPHEN + "metataskShuffling"; //$NON-NLS-1$

   /**
    * Instantiates a new enhance dataset task.
    */
   public MetaTaskTaskData() {
      super(TASK_NAME);
   }

   /**
    * Gets the dataset directory (argument = <code>-d</code>).
    *
    * @return the dataset directory
    */
   public String getDatasetDirectory() {
      return getArgument(ARG_D);
   }

   /**
    * Sets the dataset directory (argument = <code>-d</code>).
    *
    * @param datasetDirectory the dataset directory
    */
   public void setDatasetDirectory(final String datasetDirectory) {
      if (!Utility.isEmpty(datasetDirectory)) {
         putArgument(ARG_D, datasetDirectory);
      }
   }

   /**
    * Gets the output directory (argument = <code>-d</code>).
    *
    * @return the output directory
    */
   public String getOutputDirectory() {
      return getArgument(ARG_O);
   }

   /**
    * Sets the output directory (argument = <code>-d</code>).
    *
    * @param outputDirectory the output directory
    */
   public void setOutputDirectory(final String outputDirectory) {
      if (!Utility.isEmpty(outputDirectory)) {
         putArgument(ARG_O, outputDirectory);
      }
   }

   /**
    * Gets the number of tasks (argument = <code>-numberOfTasks</code>).
    *
    * @return the number of folds
    */
   public Integer getNumberOfTasks() {
      return Integer.valueOf(getArgument(ARG_NUMBER_OF_TASKS));
   }

   /**
    * Sets the number of tasks (argument = <code>-numberOfTasks</code>).
    *
    * @param numberOfTasks the number of tasks
    */
   public void setNumberOfTasks(final Integer numberOfTasks) {
      if (numberOfTasks != null) {
         putArgument(ARG_NUMBER_OF_TASKS, numberOfTasks.toString());
      }
   }

   /**
    * Gets the target category (argument = <code>-targetCategory</code>).
    *
    * @return the target category
    */
   public String getTargetCategory() {
      return getArgument(ARG_TARGET_CATEGORY);
   }

   /**
    * Sets the target category (argument = <code>-targetCategory</code>).
    *
    * @param targetCategory the target category
    */
   public void setTargetCategory(final String targetCategory) {
      if (!Utility.isEmpty(targetCategory)) {
         putArgument(ARG_TARGET_CATEGORY, targetCategory);
      }
   }

   /**
    * Gets the classification method (argument = <code>-classificationMethod</code>).
    *
    * @return the ontology association file
    */
   public ClassificationMethodEnum getClassificationMethod() {
      return ClassificationMethodEnum.parse(getArgument(ARG_CLASSIFICATION_METHOD));
   }

   /**
    * Sets the classification method (argument = <code>-classificationMethod</code>).
    *
    * @param featureSelectionMethod the feature selection method, must be one of {@link ClassificationMethodEnum}.
    */
   public void setClassficationMethod(final ClassificationMethodEnum featureSelectionMethod) {
      if (featureSelectionMethod != null) {
         putArgument(ARG_CLASSIFICATION_METHOD, featureSelectionMethod.toString());
      }
   }

   /**
    * Gets the meta task shuffling.
    *
    * @return the meta task shuffling
    *
    * @see MetaTaskShufflingEnum
    */
   public MetaTaskShufflingEnum getMetaTaskShuffling() {
      return MetaTaskShufflingEnum.parse(getArgument(ARG_METATASK_SHUFFLING));
   }

   /**
    * Sets the meta task shuffling.
    *
    * @param metaTaskShuffling the meta task shuffling
    *
    * @see MetaTaskShufflingEnum
    */
   public void setMetaTaskShuffling(final MetaTaskShufflingEnum metaTaskShuffling) {
      if (metaTaskShuffling != null) {
         putArgument(ARG_METATASK_SHUFFLING, metaTaskShuffling.toString());
      }
   }

   /*
    * @see openbiomind.gui.tasks.AbstractTaskData#createTaskDataProject()
    */
   @Override
   public TaskDataProject createTaskDataProject() {
      final TaskDataProject taskDataProject = new TaskDataProject(getProjectName());
      taskDataProject.add(createTrainTestPairTaskDataFolder(ARG_D, getDatasetDirectory()));
      taskDataProject.add(createOutputTaskDataFolder(ARG_O, getOutputDirectory()));
      return taskDataProject;
   }

   /**
    * Creates task data folder and links the train and test files as pairs.
    *
    * @param folderName the folder name
    * @param folderPath the path of the folder whose files are to be linked
    *
    * @return the task data folder
    */
   private TaskDataFolder createTrainTestPairTaskDataFolder(final String folderName, final String folderPath) {
      final File directory = new File(folderPath);
      final String directoryPath = directory.getAbsolutePath();
      final TaskDataFolder taskDataFolder = new TaskDataFolder(folderName);
      final String[] trainFiles = Utility.listFileNames(directory, Resources.TRAIN_FILE_STARTS_WITH,
            Resources.TAB_EXTENSION);
      final String[] testFiles = Utility.listFileNames(directory, Resources.TEST_FILE_STARTS_WITH,
            Resources.TAB_EXTENSION);

      final int size = trainFiles.length < testFiles.length ? trainFiles.length : testFiles.length;

      if (size >= 0) {
         taskDataFolder.add(createTrainTestPairTaskDataFolder("0", directoryPath, trainFiles[0], testFiles[0], false)); //$NON-NLS-1$
         for (int i = 1; i < size; i++) {
            taskDataFolder.add(createTrainTestPairTaskDataFolder(Integer.toString(i), directoryPath, trainFiles[i],
                  testFiles[i], false));
         }
      }

      return taskDataFolder;
   }

   /**
    * Creates the train test pair task data folder.
    *
    * @param directoryName the directory name
    * @param directoryPath the directory path
    * @param trainFileName the train file name
    * @param testFileName the test file name
    * @param autoOpen the auto open
    *
    * @return the task data folder
    */
   private TaskDataFolder createTrainTestPairTaskDataFolder(final String directoryName, final String directoryPath,
         final String trainFileName, final String testFileName, final boolean autoOpen) {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(directoryName);
      taskDataFolder.add(createTaskDataFile(trainFileName, directoryPath, autoOpen));
      taskDataFolder.add(createTaskDataFile(testFileName, directoryPath, autoOpen));
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
   private TaskDataFile createTaskDataFile(final String fileName, final String filePath, final boolean autoOpen) {
      final TaskDataFile taskDataFile = new TaskDataFile(fileName);
      taskDataFile.setPath(filePath + File.separator + fileName);
      taskDataFile.setLinked(true);
      taskDataFile.setAutoOpen(autoOpen);
      return taskDataFile;
   }

   /**
    * Creates the output task data folder.
    *
    * @param folderName the folder name
    * @param folderPath the folder path
    *
    * @return the task data folder
    */
   private TaskDataFolder createOutputTaskDataFolder(final String folderName, final String folderPath) {
      final File directory = new File(folderPath);
      final String directoryPath = directory.getAbsolutePath();
      final TaskDataFolder taskDataFolder = new TaskDataFolder(folderName);
      final String[] outFilesArray = Utility.listFileNames(directory, Resources.OUT_FILE_STARTS_WITH,
            Resources.TXT_EXTENSION);

      for (final String outFile : outFilesArray) {
         taskDataFolder.add(createTaskDataFile(outFile, directoryPath, Resources.OUT_FINAL_FILENAME.equals(outFile)));
      }

      return taskDataFolder;
   }

}
