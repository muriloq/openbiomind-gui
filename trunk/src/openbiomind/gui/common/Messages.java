/**
 * Messages.java
 *
 * This class contains messages used in the openbiomind.gui.common package.
 *
 * $Id$
 */
package openbiomind.gui.common;

import org.eclipse.osgi.util.NLS;

/**
 * This class contains messages used in the openbiomind.gui.common package.
 *
 * @author bsanghvi
 * @since Jul 27, 2008
 * @version Jul 28, 2008
 */
class Messages extends NLS {

   /** The constant bundle name. */
   private static final String BUNDLE_NAME = "openbiomind.gui.common.messages"; //$NON-NLS-1$

   /** The friendly name for ConstantArguments.ARG_D. */
   public static String ConstArg_ArgD_FName;

   /** The friendly name for ConstantArguments.ARG_O. */
   public static String ConstArg_ArgO_FName;

   /** The friendly name for ConstantArguments.ARG_ONTOLOGY_ASSOCIATION_FILE. */
   public static String ConstArg_ArgOntAssoFile_FName;

   /** The friendly name for ConstantArguments.ARG_ONTOLOGY_DESCRIPTION_FILE. */
   public static String ConstArg_ArgOntDescFile_FName;

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
