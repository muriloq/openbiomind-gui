/**
 * CompletePipelineWizardPage1.java
 *
 * The file CompletePipelineWizardPage1.java.
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;

/**
 * The class CompletePipelineWizardPage1.
 *
 * @author bsanghvi
 * @since Jul 31, 2008
 * @version Aug 3, 2008
 */
public class CompletePipelineWizardPage1 extends AbstractTaskWizardPage implements IWizardPage {

   /**
    * The constant for page name (value = <code>openbiomind.gui.wizards.CompletePipelineWizardPage1</code>).
    */
   public static final String PAGE_NAME = "openbiomind.gui.wizards.CompletePipelineWizardPage1"; //$NON-NLS-1$

   /** The number of columns in various groups. */
   private static final int NUM_COLUMN_IN_GROUP = 3;

   /** The original dataset text button composite. */
   private TextButtonComposite originalDatasetTBC = null;

   /** The output directory text button composite. */
   private TextButtonComposite outputDirectoryTBC = null;

   /** The test dataset text button composite. */
   private TextButtonComposite testDatasetTBC = null;

   /** The property file text button composite. */
   private TextButtonComposite propertyFileTBC = null;

   /**
    * Instantiates a new dataset transformer wizard page.
    *
    * @param pageTitle the page title
    * @param pageDescription the page description
    */
   public CompletePipelineWizardPage1(final String pageTitle, final String pageDescription) {
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

      // Original dataset
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_SrcData, Messages.Tip_SrcData, true);
      this.originalDatasetTBC = createOriginalDatasetTBC(parent);

      // Output directory
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_OutDir, Messages.Tip_OutDir, true);
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
      textButtonComposite.setToolTipText(Messages.Tip_OutDir);

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

      // Test dataset
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_TestData);
      this.testDatasetTBC = createNewOptionalFileTextButtonComposite(parent, NUM_COLUMN_IN_GROUP - 1);

      // Alternate property file
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_PropFile, Messages.Tip_PropFile);
      this.propertyFileTBC = createNewPropertyFileTextButtonComposite(parent);
   }

   /**
    * Creates the new property file text button composite.
    *
    * @param parent the parent
    *
    * @return the text button composite
    */
   protected TextButtonComposite createNewPropertyFileTextButtonComposite(final Composite parent) {
      final TextButtonComposite textButtonComposite = new TextButtonComposite(parent) {

         private FileDialog fileDialog = null;

         @Override
         protected String buttonSelected() {
            return getFileDialog().open();
         }

         private FileDialog getFileDialog() {
            if (this.fileDialog == null) {
               this.fileDialog = new FileDialog(getShell(), SWT.OPEN);
               this.fileDialog.setFilterExtensions(new String[] { WILDCARD_ANY + Resources.PROPERTIES_EXTENSION });
            }
            return this.fileDialog;
         }

      };
      textButtonComposite.setValid(true);
      textButtonComposite.setToolTipText(Messages.Tip_LeaveBlankOrSpecifyPropertiesFile);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(NUM_COLUMN_IN_GROUP - 1, 1).applyTo(textButtonComposite);

      // create decorations
      final ControlDecoration infoDecoration = WidgetHelper.createNewInformationDecoration(textButtonComposite
            .getTextField(), Messages.Tip_LeaveBlankOrSpecifyPropertiesFile);
      infoDecoration.setShowOnlyOnFocus(true);
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textButtonComposite,
            Messages.Err_FileNotExist);
      errorDecoration.hide();

      // apply listeners
      textButtonComposite.addModifyListenerOnTextField(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            final String fileName = textButtonComposite.getText();
            textButtonComposite.setValid(Utility.isEmptyOrExistingFile(fileName)
                  && fileName.endsWith(Resources.PROPERTIES_EXTENSION));
            if (textButtonComposite.isValid()) {
               errorDecoration.hide();
            } else {
               infoDecoration.hideHover();
               errorDecoration.show();
               errorDecoration.showHoverText(errorDecoration.getDescriptionText());
            }

            validatePage();
         }

      });

      return textButtonComposite;
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

   /**
    * Gets the property file.
    *
    * @return the property file
    */
   public String getPropertyFile() {
      return getPropertyFileTBC().getText();
   }

   /**
    * Gets the property file text button composite.
    *
    * @return the property file text button composite
    */
   private TextButtonComposite getPropertyFileTBC() {
      return this.propertyFileTBC;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#validatePage()
    */
   @Override
   protected void validatePage() {
      final boolean valid = isProjectInformationValid() && getOriginalDatasetTBC().isValid()
            && getOutputDirectoryTBC().isValid() && getTestDatasetTBC().isValid() && getPropertyFileTBC().isValid();
      setPageComplete(valid);
      if (!valid) {
         setErrorMessage(Messages.Err_FixErrToContinue);
      } else {
         setErrorMessage(null);
      }
   }

}
