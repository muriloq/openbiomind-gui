/**
 * Constants.java
 *
 * The file Constants.java.
 *
 * $Id$
 */
package openbiomind.gui.util;

import java.io.File;

import openbiomind.gui.console.Console;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * The final class Constants contains various constant definitions.
 *
 * @author bsanghvi
 * @since Jun 12, 2008
 * @version Jun 12, 2008
 */
public final class Constants {

   /** The default text editor ID. */
   public static final String DEFAULT_TEXT_EDITOR_ID = "org.eclipse.ui.DefaultTextEditor";

   /** The Constant COLOR_SYSTEM_OUT. */
   public static final Color COLOR_SYSTEM_OUT;

   /** The Constant COLOR_SYSTEM_ERR. */
   public static final Color COLOR_SYSTEM_ERR;

   /** The Constant COLOR_ERROR. */
   public static final Color COLOR_OUTPUT;

   /** The Constant COLOR_ERROR. */
   public static final Color COLOR_ERROR;

   /** The Constant COLOR_WARN. */
   public static final Color COLOR_WARN;

   /** The Constant COLOR_INFO. */
   public static final Color COLOR_INFO;

   /** The Constant COLOR_DEBUG. */
   public static final Color COLOR_DEBUG;

   /** The Constant COLOR_TEXT_NORMAL. */
   public static final Color COLOR_TEXT_NORMAL;

   /** The Constant COLOR_TEXT_ERROR. */
   public static final Color COLOR_TEXT_ERROR;

   /** The Constant COLOR_TEXT_WARN. */
   public static final Color COLOR_TEXT_WARN;

   /** The constant for empty string. */
   public static final String EMPTY = ""; //$NON-NLS-1$

   /** The constant for space (value = <i>single blank space</i>). */
   public static final String SPACE = " "; //$NON-NLS-1$

   /** The constant for hyphen (value = <code>-</code>). */
   public static final String HYPHEN = "-"; //$NON-NLS-1$

   /** The constant for quote (value = <code>"</code>). */
   public static final String QUOTE = "\""; //$NON-NLS-1$

   /** The constant for hyphen (value = <code>&</code>). */
   public static final String AMPERSAND = "&"; //$NON-NLS-1$

   /** The constant that separates label from field (value = <code>:</code>). */
   public static final String LABEL_SEPARATOR = ":"; //$NON-NLS-1$

   /** The constant for specifying wild card for anything (value = <code>*</code>). */
   public static final String WILDCARD_ANY = "*"; //$NON-NLS-1$

   /** The Constant specifying the JAR file extension (value = <code>.jar</code>). */
   public static final String JAR_EXTENSION = ".jar"; //$NON-NLS-1$

   /** The constant specifying the label for OpenBiomind JAR file. */
   public static final String OPENBIOMIND_JAR_NAME = "OpenBiomind JAR"; //$NON-NLS-1$

   /**
    * The constant specifying the name of the pipeline properties file (value = <code>pipeline.properties</code>).
    */
   public static final String PIPELINE_PROPERTIES_FILENAME = "pipeline.properties"; //$NON-NLS-1$

   /** The constant to access the current directory. This is calculated at application startup. */
   public static final String CURRENT_DIRECTORY;

   /*
    * Constants from JFace Resources
    */
   /** The Error. */
   public static final String Error = JFaceResources.getString("error"); //$NON-NLS-1$

   /** The Browse. */
   public static final String Browse = JFaceResources.getString("openBrowse"); //$NON-NLS-1$

   /*
    * Static block to initialize various properties
    */
   static {
      /*
       * Define all the colors
       */
      // TODO Update colors based on the default background color
      final Display defaultDisplay = Display.getDefault();
      COLOR_SYSTEM_OUT = defaultDisplay.getSystemColor(SWT.COLOR_BLACK);
      COLOR_SYSTEM_ERR = defaultDisplay.getSystemColor(SWT.COLOR_DARK_RED);
      COLOR_OUTPUT = defaultDisplay.getSystemColor(SWT.COLOR_BLUE);
      COLOR_ERROR = defaultDisplay.getSystemColor(SWT.COLOR_RED);
      COLOR_WARN = defaultDisplay.getSystemColor(SWT.COLOR_DARK_YELLOW);
      COLOR_INFO = defaultDisplay.getSystemColor(SWT.COLOR_GREEN);
      COLOR_DEBUG = defaultDisplay.getSystemColor(SWT.COLOR_GRAY);
      COLOR_TEXT_NORMAL = defaultDisplay.getSystemColor(SWT.COLOR_LIST_BACKGROUND);
      COLOR_TEXT_ERROR = defaultDisplay.getSystemColor(SWT.COLOR_RED);
      COLOR_TEXT_WARN = defaultDisplay.getSystemColor(SWT.COLOR_YELLOW);

      /*
       * Find the current directory
       */
      String currentDirectory = "."; //$NON-NLS-1$
      try {
         currentDirectory = new File(currentDirectory).getCanonicalPath();
      } catch (final Exception e) {
         Console.error(e);
      }
      CURRENT_DIRECTORY = currentDirectory;
   }

}
