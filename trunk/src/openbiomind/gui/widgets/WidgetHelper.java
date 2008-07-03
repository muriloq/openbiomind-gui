/**
 * WidgetHelper.java
 *
 * The file WidgetHelper.java.
 *
 * $Id$
 */
package openbiomind.gui.widgets;

import openbiomind.gui.util.Constants;
import openbiomind.gui.util.Utility;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

/**
 * The class WidgetHelper.
 *
 * @author bsanghvi
 * @since Jun 26, 2008
 * @version Jun 26, 2008
 */
public final class WidgetHelper implements Constants {

   /**
    * Creates the new details label.
    *
    * @param parent the parent
    * @param text the text
    * @param horizontalSpan the horizontal span
    * @param verticalSpan the vertical span
    *
    * @return the label
    */
   public static Label createNewDetailsLabel(final Composite parent, final String text, final int horizontalSpan,
         final int verticalSpan) {
      final Label label = new Label(parent, SWT.NULL);
      label.setText(text);
      label.setToolTipText(text);

      final Font font = getBoldFont(label);
      label.setFont(font);

      // apply layout
      GUI.GRID_DATA_DEFAULT.copy().span(horizontalSpan, verticalSpan).applyTo(label);

      // add listeners
      label.addDisposeListener(new DisposeListener() {

         @Override
         public void widgetDisposed(final DisposeEvent e) {
            font.dispose();
         }

      });

      return label;
   }

   /**
    * Creates the new details label. This is similar to
    * <code>createNewDetailsLabel(parent, text, horizontalSpan, 1)</code>.
    *
    * @param parent the parent
    * @param text the text
    * @param horizontalSpan the horizontal span
    *
    * @return the label
    */
   public static Label createNewDetailsLabel(final Composite parent, final String text, final int horizontalSpan) {
      return createNewDetailsLabel(parent, text, horizontalSpan, 1);
   }

   /**
    * Creates the new details label. This is similar to <code>createNewDetailsLabel(parent, text, 1, 1)</code>.
    *
    * @param parent the parent
    * @param text the text
    *
    * @return the label
    */
   public static Label createNewDetailsLabel(final Composite parent, final String text) {
      return createNewDetailsLabel(parent, text, 1, 1);
   }

   /**
    * Creates the new field label.
    *
    * @param parent the parent
    * @param text the text
    * @param toolTip the tool tip
    * @param requiredField the required field
    *
    * @return the label
    */
   public static Label createNewFieldLabel(final Composite parent, final String text, final String toolTip,
         final boolean requiredField) {
      final Label label = new Label(parent, SWT.NULL);
      label.setText(text + LABEL_SEPARATOR);

      if (!Utility.isEmpty(toolTip)) {
         label.setToolTipText(toolTip);
      }

      // apply layout
      GUI.GRID_DATA_DEFAULT.applyTo(label);

      // add decoration if it is required field
      if (requiredField) {
         createNewRequiredDecoration(label);
      }

      return label;
   }

   /**
    * Creates the new field label. This is similar to <code>createNewFieldLabel(parent, text, toolTip, false)</code>.
    *
    * @param parent the parent
    * @param text the text
    * @param toolTip the tool tip
    *
    * @return the label
    */
   public static Label createNewFieldLabel(final Composite parent, final String text, final String toolTip) {
      return createNewFieldLabel(parent, text, toolTip, false);
   }

   /**
    * Creates the new field label. This is similar to
    * <code>createNewFieldLabel(parent, text, null, requiredField)</code>.
    *
    * @param parent the parent
    * @param text the text
    * @param requiredField the required field
    *
    * @return the label
    */
   public static Label createNewFieldLabel(final Composite parent, final String text, final boolean requiredField) {
      return createNewFieldLabel(parent, text, null, requiredField);
   }

   /**
    * Creates the new field label. This is similar to <code>createNewFieldLabel(parent, text, null, false)</code>.
    *
    * @param parent the parent
    * @param text the text
    *
    * @return the label
    */
   public static Label createNewFieldLabel(final Composite parent, final String text) {
      return createNewFieldLabel(parent, text, null, false);
   }

   /**
    * Creates the new blank label.
    *
    * @param parent the parent
    * @param horizontalSpan the horizontal span
    * @param verticalSpan the vertical span
    *
    * @return the label
    */
   public static Label createNewBlankLabel(final Composite parent, final int horizontalSpan, final int verticalSpan) {
      final Label label = new Label(parent, SWT.NULL);

      // apply layout
      GUI.GRID_DATA_DEFAULT.copy().span(horizontalSpan, verticalSpan).applyTo(label);
      return label;
   }

   /**
    * Creates the new blank label. This is similar to <code>createNewBlankLabel(parent, horizontalSpan, 1)</code>.
    *
    * @param parent the parent
    * @param horizontalSpan the horizontal span
    *
    * @return the label
    */
   public static Label createNewBlankLabel(final Composite parent, final int horizontalSpan) {
      return createNewBlankLabel(parent, horizontalSpan, 1);
   }

   /**
    * Creates the new blank label. This is similar to <code>createNewBlankLabel(parent, 1, 1)</code>.
    *
    * @param parent the parent
    *
    * @return the label
    */
   public static Label createNewBlankLabel(final Composite parent) {
      return createNewBlankLabel(parent, 1, 1);
   }

   /**
    * Creates the new separator.
    *
    * @param parent the parent
    * @param alignment the alignment must be either {@link SWT#HORIZONTAL} or {@link SWT#VERTICAL}.
    * @param span the span
    *
    * @return the label
    */
   public static Label createNewSeparator(final Composite parent, final int alignment, final int span) {
      final Label label = new Label(parent, SWT.SEPARATOR | alignment);

      // apply layout
      if ((SWT.HORIZONTAL & alignment) == SWT.HORIZONTAL) {
         GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(span, 1).applyTo(label);
      } else if ((SWT.VERTICAL & alignment) == SWT.VERTICAL) {
         GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(1, span).applyTo(label);
      } else {
         GUI.GRID_DATA_FILL_H_GRAB_H.applyTo(label);
      }

      return label;
   }

   /**
    * Creates the new separator. This is similar to <code>createNewSeparator(parent, alignment, 1)</code>.
    *
    * @param parent the parent
    * @param alignment the alignment must be either {@link SWT#HORIZONTAL} or {@link SWT#VERTICAL}.
    *
    * @return the label
    */
   public static Label createNewSeparator(final Composite parent, final int alignment) {
      return createNewSeparator(parent, alignment, 1);
   }

   /**
    * Creates the error decoration.
    *
    * @param control the control
    * @param description the description
    *
    * @return the control decoration
    */
   public static ControlDecoration createNewErrorDecoration(final Control control, final String description) {
      return createNewDecoration(control, GUI.FIELD_DECORATION_ERROR_IMAGE, SWT.LEFT | SWT.CENTER, description);
   }

   /**
    * Creates the new warning decoration.
    *
    * @param control the control
    * @param description the description
    *
    * @return the control decoration
    */
   public static ControlDecoration createNewWarningDecoration(final Control control, final String description) {
      return createNewDecoration(control, GUI.FIELD_DECORATION_WARNING_IMAGE, SWT.LEFT | SWT.CENTER, description);
   }

   /**
    * Creates the new information decoration.
    *
    * @param control the control
    * @param description the description
    *
    * @return the control decoration
    */
   public static ControlDecoration createNewInformationDecoration(final Control control, final String description) {
      return createNewDecoration(control, GUI.FIELD_DECORATION_INFORMATION_IMAGE, SWT.RIGHT | SWT.TOP, description);
   }

   /**
    * Creates the new required decoration.
    *
    * @param control the control
    *
    * @return the control decoration
    */
   public static ControlDecoration createNewRequiredDecoration(final Control control) {
      return createNewDecoration(control, GUI.FIELD_DECORATION_REQUIRED_IMAGE, SWT.LEFT | SWT.TOP,
            GUI.FIELD_DECORATION_REQUIRED_DESCRIPTION);
   }

   /**
    * Creates the new decoration.
    *
    * @param control the control
    * @param image the image
    * @param position the position
    * @param description the description
    *
    * @return the control decoration
    */
   public static ControlDecoration createNewDecoration(final Control control, final Image image, final int position,
         final String description) {
      final ControlDecoration controlDecoration = new ControlDecoration(control, position);
      controlDecoration.setImage(image);
      controlDecoration.setDescriptionText(description);
      return controlDecoration;
   }

   /**
    * Gets the bold font for the given control. It is the responsibility of the accessor to dispose the font after use.
    *
    * @param control the control
    *
    * @return the bold font
    */
   public static Font getBoldFont(final Control control) {
      final FontData fontData = control.getFont().getFontData()[0];
      fontData.setStyle(SWT.BOLD);
      return new Font(Display.getDefault(), fontData);
   }

}
