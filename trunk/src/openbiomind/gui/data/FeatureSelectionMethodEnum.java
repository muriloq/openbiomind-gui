/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (FeatureSelectionMethodEnum.java) is part of OpenBiomind-GUI.
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
 * The enum FeatureSelectionMethodEnum.
 * 
 * @author bsanghvi
 * @since Jul 15, 2008
 * @version Aug 18, 2008
 */
public enum FeatureSelectionMethodEnum {

   /** The <code>differentiation</code> method. */
   DIFFERENTIATION("differentiation"), //$NON-NLS-1$

   /** The <code>SAM</code> method. */
   SAM("SAM"), //$NON-NLS-1$

   // End of allowed values
   ;

   /** The name. */
   private String name = null;

   /**
    * Instantiates a new feature selection method enum.
    * 
    * @param name the name
    */
   private FeatureSelectionMethodEnum(final String name) {
      this.name = name;
   }

   /**
    * Parses the string to find the FeatureSelectionMethodEnum.
    * 
    * @param string the string
    * 
    * @return the feature selection method enum
    */
   public static FeatureSelectionMethodEnum parse(final String string) {
      if (DIFFERENTIATION.toString().equals(string)) {
         return DIFFERENTIATION;
      } else if (SAM.toString().equals(string)) {
         return SAM;
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
