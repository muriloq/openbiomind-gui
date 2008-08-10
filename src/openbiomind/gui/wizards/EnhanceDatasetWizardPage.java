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
import openbiomind.gui.data.EnhanceDatasetTaskData;
import openbiomind.gui.util.Utility;
import openbiomind.gui.util.WidgetHelper;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * The class EnhanceDatasetWizardPage.
 *
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Aug 10, 2008
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
    */
   public EnhanceDatasetWizardPage() {
      this(Messages.EnhDataWiz_Name, Messages.EnhDataWiz_Desc);
   }

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
      addSection(parent, Messages.Label_ReqdArg, NUM_COLUMN_IN_GROUP);

      // Source dataset
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_SrcData, Messages.Tip_SrcData, true);
      this.originalDatasetTBC = createOriginalDatasetTBC(parent);

      // Enhanced dataset
      // - leave a blank row
      WidgetHelper.createNewBlankLabel(parent, NUM_COLUMN_IN_GROUP);
      // - Detail row: Specify the enhanced dataset file
      WidgetHelper.createNewDetailsLabel(parent, Messages.Label_SpecifyEnhData, NUM_COLUMN_IN_GROUP);
      // - File name
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_File, Messages.Label_SpecifyEnhData, true);
      this.enhancedDatasetDestFileNameText = createEnhancedDatasetDestFileNameText(parent);
      WidgetHelper.createNewBlankLabel(parent);
      // - Destination directory
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_Dir, Messages.Tip_LeaveBlankToUseCurrDirOrSpecifyDir);
      this.enhancedDatasetDestDirTBC = createEnhancedDatasetDestDirTBC(parent);
      // - Path - read only
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_FilePath, Messages.Tip_Path);
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
            handleModifyText(textButtonComposite, errorDecoration, Utility.fileExists(textButtonComposite.getText()));
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
      text.setToolTipText(Messages.Label_SpecifyEnhData);
      setValidEnhancedDatasetDestFileName(false);

      // apply layout
      GUI.GRID_DATA_DEFAULT.applyTo(text);

      // create decorations
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(text, Messages.Err_InvalidFile);
      errorDecoration.show();

      // apply listeners
      text.addModifyListener(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            setValidEnhancedDatasetDestFileName(!Utility.isEmpty(text.getText()));
            handleErrorDecoration(errorDecoration, isValidEnhancedDatasetDestFileName());
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
      textButtonComposite.setToolTipText(Messages.Tip_LeaveBlankToUseCurrDirOrSpecifyDir);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(NUM_COLUMN_IN_GROUP - 1, 1).applyTo(textButtonComposite);

      // create decorations
      final Text textField = textButtonComposite.getTextField();
      final ControlDecoration infoDecoration = WidgetHelper.createNewInformationDecoration(textField,
            Messages.Tip_LeaveBlankToUseCurrDirOrSpecifyDir);
      infoDecoration.setShowOnlyOnFocus(true);
      final ControlDecoration warningDecoration = WidgetHelper.createNewWarningDecoration(textField,
            Messages.Warn_DirNotExist);
      warningDecoration.hide();
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textField,
            Messages.Err_InvalidDir);
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
    * Gets the enhanced dataset path text. This method uses {@link #getEnhancedDataset()}.
    *
    * @param parent the parent
    *
    * @return the enhanced dataset path text
    */
   private Text createEnhancedDatasetPathText(final Composite parent) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
      text.setToolTipText(Messages.Tip_Path);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(NUM_COLUMN_IN_GROUP - 1, 1).applyTo(text);

      // create decorations
      final ControlDecoration warningDecoration = WidgetHelper.createNewWarningDecoration(text,
            Messages.Warn_FileAlreadyExist);
      warningDecoration.hide();
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(text, Messages.Err_InvalidFile);
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
      addSection(parent, Messages.Label_OptionalArg, NUM_COLUMN_IN_GROUP);

      // Ontology description file
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_OntDescFile);
      this.ontologyDescriptionFileTBC = createNewOptionalFileTextButtonComposite(parent, NUM_COLUMN_IN_GROUP - 1);

      // Ontology association file
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_OntAssoFile);
      this.ontologyAssociationFileTBC = createNewOptionalFileTextButtonComposite(parent, NUM_COLUMN_IN_GROUP - 1);
   }

   /**
    * Gets the input dataset.
    *
    * @return the input dataset
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
         setErrorMessage(Messages.Err_FixErrToContinue);
      } else {
         setErrorMessage(null);
      }
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#prepareTaskData()
    */
   @Override
   public EnhanceDatasetTaskData prepareTaskData() {
      final EnhanceDatasetTaskData enhanceDatasetTaskData = new EnhanceDatasetTaskData();
      enhanceDatasetTaskData.setInputDataset(getInputDataset());
      enhanceDatasetTaskData.setEnhancedDataset(getEnhancedDataset());
      enhanceDatasetTaskData.setOntologyDescriptionFile(getOntologyDescriptionFile());
      enhanceDatasetTaskData.setOntologyAssociationFile(getOntologyAssociationFile());
      return enhanceDatasetTaskData;
   }

}
