/**
 * Perspective.java
 *
 * The file Perspective.java.
 *
 * $Id$
 */
package openbiomind.gui;

import openbiomind.gui.views.CommandConsole;
import openbiomind.gui.views.FileList;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * The class Perspective.
 *
 * @author bsanghvi
 * @since Jun 1, 2008
 * @version Jun 1, 2008
 */
public class Perspective implements IPerspectiveFactory {

   /*
    * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
    */
   @Override
   public void createInitialLayout(final IPageLayout layout) {
      final String editorArea = layout.getEditorArea();

      // // display the editor view
      // layout.setEditorAreaVisible(true);

      /*
       * Bottom
       * - Command Console
       * - Error Log
       */
      final IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.75f,
            editorArea);
      bottom.addView(CommandConsole.ID);
      bottom.addView("org.eclipse.pde.runtime.LogView"); // Error Log

      /*
       * Left
       * - File List
       */
      layout.addView(FileList.ID, IPageLayout.LEFT, 0.25f, editorArea);
   }

}
