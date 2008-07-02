/**
 * CommonMessages.java
 *
 * The file CommonMessages.java.
 *
 * $Id$
 */
package openbiomind.gui.util;

import org.eclipse.osgi.util.NLS;

/**
 * The class CommonMessages.
 *
 * @author bsanghvi
 * @since Jun 14, 2008
 * @version Jun 27, 2008
 */
public class CommonMessages extends NLS {

   /** The Constant BUNDLE_NAME. */
   private static final String BUNDLE_NAME = "openbiomind.gui.util.common_messages"; //$NON-NLS-1$

   /** The Console title. */
   public static String ConsoleTitle;

   /** The Error file not exist. */
   public static String Error_FileNotExist;

   /** The Error fix to continue. */
   public static String Error_FixToContinue;

   /** The Error invalid directory. */
   public static String Error_InvalidDirectory;

   /** The Error invalid file. */
   public static String Error_InvalidFile;

   /** The preference error string. */
   public static String Error_Preference;

   /** The destination directory information. */
   public static String Info_DestinationDirectory;

   /** The leave blank or specify file information. */
   public static String Info_LeaveBlankOrSpecifyFile;

   /** The Startup message. */
   public static String Startup;

   /** The Warning directory not exist. */
   public static String Warn_DirectoryNotExist;

   /** The Warning file already exists. */
   public static String Warn_FileAlreadyExists;

   /*
    * Initialize the messages
    */
   static {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, CommonMessages.class);
   }

   /**
    * Instantiates a new messages.
    */
   private CommonMessages() {
      // empty constructor
   }

}
