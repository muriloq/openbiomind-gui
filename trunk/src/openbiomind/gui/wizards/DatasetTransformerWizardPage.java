/**
 * DatasetTransformerWizardPage.java
 *
 * The file DatasetTransformerWizardPage.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import java.io.File;

import openbiomind.gui.tasks.DatasetTransformerTaskData.FeatureSelectionMethodEnum;
import openbiomind.gui.util.CommonMessages;
import openbiomind.gui.util.Utility;
import openbiomind.gui.widgets.TextButtonComposite;
import openbiomind.gui.widgets.WidgetHelper;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * The class DatasetTransformerWizardPage.
 *
 * @author bsanghvi
 * @since Jul 2, 2008
 * @version Jul 10, 2008
 */
public class DatasetTransformerWizardPage extends AbstractTaskWizardPage implements IWizardPage {

   /**
    * The constant for page name (value = <code>openbiomind.gui.wizards.DatasetTransformerWizardPage</code>).
    */
   public static final String PAGE_NAME = "openbiomind.gui.wizards.DatasetTransformerWizardPage"; //$NON-NLS-1$

   /** The number of columns in various groups. */
   private static final int NUM_COLUMN_IN_GROUP = 2;

   /** The original dataset text button composite. */
   private TextButtonComposite originalDatasetTBC = null;

   /** The output directory text button composite. */
   private TextButtonComposite outputDirectoryTBC = null;

   /** The target category text. */
   private Text targetCategoryText = null;

   /** The number of selected features text. */
   private Text numberOfSelectedFeaturesText = null;

   /** The feature selection method combo. */
   private Combo featureSelectionMethodCombo = null;

   /** The feature selection method array. */
   private String[] featureSelectionMethodArray = null;

   /** The number of folds button. */
   private Button numberOfFoldsButton = null;

   /** The number of folds text. */
   private Text numberOfFoldsText = null;

   /** The test dataset button. */
   private Button testDatasetButton = null;

   /** The test dataset text button composite. */
   private TextButtonComposite testDatasetTBC = null;

   /**
    * Instantiates a new dataset transformer wizard page.
    *
    * @param pageTitle the page title
    * @param pageDescription the page description
    */
   public DatasetTransformerWizardPage(final String pageTitle, final String pageDescription) {
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
      addProjectInformationFields(composite, NUM_COLUMN_IN_GROUP);
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
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.Label_OriginalDataset,
            WizardMessages.Detail_OriginalDataset, true);
      this.originalDatasetTBC = createOriginalDatasetTBC(parent);

      // Output directory
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.Label_OutputDataset, WizardMessages.Detail_OutputDir,
            true);
      this.outputDirectoryTBC = createOutputDirTBC(parent);
   }

   /**
    * Creates the original dataset text button composite.
    *
    * @param parent the parent
    *
    * @return the text button composite
    */
   private TextButtonComposite createOriginalDatasetTBC(final Composite parent) {
      final TextButtonComposite textButtonComposite = new TextButtonComposite(parent) {

         @Override
         protected String buttonSelected() {
            return getFileDialog().open();
         }

      };
      textButtonComposite.setValid(false);
      textButtonComposite.setToolTipText(WizardMessages.Detail_OriginalDataset);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.applyTo(textButtonComposite);

      // create decorations
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textButtonComposite,
            CommonMessages.Error_FileNotExist);
      errorDecoration.show();

      // apply listeners
      textButtonComposite.addModifyListenerOnTextField(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            textButtonComposite.setValid(Utility.fileExists(getOriginalDatasetFilePath()));
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
      GUI.GRID_DATA_FILL_H_GRAB_H.applyTo(textButtonComposite);

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

      // Target category
      // TODO Find out if there is a list of category to choose from
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.Label_TargetCategory,
            WizardMessages.Detail_TargetCategory);
      this.targetCategoryText = createDefaultText(parent);

      // Number of selected features
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.DatasetTransformerWizardPage_Label_NumOfSelectedFeatures,
            WizardMessages.DatasetTransformerWizardPage_Detail_NumOfSelectedFeatures);
      this.numberOfSelectedFeaturesText = createNewNumberOnlyText(parent);

      // Feature selection method
      WidgetHelper
            .createNewFieldLabel(parent, WizardMessages.DatasetTransformerWizardPage_Label_FeatureSelectionMethod);
      this.featureSelectionMethodCombo = createDefaultCombo(parent, getFeatureSelectionMethodArray());

      // Select one of these
      WidgetHelper.createNewDetailsLabel(parent, WizardMessages.Detail_SelectOne, NUM_COLUMN_IN_GROUP);

      // Number of Folds
      this.numberOfFoldsButton = createNumberOfFoldsButton(parent);
      this.numberOfFoldsText = createNewNumberOnlyText(parent);
      getNumberOfFoldsText().setEnabled(false);

      // Test Dataset
      this.testDatasetButton = createTestDatasetButton(parent);
      this.testDatasetTBC = createNewOptionalFileTextButtonComposite(parent);
      getTestDatasetTBC().setEnabled(false);
   }

   /**
    * Creates the number of folds button.
    *
    * @param parent the parent
    *
    * @return the button
    */
   private Button createNumberOfFoldsButton(final Composite parent) {
      final Button button = new Button(parent, SWT.RADIO);
      button.setText(WizardMessages.DatasetTransformerWizardPage_Label_NumOfFolds + LABEL_SEPARATOR);
      button.setToolTipText(WizardMessages.DatasetTransformerWizardPage_Label_NumOfFolds);

      // apply layout
      GUI.GRID_DATA_DEFAULT.applyTo(button);

      // apply listeners
      button.addSelectionListener(new SelectionAdapter() {

         @Override
         public void widgetSelected(final SelectionEvent event) {
            getNumberOfFoldsText().setEnabled(specifiedNumberOfFolds());
            if (getNumberOfFoldsText().isEnabled()) {
               getNumberOfFoldsText().setFocus();
            }
         }

      });

      button.setSelection(false);

      return button;
   }

   /**
    * Creates the test dataset button.
    *
    * @param parent the parent
    *
    * @return the button
    */
   private Button createTestDatasetButton(final Composite parent) {
      final Button button = new Button(parent, SWT.RADIO);
      button.setText(WizardMessages.DatasetTransformerWizardPage_Label_TestDataset + LABEL_SEPARATOR);
      button.setToolTipText(WizardMessages.DatasetTransformerWizardPage_Label_TestDataset);

      // apply layout
      GUI.GRID_DATA_DEFAULT.applyTo(button);

      // apply listeners
      button.addSelectionListener(new SelectionAdapter() {

         @Override
         public void widgetSelected(final SelectionEvent event) {
            getTestDatasetTBC().setEnabled(specifiedTestDataset());
            if (getTestDatasetTBC().isEnabled()) {
               getTestDatasetTBC().setFocus();
            }
         }

      });

      button.setSelection(false);

      return button;
   }

   /**
    * Gets the original dataset file path.
    *
    * @return the original dataset file
    */
   public String getOriginalDatasetFilePath() {
      return getOriginalDatasetTBC().getText();
   }

   /**
    * Gets the original dataset text button composite.
    *
    * @return the original dataset text button composite
    */
   private TextButtonComposite getOriginalDatasetTBC() {
      return this.originalDatasetTBC;
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
    * Gets the target category.
    *
    * @return the target category
    */
   public String getTargetCategory() {
      return getTargetCategoryText().getText();
   }

   /**
    * Gets the target category text.
    *
    * @return the target category text
    */
   private Text getTargetCategoryText() {
      return this.targetCategoryText;
   }

   /**
    * Gets the number of selected features.
    *
    * @return the number of selected features
    */
   public Integer getNumberOfSelectedFeatures() {
      try {
         return Integer.valueOf(getNumberOfSelectedFeaturesText().getText());
      } catch (final NumberFormatException e) {
         return null;
      }
   }

   /**
    * Gets the enhanced dataset file path text.
    *
    * @return the enhancedDatasetFilePathText
    */
   private Text getNumberOfSelectedFeaturesText() {
      return this.numberOfSelectedFeaturesText;
   }

   /**
    * Gets the feature selection method.
    *
    * @return the feature selection method
    */
   public FeatureSelectionMethodEnum getFeatureSelectionMethod() {
      return FeatureSelectionMethodEnum.parse(getFeatureSelectionMethodArray()[getFeatureSelectionMethodCombo()
            .getSelectionIndex()]);
   }

   /**
    * Gets the feature selection method combo.
    *
    * @return the feature selection method combo
    */
   private Combo getFeatureSelectionMethodCombo() {
      return this.featureSelectionMethodCombo;
   }

   /**
    * Gets the feature selection method array.
    *
    * @return the feature selection method array
    */
   private String[] getFeatureSelectionMethodArray() {
      if (this.featureSelectionMethodArray == null) {
         this.featureSelectionMethodArray = new String[] { EMPTY,
               FeatureSelectionMethodEnum.DIFFERENTIATION.toString(), FeatureSelectionMethodEnum.SAM.toString() };
      }

      return this.featureSelectionMethodArray;
   }

   /**
    * Specified number of folds.
    *
    * @return true, if successful
    */
   public boolean specifiedNumberOfFolds() {
      return getNumberOfFoldsButton().getSelection();
   }

   /**
    * Gets the number of folds button.
    *
    * @return the number of folds button
    */
   private Button getNumberOfFoldsButton() {
      return this.numberOfFoldsButton;
   }

   /**
    * Gets the number of folds.
    *
    * @return the number of folds
    */
   public Integer getNumberOfFolds() {
      try {
         return Integer.valueOf(getNumberOfFoldsText().getText());
      } catch (final NumberFormatException e) {
         return null;
      }
   }

   /**
    * Gets the number of folds text.
    *
    * @return the number of folds text
    */
   private Text getNumberOfFoldsText() {
      return this.numberOfFoldsText;
   }

   /**
    * Specified test dataset.
    *
    * @return true, if successful
    */
   public boolean specifiedTestDataset() {
      return getTestDatasetButton().getSelection();
   }

   /**
    * Gets the test dataset button.
    *
    * @return the test dataset button
    */
   private Button getTestDatasetButton() {
      return this.testDatasetButton;
   }

   /**
    * Gets the test dataset.
    *
    * @return the test dataset
    */
   public String getTestDataset() {
      return getTestDatasetTBC().getText();
   }

   /**
    * Gets the test dataset text button composite.
    *
    * @return the test dataset text button composite
    */
   private TextButtonComposite getTestDatasetTBC() {
      return this.testDatasetTBC;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#validatePage()
    */
   @Override
   protected void validatePage() {
      final boolean valid = isProjectInformationValid() && getOriginalDatasetTBC().isValid()
            && getOutputDirectoryTBC().isValid() && getTestDatasetTBC().isValid();
      setPageComplete(valid);
      if (!valid) {
         setErrorMessage(CommonMessages.Error_FixToContinue);
      } else {
         setErrorMessage(null);
      }
   }

}
