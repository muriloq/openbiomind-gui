/**
 * Messages.java
 *
 * This class contains messages used in the openbiomind.gui.handlers package.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import org.eclipse.osgi.util.NLS;

/**
 * This class contains messages used in the openbiomind.gui.handlers package.
 *
 * @author bsanghvi
 * @since Jul 27, 2008
 * @version Jul 28, 2008
 */
class Messages extends NLS {

   /** The constant bundle name. */
   private static final String BUNDLE_NAME = "openbiomind.gui.handlers.messages"; //$NON-NLS-1$

   /** The Preferences not set error. */
   public static String Err_PrefNotSet;

   /** The Unable to open error. */
   public static String Err_UnableToOpen;

   /*
    * Initialize the resource bundle
    */
   static {
      NLS.initializeMessages(BUNDLE_NAME, Messages.class);
   }

   /**
    * Instantiates new messages.
    */
   private Messages() {
      // left empty
   }

}
