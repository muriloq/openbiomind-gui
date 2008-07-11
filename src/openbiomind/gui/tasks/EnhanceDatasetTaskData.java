/**
 * EnhanceDatasetTaskData.java
 *
 * The file EnhanceDatasetTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

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
 * @version Jul 10, 2008
 */
public class EnhanceDatasetTaskData extends AbstractTaskData {

   /** Name of this task is <code>task.EnhanceDataset</code>. */
   private static final String TASK_NAME = "task.EnhanceDataset"; //$NON-NLS-1$

   /** Argument <code>-d</code> for specifying the name of original dataset (i.e. the input dataset). */
   private static final String ARG_D = HYPHEN + "d"; //$NON-NLS-1$

   /** Argument <code>-e</code> for specifying name of the name of enhanced dataset (i.e. the output dataset). */
   private static final String ARG_E = HYPHEN + "e"; //$NON-NLS-1$

   /** Argument <code>-ontologyDescriptionFile</code> for specifying the name of ontology association file. */
   private static final String ARG_ONTOLOGY_DESCRIPTION_FILE = HYPHEN + "ontologyDescriptionFile"; //$NON-NLS-1$

   /** Argument <code>-ontologyAssociationFile</code> for specifying the name of the ontology description file. */
   private static final String ARG_ONTOLOGY_ASSOCIATION_FILE = HYPHEN + "ontologyAssociationFile"; //$NON-NLS-1$

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
      return getArgument(ARG_D);
   }

   /**
    * Sets the original dataset (argument = <code>-d</code>).
    *
    * @param originalDataset the original dataset
    */
   public void setOriginalDataset(final String originalDataset) {
      if (!Utility.isEmpty(originalDataset)) {
         putArgument(ARG_D, originalDataset);
      }
   }

   /**
    * Gets the enhanced dataset (argument = <code>-e</code>).
    *
    * @return the enhanced dataset
    */
   public String getEnhancedDataset() {
      return getArgument(ARG_E);
   }

   /**
    * Sets the enhanced dataset (argument = <code>-e</code>).
    *
    * @param enhancedDataset the enhanced dataset
    */
   public void setEnhancedDataset(final String enhancedDataset) {
      if (!Utility.isEmpty(enhancedDataset)) {
         putArgument(ARG_E, enhancedDataset);
      }
   }

   /**
    * Gets the ontology description file (argument = <code>-ontologyDescriptionFile</code>).
    *
    * @return the ontology description file
    */
   public String getOntologyDescriptionFile() {
      return getArgument(ARG_ONTOLOGY_DESCRIPTION_FILE);
   }

   /**
    * Sets the ontology description file (argument = <code>-ontologyDescriptionFile</code>).
    *
    * @param ontologyDescriptionFile the ontology description file
    */
   public void setOntologyDescriptionFile(final String ontologyDescriptionFile) {
      if (!Utility.isEmpty(ontologyDescriptionFile)) {
         putArgument(ARG_ONTOLOGY_DESCRIPTION_FILE, ontologyDescriptionFile);
      }
   }

   /**
    * Gets the ontology association file (argument = <code>-ontologyAssociationFile</code>).
    *
    * @return the ontology association file
    */
   public String getOntologyAssociationFile() {
      return getArgument(ARG_ONTOLOGY_ASSOCIATION_FILE);
   }

   /**
    * Sets the ontology association file (argument = <code>-ontologyAssociationFile</code>).
    *
    * @param ontologyAssociationFile the ontology association file
    */
   public void setOntologyAssociationFile(final String ontologyAssociationFile) {
      if (!Utility.isEmpty(ontologyAssociationFile)) {
         putArgument(ARG_ONTOLOGY_ASSOCIATION_FILE, ontologyAssociationFile);
      }
   }

   /*
    * @see openbiomind.gui.tasks.AbstractTaskData#createTaskDataProject()
    */
   @Override
   public TaskDataProject createTaskDataProject() {
      final TaskDataProject taskDataProject = new TaskDataProject(getProjectName());
      taskDataProject.add(createTaskDataFolder(ARG_D, getOriginalDataset(), false));
      taskDataProject.add(createTaskDataFolder(ARG_E, getEnhancedDataset(), true));
      taskDataProject.add(createTaskDataFolder(ARG_ONTOLOGY_ASSOCIATION_FILE, getOntologyAssociationFile(), false));
      taskDataProject.add(createTaskDataFolder(ARG_ONTOLOGY_DESCRIPTION_FILE, getOntologyDescriptionFile(), false));
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
