/**
 * Console.java
 *
 * The file Console.java.
 *
 * $Id$
 */
package openbiomind.gui.console;

import static openbiomind.gui.util.Constants.COLOR_DEBUG;
import static openbiomind.gui.util.Constants.COLOR_ERROR;
import static openbiomind.gui.util.Constants.COLOR_INFO;
import static openbiomind.gui.util.Constants.COLOR_OUTPUT;
import static openbiomind.gui.util.Constants.COLOR_SYSTEM_ERR;
import static openbiomind.gui.util.Constants.COLOR_SYSTEM_OUT;
import static openbiomind.gui.util.Constants.COLOR_WARN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import openbiomind.gui.util.Messages;

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
 * @version Jun 8, 2008
 */
public class Console {

   /** The console. */
   private static MessageConsole console = new MessageConsole(Messages.ConsoleTitle, null);

   /** The debug. */
   private static boolean debug = true;

   /**
    * Inits the Console.
    */
   public static void init() {
      ConsolePlugin.getDefault().getConsoleManager().showConsoleView(Console.console);
      ConsolePlugin.getDefault().getConsoleManager()
            .addConsoles(new IConsole[] { Console.console });
      initSystemOut();
      initSystemErr();
      setDebug(true);
   }

   /**
    * Inits the system out.
    */
   private static void initSystemOut() {
      final MessageConsoleStream stream = Console.console.newMessageStream();
      stream.setColor(COLOR_SYSTEM_OUT);
      System.setOut(new PrintStream(stream));
   }

   /**
    * Inits the system err.
    */
   private static void initSystemErr() {
      final MessageConsoleStream stream = Console.console.newMessageStream();
      stream.setColor(COLOR_SYSTEM_ERR);
      System.setErr(new PrintStream(stream));
   }

   /**
    * Write.
    *
    * @param message the message
    * @param color the color
    */
   public synchronized static void write(final String message, final Color color) {
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
    * @param inputStream the input stream
    * @param color the color
    */
   public synchronized static void write(final InputStream inputStream, final Color color) {
      Display.getDefault().asyncExec(new Runnable() {

         /*
          * @see java.lang.Runnable#run()
          */
         public void run() {
            final MessageConsoleStream stream = Console.console.newMessageStream();
            stream.setColor(color);

            final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String message;

            try {
               while ((message = br.readLine()) != null) {
                  stream.println(message);
               }
            } catch (IOException e) {
               error(e);
            }
         }

      });
   }

   /**
    * Write.
    *
    * @param e the e
    * @param color the color
    */
   private synchronized static void write(final Throwable e, final Color color) {
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
   public synchronized static void output(final String message) {
      write(message, COLOR_OUTPUT);
   }

   /**
    * Error.
    *
    * @param message the message
    */
   public synchronized static void error(final String message) {
      write(message, COLOR_ERROR);
   }

   /**
    * Error.
    *
    * @param inputStream the input stream
    */
   public synchronized static void error(final InputStream inputStream) {
      write(inputStream, COLOR_ERROR);
   }

   /**
    * Error.
    *
    * @param e the e
    */
   public synchronized static void error(final Throwable e) {
      write(e, COLOR_ERROR);
   }

   /**
    * Warn.
    *
    * @param message the message
    */
   public synchronized static void warn(final String message) {
      write(message, COLOR_WARN);
   }

   /**
    * Info.
    *
    * @param message the message
    */
   public synchronized static void info(final String message) {
      write(message, COLOR_INFO);
   }

   /**
    * Info.
    *
    * @param inputStream the input stream
    */
   public synchronized static void info(final InputStream inputStream) {
      write(inputStream, COLOR_INFO);
   }

   /**
    * Debug.
    *
    * @param message the message
    */
   public synchronized static void debug(final String message) {
      if (isDebug()) {
         write(message, COLOR_DEBUG);
      }
   }

   /**
    * Debug.
    *
    * @param e the e
    */
   public synchronized static void debug(final Throwable e) {
      if (isDebug()) {
         write(e, COLOR_DEBUG);
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
