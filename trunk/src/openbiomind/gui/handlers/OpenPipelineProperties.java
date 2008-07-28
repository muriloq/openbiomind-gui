/**
 * OpenPipelineProperties.java
 *
 * The file OpenPipelineProperties.java.
 *
 * $Id$
 */
package openbiomind.gui.handlers;

import java.io.File;

import openbiomind.gui.common.Constants;
import openbiomind.gui.console.Console;
import openbiomind.gui.preferences.Preference;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.ide.IDE;

/**
 * The class OpenPipelineProperties.
 *
 * @author bsanghvi
 * @since Jul 8, 2008
 * @version Jul 24, 2008
 */
public class OpenPipelineProperties extends AbstractHandler {

   /*
    * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
    */
   @Override
   public Object execute(final ExecutionEvent event) throws ExecutionException {
      // TODO Identify a way to open in Properties File Editor. The following editor must not be used, since it needs
      // dependency on JDT
      // Properties File Editor: org.eclipse.jdt.ui.PropertiesFileEditor
      final File file = new File(Preference.getPipelinePropertiesPath());
      try {
         IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), new FileStoreEditorInput(
               EFS.getLocalFileSystem().getStore(file.toURI())), Constants.Properties.DEFAULT_TEXT_EDITOR_ID);
      } catch (final PartInitException e) {
         Console.debug(e);
      }

      return null;
   }

   /*
    * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
    */
   @Override
   public boolean isEnabled() {
      return Preference.isPipelinePropertiesPreferenceValid();
   }

}
