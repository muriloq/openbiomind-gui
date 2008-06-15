/**
 * Activator.java
 *
 * The file Activator.java.
 *
 * $Id$
 */
package openbiomind.gui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The class Activator controls the plug-in life cycle.
 *
 * @author bsanghvi
 * @since Jun 1, 2008
 * @version Jun 1, 2008
 */
public class Activator extends AbstractUIPlugin {

   /** The Constant Plug-in ID. */
   public static final String PLUGIN_ID = "openbiomind.gui"; //$NON-NLS-1$

   /** The shared instance. */
   private static Activator plugin = null;

   /**
    * The default constructor.
    */
   public Activator() {
      // empty constructor
   }

   /*
    * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
    */
   @Override
   public void start(final BundleContext context) throws Exception {
      super.start(context);
      plugin = this;
   }

   /*
    * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
    */
   @Override
   public void stop(final BundleContext context) throws Exception {
      plugin = null;
      super.stop(context);
   }

   /**
    * Returns the shared instance.
    *
    * @return the shared instance
    */
   public static Activator getDefault() {
      return plugin;
   }

   /**
    * Returns an image descriptor for the image file at the given plug-in relative path.
    *
    * @param path the path
    *
    * @return the image descriptor
    */
   public static ImageDescriptor getImageDescriptor(final String path) {
      return imageDescriptorFromPlugin(PLUGIN_ID, path);
   }

}
