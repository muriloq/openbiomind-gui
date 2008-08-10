/**
 * Messages.java
 *
 * This class contains messages used in the openbiomind.gui.main package.
 *
 * $Id$
 */
package openbiomind.gui.main;

import org.eclipse.osgi.util.NLS;

class Messages extends NLS {

   /** The constant bundle name. */
   private static final String BUNDLE_NAME = "openbiomind.gui.main.messages"; //$NON-NLS-1$

   /** Set Graphviz dot utility in Preferences to automatically convert dot files to images */
   public static String Err_GraphvizDotUtility;

   /*
    * Initialize the resource bundle
    */
   static {
      NLS.initializeMessages(BUNDLE_NAME, Messages.class);
   }

   /**
    * Instantiates a new wizard messages.
    */
   private Messages() {
      // left empty
   }

}
