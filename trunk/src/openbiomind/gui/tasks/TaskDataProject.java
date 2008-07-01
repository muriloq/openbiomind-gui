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

   /**
    * Instantiates a new task data project.
    */
   public TaskDataProject() {
      this(EMPTY, null);
   }

   /**
    * Instantiates a new task data project.
    *
    * @param name the name
    */
   public TaskDataProject(final String name) {
      this(name, null);
   }

   /**
    * The constructor.
    *
    * @param name the name
    * @param taskDataFolderSet the task data folder set
    */
   public TaskDataProject(final String name, final Set<TaskDataFolder> taskDataFolderSet) {
      setName(name);
      setTaskDataFolderSet(taskDataFolderSet);
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

   /*
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      final StringBuilder stringBuilder = new StringBuilder(getName());
      final String separator = "\n\t - "; //$NON-NLS-1$
      Set<TaskDataFolder> folderSet = getTaskDataFolderSet();
      for (final TaskDataFolder taskDataFolder : folderSet) {
         stringBuilder.append(separator + taskDataFolder);
      }
      return stringBuilder.toString();
   }

   /*
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(final Object otherObject) {
      if (otherObject != null && otherObject instanceof TaskDataProject) {
         final TaskDataProject otherTaskDataProject = (TaskDataProject) otherObject;
         return (getName().equals(otherTaskDataProject.getName())
               && getTaskDataFolderSet().equals(otherTaskDataProject.getTaskDataFolderSet()));
      }

      return false;
   }

}
