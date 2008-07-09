/**
 * TaskProcessBuider.java
 *
 * The file TaskProcessBuider.java.
 *
 * $Id$
 */
package openbiomind.gui.main;

import static openbiomind.gui.util.Constants.EMPTY;
import static openbiomind.gui.util.Constants.HYPHEN;
import static openbiomind.gui.util.Constants.SPACE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import openbiomind.gui.console.Console;
import openbiomind.gui.preferences.Preference;
import openbiomind.gui.tasks.AbstractTaskData;

/**
 * The class TaskProcessBuider.
 *
 * @author bsanghvi
 * @since Jun 12, 2008
 * @version Jun 12, 2008
 */
public class TaskProcessBuider {

   /** The <code>java</code> command for executing Java programs. */
   public static final String JAVA_COMMAND = "java"; //$NON-NLS-1$

   /** Argument <code>-cp</code> for specifying the classpath. */
   public static final String JAVA_ARGUMENT_CLASSPATH = HYPHEN + "cp"; //$NON-NLS-1$

   /** The abstract task data. */
   private AbstractTaskData abstractTaskData = null;

   /** The process builder. */
   private ProcessBuilder processBuilder = null;

   /**
    * The Constructor.
    *
    * @param abstractTaskData the abstract task data
    */
   public TaskProcessBuider(final AbstractTaskData abstractTaskData) {
      this.abstractTaskData = abstractTaskData;
      buildProcess();
   }

   /**
    * Start the process.
    *
    * @return the process
    *
    * @throws IOException Signals that an I/O exception has occurred.
    *
    * @see java.lang.ProcessBuilder#start()
    */
   public Process start() throws IOException {
      return this.processBuilder.start();
   }

   /**
    * Builds the process.
    */
   private void buildProcess() {
      final List<String> commandList = new ArrayList<String>();
      commandList.add(JAVA_COMMAND);
      commandList.add(JAVA_ARGUMENT_CLASSPATH);
      commandList.add(getClasspath());
      commandList.add(this.abstractTaskData.getTaskName());
      commandList.addAll(this.abstractTaskData.getAllArgumentsAsList());

      this.processBuilder = new ProcessBuilder(commandList);
      this.processBuilder.redirectErrorStream(true);

      logCommand(this.processBuilder.command());
   }

   /**
    * Log command.
    *
    * @param commandList the command list
    */
   private void logCommand(final List<String> commandList) {
      final StringBuilder commandStringBuilder = new StringBuilder(EMPTY);
      for (final String commandString : commandList) {
         commandStringBuilder.append(commandString + SPACE);
      }
      Console.debug(commandStringBuilder.toString());
   }

   /**
    * Gets the classpath.
    *
    * @return the classpath
    */
   private String getClasspath() {
      return Preference.getOpenBiomindJarLocation() + File.pathSeparator + Preference.getPipelinePropertiesHome();
   }

}
