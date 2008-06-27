/**
 * AbstractTaskWizardPage.java
 *
 * The file AbstractTaskWizardPage.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.util.Constants;
import openbiomind.gui.util.Messages;
import openbiomind.gui.util.Utility;
import openbiomind.gui.widgets.TextButtonComposite;
import openbiomind.gui.widgets.WidgetHelper;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;

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
      return getBaseContainer();
   }

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
    * Gets the base container.
    *
    * @return the base container
    */
   protected abstract Composite getBaseContainer();

   /**
    * Validate page.
    */
   protected abstract void validatePage();

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

      // apply layout
      GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(textButtonComposite);

      // create decorations
      WidgetHelper.createNewInformationDecoration(textButtonComposite.getTextField(),
            Messages.Info_LeaveBlankOrSpecifyFile).show();
      final ControlDecoration errorDecoration = WidgetHelper.createNewErrorDecoration(textButtonComposite,
            Messages.Error_FileNotExist);
      errorDecoration.hide();

      // apply listeners
      textButtonComposite.addModifyListener(new ModifyListener() {

         @Override
         public void modifyText(final ModifyEvent e) {
            textButtonComposite.setValid(Utility.isEmptyOrExistingFile(textButtonComposite.getText()));
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

}
