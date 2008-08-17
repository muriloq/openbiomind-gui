/**
 * ApplicationWorkbenchAdvisor.java
 *
 * The file ApplicationWorkbenchAdvisor.java.
 *
 * $Id$
 */
package openbiomind.gui;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.ide.model.WorkbenchAdapterBuilder;
import org.osgi.framework.Bundle;

/**
 * The class ApplicationWorkbenchAdvisor.
 * 
 * @author bsanghvi
 * @since Jun 1, 2008
 * @version Jul 1, 2008
 */
@SuppressWarnings("restriction")
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

   /** The Constant for Perspective ID. */
   private static final String PERSPECTIVE_ID = "openbiomind.gui.Perspective"; //$NON-NLS-1$

   /*
    * @see WorkbenchAdvisor#createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer)
    */
   @Override
   public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(final IWorkbenchWindowConfigurer configurer) {
      return new ApplicationWorkbenchWindowAdvisor(configurer);
   }

   /*
    * @see org.eclipse.ui.application.WorkbenchAdvisor#getInitialWindowPerspectiveId()
    */
   @Override
   public String getInitialWindowPerspectiveId() {
      return PERSPECTIVE_ID;
   }

   /*
    * @see org.eclipse.ui.application.WorkbenchAdvisor#initialize(org.eclipse.ui.application.IWorkbenchConfigurer)
    */
   @Override
   public void initialize(final IWorkbenchConfigurer configurer) {
      super.initialize(configurer);

      // persist the window location and size
      configurer.setSaveAndRestore(true);

      initWorkbench(configurer); // TODO Verify if this is required?
   }

   /*
    * @see org.eclipse.ui.application.WorkbenchAdvisor#getDefaultPageInput()
    */
   @Override
   public IAdaptable getDefaultPageInput() {
      // Get the resource workspace as input
      return ResourcesPlugin.getWorkspace().getRoot();
   }

   /**
    * Inits the workbench. Please refer <a
    * href="http://help.eclipse.org/ganymede/topic/org.eclipse.platform.doc.isv/guide/cnf_rcp.htm"
    * >/topic/org.eclipse.platform.doc.isv/guide/cnf_rcp.htm</a>.
    * 
    * @param configurer the configurer
    */
   private void initWorkbench(final IWorkbenchConfigurer configurer) {
      WorkbenchAdapterBuilder.registerAdapters();

      final String ICONS_PATH = "icons/full/"; //$NON-NLS-1$
      final String PATH_OBJECT = ICONS_PATH + "obj16/"; //$NON-NLS-1$
      final Bundle ideBundle = Platform.getBundle(IDEWorkbenchPlugin.IDE_WORKBENCH);
      declareWorkbenchImage(configurer, ideBundle, IDE.SharedImages.IMG_OBJ_PROJECT, PATH_OBJECT + "prj_obj.gif", true); //$NON-NLS-1$
      declareWorkbenchImage(configurer, ideBundle, IDE.SharedImages.IMG_OBJ_PROJECT_CLOSED, PATH_OBJECT
            + "cprj_obj.gif", true); //$NON-NLS-1$
   }

   /**
    * Declare workbench image.
    * 
    * @param workbenchConfigurer the workbench configurer
    * @param ideBundle the ide bundle
    * @param symbolicName the symbolic name
    * @param path the path
    * @param shared the shared
    */
   private void declareWorkbenchImage(final IWorkbenchConfigurer workbenchConfigurer, final Bundle ideBundle,
         final String symbolicName, final String path, final boolean shared) {
      workbenchConfigurer.declareImage(symbolicName, ImageDescriptor.createFromURL(ideBundle.getEntry(path)), shared);
   }

}
