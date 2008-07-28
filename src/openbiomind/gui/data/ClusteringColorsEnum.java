/**
 * ClusteringColorsEnum.java
 *
 * The file ClusteringColorsEnum.java.
 *
 * $Id$
 */
package openbiomind.gui.data;

/**
 * The enum ClusteringColorsEnum.
 *
 * @author bsanghvi
 * @since Jul 20, 2008
 * @version Jul 27, 2008
 */
public enum ClusteringColorsEnum {

   /** The <code>traditional</code> clustering color. */
   TRADITIONAL("traditional"), //$NON-NLS-1$

   /** The <code>mono</code> clustering color. */
   MONO("mono"), //$NON-NLS-1$

   ; // End of allowed values

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
