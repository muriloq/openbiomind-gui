/**
 * EnhanceDatasetTaskData.java
 *
 * The file EnhanceDatasetTaskData.java.
 *
 * $Id$
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
 * @version Aug 3, 2008
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
