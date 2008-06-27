/**
 * OpenBiomindPreferencePage.java
 *
 * The file OpenBiomindPreferencePage.java.
 *
 * $Id$
 */
package openbiomind.gui.preferences;

import openbiomind.gui.Activator;
import openbiomind.gui.util.Constants;
import openbiomind.gui.util.Messages;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The class OpenBiomindPreferencePage represents a preference page that is contributed to the Preferences dialog.
 *
 * <p>
 * This page is used to modify preferences only. They are stored in the preference store that belongs to the main
 * plug-in class. That way, preferences can be accessed directly via the preference store.
 * </p>
 *
 * @author bsanghvi
 * @since Jun 9, 2008
 * @version Jun 27, 2008
 */
public class OpenBiomindPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage, Constants {

   /**
    * Instantiates a new OpenBiomind preference page.
    */
   public OpenBiomindPreferencePage() {
      super(GRID);
      setPreferenceStore(Activator.getDefault().getPreferenceStore());
      setDescription(Messages.Desc_PreferencePage);
   }

   /*
    * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
    */
   @Override
   public void createFieldEditors() {
      /*
       * openbiomind.jar
       */
      final FileFieldEditor openBiomindJarFileFieldEditor = new FileFieldEditor(Preference.OPENBIOMIND_JAR, AMPERSAND
            + Resources.OPENBIOMIND_JAR_NAME + LABEL_SEPARATOR, getFieldEditorParent());
      openBiomindJarFileFieldEditor.setFileExtensions(new String[] { WILDCARD_ANY + Resources.JAR_EXTENSION });
      addField(openBiomindJarFileFieldEditor);

      /*
       * pipeline.properties
       */
      final FileFieldEditor pipelinePropertiesFileFieldEditor = new FileFieldEditor(Preference.PIPELINE_PROPERTIES,
            AMPERSAND + Resources.PIPELINE_PROPERTIES_FILENAME + LABEL_SEPARATOR, getFieldEditorParent());
      pipelinePropertiesFileFieldEditor.setFileExtensions(new String[] { Resources.PIPELINE_PROPERTIES_FILENAME });
      addField(pipelinePropertiesFileFieldEditor);
   }

   /*
    * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
    */
   @Override
   public void init(final IWorkbench workbench) {
   }

   /*
    * @see org.eclipse.jface.preference.FieldEditorPreferencePage#propertyChange(org.eclipse.jface.util
    * .PropertyChangeEvent)
    */
   @Override
   public void propertyChange(final PropertyChangeEvent event) {
      super.propertyChange(event);

      boolean valid = true;

      final Object eventSource = event.getSource();
      if (eventSource instanceof FileFieldEditor) {
         final FileFieldEditor fileFieldEditor = (FileFieldEditor) eventSource;
         final String preferenceName = fileFieldEditor.getPreferenceName();
         final String value = fileFieldEditor.getStringValue();

         if (Preference.OPENBIOMIND_JAR.equals(preferenceName)) {
            if (!Preference.isOpenBiomindJarPreferenceValid(value)) {
               valid = false;
               setErrorMessage(Messages.Error_Preference_OpenBiomindJAR);
            }
         } else if (Preference.PIPELINE_PROPERTIES.equals(preferenceName)) {
            if (!Preference.isPipelinePropertiesPreferenceValid(value)) {
               valid = false;
               setErrorMessage(Messages.Error_Preference_PipelineProperties);
            }
         }
      }

      setValid(valid);
   }

}
