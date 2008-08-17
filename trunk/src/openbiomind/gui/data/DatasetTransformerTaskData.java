/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (DatasetTransformerTaskData.java) is part of OpenBiomind-GUI.
 *
 *  OpenBiomind-GUI is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenBiomind-GUI is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenBiomind-GUI.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Please visit the following pages to contact the author(s):
 *  Homepage: http://code.google.com/p/openbiomind-gui/
 *  Mailing list: http://groups.google.com/group/openbiomind-gui/
 */

package openbiomind.gui.data;

import openbiomind.gui.common.Argument;
import openbiomind.gui.project.TaskDataFolder;
import openbiomind.gui.util.Utility;

/**
 * The class DatasetTransformerTaskData is used by the DatasetTransformer task. The syntax of this task is as follows:
 * 
 * <pre>
 * task.DatasetTransformer &lt;-d dataset&gt; &lt;-o output directory&gt; [-targetCategory category] [-numberOfFolds folds|-testDataset test dataset] [-numberOfSelectedFeatures nf] [-featureSelectionMethod differentiation|SAM]
 * </pre>
 * 
 * @author bsanghvi
 * @since Jul 2, 2008
 * @version Aug 18, 2008
 */
public class DatasetTransformerTaskData extends AbstractTaskData {

   /** Name of this task is <code>task.DatasetTransformer</code>. */
   private static final String TASK_NAME = "task.DatasetTransformer"; //$NON-NLS-1$

   /** Argument <code>-testDataset</code> for specifying the name of test dataset. */
   private static final Argument ARG_TEST_DATASET = new Argument("testDataset", Messages.DataTrans_ArgTData_FName); //$NON-NLS-1$

   /**
    * Instantiates a new enhance dataset task.
    */
   public DatasetTransformerTaskData() {
      super(TASK_NAME);
   }

   /**
    * Gets the input dataset (argument = <code>-d</code>).
    * 
    * @return the input dataset
    */
   public String getInputDataset() {
      return getPairedArgument().get(ARG_D.argument());
   }

   /**
    * Sets the input dataset (argument = <code>-d</code>).
    * 
    * @param inputDataset the input dataset
    */
   public void setInputDataset(final String inputDataset) {
      getPairedArgument().put(ARG_D.argument(), inputDataset);
   }

   /**
    * Gets the output directory (argument = <code>-o</code>).
    * 
    * @return the output directory
    */
   public String getOutputDirectory() {
      return getPairedArgument().get(ARG_O.argument());
   }

   /**
    * Sets the output directory (argument = <code>-o</code>).
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
    * @return the test dataset
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
    * @see openbiomind.gui.data.AbstractTaskData#createInputFolder()
    */
   @Override
   protected TaskDataFolder createInputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_In);
      taskDataFolder.add(createTaskDataFile(getInputDataset(), true, false, Resources.TAB_EXTENSION));
      taskDataFolder.add(createTaskDataFile(getTestDataset(), true, false, Resources.TAB_EXTENSION));
      return taskDataFolder;
   }

   /*
    * @see openbiomind.gui.data.AbstractTaskData#createOutputFolder()
    */
   @Override
   protected TaskDataFolder createOutputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_Out);
      taskDataFolder.add(createTaskDataFolder(getOutputDirectory(), true));
      return taskDataFolder;
   }

}
