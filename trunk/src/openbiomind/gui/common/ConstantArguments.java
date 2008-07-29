/**
 * ConstantArguments.java
 *
 * The file ConstantArguments.java.
 *
 * $Id$
 */
package openbiomind.gui.common;

/**
 * The interface ConstantArguments.
 *
 * @author bsanghvi
 * @since Jul 26, 2008
 * @version Jul 28, 2008
 */
public interface ConstantArguments {

   /**
    * Argument <code>classificationMethod</code> for specifying the feature selection method.
    *
    * @see openbiomind.gui.data.ClassificationMethodEnum
    */
   public static final Argument ARG_CLASSIFICATION_METHOD = new Argument("classificationMethod"); //$NON-NLS-1$

   /**
    * Argument <code>clusteringColors</code> for specifying the clustering colors.
    *
    * @see openbiomind.gui.data.ClusteringColorsEnum
    */
   public static final Argument ARG_CLUSTERING_COLORS = new Argument("clusteringColors"); //$NON-NLS-1$

   /** Argument <code>d</code> for specifying the base dataset. */
   public static final Argument ARG_D = new Argument("d", Messages.ConstArg_ArgD_FName); //$NON-NLS-1$

   /**
    * Argument <code>datasetClusteringMetric</code> for specifying the dataset clustering metric.
    *
    * @see openbiomind.gui.data.DatasetClusteringMetricEnum
    */
   public static final Argument ARG_DATASET_CLUSTERING_METRIC = new Argument("datasetClusteringMetric"); //$NON-NLS-1$

   /**
    * Argument <code>featureSelectionMethod</code> for specifying the feature selection method.
    *
    * @see openbiomind.gui.data.FeatureSelectionMethodEnum
    */
   public static final Argument ARG_FEATURE_SELECTION_METHOD = new Argument("featureSelectionMethod"); //$NON-NLS-1$

   //   public static final Argument ARG_IS_FEATURE_SELECTED = new Argument("isFeatureSelected"); //$NON-NLS-1$

   //   public static final Argument ARG_IS_FOLDED = new Argument("isFolded"); //$NON-NLS-1$

   /**
    * Argument <code>metataskShuffling</code> for specifying if permutation analysis much be used or not?
    *
    * @see openbiomind.gui.data.ShuffleEnum
    */
   public static final Argument ARG_METATASK_SHUFFLING = new Argument("metataskShuffling"); //$NON-NLS-1$

   /** Argument <code>numberOfFolds</code> for specifying the number of folds. */
   public static final Argument ARG_NUMBER_OF_FOLDS = new Argument("numberOfFolds"); //$NON-NLS-1$

   /** Argument <code>numberOfSelectedFeatures</code> for specifying the number of selected features. */
   public static final Argument ARG_NUMBER_OF_SELECTED_FEATURES = new Argument("numberOfSelectedFeatures"); //$NON-NLS-1$

   /** Argument <code>numberOfTasks</code> for specifying the number of tasks. */
   public static final Argument ARG_NUMBER_OF_TASKS = new Argument("numberOfTasks"); //$NON-NLS-1$

   /** Argument <code>o</code> for specifying the output file. */
   public static final Argument ARG_O = new Argument("o", Messages.ConstArg_ArgO_FName); //$NON-NLS-1$

   /** Argument <code>ontologyAssociationFile</code> for specifying ontology association file. */
   public static final Argument ARG_ONTOLOGY_ASSOCIATION_FILE = new Argument(
         "ontologyAssociationFile", Messages.ConstArg_ArgOntAssoFile_FName); //$NON-NLS-1$

   /** Argument <code>ontologyDescriptionFile</code> for specifying ontology description file. */
   public static final Argument ARG_ONTOLOGY_DESCRIPTION_FILE = new Argument(
         "ontologyDescriptionFile", Messages.ConstArg_ArgOntDescFile_FName); //$NON-NLS-1$

   //   public static final Argument ARG_SNP_SELECTION_SHUFFLE = new Argument("snpSelectionShuffle"); //$NON-NLS-1$

   /** Argument <code>targetCategory</code> for specifying the category. */
   public static final Argument ARG_TARGET_CATEGORY = new Argument("targetCategory"); //$NON-NLS-1$

   /** Argument <code>-topNCoex</code> for specifying the number of maximum co-expression edges. */
   public static final Argument ARG_TOP_N_COEX = new Argument("topNCoex"); //$NON-NLS-1$

   /** Argument <code>-topNCooc</code> for specifying the number of maximum co-occurrence edges. */
   public static final Argument ARG_TOP_N_COOC = new Argument("topNCooc"); //$NON-NLS-1$

   /** Argument <code>-topNUseful</code> for specifying the number of top useful features. */
   public static final Argument ARG_TOP_N_USEFUL = new Argument("topNUseful"); //$NON-NLS-1$

}
