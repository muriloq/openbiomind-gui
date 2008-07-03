/**
 * TextButtonComposite.java
 *
 * The file TextButtonComposite.java.
 *
 * $Id$
 */
package openbiomind.gui.widgets;

import openbiomind.gui.util.Constants;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * The class TextButtonComposite.
 *
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Jun 27, 2008
 */
public abstract class TextButtonComposite extends Composite implements Constants {

   /** The textField. */
   private Text textField = null;

   /** The actionButton. */
   private Button actionButton = null;

   /** The valid. */
   private boolean valid = false;

   /**
    * Instantiates a new textField actionButton composite.
    *
    * @param parent the parent
    */
   public TextButtonComposite(final Composite parent) {
      super(parent, SWT.NONE);
      init();

      // apply layout
      GUI.GRID_LAYOUT_DEFAULT.copy().numColumns(2).margins(0, 0).applyTo(this);

      // add components
      this.textField = createTextField(this);
      this.actionButton = createActionButton(this);
   }

   /**
    * Init the component. Override this method to initialize required properties.
    */
   protected void init() {
      // default empty implementation
   }

   /**
    * Creates the text field.
    *
    * @param parent the parent
    *
    * @return the text
    */
   private Text createTextField(final Composite parent) {
      final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.applyTo(text);

      return text;
   }

   /**
    * Adds the modify listener.
    *
    * @param listener the listener
    *
    * @see org.eclipse.swt.widgets.Text#addModifyListener(org.eclipse.swt.events.ModifyListener)
    */
   public void addModifyListener(final ModifyListener listener) {
      this.textField.addModifyListener(listener);
   }

   /**
    * Creates the action button.
    *
    * @param parent the parent
    *
    * @return the button
    */
   private Button createActionButton(final Composite parent) {
      final Button button = new Button(this, SWT.PUSH);
      button.setText(Resources.BROWSE);

      // apply layout
      GUI.GRID_DATA_DEFAULT.applyTo(button);

      // apply listeners
      button.addSelectionListener(new SelectionAdapter() {

         @Override
         public void widgetSelected(final SelectionEvent e) {
            final String absolutePath = buttonSelected();
            if (absolutePath != null) {
               setText(absolutePath);
            }
         }

      });

      return button;
   }

   /**
    * Gets the textField.
    *
    * @return the textField
    */
   public Text getTextField() {
      return this.textField;
   }

   /**
    * Gets the text.
    *
    * @return the text
    */
   public String getText() {
      return getTextField().getText();
   }

   /**
    * Sets the text.
    *
    * @param text the new text
    */
   public void setText(final String text) {
      getTextField().setText(text);
   }

   /**
    * Gets the actionButton.
    *
    * @return the actionButton
    */
   public Button getActionButton() {
      return this.actionButton;
   }

   /*
    * @see org.eclipse.swt.widgets.Control#setEnabled(boolean)
    */
   @Override
   public void setEnabled(final boolean enabled) {
      super.setEnabled(enabled);
      getTextField().setEnabled(enabled);
      getActionButton().setEnabled(enabled);
   }

   /*
    * @see org.eclipse.swt.widgets.Control#setToolTipText(java.lang.String)
    */
   @Override
   public void setToolTipText(final String string) {
      getTextField().setToolTipText(string);
   }

   /**
    * Checks if is valid.
    *
    * @return the valid
    */
   public boolean isValid() {
      return this.valid;
   }

   /**
    * Sets the valid.
    *
    * @param valid the valid to set
    */
   public void setValid(final boolean valid) {
      this.valid = valid;
   }

   /**
    * Button selected.
    *
    * @return the string
    */
   protected abstract String buttonSelected();

}
