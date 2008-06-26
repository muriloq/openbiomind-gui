/**
 * TextButtonComposite.java
 *
 * The file TextButtonComposite.java.
 *
 * $Id$
 */
package openbiomind.gui.wizards;

import openbiomind.gui.util.Constants;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * The class TextButtonComposite.
 *
 * @author bsanghvi
 * @since Jun 13, 2008
 * @version Jun 13, 2008
 */
public abstract class TextButtonComposite extends Composite {

   /** The textField. */
   private Text textField = null;

   /** The actionButton. */
   private Button actionButton = null;

   /**
    * Instantiates a new textField actionButton composite.
    *
    * @param parent the parent
    */
   public TextButtonComposite(final Composite parent) {
      super(parent, SWT.NONE);
      init();

      /*
       * apply layout information
       */
      GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, false).applyTo(this);
      GridLayoutFactory.swtDefaults().margins(0, 0).numColumns(2).equalWidth(false).applyTo(this);

      /*
       * add components
       */
      getTextField();
      getActionButton();
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
    * Init the component. Override this method to initialize required properties.
    */
   protected void init() {
      // default empty implementation
   }

   /**
    * Gets the textField.
    *
    * @return the textField
    */
   private Text getTextField() {
      if (this.textField == null) {
         this.textField = new Text(this, SWT.SINGLE | SWT.BORDER);

         /*
          * apply layout information
          */
         GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(this.textField);

         /*
          * apply listeners
          */
         this.textField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(final KeyEvent e) {
               textKeyReleased();
            }

         });

         this.textField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(final FocusEvent e) {
               onTextChange();
            }

         });
      }

      return this.textField;
   }

   /**
    * Gets the actionButton.
    *
    * @return the actionButton
    */
   private Button getActionButton() {
      if (this.actionButton == null) {
         this.actionButton = new Button(this, SWT.PUSH);
         this.actionButton.setText(Constants.Browse);

         /*
          * apply layout information
          */
         GridDataFactory.swtDefaults().applyTo(this.actionButton);

         /*
          * apply listeners
          */
         this.actionButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
               final String newText = buttonSelected();
               if (newText != null) {
                  getTextField().setText(newText);
               }

               onTextChange();
            }

         });
      }

      return this.actionButton;
   }

   /*
    * @see org.eclipse.swt.widgets.Control#setEnabled(boolean)
    */
   @Override
   public void setEnabled(final boolean enabled) {
      super.setEnabled(enabled);
      getActionButton().setEnabled(enabled);
      getTextField().setEnabled(enabled);
   }

   /*
    * @see org.eclipse.swt.widgets.Control#setBackground(org.eclipse.swt.graphics.Color)
    */
   @Override
   public void setBackground(final Color color) {
      getTextField().setBackground(color);
   }

   /**
    * Button selected.
    *
    * @return the string
    */
   protected abstract String buttonSelected();

   /**
    * Text key released.
    */
   protected abstract void textKeyReleased();

   /**
    * On textField change.
    */
   protected abstract void onTextChange();

}
