/**
 * DatasetClusteringMetricEnum.java
 *
 * The file DatasetClusteringMetricEnum.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

/**
 * The enum DatasetClusteringMetricEnum.
 *
 * @author bsanghvi
 * @since Jul 18, 2008
 * @version Jul 18, 2008
 */
public enum DatasetClusteringMetricEnum {

   /** The <code>Cosine</code> transform. */
   COSINE("Cosine"), //$NON-NLS-1$

   /** The <code>Euclidean</code> transform. */
   EUCLIDEAN("Euclidean"), //$NON-NLS-1$

   /** The <code>SNP</code> transform. */
   SNP("SNP"), //$NON-NLS-1$

   ; // End of allowed values

   /** The name. */
   private String name = null;

   /**
    * The Constructor.
    *
    * @param name the name
    */
   private DatasetClusteringMetricEnum(final String name) {
      this.name = name;
   }

   /**
    * Parses the string to find the DatasetClusteringMetricEnum.
    *
    * @param string the string
    *
    * @return the dataset clustering metric enum
    */
   public static DatasetClusteringMetricEnum parse(final String string) {
      if (COSINE.toString().equals(string)) {
         return COSINE;
      } else if (EUCLIDEAN.toString().equals(string)) {
         return EUCLIDEAN;
      } else if (SNP.toString().equals(string)) {
         return SNP;
      }

      return null;
   }

   /*
    * @see java.lang.Enum#toString()
    */
   @Override
   public String toString() {
      return this.name;
   }

}
