/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (ClusteringColorsEnum.java) is part of OpenBiomind-GUI.
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

package openbiomind.gui.data;

/**
 * The enum ClusteringColorsEnum.
 * 
 * @author bsanghvi
 * @since Jul 20, 2008
 * @version Aug 18, 2008
 */
public enum ClusteringColorsEnum {

   /** The <code>traditional</code> clustering color. */
   TRADITIONAL("traditional"), //$NON-NLS-1$

   /** The <code>mono</code> clustering color. */
   MONO("mono"), //$NON-NLS-1$

   // End of allowed values
   ;

   /** The name. */
   private String name = null;

   /**
    * Instantiates a new clustering colors enum.
    * 
    * @param name the name
    */
   private ClusteringColorsEnum(final String name) {
      this.name = name;
   }

   /**
    * Parses the string to find the ClusteringColorsEnum.
    * 
    * @param string the string
    * 
    * @return the clustering colors enum
    */
   public static ClusteringColorsEnum parse(final String string) {
      if (TRADITIONAL.toString().equals(string)) {
         return TRADITIONAL;
      } else if (MONO.toString().equals(string)) {
         return MONO;
      }

      return null;
   }

   /*
    * @see java.lang.Enum#toString()
    */
   @Override
   public String toString() {
      return this.name;
   }

}
