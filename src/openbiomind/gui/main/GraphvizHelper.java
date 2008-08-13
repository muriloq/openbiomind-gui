/**
 * GraphvizHelper.java
 *
 * The file GraphvizHelper.java.
 *
 * $Id$
 */
package openbiomind.gui.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import openbiomind.gui.common.Constants;
import openbiomind.gui.console.Console;
import openbiomind.gui.preferences.Preference;
import openbiomind.gui.util.Utility;

/**
 * The class GraphvizHelper.
 *
 * @author bsanghvi
 * @since Jul 25, 2008
 * @version Aug 13, 2008
 */
public class GraphvizHelper {

   /** The argument <code>-t</code> for specifying the output format. */
   private static final String ARG_T = Constants.HYPHEN + "T"; //$NON-NLS-1$

   /** The argument <code>-o</code> for specifying the output file path. */
   private static final String ARG_O = Constants.HYPHEN + "o"; //$NON-NLS-1$

   /** The argument <code>-V</code> for reading version information and verifying the dot utility. */
   private static final String ARG_V = Constants.HYPHEN + "V"; //$NON-NLS-1$

   /** If a dot command is valid, then the first line of its output on execution with -V option must start with this. */
   private static final String DOT_GRAPHVIZ_MATCH_KEY = "dot - Graphviz"; //$NON-NLS-1$

   /** The output formal of the image is {@link openbiomind.gui.common.Constants.Resources#PNG_FORMAT}. */
   private static final String OUTPUT_FORMAT = Constants.Resources.PNG_FORMAT;

   /** The source file path. */
   private String sourceFilePath = null;

   /** The output file path. */
   private String outputFilePath = null;

   /**
    * Instantiates a new Graphviz helper.
    *
    * @param sourceFilePath the source file path
    */
   public GraphvizHelper(final String sourceFilePath) {
      this.sourceFilePath = sourceFilePath;
      createOutputFilePath();
   }

   /**
    * Creates the dot process.
    *
    * @return the process
    */
   public Process createDotProcess() {
      if (Preference.isGraphvizDotUtilityPreferenceSet()) {
         if (!Utility.isEmpty(getOutputFilePath())) {
            final List<String> commandList = new ArrayList<String>();
            commandList.add(Preference.getGraphvizDotUtilityPath());
            commandList.add(ARG_T + OUTPUT_FORMAT);
            commandList.add(getSourceFilePath());
            commandList.add(ARG_O + getOutputFilePath());

            try {
               return (new ProcessBuilder(commandList)).start();
            } catch (final IOException e) {
               Console.debug(e);
            }
         }
      } else {
         Console.info(Messages.Err_GraphvizDotUtility);
      }

      return null;
   }

   /**
    * Gets the output file path.
    *
    * @return the output file path
    */
   public String getOutputFilePath() {
      return this.outputFilePath;
   }

   /**
    * Creates the output file path.
    */
   private void createOutputFilePath() {
      try {
         this.outputFilePath = File.createTempFile(
               Utility.extractFileName(getSourceFilePath()) + Constants.DOT + OUTPUT_FORMAT + Constants.DOT,
               Constants.EMPTY).getAbsolutePath();
      } catch (final IOException e) {
         Console.debug(e);
      }
   }

   /**
    * Gets the source file path.
    *
    * @return the source file path
    */
   private String getSourceFilePath() {
      return this.sourceFilePath;
   }

   /**
    * Validate dot command.
    *
    * @param command the command
    *
    * @return true, if successful
    */
   public static boolean validateDotCommand(final String command) {
      // prepare process
      final List<String> commandList = new ArrayList<String>();
      commandList.add(command);
      commandList.add(ARG_V);
      final ProcessBuilder processBuilder = new ProcessBuilder(commandList);
      processBuilder.redirectErrorStream(true);
      Process process = null;
      try {
         process = processBuilder.start();
      } catch (final IOException e) {
         Console.debug(e);
      }

      // verify the first line of the output of the execution
      boolean valid = false;
      if (process != null) {
         final Scanner reader = new Scanner(new BufferedReader(new InputStreamReader(process.getInputStream())));
         if (reader.hasNextLine()) {
            final String message = reader.nextLine();
            valid = (!Utility.isEmpty(message) && message.startsWith(DOT_GRAPHVIZ_MATCH_KEY));
         }
         process.destroy();
      }
      return valid;
   }

}
