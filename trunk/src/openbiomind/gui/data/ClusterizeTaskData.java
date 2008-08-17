/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (ClusterizeTaskData.java) is part of OpenBiomind-GUI.
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

import openbiomind.gui.project.TaskDataFolder;

/**
 * The class ClusterizeTaskData is used by the ClusterizeTaskData task. The syntax of this task is as follows:
 * 
 * <pre>
 * task.Clusterize &lt;-d clustering dataset&gt; &lt;-o output file&gt; [-datasetClusteringMetric Cosine|Euclidean|SNP]
 * </pre>
 * 
 * @author bsanghvi
 * @since Jul 18, 2008
 * @version Aug 18, 2008
 */
public class ClusterizeTaskData extends AbstractTaskData {

   /** Name of this task is <code>task.ClusteringTransformer</code>. */
   private static final String TASK_NAME = "task.Clusterize"; //$NON-NLS-1$

   /**
    * Instantiates a new clusterize task data.
    */
   public ClusterizeTaskData() {
      super(TASK_NAME);
   }

   /**
    * Gets the clustering dataset file (argument = <code>-d</code>).
    * 
    * @return the clustering dataset file
    */
   public String getClusteringDatasetFile() {
      return getPairedArgument().get(ARG_D.argument());
   }

   /**
    * Sets the clustering dataset file (argument = <code>-d</code>).
    * 
    * @param clusteringDatasetFile the clustering dataset file
    */
   public void setClusteringDatasetFile(final String clusteringDatasetFile) {
      getPairedArgument().put(ARG_D.argument(), clusteringDatasetFile);
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

   /*
    * @see openbiomind.gui.data.AbstractTaskData#createInputFolder()
    */
   @Override
   protected TaskDataFolder createInputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_In);
      taskDataFolder.add(createTaskDataFile(getClusteringDatasetFile(), true, false, Resources.TAB_EXTENSION));
      return taskDataFolder;
   }

   /*
    * @see openbiomind.gui.data.AbstractTaskData#createOutputFolder()
    */
   @Override
   protected TaskDataFolder createOutputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_Out);
      taskDataFolder.add(createTaskDataFile(getOutputFile(), true, true, Resources.TXT_EXTENSION));
      return taskDataFolder;
   }

}
