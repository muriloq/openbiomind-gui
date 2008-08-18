/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (Preference.java) is part of OpenBiomind-GUI.
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

import openbiomind.gui.Activator;
import openbiomind.gui.common.Constants;
import openbiomind.gui.main.GraphvizHelper;
import openbiomind.gui.util.Utility;

/**
 * The class Preference methods to access the plug-in preferences. It also contains constant definitions.
 * 
 * @author bsanghvi
 * @since Jun 9, 2008
 * @version Aug 18, 2008
 */
public class Preference implements Constants {

   /** The constant for OpenBiomind JAR. */
   public static final String OPENBIOMIND_JAR = "openbiomind.gui.preferences.OpenBiomindJar"; //$NON-NLS-1$

   /** The constant for pipeline.properties. */
   public static final String PIPELINE_PROPERTIES = "openbiomind.gui.preferences.PipelineProperties"; //$NON-NLS-1$

   /** The constant for Graphviz dot utility. */
   public static final String GRAPHVIZ_DOT_UTILITY = "openbiomind.gui.preferences.GraphvizDotUtility"; //$NON-NLS-1$

   /**
    * Checks if all the set preferences are valid?
    * 
    * @return true, if the set preferences are valid
    */
   public static boolean isRequiredPreferenceSet() {
      return (isOpenBiomindJarPreferenceValid() && isPipelinePropertiesPreferenceValid());
   }

   /**
    * Gets the OpenBiomind jar path.
    * 
    * @return the OpenBiomind jar path
    */
   public static String getOpenBiomindJarPath() {
      return Activator.getDefault().getPreferenceStore().getString(Preference.OPENBIOMIND_JAR);
   }

   /**
    * Checks if OpenBiomind jar preference is valid?
    * 
    * @return true, if OpenBiomind jar preference is valid
    */
   public static boolean isOpenBiomindJarPreferenceValid() {
      return isOpenBiomindJarPreferenceValid(getOpenBiomindJarPath());
   }

   /**
    * Checks if OpenBiomind jar preference is valid for the given value?
    * 
    * @param value the value
    * 
    * @return true, if OpenBiomind jar preference is valid
    */
   public static boolean isOpenBiomindJarPreferenceValid(final String value) {
      return (value != null && value.trim().toLowerCase().endsWith(Resources.JAR_EXTENSION) && Utility
            .fileExists(value));
   }

   /**
    * Gets the pipeline properties path.
    * 
    * @return the pipeline properties path
    */
   public static String getPipelinePropertiesPath() {
      return Activator.getDefault().getPreferenceStore().getString(Preference.PIPELINE_PROPERTIES);
   }

   /**
    * Gets the pipeline properties home.
    * 
    * @return the pipeline properties home
    */
   public static String getPipelinePropertiesHome() {
      final String pipelinePropertiesPath = getPipelinePropertiesPath();
      return pipelinePropertiesPath.substring(0, pipelinePropertiesPath
            .lastIndexOf(Resources.PIPELINE_PROPERTIES_FILENAME));
   }

   /**
    * Checks if pipeline.properties preference is valid?
    * 
    * @return true, if pipeline.properties preference is valid
    */
   public static boolean isPipelinePropertiesPreferenceValid() {
      return isPipelinePropertiesPreferenceValid(getPipelinePropertiesPath());
   }

   /**
    * Checks if pipeline.properties preference is valid for the given value?
    * 
    * @param value the value
    * 
    * @return true, if pipeline.properties preference is valid
    */
   public static boolean isPipelinePropertiesPreferenceValid(final String value) {
      return (value != null && value.trim().toLowerCase().endsWith(Resources.PIPELINE_PROPERTIES_FILENAME) && Utility
            .fileExists(value));
   }

   /**
    * Gets the Graphviz dot utility path.
    * 
    * @return the Graphviz dot utility path
    */
   public static String getGraphvizDotUtilityPath() {
      return Activator.getDefault().getPreferenceStore().getString(Preference.GRAPHVIZ_DOT_UTILITY);
   }

   /**
    * Checks if the Graphviz dot utility preference is set?
    * 
    * @return true, if Graphviz dot utility preference is set
    */
   public static boolean isGraphvizDotUtilityPreferenceSet() {
      final String value = getGraphvizDotUtilityPath();
      return (!Utility.isEmpty(value) && isGraphvizDotUtilityPreferenceValid(value));
   }

   /**
    * Checks if the Graphviz dot utility preference is valid?
    * 
    * @return true, if Graphviz dot utility preference is valid
    */
   public static boolean isGraphvizDotUtilityPreferenceValid() {
      return isGraphvizDotUtilityPreferenceValid(getGraphvizDotUtilityPath());
   }

   /**
    * Checks if the Graphviz dot utility preference is valid for the given value?
    * 
    * @param value the value
    * 
    * @return true, if Graphviz dot utility preference is valid
    */
   public static boolean isGraphvizDotUtilityPreferenceValid(final String value) {
      return (Utility.isEmpty(value) || (Utility.fileExists(value)
            && Resources.GRAPHVIZ_DOT_UTILITY_NAME.equals(Utility.extractFileName(value)) && GraphvizHelper
            .validateDotCommand(value)));
   }

}
