/**
 * GraphvizHelper.java
 *
 * The file GraphvizHelper.java.
 *
 * $Id$
 */
package openbiomind.gui.main;

import java.util.ArrayList;
import java.util.List;

import openbiomind.gui.preferences.Preference;
import openbiomind.gui.util.Constants;

/**
 * The class GraphvizHelper.
 *
 * @author bsanghvi
 * @since Jul 25, 2008
 * @version Jul 25, 2008
 */
public class GraphvizHelper {

   /** The argument <code>-t</code> for specifying the output format. */
   private static final String ARG_T = Constants.HYPHEN + "T"; //$NON-NLS-1$

   /** The argument <code>-o</code> for specifying the output file path. */
   private static final String ARG_O = Constants.HYPHEN + "o"; //$NON-NLS-1$

   /**
    * Creates the dot command.
    *
    * @param sourceDotPath the source dot path
    * @param outputFilePath the output file path
    * @param outputFormat the output format can be used to specify image type
    *
    * @return the list< string>
    */
   public static List<String> createDotCommand(final String sourceDotPath, final String outputFilePath,
         final String outputFormat) {
      if (Preference.isGraphvizDotUtilityPreferenceSet()) {
         final List<String> commandList = new ArrayList<String>();
         commandList.add(Preference.getGraphvizDotUtilityPath());
         commandList.add(ARG_T + outputFormat);
         commandList.add(sourceDotPath);
         commandList.add(ARG_O + outputFilePath);
         return commandList;
      }

      return null;
   }

}
