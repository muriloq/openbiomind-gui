/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (TaskDataProject.java) is part of OpenBiomind-GUI.
 *
 *  OpenBiomind-GUI is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenBiomind-GUI is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenBiomind-GUI.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Please visit the following pages to contact the author(s):
 *  Homepage: http://code.google.com/p/openbiomind-gui/
 *  Mailing list: http://groups.google.com/group/openbiomind-gui/
 */

package openbiomind.gui.project;

import static openbiomind.gui.common.Constants.EMPTY;

import java.util.HashSet;
import java.util.Set;

/**
 * The class TaskDataProject.
 * 
 * @author bsanghvi
 * @since Jun 30, 2008
 * @version Aug 18, 2008
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
