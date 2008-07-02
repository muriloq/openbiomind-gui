/**
 * Messages.java
 *
 * The file Messages.java.
 *
 * $Id$
 */
package openbiomind.gui.util;

import org.eclipse.osgi.util.NLS;

/**
 * The class Messages.
 *
 * @author bsanghvi
 * @since Jun 14, 2008
 * @version Jun 27, 2008
 */
public class Messages extends NLS {

   /** The Constant BUNDLE_NAME. */
   private static final String BUNDLE_NAME = "openbiomind.gui.messages"; //$NON-NLS-1$

   /** The destination directory. */
   public static String Amp_DestinationDirectory;

   /** The association file. */
   public static String Amp_AssociationFile;

   /** The description file. */
   public static String Amp_DescriptionFile;

   /** The destination file. */
   public static String Amp_DestinationFile;

   /** The enhanced dataset path. */
   public static String Amp_EnhancedDatasetPath;

   /** The execution name. */
   public static String Amp_ExecutionName;

   /** The source file. */
   public static String Amp_SourceFile;

   /** The use original dataset directory. */
   public static String Amp_UseOriginalDatasetDir;

   /** The Console title. */
   public static String ConsoleTitle;

   /** The preference page description. */
   public static String Desc_PreferencePage;

   /** The Enhance Dataset Wizard page description. */
   public static String Desc_Wizard_EnhanceDataset;

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

   /** The error string for OpenBiomind JAR related preference. */
   public static String Error_Preference_OpenBiomindJAR;

   /** The error string for pipeline.properties related preference. */
   public static String Error_Preference_PipelineProperties;

   /** The association description information. */
   public static String Info_AssociationDescription;

   /** The destination directory information. */
   public static String Info_DestinationDirectory;

   /** The enhanced dataset information. */
   public static String Info_EnhancedDataset;

   /** The leave blank or specify file information. */
   public static String Info_LeaveBlankOrSpecifyFile;

   /** The ontology description information. */
   public static String Info_OntologyDescription;

   /** The original dataset information. */
   public static String Info_OriginalDataset;

   /** The project details information. */
   public static String Info_ProjectDetails;

   /** The project name information. */
   public static String Info_ProjectName;

   /** The Optional arguments. */
   public static String OptionalArguments;

   /** The Required arguments. */
   public static String RequiredArguments;

   /** The Startup message. */
   public static String Startup;

   /** The tool-tip for enhanced dataset. */
   public static String Tip_EnhancedDataset;

   /** The tip for project name. */
   public static String Tip_ProjectName;

   /** The Warning directory not exist. */
   public static String Warn_DirectoryNotExist;

   /** The Warning file already exists. */
   public static String Warn_FileAlreadyExists;

   /** The Wizard progress execute task. */
   public static String WizardProgress_ExecuteTask;

   /** The Wizard progress load files. */
   public static String WizardProgress_LoadFiles;

   /** The Wizard progress prepare process. */
   public static String WizardProgress_PrepareProcess;

   /** The Wizard progress prepare task data. */
   public static String WizardProgress_PrepareTaskData;

   /*
    * Initialize the messages
    */
   static {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, Messages.class);
   }

   /**
    * Instantiates a new messages.
    */
   private Messages() {
      // empty constructor
   }

}
