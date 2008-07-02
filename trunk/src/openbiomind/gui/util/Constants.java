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

import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * The final class Constants contains various constant definitions.
 *
 * @author bsanghvi
 * @since Jun 12, 2008
 * @version Jun 26, 2008
 */
public interface Constants {

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

   /**
    * The class Resources.
    *
    * @author bsanghvi
    * @since Jun 26, 2008
    * @version Jun 26, 2008
    */
   public final class Resources {

      /** The Error. */
      public static final String ERROR = JFaceResources.getString("error"); //$NON-NLS-1$

      /** The Browse. */
      public static final String BROWSE = JFaceResources.getString("openBrowse"); //$NON-NLS-1$

      /** The Constant specifying the JAR file extension (value = <code>.jar</code>). */
      public static final String JAR_EXTENSION = ".jar"; //$NON-NLS-1$

      /** The constant specifying the label for OpenBiomind JAR file. */
      public static final String OPENBIOMIND_JAR_NAME = "OpenBiomind JAR"; //$NON-NLS-1$

      /**
       * The constant specifying the name of the pipeline properties file (value = <code>pipeline.properties</code>).
       */
      public static final String PIPELINE_PROPERTIES_FILENAME = "pipeline.properties"; //$NON-NLS-1$

   }

   /**
    * The class Properties.
    *
    * @author bsanghvi
    * @since Jun 26, 2008
    * @version Jun 26, 2008
    */
   public final class Properties {

      /** The constant to access the current directory. This is calculated at application startup. */
      public static final String CURRENT_DIRECTORY;

      /** The default text editor ID. */
      public static final String DEFAULT_TEXT_EDITOR_ID = "org.eclipse.ui.DefaultTextEditor"; //$NON-NLS-1$

      /*
       * Static block to initialize various properties
       */
      static {
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

   /**
    * The class Colors.
    *
    * @author bsanghvi
    * @since Jun 26, 2008
    * @version Jun 26, 2008
    */
   public final class Colors {
      /** The Constant COLOR_SYSTEM_OUT. */
      public static final Color SYSTEM_OUT;

      /** The Constant SYSTEM_ERR. */
      public static final Color SYSTEM_ERR;

      /** The Constant ERROR. */
      public static final Color OUTPUT;

      /** The Constant ERROR. */
      public static final Color ERROR;

      /** The Constant WARN. */
      public static final Color WARN;

      /** The Constant INFO. */
      public static final Color INFO;

      /** The Constant DEBUG. */
      public static final Color DEBUG;

      /** The Constant TEXT_NORMAL. */
      public static final Color TEXT_NORMAL;

      /** The Constant TEXT_ERROR. */
      public static final Color TEXT_ERROR;

      /** The Constant TEXT_WARN. */
      public static final Color TEXT_WARN;

      /*
       * Static block to initialize various properties
       */
      static {
         /*
          * Define all the colors
          */
         // TODO Update colors based on the default background color
         final Display defaultDisplay = Display.getDefault();
         SYSTEM_OUT = defaultDisplay.getSystemColor(SWT.COLOR_BLACK);
         SYSTEM_ERR = defaultDisplay.getSystemColor(SWT.COLOR_RED);
         OUTPUT = defaultDisplay.getSystemColor(SWT.COLOR_BLUE);
         ERROR = defaultDisplay.getSystemColor(SWT.COLOR_RED);
         WARN = defaultDisplay.getSystemColor(SWT.COLOR_DARK_YELLOW);
         INFO = defaultDisplay.getSystemColor(SWT.COLOR_GREEN);
         DEBUG = defaultDisplay.getSystemColor(SWT.COLOR_GRAY);
         TEXT_NORMAL = defaultDisplay.getSystemColor(SWT.COLOR_LIST_BACKGROUND);
         TEXT_ERROR = defaultDisplay.getSystemColor(SWT.COLOR_RED);
         TEXT_WARN = defaultDisplay.getSystemColor(SWT.COLOR_YELLOW);
      }

   }

   /**
    * The class GUI.
    */
   public final class GUI {

      /** The Constant MARGIN. */
      public static final int MARGIN = 10;

      /** The Constant SPACING_V. */
      public static final int SPACING_V = 5;

      /** The Constant SPACING_H. */
      public static final int SPACING_H = 15;

      /** The Constant DEFAULT_GLF. */
      public final static GridLayoutFactory DEFAULT_GRID_LAYOUT = GridLayoutFactory.swtDefaults().spacing(SPACING_H,
            SPACING_V).equalWidth(false);

      /** The Constant WIZARD_GROUP_GLF. */
      public final static GridLayoutFactory WIZARD_GROUP_GRID_LAYOUT = GridLayoutFactory.swtDefaults().spacing(
            SPACING_H, SPACING_V).equalWidth(false).margins(MARGIN, MARGIN);

      /** The Constant WIZARD_GROUP_GDF. */
      public final static GridDataFactory WIZARD_GROUP_GRID_DATA = GridDataFactory.swtDefaults().align(SWT.FILL,
            SWT.CENTER).grab(true, false);

      /** The Constant GRID_DATA_INFORMATION_LABEL. */
      public final static GridDataFactory WIZARD_INFORMATION_LABEL_GRID_DATA = GridDataFactory.swtDefaults().align(
            SWT.FILL, SWT.CENTER).grab(true, false);

      /** The Constant GRID_DATA_COMPONENT_LABEL. */
      public final static GridDataFactory WIZARD_COMPONENT_LABEL_GRID_DATA = GridDataFactory.swtDefaults().align(
            SWT.BEGINNING, SWT.CENTER);

      /** The Constant GRID_DATA_SEPARATOR. */
      public final static GridDataFactory WIZARD_SEPARATOR_GRID_DATA = GridDataFactory.swtDefaults().align(SWT.FILL,
            SWT.CENTER).grab(true, false);

      /** The image for required field decoration. */
      public final static Image FIELD_DECORATION_REQUIRED_IMAGE;

      /** The description for required field decoration. */
      public final static String FIELD_DECORATION_REQUIRED_DESCRIPTION;

      /** The image for error field decoration. */
      public final static Image FIELD_DECORATION_ERROR_IMAGE;

      /** The description for error field decoration. */
      public final static String FIELD_DECORATION_ERROR_DESCRIPTION;

      /** The image for warning field decoration. */
      public final static Image FIELD_DECORATION_WARNING_IMAGE = FieldDecorationRegistry.getDefault()
            .getFieldDecoration(FieldDecorationRegistry.DEC_WARNING).getImage();

      /** The image for information field decoration. */
      public final static Image FIELD_DECORATION_INFORMATION_IMAGE = FieldDecorationRegistry.getDefault()
            .getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage();

      /*
       * Static block to initialize various properties
       */
      static {
         final FieldDecoration requiredFieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(
               FieldDecorationRegistry.DEC_REQUIRED);
         FIELD_DECORATION_REQUIRED_IMAGE = requiredFieldDecoration.getImage();
         FIELD_DECORATION_REQUIRED_DESCRIPTION = requiredFieldDecoration.getDescription();

         final FieldDecoration errorFieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(
               FieldDecorationRegistry.DEC_ERROR);
         FIELD_DECORATION_ERROR_IMAGE = errorFieldDecoration.getImage();
         FIELD_DECORATION_ERROR_DESCRIPTION = errorFieldDecoration.getDescription();
      }

   }

}
