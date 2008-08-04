/**
 * Messages.java
 *
 * This class contains messages used in the openbiomind.gui.data package.
 *
 * $Id$
 */
package openbiomind.gui.data;

import org.eclipse.osgi.util.NLS;

/**
 * This class contains messages used in the openbiomind.gui.data package.
 *
 * @author bsanghvi
 * @since Jul 27, 2008
 * @version Aug 3, 2008
 */
class Messages extends NLS {

   /** The constant bundle name. */
   private static final String BUNDLE_NAME = "openbiomind.gui.data.messages"; //$NON-NLS-1$

   /** metatask_result */
   public static String ClustTrans_ArgP_FName;

   /** test_dataset */
   public static String CompPipe_ArgDT_FName;

   /** property_file */
   public static String CompPipe_ArgP_FName;

   /** source_dataset */
   public static String ConstArg_ArgD_FName;

   /** output_dataset */
   public static String ConstArg_ArgO_FName;

   /** ontology_association */
   public static String ConstArg_ArgOntAssoFile_FName;

   /** ontology_description */
   public static String ConstArg_ArgOntDescFile_FName;

   /** test_dataset */
   public static String DataTrans_ArgTData_FName;

   /** enhanced_dataset */
   public static String EnhanData_ArgE_FName;

   /** input */
   public static String Folder_In;

   /** output */
   public static String Folder_Out;

   /** horizontal_dataset */
   public static String GraphFeatures_ArgH_FName;

   /** mobra_result */
   public static String GraphFeatures_ArgM_FName;

   /** utility */
   public static String GraphFeatures_ArgU_FName;

   /** metatask_result */
   public static String UtilComp_ArgR_FName;

   /** clustering_result */
   public static String ViewClust_ArgR_FName;

   /*
    * Initialize the resource bundle
    */
   static {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Messages.class);
   }

   /**
    * Instantiates new messages.
    */
   private Messages() {
      // left empty
   }

}
