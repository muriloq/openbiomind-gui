/**
 * DatasetTransformerTaskData.java
 *
 * The file DatasetTransformerTaskData.java.
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
 * The class DatasetTransformerTaskData is used by the DatasetTransformer task. The syntax of this task is as follows:
 *
 * <pre>
 * task.DatasetTransformer &lt;-d dataset&gt; &lt;-o output dir&gt; [-targetCategory category] [-numberOfFolds folds|-testDataset test dataset] [-numberOfSelectedFeatures nf] [-featureSelectionMethod differentiation|SAM]
 * </pre>
 *
 * @author bsanghvi
 * @since Jul 2, 2008
 * @version Jul 27, 2008
 */
public class DatasetTransformerTaskData extends AbstractTaskData {

   /** Name of this task is <code>task.DatasetTransformer</code>. */
   private static final String TASK_NAME = "task.DatasetTransformer"; //$NON-NLS-1$

   /** Argument <code>-testDataset</code> for specifying the name of test dataset. */
   private static final Argument ARG_TEST_DATASET = new Argument("testDataset", "Test Dataset"); //$NON-NLS-1$

   /**
    * Instantiates a new enhance dataset task.
    */
   public DatasetTransformerTaskData() {
      super(TASK_NAME);
   }

   /**
    * Gets the original dataset (argument = <code>-d</code>).
    *
    * @return the original dataset
    */
   public String getOriginalDataset() {
      return getPairedArgument().get(ARG_D.argument());
   }

   /**
    * Sets the original dataset (argument = <code>-d</code>).
    *
    * @param originalDataset the original dataset
    */
   public void setOriginalDataset(final String originalDataset) {
      getPairedArgument().put(ARG_D.argument(), originalDataset);
   }

   /**
    * Gets the output directory (argument = <code>-d</code>).
    *
    * @return the output directory
    */
   public String getOutputDirectory() {
      return getPairedArgument().get(ARG_O.argument());
   }

   /**
    * Sets the output directory (argument = <code>-d</code>).
    *
    * @param outputDirectory the output directory
    */
   public void setOutputDirectory(final String outputDirectory) {
      getPairedArgument().put(ARG_O.argument(), outputDirectory);
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
      if (!Utility.isEmpty(targetCategory)) {
         getPairedArgument().put(ARG_TARGET_CATEGORY.argument(), targetCategory);
      }
   }

   /**
    * Gets the number of folds (argument = <code>-numberOfFolds</code>).
    *
    * @return the number of folds
    */
   public Integer getNumberOfFolds() {
      return getPairedArgument().getInteger(ARG_NUMBER_OF_FOLDS.argument());
   }

   /**
    * Sets the number of folds (argument = <code>-numberOfFolds</code>). Removes the argument <code>-testDataset</code>
    * if it is already set.
    *
    * @param numberOfFolds the number of folds
    */
   public void setNumberOfFolds(final Integer numberOfFolds) {
      if (numberOfFolds != null) {
         getPairedArgument().remove(ARG_TEST_DATASET.argument());
         getPairedArgument().put(ARG_NUMBER_OF_FOLDS.argument(), numberOfFolds);
      }
   }

   /**
    * Gets the test dataset (argument = <code>-testDataset</code>).
    *
    * @return the number of folds
    */
   public String getTestDataset() {
      return getPairedArgument().get(ARG_TEST_DATASET.argument());
   }

   /**
    * Sets the test dataset (argument = <code>-testDataset</code>). Removes the argument <code>-numberOfFolds</code> if
    * it is already set.
    *
    * @param testDataset the test dataset
    */
   public void setTestDataset(final String testDataset) {
      if (!Utility.isEmpty(testDataset)) {
         getPairedArgument().remove(ARG_NUMBER_OF_FOLDS.argument());
         getPairedArgument().put(ARG_TEST_DATASET.argument(), testDataset);
      }
   }

   /**
    * Gets the number of selected features (argument = <code>-numberOfSelectedFeatures</code>).
    *
    * @return the number of selected features
    */
   public Integer getNumberOfSelectedFeatures() {
      return getPairedArgument().getInteger(ARG_NUMBER_OF_SELECTED_FEATURES.argument());
   }

   /**
    * Sets the number of selected features (argument = <code>-numberOfSelectedFeatures</code>).
    *
    * @param numberOfSelectedFeatures the number of selected features
    */
   public void setNumberOfSelectedFeatures(final Integer numberOfSelectedFeatures) {
      getPairedArgument().put(ARG_NUMBER_OF_SELECTED_FEATURES.argument(), numberOfSelectedFeatures);
   }

   /**
    * Gets the feature selection method (argument = <code>-featureSelectionMethod</code>).
    *
    * @return the ontology association file
    */
   public FeatureSelectionMethodEnum getFeatureSelectionMethod() {
      return getPairedArgument().getFeatureSelectionMethodEnum(ARG_FEATURE_SELECTION_METHOD.argument());
   }

   /**
    * Sets the feature selection method (argument = <code>-featureSelectionMethod</code>).
    *
    * @param featureSelectionMethod the feature selection method, must be one of
    * {@link FeatureSelectionMethodEnum#DIFFERENTIATION} or {@link FeatureSelectionMethodEnum#SAM}.
    */
   public void setFeatureSelectionMethod(final FeatureSelectionMethodEnum featureSelectionMethod) {
      getPairedArgument().put(ARG_FEATURE_SELECTION_METHOD.argument(), featureSelectionMethod);
   }

   /*
    * @see openbiomind.gui.tasks.AbstractTaskData#createTaskDataProject()
    */
   @Override
   public TaskDataProject createTaskDataProject() {
      final TaskDataProject taskDataProject = new TaskDataProject(getProjectName());
      taskDataProject.add(createTaskDataFolder(ARG_D.friendlyName(), getOriginalDataset(), false));
      taskDataProject.add(createTrainTestPairTaskDataFolder(ARG_O.friendlyName(), getOutputDirectory()));
      taskDataProject.add(createTaskDataFolder(ARG_TEST_DATASET.friendlyName(), getTestDataset(), false));
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
         taskDataFolder.add(createTrainTestPairTaskDataFolder("0", directoryPath, trainFiles[0], testFiles[0], true)); //$NON-NLS-1$
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
    * Creates the task data folder.
    *
    * @param folderName the folder name
    * @param filePath the file path
    * @param autoOpen the auto open
    *
    * @return the task data folder
    */
   private TaskDataFolder createTaskDataFolder(final String folderName, final String filePath, final boolean autoOpen) {
      if (Utility.isEmpty(filePath)) {
         return null;
      } else {
         final TaskDataFile taskDataFile = new TaskDataFile();
         taskDataFile.setPath(filePath);
         taskDataFile.setLinked(true);
         taskDataFile.setAutoOpen(autoOpen);

         final TaskDataFolder taskDataFolder = new TaskDataFolder(folderName);
         taskDataFolder.add(taskDataFile);

         return taskDataFolder;
      }
   }

}
