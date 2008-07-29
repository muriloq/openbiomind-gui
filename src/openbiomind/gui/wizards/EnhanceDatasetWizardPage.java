/**
 * EnhanceDatasetWizardPage.java
 *
 * The file EnhanceDatasetWizardPage.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import java.io.File;

import openbiomind.gui.common.TextButtonComposite;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * The class EnhanceDatasetWizardPage.
 *
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Jul 28, 2008
 */
public class EnhanceDatasetWizardPage extends AbstractTaskWizardPage implements IWizardPage {

   /**
    * The constant for page name (value = <code>openbiomind.gui.wizards.EnhanceDatasetWizardPage</code>).
    */
   public static final String PAGE_NAME = "openbiomind.gui.wizards.EnhanceDatasetWizardPage"; //$NON-NLS-1$

   /** The number of columns in various groups. */
   private static final int NUM_COLUMN_IN_GROUP = 3;

   /** The original dataset text button composite. */
   private TextButtonComposite originalDatasetTBC = null;

   /** The enhanced dataset destination file name text. */
   private Text enhancedDatasetDestFileNameText = null;

   /** The valid enhanced dataset destination file name. */
   private boolean validEnhancedDatasetDestFileName = false;

   /** The enhanced dataset destination directory text button composite. */
   private TextButtonComposite enhancedDatasetDestDirTBC = null;

   /** The use original dataset directory for enhanced dataset. */
   private Button useOriginalDatasetDirButton = null;

   /** The enhanced dataset path text. */
   private Text enhancedDatasetPathText = null;

   /** The ontology description file text button composite. */
   private TextButtonComposite ontologyDescriptionFileTBC = null;

   /** The ontology association file text button composite. */
   private TextButtonComposite ontologyAssociationFileTBC = null;

   /** The valid enhanced dataset file path. */
   private boolean validEnhancedDatasetFilePath = false;

   /**
    * Instantiates a new enhance dataset wizard page.
    *
    * @param pageTitle the page title
    * @param pageDescription the page description
    */
   public EnhanceDatasetWizardPage(final String pageTitle, final String pageDescription) {
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
      addSection(parent, Messages.GroupLabel_RequiredArguments, NUM_COLUMN_IN_GROUP);

      // Original dataset
      WidgetHelper.createNewFieldLabel(parent, Messages.EnhanceDatasetWizardPage_Label_OriginalDataset,
            Messages.Detail_OriginalDataset, true);
      this.originalDatasetTBC = createOriginalDatasetTBC(parent);

      // Enhanced dataset
      // - leave a blank row
      WidgetHelper.createNewBlankLabel(parent, NUM_COLUMN_IN_GROUP);
      // - Detail row: Specify the enhanced dataset file
      WidgetHelper.createNewDetailsLabel(parent, Messages.EnhanceDatasetWizardPage_Detail_EnhancedDataset,
            NUM_COLUMN_IN_GROUP);
      // - File name
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_DestinationFile,
            Messages.EnhanceDatasetWizardPage_Detail_EnhancedDataset, true);
      this.enhancedDatasetDestFileNameText = createEnhancedDatasetDestFileNameText(parent);
      WidgetHelper.createNewBlankLabel(parent);
      // - Destination directory
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_DestinationDir,
            "Leave blank to use current directory or specify an existing directory");
      this.enhancedDatasetDestDirTBC = createEnhancedDatasetDestDirTBC(parent);
      // - Use original dataset directory check box
      WidgetHelper.createNewBlankLabel(parent);
      this.useOriginalDatasetDirButton = createUseOriginalDatasetDirButton(parent);
      // - Enhanced dataset path - read only
      WidgetHelper.createNewFieldLabel(parent, Messages.EnhanceDatasetWizardPage_Label_EnhancedDatasetPath,
            Messages.EnhanceDatasetWizardPage_Tip_EnhancedDataset);
      this.enhancedDatasetPathText = createEnhancedDatasetPathText(parent);
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
      textButtonComposite.setToolTipText(Messages.Detail_OriginalDataset);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(NUM_COLUMN_IN_GROUP - 1, 1).applyTo(textButtonComposite);

      // create decorations
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textButtonComposite,
            "Please specify an existing file");
      errorDecoration.show();

      // apply listeners
      textButtonComposite.addModifyListenerOnTextField(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            textButtonComposite.setValid(Utility.fileExists(textButtonComposite.getText()));
            if (textButtonComposite.isValid()) {
               errorDecoration.hide();
            } else {
               errorDecoration.show();
               errorDecoration.showHoverText(errorDecoration.getDescriptionText());
            }

            if (useOriginalDatasetDir()) {
               updateEnhancedDatasetDestDirText();
            }

            validatePage();
         }

      });

      return textButtonComposite;
   }

   /**
    * Creates the enhanced dataset destination file name text.
    *
    * @param parent the parent
    *
    * @return the text
    */
   private Text createEnhancedDatasetDestFileNameText(final Composite parent) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
      text.setToolTipText(Messages.EnhanceDatasetWizardPage_Detail_EnhancedDataset);
      setValidEnhancedDatasetDestFileName(false);

      // apply layout
      GUI.GRID_DATA_DEFAULT.applyTo(text);

      // create decorations
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(text, "Invalid file");
      errorDecoration.show();

      // apply listeners
      text.addModifyListener(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            setValidEnhancedDatasetDestFileName(!Utility.isEmpty(text.getText()));
            if (isValidEnhancedDatasetDestFileName()) {
               errorDecoration.hide();
            } else {
               errorDecoration.show();
               errorDecoration.showHoverText(errorDecoration.getDescriptionText());
            }

            getEnhancedDatasetPathText().setText(getEnhancedDataset());

            validatePage();
         }

      });

      return text;
   }

   /**
    * Creates the enhanced dataset destination directory text button composite.
    *
    * @param parent the parent
    *
    * @return the text button composite
    */
   private TextButtonComposite createEnhancedDatasetDestDirTBC(final Composite parent) {
      final TextButtonComposite textButtonComposite = new TextButtonComposite(parent) {

         @Override
         protected String buttonSelected() {
            return getDirectoryDialog().open();
         }

      };
      textButtonComposite.setText(Properties.CURRENT_DIRECTORY);
      textButtonComposite.setValid(true);
      textButtonComposite.setToolTipText("Leave blank to use current directory or specify an existing directory");

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(NUM_COLUMN_IN_GROUP - 1, 1).applyTo(textButtonComposite);

      // create decorations
      final Text textField = textButtonComposite.getTextField();
      final ControlDecoration infoDecoration = WidgetHelper.createNewInformationDecoration(textField,
            "Leave blank to use current directory or specify an existing directory");
      infoDecoration.setShowOnlyOnFocus(true);
      final ControlDecoration warningDecoration = WidgetHelper.createNewWarningDecoration(textField,
            "Specified directory does not exist and will be automatically created");
      warningDecoration.hide();
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textField, "Invalid directory");
      errorDecoration.hide();

      // apply listeners
      textButtonComposite.addModifyListenerOnTextField(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            boolean inError = false;
            boolean inWarning = false;
            final String directoryPath = textButtonComposite.getText();

            if (!Utility.isEmpty(directoryPath)) {
               final File directory = new File(directoryPath);
               if (directory.isFile()) {
                  inError = true;
               } else if (!directory.exists()) {
                  inWarning = true;
               }
            }

            textButtonComposite.setValid(!inError);
            showErrorOrWarning(inError, errorDecoration, inWarning, warningDecoration, infoDecoration);

            getEnhancedDatasetPathText().setText(getEnhancedDataset());

            validatePage();
         }

      });

      return textButtonComposite;
   }

   /**
    * Creates the use original dataset directory for enhanced dataset.
    *
    * @param parent the parent
    *
    * @return the button
    */
   private Button createUseOriginalDatasetDirButton(final Composite parent) {
      final Button button = new Button(parent, SWT.CHECK);
      button.setText(Messages.EnhanceDatasetWizardPage_UseOriginalDatasetDir);
      button.setToolTipText(Messages.EnhanceDatasetWizardPage_UseOriginalDatasetDir);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(NUM_COLUMN_IN_GROUP - 1, 1).applyTo(button);

      // apply listeners
      button.addSelectionListener(new SelectionAdapter() {

         @Override
         public void widgetSelected(final SelectionEvent event) {
            if (button.getSelection()) {
               getEnhancedDatasetDestDirTBC().setEnabled(false);
               updateEnhancedDatasetDestDirText();
            } else {
               getEnhancedDatasetDestDirTBC().setEnabled(true);
            }
         }

      });

      return button;
   }

   /**
    * Gets the enhanced dataset path text. This method uses {@link #getEnhancedDataset()}.
    *
    * @param parent the parent
    *
    * @return the enhanced dataset path text
    */
   private Text createEnhancedDatasetPathText(final Composite parent) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
      text.setToolTipText(Messages.EnhanceDatasetWizardPage_Tip_EnhancedDataset);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(NUM_COLUMN_IN_GROUP - 1, 1).applyTo(text);

      // create decorations
      final ControlDecoration warningDecoration = WidgetHelper.createNewWarningDecoration(text,
            "File already exists and would be overwritten");
      warningDecoration.hide();
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(text, "Invalid file");
      errorDecoration.hide();

      // apply listeners
      text.addModifyListener(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            boolean inError = false;
            boolean inWarning = false;
            final File file = new File(getEnhancedDataset());

            if (file.isDirectory()) {
               inError = true;
            } else if (file.exists()) {
               inWarning = true;
            }

            setValidEnhancedDatasetFilePath(!inError);
            showErrorOrWarning(inError, errorDecoration, inWarning, warningDecoration);
            validatePage();
         }

      });

      return text;
   }

   /**
    * Creates the optional group.
    *
    * @param parent the parent
    */
   private void createOptionalGroup(final Composite parent) {
      // Optional Arguments
      addSection(parent, Messages.GroupLabel_OptionalArguments, NUM_COLUMN_IN_GROUP);

      // Ontology description file
      WidgetHelper.createNewFieldLabel(parent, Messages.EnhanceDatasetWizardPage_Label_DescriptionFile,
            Messages.EnhanceDatasetWizardPage_Detail_OntologyDesc);
      this.ontologyDescriptionFileTBC = createNewOptionalFileTextButtonComposite(parent, NUM_COLUMN_IN_GROUP - 1);

      // Ontology association file
      WidgetHelper.createNewFieldLabel(parent, Messages.EnhanceDatasetWizardPage_Label_AssociationFile,
            Messages.EnhanceDatasetWizardPage_Detail_AssociationDesc);
      this.ontologyAssociationFileTBC = createNewOptionalFileTextButtonComposite(parent, NUM_COLUMN_IN_GROUP - 1);
   }

   /**
    * Gets the original dataset.
    *
    * @return the original dataset
    */
   public String getOriginalDataset() {
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
    * Gets the enhanced dataset destination file name.
    *
    * @return the enhanced dataset destination file name
    */
   private String getEnhancedDatasetDestFileName() {
      return getEnhancedDatasetDestFileNameText().getText();
   }

   /**
    * Gets the enhanced dataset destination file name text.
    *
    * @return the enhanced dataset destination file name text
    */
   private Text getEnhancedDatasetDestFileNameText() {
      return this.enhancedDatasetDestFileNameText;
   }

   /**
    * Checks if is valid enhanced dataset destination file name.
    *
    * @return true, if checks if is valid enhanced dataset destination file name
    */
   private boolean isValidEnhancedDatasetDestFileName() {
      return this.validEnhancedDatasetDestFileName;
   }

   /**
    * Sets the valid enhanced dataset destination file name.
    *
    * @param validEnhancedDatasetDestFileName the valid enhanced dataset destination file name
    */
   private void setValidEnhancedDatasetDestFileName(final boolean validEnhancedDatasetDestFileName) {
      this.validEnhancedDatasetDestFileName = validEnhancedDatasetDestFileName;
   }

   /**
    * Gets the enhanced dataset destination directory path.
    *
    * @return the enhanced dataset destination directory
    */
   private String getEnhancedDatasetDestDirPath() {
      return getEnhancedDatasetDestDirTBC().getText();
   }

   /**
    * Gets the enhanced dataset destination directory text button composite.
    *
    * @return the enhanced dataset destination directory text button composite
    */
   private TextButtonComposite getEnhancedDatasetDestDirTBC() {
      return this.enhancedDatasetDestDirTBC;
   }

   /**
    * Update enhanced dataset destination directory text.
    */
   private void updateEnhancedDatasetDestDirText() {
      getEnhancedDatasetDestDirTBC().setText(Utility.extractDirectory(getOriginalDataset()));
   }

   /**
    * Use original dataset directory for enhanced dataset.
    *
    * @return true, if successful
    */
   private boolean useOriginalDatasetDir() {
      return getUseOriginalDatasetDirButton().getSelection();
   }

   /**
    * Gets the use original dataset directory for enhanced dataset.
    *
    * @return the use original dataset directory for enhanced dataset
    */
   private Button getUseOriginalDatasetDirButton() {
      return this.useOriginalDatasetDirButton;
   }

   /**
    * Gets the enhanced dataset.
    *
    * @return the enhanced dataset
    */
   public String getEnhancedDataset() {
      final String directoryPath = getEnhancedDatasetDestDirPath();
      final String fileName = getEnhancedDatasetDestFileName();
      if (!Utility.exists(directoryPath)) {
         return Properties.CURRENT_DIRECTORY + File.separator + fileName;
      } else if (directoryPath.endsWith(File.separator)) {
         return directoryPath + fileName;
      } else {
         return directoryPath + File.separator + fileName;
      }
   }

   /**
    * Gets the enhanced dataset file path text.
    *
    * @return the enhancedDatasetPathText
    */
   private Text getEnhancedDatasetPathText() {
      return this.enhancedDatasetPathText;
   }

   /**
    * Checks if is valid enhanced dataset file path.
    *
    * @return the validEnhancedDatasetFilePath
    */
   private boolean isValidEnhancedDatasetFilePath() {
      return this.validEnhancedDatasetFilePath;
   }

   /**
    * Sets the valid enhanced dataset file path.
    *
    * @param validEnhancedDatasetFilePath the validEnhancedDatasetFilePath to set
    */
   private void setValidEnhancedDatasetFilePath(final boolean validEnhancedDatasetFilePath) {
      this.validEnhancedDatasetFilePath = validEnhancedDatasetFilePath;
   }

   /**
    * Gets the ontology description file.
    *
    * @return the ontology description file
    */
   public String getOntologyDescriptionFile() {
      return getOntologyDescriptionFileTBC().getText();
   }

   /**
    * Gets the ontology description file text button composite.
    *
    * @return the ontology description file text button composite
    */
   private TextButtonComposite getOntologyDescriptionFileTBC() {
      return this.ontologyDescriptionFileTBC;
   }

   /**
    * Gets the ontology association file.
    *
    * @return the ontology association file
    */
   public String getOntologyAssociationFile() {
      return getOntologyAssociationFileTBC().getText();
   }

   /**
    * Gets the ontology association file text button composite.
    *
    * @return the ontology association file text button composite
    */
   private TextButtonComposite getOntologyAssociationFileTBC() {
      return this.ontologyAssociationFileTBC;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#validatePage()
    */
   @Override
   protected void validatePage() {
      final boolean valid = isProjectInformationValid() && getOriginalDatasetTBC().isValid()
            && isValidEnhancedDatasetDestFileName() && getEnhancedDatasetDestDirTBC().isValid()
            && isValidEnhancedDatasetFilePath() && getOntologyDescriptionFileTBC().isValid()
            && getOntologyAssociationFileTBC().isValid();
      setPageComplete(valid);
      if (!valid) {
         setErrorMessage("Fix the errors to continue");
      } else {
         setErrorMessage(null);
      }
   }

}
