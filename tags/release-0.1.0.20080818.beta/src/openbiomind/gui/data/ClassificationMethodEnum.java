/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (ClassificationMethodEnum.java) is part of OpenBiomind-GUI.
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

import openbiomind.gui.util.Utility;

/**
 * The enum ClassificationMethodEnum.
 * 
 * @author bsanghvi
 * @since Jul 15, 2008
 * @version Aug 18, 2008
 */
public enum ClassificationMethodEnum {

   /** The <code>snpqa</code> method. */
   SNPQA("snpqa"), //$NON-NLS-1$

   /** The <code>boolsimple</code> method. */
   BOOLSIMPLE("boolsimple"), //$NON-NLS-1$

   /** The <code>snplocal</code> method. */
   SNPLOCAL("snplocal"), //$NON-NLS-1$

   /** The <code>snp</code> method. */
   SNP("snp"), //$NON-NLS-1$

   /** The <code>boolcomplex</code> method. */
   BOOLCOMPLEX("boolcomplex"), //$NON-NLS-1$

   /** The <code>conventional</code> method. */
   CONVENTIONAL("conventional"), //$NON-NLS-1$

   // End of allowed values
   ;

   /** The name. */
   private String name = null;

   /**
    * Instantiates a new feature selection method enum.
    * 
    * @param name the name
    */
   private ClassificationMethodEnum(final String name) {
      this.name = name;
   }

   /**
    * Parses the string to find the ClassificationMethodEnum.
    * 
    * @param string the string
    * 
    * @return the classification method enum
    */
   public static ClassificationMethodEnum parse(final String string) {
      if (!Utility.isEmpty(string)) {
         if ("snp".startsWith(string)) { //$NON-NLS-1$
            if (SNPQA.toString().equals(string)) {
               return SNPQA;
            } else if (SNPLOCAL.toString().equals(string)) {
               return SNPLOCAL;
            } else if (SNP.toString().equals(string)) {
               return SNP;
            }
         } else if ("bool".startsWith(string)) { //$NON-NLS-1$
            if (BOOLSIMPLE.toString().equals(string)) {
               return BOOLSIMPLE;
            } else if (BOOLCOMPLEX.toString().equals(string)) {
               return BOOLCOMPLEX;
            }
         } else if (CONVENTIONAL.toString().equals(string)) {
            return CONVENTIONAL;
         }
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
