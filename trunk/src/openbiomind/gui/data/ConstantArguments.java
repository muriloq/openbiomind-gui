/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (ConstantArguments.java) is part of OpenBiomind-GUI.
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

/**
 * The interface ConstantArguments.
 * 
 * @author bsanghvi
 * @since Jul 26, 2008
 * @version Aug 18, 2008
 */
public interface ConstantArguments {

   /** Argument <code>classificationMethod</code> for specifying the feature selection method. */
   public static final Argument ARG_CLASSIFICATION_METHOD = new Argument("classificationMethod"); //$NON-NLS-1$

   /** Argument <code>clusteringColors</code> for specifying the clustering colors. */
   public static final Argument ARG_CLUSTERING_COLORS = new Argument("clusteringColors"); //$NON-NLS-1$

   /** Argument <code>d</code> for specifying the base dataset. */
   public static final Argument ARG_D = new Argument("d", Messages.ConstArg_ArgD_FName); //$NON-NLS-1$

   /** Argument <code>datasetClusteringMetric</code> for specifying the dataset clustering metric. */
   public static final Argument ARG_DATASET_CLUSTERING_METRIC = new Argument("datasetClusteringMetric"); //$NON-NLS-1$

   /** Argument <code>featureSelectionMethod</code> for specifying the feature selection method. */
   public static final Argument ARG_FEATURE_SELECTION_METHOD = new Argument("featureSelectionMethod"); //$NON-NLS-1$

   /** Argument <code>isFeatureSelected</code> for specifying whether feature is selected or not. */
   public static final Argument ARG_IS_FEATURE_SELECTED = new Argument("isFeatureSelected"); //$NON-NLS-1$

   /** Argument <code>isFolded</code> for specifying whether folded or not. */
   public static final Argument ARG_IS_FOLDED = new Argument("isFolded"); //$NON-NLS-1$

   /** Argument <code>metataskShuffling</code> for specifying if permutation analysis much be used or not?. */
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
