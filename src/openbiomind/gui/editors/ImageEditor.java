/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (ImageEditor.java) is part of OpenBiomind-GUI.
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

package openbiomind.gui.editors;

import java.io.File;
import java.io.IOException;

import openbiomind.gui.common.Constants.GUI;
import openbiomind.gui.console.Console;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

/**
 * The class ImageEditor is used to open an editor that contains image.
 * 
 * @author bsanghvi
 * @since Jul 20, 2008
 * @version Aug 18, 2008
 */
public class ImageEditor extends EditorPart {

   /** The Constant ID. */
   public static final String ID = "openbiomind.gui.editors.ImageEditor"; //$NON-NLS-1$

   /** The Constant GRID_DATA_GRAB_ALL. */
   private static final GridDataFactory GRID_DATA_GRAB_ALL = GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL)
         .grab(true, true);

   /** The container. */
   private Composite container = null;

   /** The image. */
   private Image image = null;

   /**
    * Instantiates a new image editor.
    */
   public ImageEditor() {
      // empty constructor
   }

   /*
    * @see org.eclipse.ui.part.EditorPart#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
    */
   @Override
   public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
      setSite(site);
      setInput(input);
      setPartName(input.getName());
   }

   /*
    * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
    */
   @Override
   public void createPartControl(final Composite parent) {
      final Image image = getImage();
      if (image != null) {
         this.container = createImageContainer(parent, image);
      } else {
         this.container = createErrorContainer(parent);
      }
   }

   /**
    * Creates the image container.
    * 
    * @param parent the parent
    * @param image the image
    * 
    * @return the composite
    */
   private Composite createImageContainer(final Composite parent, final Image image) {
      final Composite composite = new Composite(parent, SWT.NULL);

      // apply layout
      GRID_DATA_GRAB_ALL.applyTo(composite);
      GridLayoutFactory.swtDefaults().applyTo(composite);

      // add components
      createNewImageCanvas(composite, image); // no need to store the canvas

      return composite;
   }

   /**
    * Creates the new image canvas. <em>This method is based on Snippet 48 of Eclipse SWT Snippets.</em>
    * 
    * @param parent the parent
    * @param image the image
    * 
    * @return the canvas
    */
   private Canvas createNewImageCanvas(final Composite parent, final Image image) {
      final Canvas canvas = new Canvas(parent, SWT.NO_BACKGROUND | SWT.NO_REDRAW_RESIZE | SWT.V_SCROLL | SWT.H_SCROLL);

      // apply layput
      GRID_DATA_GRAB_ALL.applyTo(canvas);

      // required
      final Point origin = new Point(0, 0);
      final ScrollBar hBar = canvas.getHorizontalBar();
      final ScrollBar vBar = canvas.getVerticalBar();

      // add listeners
      // - listener that will be called when the control is resized
      canvas.addControlListener(new ControlAdapter() {

         @Override
         public void controlResized(final ControlEvent event) {
            final Rectangle bounds = image.getBounds();
            final Rectangle client = canvas.getClientArea();

            hBar.setMaximum(bounds.width);
            vBar.setMaximum(bounds.height);
            hBar.setThumb(Math.min(bounds.width, client.width));
            vBar.setThumb(Math.min(bounds.height, client.height));

            final int hPage = bounds.width - client.width;
            final int vPage = bounds.height - client.height;

            // updated x component of origin, if required
            int hSelection = hBar.getSelection();
            if (hSelection >= hPage) {
               if (hPage <= 0) {
                  hSelection = 0;
               }

               origin.x = -hSelection;
            }

            // updated y component of origin, if required
            int vSelection = vBar.getSelection();
            if (vSelection >= vPage) {
               if (vPage <= 0) {
                  vSelection = 0;
               }

               origin.y = -vSelection;
            }

            // update the canvas
            canvas.redraw();
         }

      });

      // - listener that will be called if the control is to be repainted
      canvas.addPaintListener(new PaintListener() {

         @Override
         public void paintControl(final PaintEvent event) {
            final GC gc = event.gc;
            gc.drawImage(image, origin.x, origin.y);

            final Rectangle bounds = image.getBounds();
            final Rectangle client = canvas.getClientArea();

            final int marginWidth = client.width - bounds.width;
            if (marginWidth > 0) {
               gc.fillRectangle(bounds.width, 0, marginWidth, client.height);
            }

            final int marginHeight = client.height - bounds.height;
            if (marginHeight > 0) {
               gc.fillRectangle(0, bounds.height, client.width, marginHeight);
            }
         }

      });

      // - listener to be called when horizontal scroll has been selected
      hBar.addSelectionListener(new SelectionAdapter() {

         @Override
         public void widgetSelected(final SelectionEvent event) {
            final int selection = hBar.getSelection();
            final Rectangle bounds = image.getBounds();
            canvas.scroll(-(selection + origin.x), 0, 0, 0, bounds.width, bounds.height, false);
            origin.x = -selection;
         }

      });

      // - listener to be called when vertical scroll has been selected
      vBar.addSelectionListener(new SelectionAdapter() {

         @Override
         public void widgetSelected(final SelectionEvent event) {
            final int selection = vBar.getSelection();
            final Rectangle bounds = image.getBounds();
            canvas.scroll(0, -(selection + origin.y), 0, 0, bounds.width, bounds.height, false);
            origin.y = -selection;
         }

      });

      return canvas;
   }

   /**
    * Creates the error container.
    * 
    * @param parent the parent
    * 
    * @return the composite
    */
   private Composite createErrorContainer(final Composite parent) {
      final Composite composite = new Composite(parent, SWT.NULL);

      // apply layout
      GRID_DATA_GRAB_ALL.applyTo(composite);
      GridLayoutFactory.swtDefaults().numColumns(2).applyTo(composite);

      // add components
      new Label(composite, SWT.CENTER).setImage(GUI.FIELD_DECORATION_ERROR_IMAGE);
      new Label(composite, SWT.CENTER).setText(NLS.bind(Messages.Err_UnableToOpen, getEditorInput().getName()));

      return composite;
   }

   /**
    * Gets the container.
    * 
    * @return the container
    */
   private Composite getContainer() {
      return this.container;
   }

   /**
    * Gets the image.
    * 
    * @return the image
    */
   private Image getImage() {
      if (this.image == null && getEditorInput() instanceof IURIEditorInput) {
         this.image = new Image(Display.getCurrent(), getImagePath());
      }
      return this.image;
   }

   /**
    * Gets the image name.
    * 
    * @return the image name
    */
   private String getImagePath() {
      try {
         return new File(((IURIEditorInput) getEditorInput()).getURI()).getCanonicalPath();
      } catch (final IOException e) {
         Console.error(e);
      }
      return null;
   }

   /*
    * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
    */
   @Override
   public void doSave(final IProgressMonitor monitor) {
      // nothing to save
   }

   /*
    * @see org.eclipse.ui.part.EditorPart#doSaveAs()
    */
   @Override
   public void doSaveAs() {
      // nothing to save
   }

   /*
    * @see org.eclipse.ui.part.EditorPart#isDirty()
    */
   @Override
   public boolean isDirty() {
      return false;
   }

   /*
    * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
    */
   @Override
   public boolean isSaveAsAllowed() {
      return false;
   }

   /*
    * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
    */
   @Override
   public void setFocus() {
      getContainer().setFocus();
   }

   /*
    * @see org.eclipse.ui.part.WorkbenchPart#dispose()
    */
   @Override
   public void dispose() {
      super.dispose();
      if (getImage() != null) {
         getImage().dispose();
      }
   }

}
