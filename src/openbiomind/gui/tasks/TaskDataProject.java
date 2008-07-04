/**
 * TaskDataProject.java
 *
 * The file TaskDataProject.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

import static openbiomind.gui.util.Constants.EMPTY;

import java.util.HashSet;
import java.util.Set;

/**
 * The class TaskDataProject.
 *
 * @author bsanghvi
 * @since Jun 30, 2008
 * @version Jun 30, 2008
 */
public class TaskDataProject {

   /** The name. */
   private String name = null;

   /** The task data folder set. */
   private Set<TaskDataFolder> taskDataFolderSet = null;

   /** The task data file set. */
   private Set<TaskDataFile> taskDataFileSet = null;

   /**
    * Instantiates a new task data project.
    */
   public TaskDataProject() {
      this(EMPTY);
   }

   /**
    * Instantiates a new task data project.
    *
    * @param name the name of the project
    */
   public TaskDataProject(final String name) {
      setName(name);
      setTaskDataFolderSet(null);
      setTaskDataFileSet(null);
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
    * Gets the task data folder set.
    *
    * @return the taskDataFolderSet
    */
   public Set<TaskDataFolder> getTaskDataFolderSet() {
      return this.taskDataFolderSet;
   }

   /**
    * Sets the task data folder set.
    *
    * @param taskDataFolderSet the new task data folder set
    */
   public void setTaskDataFolderSet(final Set<TaskDataFolder> taskDataFolderSet) {
      if (taskDataFolderSet != null) {
         this.taskDataFolderSet = taskDataFolderSet;
      } else {
         this.taskDataFolderSet = new HashSet<TaskDataFolder>();
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
    * Adds the task data folder.
    *
    * @param taskDataFolder the task data folder
    *
    * @return true, if add is successful
    *
    * @see java.util.Set#add(java.lang.Object)
    */
   public boolean add(final TaskDataFolder taskDataFolder) {
      if (taskDataFolder != null) {
         return this.taskDataFolderSet.add(taskDataFolder);
      } else {
         return false;
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
      return getName();
   }

   /*
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(final Object otherObject) {
      if (otherObject != null && otherObject instanceof TaskDataProject) {
         final TaskDataProject otherTaskDataProject = (TaskDataProject) otherObject;
         return (getName().equals(otherTaskDataProject.getName())
               && getTaskDataFolderSet().equals(otherTaskDataProject.getTaskDataFolderSet()) && getTaskDataFileSet()
               .equals(otherTaskDataProject.getTaskDataFileSet()));
      }

      return false;
   }

}
