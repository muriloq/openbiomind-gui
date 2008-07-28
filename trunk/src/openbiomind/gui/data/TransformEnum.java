/**
 * TransformEnum.java
 *
 * The file TransformEnum.java.
 *
 * $Id$
 */
package openbiomind.gui.data;

/**
 * The enum TransformEnum.
 *
 * @author bsanghvi
 * @since Jul 15, 2008
 * @version Jul 27, 2008
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

   ; // End of allowed values

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
