/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (TaskProcessBuider.java) is part of OpenBiomind-GUI.
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

package openbiomind.gui.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import openbiomind.gui.common.Constants;
import openbiomind.gui.console.Console;
import openbiomind.gui.data.AbstractTaskData;
import openbiomind.gui.preferences.Preference;

/**
 * The class TaskProcessBuider.
 * 
 * @author bsanghvi
 * @since Jun 12, 2008
 * @version Aug 18, 2008
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
      commandList.addAll(this.abstractTaskData.toList());

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
