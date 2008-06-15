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

   /** The text. */
   private Text text = null;

   /** The button. */
   private Button button = null;

   /**
    * Instantiates a new text button composite.
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
      getText();
      getButton();
   }

   /**
    * Init the component. Override this method to initialize required properties.
    */
   protected void init() {
      // default empty implementation
   }

   /**
    * Gets the text.
    *
    * @return the text
    */
   protected Text getText() {
      if (this.text == null) {
         this.text = new Text(this, SWT.SINGLE | SWT.BORDER);

         /*
          * apply layout information
          */
         GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(
               this.text);

         /*
          * apply listeners
          */
         this.text.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(final KeyEvent e) {
               textKeyReleased();
            }

         });

         this.text.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(final FocusEvent e) {
               onTextChange();
            }

         });
      }

      return this.text;
   }

   /**
    * Gets the button.
    *
    * @return the button
    */
   protected Button getButton() {
      if (this.button == null) {
         this.button = new Button(this, SWT.PUSH);
         this.button.setText(Constants.Browse);

         /*
          * apply layout information
          */
         GridDataFactory.swtDefaults().applyTo(this.button);

         /*
          * apply listeners
          */
         this.button.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
               final String newText = buttonSelected();
               if (newText != null) {
                  getText().setText(newText);
               }

               onTextChange();
            }

         });
      }

      return button;
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
    * On text change.
    */
   protected abstract void onTextChange();

}
