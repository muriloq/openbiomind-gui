/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (ClusteringTransformerTaskData.java) is part of OpenBiomind-GUI.
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

/**
 * The class ClusteringTransformerTaskData is used by the ClusteringTransformer task. The syntax of this task is as
 * follows:
 * 
 * <pre>
 * task.ClusteringTransformer &lt;-d dataset file&gt; &lt;-o output file&gt; &lt;-t transform&gt; [-p metatask results dir]
 * </pre>
 * 
 * @author bsanghvi
 * @since Jul 13, 2008
 * @version Aug 18, 2008
 */
public class ClusteringTransformerTaskData extends AbstractTaskData {

   /** Name of this task is <code>task.ClusteringTransformer</code>. */
   private static final String TASK_NAME = "task.ClusteringTransformer"; //$NON-NLS-1$

   /** Argument <code>-t</code> for specifying the transform. */
   private static final Argument ARG_T = new Argument("t"); //$NON-NLS-1$

   /** Argument <code>-p</code> for specifying the meta task results directory. */
   private static final Argument ARG_P = new Argument("p", Messages.ClustTrans_ArgP_FName); //$NON-NLS-1$

   /**
    * Instantiates a new clustering transformer task data.
    */
   public ClusteringTransformerTaskData() {
      super(TASK_NAME);
   }

   /**
    * Gets the dataset file (argument = <code>-d</code>).
    * 
    * @return the dataset file
    */
   public String getDatasetFile() {
      return getPairedArgument().get(ARG_D.argument());
   }

   /**
    * Sets the dataset file (argument = <code>-d</code>).
    * 
    * @param datasetFile the dataset file
    */
   public void setDatasetFile(final String datasetFile) {
      getPairedArgument().put(ARG_D.argument(), datasetFile);
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
    * Gets the transform (argument = <code>-t</code>).
    * 
    * @return the transform
    */
   public TransformEnum getTransform() {
      return getPairedArgument().getTransformEnum(ARG_T.argument());
   }

   /**
    * Sets the transform (argument = <code>-t</code>).
    * 
    * @param transform the transform
    */
   public void setTransform(final TransformEnum transform) {
      getPairedArgument().put(ARG_T.argument(), transform);
   }

   /**
    * Gets the meta task result directory (argument = <code>-p</code>).
    * 
    * @return the meta task result directory
    */
   public String getMetaTaskResultDir() {
      return getPairedArgument().get(ARG_P.argument());
   }

   /**
    * Sets the meta task result directory (argument = <code>-p</code>).
    * 
    * @param metaTaskResultDir the meta task result directory
    */
   public void setMetaTaskResultDir(final String metaTaskResultDir) {
      getPairedArgument().put(ARG_P.argument(), metaTaskResultDir);
   }

   /*
    * @see openbiomind.gui.data.AbstractTaskData#createInputFolder()
    */
   @Override
   protected TaskDataFolder createInputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_In);
      taskDataFolder.add(createTaskDataFile(getDatasetFile(), true, false, Resources.TAB_EXTENSION));
      taskDataFolder.add(createTaskDataFolder(getMetaTaskResultDir(), true));
      return taskDataFolder;
   }

   /*
    * @see openbiomind.gui.data.AbstractTaskData#createOutputFolder()
    */
   @Override
   protected TaskDataFolder createOutputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_Out);
      taskDataFolder.add(createTaskDataFile(getOutputFile(), true, true, Resources.TAB_EXTENSION));
      return taskDataFolder;
   }

}
