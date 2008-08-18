/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (TaskDataFile.java) is part of OpenBiomind-GUI.
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

/**
 * The class TaskDataFile.
 * 
 * @author bsanghvi
 * @since Jun 30, 2008
 * @version Aug 18, 2008
 */
public class TaskDataFile {

   /** The name. */
   private String name = null;

   /** The path. */
   private String path = null;

   /** The linked. */
   private boolean linked = false;

   /** The auto open. */
   private boolean autoOpen = false;

   /** The editor id. */
   private String editorId = null;

   /**
    * Instantiates a new task data file.
    */
   public TaskDataFile() {
      this(EMPTY);
   }

   /**
    * Instantiates a new task data file.
    * 
    * @param name the name
    */
   public TaskDataFile(final String name) {
      this(name, EMPTY, false, false);
   }

   /**
    * Instantiates a new task data file.
    * 
    * @param name the name
    * @param path the path
    * @param linked the linked
    * @param autoOpen the auto open
    */
   public TaskDataFile(final String name, final String path, final boolean linked, final boolean autoOpen) {
      setName(name);
      setPath(path);
      setLinked(linked);
      setAutoOpen(autoOpen);
      setEditorId(null);
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
    * @param name the new name
    */
   public void setName(final String name) {
      if (name != null) {
         this.name = name;
      } else {
         this.name = EMPTY;
      }
   }

   /**
    * Gets the path.
    * 
    * @return the path
    */
   public String getPath() {
      return this.path;
   }

   /**
    * Sets the path.
    * 
    * @param path the path to set
    */
   public void setPath(final String path) {
      if (path != null) {
         this.path = path;
      } else {
         this.path = EMPTY;
      }
   }

   /**
    * Checks if the file must be linked.
    * 
    * @return true, if is linked
    */
   public boolean isLinked() {
      return this.linked;
   }

   /**
    * Sets if the file must be linked.
    * 
    * @param linked the new linked
    */
   public void setLinked(final boolean linked) {
      this.linked = linked;
   }

   /**
    * Checks if is auto open.
    * 
    * @return the autoOpen
    */
   public boolean isAutoOpen() {
      return this.autoOpen;
   }

   /**
    * Sets the auto open.
    * 
    * @param autoOpen the autoOpen to set
    */
   public void setAutoOpen(final boolean autoOpen) {
      this.autoOpen = autoOpen;
   }

   /**
    * Gets the editor id.
    * 
    * @return the editor id
    */
   public String getEditorId() {
      return this.editorId;
   }

   /**
    * Sets the editor id.
    * 
    * @param editorId the new editor id
    */
   public void setEditorId(final String editorId) {
      this.editorId = editorId;
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
      if (otherObject != null && otherObject instanceof TaskDataFile) {
         final TaskDataFile otherTaskDataFile = (TaskDataFile) otherObject;
         return getName().equals(otherTaskDataFile.getName()) && getPath().equals(otherTaskDataFile.getPath());
      }

      return false;
   }

}
