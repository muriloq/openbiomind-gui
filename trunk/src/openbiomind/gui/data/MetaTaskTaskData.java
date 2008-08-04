/**
 * MetaTaskTaskData.java
 *
 * The file MetaTaskTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.data;

import openbiomind.gui.project.TaskDataFolder;

/**
 * The class MetaTaskTaskData is used by the MetaTask task. The syntax of this task is as follows:
 *
 * <pre>
 * task.MetaTask &lt;-d dataset directory&gt; &lt;-o output&gt; [-numberOfTasks number of tasks] [-targetCategory case category] [-classificationMethod snpga|boolsimple|snplocal|snp|conventional|boolcomplex] [-metataskShuffling on|off]
 * </pre>
 *
 * @author bsanghvi
 * @since Jul 2, 2008
 * @version Aug 3, 2008
 */
public class MetaTaskTaskData extends AbstractTaskData {

   /** Name of this task is <code>task.DatasetTransformer</code>. */
   private static final String TASK_NAME = "task.MetaTask"; //$NON-NLS-1$

   /**
    * Instantiates a new enhance dataset task.
    */
   public MetaTaskTaskData() {
      super(TASK_NAME);
   }

   /**
    * Gets the dataset directory (argument = <code>-d</code>).
    *
    * @return the dataset directory
    */
   public String getDatasetDirectory() {
      return getPairedArgument().get(ARG_D.argument());
   }

   /**
    * Sets the dataset directory (argument = <code>-d</code>).
    *
    * @param datasetDirectory the dataset directory
    */
   public void setDatasetDirectory(final String datasetDirectory) {
      getPairedArgument().put(ARG_D.argument(), datasetDirectory);
   }

   /**
    * Gets the output directory (argument = <code>-d</code>).
    *
    * @return the output directory
    */
   public String getOutputDirectory() {
      return getPairedArgument().get(ARG_O.argument());
   }

   /**
    * Sets the output directory (argument = <code>-d</code>).
    *
    * @param outputDirectory the output directory
    */
   public void setOutputDirectory(final String outputDirectory) {
      getPairedArgument().put(ARG_O.argument(), outputDirectory);
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

   /*
    * @see openbiomind.gui.data.AbstractTaskData#createInputFolder()
    */
   @Override
   protected TaskDataFolder createInputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_In);
      taskDataFolder.add(createTaskDataFolder(getDatasetDirectory(), true));
      return taskDataFolder;
   }

   /*
    * @see openbiomind.gui.data.AbstractTaskData#createOutputFolder()
    */
   @Override
   protected TaskDataFolder createOutputFolder() {
      final TaskDataFolder taskDataFolder = new TaskDataFolder(Messages.Folder_Out);
      taskDataFolder.add(createTaskDataFolder(getOutputDirectory(), true));
      return taskDataFolder;
   }

}
