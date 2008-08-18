/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (WidgetHelper.java) is part of OpenBiomind-GUI.
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

package openbiomind.gui.util;

import openbiomind.gui.common.Constants;

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
 * @version Aug 18, 2008
 */
public final class WidgetHelper implements Constants {

   /**
    * Creates the new section label.
    * 
    * @param parent the parent
    * @param text the text
    * @param horizontalSpan the horizontal span
    * @param verticalSpan the vertical span
    * 
    * @return the label
    */
   public static Label createNewSectionLabel(final Composite parent, final String text, final int horizontalSpan,
         final int verticalSpan) {
      final Label label = new Label(parent, SWT.CENTER);
      label.setText(text);

      changeToDisposableBoldFold(label, 1);

      return label;
   }

   /**
    * Creates the new section label. This is similar to
    * <code>createNewSectionLabel(parent, text, horizontalSpan, 1)</code>.
    * 
    * @param parent the parent
    * @param text the text
    * @param horizontalSpan the horizontal span
    * 
    * @return the label
    */
   public static Label createNewSectionLabel(final Composite parent, final String text, final int horizontalSpan) {
      return createNewSectionLabel(parent, text, horizontalSpan, 1);
   }

   /**
    * Creates the new section label. This is similar to <code>createNewSectionLabel(parent, text, 1, 1)</code>.
    * 
    * @param parent the parent
    * @param text the text
    * 
    * @return the label
    */
   public static Label createNewSectionLabel(final Composite parent, final String text) {
      return createNewSectionLabel(parent, text, 1, 1);
   }

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
      changeToDisposableBoldFold(label);

      // apply layout
      GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(horizontalSpan, verticalSpan).applyTo(label);

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
    * Creates the new label.
    * 
    * @param parent the parent
    * @param text the text
    * @param toolTip the tool tip
    * 
    * @return the label
    */
   public static Label createNewLabel(final Composite parent, final String text, final String toolTip) {
      final Label label = new Label(parent, SWT.NULL);
      label.setText(text);

      if (!Utility.isEmpty(toolTip)) {
         label.setToolTipText(toolTip);
      }

      // apply layout
      GUI.GRID_DATA_DEFAULT.applyTo(label);

      return label;
   }

   /**
    * Creates the new label.
    * 
    * @param parent the parent
    * @param text the text
    * 
    * @return the label
    */
   public static Label createNewLabel(final Composite parent, final String text) {
      return createNewLabel(parent, text, null);
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
      final Label label = createNewLabel(parent, text + LABEL_SEPARATOR, toolTip);

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
    * <code>createNewFieldLabel(parent, text, text, requiredField)</code>.
    * 
    * @param parent the parent
    * @param text the text
    * @param requiredField the required field
    * 
    * @return the label
    */
   public static Label createNewFieldLabel(final Composite parent, final String text, final boolean requiredField) {
      return createNewFieldLabel(parent, text, text, requiredField);
   }

   /**
    * Creates the new field label. This is similar to <code>createNewFieldLabel(parent, text, text, false)</code>.
    * 
    * @param parent the parent
    * @param text the text
    * 
    * @return the label
    */
   public static Label createNewFieldLabel(final Composite parent, final String text) {
      return createNewFieldLabel(parent, text, text, false);
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
      if ((SWT.HORIZONTAL & alignment) != 0) {
         GUI.GRID_DATA_FILL_H_GRAB_H.copy().span(span, 1).applyTo(label);
      } else if ((SWT.VERTICAL & alignment) != 0) {
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
    * Creates the new horizontal separator. This is similar to
    * <code>createNewSeparator(parent, SWT.HORIZONTAL, span)</code>.
    * 
    * @param parent the parent
    * @param span the span
    * 
    * @return the label
    */
   public static Label createNewSeparatorH(final Composite parent, final int span) {
      return createNewSeparator(parent, SWT.HORIZONTAL, span);
   }

   /**
    * Creates the new horizontal separator. This is similar to
    * <code>createNewSeparator(parent, SWT.HORIZONTAL, 1)</code>.
    * 
    * @param parent the parent
    * 
    * @return the label
    */
   public static Label createNewSeparatorH(final Composite parent) {
      return createNewSeparatorH(parent, 1);
   }

   /**
    * Creates the new vertical separator. This is similar to <code>createNewSeparator(parent, SWT.VERTICAL, span)</code>
    * .
    * 
    * @param parent the parent
    * @param span the span
    * 
    * @return the label
    */
   public static Label createNewSeparatorV(final Composite parent, final int span) {
      return createNewSeparator(parent, SWT.VERTICAL, span);
   }

   /**
    * Creates the new vertical separator. This is similar to <code>createNewSeparatorV(parent, 1)</code>.
    * 
    * @param parent the parent
    * 
    * @return the label
    */
   public static Label createNewSeparatorV(final Composite parent) {
      return createNewSeparatorV(parent, 1);
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
    * Change font style and height. A DisposeListener will be added to the control to dispose the font.
    * 
    * @param control the control
    * @param fontStyle the font style must be a combination of {@link SWT#NORMAL}, {@link SWT#ITALIC}, or
    * {@link SWT#BOLD}
    * @param heightVariation the height variation can be positive or negative to increase or decrease the font height
    */
   public static void changeToDisposableFont(final Control control, final int fontStyle, final int heightVariation) {
      final FontData fontData = control.getFont().getFontData()[0];
      fontData.setStyle(fontStyle);
      if (heightVariation != 0) {
         fontData.setHeight(fontData.getHeight() + heightVariation);
      }
      final Font font = new Font(Display.getDefault(), fontData);
      control.setFont(font);

      // apply listener to dispose the font when the control is disposed
      control.addDisposeListener(new DisposeListener() {

         @Override
         public void widgetDisposed(final DisposeEvent event) {
            font.dispose();
         }

      });
   }

   /**
    * Change font style. A DisposeListener will be added to the control to dispose the font. This is similar to calling
    * <code>changeToDisposableFont(control, fontStyle, 0)</code>.
    * 
    * @param control the control
    * @param fontStyle the font style must be a combination of {@link SWT#NORMAL}, {@link SWT#ITALIC}, or
    * {@link SWT#BOLD}
    */
   public static void changeToDisposableFont(final Control control, final int fontStyle) {
      changeToDisposableFont(control, fontStyle, 0);
   }

   /**
    * Change font style to Bold. A DisposeListener will be added to the control to dispose the font. This is similar to
    * calling <code>changeToDisposableFont(control, SWT.BOLD, 0)</code>.
    * 
    * @param control the control
    * @param heightVariation the height variation
    */
   public static void changeToDisposableBoldFold(final Control control, final int heightVariation) {
      changeToDisposableFont(control, SWT.BOLD, heightVariation);
   }

   /**
    * Change font style to Bold. A DisposeListener will be added to the control to dispose the font. This is similar to
    * calling <code>changeToDisposableBoldFold(control, 0)</code>.
    * 
    * @param control the control
    */
   public static void changeToDisposableBoldFold(final Control control) {
      changeToDisposableBoldFold(control, 0);
   }

   /**
    * Change font style to Italic. A DisposeListener will be added to the control to dispose the font. This is similar
    * to calling <code>changeToDisposableFont(control, SWT.ITALIC, 0)</code>.
    * 
    * @param control the control
    * @param heightVariation the height variation
    */
   public static void changeToDisposableItalicFold(final Control control, final int heightVariation) {
      changeToDisposableFont(control, SWT.ITALIC, heightVariation);
   }

   /**
    * Change font style to Italic. A DisposeListener will be added to the control to dispose the font. This is similar
    * to calling <code>changeToDisposableItalicFold(control, 0)</code>.
    * 
    * @param control the control
    */
   public static void changeToDisposableItalicFold(final Control control) {
      changeToDisposableItalicFold(control, 0);
   }

   /**
    * Change font style to Bold and Italic. A DisposeListener will be added to the control to dispose the font. This is
    * similar to calling <code>changeToDisposableFont(control, SWT.BOLD | SWT.ITALIC, 0)</code>.
    * 
    * @param control the control
    * @param heightVariation the height variation
    */
   public static void changeToDisposableBoldItalicFold(final Control control, final int heightVariation) {
      changeToDisposableFont(control, SWT.BOLD | SWT.ITALIC, heightVariation);
   }

   /**
    * Change font style to Bold and Italic. A DisposeListener will be added to the control to dispose the font. This is
    * similar to calling <code>changeToDisposableBoldItalicFold(control, 0)</code>.
    * 
    * @param control the control
    */
   public static void changeToDisposableBoldItalicFold(final Control control) {
      changeToDisposableBoldItalicFold(control, 0);
   }

}
