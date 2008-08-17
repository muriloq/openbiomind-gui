/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (Messages.java) is part of OpenBiomind-GUI.
 *
 *  OpenBiomind-GUI is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenBiomind-GUI is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenBiomind-GUI.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Please visit the following pages to contact the author(s):
 *  Homepage: http://code.google.com/p/openbiomind-gui/
 *  Mailing list: http://groups.google.com/group/openbiomind-gui/
 */

package openbiomind.gui.data;

import org.eclipse.osgi.util.NLS;

/**
 * This class contains messages used in the openbiomind.gui.data package.
 * 
 * @author bsanghvi
 * @since Jul 27, 2008
 * @version Aug 18, 2008
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
