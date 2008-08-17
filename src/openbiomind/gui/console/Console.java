/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (Console.java) is part of OpenBiomind-GUI.
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

package openbiomind.gui.console;

import java.io.PrintStream;

import openbiomind.gui.common.Constants;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * The class Console.
 * 
 * @author bsanghvi
 * @since Jun 8, 2008
 * @version Aug 18, 2008
 */
public class Console implements Constants {

   /** The console. */
   private static MessageConsole console = new MessageConsole(Constants.EMPTY, null);

   /** The debug. */
   private static boolean debug = true;

   /**
    * Inits the Console.
    */
   public static void init() {
      ConsolePlugin.getDefault().getConsoleManager().showConsoleView(Console.console);
      ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { Console.console });
      initSystemOut();
      initSystemErr();
      setDebug(true);
   }

   /**
    * Inits the system out.
    */
   private static void initSystemOut() {
      final MessageConsoleStream stream = Console.console.newMessageStream();
      stream.setColor(Colors.SYSTEM_OUT);
      System.setOut(new PrintStream(stream));
   }

   /**
    * Inits the system err.
    */
   private static void initSystemErr() {
      final MessageConsoleStream stream = Console.console.newMessageStream();
      stream.setColor(Colors.SYSTEM_ERR);
      System.setErr(new PrintStream(stream));
   }

   /**
    * Write.
    * 
    * @param message the message
    * @param color the color
    */
   public static void write(final String message, final Color color) {
      Display.getDefault().asyncExec(new Runnable() {

         @Override
         public void run() {
            final MessageConsoleStream stream = Console.console.newMessageStream();
            stream.setColor(color);
            stream.println(message);
         }

      });
   }

   /**
    * Write.
    * 
    * @param e the e
    * @param color the color
    */
   private static void write(final Throwable e, final Color color) {
      Display.getDefault().asyncExec(new Runnable() {

         @Override
         public void run() {
            final MessageConsoleStream stream = Console.console.newMessageStream();
            stream.setColor(color);
            e.printStackTrace(new PrintStream(stream));
         }

      });
   }

   /**
    * Output.
    * 
    * @param message the message
    */
   public static void output(final String message) {
      write(message, Colors.OUTPUT);
   }

   /**
    * Error.
    * 
    * @param message the message
    */
   public static void error(final String message) {
      write(message, Colors.ERROR);
   }

   /**
    * Error.
    * 
    * @param e the e
    */
   public static void error(final Throwable e) {
      write(e, Colors.ERROR);
   }

   /**
    * Error.
    * 
    * @param message the message
    * @param e the e
    */
   public static void error(final String message, final Throwable e) {
      write(message, Colors.ERROR);
      write(e, Colors.ERROR);
   }

   /**
    * Warn.
    * 
    * @param message the message
    */
   public static void warn(final String message) {
      write(message, Colors.WARN);
   }

   /**
    * Info.
    * 
    * @param message the message
    */
   public static void info(final String message) {
      write(message, Colors.INFO);
   }

   /**
    * Debug.
    * 
    * @param message the message
    */
   public static void debug(final String message) {
      if (isDebug()) {
         write(message, Colors.DEBUG);
      }
   }

   /**
    * Debug.
    * 
    * @param e the e
    */
   public static void debug(final Throwable e) {
      if (isDebug()) {
         write(e, Colors.DEBUG);
      }
   }

   /**
    * Checks if is debug.
    * 
    * @return the debug
    */
   private static boolean isDebug() {
      return Console.debug;
   }

   /**
    * Sets the debug.
    * 
    * @param debug the debug to set
    */
   private static void setDebug(final boolean debug) {
      Console.debug = debug;
   }

}
