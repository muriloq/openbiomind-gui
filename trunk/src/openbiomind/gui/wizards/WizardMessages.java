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
 * @version Jul 6, 2008
 */
public class WizardMessages extends NLS {

   /** The Constant BUNDLE_NAME. */
   private static final String BUNDLE_NAME = "openbiomind.gui.wizards.wizard_messages"; //$NON-NLS-1$

   /** The Abstract task wizard executing task. */
   public static String AbstractTaskWizard_ExecutingTask;

   /** The Abstract task wizard leave blank or specify a number. */
   public static String AbstractTaskWizard_LeaveBlank_Number;

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

   /** The Dataset transformer wizard description. */
   public static String DatasetTransformerWizard_Description;

   /** The Dataset transformer wizard name. */
   public static String DatasetTransformerWizard_Name;

   /** The Dataset transformer wizard page detail number of selected features. */
   public static String DatasetTransformerWizardPage_Detail_NumOfSelectedFeatures;

   /** The Dataset transformer wizard page label feature selection method. */
   public static String DatasetTransformerWizardPage_Label_FeatureSelectionMethod;

   /** The Dataset transformer wizard page label number of folds. */
   public static String DatasetTransformerWizardPage_Label_NumOfFolds;

   /** The Dataset transformer wizard page label number of selected features. */
   public static String DatasetTransformerWizardPage_Label_NumOfSelectedFeatures;

   /** The Dataset transformer wizard page label test dataset. */
   public static String DatasetTransformerWizardPage_Label_TestDataset;

   /** The Detail original dataset. */
   public static String Detail_OriginalDataset;

   /** The Detail output directory. */
   public static String Detail_OutputDir;

   /** The Detail select one of these. */
   public static String Detail_SelectOne;

   /** The detail target category. */
   public static String Detail_TargetCategory;

   /** The Enhance dataset wizard description. */
   public static String EnhanceDatasetWizard_Description;

   /** The Enhance dataset wizard name. */
   public static String EnhanceDatasetWizard_Name;

   /** The Enhance dataset wizard page detail association description. */
   public static String EnhanceDatasetWizardPage_Detail_AssociationDesc;

   /** The Enhance dataset wizard page detail enhanced dataset. */
   public static String EnhanceDatasetWizardPage_Detail_EnhancedDataset;

   /** The Enhance dataset wizard page detail ontology description. */
   public static String EnhanceDatasetWizardPage_Detail_OntologyDesc;

   /** The Enhance dataset wizard page label association file. */
   public static String EnhanceDatasetWizardPage_Label_AssociationFile;

   /** The Enhance dataset wizard page label description file. */
   public static String EnhanceDatasetWizardPage_Label_DescriptionFile;

   /** The Enhance dataset wizard page label destination directory. */
   public static String EnhanceDatasetWizardPage_Label_DestinationDir;

   /** The Enhance dataset wizard page label destination file. */
   public static String EnhanceDatasetWizardPage_Label_DestinationFile;

   /** The Enhance dataset wizard page label enhanced dataset path. */
   public static String EnhanceDatasetWizardPage_Label_EnhancedDatasetPath;

   /** The Enhance dataset wizard page tip enhanced dataset. */
   public static String EnhanceDatasetWizardPage_Tip_EnhancedDataset;

   /** The Enhance dataset wizard page use original dataset directory. */
   public static String EnhanceDatasetWizardPage_UseOriginalDatasetDir;

   /** The Group label optional arguments. */
   public static String GroupLabel_OptionalArguments;

   /** The Group label required arguments. */
   public static String GroupLabel_RequiredArguments;

   /** The Label none. */
   public static String Label_None;

   /** The label original dataset. */
   public static String Label_OriginalDataset;

   /** The label output dataset. */
   public static String Label_OutputDataset;

   /** The Dataset transformer wizard page label target category. */
   public static String Label_TargetCategory;

   /** The Meta task wizard description. */
   public static String MetaTaskWizard_Description;

   /** The Meta task wizard name. */
   public static String MetaTaskWizard_Name;

   /** The Meta task wizard page label classification method. */
   public static String MetaTaskWizardPage_Label_ClassificationMethod;

   /** The Meta task wizard page label dataset directory. */
   public static String MetaTaskWizardPage_Label_DatasetDir;

   /** The Meta task wizard page label number of tasks. */
   public static String MetaTaskWizardPage_Label_NumOfTasks;

   /** The Meta task wizard page_ tip_ dataset directory. */
   public static String MetaTaskWizardPage_Tip_DatasetDir;

   /** The Meta task wizard page tip number of tasks. */
   public static String MetaTaskWizardPage_Tip_NumOfTasks;

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
