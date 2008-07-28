/**
 * Argument.java
 *
 * The file Argument.java.
 *
 * $Id$
 */
package openbiomind.gui.common;

/**
 * The class Argument.
 *
 * @author bsanghvi
 * @since Jul 26, 2008
 * @version Jul 27, 2008
 */
public class Argument {

   /** The id. */
   private String id = null;

   /** The argument. */
   private String argument = null;

   /** The friendly name. */
   private String friendlyName = null;

   /**
    * Instantiates a new argument.
    *
    * @param id the id
    */
   public Argument(final String id) {
      this(id, id);
   }

   /**
    * Instantiates a new argument.
    *
    * @param id the id
    * @param friendlyName the friendly name
    */
   public Argument(final String id, final String friendlyName) {
      this(id, friendlyName, Constants.HYPHEN + id);
   }

   /**
    * Instantiates a new argument.
    *
    * @param id the id
    * @param friendlyName the friendly name
    * @param argument the argument
    */
   public Argument(final String id, final String friendlyName, final String argument) {
      this.id = id;
      this.friendlyName = friendlyName;
      this.argument = argument;
   }

   /**
    * Gets the id.
    *
    * @return the id
    */
   public String getId() {
      return this.id;
   }

   /**
    * Sets the id.
    *
    * @param id the new id
    */
   protected void setId(final String id) {
      this.id = id;
   }

   /**
    * Gets the argument.
    *
    * @return the argument
    */
   public String argument() {
      return getArgument();
   }

   /**
    * Gets the argument.
    *
    * @return the argument
    */
   public String getArgument() {
      return this.argument;
   }

   /**
    * Sets the argument.
    *
    * @param argument the new argument
    */
   protected void setArgument(final String argument) {
      this.argument = argument;
   }

   /**
    * Gets the friendly name.
    *
    * @return the friendly name
    */
   public String friendlyName() {
      return getFriendlyName();
   }

   /**
    * Gets the friendly name.
    *
    * @return the friendly name
    */
   public String getFriendlyName() {
      return this.friendlyName;
   }

   /**
    * Sets the friendly name.
    *
    * @param friendlyName the new friendly name
    */
   protected void setFriendlyName(final String friendlyName) {
      this.friendlyName = friendlyName;
   }

   /*
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      return getId();
   }

}
