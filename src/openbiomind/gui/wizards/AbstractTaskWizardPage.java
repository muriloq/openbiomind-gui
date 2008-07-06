/**
 * AbstractTaskWizardPage.java
 *
 * The file AbstractTaskWizardPage.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.util.CommonMessages;
import openbiomind.gui.util.Constants;
import openbiomind.gui.util.Utility;
import openbiomind.gui.widgets.TextButtonComposite;
import openbiomind.gui.widgets.WidgetHelper;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
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
 * @version Jun 26, 2008
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

      // Required to avoid an error in the system
      setControl(getControl());

      // initially page is not complete
      setPageComplete(false);
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
    * @param numberOfColumns the number of columns
    */
   protected void addProjectInformationFields(final Composite parent, final int numberOfColumns) {
      // Execution Name (optional)
      WidgetHelper.createNewFieldLabel(parent, WizardMessages.AbstractTaskWizardPage_Label_ProjectName,
            WizardMessages.AbstractTaskWizardPage_Tip_ProjectName);
      this.projectNameText = createProjectNameText(parent, numberOfColumns - 1);
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
    * @param numberOfColumns the number of columns
    *
    * @return the text
    */
   private Text createProjectNameText(final Composite parent, final int numberOfColumns) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
      text.setToolTipText(WizardMessages.AbstractTaskWizardPage_Info_ProjectName);

      // apply layout
      GUI.GRID_DATA_DEFAULT.copy().span(numberOfColumns, 1).applyTo(text);

      // create decorations
      final ControlDecoration infoDecoration = WidgetHelper.createNewInformationDecoration(text,
            WizardMessages.AbstractTaskWizardPage_Info_ProjectName);
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
    * @param numOfColumns the num of columns
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
    *
    * @return the text button composite
    */
   protected TextButtonComposite createNewOptionalFileTextButtonComposite(final Composite parent) {
      final TextButtonComposite textButtonComposite = new TextButtonComposite(parent) {

         @Override
         protected String buttonSelected() {
            return getFileDialog().open();
         }

      };
      textButtonComposite.setValid(true);
      textButtonComposite.setToolTipText(CommonMessages.Info_LeaveBlankOrSpecifyFile);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.applyTo(textButtonComposite);

      // create decorations
      final ControlDecoration infoDecoration = WidgetHelper.createNewInformationDecoration(textButtonComposite
            .getTextField(), CommonMessages.Info_LeaveBlankOrSpecifyFile);
      infoDecoration.hide();
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textButtonComposite,
            CommonMessages.Error_FileNotExist);
      errorDecoration.hide();

      // apply listeners
      textButtonComposite.addFocusListenerOnTextField(new FocusListener() {

         @Override
         public void focusGained(final FocusEvent event) {
            infoDecoration.show();
         }

         @Override
         public void focusLost(final FocusEvent event) {
            infoDecoration.hide();
         }

      });

      textButtonComposite.addModifyListenerOnTextField(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent event) {
            textButtonComposite.setValid(Utility.isEmptyOrExistingFile(textButtonComposite.getText()));
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
    * Creates the new number only text.
    *
    * @param parent the parent
    *
    * @return the text
    */
   protected Text createNewNumberOnlyText(final Composite parent) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
      text.setToolTipText(WizardMessages.AbstractTaskWizard_LeaveBlank_Number);

      // apply layout
      GUI.GRID_DATA_DEFAULT.applyTo(text);

      // create decorations
      final ControlDecoration infoDecoration = WidgetHelper.createNewInformationDecoration(text,
            WizardMessages.AbstractTaskWizard_LeaveBlank_Number);
      infoDecoration.hide();

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
    * Creates the default combo.
    *
    * @param parent the parent
    * @param items the items
    *
    * @return the combo
    */
   protected Combo createDefaultCombo(final Composite parent, final String[] items) {
      final Combo combo = new Combo(parent, SWT.READ_ONLY);
      combo.setItems(items);

      // apply layout
      GUI.GRID_DATA_DEFAULT.applyTo(combo);

      combo.select(0);

      return combo;
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
         this.fileDialog = new FileDialog(getShell());
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

}
