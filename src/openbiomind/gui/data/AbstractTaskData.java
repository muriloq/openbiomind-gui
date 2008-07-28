/**
 * AbstractTaskData.java
 *
 * The file AbstractTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.data;

import java.util.List;

import openbiomind.gui.common.ConstantArguments;
import openbiomind.gui.common.Constants;
import openbiomind.gui.common.PairedArgument;
import openbiomind.gui.project.TaskDataProject;

/**
 * The class AbstractTaskData.
 *
 * @author bsanghvi
 * @since Jun 12, 2008
 * @version Jul 27, 2008
 */
public abstract class AbstractTaskData implements Constants, ConstantArguments {

   /** The task name. */
   private String taskName = null;

   /** The project name. */
   private String projectName = null;

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
    * Creates the task data project.
    *
    * @return the task data project
    */
   public abstract TaskDataProject createTaskDataProject();

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
               && (getProjectName() != null && getProjectName().equals(otherTaskData.getProjectName()))
               && getPairedArgument().equals(otherTaskData.getPairedArgument())) {
            return true;
         }
      }

      return false;
   }

}
