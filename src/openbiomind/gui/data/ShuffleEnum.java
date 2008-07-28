/**
 * ShuffleEnum.java
 *
 * The file ShuffleEnum.java.
 *
 * $Id$
 */
package openbiomind.gui.data;

/**
 * The enum ShuffleEnum.
 *
 * @author bsanghvi
 * @since Jul 15, 2008
 * @version Jul 27, 2008
 */
public enum ShuffleEnum {

   /** The <code>on</code> value. */
   ON("on"), //$NON-NLS-1$

   /** The <code>off</code> value. */
   OFF("off"), //$NON-NLS-1$

   ; // End of allowed values

   /** The name. */
   private String name = null;

   /**
    * Instantiates a new Shuffle enum.
    *
    * @param name the name
    */
   private ShuffleEnum(final String name) {
      this.name = name;
   }

   /**
    * Parses the string to find the ShuffleEnum.
    *
    * @param string the string
    *
    * @return the shuffle enum
    */
   public static ShuffleEnum parse(final String string) {
      if (ON.toString().equals(string)) {
         return ON;
      } else if (OFF.toString().equals(string)) {
         return OFF;
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
