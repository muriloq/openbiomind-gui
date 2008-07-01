/**
 * TaskDataFolder.java
 *
 * The file TaskDataFolder.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

import static openbiomind.gui.util.Constants.EMPTY;

import java.util.HashSet;
import java.util.Set;

/**
 * The class TaskDataFolder.
 *
 * @author bsanghvi
 * @since Jun 30, 2008
 * @version Jun 30, 2008
 */
public class TaskDataFolder {

   /** The name. */
   private String name = null;

   /** The task data file set. */
   private Set<TaskDataFile> taskDataFileSet = null;

   /**
    * Instantiates a new task data folder.
    */
   public TaskDataFolder() {
      this(EMPTY, null);
   }

   /**
    * Instantiates a new task data folder.
    *
    * @param name the name
    */
   public TaskDataFolder(final String name) {
      this(name, null);
   }

   /**
    * The constructor.
    *
    * @param name the name
    * @param taskDataFileSet the task data file set
    */
   public TaskDataFolder(final String name, final Set<TaskDataFile> taskDataFileSet) {
      setName(name);
      setTaskDataFileSet(taskDataFileSet);
   }

   /**
    * Gets the name.
    *
    * @return the name
    */
   public String getName() {
      return this.name;
   }

   /**
    * Sets the name.
    *
    * @param name the name to set
    */
   public void setName(final String name) {
      if (name != null) {
         this.name = name;
      } else {
         this.name = EMPTY;
      }
   }

   /**
    * Gets the task data file set.
    *
    * @return the task data file set
    */
   public Set<TaskDataFile> getTaskDataFileSet() {
      return this.taskDataFileSet;
   }

   /**
    * Sets the task data file set.
    *
    * @param taskDataFileSet the task data file set
    */
   public void setTaskDataFileSet(final Set<TaskDataFile> taskDataFileSet) {
      if (taskDataFileSet != null) {
         this.taskDataFileSet = taskDataFileSet;
      } else {
         this.taskDataFileSet = new HashSet<TaskDataFile>();
      }
   }

   /**
    * Adds the task data file.
    *
    * @param taskDataFile the task data file
    *
    * @return true, if add is successful
    *
    * @see java.util.Set#add(java.lang.Object)
    */
   public boolean add(final TaskDataFile taskDataFile) {
      if (taskDataFile != null) {
         return getTaskDataFileSet().add(taskDataFile);
      } else {
         return false;
      }
   }

   /*
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      final StringBuilder stringBuilder = new StringBuilder(getName());
      final String separator = "\n\t\t - "; //$NON-NLS-1$
      Set<TaskDataFile> fileSet = getTaskDataFileSet();
      for (final TaskDataFile taskDataFile : fileSet) {
         stringBuilder.append(separator + taskDataFile);
      }
      return stringBuilder.toString();
   }

   /*
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(final Object otherObject) {
      if (otherObject != null && otherObject instanceof TaskDataFolder) {
         final TaskDataFolder otherTaskDataFolder = (TaskDataFolder) otherObject;
         return (getName().equals(otherTaskDataFolder.getName())
               && getTaskDataFileSet().equals(otherTaskDataFolder.getTaskDataFileSet()));
      }

      return false;
   }

}
