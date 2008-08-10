/**
 * DatasetTransformerWizardPage.java
 *
 * The file DatasetTransformerWizardPage.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import java.io.File;

import openbiomind.gui.common.TextButtonComposite;
import openbiomind.gui.data.DatasetTransformerTaskData;
import openbiomind.gui.data.FeatureSelectionMethodEnum;
import openbiomind.gui.util.Utility;
import openbiomind.gui.util.WidgetHelper;

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
 * @version Aug 10, 2008
 */
public class DatasetTransformerWizardPage extends AbstractTaskWizardPage implements IWizardPage {

   /**
    * The constant for page name (value = <code>openbiomind.gui.wizards.DatasetTransformerWizardPage</code>).
    */
   public static final String PAGE_NAME = "openbiomind.gui.wizards.DatasetTransformerWizardPage"; //$NON-NLS-1$

   /** The number of columns in various groups. */
   private static final int NUM_COLUMN_IN_GROUP = 3;

   /** The original dataset text button composite. */
   private TextButtonComposite originalDatasetTBC = null;

   /** The output directory text button composite. */
   private TextButtonComposite outputDirectoryTBC = null;

   /** The target category combo. */
   private Combo targetCategoryCombo = null;

   /** The number of selected features text. */
   private Text numberOfSelectedFeaturesText = null;

   /** The feature selection method combo. */
   private Combo featureSelectionMethodCombo = null;

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
    */
   public DatasetTransformerWizardPage() {
      this(Messages.DataTransWiz_Name, Messages.DataTransWiz_Desc);
   }

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
      addSection(parent, Messages.Label_ReqdArg, NUM_COLUMN_IN_GROUP);

      // Source dataset
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_SrcData, Messages.Tip_SrcData, true);
      this.originalDatasetTBC = createOriginalDatasetTBC(parent);

      // Output directory
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_OutDir, Messages.Label_SpecifyOutDir, true);
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
      textButtonComposite.setToolTipText(Messages.Tip_SrcData);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(NUM_COLUMN_IN_GROUP - 1, 1).applyTo(textButtonComposite);

      // create decorations
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textButtonComposite,
            Messages.Err_FileNotExist);
      errorDecoration.show();

      // apply listeners
      textButtonComposite.addModifyListenerOnTextField(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            handleModifyText(textButtonComposite, errorDecoration, Utility.fileExists(getInputDataset()));
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
      textButtonComposite.setToolTipText(Messages.Label_SpecifyOutDir);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(NUM_COLUMN_IN_GROUP - 1, 1).applyTo(textButtonComposite);

      // create decorations
      final Text textField = textButtonComposite.getTextField();
      final ControlDecoration warningDecoration = WidgetHelper.createNewWarningDecoration(textField,
            Messages.Err_DirAlreadyExist);
      warningDecoration.hide();
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textField,
            Messages.Err_InvalidDir);
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
      addSection(parent, Messages.Label_OptionalArg, NUM_COLUMN_IN_GROUP);

      // Target category
      // TODO Read from the given input files
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_TargetCat);
      this.targetCategoryCombo = createDefaultDropDownCombo(parent, getTargetCategoryArray());
      WidgetHelper.createNewBlankLabel(parent);

      // Number of selected features
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_NumOfSelecFeatures);
      this.numberOfSelectedFeaturesText = createNewNumberOnlyText(parent);
      WidgetHelper.createNewBlankLabel(parent);

      // Feature selection method
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_FeatureSelecMethod);
      this.featureSelectionMethodCombo = createDefaultReadOnlyCombo(parent, getFeatureSelectionMethodArray());
      WidgetHelper.createNewBlankLabel(parent);

      // Select one of these
      WidgetHelper.createNewDetailsLabel(parent, Messages.Detail_SelectOne, NUM_COLUMN_IN_GROUP);
      // Number of Folds
      this.numberOfFoldsButton = createNumberOfFoldsButton(parent);
      this.numberOfFoldsText = createNewNumberOnlyText(parent);
      getNumberOfFoldsText().setEnabled(false);
      WidgetHelper.createNewBlankLabel(parent);
      // Test Dataset
      this.testDatasetButton = createTestDatasetButton(parent);
      this.testDatasetTBC = createNewOptionalFileTextButtonComposite(parent, NUM_COLUMN_IN_GROUP - 1);
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
      button.setText(Messages.Label_NumOfFolds + LABEL_SEPARATOR);
      button.setToolTipText(Messages.Label_NumOfFolds);

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
      button.setText(Messages.Label_TestData + LABEL_SEPARATOR);
      button.setToolTipText(Messages.Label_TestData);

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
    * Gets the input dataset file path.
    *
    * @return the input dataset file
    */
   public String getInputDataset() {
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
         setErrorMessage(Messages.Err_FixErrToContinue);
      } else {
         setErrorMessage(null);
      }
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#prepareTaskData()
    */
   @Override
   public DatasetTransformerTaskData prepareTaskData() {
      final DatasetTransformerTaskData datasetTransformerTaskData = new DatasetTransformerTaskData();
      datasetTransformerTaskData.setInputDataset(getInputDataset());
      datasetTransformerTaskData.setOutputDirectory(getOutputDirectory());
      datasetTransformerTaskData.setTargetCategory(getTargetCategory());
      if (specifiedNumberOfFolds()) {
         datasetTransformerTaskData.setNumberOfFolds(getNumberOfFolds());
      } else if (specifiedTestDataset()) {
         datasetTransformerTaskData.setTestDataset(getTestDataset());
      }
      datasetTransformerTaskData.setNumberOfSelectedFeatures(getNumberOfSelectedFeatures());
      datasetTransformerTaskData.setFeatureSelectionMethod(getFeatureSelectionMethod());
      return datasetTransformerTaskData;
   }

}
