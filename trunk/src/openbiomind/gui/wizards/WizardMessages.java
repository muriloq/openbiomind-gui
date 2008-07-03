/**
 * WizardMessages.java
 *
 * The file WizardMessages.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import org.eclipse.osgi.util.NLS;

/**
 * The class WizardMessages.
 *
 * @author bsanghvi
 * @since Jul 2, 2008
 * @version Jul 2, 2008
 */
public class WizardMessages extends NLS {

   /** The Constant BUNDLE_NAME. */
   private static final String BUNDLE_NAME = "openbiomind.gui.wizards.wizard_messages"; //$NON-NLS-1$

   /** The Abstract task wizard executing task. */
   public static String AbstractTaskWizard_ExecutingTask;

   /** The Abstract task wizard loading files. */
   public static String AbstractTaskWizard_LoadingFiles;

   /** The Abstract task wizard preparing process. */
   public static String AbstractTaskWizard_PreparingProcess;

   /** The Abstract task wizard preparing task data. */
   public static String AbstractTaskWizard_PreparingTaskData;

   /** The Abstract task wizard page info project name. */
   public static String AbstractTaskWizardPage_Info_ProjectName;

   /** The Abstract task wizard page label project name. */
   public static String AbstractTaskWizardPage_Label_ProjectName;

   /** The Abstract task wizard page tip project name. */
   public static String AbstractTaskWizardPage_Tip_ProjectName;

   /** The Detail original dataset. */
   public static String Detail_OriginalDataset;

   /** The Enhance dataset wizard description. */
   public static String EnhanceDatasetWizard_Description;

   /** The Enhance dataset wizard name. */
   public static String EnhanceDatasetWizard_Name;

   /** The Enhance dataset wizard page detail association description. */
   public static String EnhanceDatasetWizardPage_Detail_AssociationDescription;

   /** The Enhance dataset wizard page detail enhanced dataset. */
   public static String EnhanceDatasetWizardPage_Detail_EnhancedDataset;

   /** The Enhance dataset wizard page detail ontology description. */
   public static String EnhanceDatasetWizardPage_Detail_OntologyDescription;

   /** The Enhance dataset wizard page label association file. */
   public static String EnhanceDatasetWizardPage_Label_AssociationFile;

   /** The Enhance dataset wizard page label description file. */
   public static String EnhanceDatasetWizardPage_Label_DescriptionFile;

   /** The Enhance dataset wizard page label destination directory. */
   public static String EnhanceDatasetWizardPage_Label_DestinationDirectory;

   /** The Enhance dataset wizard page label destination file. */
   public static String EnhanceDatasetWizardPage_Label_DestinationFile;

   /** The Enhance dataset wizard page label enhanced dataset. */
   public static String EnhanceDatasetWizardPage_Label_EnhancedDataset;

   /** The Enhance dataset wizard page label enhanced dataset path. */
   public static String EnhanceDatasetWizardPage_Label_EnhancedDatasetPath;

   /** The Enhance dataset wizard page tip enhanced dataset. */
   public static String EnhanceDatasetWizardPage_Tip_EnhancedDataset;

   /** The Enhance dataset wizard page use original dataset dir. */
   public static String EnhanceDatasetWizardPage_UseOriginalDatasetDir;

   /** The Group label optional arguments. */
   public static String GroupLabel_OptionalArguments;

   /** The Group label required arguments. */
   public static String GroupLabel_RequiredArguments;

   /** The label original dataset. */
   public static String Label_OriginalDataset;

   /*
    * Initialize the messages
    */
   static {
      // initialize resource bundle
      NLS.initializeMessages(BUNDLE_NAME, WizardMessages.class);
   }

   /**
    * Instantiates a new wizard messages.
    */
   private WizardMessages() {
      // empty constructor
   }

}
