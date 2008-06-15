/**
 * EnhanceDatasetTaskData.java
 *
 * The file EnhanceDatasetTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

import static openbiomind.gui.util.Constants.HYPHEN;
import static openbiomind.gui.util.Constants.QUOTE;

/**
 * The class EnhanceDatasetTaskData is used by the EnhanceDataset task. The syntax of this task is
 * as follows:
 *
 * <pre>
 * task.EnhanceDataset <-d original dataset> <-e enhanced dataset> [-ontologyDescriptionFile ontology description file] [-ontologyAssociationFile ontology association file]
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
      putArgument(ARG_D, QUOTE + originalDataset + QUOTE);
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
      putArgument(ARG_E, QUOTE + enhancedDataset + QUOTE);
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
      putArgument(ARG_ONTOLOGY_DESCRIPTION_FILE, QUOTE + ontologyDescriptionFile + QUOTE);
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
      putArgument(ARG_ONTOLOGY_ASSOCIATION_FILE, QUOTE + ontologyAssociationFile + QUOTE);
   }

//   /** Value <code>original dataset</code> for key {@link #ARG_D}. */
//   public static final String DESC_D = "original dataset";
//
//   /** Value <code>enhanced dataset</code> for key {@link #ARG_E}. */
//   public static final String DESC_E = "enhanced dataset";
//
//   /** Value <code>ontology description file</code> for key {@link #ARG_ONTOLOGY_DESCRIPTION_FILE}. */
//   public static final String DESC_ONTOLOGY_DESCRIPTION_FILE = "ontology description file";
//
//   /** Value <code>ontology association file</code> for key {@link #ARG_ONTOLOGY_ASSOCIATION_FILE}. */
//   public static final String DESC_ONTOLOGY_ASSOCIATION_FILE = "ontology association file";
//
//   /**
//    * The syntax (value =
//    * <code>task.EnhanceDataset <-d original dataset> <-e enhanced dataset> [-ontologyDescriptionFile ontology description file] [-ontologyAssociationFile ontology association file]</code>
//    * ).
//    */
//   private static final String SYNTAX = TASK_NAME + SPACE + ARGUMENT_REQUIRED_LEFT + ARG_D + SPACE
//         + DESC_D + ARGUMENT_REQUIRED_RIGHT + SPACE + ARGUMENT_REQUIRED_LEFT + ARG_E + SPACE
//         + DESC_E + ARGUMENT_REQUIRED_RIGHT + SPACE + ARGUMENT_OPTIONAL_LEFT
//         + ARG_ONTOLOGY_DESCRIPTION_FILE + SPACE + DESC_ONTOLOGY_DESCRIPTION_FILE
//         + ARGUMENT_OPTIONAL_RIGHT + SPACE + ARGUMENT_OPTIONAL_LEFT + ARG_ONTOLOGY_ASSOCIATION_FILE
//         + SPACE + DESC_ONTOLOGY_ASSOCIATION_FILE + ARGUMENT_OPTIONAL_RIGHT;
//
//   /*
//    * @see openbiomind.gui.tasks.AbstractTaskData#getSyntax()
//    */
//   @Override
//   public String getSyntax() {
//      return SYNTAX;
//   }

}
