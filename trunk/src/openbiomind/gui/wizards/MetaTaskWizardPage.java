/**
 * MetaTaskWizardPage.java
 *
 * The file MetaTaskWizardPage.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import java.io.File;

import openbiomind.gui.tasks.MetaTaskTaskData.ClassificationMethodEnum;
import openbiomind.gui.util.CommonMessages;
import openbiomind.gui.util.Utility;
import openbiomind.gui.widgets.TextButtonComposite;
import openbiomind.gui.widgets.WidgetHelper;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
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
 * @version Jul 6, 2008
 */
public class MetaTaskWizardPage extends AbstractTaskWizardPage implements IWizardPage {

   /**
    * The constant for page name (value = <code>openbiomind.gui.wizards.DatasetTransformerWizardPage</code>).
    */
   public static final String PAGE_NAME = "openbiomind.gui.wizards.MetaTaskWizardPage"; //$NON-NLS-1$

   /** The number of columns in various groups. */
   private static final int NUM_COLUMN_IN_GROUP = 2;

   /** The dataset directory text button composite. */
   private TextButtonComposite datasetDirectoryTBC = null;

   /** The output directory text button composite. */
   private TextButtonComposite outputDirectoryTBC = null;

   /** The number of tasks. */
   private Text numberOfTasks = null;

   /** The target category text. */
   private Text targetCategoryText = null;

   /** The feature classification method combo. */
   private Combo classificationMethodCombo = null;

   /** The classification method array. */
   private String[] classificationMethodArray = null;

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
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.MetaTaskWizardPage_Label_DatasetDir,
            WizardMessages.MetaTaskWizardPage_Tip_DatasetDir, true);
      this.datasetDirectoryTBC = createDatasetDirectoryTBC(parent);

      // Output directory
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.Label_OutputDataset, WizardMessages.Detail_OutputDir,
            true);
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
      textButtonComposite
            .setToolTipText(WizardMessages.MetaTaskWizardPage_Tip_DatasetDir);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.applyTo(textButtonComposite);

      // create decorations
      // TODO Update to identify that the folder must contain train and test tab files
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textButtonComposite,
            CommonMessages.Error_InvalidDir);
      errorDecoration.show();

      // apply listeners
      textButtonComposite.addModifyListenerOnTextField(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            final File directory = new File(getDatasetDirectoryFilePath());
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

      // Number of tasks
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.MetaTaskWizardPage_Label_NumOfTasks, WizardMessages.MetaTaskWizardPage_Tip_NumOfTasks);
      this.numberOfTasks = createNewNumberOnlyText(parent);

      // Target category
      // TODO Find out if there is a list of category to choose from
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.Label_TargetCategory,
            WizardMessages.Detail_TargetCategory);
      this.targetCategoryText = createTargetCategoryText(parent);

      // Classification method
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.MetaTaskWizardPage_Label_ClassificationMethod);
      this.classificationMethodCombo = createDefaultCombo(parent, getClassificationMethodArray());
   }

   /**
    * Creates the target category text.
    *
    * @param parent the parent
    *
    * @return the text
    */
   private Text createTargetCategoryText(final Composite parent) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
      text.setToolTipText(CommonMessages.Info_LeaveBlank);

      // apply layout
      GUI.GRID_DATA_DEFAULT.applyTo(text);

      // create decorations
      final ControlDecoration infoDecoration = WidgetHelper.createNewInformationDecoration(text,
            CommonMessages.Info_LeaveBlank);
      infoDecoration.hide();

      // apply listeners
      text.addFocusListener(new FocusListener() {

         @Override
         public void focusGained(final FocusEvent event) {
            infoDecoration.show();
         }

         @Override
         public void focusLost(final FocusEvent event) {
            infoDecoration.hide();
         }

      });

      return text;
   }

   /**
    * Gets the dataset directory file path.
    *
    * @return the dataset directory file path
    */
   public String getDatasetDirectoryFilePath() {
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
         this.classificationMethodArray = new String[] { EMPTY, ClassificationMethodEnum.BOOLSIMPLE.toString(),
               ClassificationMethodEnum.BOOLCOMPLEX.toString(), ClassificationMethodEnum.CONVENTIONAL.toString() };
      }

      return this.classificationMethodArray;
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
