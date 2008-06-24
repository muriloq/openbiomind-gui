/**
 * DefaultTextEditor.java
 *
 * The file DefaultTextEditor.java.
 *
 * TODO THIS FILE IS JUST A STUB. IT NEEDS TO BE REFACTORED AND REDESIGNED.
 *
 * $Id$
 */
package openbiomind.gui.editors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

/**
 * The class DefaultTextEditor.
 *
 * @author bsanghvi
 * @since Jun 24, 2008
 * @version Jun 24, 2008
 */
public class DefaultTextEditor extends EditorPart {

   /** The constant ID. */
   public static final String ID = "openbiomind.gui.editors.DefaultTextEditor";

   /** The text. */
   private Text text = null;

   /*
    * @see org.eclipse.ui.part.MultiPageEditorPart#init(org.eclipse.ui.IEditorSite,
    * org.eclipse.ui.IEditorInput)
    */
   @Override
   public void init(IEditorSite site, IEditorInput input) throws PartInitException {
      setSite(site);
      setInput(input);
      setPartName(input.getName());
   }

   /*
    * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
    */
   @Override
   public void createPartControl(Composite parent) {
      final Font font = new Font(null, "Courier New", 12, SWT.NORMAL);
      this.text = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.READ_ONLY);
      this.text.setFont(font);
      this.text.addDisposeListener(new DisposeListener() {

         @Override
         public void widgetDisposed(DisposeEvent e) {
            if (font != null && !font.isDisposed()) {
               font.dispose();
            }
         }

      });

      final File file = new File(getPartName());
      if (file.isFile() && file.canRead()) {
         try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
               text.append(line + "\n");
            }
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   /*
    * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
    */
   @Override
   public void doSave(IProgressMonitor monitor) {
   }

   /*
    * @see org.eclipse.ui.part.EditorPart#doSaveAs()
    */
   @Override
   public void doSaveAs() {
   }

   /*
    * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
    */
   @Override
   public boolean isSaveAsAllowed() {
      return false;
   }

   /*
    * @see org.eclipse.ui.part.MultiPageEditorPart#setFocus()
    */
   @Override
   public void setFocus() {
   }

   @Override
   public boolean isDirty() {
      return false;
   }

}
