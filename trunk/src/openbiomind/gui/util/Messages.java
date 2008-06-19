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
 * @version Jun 14, 2008
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

   /** The source file. */
   public static String Amp_SourceFile;

   /** The preference page description. */
   public static String Desc_PreferencePage;

   /** The Enhance Dataset Wizard page description. */
   public static String Desc_Wizard_EnhanceDataset;

   /** The Error enhanced dataset is directory. */
   public static String Error_EnhancedDataset_IsDirectory;

   /** The Error enhanced dataset directory invalid. */
   public static String Error_EnhancedDatasetDirectory_Invalid;

   /** The Error enhanced dataset file empty. */
   public static String Error_EnhancedDatasetFile_Empty;

   /** The Error ontology association must exist or empty. */
   public static String Error_OntologyAssociation_MustExistOrEmpty;

   /** The Error ontology description must exist or empty. */
   public static String Error_OntologyDescription_MustExistOrEmpty;

   /** The Error original dataset must exist. */
   public static String Error_OriginalDataset_MustExist;

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

   /** The ontology description information. */
   public static String Info_OntologyDescription;

   /** The original dataset information. */
   public static String Info_OriginalDataset;

   /** The Optional arguments. */
   public static String OptionalArguments;

   /** The Required arguments. */
   public static String RequiredArguments;

   /** The Startup message. */
   public static String Startup;

   /** The Warning enhanced dataset already exists. */
   public static String Warning_EnhancedDataset_AlreadyExists;

   /** The Warning enhanced dataset directory does not exist. */
   public static String Warning_EnhancedDatasetDirectory_DoesNotExist;

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
