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
 * @version Jul 28, 2008
 */
class Messages extends NLS {

   /** The constant bundle name. */
   private static final String BUNDLE_NAME = "openbiomind.gui.data.messages"; //$NON-NLS-1$

   /** The friendly name for ClusteringTransformerTaskData.ARG_P. */
   public static String ClustTrans_ArgP_FName;

   /** The friendly name for DatasetTransformerTaskData.ARG_TEST_DATASET. */
   public static String DataTrans_ArgTData_FName;

   /** The friendly name for EnhanceDatasetTaskData.ARG_E. */
   public static String EnhanData_ArgE_FName;

   /** The friendly name for GraphFeaturesTaskData.ARG_H. */
   public static String GraphFeatures_ArgH_FName;

   /** The friendly name for GraphFeaturesTaskData.ARG_M. */
   public static String GraphFeatures_ArgM_FName;

   /** The friendly name for GraphFeaturesTaskData.ARG_U. */
   public static String GraphFeatures_ArgU_FName;

   /** The friendly name for UtilityComputerTaskData.ARG_R. */
   public static String UtilComp_ArgR_FName;

   /** The friendly name for ViewClustersTaskData.ARG_R. */
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
