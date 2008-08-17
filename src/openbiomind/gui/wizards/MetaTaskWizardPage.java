/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (MetaTaskWizardPage.java) is part of OpenBiomind-GUI.
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
import openbiomind.gui.data.ClassificationMethodEnum;
import openbiomind.gui.data.MetaTaskTaskData;
import openbiomind.gui.data.ShuffleEnum;
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
 * The class MetaTaskWizardPage.
 * 
 * @author bsanghvi
 * @since Jul 5, 2008
 * @version Aug 18, 2008
 */
public class MetaTaskWizardPage extends AbstractTaskWizardPage implements IWizardPage {

   /** The constant for page name (value = <code>openbiomind.gui.wizards.MetaTaskWizardPage</code>). */
   public static final String PAGE_NAME = "openbiomind.gui.wizards.MetaTaskWizardPage"; //$NON-NLS-1$

   /** The number of columns in various groups. */
   private static final int NUM_COLUMN_IN_GROUP = 3;

   /** The dataset directory text button composite. */
   private TextButtonComposite datasetDirectoryTBC = null;

   /** The output directory text button composite. */
   private TextButtonComposite outputDirectoryTBC = null;

   /** The number of tasks text. */
   private Text numberOfTasksText = null;

   /** The target category combo. */
   private Combo targetCategoryCombo = null;

   /** The feature classification method combo. */
   private Combo classificationMethodCombo = null;

   /** The meta task shuffling combo. */
   private Combo metaTaskShufflingCombo = null;

   /**
    * Instantiates a new meta task wizard page.
    */
   public MetaTaskWizardPage() {
      this(Messages.MetaWiz_Name, Messages.MetaWiz_Desc);
   }

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
      WidgetHelper
            .createNewFieldLabel(parent, Messages.Label_DataTransResultDir, Messages.Tip_DataTransResultDir, true);
      this.datasetDirectoryTBC = createDatasetDirectoryTBC(parent);

      // Output directory
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_OutDir, Messages.Label_SpecifyOutDir, true);
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
      textButtonComposite.setToolTipText(Messages.Tip_DataTransResultDir);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(NUM_COLUMN_IN_GROUP - 1, 1).applyTo(textButtonComposite);

      // create decorations
      // TODO Update to identify that the folder must contain train and test tab files
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textButtonComposite,
            Messages.Err_InvalidDir);
      errorDecoration.show();

      // apply listeners
      textButtonComposite.addModifyListenerOnTextField(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            final File directory = new File(getDatasetDirectoryPath());
            final boolean valid = Utility.directoryExists(directory)
                  && Utility.listFileNames(directory, Resources.TRAIN_FILE_STARTS_WITH, Resources.TAB_EXTENSION).length > 0
                  && Utility.listFileNames(directory, Resources.TEST_FILE_STARTS_WITH, Resources.TAB_EXTENSION).length > 0;
            handleModifyTextWhenEditable(textButtonComposite, errorDecoration, valid);
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

      // Number of tasks
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_NumOfTasks);
      this.numberOfTasksText = createNewNumberOnlyText(parent);
      WidgetHelper.createNewBlankLabel(parent);

      // Target category
      // TODO Read from the given input files
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_TargetCat);
      this.targetCategoryCombo = createDefaultDropDownCombo(parent, getTargetCategoryArray());
      WidgetHelper.createNewBlankLabel(parent);

      // Classification method
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_ClassMethod);
      this.classificationMethodCombo = createDefaultReadOnlyCombo(parent, getClassificationMethodArray());
      WidgetHelper.createNewBlankLabel(parent);

      // MetaTask shuffling
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_MetaShuffling);
      this.metaTaskShufflingCombo = createDefaultReadOnlyCombo(parent, getMetaTaskShufflingArray());
      WidgetHelper.createNewBlankLabel(parent);
   }

   /**
    * Gets the dataset directory path.
    * 
    * @return the dataset directory path
    */
   public String getDatasetDirectoryPath() {
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
    * Gets the number of tasks.
    * 
    * @return the number of tasks
    */
   public Integer getNumberOfTasks() {
      try {
         return Integer.valueOf(getNumberOfTasksText().getText());
      } catch (final NumberFormatException e) {
         return null;
      }
   }

   /**
    * Gets the number of tasks text.
    * 
    * @return the number of tasks
    */
   private Text getNumberOfTasksText() {
      return this.numberOfTasksText;
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
    * Gets the meta task shuffling.
    * 
    * @return the meta task shuffling
    */
   public ShuffleEnum getMetaTaskShuffling() {
      return ShuffleEnum.parse(getMetaTaskShufflingArray()[getMetaTaskShufflingCombo().getSelectionIndex()]);
   }

   /**
    * Gets the meta task shuffling combo.
    * 
    * @return the meta task shuffling combo
    */
   private Combo getMetaTaskShufflingCombo() {
      return this.metaTaskShufflingCombo;
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#setVisible(boolean)
    */
   @Override
   public void setVisible(final boolean visible) {
      if (visible) {
         final IWizardPage previousPage = getPreviousPage();
         if (previousPage instanceof DatasetTransformerWizardPage) {
            disableComponent(getDatasetDirectoryTBC(), ((DatasetTransformerWizardPage) previousPage)
                  .getOutputDirectory());
         }
      }

      super.setVisible(visible);
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
         setErrorMessage(Messages.Err_FixErrToContinue);
      } else {
         setErrorMessage(null);
      }
   }

   /*
    * @see openbiomind.gui.wizards.AbstractTaskWizardPage#prepareTaskData()
    */
   @Override
   public MetaTaskTaskData prepareTaskData() {
      final MetaTaskTaskData metaTaskTaskData = new MetaTaskTaskData();
      metaTaskTaskData.setDatasetDirectory(getDatasetDirectoryPath());
      metaTaskTaskData.setOutputDirectory(getOutputDirectory());
      metaTaskTaskData.setNumberOfTasks(getNumberOfTasks());
      metaTaskTaskData.setTargetCategory(getTargetCategory());
      metaTaskTaskData.setClassficationMethod(getClassificationMethod());
      metaTaskTaskData.setMetaTaskShuffling(getMetaTaskShuffling());
      return metaTaskTaskData;
   }

}
