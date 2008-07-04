/**
 * EnhanceDatasetWizardPage.java
 *
 * The file EnhanceDatasetWizardPage.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import java.io.File;

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
 * @version Jun 27, 2008
 */
public class EnhanceDatasetWizardPage extends AbstractTaskWizardPage implements IWizardPage {

   /**
    * The constant for page name (value = <code>openbiomind.gui.wizards.EnhanceDatasetWizardPage</code>).
    */
   public static final String PAGE_NAME = "openbiomind.gui.wizards.EnhanceDatasetWizardPage"; //$NON-NLS-1$

   /** The number of columns in various groups. */
   private static final int NUM_COLUMN_IN_GROUP = 2;

   /** The original dataset text button composite. */
   private TextButtonComposite originalDatasetTBC = null;

   /** The enhanced dataset destination file text. */
   private Text enhancedDatasetDestFileText = null;

   /** The enhanced dataset destination directory text button composite. */
   private TextButtonComposite enhancedDatasetDestDirTBC = null;

   /** The use original dataset directory for enhanced dataset. */
   private Button useOriginalDatasetDirButton = null;

   /** The enhanced dataset file path text. */
   private Text enhancedDatasetFilePathText = null;

   /** The ontology description file text button composite. */
   private TextButtonComposite ontologyDescriptionFileTBC = null;

   /** The ontology association file text button composite. */
   private TextButtonComposite ontologyAssociationFileTBC = null;

   /** The valid enhanced dataset file name. */
   private boolean validEnhancedDatasetFileName = false;

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
      WidgetHelper.createNewBlankLabel(parent, NUM_COLUMN_IN_GROUP);

      // Enhanced dataset
      WidgetHelper.createNewDetailsLabel(parent, WizardMessages.EnhanceDatasetWizardPage_Detail_EnhancedDataset,
            NUM_COLUMN_IN_GROUP);
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.EnhanceDatasetWizardPage_Label_DestinationFile,
            WizardMessages.EnhanceDatasetWizardPage_Detail_EnhancedDataset, true);
      this.enhancedDatasetDestFileText = createEnhancedDatasetDestFileText(parent);
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.EnhanceDatasetWizardPage_Label_DestinationDir,
            CommonMessages.Info_DestinationDir);
      this.enhancedDatasetDestDirTBC = createEnhancedDatasetDestDirTBC(parent);
      WidgetHelper.createNewBlankLabel(parent);
      this.useOriginalDatasetDirButton = createUseOriginalDatasetDirButton(parent);
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.EnhanceDatasetWizardPage_Label_EnhancedDatasetPath,
            WizardMessages.EnhanceDatasetWizardPage_Tip_EnhancedDataset);
      this.enhancedDatasetFilePathText = createEnhancedDatasetFilePathText(parent);
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
    * Creates the enhanced dataset destination file text.
    *
    * @param parent the parent
    *
    * @return the text
    */
   private Text createEnhancedDatasetDestFileText(final Composite parent) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
      text.setToolTipText(WizardMessages.EnhanceDatasetWizardPage_Detail_EnhancedDataset);
      setValidEnhancedDatasetFileName(false);

      // apply layout
      GUI.GRID_DATA_DEFAULT.applyTo(text);

      // create decorations
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(text,
            CommonMessages.Error_InvalidFile);
      errorDecoration.show();

      // apply listeners
      text.addModifyListener(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            setValidEnhancedDatasetFileName(!Utility.isEmpty(text.getText()));
            if (isValidEnhancedDatasetFileName()) {
               errorDecoration.hide();
            } else {
               errorDecoration.show();
               errorDecoration.showHoverText(errorDecoration.getDescriptionText());
            }

            getEnhancedDatasetFilePathText().setText(getEnhancedDatasetFilePath());

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
      textButtonComposite.setToolTipText(CommonMessages.Info_DestinationDir);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.applyTo(textButtonComposite);

      // create decorations
      final Text textField = textButtonComposite.getTextField();
      final ControlDecoration infoDecoration = WidgetHelper.createNewInformationDecoration(textField,
            CommonMessages.Info_DestinationDir);
      infoDecoration.hide();
      final ControlDecoration warningDecoration = WidgetHelper.createNewWarningDecoration(textField,
            CommonMessages.Warn_DirNotExist);
      warningDecoration.hide();
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textField,
            CommonMessages.Error_InvalidDir);
      errorDecoration.hide();

      // apply listeners
      textButtonComposite.addFocusListenerOnTextField(new FocusListener() {

         @Override
         public void focusGained(final FocusEvent event) {
            infoDecoration.show();
            infoDecoration.showHoverText(infoDecoration.getDescriptionText());
         }

         @Override
         public void focusLost(final FocusEvent event) {
            infoDecoration.hide();
         }

      });

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

            getEnhancedDatasetFilePathText().setText(getEnhancedDatasetFilePath());

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
      button.setText(WizardMessages.EnhanceDatasetWizardPage_UseOriginalDatasetDir);
      button.setToolTipText(WizardMessages.EnhanceDatasetWizardPage_UseOriginalDatasetDir);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.applyTo(button);

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
    * Gets the enhanced dataset file path text. This method uses {@link #getEnhancedDatasetFilePath()}.
    *
    * @param parent the parent
    *
    * @return the enhanced dataset file path text
    */
   private Text createEnhancedDatasetFilePathText(final Composite parent) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
      text.setToolTipText(WizardMessages.EnhanceDatasetWizardPage_Tip_EnhancedDataset);
      setValidEnhancedDatasetFileName(false);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.applyTo(text);

      // create decorations
      final ControlDecoration warningDecoration = WidgetHelper.createNewWarningDecoration(text,
            CommonMessages.Warn_FileAlreadyExists);
      warningDecoration.hide();
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(text,
            CommonMessages.Error_InvalidFile);
      errorDecoration.hide();

      // apply listeners
      text.addModifyListener(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            boolean inError = false;
            boolean inWarning = false;
            final File file = new File(getEnhancedDatasetFilePath());

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
      addSection(parent, WizardMessages.GroupLabel_OptionalArguments, NUM_COLUMN_IN_GROUP);

      // Ontology description file
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.EnhanceDatasetWizardPage_Label_DescriptionFile,
            WizardMessages.EnhanceDatasetWizardPage_Detail_OntologyDesc);
      this.ontologyDescriptionFileTBC = createNewOptionalFileTextButtonComposite(parent);

      // Ontology association file
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.EnhanceDatasetWizardPage_Label_AssociationFile,
            WizardMessages.EnhanceDatasetWizardPage_Detail_AssociationDesc);
      this.ontologyAssociationFileTBC = createNewOptionalFileTextButtonComposite(parent);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#validatePage()
    */
   @Override
   protected void validatePage() {
      final boolean valid = isProjectInformationValid() && getOriginalDatasetTBC().isValid()
            && isValidEnhancedDatasetFileName() && getEnhancedDatasetDestDirTBC().isValid()
            && isValidEnhancedDatasetFilePath() && getOntologyDescriptionFileTBC().isValid()
            && getOntologyAssociationFileTBC().isValid();
      setPageComplete(valid);
      if (!valid) {
         setErrorMessage(CommonMessages.Error_FixToContinue);
      } else {
         setErrorMessage(null);
      }
   }

   /**
    * Checks if is valid enhanced dataset file name.
    *
    * @return the validEnhancedDataset
    */
   private boolean isValidEnhancedDatasetFileName() {
      return this.validEnhancedDatasetFileName;
   }

   /**
    * Sets the valid enhanced dataset file name.
    *
    * @param validEnhancedDatasetFileName the new valid enhanced dataset file name
    */
   private void setValidEnhancedDatasetFileName(final boolean validEnhancedDatasetFileName) {
      this.validEnhancedDatasetFileName = validEnhancedDatasetFileName;
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
    * Update enhanced dataset destination directory text.
    */
   private void updateEnhancedDatasetDestDirText() {
      getEnhancedDatasetDestDirTBC().setText(Utility.extractDirectory(getOriginalDatasetFilePath()));
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
    * Gets the enhanced dataset file path.
    *
    * @return the enhanced dataset file
    */
   public String getEnhancedDatasetFilePath() {
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
    * Gets the enhanced dataset destination directory path.
    *
    * @return the enhanced dataset destination directory
    */
   private String getEnhancedDatasetDestDirPath() {
      return getEnhancedDatasetDestDirTBC().getText();
   }

   /**
    * Gets the enhanced dataset destination file.
    *
    * @return the enhanced dataset destination file
    */
   private String getEnhancedDatasetDestFileName() {
      return getEnhancedDatasetDestFileText().getText();
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
    * Gets the ontology description file path.
    *
    * @return the ontology description file path
    */
   public String getOntologyDescriptionFilePath() {
      return getOntologyDescriptionFileTBC().getText();
   }

   /**
    * Gets the ontology association file path.
    *
    * @return the ontology association file path
    */
   public String getOntologyAssociationFilePath() {
      return getOntologyAssociationFileTBC().getText();
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
    * Gets the enhanced dataset file path text.
    *
    * @return the enhancedDatasetFilePathText
    */
   private Text getEnhancedDatasetFilePathText() {
      return this.enhancedDatasetFilePathText;
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
    * Gets the use original dataset directory for enhanced dataset.
    *
    * @return the use original dataset directory for enhanced dataset
    */
   private Button getUseOriginalDatasetDirButton() {
      return this.useOriginalDatasetDirButton;
   }

   /**
    * Gets the enhanced dataset destination file text.
    *
    * @return the enhanced dataset destination file text
    */
   private Text getEnhancedDatasetDestFileText() {
      return this.enhancedDatasetDestFileText;
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
    * Gets the ontology association file text button composite.
    *
    * @return the ontology association file text button composite
    */
   private TextButtonComposite getOntologyAssociationFileTBC() {
      return this.ontologyAssociationFileTBC;
   }

}
