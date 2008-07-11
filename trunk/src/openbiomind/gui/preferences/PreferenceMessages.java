/**
 * PreferenceMessages.java
 *
 * The file PreferenceMessages.java.
 *
 * $Id$
 */
package openbiomind.gui.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * The class PreferenceMessages.
 *
 * @author bsanghvi
 * @since Jul 2, 2008
 * @version Jul 2, 2008
 */
public class PreferenceMessages extends NLS {

   /** The Constant BUNDLE_NAME. */
   private static final String BUNDLE_NAME = "openbiomind.gui.preferences.preference_messages"; //$NON-NLS-1$

   /** The Description. */
   public static String Description;

   /** The Error when OpenBiomind JAR is not set. */
   public static String Error_OpenBiomindJAR;

   /** The Error when pipeline.properties is not set. */
   public static String Error_PipelineProperties;

   /*
    * Initialize the messages
    */
   static {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, PreferenceMessages.class);
   }

   /**
    * Instantiates a new messages.
    */
   private PreferenceMessages() {
      // empty constructor
   }

}