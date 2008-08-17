/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (Argument.java) is part of OpenBiomind-GUI.
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

package openbiomind.gui.common;

/**
 * The class Argument.
 * 
 * @author bsanghvi
 * @since Jul 26, 2008
 * @version Aug 18, 2008
 */
public class Argument {

   /** The id. */
   private String id = null;

   /** The argument. */
   private String argument = null;

   /** The friendly name. */
   private String friendlyName = null;

   /**
    * Instantiates a new argument.
    * 
    * @param id the id
    */
   public Argument(final String id) {
      this(id, id);
   }

   /**
    * Instantiates a new argument.
    * 
    * @param id the id
    * @param friendlyName the friendly name
    */
   public Argument(final String id, final String friendlyName) {
      this(id, friendlyName, Constants.HYPHEN + id);
   }

   /**
    * Instantiates a new argument.
    * 
    * @param id the id
    * @param friendlyName the friendly name
    * @param argument the argument
    */
   public Argument(final String id, final String friendlyName, final String argument) {
      this.id = id;
      this.friendlyName = friendlyName;
      this.argument = argument;
   }

   /**
    * Gets the id.
    * 
    * @return the id
    */
   public String getId() {
      return this.id;
   }

   /**
    * Sets the id.
    * 
    * @param id the new id
    */
   protected void setId(final String id) {
      this.id = id;
   }

   /**
    * Gets the argument.
    * 
    * @return the argument
    */
   public String argument() {
      return getArgument();
   }

   /**
    * Gets the argument.
    * 
    * @return the argument
    */
   public String getArgument() {
      return this.argument;
   }

   /**
    * Sets the argument.
    * 
    * @param argument the new argument
    */
   protected void setArgument(final String argument) {
      this.argument = argument;
   }

   /**
    * Gets the friendly name.
    * 
    * @return the friendly name
    */
   public String friendlyName() {
      return getFriendlyName();
   }

   /**
    * Gets the friendly name.
    * 
    * @return the friendly name
    */
   public String getFriendlyName() {
      return this.friendlyName;
   }

   /**
    * Sets the friendly name.
    * 
    * @param friendlyName the new friendly name
    */
   protected void setFriendlyName(final String friendlyName) {
      this.friendlyName = friendlyName;
   }

   /*
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      return getId();
   }

}
