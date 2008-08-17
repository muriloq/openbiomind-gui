/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (CompletePipelineWizardPage1.java) is part of OpenBiomind-GUI.
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
import openbiomind.gui.data.CompletePipelineTaskData;
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
 * The class CompletePipelineWizardPage1.
 * 
 * @author bsanghvi
 * @since Jul 31, 2008
 * @version Aug 18, 2008
 */
public class CompletePipelineWizardPage1 extends AbstractTaskWizardPage implements IWizardPage {

   /** The constant for page name (value = <code>openbiomind.gui.wizards.CompletePipelineWizardPage1</code>). */
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
    * Instantiates a new complete pipeline wizard page 1.
    */
   public CompletePipelineWizardPage1() {
      this(Messages.CompPipeWiz_P1_Name, Messages.CompPipeWiz_P1_Desc);
   }

   /**
    * Instantiates a new complete pipeline wizard page 1.
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
            handleModifyText(textButtonComposite, errorDecoration, Utility.fileExists(getOriginalDatasetFilePath()));
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
      this.propertyFileTBC = createNewOptionalFileTextButtonComposite(parent, NUM_COLUMN_IN_GROUP - 1);
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

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#prepareTaskData()
    */
   @Override
   public CompletePipelineTaskData prepareTaskData() {
      final CompletePipelineTaskData completePipelineTaskData = new CompletePipelineTaskData();
      completePipelineTaskData.setInputDataset(getOriginalDatasetFilePath());
      completePipelineTaskData.setOutputDirectory(getOutputDirectory());
      completePipelineTaskData.setTestDataset(getTestDataset());
      completePipelineTaskData.setPropertyFile(getPropertyFile());
      return completePipelineTaskData;
   }

}
