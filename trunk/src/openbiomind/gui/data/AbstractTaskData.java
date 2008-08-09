/**
 * AbstractTaskData.java
 *
 * The file AbstractTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.data;

import java.util.List;

import openbiomind.gui.common.Constants;
import openbiomind.gui.common.PairedArgument;
import openbiomind.gui.project.TaskDataFile;
import openbiomind.gui.project.TaskDataFolder;
import openbiomind.gui.util.Utility;

/**
 * The class AbstractTaskData.
 *
 * @author bsanghvi
 * @since Jun 12, 2008
 * @version Aug 9, 2008
 */
public abstract class AbstractTaskData implements Constants, ConstantArguments {

   /** The task name. */
   private String taskName = null;

   /** The memory intensive operation flag. */
   private boolean memoryIntensive = false;

   /** The paired argument. */
   private PairedArgument pairedArgument = null;

   /**
    * Instantiates a new abstract task.
    *
    * @param taskName the task name
    */
   protected AbstractTaskData(final String taskName) {
      setTaskName(taskName);
      this.pairedArgument = new PairedArgument();
   }

   /**
    * Creates the task data folder.
    *
    * @return the task data folder
    */
   public TaskDataFolder createTaskDataFolder() {
      final TaskDataFolder taskDataProject = new TaskDataFolder(getTaskName());
      taskDataProject.add(createInputFolder());
      taskDataProject.add(createOutputFolder());
      return taskDataProject;
   }

   /**
    * Creates the input folder.
    *
    * @return the task data folder
    */
   protected abstract TaskDataFolder createInputFolder();

   /**
    * Creates the output folder.
    *
    * @return the task data folder
    */
   protected abstract TaskDataFolder createOutputFolder();

   /**
    * Gets the paired arguments.
    *
    * @return the paired arguments
    */
   protected PairedArgument getPairedArgument() {
      return this.pairedArgument;
   }

   /**
    * Gets the task name.
    *
    * @return the taskName
    */
   public String getTaskName() {
      if (this.taskName == null) {
         this.taskName = Constants.EMPTY;
      }

      return this.taskName;
   }

   /**
    * Sets the task name.
    *
    * @param taskName the taskName to set
    */
   public void setTaskName(final String taskName) {
      this.taskName = taskName;
   }

   /**
    * Checks if is memory intensive.
    *
    * @return true, if is memory intensive
    */
   public boolean isMemoryIntensive() {
      return this.memoryIntensive;
   }

   /**
    * Sets the memory intensive.
    *
    * @param memoryIntensive the new memory intensive
    */
   public void setMemoryIntensive(final boolean memoryIntensive) {
      this.memoryIntensive = memoryIntensive;
   }

   /**
    * This method converts the currently stored arguments into a list of the form
    * <code>[key1 value1, key2 value2, ...]</code>. Hence, the size of this list is same as the number of arguments
    * presently stored. To get on a list of all elements, use {@link #toList()}.
    *
    * @return the list of arguments
    *
    * @see #toList()
    * @see openbiomind.gui.common.PairedArgument#toArgumentList()
    */
   public List<String> toArgumentList() {
      return getPairedArgument().toArgumentList();
   }

   /**
    * This returns a string of the form <code>key1 value1 key2 value2 ...</code>.
    *
    * @return the argument string
    *
    * @see openbiomind.gui.common.PairedArgument#toArgumentString()
    */
   public String toArgumentString() {
      return getPairedArgument().toArgumentString();
   }

   /**
    * This method converts the currently stored arguments into a list of the form
    * <code>[key1, value1, key2, value2, ...]</code>. Hence, the size of this list is twice the number of arguments
    * presently stored. To get a space separated list of arguments, use {@link #toArgumentList()}.
    *
    * @return the list of arguments
    *
    * @see #toArgumentList()
    * @see openbiomind.gui.common.PairedArgument#toList()
    */
   public List<String> toList() {
      return getPairedArgument().toList();
   }

   /*
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      return getTaskName() + Constants.SPACE + toArgumentString();
   }

   /*
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(final Object otherObject) {
      if (otherObject != null && otherObject instanceof AbstractTaskData) {
         final AbstractTaskData otherTaskData = (AbstractTaskData) otherObject;

         if ((getTaskName() != null && getTaskName().equals(otherTaskData.getTaskName()))
               && getPairedArgument().equals(otherTaskData.getPairedArgument())) {
            return true;
         }
      }

      return false;
   }

   /**
    * Creates the task data file.
    *
    * @param fileName the file name
    * @param filePath the file path
    * @param linked the linked
    * @param autoOpen the auto open
    *
    * @return the task data file
    */
   protected TaskDataFile createTaskDataFile(final String fileName, final String filePath, final boolean linked,
         final boolean autoOpen) {
      return ((!Utility.isEmpty(filePath) && !Utility.isEmpty(fileName)) ? new TaskDataFile(fileName, filePath, linked,
            autoOpen) : null);
   }

   /**
    * Creates the task data file.
    *
    * @param filePath the file path
    * @param linked the linked
    * @param autoOpen the auto open
    *
    * @return the task data file
    */
   protected TaskDataFile createTaskDataFile(final String filePath, final boolean linked, final boolean autoOpen) {
      return createTaskDataFile(Utility.extractFullName(filePath), filePath, linked, autoOpen);
   }

   /**
    * Creates the task data file.
    *
    * @param filePath the file path
    * @param linked the linked
    * @param autoOpen the auto open
    * @param fileExtension the file extension
    *
    * @return the task data file
    */
   protected TaskDataFile createTaskDataFile(final String filePath, final boolean linked, final boolean autoOpen,
         final String fileExtension) {
      final String fileName = Utility.extractFullName(filePath);
      if (!fileName.endsWith(fileExtension)) {
         return createTaskDataFile(fileName + fileExtension, filePath, linked, autoOpen);
      } else {
         return createTaskDataFile(fileName, filePath, linked, autoOpen);
      }
   }

   /**
    * Creates the task data folder.
    *
    * @param folderName the folder name
    * @param folderPath the folder path
    * @param linked the linked
    *
    * @return the task data folder
    */
   protected TaskDataFolder createTaskDataFolder(final String folderName, final String folderPath, final boolean linked) {
      return ((!Utility.isEmpty(folderPath) && !Utility.isEmpty(folderName)) ? new TaskDataFolder(folderName,
            folderPath, linked) : null);

   }

   /**
    * Creates the task data folder.
    *
    * @param folderPath the folder path
    * @param linked the linked
    *
    * @return the task data folder
    */
   protected TaskDataFolder createTaskDataFolder(final String folderPath, final boolean linked) {
      return createTaskDataFolder(Utility.extractFullName(folderPath), folderPath, linked);
   }

}
