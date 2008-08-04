/**
 * CompletePipelineTaskData.java
 *
 * The file CompletePipelineTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.data;

import openbiomind.gui.common.Argument;
import openbiomind.gui.project.TaskDataFolder;
import openbiomind.gui.util.Utility;

/**
 * The class CompletePipelineTaskData is used by the CompletePipeline task. The syntax of this task is as follows:
 *
 * <pre>
 * task.CompletePipeline &lt;-d dataset&gt; &lt;-o output directory&gt; [-dt test dataset] [-p property file] [property options]
 * </pre>
 *
 * @author bsanghvi
 * @since Jul 2, 2008
 * @version Jul 28, 2008
 */
public class CompletePipelineTaskData extends AbstractTaskData {

   /** Name of this task is <code>task.CompletePipeline</code>. */
   private static final String TASK_NAME = "task.CompletePipeline"; //$NON-NLS-1$

   /** Argument <code>-dt</code> for specifying the name of test dataset. */
   private static final Argument ARG_DT = new Argument("dt", Messages.CompPipe_ArgDT_FName); //$NON-NLS-1$

   /** Argument <code>-p</code> for specifying the name of property file. */
   private static final Argument ARG_P = new Argument("p", Messages.CompPipe_ArgP_FName); //$NON-NLS-1$

   /** The graph image path. */
   private String graphImagePath = null;

   /**
    * Instantiates a new complete pipeline task data.
    */
   public CompletePipelineTaskData() {
      super(TASK_NAME);
      setMemoryIntensive(true);
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
    * Gets the test dataset (argument = <code>-dt</code>).
    *
    * @return the test dataset
    */
   public String getTestDataset() {
      return getPairedArgument().get(ARG_DT.argument());
   }

   /**
    * Sets the test dataset (argument = <code>-dt</code>).
    *
    * @param testDataset the test dataset
    */
   public void setTestDataset(final String testDataset) {
      getPairedArgument().put(ARG_DT.argument(), testDataset);
   }

   /**
    * Gets the property file (argument = <code>-p</code>).
    *
    * @return the property file
    */
   public String getPropertyFile() {
      return getPairedArgument().get(ARG_P.argument());
   }

   /**
    * Sets the property file (argument = <code>-p</code>).
    *
    * @param propertyFile the property file
    */
   public void setPropertyFile(final String propertyFile) {
      getPairedArgument().put(ARG_P.argument(), propertyFile);
   }

   /**
    * Gets the graph image path.
    *
    * @return the graph image path
    */
   public String getGraphImagePath() {
      return this.graphImagePath;
   }

   /**
    * Sets the graph image path.
    *
    * @param graphImagePath the new graph image path
    */
   public void setGraphImagePath(final String graphImagePath) {
      this.graphImagePath = graphImagePath;
   }

   /**
    * Gets the number of top useful features (argument = <code>-topNUseful</code>).
    *
    * @return the top useful features
    */
   public Integer getTopUsefulFeatures() {
      return getPairedArgument().getInteger(ARG_TOP_N_USEFUL.argument());
   }

   /**
    * Sets the number of top useful features (argument = <code>-topNUseful</code>).
    *
    * @param topUsefulFeatures the top useful features
    */
   public void setTopUsefulFeatures(final Integer topUsefulFeatures) {
      getPairedArgument().put(ARG_TOP_N_USEFUL.argument(), topUsefulFeatures);
   }

   /**
    * Gets the number of maximum co-occurrence edges (argument = <code>-topNCooc</code>).
    *
    * @return the maximum co-occurrence edges
    */
   public Integer getMaximumCoOccurrenceEdges() {
      return getPairedArgument().getInteger(ARG_TOP_N_COOC.argument());
   }

   /**
    * Sets the number of maximum co-occurrence edges (argument = <code>-topNCooc</code>).
    *
    * @param maximumCoOccurrenceEdges the maximum co-occurrence edges
    */
   public void setMaximumCoOccurrenceEdges(final Integer maximumCoOccurrenceEdges) {
      getPairedArgument().put(ARG_TOP_N_COOC.argument(), maximumCoOccurrenceEdges);
   }

   /**
    * Gets the number of maximum co-expression edges (argument = <code>-topNCoex</code>).
    *
    * @return the maximum co-expression edges
    */
   public Integer getMaximumCoExpressionEdges() {
      return getPairedArgument().getInteger(ARG_TOP_N_COEX.argument());
   }

   /**
    * Sets the number of maximum co-expression edges (argument = <code>-topNCoex</code>).
    *
    * @param maximumCoExpressionEdges the maximum co-expression edges
    */
   public void setMaximumCoExpressionEdges(final Integer maximumCoExpressionEdges) {
      getPairedArgument().put(ARG_TOP_N_COEX.argument(), maximumCoExpressionEdges);
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
    * Sets the number of folds (argument = <code>-numberOfFolds</code>). if it is already set.
    *
    * @param numberOfFolds the number of folds
    */
   public void setNumberOfFolds(final Integer numberOfFolds) {
      getPairedArgument().put(ARG_NUMBER_OF_FOLDS.argument(), numberOfFolds);
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

   /**
    * Gets the ontology description file (argument = <code>-ontologyDescriptionFile</code>).
    *
    * @return the ontology description file
    */
   public String getOntologyDescriptionFile() {
      return getPairedArgument().get(ARG_ONTOLOGY_DESCRIPTION_FILE.argument());
   }

   /**
    * Sets the ontology description file (argument = <code>-ontologyDescriptionFile</code>).
    *
    * @param ontologyDescriptionFile the ontology description file
    */
   public void setOntologyDescriptionFile(final String ontologyDescriptionFile) {
      getPairedArgument().put(ARG_ONTOLOGY_DESCRIPTION_FILE.argument(), ontologyDescriptionFile);
   }

   /**
    * Gets the ontology association file (argument = <code>-ontologyAssociationFile</code>).
    *
    * @return the ontology association file
    */
   public String getOntologyAssociationFile() {
      return getPairedArgument().get(ARG_ONTOLOGY_ASSOCIATION_FILE.argument());
   }

   /**
    * Sets the ontology association file (argument = <code>-ontologyAssociationFile</code>).
    *
    * @param ontologyAssociationFile the ontology association file
    */
   public void setOntologyAssociationFile(final String ontologyAssociationFile) {
      getPairedArgument().put(ARG_ONTOLOGY_ASSOCIATION_FILE.argument(), ontologyAssociationFile);
   }

   /**
    * Gets the number of tasks (argument = <code>-numberOfTasks</code>).
    *
    * @return the number of folds
    */
   public Integer getNumberOfTasks() {
      return getPairedArgument().getInteger(ARG_NUMBER_OF_TASKS.argument());
   }

   /**
    * Sets the number of tasks (argument = <code>-numberOfTasks</code>).
    *
    * @param numberOfTasks the number of tasks
    */
   public void setNumberOfTasks(final Integer numberOfTasks) {
      getPairedArgument().put(ARG_NUMBER_OF_TASKS.argument(), numberOfTasks);
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

   /**
    * Gets the classification method (argument = <code>-classificationMethod</code>).
    *
    * @return the ontology association file
    */
   public ClassificationMethodEnum getClassificationMethod() {
      return getPairedArgument().getClassificationMethodEnum(ARG_CLASSIFICATION_METHOD.argument());
   }

   /**
    * Sets the classification method (argument = <code>-classificationMethod</code>).
    *
    * @param featureSelectionMethod the feature selection method, must be one of {@link ClassificationMethodEnum}.
    */
   public void setClassficationMethod(final ClassificationMethodEnum featureSelectionMethod) {
      getPairedArgument().put(ARG_CLASSIFICATION_METHOD.argument(), featureSelectionMethod);
   }

   /**
    * Gets the meta task shuffling.
    *
    * @return the meta task shuffling
    *
    * @see ShuffleEnum
    */
   public ShuffleEnum getMetaTaskShuffling() {
      return getPairedArgument().getShuffleEnum(ARG_METATASK_SHUFFLING.argument());
   }

   /**
    * Sets the meta task shuffling.
    *
    * @param metaTaskShuffling the meta task shuffling
    *
    * @see ShuffleEnum
    */
   public void setMetaTaskShuffling(final ShuffleEnum metaTaskShuffling) {
      getPairedArgument().put(ARG_METATASK_SHUFFLING.argument(), metaTaskShuffling);
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

   /**
    * Gets the isFeatureSelected (argument = <code>-isFeatureSelected</code>).
    *
    * @return the is feature selected
    */
   public Boolean getIsFeatureSelected() {
      return getPairedArgument().getBoolean(ARG_IS_FEATURE_SELECTED.argument());
   }

   /**
    * Sets the isFeatureSelected (argument = <code>-isFeatureSelected</code>).
    *
    * @param isFeatureSelected the is feature selected
    */
   public void setIsFeatureSelected(final Boolean isFeatureSelected) {
      getPairedArgument().put(ARG_IS_FEATURE_SELECTED.argument(), isFeatureSelected);
   }

   /**
    * Gets the isFolded (argument = <code>-isFolded</code>).
    *
    * @return the is feature selected
    */
   public Boolean getIsFolded() {
      return getPairedArgument().getBoolean(ARG_IS_FOLDED.argument());
   }

   /**
    * Sets the isFolded (argument = <code>-isFolded</code>).
    *
    * @param isFolded the is folded
    */
   public void setIsFolded(final Boolean isFolded) {
      getPairedArgument().put(ARG_IS_FOLDED.argument(), isFolded);
   }

   /*
    * @see openbiomind.gui.data.AbstractTaskData#createInputFolder()
    */
   @Override
   protected TaskDataFolder createInputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_In);
      taskDataFolder.add(createTaskDataFile(getInputDataset(), true, false, Resources.TAB_EXTENSION));
      taskDataFolder.add(createTaskDataFile(getTestDataset(), true, false, Resources.TAB_EXTENSION));
      taskDataFolder.add(createTaskDataFile(getPropertyFile(), true, false, Resources.PROPERTIES_EXTENSION));
      taskDataFolder.add(createTaskDataFile(getOntologyDescriptionFile(), true, false, Resources.TAB_EXTENSION));
      taskDataFolder.add(createTaskDataFile(getOntologyAssociationFile(), true, false, Resources.TAB_EXTENSION));
      return taskDataFolder;
   }

   /*
    * @see openbiomind.gui.data.AbstractTaskData#createOutputFolder()
    */
   @Override
   protected TaskDataFolder createOutputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_Out);
      taskDataFolder.add(createTaskDataFolder(getOutputDirectory(), true));
      final String imagePath = getGraphImagePath();
      taskDataFolder.add(createTaskDataFile(Utility.extractFileName(imagePath), imagePath, false, false));
      return taskDataFolder;
   }

}
