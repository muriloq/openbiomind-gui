/**
 * EnhanceDatasetWizardPage1.java
 *
 * The file EnhanceDatasetWizardPage1.java.
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
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
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
   private final int NUM_COLUMN_IN_GROUP = 2;

   /** The original dataset text button composite. */
   private TextButtonComposite originalDatasetTBC = null;

   /** The enhanced dataset destination file text. */
   private Text enhancedDatasetDestFileText = null;

   /** The enhanced dataset destination directory text button composite. */
   private TextButtonComposite enhancedDatasetDestDirTBC = null;

   /** The use original dataset directory for enhanced dataset. */
   private Button useOriginalDatasetDir = null;

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
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#getBaseContainer()
    */
   @Override
   protected Composite createArgumentsComposite(final Composite parent) {
      final Composite composite = new Composite(parent, SWT.NULL);

      // apply layout
      GridLayoutFactory.swtDefaults().numColumns(1).applyTo(composite);

      // add components
      createRequiredGroup(composite);
      createOptionalGroup(composite);

      return composite;
   }

   /**
    * Creates the required group.
    *
    * @param parent the parent
    *
    * @return the group
    */
   private Group createRequiredGroup(final Composite parent) {
      final Group group = new Group(parent, SWT.SHADOW_ETCHED_IN);
      group.setText(WizardMessages.GroupLabel_RequiredArguments);

      // apply layout
      GUI.WIZARD_GROUP_GRID_DATA.applyTo(group);
      GUI.WIZARD_GROUP_GRID_LAYOUT.numColumns(this.NUM_COLUMN_IN_GROUP).applyTo(group);

      // add components
      WidgetHelper.createNewInformationLabel(group, WizardMessages.Detail_OriginalDataset, this.NUM_COLUMN_IN_GROUP);
      WidgetHelper.createNewComponentLabel(group, WizardMessages.Label_OriginalDataset, WizardMessages.Detail_OriginalDataset, true);
      this.originalDatasetTBC = createOriginalDatasetTBC(group);
      WidgetHelper.createNewSeparator(group, SWT.HORIZONTAL, this.NUM_COLUMN_IN_GROUP);
      WidgetHelper.createNewInformationLabel(group, WizardMessages.EnhanceDatasetWizardPage_Detail_EnhancedDataset, this.NUM_COLUMN_IN_GROUP);
      WidgetHelper.createNewComponentLabel(group, WizardMessages.EnhanceDatasetWizardPage_Label_DestinationFile, WizardMessages.EnhanceDatasetWizardPage_Label_EnhancedDataset, true);
      this.enhancedDatasetDestFileText = createEnhancedDatasetDestFileText(group);
      WidgetHelper
            .createNewComponentLabel(group, WizardMessages.EnhanceDatasetWizardPage_Label_DestinationDirectory, "Leave blank to use current directory or specify an existing directory"); //$NON-NLS-1$
      this.enhancedDatasetDestDirTBC = createEnhancedDatasetDestDirTBC(group);
      WidgetHelper.createNewBlankComponentLabel(group);
      this.useOriginalDatasetDir = createUseOriginalDatasetDir(group);
      WidgetHelper.createNewComponentLabel(group, WizardMessages.EnhanceDatasetWizardPage_Label_EnhancedDatasetPath, WizardMessages.EnhanceDatasetWizardPage_Tip_EnhancedDataset);
      this.enhancedDatasetFilePathText = createEnhancedDatasetFilePathText(group);

      return group;
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

      // apply layout
      GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(textButtonComposite);

      // create decorations
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textButtonComposite,
            CommonMessages.Error_FileNotExist);
      errorDecoration.show();

      // apply listeners
      textButtonComposite.addModifyListener(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent e) {
            textButtonComposite.setValid(Utility.fileExists(getOriginalDatasetFilePath()));
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
    * Gets the enhanced dataset file path text.
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
      GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(text);

      // create decorations
      final ControlDecoration warningDecoration = WidgetHelper.createNewWarningDecoration(text,
            CommonMessages.Warn_FileAlreadyExists);
      warningDecoration.hide();
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(text, CommonMessages.Error_InvalidFile);
      errorDecoration.show();

      // apply listeners
      text.addModifyListener(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent e) {
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
      textButtonComposite.setToolTipText(CommonMessages.Info_DestinationDirectory);

      // apply layout
      GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, false).applyTo(textButtonComposite);

      // create decorations
      final Text textField = textButtonComposite.getTextField();
      WidgetHelper.createNewInformationDecoration(textField, CommonMessages.Info_DestinationDirectory).show();
      final ControlDecoration warningDecoration = WidgetHelper.createNewWarningDecoration(textField,
            CommonMessages.Warn_DirectoryNotExist);
      warningDecoration.hide();
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textField,
            CommonMessages.Error_InvalidDirectory);
      errorDecoration.hide();

      // apply listeners
      textButtonComposite.addModifyListener(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent e) {
            boolean inError = false;
            boolean inWarning = false;
            final String directoryPath = getEnhancedDatasetDestDirPath();

            if (!Utility.isEmpty(directoryPath)) {
               final File directory = new File(directoryPath);
               if (directory.isFile()) {
                  inError = true;
               } else if (!directory.exists()) {
                  inWarning = true;
               }
            }

            textButtonComposite.setValid(!inError);
            showErrorOrWarning(inError, errorDecoration, inWarning, warningDecoration);

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
   private Button createUseOriginalDatasetDir(final Composite parent) {
      final Button button = new Button(parent, SWT.CHECK);
      button.setText(WizardMessages.EnhanceDatasetWizardPage_UseOriginalDatasetDir);

      // apply layout
      GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).align(SWT.FILL, SWT.CENTER).grab(true, false)
            .applyTo(button);

      // apply listeners
      button.addSelectionListener(new SelectionAdapter() {

         @Override
         public void widgetSelected(final SelectionEvent e) {
            if (useOriginalDatasetDir()) {
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
    * Creates the enhanced dataset destination file text.
    *
    * @param parent the parent
    *
    * @return the text
    */
   private Text createEnhancedDatasetDestFileText(final Composite parent) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
      setValidEnhancedDatasetFileName(false);

      // apply layout
      GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(text);

      // create decorations
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(text, CommonMessages.Error_InvalidFile);
      errorDecoration.show();

      // apply listeners
      text.addModifyListener(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent e) {
            setValidEnhancedDatasetFileName(!Utility.isEmpty(getEnhancedDatasetDestFileName()));
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
    * Creates the optional group.
    *
    * @param parent the parent
    *
    * @return the group
    */
   private Group createOptionalGroup(final Composite parent) {
      final Group group = new Group(parent, SWT.SHADOW_ETCHED_IN);
      group.setText(WizardMessages.GroupLabel_OptionalArguments);

      // apply layout
      GUI.WIZARD_GROUP_GRID_DATA.applyTo(group);
      GUI.WIZARD_GROUP_GRID_LAYOUT.numColumns(this.NUM_COLUMN_IN_GROUP).applyTo(group);

      // add components
      WidgetHelper.createNewInformationLabel(group, WizardMessages.EnhanceDatasetWizardPage_Detail_OntologyDescription, this.NUM_COLUMN_IN_GROUP);
      WidgetHelper.createNewComponentLabel(group, WizardMessages.EnhanceDatasetWizardPage_Label_DescriptionFile, WizardMessages.EnhanceDatasetWizardPage_Detail_OntologyDescription);
      this.ontologyDescriptionFileTBC = createNewOptionalFileTextButtonComposite(group);
      WidgetHelper.createNewSeparator(group, SWT.HORIZONTAL, this.NUM_COLUMN_IN_GROUP);
      WidgetHelper.createNewInformationLabel(group, WizardMessages.EnhanceDatasetWizardPage_Detail_AssociationDescription, this.NUM_COLUMN_IN_GROUP);
      WidgetHelper.createNewComponentLabel(group, WizardMessages.EnhanceDatasetWizardPage_Label_AssociationFile, WizardMessages.EnhanceDatasetWizardPage_Detail_AssociationDescription);
      this.ontologyAssociationFileTBC = createNewOptionalFileTextButtonComposite(group);

      return group;
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

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#validatePage()
    */
   @Override
   protected void validatePage() {
      final boolean valid = getOriginalDatasetTBC().isValid() && getEnhancedDatasetDestDirTBC().isValid()
            && isValidEnhancedDatasetFileName() && isValidEnhancedDatasetFilePath()
            && getOntologyDescriptionFileTBC().isValid() && getOntologyAssociationFileTBC().isValid();
      setPageComplete(valid);
      if (!valid) {
         setErrorMessage(CommonMessages.Error_FixToContinue);
      } else {
         setErrorMessage(null);
      }
   }

   /**
    * Update enhanced dataset destination directory text.
    */
   private void updateEnhancedDatasetDestDirText() {
      getEnhancedDatasetDestDirTBC().setText(Utility.extractDirectoryName(getOriginalDatasetFilePath()));
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
      return getUseOriginalDatasetDir().getSelection();
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
   private Button getUseOriginalDatasetDir() {
      return this.useOriginalDatasetDir;
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

   /**
    * Show error or warning.
    *
    * @param errorDecoration the error decoration
    * @param warningDecoration the warning decoration
    * @param inError the in error
    * @param inWarning the in warning
    */
   private void showErrorOrWarning(final boolean inError, final ControlDecoration errorDecoration,
         final boolean inWarning, final ControlDecoration warningDecoration) {
      if (inError) {
         errorDecoration.show();
         errorDecoration.showHoverText(errorDecoration.getDescriptionText());
      } else {
         errorDecoration.hide();

         if (inWarning) {
            warningDecoration.show();
            warningDecoration.showHoverText(warningDecoration.getDescriptionText());
         } else {
            warningDecoration.hide();
         }
      }
   }

}
