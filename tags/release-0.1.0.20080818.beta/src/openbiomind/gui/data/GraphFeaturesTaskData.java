/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (GraphFeaturesTaskData.java) is part of OpenBiomind-GUI.
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
import openbiomind.gui.project.TaskDataFile;
import openbiomind.gui.project.TaskDataFolder;
import openbiomind.gui.util.Utility;

/**
 * The class GraphFeaturesTaskData is used by the GraphFeatures task. The syntax of this task is as follows:
 * 
 * <pre>
 * task.GraphFeatures &lt;-h horizontal dataset&gt; &lt;-m mobra dataset&gt; &lt;-u utility file&gt; &lt;-o output file&gt; [-topNUseful nu] [-topNCooc max co-occurence edges] [-topNCoex max co-expression edges]
 * </pre>
 * 
 * @author bsanghvi
 * @since Jul 20, 2008
 * @version Aug 18, 2008
 */
public class GraphFeaturesTaskData extends AbstractTaskData {

   /** Name of this task is <code>task.GraphFeatures</code>. */
   private static final String TASK_NAME = "task.GraphFeatures"; //$NON-NLS-1$

   /** Argument <code>-h</code> for specifying the horizontal dataset. */
   private static final Argument ARG_H = new Argument("h", Messages.GraphFeatures_ArgH_FName); //$NON-NLS-1$

   /** Argument <code>-m</code> for specifying the MOBRA result. */
   private static final Argument ARG_M = new Argument("m", Messages.GraphFeatures_ArgM_FName); //$NON-NLS-1$

   /** Argument <code>-u</code> for specifying the utility file. */
   private static final Argument ARG_U = new Argument("u", Messages.GraphFeatures_ArgU_FName); //$NON-NLS-1$

   /** The graph image path. */
   private String graphImagePath = null;

   /**
    * Instantiates a new view clusters task data.
    */
   public GraphFeaturesTaskData() {
      super(TASK_NAME);
   }

   /**
    * Gets the horizontal dataset (argument = <code>-d</code>).
    * 
    * @return the horizontal dataset
    */
   public String getHorizontalDataset() {
      return getPairedArgument().get(ARG_H.argument());
   }

   /**
    * Sets the horizontal dataset (argument = <code>-h</code>).
    * 
    * @param horizontalDataset the horizontal dataset
    */
   public void setHorizontalDataset(final String horizontalDataset) {
      getPairedArgument().put(ARG_H.argument(), horizontalDataset);
   }

   /**
    * Gets the MOBRA dataset (argument = <code>-m</code>).
    * 
    * @return the MOBRA dataset
    */
   public String getMobraDataset() {
      return getPairedArgument().get(ARG_M.argument());
   }

   /**
    * Sets the MOBRA dataset (argument = <code>-r</code>).
    * 
    * @param mobraDataset the MOBRA dataset
    */
   public void setMobraDataset(final String mobraDataset) {
      getPairedArgument().put(ARG_M.argument(), mobraDataset);
   }

   /**
    * Gets the utility file (argument = <code>-u</code>).
    * 
    * @return the utility file
    */
   public String getUtilityFile() {
      return getPairedArgument().get(ARG_U.argument());
   }

   /**
    * Sets the utility file (argument = <code>-u</code>).
    * 
    * @param utilityFile the new utility file
    */
   public void setUtilityFile(final String utilityFile) {
      getPairedArgument().put(ARG_U.argument(), utilityFile);
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

   /*
    * @see openbiomind.gui.data.AbstractTaskData#createInputFolder()
    */
   @Override
   protected TaskDataFolder createInputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_In);
      taskDataFolder.add(createTaskDataFile(getHorizontalDataset(), true, false, Resources.TAB_EXTENSION));
      taskDataFolder.add(createTaskDataFile(getMobraDataset(), true, false, Resources.TAB_EXTENSION));
      taskDataFolder.add(createTaskDataFile(getUtilityFile(), true, false, Resources.TAB_EXTENSION));
      return taskDataFolder;
   }

   /*
    * @see openbiomind.gui.data.AbstractTaskData#createOutputFolder()
    */
   @Override
   protected TaskDataFolder createOutputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_Out);
      final TaskDataFile outputFile = createTaskDataFile(getOutputFile(), true, true);
      outputFile.setEditorId(Properties.DEFAULT_TEXT_EDITOR_ID); // FIXME Temporary hack
      taskDataFolder.add(outputFile);
      final String imagePath = getGraphImagePath();
      taskDataFolder.add(createTaskDataFile(Utility.extractFileName(imagePath), imagePath, false, true));
      return taskDataFolder;
   }

}
