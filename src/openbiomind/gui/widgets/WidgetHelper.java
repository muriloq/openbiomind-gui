/**
 * WidgetHelper.java
 *
 * The file WidgetHelper.java.
 *
 * $Id$
 */
package openbiomind.gui.widgets;

import openbiomind.gui.util.Constants;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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
    * Creates the new information label.
    *
    * @param parent the parent
    * @param text the text
    * @param horizontalSpan the horizontal span
    * @param verticalSpan the vertical span
    *
    * @return the label
    */
   public static Label createNewInformationLabel(final Composite parent, final String text, final int horizontalSpan,
         final int verticalSpan) {
      final Label label = new Label(parent, SWT.BOLD);
      label.setText(text);
      GUI.WIZARD_INFORMATION_LABEL_GRID_DATA.span(horizontalSpan, verticalSpan).applyTo(label);
      return label;
   }

   /**
    * Creates the new information label.
    *
    * @param parent the parent
    * @param text the text
    * @param horizontalSpan the horizontal span
    *
    * @return the label
    */
   public static Label createNewInformationLabel(final Composite parent, final String text, final int horizontalSpan) {
      return createNewInformationLabel(parent, text, horizontalSpan, 1);
   }

   /**
    * Creates the new component label.
    *
    * @param parent the parent
    * @param text the text
    * @param toolTip the tool tip
    * @param requiredField the required field
    *
    * @return the label
    */
   public static Label createNewComponentLabel(final Composite parent, final String text, final String toolTip,
         final boolean requiredField) {
      final Label label = new Label(parent, SWT.NULL);
      label.setText(text + LABEL_SEPARATOR);
      label.setToolTipText(toolTip);
      GUI.WIZARD_COMPONENT_LABEL_GRID_DATA.applyTo(label);

      // add decoration if it is required field
      if (requiredField) {
         createNewRequiredDecoration(label);
      }

      return label;
   }

   /**
    * Creates the new component label.
    *
    * @param parent the parent
    * @param text the text
    * @param toolTip the tool tip
    *
    * @return the label
    */
   public static Label createNewComponentLabel(final Composite parent, final String text, final String toolTip) {
      return createNewComponentLabel(parent, text, toolTip, false);
   }

   /**
    * Creates the new blank component label.
    *
    * @param parent the parent
    * @param horizontalSpan the horizontal span
    * @param verticalSpan the vertical span
    *
    * @return the label
    */
   public static Label createNewBlankComponentLabel(final Composite parent, final int horizontalSpan,
         final int verticalSpan) {
      final Label label = new Label(parent, SWT.NULL);
      GUI.WIZARD_COMPONENT_LABEL_GRID_DATA.span(horizontalSpan, verticalSpan).applyTo(label);
      return label;
   }

   /**
    * Creates the new blank component label.
    *
    * @param parent the parent
    * @param horizontalSpan the horizontal span
    *
    * @return the label
    */
   public static Label createNewBlankComponentLabel(final Composite parent, final int horizontalSpan) {
      return createNewBlankComponentLabel(parent, horizontalSpan, 1);
   }

   /**
    * Creates the new blank component label.
    *
    * @param parent the parent
    *
    * @return the label
    */
   public static Label createNewBlankComponentLabel(final Composite parent) {
      return createNewBlankComponentLabel(parent, 1, 1);
   }

   /**
    * Creates the new separator.
    *
    * @param parent the parent
    * @param alignment the alignment
    * @param span the span
    *
    * @return the label
    */
   public static Label createNewSeparator(final Composite parent, final int alignment, final int span) {
      final Label label = new Label(parent, SWT.SEPARATOR | alignment);
      if ((SWT.HORIZONTAL & alignment) == SWT.HORIZONTAL) {
         GUI.WIZARD_SEPARATOR_GRID_DATA.span(span, 1).applyTo(label);
      } else if ((SWT.VERTICAL & alignment) == SWT.VERTICAL) {
         GUI.WIZARD_SEPARATOR_GRID_DATA.span(1, span).applyTo(label);
      } else {
         GUI.WIZARD_SEPARATOR_GRID_DATA.span(1, 1).applyTo(label);
      }
      return label;
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

}
