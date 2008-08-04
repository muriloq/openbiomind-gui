/**
 * Perspective.java
 *
 * The file Perspective.java.
 *
 * $Id$
 */
package openbiomind.gui;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * The class Perspective.
 *
 * @author bsanghvi
 * @since Jun 1, 2008
 * @version Jul 30, 2008
 */
public class Perspective implements IPerspectiveFactory {

   /*
    * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
    */
   @Override
   public void createInitialLayout(final IPageLayout layout) {
      // display the editor view
      layout.setEditorAreaVisible(true);
      // layout.setFixed(true);

      /*
       * Add other views
       */
      final String editorArea = layout.getEditorArea();

      /*
       * Left
       * - Results
       */
      layout.addView("openbiomind.gui.views.Results", IPageLayout.LEFT, 0.25f, editorArea); //$NON-NLS-1$

      /*
       * Bottom
       * - Console
       * - Error Log
       */
      final String bottomFolderId = "openbiomind.gui.perspective.folder.Bottom"; //$NON-NLS-1$
      final IFolderLayout bottom = layout.createFolder(bottomFolderId, IPageLayout.BOTTOM, 0.65f, editorArea);
      // Console
      bottom.addView("org.eclipse.ui.console.ConsoleView"); //$NON-NLS-1$
      // Error Log
      // bottom.addView("org.eclipse.pde.runtime.LogView"); //$NON-NLS-1$
   }

}
