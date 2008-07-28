/**
 * Console.java
 *
 * The file Console.java.
 *
 * $Id$
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
 * @version Jul 27, 2008
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

         /*
          * @see java.lang.Runnable#run()
          */
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

         /*
          * @see java.lang.Runnable#run()
          */
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
