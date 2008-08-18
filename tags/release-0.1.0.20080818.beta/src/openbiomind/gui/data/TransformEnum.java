/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (TransformEnum.java) is part of OpenBiomind-GUI.
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
 * The enum TransformEnum.
 * 
 * @author bsanghvi
 * @since Jul 15, 2008
 * @version Aug 18, 2008
 */
public enum TransformEnum {

   /** The <code>horizontal</code> transform. */
   HORIZONTAL("horizontal"), //$NON-NLS-1$

   /** The <code>vertical</code> transform. */
   VERTICAL("vertical"), //$NON-NLS-1$

   /** The <code>MUTIC</code> transform. */
   MUTIC("MUTIC"), //$NON-NLS-1$

   /** The <code>MOBRA</code> transform. */
   MOBRA("MOBRA"), //$NON-NLS-1$

   // End of allowed values
   ;

   /** The name. */
   private String name = null;

   /**
    * Instantiates a new transform enum.
    * 
    * @param name the name
    */
   private TransformEnum(final String name) {
      this.name = name;
   }

   /**
    * Parses the string to find the TransformEnum.
    * 
    * @param string the string
    * 
    * @return the transform enum
    */
   public static TransformEnum parse(final String string) {
      if (HORIZONTAL.toString().equals(string)) {
         return HORIZONTAL;
      } else if (VERTICAL.toString().equals(string)) {
         return VERTICAL;
      } else if (MUTIC.toString().equals(string)) {
         return MUTIC;
      } else if (MOBRA.toString().equals(string)) {
         return MOBRA;
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
