/**
 * AbstractTaskData.java
 *
 * The file AbstractTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

import static openbiomind.gui.util.Constants.HYPHEN;
import static openbiomind.gui.util.Constants.QUOTE;
import static openbiomind.gui.util.Constants.SPACE;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import openbiomind.gui.preferences.Preference;

/**
 * The class AbstractTaskData.
 *
 * @author bsanghvi
 * @since Jun 12, 2008
 * @version Jun 12, 2008
 */
public abstract class AbstractTaskData implements Serializable {

   /** The Constant serialVersionUID. */
   private static final long serialVersionUID = 1L;

   /** The <code>java</code> command for executing Java programs. */
   public static final String JAVA_COMMAND = "java"; //$NON-NLS-1$

   /** Argument <code>-cp</code> for specifying the classpath. */
   public static final String JAVA_ARGUMENT_CLASSPATH = HYPHEN + "cp"; //$NON-NLS-1$

   /** The task name. */
   private String taskName = null;

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
         this.taskName = ""; //$NON-NLS-1$
      }

      return this.taskName;
   }

   /**
    * Sets the task name.
    *
    * @param taskName the taskName to set
    */
   public void setTaskName(String taskName) {
      this.taskName = taskName;
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
    * @return previous value associated with the <code>key</code>, or <code>null</code> if there was
    * no mapping (or <code>null</code> mapping)
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
   public String getArgument(String key) {
      return getArgumentMap().get(key);
   }

   /**
    * Gets the command.
    *
    * @return the command
    */
   public String getCommand() {
      return (JAVA_COMMAND + SPACE + JAVA_ARGUMENT_CLASSPATH + SPACE + getClasspath() + SPACE
            + getTaskName() + SPACE + getAllArgumentString()).trim();
   }

   /*
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      return getCommand();
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

   /**
    * Gets the classpath.
    *
    * @return the classpath
    */
   private String getClasspath() {
      return QUOTE + Preference.getOpenBiomindJarLocation() + File.pathSeparator
            + Preference.getPipelinePropertiesHome() + QUOTE;
   }

   /**
    * Gets the all argument string.
    *
    * @return the all argument string
    */
   private String getAllArgumentString() {
      final StringBuilder argumentStringBuilder = new StringBuilder(""); //$NON-NLS-1$

      final Set<Entry<String, String>> entrySet = getArgumentMap().entrySet();
      for (final Entry<String, String> entry : entrySet) {
         argumentStringBuilder.append(entry.getKey() + SPACE + entry.getValue() + SPACE);
      }

      return argumentStringBuilder.toString();
   }

//   /**
//    * Gets the syntax.
//    *
//    * @return the syntax
//    */
//   public abstract String getSyntax();

}
