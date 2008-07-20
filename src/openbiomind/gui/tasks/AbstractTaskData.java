/**
 * AbstractTaskData.java
 *
 * The file AbstractTaskData.java.
 *
 * TODO THIS CLASS AND ITS SUBCLASSES NEED TO BE REFACTORED AND REDESIGNED.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import openbiomind.gui.util.Constants;

/**
 * The class AbstractTaskData.
 *
 * @author bsanghvi
 * @since Jun 12, 2008
 * @version Jul 20, 2008
 */
public abstract class AbstractTaskData implements Constants {

   /** The Constant serialVersionUID. */
   private static final long serialVersionUID = 1L;

   /** The task name. */
   private String taskName = null;

   /** The project name. */
   private String projectName = null;

   /** The memory intensive operation flag. */
   private boolean memoryIntensive = false;

   /** The argument map. */
   private Map<String, String> argumentMap = null;

   /**
    * Instantiates a new abstract task.
    *
    * @param taskName the task name
    */
   protected AbstractTaskData(final String taskName) {
      setTaskName(taskName);
   }

   /**
    * Gets the task name.
    *
    * @return the taskName
    */
   public String getTaskName() {
      if (this.taskName == null) {
         this.taskName = EMPTY;
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
    * Gets the project name.
    *
    * @return the project name
    */
   public String getProjectName() {
      return this.projectName;
   }

   /**
    * Sets the project name.
    *
    * @param projectName the new project name
    */
   public void setProjectName(final String projectName) {
      this.projectName = projectName;
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
    * Gets the argument map.
    *
    * @return the argumentMap
    */
   public Map<String, String> getArgumentMap() {
      if (this.argumentMap == null) {
         this.argumentMap = new HashMap<String, String>();
      }

      return this.argumentMap;
   }

   /**
    * Clears the argument map.
    *
    * @see java.util.Map#clear()
    */
   public void clearArgumentMap() {
      getArgumentMap().clear();
   }

   /**
    * Put argument as a key-value pair.
    *
    * @param key the key
    * @param value the value
    *
    * @return previous value associated with the <code>key</code>, or <code>null</code> if there was no mapping (or
    * <code>null</code> mapping)
    *
    * @see java.util.Map#put(java.lang.Object, java.lang.Object)
    */
   public String putArgument(final String key, final String value) {
      return getArgumentMap().put(key, value);
   }

   /**
    * Gets the argument.
    *
    * @param key the key
    *
    * @return the argument
    *
    * @see java.util.Map#get(java.lang.Object)
    */
   public String getArgument(final String key) {
      return getArgumentMap().get(key);
   }

   /**
    * Removes the mapping for the given <code>key</code>.
    *
    * @param key the key
    *
    * @return the string
    *
    * @see java.util.Map#remove(java.lang.Object)
    */
   public String remove(final String key) {
      return getArgumentMap().remove(key);
   }

   /*
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(final Object otherObject) {
      if (otherObject != null && otherObject instanceof AbstractTaskData) {
         final AbstractTaskData otherTaskData = (AbstractTaskData) otherObject;

         if (getTaskName().equals(otherTaskData.getTaskName())
               && getArgumentMap().equals(otherTaskData.getArgumentMap())) {
            return true;
         }
      }

      return false;
   }

   /*
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      final StringBuilder stringBuilder = new StringBuilder(getTaskName());
      final List<String> allArgumentsAsList = getAllArgumentsAsList();
      for (final String argument : allArgumentsAsList) {
         stringBuilder.append(SPACE).append(argument);
      }
      return stringBuilder.toString();
   }

   /**
    * Gets the all arguments as list.
    *
    * @return the all arguments as list
    */
   public List<String> getAllArgumentsAsList() {
      final Set<Entry<String, String>> entrySet = getArgumentMap().entrySet();
      final List<String> allArgumentList = new ArrayList<String>(entrySet.size());

      for (final Entry<String, String> entry : entrySet) {
         allArgumentList.add(entry.getKey());
         allArgumentList.add(entry.getValue());
      }

      return allArgumentList;
   }

   /**
    * Creates the task data project.
    *
    * @return the task data project
    */
   public abstract TaskDataProject createTaskDataProject();

}
