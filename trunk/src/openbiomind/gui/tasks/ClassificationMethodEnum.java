/**
 * ClassificationMethodEnum.java
 *
 * The file ClassificationMethodEnum.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

import openbiomind.gui.util.Utility;

/**
 * The enum ClassificationMethodEnum.
 *
 * @author bsanghvi
 * @since Jul 15, 2008
 * @version Jul 16, 2008
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

   ; // End of allowed values

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
