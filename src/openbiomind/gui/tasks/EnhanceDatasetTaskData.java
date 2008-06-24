/**
 * EnhanceDatasetTaskData.java
 *
 * The file EnhanceDatasetTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

import static openbiomind.gui.util.Constants.HYPHEN;
import openbiomind.gui.util.Utility;

/**
 * The class EnhanceDatasetTaskData is used by the EnhanceDataset task. The syntax of this task is
 * as follows:
 *
 * <pre>
 * task.EnhanceDataset &lt;-d original dataset&gt; &lt;-e enhanced dataset&gt; [-ontologyDescriptionFile ontology description file] [-ontologyAssociationFile ontology association file]
 * </pre>
 *
 * @author bsanghvi
 * @since Jun 12, 2008
 * @version Jun 12, 2008
 */
public class EnhanceDatasetTaskData extends AbstractTaskData {

   /** The Constant serialVersionUID. */
   private static final long serialVersionUID = 1L;

   /**
    * Argument <code>-d</code> for specifying the name of original dataset (i.e. the input dataset).
    */
   public static final String TASK_NAME = "task.EnhanceDataset"; //$NON-NLS-1$

   /**
    * Argument <code>-d</code> for specifying the name of original dataset (i.e. the input dataset).
    */
   public static final String ARG_D = HYPHEN + "d"; //$NON-NLS-1$

   /**
    * Argument <code>-e</code> for specifying name of the name of enhanced dataset (i.e. the output
    * dataset).
    */
   public static final String ARG_E = HYPHEN + "e"; //$NON-NLS-1$

   /**
    * Argument <code>-ontologyDescriptionFile</code> for specifying the name of ontology association
    * file.
    */
   public static final String ARG_ONTOLOGY_DESCRIPTION_FILE = HYPHEN + "ontologyDescriptionFile"; //$NON-NLS-1$

   /**
    * Argument <code>-ontologyAssociationFile</code> for specifying the name of the ontology
    * description file.
    */
   public static final String ARG_ONTOLOGY_ASSOCIATION_FILE = HYPHEN + "ontologyAssociationFile"; //$NON-NLS-1$

   /**
    * Instantiates a new enhance dataset task.
    */
   public EnhanceDatasetTaskData() {
      super(TASK_NAME);
   }

   /**
    * Gets the original dataset (argument = <code>-d</code>).
    *
    * @return the original dataset
    */
   public String getOriginalDataset() {
      return getArgument(ARG_D);
   }

   /**
    * Sets the original dataset (argument = <code>-d</code>).
    *
    * @param originalDataset the original dataset
    */
   public void setOriginalDataset(final String originalDataset) {
      if (!Utility.isEmpty(originalDataset)) {
         putArgument(ARG_D, originalDataset);
      }
   }

   /**
    * Gets the enhanced dataset (argument = <code>-e</code>).
    *
    * @return the enhanced dataset
    */
   public String getEnhancedDataset() {
      return getArgument(ARG_E);
   }

   /**
    * Sets the enhanced dataset (argument = <code>-e</code>).
    *
    * @param enhancedDataset the enhnaced dataset
    */
   public void setEnhancedDataset(final String enhancedDataset) {
      if (!Utility.isEmpty(enhancedDataset)) {
         putArgument(ARG_E, enhancedDataset);
      }
   }

   /**
    * Gets the ontology description file (argument = <code>-ontologyDescriptionFile</code>).
    *
    * @return the ontology description file
    */
   public String getOntologyDescriptionFile() {
      return getArgument(ARG_ONTOLOGY_DESCRIPTION_FILE);
   }

   /**
    * Sets the ontology description file (argument = <code>-ontologyDescriptionFile</code>).
    *
    * @param ontologyDescriptionFile the ontology description file
    */
   public void setOntologyDescriptionFile(final String ontologyDescriptionFile) {
      if (!Utility.isEmpty(ontologyDescriptionFile)) {
         putArgument(ARG_ONTOLOGY_DESCRIPTION_FILE, ontologyDescriptionFile);
      }
   }

   /**
    * Gets the ontology association file (argument = <code>-ontologyAssociationFile</code>).
    *
    * @return the ontology association file
    */
   public String getOntologyAssociationFile() {
      return getArgument(ARG_ONTOLOGY_ASSOCIATION_FILE);
   }

   /**
    * Sets the ontology association file (argument = <code>-ontologyAssociationFile</code>).
    *
    * @param ontologyAssociationFile the ontology association file
    */
   public void setOntologyAssociationFile(final String ontologyAssociationFile) {
      if (!Utility.isEmpty(ontologyAssociationFile)) {
         putArgument(ARG_ONTOLOGY_ASSOCIATION_FILE, ontologyAssociationFile);
      }
   }

   /*
    * @see openbiomind.gui.tasks.AbstractTaskData#getFilesArray()
    */
   @Override
   public String[] getFilesArray() {
      return new String[] { getEnhancedDataset() };
//      return new String[] { getEnhancedDataset(), getOriginalDataset(),
//            getOntologyDescriptionFile(), getOntologyAssociationFile() };
   }

}
