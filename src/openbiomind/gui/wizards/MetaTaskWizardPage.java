/**
 * MetaTaskWizardPage.java
 *
 * The file MetaTaskWizardPage.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import java.io.File;

import openbiomind.gui.tasks.ClassificationMethodEnum;
import openbiomind.gui.tasks.MetaTaskShufflingEnum;
import openbiomind.gui.util.CommonMessages;
import openbiomind.gui.util.Utility;
import openbiomind.gui.widgets.TextButtonComposite;
import openbiomind.gui.widgets.WidgetHelper;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * The class MetaTaskWizardPage.
 *
 * @author bsanghvi
 * @since Jul 5, 2008
 * @version Jul 16, 2008
 */
public class MetaTaskWizardPage extends AbstractTaskWizardPage implements IWizardPage {

   /**
    * The constant for page name (value = <code>openbiomind.gui.wizards.MetaTaskWizardPage</code>).
    */
   public static final String PAGE_NAME = "openbiomind.gui.wizards.MetaTaskWizardPage"; //$NON-NLS-1$

   /** The number of columns in various groups. */
   private static final int NUM_COLUMN_IN_GROUP = 3;

   /** The dataset directory text button composite. */
   private TextButtonComposite datasetDirectoryTBC = null;

   /** The output directory text button composite. */
   private TextButtonComposite outputDirectoryTBC = null;

   /** The number of tasks. */
   private Text numberOfTasks = null;

   /** The target category combo. */
   private Combo targetCategoryCombo = null;

   /** The target category array. */
   private String[] targetCategoryArray = null;

   /** The feature classification method combo. */
   private Combo classificationMethodCombo = null;

   /** The classification method array. */
   private String[] classificationMethodArray = null;

   /** The meta task shuffling combo. */
   private Combo metaTaskShufflingCombo = null;

   /** The meta task shuffling array. */
   private String[] metaTaskShufflingArray = null;

   /**
    * Instantiates a new meta task wizard page.
    *
    * @param pageTitle the page title
    * @param pageDescription the page description
    */
   public MetaTaskWizardPage(final String pageTitle, final String pageDescription) {
      super(PAGE_NAME, pageTitle, pageDescription);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#createBaseComposite(org.eclipse.swt.widgets.Composite)
    */
   @Override
   protected Composite createBaseComposite(final Composite parent) {
      final Composite composite = new Composite(parent, SWT.NULL);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.applyTo(composite);
      GUI.GRID_LAYOUT_WITH_MARGIN.copy().numColumns(NUM_COLUMN_IN_GROUP).applyTo(composite);

      // add components
      addProjectInformationFields(composite);
      createRequiredGroup(composite);
      createOptionalGroup(composite);

      return composite;
   }

   /**
    * Creates the required group.
    *
    * @param parent the parent
    */
   private void createRequiredGroup(final Composite parent) {
      // Required Arguments
      addSection(parent, WizardMessages.GroupLabel_RequiredArguments, NUM_COLUMN_IN_GROUP);

      // Original dataset
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.MetaTaskWizardPage_Label_DatasetDir,
            WizardMessages.MetaTaskWizardPage_Tip_DatasetDir, true);
      this.datasetDirectoryTBC = createDatasetDirectoryTBC(parent);

      // Output directory
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.Label_OutputDir, WizardMessages.Detail_OutputDir, true);
      this.outputDirectoryTBC = createOutputDirTBC(parent);
   }

   /**
    * Creates the dataset directory text button composite.
    *
    * @param parent the parent
    *
    * @return the text button composite
    */
   private TextButtonComposite createDatasetDirectoryTBC(final Composite parent) {
      final TextButtonComposite textButtonComposite = new TextButtonComposite(parent) {

         @Override
         protected String buttonSelected() {
            return getDirectoryDialog().open();
         }

      };
      textButtonComposite.setValid(false);
      textButtonComposite.setToolTipText(WizardMessages.MetaTaskWizardPage_Tip_DatasetDir);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(NUM_COLUMN_IN_GROUP - 1, 1).applyTo(textButtonComposite);

      // create decorations
      // TODO Update to identify that the folder must contain train and test tab files
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textButtonComposite,
            CommonMessages.Error_InvalidDir);
      errorDecoration.show();

      // apply listeners
      textButtonComposite.addModifyListenerOnTextField(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            final File directory = new File(getDatasetDirectoryPath());
            textButtonComposite
                  .setValid(Utility.directoryExists(directory)
                        && Utility.listFileNames(directory, Resources.TRAIN_FILE_STARTS_WITH, Resources.TAB_EXTENSION).length > 0
                        && Utility.listFileNames(directory, Resources.TEST_FILE_STARTS_WITH, Resources.TAB_EXTENSION).length > 0);
            if (textButtonComposite.isValid()) {
               errorDecoration.hide();
            } else {
               errorDecoration.show();
               errorDecoration.showHoverText(errorDecoration.getDescriptionText());
            }

            validatePage();
         }

      });

      return textButtonComposite;
   }

   /**
    * Creates the output directory text button composite.
    *
    * @param parent the parent
    *
    * @return the text button composite
    */
   private TextButtonComposite createOutputDirTBC(final Composite parent) {
      final TextButtonComposite textButtonComposite = new TextButtonComposite(parent) {

         @Override
         protected String buttonSelected() {
            return getDirectoryDialog().open();
         }

      };
      textButtonComposite.setValid(false);
      textButtonComposite.setToolTipText(WizardMessages.Detail_OutputDir);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(NUM_COLUMN_IN_GROUP - 1, 1).applyTo(textButtonComposite);

      // create decorations
      final Text textField = textButtonComposite.getTextField();
      final ControlDecoration warningDecoration = WidgetHelper.createNewWarningDecoration(textField,
            CommonMessages.Warn_DirAlreadyExist);
      warningDecoration.hide();
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textField,
            CommonMessages.Error_InvalidDir);
      errorDecoration.show();

      // apply listeners
      textButtonComposite.addModifyListenerOnTextField(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            boolean inError = false;
            boolean inWarning = false;
            final String directoryPath = textButtonComposite.getText();

            if (Utility.isEmpty(directoryPath)) {
               inError = true;
            } else {
               final File directory = new File(directoryPath);
               if (directory.isFile()) {
                  inError = true;
               } else if (directory.exists()) {
                  inWarning = true;
               }
            }

            textButtonComposite.setValid(!inError);
            showErrorOrWarning(inError, errorDecoration, inWarning, warningDecoration);

            validatePage();
         }

      });

      return textButtonComposite;
   }

   /**
    * Creates the optional group.
    *
    * @param parent the parent
    */
   private void createOptionalGroup(final Composite parent) {
      // Optional Arguments
      addSection(parent, WizardMessages.GroupLabel_OptionalArguments, NUM_COLUMN_IN_GROUP);

      // Number of tasks
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.MetaTaskWizardPage_Label_NumOfTasks,
            WizardMessages.MetaTaskWizardPage_Tip_NumOfTasks);
      this.numberOfTasks = createNewNumberOnlyText(parent);
      WidgetHelper.createNewBlankLabel(parent);

      // Target category
      // TODO Read from the given input files
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.Label_TargetCategory,
            WizardMessages.Detail_TargetCategory);
      this.targetCategoryCombo = createDefaultDropDownCombo(parent, getTargetCategoryArray());
      WidgetHelper.createNewBlankLabel(parent);

      // Classification method
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.MetaTaskWizardPage_Label_ClassificationMethod);
      this.classificationMethodCombo = createDefaultReadOnlyCombo(parent, getClassificationMethodArray());
      WidgetHelper.createNewBlankLabel(parent);

      // MetaTask shuffling
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.MetaTaskWizardPage_Label_MetaTaskShuffling);
      this.metaTaskShufflingCombo = createDefaultReadOnlyCombo(parent, getMetaTaskShufflingArray());
      WidgetHelper.createNewBlankLabel(parent);
   }

   /**
    * Gets the dataset directory path.
    *
    * @return the dataset directory path
    */
   public String getDatasetDirectoryPath() {
      return getDatasetDirectoryTBC().getText();
   }

   /**
    * Gets the dataset directory text button composite.
    *
    * @return the dataset directory text button composite
    */
   private TextButtonComposite getDatasetDirectoryTBC() {
      return this.datasetDirectoryTBC;
   }

   /**
    * Gets the output directory.
    *
    * @return the output directory
    */
   public String getOutputDirectory() {
      return getOutputDirectoryTBC().getText();
   }

   /**
    * Gets the output directory text button composite.
    *
    * @return the output directory text button composite
    */
   private TextButtonComposite getOutputDirectoryTBC() {
      return this.outputDirectoryTBC;
   }

   /**
    * Gets the number of selected tasks.
    *
    * @return the number of selected tasks
    */
   public Integer getNumberTasks() {
      try {
         return Integer.valueOf(getNumberOfTasks().getText());
      } catch (final NumberFormatException e) {
         return null;
      }
   }

   /**
    * Gets the number of tasks.
    *
    * @return the number of tasks
    */
   private Text getNumberOfTasks() {
      return this.numberOfTasks;
   }

   /**
    * Gets the target category.
    *
    * @return the target category
    */
   public String getTargetCategory() {
      return getTargetCategoryCombo().getText();
   }

   /**
    * Gets the target category combo.
    *
    * @return the target category combo
    */
   private Combo getTargetCategoryCombo() {
      return this.targetCategoryCombo;
   }

   /**
    * Gets the target category array.
    *
    * @return the target category array
    */
   private String[] getTargetCategoryArray() {
      if (this.targetCategoryArray == null) {
         this.targetCategoryArray = new String[] { EMPTY, Resources.CATEGORY_CASE.toString() };
      }

      return this.targetCategoryArray;
   }

   /**
    * Gets the classification method.
    *
    * @return the classification method
    */
   public ClassificationMethodEnum getClassificationMethod() {
      return ClassificationMethodEnum.parse(getClassificationMethodArray()[getClassificationMethodCombo()
            .getSelectionIndex()]);
   }

   /**
    * Gets the classification method combo.
    *
    * @return the classification method combo
    */
   private Combo getClassificationMethodCombo() {
      return this.classificationMethodCombo;
   }

   /**
    * Gets the classification method array.
    *
    * @return the classification method array
    */
   private String[] getClassificationMethodArray() {
      if (this.classificationMethodArray == null) {
         this.classificationMethodArray = new String[] { EMPTY, ClassificationMethodEnum.SNPQA.toString(),
               ClassificationMethodEnum.BOOLSIMPLE.toString(), ClassificationMethodEnum.SNPLOCAL.toString(),
               ClassificationMethodEnum.SNP.toString(), ClassificationMethodEnum.BOOLCOMPLEX.toString(),
               ClassificationMethodEnum.CONVENTIONAL.toString() };
      }

      return this.classificationMethodArray;
   }

   /**
    * Gets the meta task shuffling.
    *
    * @return the meta task shuffling
    */
   public MetaTaskShufflingEnum getMetaTaskShuffling() {
      return MetaTaskShufflingEnum.parse(getMetaTaskShufflingArray()[getMetaTaskShufflingCombo().getSelectionIndex()]);
   }

   /**
    * Gets the meta task shuffling combo.
    *
    * @return the meta task shuffling combo
    */
   private Combo getMetaTaskShufflingCombo() {
      return this.metaTaskShufflingCombo;
   }

   /**
    * Gets the meta task shuffling array.
    *
    * @return the meta task shuffling array
    */
   private String[] getMetaTaskShufflingArray() {
      if (this.metaTaskShufflingArray == null) {
         this.metaTaskShufflingArray = new String[] { EMPTY, MetaTaskShufflingEnum.ON.toString(),
               MetaTaskShufflingEnum.OFF.toString() };
      }

      return this.metaTaskShufflingArray;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#validatePage()
    */
   @Override
   protected void validatePage() {
      final boolean valid = isProjectInformationValid() && getDatasetDirectoryTBC().isValid()
            && getOutputDirectoryTBC().isValid();
      setPageComplete(valid);
      if (!valid) {
         setErrorMessage(CommonMessages.Error_FixToContinue);
      } else {
         setErrorMessage(null);
      }
   }

}
