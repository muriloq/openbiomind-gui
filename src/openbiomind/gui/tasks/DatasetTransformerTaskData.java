/**
 * DatasetTransformerTaskData.java
 *
 * The file DatasetTransformerTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

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
 * @version Jul 2, 2008
 */
public class DatasetTransformerTaskData extends AbstractTaskData {

   /**
    * Name of this task is <code>task.DatasetTransformer</code>.
    */
   public static final String TASK_NAME = "task.DatasetTransformer"; //$NON-NLS-1$

   /**
    * Argument <code>-d</code> for specifying the name of original dataset (i.e. the input dataset).
    */
   public static final String ARG_D = HYPHEN + "d"; //$NON-NLS-1$

   /**
    * Argument <code>-o</code> for specifying name of the name of the output directory.
    */
   public static final String ARG_O = HYPHEN + "o"; //$NON-NLS-1$

   /**
    * Argument <code>-targetCategory</code> for specifying the category.
    */
   public static final String ARG_TARGET_CATEGORY = HYPHEN + "targetCategory"; //$NON-NLS-1$

   /**
    * Argument <code>-numberOfFolds</code> for specifying the number of folds.
    */
   public static final String ARG_NUMBER_OF_FOLDS = HYPHEN + "numberOfFolds"; //$NON-NLS-1$

   /**
    * Argument <code>-testDataset</code> for specifying the name of test dataset.
    */
   public static final String ARG_TEST_DATASET = HYPHEN + "testDataset"; //$NON-NLS-1$

   /**
    * Argument <code>-numberOfSelectedFeatures</code> for specifying the number of selected features.
    */
   public static final String ARG_NUMBER_OF_SELECTED_FEATURES = HYPHEN + "numberOfSelectedFeatures"; //$NON-NLS-1$

   /**
    * Argument <code>-featureSelectionMethod</code> for specifying the feature selection method. Must be one of
    * {@link FeatureSelectionMethodEnum#DIFFERENTIATION} or {@link FeatureSelectionMethodEnum#SAM}.
    *
    * @see FeatureSelectionMethodEnum
    */
   public static final String ARG_FEATURE_SELECTION_METHOD = HYPHEN + "featureSelectionMethod"; //$NON-NLS-1$

   /**
    * The enum FeatureSelectionMethodEnum.
    */
   public enum FeatureSelectionMethodEnum {

      /** The differentiation method. */
      DIFFERENTIATION("differentiation"), //$NON-NLS-1$

      /** The SAM method. */
      SAM("SAM"); //$NON-NLS-1$

      /** The name. */
      private String name = null;

      /**
       * Instantiates a new feature selection method enum.
       *
       * @param name the name
       */
      private FeatureSelectionMethodEnum(final String name) {
         this.name = name;
      }

      /**
       * Parses the string to find the FeatureSelectionMethodEnum.
       *
       * @param string the string
       *
       * @return the feature selection method enum
       */
      public static FeatureSelectionMethodEnum parse(final String string) {
         if (DIFFERENTIATION.toString().equals(string)) {
            return DIFFERENTIATION;
         } else if (SAM.toString().equals(string)) {
            return SAM;
         } else {
            return null;
         }
      }

      /*
       * @see java.lang.Enum#toString()
       */
      @Override
      public String toString() {
         return this.name;
      }

   }

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
      return getArgument(ARG_D);
   }

   /**
    * Sets the original dataset (argument = <code>-d</code>).
    *
    * @param originalDataset the original dataset
    */
   public void setOriginalDataset(final String originalDataset) {
      if (!Utility.isEmpty(originalDataset)) {
         putArgument(ARG_D, originalDataset);
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
    * Gets the number of folds (argument = <code>-numberOfFolds</code>).
    *
    * @return the number of folds
    */
   public Integer getNumberOfFolds() {
      return Integer.valueOf(getArgument(ARG_NUMBER_OF_FOLDS));
   }

   /**
    * Sets the number of folds (argument = <code>-numberOfFolds</code>). Removes the argument <code>-testDataset</code>
    * if it is already set.
    *
    * @param numberOfFolds the number of folds
    */
   public void setNumberOfFolds(final Integer numberOfFolds) {
      if (numberOfFolds != null) {
         remove(ARG_TEST_DATASET);
         putArgument(ARG_NUMBER_OF_FOLDS, numberOfFolds.toString());
      }
   }

   /**
    * Gets the test dataset (argument = <code>-testDataset</code>).
    *
    * @return the number of folds
    */
   public String getTestDataset() {
      return getArgument(ARG_TEST_DATASET);
   }

   /**
    * Sets the test dataset (argument = <code>-testDataset</code>). Removes the argument <code>-numberOfFolds</code> if
    * it is already set.
    *
    * @param testDataset the test dataset
    */
   public void setTestDataset(final String testDataset) {
      remove(ARG_NUMBER_OF_FOLDS);
      putArgument(ARG_TEST_DATASET, testDataset);
   }

   /**
    * Gets the number of selected features (argument = <code>-numberOfSelectedFeatures</code>).
    *
    * @return the number of selected features
    */
   public Integer getNumberOfSelectedFeatures() {
      return Integer.valueOf(getArgument(ARG_NUMBER_OF_SELECTED_FEATURES));
   }

   /**
    * Sets the number of selected features (argument = <code>-numberOfSelectedFeatures</code>).
    *
    * @param numberOfSelectedFeatures the number of selected features
    */
   public void setNumberOfSelectedFeatures(final Integer numberOfSelectedFeatures) {
      if (numberOfSelectedFeatures != null) {
         putArgument(ARG_NUMBER_OF_SELECTED_FEATURES, numberOfSelectedFeatures.toString());
      }
   }

   /**
    * Gets the feature selection method (argument = <code>-featureSelectionMethod</code>).
    *
    * @return the ontology association file
    */
   public FeatureSelectionMethodEnum getFeatureSelectionMethod() {
      return FeatureSelectionMethodEnum.parse(getArgument(ARG_FEATURE_SELECTION_METHOD));
   }

   /**
    * Sets the feature selection method (argument = <code>-featureSelectionMethod</code>).
    *
    * @param featureSelectionMethod the feature selection method, must be one of
    * {@link FeatureSelectionMethodEnum#DIFFERENTIATION} or {@link FeatureSelectionMethodEnum#SAM}.
    */
   public void setFeatureSelectionMethod(final FeatureSelectionMethodEnum featureSelectionMethod) {
      if (featureSelectionMethod != null) {
         putArgument(ARG_FEATURE_SELECTION_METHOD, featureSelectionMethod.toString());
      }
   }

   /*
    * @see openbiomind.gui.tasks.AbstractTaskData#createTaskDataProject()
    */
   @Override
   public TaskDataProject createTaskDataProject() {
      final TaskDataProject taskDataProject = new TaskDataProject(getProjectName());
      taskDataProject.add(createTaskDataFolder(ARG_D, getOriginalDataset(), false));
      taskDataProject.add(createOutputDirectoryTaskDataFolder(ARG_O, getOutputDirectory()));
      taskDataProject.add(createTaskDataFolder(ARG_TEST_DATASET, getTestDataset(), false));
      return taskDataProject;
   }

   /**
    * Creates the output directory task data folder.
    *
    * @param folderName the folder name
    * @param linkedFolderPath the folder path for the folder to be linked
    *
    * @return the task data folder
    */
   private TaskDataFolder createOutputDirectoryTaskDataFolder(final String folderName, final String linkedFolderPath) {
      final TaskDataFolder linkedTaskDataFolder = new TaskDataFolder(Utility.extractName(linkedFolderPath));
      linkedTaskDataFolder.setLinked(true);
      linkedTaskDataFolder.setPath(linkedFolderPath);

      final TaskDataFolder taskDataFolder = new TaskDataFolder(folderName);
      taskDataFolder.add(linkedTaskDataFolder);
      return taskDataFolder;
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
