/**
 * DefaultTextEditorInput.java
 *
 * The file DefaultTextEditorInput.java.
 *
 * TODO THIS FILE IS JUST A STUB. IT NEEDS TO BE REFACTORED AND REDESIGNED.
 *
 * $Id$
 */
package openbiomind.gui.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * The class DefaultTextEditorInput.
 *
 * @author bsanghvi
 * @since Jun 24, 2008
 * @version Jun 24, 2008
 */
public class DefaultTextEditorInput implements IEditorInput {

   /** The file name. */
   private final String filepath;

   /**
    * Instantiates a new default text editor input.
    *
    * @param fileName the file name
    */
   public DefaultTextEditorInput(String fileName) {
      this.filepath = fileName;
   }

   /*
    * @see org.eclipse.ui.IEditorInput#exists()
    */
   @Override
   public boolean exists() {
      return false;
   }

   /*
    * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
    */
   @Override
   public ImageDescriptor getImageDescriptor() {
      return null;
   }

   /*
    * @see org.eclipse.ui.IEditorInput#getName()
    */
   @Override
   public String getName() {
      return this.filepath;
   }

   /*
    * @see org.eclipse.ui.IEditorInput#getPersistable()
    */
   @Override
   public IPersistableElement getPersistable() {
      return null;
   }

   /*
    * @see org.eclipse.ui.IEditorInput#getToolTipText()
    */
   @Override
   public String getToolTipText() {
      return getName();
   }

   /*
    * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
    */
   @Override
   public Object getAdapter(final Class adapter) {
      return null;
   }

   /*
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(Object obj) {
      if (super.equals(obj)) {
         return true;
      }

      if (obj instanceof DefaultTextEditorInput) {
         return this.filepath.equals(((DefaultTextEditorInput) obj).getName());
      }

      return false;
   }

   /*
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      return this.filepath.hashCode();
   }

}
