/**
 * AbstractTaskWizardPage.java
 *
 * The file AbstractTaskWizardPage.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import static openbiomind.gui.util.Constants.LABEL_SEPARATOR;
import static openbiomind.gui.util.Constants.SPACE;
import openbiomind.gui.util.Utility;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * The class AbstractTaskWizardPage.
 *
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Jun 13, 2008
 */
public abstract class AbstractTaskWizardPage extends WizardPage implements IWizardPage {

   /** The Constant GRID_DATA_INFORMATION_LABEL. */
   protected final static GridDataFactory GRID_DATA_INFORMATION_LABEL = GridDataFactory
         .swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(2, 1);

   /** The Constant GRID_DATA_SEPARATOR. */
   protected final static GridDataFactory GRID_DATA_SEPARATOR = GridDataFactory.swtDefaults()
         .align(SWT.FILL, SWT.CENTER).grab(true, false).span(2, 1);

   /** The Constant GRID_DATA_COMPONENT_LABEL. */
   protected final static GridDataFactory GRID_DATA_COMPONENT_LABEL = GridDataFactory.swtDefaults()
         .align(SWT.BEGINNING, SWT.CENTER);

   /** The warning. */
   private StringBuilder warning = null;

   /** The parent. */
   private Composite parent = null;

   /**
    * Instantiates a new abstract task wizard page.
    *
    * @param pageName the page name
    * @param pageTitle the page title
    * @param syntax the syntax
    */
   protected AbstractTaskWizardPage(final String pageName, final String pageTitle,
         final String syntax) {
      super(pageName);
      setTitle(pageTitle);
      setDescription(syntax);
      resetWarning();
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

   /*
    * @see org.eclipse.jface.wizard.WizardPage#setErrorMessage(java.lang.String)
    */
   @Override
   public void setErrorMessage(final String newMessage) {
      super.setErrorMessage(newMessage);
      setPageComplete(false);
   }

   /*
    * @see org.eclipse.jface.wizard.WizardPage#setPageComplete(boolean)
    */
   @Override
   public void setPageComplete(final boolean complete) {
      super.setPageComplete(complete);
      if (complete) {
         super.setErrorMessage(null); // reset the error message, if any
         updateWarningDisplay();
      }
   }

   /**
    * Checks if is warning needed.
    *
    * @return true, if is warning needed
    */
   protected boolean isWarningNeeded() {
      return !Utility.isEmpty(getWarning());
   }

   /**
    * Reset warning.
    */
   private void resetWarning() {
      this.warning = new StringBuilder(""); //$NON-NLS-1$
   }

   /**
    * Append warning.
    *
    * @param warning the warning
    *
    * @see java.lang.StringBuilder#append(java.lang.String)
    */
   protected void appendWarning(final String warning) {
      this.warning.append(warning).append(SPACE);
   }

   /**
    * Gets the warning.
    *
    * @return the warning
    */
   private String getWarning() {
      return this.warning.toString();
   }

   /**
    * Update warning display.
    */
   private void updateWarningDisplay() {
      if (isWarningNeeded()) {
         setMessage(getWarning());
         resetWarning();
      } else {
         setMessage(null);
      }
   }

   /**
    * Adds the information label.
    *
    * @param parent the parent
    * @param text the text
    */
   protected void addInformationLabel(final Composite parent, final String text) {
      final Label label = new Label(parent, SWT.BOLD);
      label.setText(text);
      GRID_DATA_INFORMATION_LABEL.applyTo(label);
   }

   /**
    * Adds the component label.
    *
    * @param parent the parent
    * @param text the text
    * @param toolTip the tool tip
    */
   protected void addComponentLabel(final Composite parent, final String text, final String toolTip) {
      final Label label = new Label(parent, SWT.NULL);
      label.setText(text + LABEL_SEPARATOR);
      label.setToolTipText(toolTip);
      GRID_DATA_COMPONENT_LABEL.applyTo(label);
   }

   /**
    * Adds the separator.
    *
    * @param parent the parent
    * @param alignment the alignment must be {@link SWT#HORIZONTAL} or {@link SWT#VERTICAL}.
    */
   protected void addSeparator(final Composite parent, final int alignment) {
      final Label label = new Label(parent, SWT.SEPARATOR | alignment);
      GRID_DATA_SEPARATOR.applyTo(label);
   }

   /**
    * Gets the base container.
    *
    * @return the base container
    */
   protected abstract Composite getBaseContainer();

}
