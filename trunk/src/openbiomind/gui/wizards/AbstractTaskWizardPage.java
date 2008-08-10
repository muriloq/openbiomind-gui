/**
 * AbstractTaskWizardPage.java
 *
 * The file AbstractTaskWizardPage.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.common.Constants;
import openbiomind.gui.common.TextButtonComposite;
import openbiomind.gui.data.AbstractTaskData;
import openbiomind.gui.data.ClassificationMethodEnum;
import openbiomind.gui.data.ClusteringColorsEnum;
import openbiomind.gui.data.DatasetClusteringMetricEnum;
import openbiomind.gui.data.FeatureSelectionMethodEnum;
import openbiomind.gui.data.ShuffleEnum;
import openbiomind.gui.util.Utility;
import openbiomind.gui.util.WidgetHelper;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;

/**
 * The class AbstractTaskWizardPage.
 *
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Aug 9, 2008
 */
public abstract class AbstractTaskWizardPage extends WizardPage implements IWizardPage, Constants {

   /** The parent. */
   private Composite parent = null;

   /** The control container. */
   private Composite controlContainer = null;

   /** The project name text. */
   private Text projectNameText = null;

   /** The file dialog. */
   private FileDialog fileDialog = null;

   /** The directory dialog. */
   private DirectoryDialog directoryDialog = null;

   /** The target category array. */
   private String[] targetCategoryArray = null;

   /** The feature selection method array. */
   private String[] featureSelectionMethodArray = null;

   /** The classification method array. */
   private String[] classificationMethodArray = null;

   /** The meta task shuffling array. */
   private String[] metaTaskShufflingArray = null;

   /** The dataset clustering metric array. */
   private String[] datasetClusteringMetricArray = null;

   /** The target category array. */
   private String[] clusteringColorsArray = null;

   /** The boolean value array. */
   private String[] booleanValueArray = null;

   /**
    * Instantiates a new abstract task wizard page.
    *
    * @param pageName the page name
    * @param pageTitle the page title
    * @param syntax the syntax
    */
   protected AbstractTaskWizardPage(final String pageName, final String pageTitle, final String syntax) {
      super(pageName);
      setTitle(pageTitle);
      setDescription(syntax);
   }

   /*
    * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
    */
   @Override
   public void createControl(final Composite parent) {
      setParent(parent);

      // initially page is complete
      setPageComplete(true);

      // Required to avoid an error in the system
      setControl(getControl());
   }

   /**
    * Returns the top level control of this dialog page.
    *
    * Failing to override this method will result into AssertionFailedException.
    *
    * @return the control
    *
    * @see org.eclipse.jface.dialogs.DialogPage#getControl()
    */
   @Override
   public Control getControl() {
      if (this.controlContainer == null) {
         this.controlContainer = createBaseComposite(getParent());
      }
      return this.controlContainer;
   }

   /**
    * Creates the base composite.
    *
    * @param parent the parent
    *
    * @return the composite
    */
   protected abstract Composite createBaseComposite(final Composite parent);

   /*
    * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
    */
   @Override
   public void setVisible(final boolean visible) {
      if (visible) {
         final IWizardPage previousPage = getPreviousPage();
         System.out.println(previousPage);
         final IWizardPage nextPage = getNextPage();
         System.out.println(nextPage);
         if (previousPage instanceof AbstractTaskWizardPage) {
            getProjectNameText().setText(((AbstractTaskWizardPage) previousPage).getProjectNameText().getText());
         } else {
            if (nextPage instanceof AbstractTaskWizardPage) {
               getProjectNameText().setText(((AbstractTaskWizardPage) nextPage).getProjectNameText().getText());
            }
         }

         validatePage();
      } else {
         setPageComplete(true);
      }
      super.setVisible(visible);
   }

   /**
    * Disable component.
    *
    * @param textButtonComposite the text button composite
    * @param value the value
    */
   protected void disableComponent(final TextButtonComposite textButtonComposite, final String value) {
      textButtonComposite.setEnabled(false);
      textButtonComposite.setText(value);
      textButtonComposite.setToolTipText(value);
      textButtonComposite.setValid(true);
   }

   /*
    * @see org.eclipse.jface.wizard.WizardPage#isCurrentPage()
    */
   @Override
   public boolean isCurrentPage() {
      return super.isCurrentPage();
   }

   /**
    * Validate page.
    */
   protected abstract void validatePage();

   /**
    * Gets the parent.
    *
    * @return the parent
    */
   protected Composite getParent() {
      return this.parent;
   }

   /**
    * Sets the parent.
    *
    * @param parent the parent to set
    */
   protected void setParent(final Composite parent) {
      this.parent = parent;
   }

   /**
    * Adds the project information fields.
    *
    * @param parent the parent
    */
   protected void addProjectInformationFields(final Composite parent) {
      // Execution Name (optional)
      WidgetHelper.createNewFieldLabel(parent, Messages.Label_ProjName, Messages.Info_ProjName);
      this.projectNameText = createProjectNameText(parent);
   }

   /**
    * Checks if is project information valid.
    *
    * @return true, if is project information valid
    */
   protected boolean isProjectInformationValid() {
      return true;
   }

   /**
    * Creates the project name text.
    *
    * @param parent the parent
    *
    * @return the text
    */
   private Text createProjectNameText(final Composite parent) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
      text.setToolTipText(Messages.Tip_ProjName);

      // apply layout
      GUI.GRID_DATA_FILL_H.applyTo(text);

      // create decorations
      final ControlDecoration infoDecoration = WidgetHelper.createNewInformationDecoration(text, Messages.Tip_ProjName);
      infoDecoration.setShowOnlyOnFocus(true);

      // set initial focus on this field
      text.setFocus();

      return text;
   }

   /**
    * Gets the project name.
    *
    * @return the project name
    */
   public String getProjectName() {
      final String text = getProjectNameText().getText();
      final String currentTimeMillis = Long.toString(System.currentTimeMillis());
      if (Utility.isEmpty(text)) {
         return currentTimeMillis;
      } else {
         return text + SPACE + currentTimeMillis;
      }
   }

   /**
    * Gets the project name text.
    *
    * @return the project name text
    */
   protected Text getProjectNameText() {
      return this.projectNameText;
   }

   /**
    * Adds the section.
    *
    * @param parent the parent
    * @param text the text
    * @param numOfColumns the number of columns
    */
   protected void addSection(final Composite parent, final String text, final int numOfColumns) {
      WidgetHelper.createNewBlankLabel(parent, numOfColumns);
      WidgetHelper.createNewSectionLabel(parent, text, numOfColumns);
      WidgetHelper.createNewSeparatorH(parent, numOfColumns);
   }

   /**
    * Creates the new optional file text button composite.
    *
    * @param parent the parent
    * @param numOfColumns the number of columns
    *
    * @return the text button composite
    */
   protected TextButtonComposite createNewOptionalFileTextButtonComposite(final Composite parent, final int numOfColumns) {
      final TextButtonComposite textButtonComposite = new TextButtonComposite(parent) {

         @Override
         protected String buttonSelected() {
            return getFileDialog().open();
         }

      };
      textButtonComposite.setValid(true);
      textButtonComposite.setToolTipText(Messages.Tip_LeaveBlankOrSpecifyFile);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(numOfColumns, 1).applyTo(textButtonComposite);

      // create decorations
      final ControlDecoration infoDecoration = WidgetHelper.createNewInformationDecoration(textButtonComposite
            .getTextField(), Messages.Tip_LeaveBlankOrSpecifyFile);
      infoDecoration.setShowOnlyOnFocus(true);
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textButtonComposite,
            Messages.Err_FileNotExist);
      errorDecoration.hide();

      // apply listeners
      textButtonComposite.addModifyListenerOnTextField(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            if (textButtonComposite.isEnabled()) {
               textButtonComposite.setValid(Utility.isEmptyOrExistingFile(textButtonComposite.getText()));
               if (textButtonComposite.isValid()) {
                  errorDecoration.hide();
               } else {
                  infoDecoration.hideHover();
                  errorDecoration.show();
                  errorDecoration.showHoverText(errorDecoration.getDescriptionText());
               }

               validatePage();
            } else {
               errorDecoration.hide();
            }
         }

      });

      return textButtonComposite;
   }

   /**
    * Creates the new number only text.
    *
    * @param parent the parent
    *
    * @return the text
    */
   protected Text createNewNumberOnlyText(final Composite parent) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
      text.setToolTipText(Messages.Tip_LeaveBlank_Number);

      // apply layout
      GUI.GRID_DATA_FILL_H.applyTo(text);

      // create decorations
      final ControlDecoration infoDecoration = WidgetHelper.createNewInformationDecoration(text,
            Messages.Tip_LeaveBlank_Number);
      infoDecoration.setShowOnlyOnFocus(true);

      // apply listeners
      text.addVerifyListener(new VerifyListener() {

         @Override
         public void verifyText(final VerifyEvent event) {
            switch (event.keyCode) {
               case SWT.BS: // Backspace
               case SWT.DEL: // Delete
               case SWT.HOME: // Home
               case SWT.END: // End
               case SWT.ARROW_LEFT: // Left arrow
               case SWT.ARROW_RIGHT: // Right arrow
                  return;
            }

            if (!Character.isDigit(event.character)) {
               event.doit = false;
            }
         }

      });

      return text;
   }

   /**
    * Creates the default read only combo.
    *
    * @param parent the parent
    * @param items the items
    *
    * @return the combo
    */
   protected Combo createDefaultReadOnlyCombo(final Composite parent, final String[] items) {
      return createDefaultReadOnlyCombo(parent, items, true);
   }

   /**
    * Creates the default read only combo.
    *
    * @param parent the parent
    * @param items the items
    * @param fill the fill
    *
    * @return the combo
    */
   protected Combo createDefaultReadOnlyCombo(final Composite parent, final String[] items, final boolean fill) {
      return createDefaultCombo(parent, SWT.READ_ONLY, Messages.Tip_Combo_ReadOnly, items, fill);
   }

   /**
    * Creates the default drop down combo.
    *
    * @param parent the parent
    * @param items the items
    *
    * @return the combo
    */
   protected Combo createDefaultDropDownCombo(final Composite parent, final String[] items) {
      return createDefaultDropDownCombo(parent, items, true);
   }

   /**
    * Creates the default drop down combo.
    *
    * @param parent the parent
    * @param items the items
    * @param fill the fill
    *
    * @return the combo
    */
   protected Combo createDefaultDropDownCombo(final Composite parent, final String[] items, final boolean fill) {
      return createDefaultCombo(parent, SWT.DROP_DOWN, Messages.Tip_Combo_Simple, items, fill);
   }

   /**
    * Creates the default read only combo.
    *
    * @param parent the parent
    * @param style the style must be either {@link SWT#DROP_DOWN} or {@link SWT#READ_ONLY} or {@link SWT#SIMPLE}
    * @param tooltip the tool tip
    * @param items the items
    * @param fill the fill
    *
    * @return the combo
    */
   private Combo createDefaultCombo(final Composite parent, final int style, final String tooltip,
         final String[] items, final boolean fill) {
      final Combo combo = new Combo(parent, style);
      combo.setItems(items);

      // apply layout
      if (fill) {
         GUI.GRID_DATA_FILL_H.applyTo(combo);
      } else {
         GUI.GRID_DATA_DEFAULT.applyTo(combo);
      }

      // add tip and information decoration
      if (!Utility.isEmpty(tooltip)) {
         combo.setToolTipText(tooltip);

         // create decorations
         final ControlDecoration infoDecoration = WidgetHelper.createNewInformationDecoration(combo, tooltip);
         infoDecoration.setShowOnlyOnFocus(true);
      }

      // select first value
      combo.select(0);

      return combo;
   }

   /**
    * Creates the default text.
    *
    * @param parent the parent
    *
    * @return the text
    */
   protected Text createDefaultText(final Composite parent) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
      text.setToolTipText(Messages.Tip_LeaveBlank_Value);

      // apply layout
      GUI.GRID_DATA_FILL_H.applyTo(text);

      // create decorations
      final ControlDecoration infoDecoration = WidgetHelper.createNewInformationDecoration(text,
            Messages.Tip_LeaveBlank_Value);
      infoDecoration.setShowOnlyOnFocus(true);

      return text;
   }

   /**
    * Gets the directory dialog.
    *
    * @return the directoryDialog
    */
   protected DirectoryDialog getDirectoryDialog() {
      if (this.directoryDialog == null) {
         this.directoryDialog = new DirectoryDialog(getShell());
      }

      return this.directoryDialog;
   }

   /**
    * Gets the file dialog.
    *
    * @return the fileDialog
    */
   protected FileDialog getFileDialog() {
      if (this.fileDialog == null) {
         this.fileDialog = new FileDialog(getShell(), SWT.OPEN);
      }

      return this.fileDialog;
   }

   /**
    * Show error or warning.
    *
    * @param errorDecoration the error decoration
    * @param warningDecoration the warning decoration
    * @param inError the in error
    * @param inWarning the in warning
    * @param infoDecoration the info decoration
    */
   protected void showErrorOrWarning(final boolean inError, final ControlDecoration errorDecoration,
         final boolean inWarning, final ControlDecoration warningDecoration, final ControlDecoration infoDecoration) {
      boolean shown = false;
      if (inError) {
         errorDecoration.show();
         errorDecoration.showHoverText(errorDecoration.getDescriptionText());
         shown = true;
      } else {
         errorDecoration.hide();

         if (inWarning) {
            warningDecoration.show();
            warningDecoration.showHoverText(warningDecoration.getDescriptionText());
            shown = true;
         } else {
            warningDecoration.hide();
         }
      }

      if (shown && infoDecoration != null) {
         infoDecoration.hideHover();
      }
   }

   /**
    * Show error or warning.
    *
    * @param inError the in error
    * @param errorDecoration the error decoration
    * @param inWarning the in warning
    * @param warningDecoration the warning decoration
    */
   protected void showErrorOrWarning(final boolean inError, final ControlDecoration errorDecoration,
         final boolean inWarning, final ControlDecoration warningDecoration) {
      showErrorOrWarning(inError, errorDecoration, inWarning, warningDecoration, null);
   }

   /**
    * Gets the target category array.
    *
    * @return the target category array
    */
   protected String[] getTargetCategoryArray() {
      if (this.targetCategoryArray == null) {
         this.targetCategoryArray = new String[] { EMPTY, Resources.CATEGORY_CASE.toString() };
      }

      return this.targetCategoryArray;
   }

   /**
    * Gets the feature selection method array.
    *
    * @return the feature selection method array
    */
   protected String[] getFeatureSelectionMethodArray() {
      if (this.featureSelectionMethodArray == null) {
         this.featureSelectionMethodArray = new String[] { EMPTY,
               FeatureSelectionMethodEnum.DIFFERENTIATION.toString(), FeatureSelectionMethodEnum.SAM.toString() };
      }

      return this.featureSelectionMethodArray;
   }

   /**
    * Gets the classification method array.
    *
    * @return the classification method array
    */
   protected String[] getClassificationMethodArray() {
      if (this.classificationMethodArray == null) {
         this.classificationMethodArray = new String[] { EMPTY, ClassificationMethodEnum.SNPQA.toString(),
               ClassificationMethodEnum.BOOLSIMPLE.toString(), ClassificationMethodEnum.SNPLOCAL.toString(),
               ClassificationMethodEnum.SNP.toString(), ClassificationMethodEnum.BOOLCOMPLEX.toString(),
               ClassificationMethodEnum.CONVENTIONAL.toString() };
      }

      return this.classificationMethodArray;
   }

   /**
    * Gets the meta task shuffling array.
    *
    * @return the meta task shuffling array
    */
   protected String[] getMetaTaskShufflingArray() {
      if (this.metaTaskShufflingArray == null) {
         this.metaTaskShufflingArray = new String[] { EMPTY, ShuffleEnum.ON.toString(), ShuffleEnum.OFF.toString() };
      }

      return this.metaTaskShufflingArray;
   }

   /**
    * Gets the dataset clustering metric array.
    *
    * @return the dataset clustering metric array
    */
   protected String[] getDatasetClusteringMetricArray() {
      if (this.datasetClusteringMetricArray == null) {
         this.datasetClusteringMetricArray = new String[] { EMPTY, DatasetClusteringMetricEnum.COSINE.toString(),
               DatasetClusteringMetricEnum.EUCLIDEAN.toString(), DatasetClusteringMetricEnum.SNP.toString() };
      }

      return this.datasetClusteringMetricArray;
   }

   /**
    * Gets the clustering colors array.
    *
    * @return the clustering colors array
    */
   protected String[] getClusteringColorsArray() {
      if (this.clusteringColorsArray == null) {
         this.clusteringColorsArray = new String[] { EMPTY, ClusteringColorsEnum.TRADITIONAL.toString(),
               ClusteringColorsEnum.MONO.toString() };
      }

      return this.clusteringColorsArray;
   }

   /**
    * Gets the boolean value array.
    *
    * @return the boolean value array
    */
   protected String[] getBooleanValueArray() {
      if (this.booleanValueArray == null) {
         this.booleanValueArray = new String[] { EMPTY, Boolean.TRUE.toString(), Boolean.FALSE.toString() };
      }

      return this.booleanValueArray;
   }

   /**
    * Prepare task data.
    *
    * @return the abstract task data
    */
   public abstract AbstractTaskData prepareTaskData();

}
