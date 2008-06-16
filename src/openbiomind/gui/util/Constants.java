/**
 * Constants.java
 *
 * The file Constants.java.
 *
 * $Id$
 */
package openbiomind.gui.util;

import java.io.File;

import org.eclipse.jface.resource.JFaceResources;

/**
 * The final class Constants contains various constant definitions.
 *
 * @author bsanghvi
 * @since Jun 12, 2008
 * @version Jun 12, 2008
 */
public final class Constants {

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

   /** The constant for specifying wildard for anything (value = <code>*</code>). */
   public static final String WILDCARD_ANY = "*"; //$NON-NLS-1$

   /** The Constant specifying the JAR file extension (value = <code>.jar</code>). */
   public static final String JAR_EXTENSION = ".jar"; //$NON-NLS-1$

   /** The constant specifying the label for OpenBiomind JAR file. */
   public static final String OPENBIOMIND_JAR_NAME = "OpenBiomind JAR"; //$NON-NLS-1$

   /**
    * The constant specifying the name of the pipeline properties file (value =
    * <code>pipeline.properties</code>).
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
       * Find the current directory
       */
      String currentDirectory = "."; //$NON-NLS-1$
      try {
         currentDirectory = new File(currentDirectory).getCanonicalPath();
      } catch (Exception e) {
         e.printStackTrace();
      }
      CURRENT_DIRECTORY = currentDirectory;
   }

//   /** The constant for specifying left symbol for required argument (value = <code>&lt;</code>). */
//   public static final String ARGUMENT_REQUIRED_LEFT = "<"; //$NON-NLS-1$
//
//   /** The constant for specifying right symbol for required argument (value = <code>&gt;</code>). */
//   public static final String ARGUMENT_REQUIRED_RIGHT = ">"; //$NON-NLS-1$
//
//   /** The constant for specifying left symbol for optional argument (value = <code>[</code>). */
//   public static final String ARGUMENT_OPTIONAL_LEFT = "["; //$NON-NLS-1$
//
//   /** The constant for specifying right symbol for optional argument (value = <code>]</code>). */
//   public static final String ARGUMENT_OPTIONAL_RIGHT = "]"; //$NON-NLS-1$

}
