/**
 * FeatureSelectionMethodEnum.java
 *
 * The file FeatureSelectionMethodEnum.java.
 *
 * $Id$
 */
package openbiomind.gui.data;

/**
 * The enum FeatureSelectionMethodEnum.
 *
 * @author bsanghvi
 * @since Jul 15, 2008
 * @version Jul 27, 2008
 */
public enum FeatureSelectionMethodEnum {

   /** The <code>differentiation</code> method. */
   DIFFERENTIATION("differentiation"), //$NON-NLS-1$

   /** The <code>SAM</code> method. */
   SAM("SAM"), //$NON-NLS-1$

   ; // End of allowed values

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
