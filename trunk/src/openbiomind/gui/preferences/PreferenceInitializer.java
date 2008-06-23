/**
 * PreferenceInitializer.java
 *
 * The file PreferenceInitializer.java.
 *
 * $Id$
 */
package openbiomind.gui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

/**
 * The class PreferenceInitializer is used to initialize default preference values.
 *
 * @author bsanghvi
 * @since Jun 9, 2008
 * @version Jun 9, 2008
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

   /*
    * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
    */
   @Override
   public void initializeDefaultPreferences() {
      /*
       * Default preferences can be set here using org.eclipse.jface.preference.IPreferenceStore and
       * openbiomind.gui.Activator as follows
       *
       * final IPreferenceStore store = Activator.getDefault().getPreferenceStore();
       *
       * store.setDefault(Preference.KEY, value);
       */
      /*
       * This can be used to read preferences from a configuration file
       */
   }

}
