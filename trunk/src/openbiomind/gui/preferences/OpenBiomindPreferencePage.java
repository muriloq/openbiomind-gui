/**
 * OpenBiomindPreferencePage.java
 *
 * The file OpenBiomindPreferencePage.java.
 *
 * $Id$
 */
package openbiomind.gui.preferences;

import openbiomind.gui.Activator;
import openbiomind.gui.common.Constants;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
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
 * @version Aug 13, 2008
 */
public class OpenBiomindPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage, Constants {

   /** The OpenBiomind jar file field editor. */
   private FileFieldEditor openBiomindJarFileFieldEditor = null;

   /** The OpenBiomind jar valid. */
   private boolean openBiomindJarValid = false;

   /** The pipeline.properties file field editor. */
   private FileFieldEditor pipelinePropertiesFileFieldEditor = null;

   /** The pipeline.properties valid. */
   private boolean pipelinePropertiesValid = false;

   /** The Graphviz dot utility file field editor. */
   private FileFieldEditor graphvizDotUtilityFileFieldEditor = null;

   /** The Graphviz dot utility valid. */
   private boolean graphvizDotUtilityValid = false;

   /**
    * Instantiates a new OpenBiomind preference page.
    */
   public OpenBiomindPreferencePage() {
      super(FieldEditorPreferencePage.GRID);
      setPreferenceStore(Activator.getDefault().getPreferenceStore());
      setDescription(Messages.OpenBiomindPrefPage_Desc);
   }

   /*
    * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
    */
   @Override
   public void init(final IWorkbench workbench) {
      // do nothing
   }

   /*
    * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
    */
   @Override
   public void createFieldEditors() {
      // OpenBiomind jar
      setOpenBiomindJarFileFieldEditor(createNewFileFieldEditor(Preference.OPENBIOMIND_JAR,
            Messages.OpenBiomindPrefPage_Label_OpenBiomindJar, new String[] { WILDCARD_ANY + Resources.JAR_EXTENSION },
            false, Messages.OpenBiomindPrefPage_Err_OpenBiomindJAR));
      addField(getOpenBiomindJarFileFieldEditor());
      setOpenBiomindJarValid(Preference.isOpenBiomindJarPreferenceValid());

      // pipeline.properties
      setPipelinePropertiesFileFieldEditor(createNewFileFieldEditor(Preference.PIPELINE_PROPERTIES,
            Messages.OpenBiomindPrefPage_Label_PipelineProp, new String[] { Resources.PIPELINE_PROPERTIES_FILENAME },
            false, Messages.OpenBiomindPrefPage_Err_PipelineProp));
      addField(getPipelinePropertiesFileFieldEditor());
      setPipelinePropertiesValid(Preference.isPipelinePropertiesPreferenceValid());

      // Graphviz dot utility
      setGraphvizDotUtilityFileFieldEditor(createNewFileFieldEditor(Preference.GRAPHVIZ_DOT_UTILITY,
            Messages.OpenBiomindPrefPage_Label_GraphvizDotUtil, new String[] { Resources.GRAPHVIZ_DOT_UTILITY_NAME
                  + FILE_FILTER_SEPARATOR + Resources.GRAPHVIZ_DOT_UTILITY_NAME + DOT + WILDCARD_ANY }, true,
            Messages.OpenBiomindPrefPage_Err_GraphvizDotUtil));
      addField(getGraphvizDotUtilityFileFieldEditor());
      setGraphvizDotUtilityValid(Preference.isGraphvizDotUtilityPreferenceValid());
   }

   /*
    * @see org.eclipse.jface.preference.FieldEditorPreferencePage#setVisible(boolean)
    */
   @Override
   public void setVisible(final boolean visible) {
      if (visible) {
         validate();
      }
      super.setVisible(visible);
   }

   /**
    * Creates the new file field editor.
    *
    * @param preferenceName the preference name
    * @param labelText the label text
    * @param fileExtensions the file extensions
    * @param emptyStringAllowed the empty string allowed
    * @param errorMessage the error message
    *
    * @return the file field editor
    */
   private FileFieldEditor createNewFileFieldEditor(final String preferenceName, final String labelText,
         final String[] fileExtensions, final boolean emptyStringAllowed, final String errorMessage) {
      final FileFieldEditor fileFieldEditor = new FileFieldEditor(preferenceName, labelText, true,
            StringFieldEditor.VALIDATE_ON_KEY_STROKE, getFieldEditorParent());
      fileFieldEditor.setFileExtensions(fileExtensions);
      fileFieldEditor.setEmptyStringAllowed(emptyStringAllowed);
      fileFieldEditor.setErrorMessage(errorMessage);
      return fileFieldEditor;
   }

   /*
    * @see org.eclipse.jface.preference.FieldEditorPreferencePage#propertyChange(org.eclipse.jface.util
    * .PropertyChangeEvent)
    */
   @Override
   public void propertyChange(final PropertyChangeEvent event) {
      super.propertyChange(event);
      final Object eventSource = event.getSource();
      if (eventSource instanceof FileFieldEditor) {
         final FileFieldEditor fileFieldEditor = (FileFieldEditor) eventSource;
         final String preferenceName = fileFieldEditor.getPreferenceName();
         final String value = fileFieldEditor.getStringValue();
         if (Preference.OPENBIOMIND_JAR.equals(preferenceName)) {
            setOpenBiomindJarValid(Preference.isOpenBiomindJarPreferenceValid(value));
         } else if (Preference.PIPELINE_PROPERTIES.equals(preferenceName)) {
            setPipelinePropertiesValid(Preference.isPipelinePropertiesPreferenceValid(value));
         } else if (Preference.GRAPHVIZ_DOT_UTILITY.equals(preferenceName)) {
            setGraphvizDotUtilityValid(Preference.isGraphvizDotUtilityPreferenceValid(value));
         }
      }

      validate();
   }

   /**
    * Validate.
    */
   private void validate() {
      boolean valid = false;
      if (!isOpenBiomindJarValid()) {
         getOpenBiomindJarFileFieldEditor().showErrorMessage();
      } else if (!isPipelinePropertiesValid()) {
         getPipelinePropertiesFileFieldEditor().showErrorMessage();
      } else if (!isGraphvizDotUtilityValid()) {
         getGraphvizDotUtilityFileFieldEditor().showErrorMessage();
      } else {
         valid = true;
      }
      setValid(valid);
   }

   /**
    * Gets the OpenBiomind jar file field editor.
    *
    * @return the OpenBiomind jar file field editor
    */
   private FileFieldEditor getOpenBiomindJarFileFieldEditor() {
      return this.openBiomindJarFileFieldEditor;
   }

   /**
    * Sets the OpenBiomind jar file field editor.
    *
    * @param openBiomindJarFileFieldEditor the new OpenBiomind jar file field editor
    */
   private void setOpenBiomindJarFileFieldEditor(final FileFieldEditor openBiomindJarFileFieldEditor) {
      this.openBiomindJarFileFieldEditor = openBiomindJarFileFieldEditor;
   }

   /**
    * Checks if OpenBiomind jar preference is valid.
    *
    * @return true, if OpenBiomind jar preference is valid
    */
   private boolean isOpenBiomindJarValid() {
      return this.openBiomindJarValid;
   }

   /**
    * Sets the OpenBiomind jar valid.
    *
    * @param validOpenBiomindJar the new OpenBiomind jar preference valid
    */
   private void setOpenBiomindJarValid(final boolean validOpenBiomindJar) {
      this.openBiomindJarValid = validOpenBiomindJar;
   }

   /**
    * Gets the pipeline.properties file field editor.
    *
    * @return the pipeline.properties file field editor
    */
   private FileFieldEditor getPipelinePropertiesFileFieldEditor() {
      return this.pipelinePropertiesFileFieldEditor;
   }

   /**
    * Sets the pipeline.properties file field editor.
    *
    * @param pipelinePropertiesFileFieldEditor the new pipeline.properties file field editor
    */
   private void setPipelinePropertiesFileFieldEditor(final FileFieldEditor pipelinePropertiesFileFieldEditor) {
      this.pipelinePropertiesFileFieldEditor = pipelinePropertiesFileFieldEditor;
   }

   /**
    * Checks if pipeline.properties preference is valid.
    *
    * @return true, if pipeline.properties preference is valid
    */
   private boolean isPipelinePropertiesValid() {
      return this.pipelinePropertiesValid;
   }

   /**
    * Sets the pipeline.properties preference valid.
    *
    * @param validPipelineProperties the new pipeline.properties valid
    */
   private void setPipelinePropertiesValid(final boolean validPipelineProperties) {
      this.pipelinePropertiesValid = validPipelineProperties;
   }

   /**
    * Gets the Graphviz dot utility file field editor.
    *
    * @return the Graphviz dot utility file field editor
    */
   private FileFieldEditor getGraphvizDotUtilityFileFieldEditor() {
      return this.graphvizDotUtilityFileFieldEditor;
   }

   /**
    * Sets the Graphviz dot utility file field editor.
    *
    * @param graphvizDotUtilityFileFieldEditor the new Graphviz dot utility file field editor
    */
   private void setGraphvizDotUtilityFileFieldEditor(final FileFieldEditor graphvizDotUtilityFileFieldEditor) {
      this.graphvizDotUtilityFileFieldEditor = graphvizDotUtilityFileFieldEditor;
   }

   /**
    * Checks if Graphviz dot utility preference is valid.
    *
    * @return true, if Graphviz dot utility preference is valid
    */
   private boolean isGraphvizDotUtilityValid() {
      return this.graphvizDotUtilityValid;
   }

   /**
    * Sets the Graphviz dot utility preference valid.
    *
    * @param validGraphvizDotUtility the new Graphviz dot utility valid
    */
   private void setGraphvizDotUtilityValid(final boolean validGraphvizDotUtility) {
      this.graphvizDotUtilityValid = validGraphvizDotUtility;
   }

}
