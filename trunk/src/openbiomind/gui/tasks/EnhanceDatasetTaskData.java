/**
 * EnhanceDatasetTaskData.java
 *
 * The file EnhanceDatasetTaskData.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

import static openbiomind.gui.util.Constants.HYPHEN;

/**
 * The class EnhanceDatasetTaskData is used by the EnhanceDataset task. The syntax of this task is
 * as follows:
 *
 * <pre>
 * task.EnhanceDataset &lt;-d original dataset&gt; &lt;-e enhanced dataset&gt; [-ontologyDescriptionFile ontology description file] [-ontologyAssociationFile ontology association file]&lt;/code&gt;
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
   private static final String TASK_NAME = "task.EnhanceDataset"; //$NON-NLS-1$

   /**
    * Argument <code>-d</code> for specifying the name of original dataset (i.e. the input dataset).
    */
   private static final String ARGUMENT_D = HYPHEN + "d"; //$NON-NLS-1$

   /**
    * Argument <code>-e</code> for specifying name of the name of enhanced dataset (i.e. the output
    * dataset).
    */
   private static final String ARGUMENT_E = HYPHEN + "e"; //$NON-NLS-1$

   /**
    * Argument <code>-ontologyAssociationFile</code> for specifying the name of the ontology
    * description file.
    */
   private static final String ARGUMENT_ONTOLOGY_ASSOCIATION_FILE = HYPHEN
         + "ontologyAssociationFile"; //$NON-NLS-1$

   /**
    * Argument <code>-ontologyDescriptionFile</code> for specifying the name of ontology association
    * file.
    */
   private static final String ARGUMENT_ONTOLOGY_DESCRIPTION_FILE = HYPHEN
         + "ontologyDescriptionFile"; //$NON-NLS-1$

   /**
    * The syntax (value =
    * <code>task.EnhanceDataset <-d original dataset> <-e enhanced dataset> [-ontologyDescriptionFile ontology description file] [-ontologyAssociationFile ontology association file]</code>
    * ).
    */
   private static final String SYNTAX = "task.EnhanceDataset <" + ARGUMENT_D
         + " original dataset> <" + ARGUMENT_E + " enhanced dataset> ["
         + ARGUMENT_ONTOLOGY_DESCRIPTION_FILE + " ontology description file] ["
         + ARGUMENT_ONTOLOGY_ASSOCIATION_FILE + " ontology association file]"; //$NON-NLS-1$

   /**
    * Instantiates a new enhance dataset task.
    */
   public EnhanceDatasetTaskData() {
      super(TASK_NAME);
   }

   /*
    * @see openbiomind.gui.tasks.AbstractTaskData#getSyntax()
    */
   @Override
   public String getSyntax() {
      return SYNTAX;
   }

   /**
    * Gets the original dataset (argument = <code>-d</code>).
    *
    * @return the original dataset
    */
   public String getOriginalDataset() {
      return getArgument(ARGUMENT_D);
   }

   /**
    * Sets the original dataset (argument = <code>-d</code>).
    *
    * @param originalDataset the original dataset
    */
   public void setOriginalDataset(final String originalDataset) {
      putArgument(ARGUMENT_D, originalDataset);
   }

   /**
    * Gets the enhanced dataset (argument = <code>-e</code>).
    *
    * @return the enhanced dataset
    */
   public String getEnhancedDataset() {
      return getArgument(ARGUMENT_E);
   }

   /**
    * Sets the enhanced dataset (argument = <code>-e</code>).
    *
    * @param enhancedDataset the enhnaced dataset
    */
   public void setEnhancedDataset(final String enhancedDataset) {
      putArgument(ARGUMENT_E, enhancedDataset);
   }

   /**
    * Gets the ontology description file (argument = <code>-ontologyDescriptionFile</code>).
    *
    * @return the ontology description file
    */
   public String getOntologyDescriptionFile() {
      return getArgument(ARGUMENT_ONTOLOGY_DESCRIPTION_FILE);
   }

   /**
    * Sets the ontology description file (argument = <code>-ontologyDescriptionFile</code>).
    *
    * @param ontologyDescriptionFile the ontology description file
    */
   public void setOntologyDescriptionFile(final String ontologyDescriptionFile) {
      putArgument(ARGUMENT_ONTOLOGY_DESCRIPTION_FILE, ontologyDescriptionFile);
   }

   /**
    * Gets the ontology association file (argument = <code>-ontologyAssociationFile</code>).
    *
    * @return the ontology association file
    */
   public String getOntologyAssociationFile() {
      return getArgument(ARGUMENT_ONTOLOGY_ASSOCIATION_FILE);
   }

   /**
    * Sets the ontology association file (argument = <code>-ontologyAssociationFile</code>).
    *
    * @param ontologyAssociationFile the ontology association file
    */
   public void setOntologyAssociationFile(final String ontologyAssociationFile) {
      putArgument(ARGUMENT_ONTOLOGY_ASSOCIATION_FILE, ontologyAssociationFile);
   }

}
