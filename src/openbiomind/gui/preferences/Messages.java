/**
 * Messages.java
 *
 * This class contains messages used in the openbiomind.gui.preferences package.
 *
 * $Id$
 */
package openbiomind.gui.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * This class contains messages used in the openbiomind.gui.preferences package.
 *
 * @author bsanghvi
 * @since Jul 2, 2008
 * @version Jul 28, 2008
 */
class Messages extends NLS {

   /** The constant bundle name. */
   private static final String BUNDLE_NAME = "openbiomind.gui.preferences.messages"; //$NON-NLS-1$

   /** The OpenBiomindPrefPage_Desc. */
   public static String OpenBiomindPrefPage_Desc;

   /** The Error when Graphviz dot utility is not specified. */
   public static String OpenBiomindPrefPage_Err_GraphvizDotUtil;

   /** The Error when OpenBiomind JAR is not set. */
   public static String OpenBiomindPrefPage_Err_OpenBiomindJAR;

   /** The Error when pipeline.properties is not set. */
   public static String OpenBiomindPrefPage_Err_PipelineProp;

   /** The Label for Graphviz dot utility. */
   public static String OpenBiomindPrefPage_Label_GraphvizDotUtil;

   /** The Label for OpenBiomind jar. */
   public static String OpenBiomindPrefPage_Label_OpenBiomindJar;

   /** The Label for pipeline.properties. */
   public static String OpenBiomindPrefPage_Label_PipelineProp;

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
