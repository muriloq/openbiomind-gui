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

package openbiomind.gui.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * This class contains messages used in the openbiomind.gui.preferences package.
 * 
 * @author bsanghvi
 * @since Jul 2, 2008
 * @version Aug 18, 2008
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
