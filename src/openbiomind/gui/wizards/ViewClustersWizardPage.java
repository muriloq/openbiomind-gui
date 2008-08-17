/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (ViewClustersWizardPage.java) is part of OpenBiomind-GUI.
 *
 *  OpenBiomind-GUI is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenBiomind-GUI is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenBiomind-GUI.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Please visit the following pages to contact the author(s):
 *  Homepage: http://code.google.com/p/openbiomind-gui/
 *  Mailing list: http://groups.google.com/group/openbiomind-gui/
 */

package openbiomind.gui.wizards;

import java.io.File;

import openbiomind.gui.common.TextButtonComposite;
import openbiomind.gui.data.ClusteringColorsEnum;
import openbiomind.gui.data.ViewClustersTaskData;
import openbiomind.gui.util.Utility;
import openbiomind.gui.util.WidgetHelper;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * The class ViewClustersWizardPage.
 * 
 * @author bsanghvi
 * @since Jul 20, 2008
 * @version Aug 18, 2008
 */
public class ViewClustersWizardPage extends AbstractTaskWizardPage implements IWizardPage {

   /** The constant for page name (value = <code>openbiomind.gui.wizards.ViewClustersWizardPage</code>). */
   public static final String PAGE_NAME = "openbiomind.gui.wizards.ViewClustersWizardPage"; //$NON-NLS-1$

   /** The number of columns in various groups. */
   private static final int NUM_COLUMN_IN_GROUP = 3;

   /** The base dataset text button composite. */
   private TextButtonComposite clusteringDatasetTBC = null;

   /** The meta task result directory text button composite. */
   private TextButtonComposite clusteringResultTBC = null;

   /** The image file destination file text. */
   private Text imageFileDestFileText = null;

   /** The valid image file destination file name. */
   private boolean validImageFileDestFileName = false;

   /** The image file destination extension combo. */
   private Combo imageFileDestExtCombo = null;

   /** The image file destination extension array. */
   private String[] imageFileDestExtArray = null;

   /** The image file destination directory text button composite. */
   private TextButtonComposite imageFileDestDirTBC = null;

   /** The image file path text. */
   private Text imageFilePathText = null;

   /** The valid image file path. */
   private boolean validImageFilePath = false;

   /** The target category combo. */
   private Combo clusteringColorsCombo = null;

   /**
    * Instantiates a new view clusters wizard page.
    */
   public ViewClustersWizardPage() {
      this(Messages.ViewClustWiz_Name, Messages.ViewClustWiz_Desc);
   }

   /**
    * Instantiates a new view clusters wizard page.
    * 
    * @param pageTitle the page title
    * @param pageDescription the page description
    */
   public ViewClustersWizardPage(final String pageTitle, final String pageDescription) {
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

      // Clustering dataset
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_ClustDataResult, Messages.Tip_ClustDataResult, true);
      this.clusteringDatasetTBC = createSelectFileTBC(parent, Messages.Tip_ClustDataResult);

      // Clustering result
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_ClustResult, Messages.Tip_ClustResult, true);
      this.clusteringResultTBC = createSelectFileTBC(parent, Messages.Tip_ClustResult);

      // Output file
      // - leave a blank row
      WidgetHelper.createNewBlankLabel(parent, NUM_COLUMN_IN_GROUP);
      // - Detail row: Specify the image file
      WidgetHelper.createNewDetailsLabel(parent, Messages.Label_SpecifyOutImgFile, NUM_COLUMN_IN_GROUP);
      // - File name
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_File, Messages.Tip_OutFile, true);
      this.imageFileDestFileText = createImageFileDestFileText(parent);
      this.imageFileDestExtCombo = createDefaultReadOnlyCombo(parent, getImageFileDestExtArray(), false);
      // presently only PNG format is supported, so this combo can be disabled
      if (getImageFileDestExtArray().length == 1) {
         getImageFileDestExtCombo().setEnabled(false);
      }
      // - Directory
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_Dir, Messages.Tip_LeaveBlankToUseCurrDirOrSpecifyDir);
      this.imageFileDestDirTBC = createImageFileDestDirTBC(parent);
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_FilePath, Messages.Tip_OutFilePath);
      this.imageFilePathText = createImageFilePathText(parent);
   }

   /**
    * Creates the select file text button composite.
    * 
    * @param parent the parent
    * @param tooltip the tool tip
    * 
    * @return the text button composite
    */
   private TextButtonComposite createSelectFileTBC(final Composite parent, final String tooltip) {
      final TextButtonComposite textButtonComposite = new TextButtonComposite(parent) {

         @Override
         protected String buttonSelected() {
            return getFileDialog().open();
         }

      };
      textButtonComposite.setValid(false);
      if (!Utility.isEmpty(tooltip)) {
         textButtonComposite.setToolTipText(tooltip);
      }

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
    * Creates the image file destination file text.
    * 
    * @param parent the parent
    * 
    * @return the text
    */
   private Text createImageFileDestFileText(final Composite parent) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
      text.setToolTipText(Messages.Tip_OutFile);
      setValidImageFileDestFileName(false);

      // apply layout
      GUI.GRID_DATA_DEFAULT.applyTo(text);

      // create decorations
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(text, Messages.Err_InvalidFile);
      errorDecoration.show();

      // apply listeners
      text.addModifyListener(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            setValidImageFileDestFileName(!Utility.isEmpty(text.getText()));
            handleErrorDecoration(errorDecoration, isValidImageFileDestFileName());
            getImageFilePathText().setText(getImageFile());
            validatePage();
         }

      });

      return text;
   }

   /**
    * Creates the image file destination directory text button composite.
    * 
    * @param parent the parent
    * 
    * @return the text button composite
    */
   private TextButtonComposite createImageFileDestDirTBC(final Composite parent) {
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

            getImageFilePathText().setText(getImageFile());

            validatePage();
         }

      });

      return textButtonComposite;
   }

   /**
    * Gets the image file path text. This method uses {@link #getImageFile()}.
    * 
    * @param parent the parent
    * 
    * @return the image file path text
    */
   private Text createImageFilePathText(final Composite parent) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
      text.setToolTipText(Messages.Tip_OutFilePath);

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
            final File file = new File(getImageFile());

            if (file.isDirectory()) {
               inError = true;
            } else if (file.exists()) {
               inWarning = true;
            }

            setValidImageFilePath(!inError);
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

      // Clustering colors
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_ClustColor, Messages.Tip_ClustColor);
      this.clusteringColorsCombo = createDefaultReadOnlyCombo(parent, getClusteringColorsArray());
      WidgetHelper.createNewBlankLabel(parent);
   }

   /**
    * Gets the clustering dataset.
    * 
    * @return the clustering dataset
    */
   public String getClusteringDataset() {
      return getClusteringDatasetTBC().getText();
   }

   /**
    * Gets the clustering dataset text button composite.
    * 
    * @return the clustering dataset text button composite
    */
   private TextButtonComposite getClusteringDatasetTBC() {
      return this.clusteringDatasetTBC;
   }

   /**
    * Gets the clustering result.
    * 
    * @return the clustering result
    */
   public String getClusteringResult() {
      return getClusteringResultTBC().getText();
   }

   /**
    * Gets the clustering result text button composite.
    * 
    * @return the clustering result text button composite
    */
   private TextButtonComposite getClusteringResultTBC() {
      return this.clusteringResultTBC;
   }

   /**
    * Gets the image file destination file.
    * 
    * @return the image file destination file
    */
   private String getImageFileDestFileName() {
      return getImageFileDestFileText().getText();
   }

   /**
    * Gets the image file destination file text.
    * 
    * @return the image file destination file text
    */
   private Text getImageFileDestFileText() {
      return this.imageFileDestFileText;
   }

   /**
    * Checks if is valid image file destination file name.
    * 
    * @return true, if is valid image file destination file name
    */
   private boolean isValidImageFileDestFileName() {
      return this.validImageFileDestFileName;
   }

   /**
    * Sets the valid image file destination file name.
    * 
    * @param validImageFileDestFileName the new valid image file destination file name
    */
   private void setValidImageFileDestFileName(final boolean validImageFileDestFileName) {
      this.validImageFileDestFileName = validImageFileDestFileName;
   }

   /**
    * Gets the image file destination directory path.
    * 
    * @return the image file destination directory
    */
   private String getImageFileDestDirPath() {
      return getImageFileDestDirTBC().getText();
   }

   /**
    * Gets the image file destination extension.
    * 
    * @return the image file destination extension
    */
   private String getImageFileDestFileExt() {
      return getImageFileDestExtCombo().getText();
   }

   /**
    * Gets the image file destination extension combo.
    * 
    * @return the image file destination extension combo
    */
   private Combo getImageFileDestExtCombo() {
      return this.imageFileDestExtCombo;
   }

   /**
    * Gets the image file destination extension array.
    * 
    * @return the image file destination extension array
    */
   private String[] getImageFileDestExtArray() {
      if (this.imageFileDestExtArray == null) {
         this.imageFileDestExtArray = new String[] { Resources.PNG_EXTENSION };
      }

      return this.imageFileDestExtArray;
   }

   /**
    * Gets the image file destination directory text button composite.
    * 
    * @return the image file destination directory text button composite
    */
   private TextButtonComposite getImageFileDestDirTBC() {
      return this.imageFileDestDirTBC;
   }

   /**
    * Gets the image file.
    * 
    * @return the image file
    */
   public String getImageFile() {
      final String directoryPath = getImageFileDestDirPath();
      final String fileName = getImageFileDestFileName() + getImageFileDestFileExt();
      if (!Utility.exists(directoryPath)) {
         return Properties.CURRENT_DIRECTORY + File.separator + fileName;
      } else if (directoryPath.endsWith(File.separator)) {
         return directoryPath + fileName;
      } else {
         return directoryPath + File.separator + fileName;
      }
   }

   /**
    * Gets the image file path text.
    * 
    * @return the image file path text
    */
   private Text getImageFilePathText() {
      return this.imageFilePathText;
   }

   /**
    * Checks if is valid image file path.
    * 
    * @return true, if is valid image file path
    */
   private boolean isValidImageFilePath() {
      return this.validImageFilePath;
   }

   /**
    * Sets the valid image file path.
    * 
    * @param validImageFilePath the new valid image file path
    */
   private void setValidImageFilePath(final boolean validImageFilePath) {
      this.validImageFilePath = validImageFilePath;
   }

   /**
    * Gets the clustering colors.
    * 
    * @return the clustering colors
    */
   public ClusteringColorsEnum getClusteringColors() {
      return ClusteringColorsEnum.parse(getClusteringColorsCombo().getText());
   }

   /**
    * Gets the clustering colors combo.
    * 
    * @return the clustering colors combo
    */
   private Combo getClusteringColorsCombo() {
      return this.clusteringColorsCombo;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#setVisible(boolean)
    */
   @Override
   public void setVisible(final boolean visible) {
      if (visible) {
         final IWizardPage previousPage1 = getPreviousPage();
         if (previousPage1 instanceof ClusterizeWizardPage) {
            disableComponent(getClusteringResultTBC(), ((ClusterizeWizardPage) previousPage1).getOutputFile());

            final IWizardPage previousPage2 = previousPage1.getPreviousPage();
            if (previousPage2 instanceof ClusteringTransformerWizardPage) {
               disableComponent(getClusteringDatasetTBC(), ((ClusteringTransformerWizardPage) previousPage2)
                     .getDatasetFile());
            }
         } else if (previousPage1 instanceof ClusteringTransformerWizardPage) {
            disableComponent(getClusteringDatasetTBC(), ((ClusteringTransformerWizardPage) previousPage1)
                  .getDatasetFile());
         }
      }

      super.setVisible(visible);
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#validatePage()
    */
   @Override
   protected void validatePage() {
      final boolean valid = isProjectInformationValid() && getClusteringDatasetTBC().isValid()
            && getClusteringResultTBC().isValid() && isValidImageFileDestFileName()
            && getImageFileDestDirTBC().isValid() && isValidImageFilePath();
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
   public ViewClustersTaskData prepareTaskData() {
      final ViewClustersTaskData viewClustersTaskData = new ViewClustersTaskData();
      viewClustersTaskData.setClusteringDataset(getClusteringDataset());
      viewClustersTaskData.setClusteringResult(getClusteringResult());
      viewClustersTaskData.setImageFile(getImageFile());
      viewClustersTaskData.setClusteringColors(getClusteringColors());
      return viewClustersTaskData;
   }

}
