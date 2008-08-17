/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (EnhanceDatasetTaskData.java) is part of OpenBiomind-GUI.
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
 * The class EnhanceDatasetTaskData is used by the EnhanceDataset task. The syntax of this task is as follows:
 * 
 * <pre>
 * task.EnhanceDataset &lt;-d original dataset&gt; &lt;-e enhanced dataset&gt; [-ontologyDescriptionFile ontology description file] [-ontologyAssociationFile ontology association file]
 * </pre>
 * 
 * @author bsanghvi
 * @since Jun 12, 2008
 * @version Aug 18, 2008
 */
public class EnhanceDatasetTaskData extends AbstractTaskData {

   /** Name of this task is <code>task.EnhanceDataset</code>. */
   private static final String TASK_NAME = "task.EnhanceDataset"; //$NON-NLS-1$

   /** Argument <code>-e</code> for specifying the name of enhanced dataset (i.e. the output dataset). */
   private static final Argument ARG_E = new Argument("e", Messages.EnhanData_ArgE_FName); //$NON-NLS-1$

   /**
    * Instantiates a new enhance dataset task.
    */
   public EnhanceDatasetTaskData() {
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
    * Gets the enhanced dataset (argument = <code>-e</code>).
    * 
    * @return the enhanced dataset
    */
   public String getEnhancedDataset() {
      return getPairedArgument().get(ARG_E.argument());
   }

   /**
    * Sets the enhanced dataset (argument = <code>-e</code>).
    * 
    * @param enhancedDataset the enhanced dataset
    */
   public void setEnhancedDataset(final String enhancedDataset) {
      getPairedArgument().put(ARG_E.argument(), enhancedDataset);
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

   /*
    * @see openbiomind.gui.data.AbstractTaskData#createInputFolder()
    */
   @Override
   protected TaskDataFolder createInputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_In);
      taskDataFolder.add(createTaskDataFile(getInputDataset(), true, false, Resources.TAB_EXTENSION));
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
      taskDataFolder.add(createTaskDataFile(getEnhancedDataset(), true, true, Resources.TAB_EXTENSION));
      return taskDataFolder;
   }

}
