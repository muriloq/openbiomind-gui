/**
 * TaskProcessBuider.java
 *
 * The file TaskProcessBuider.java.
 *
 * $Id$
 */
package openbiomind.gui.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import openbiomind.gui.console.Console;
import openbiomind.gui.preferences.Preference;
import openbiomind.gui.tasks.AbstractTaskData;
import openbiomind.gui.util.Constants;

/**
 * The class TaskProcessBuider.
 *
 * @author bsanghvi
 * @since Jun 12, 2008
 * @version Jul 24, 2008
 */
public class TaskProcessBuider implements Constants {

   /** The <code>java</code> command for executing Java programs. */
   private static final String JAVA_COMMAND = "java"; //$NON-NLS-1$

   /** Argument <code>-cp</code> for specifying the classpath. */
   private static final String JAVA_ARGUMENT_CLASSPATH = HYPHEN + "cp"; //$NON-NLS-1$

   /** Argument <code>-Xmx256m</code> for memory intensive commands. */
   private static final String JAVA_Xmx = "-Xmx256m"; //$NON-NLS-1$

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
      if (this.abstractTaskData.isMemoryIntensive()) {
         commandList.add(JAVA_Xmx);
      }
      commandList.add(JAVA_ARGUMENT_CLASSPATH);
      commandList.add(getClasspath());
      commandList.add(this.abstractTaskData.getTaskName());
      commandList.addAll(this.abstractTaskData.getAllArgumentsAsList());

      this.processBuilder = new ProcessBuilder(commandList);

      logList(this.processBuilder.command());
   }

   /**
    * Log command list.
    *
    * @param list the command list
    */
   private void logList(final List<String> list) {
      final StringBuilder stringBuilder = new StringBuilder(EMPTY);
      for (final String string : list) {
         stringBuilder.append(string + SPACE);
      }
      Console.debug(stringBuilder.toString());
   }

   /**
    * Gets the classpath.
    *
    * @return the classpath
    */
   private String getClasspath() {
      return Preference.getOpenBiomindJarPath() + File.pathSeparator + Preference.getPipelinePropertiesHome();
   }

}
