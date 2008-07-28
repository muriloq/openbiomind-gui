/**
 * EnhanceDatasetTaskData.java
 *
 * The file EnhanceDatasetTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.data;

import openbiomind.gui.common.Argument;
import openbiomind.gui.project.TaskDataFile;
import openbiomind.gui.project.TaskDataFolder;
import openbiomind.gui.project.TaskDataProject;
import openbiomind.gui.util.Utility;

/**
 * The class EnhanceDatasetTaskData is used by the EnhanceDataset task. The syntax of this task is as follows:
 *
 * <pre>
 * task.EnhanceDataset &lt;-d original dataset&gt; &lt;-e enhanced dataset&gt; [-ontologyDescriptionFile ontology description file] [-ontologyAssociationFile ontology association file]
 * </pre>
 *
 * @author bsanghvi
 * @since Jun 12, 2008
 * @version Jul 27, 2008
 */
public class EnhanceDatasetTaskData extends AbstractTaskData {

   /** Name of this task is <code>task.EnhanceDataset</code>. */
   private static final String TASK_NAME = "task.EnhanceDataset"; //$NON-NLS-1$

   /** Argument <code>-e</code> for specifying the name of enhanced dataset (i.e. the output dataset). */
   private static final Argument ARG_E = new Argument("e", "Enhanced Dataset"); //$NON-NLS-1$

   /**
    * Instantiates a new enhance dataset task.
    */
   public EnhanceDatasetTaskData() {
      super(TASK_NAME);
   }

   /**
    * Gets the original dataset (argument = <code>-d</code>).
    *
    * @return the original dataset
    */
   public String getOriginalDataset() {
      return getPairedArgument().get(ARG_D.argument());
   }

   /**
    * Sets the original dataset (argument = <code>-d</code>).
    *
    * @param originalDataset the original dataset
    */
   public void setOriginalDataset(final String originalDataset) {
      getPairedArgument().put(ARG_D.argument(), originalDataset);
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
    * @see openbiomind.gui.tasks.AbstractTaskData#createTaskDataProject()
    */
   @Override
   public TaskDataProject createTaskDataProject() {
      final TaskDataProject taskDataProject = new TaskDataProject(getProjectName());
      taskDataProject.add(createTaskDataFolder(ARG_D.friendlyName(), getOriginalDataset(), false));
      taskDataProject.add(createTaskDataFolder(ARG_E.friendlyName(), getEnhancedDataset(), true));
      taskDataProject.add(createTaskDataFolder(ARG_ONTOLOGY_ASSOCIATION_FILE.friendlyName(),
            getOntologyAssociationFile(), false));
      taskDataProject.add(createTaskDataFolder(ARG_ONTOLOGY_DESCRIPTION_FILE.friendlyName(),
            getOntologyDescriptionFile(), false));
      return taskDataProject;
   }

   /**
    * Creates the task data folder.
    *
    * @param folderName the folder name
    * @param filepath the file path
    * @param autoOpen the auto open
    *
    * @return the task data folder
    */
   private TaskDataFolder createTaskDataFolder(final String folderName, final String filepath, final boolean autoOpen) {
      if (Utility.isEmpty(filepath)) {
         return null;
      } else {
         final TaskDataFile taskDataFile = new TaskDataFile();
         taskDataFile.setPath(filepath);
         taskDataFile.setLinked(true);
         taskDataFile.setAutoOpen(autoOpen);

         final TaskDataFolder taskDataFolder = new TaskDataFolder(folderName);
         taskDataFolder.add(taskDataFile);

         return taskDataFolder;
      }
   }

}
