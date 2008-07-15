/**
 * MetaTaskShufflingEnum.java
 *
 * The file MetaTaskShufflingEnum.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

/**
 * The enum MetaTaskShufflingEnum.
 *
 * @author bsanghvi
 * @since Jul 15, 2008
 * @version Jul 15, 2008
 */
public enum MetaTaskShufflingEnum {

   /** The <code>on</code> value. */
   ON("on"), //$NON-NLS-1$

   /** The <code>off</code> value. */
   OFF("off"), //$NON-NLS-1$

   /** The name. */
   ; // End of allowed values

   /** The name. */
   private String name = null;

   /**
    * Instantiates a new MetaTask Shuffling enum.
    *
    * @param name the name
    */
   private MetaTaskShufflingEnum(final String name) {
      this.name = name;
   }

   /**
    * Parses the string to find the MetaTaskShufflingEnum.
    *
    * @param string the string
    *
    * @return the MetaTask Shuffling enum
    */
   public static MetaTaskShufflingEnum parse(final String string) {
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
