/**
 * PairedArgument.java
 *
 * The file PairedArgument.java.
 *
 * $Id$
 */
package openbiomind.gui.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import openbiomind.gui.data.ClassificationMethodEnum;
import openbiomind.gui.data.ClusteringColorsEnum;
import openbiomind.gui.data.DatasetClusteringMetricEnum;
import openbiomind.gui.data.FeatureSelectionMethodEnum;
import openbiomind.gui.data.ShuffleEnum;
import openbiomind.gui.data.TransformEnum;
import openbiomind.gui.util.Utility;

/**
 * The class PairedArgument.
 *
 * @author bsanghvi
 * @since Jul 26, 2008
 * @version Jul 27, 2008
 */
public class PairedArgument {

   /** The argument map. */
   private Map<String, String> argumentMap = null;

   /**
    * Instantiates a new paired arguments.
    */
   public PairedArgument() {
      this.argumentMap = new HashMap<String, String>();
   }

   /**
    * Put object as string. Pass a null value to remove the key.
    *
    * @param key the key
    * @param value the value
    *
    * @return the string
    *
    * @see java.util.Map#put(java.lang.Object, java.lang.Object)
    */
   private String putObjectAsString(final String key, final Object value) {
      return (value != null ? this.argumentMap.put(key, value.toString()) : remove(key));
   }

   /**
    * Gets the value for the specified key.
    *
    * @param key the key
    *
    * @return the string
    *
    * @see java.util.Map#get(java.lang.Object)
    */
   public String get(final String key) {
      return this.argumentMap.get(key);
   }

   /**
    * Put a non-empty string value.
    *
    * @param key the key
    * @param value the value
    *
    * @return the string
    */
   public String put(final String key, final String value) {
      return (!Utility.isEmpty(value) ? putObjectAsString(key, value) : null);
   }

   /**
    * Parses the boolean.
    *
    * @param value the value
    *
    * @return the boolean
    */
   private Boolean parseBoolean(final String value) {
      return (value != null ? Boolean.valueOf(value) : null);
   }

   /**
    * Gets the Boolean value for the given <code>key</code> if available/possible, otherwise return <code>null</code>.
    *
    * @param key the key
    *
    * @return the Boolean value if possible, otherwise <code>null</code>
    */
   public Boolean getBoolean(final String key) {
      return parseBoolean(get(key));
   }

   /**
    * Put.
    *
    * @param key the key
    * @param value the value
    *
    * @return the boolean
    */
   public Boolean put(final String key, final Boolean value) {
      return parseBoolean(putObjectAsString(key, value));
   }

   /**
    * Gets the classification method enum.
    *
    * @param key the key
    *
    * @return the classification method enum
    */
   public ClassificationMethodEnum getClassificationMethodEnum(final String key) {
      return ClassificationMethodEnum.parse(get(key));
   }

   /**
    * Put.
    *
    * @param key the key
    * @param value the value
    *
    * @return the classification method enum
    */
   public ClassificationMethodEnum put(final String key, final ClassificationMethodEnum value) {
      return ClassificationMethodEnum.parse(putObjectAsString(key, value));
   }

   /**
    * Gets the clustering colors enum.
    *
    * @param key the key
    *
    * @return the clustering colors enum
    */
   public ClusteringColorsEnum getClusteringColorsEnum(final String key) {
      return ClusteringColorsEnum.parse(get(key));
   }

   /**
    * Put.
    *
    * @param key the key
    * @param value the value
    *
    * @return the clustering colors enum
    */
   public ClusteringColorsEnum put(final String key, final ClusteringColorsEnum value) {
      return ClusteringColorsEnum.parse(putObjectAsString(key, value));
   }

   /**
    * Gets the dataset clustering metric enum.
    *
    * @param key the key
    *
    * @return the dataset clustering metric enum
    */
   public DatasetClusteringMetricEnum getDatasetClusteringMetricEnum(final String key) {
      return DatasetClusteringMetricEnum.parse(get(key));
   }

   /**
    * Put.
    *
    * @param key the key
    * @param value the value
    *
    * @return the dataset clustering metric enum
    */
   public DatasetClusteringMetricEnum put(final String key, final DatasetClusteringMetricEnum value) {
      return DatasetClusteringMetricEnum.parse(putObjectAsString(key, value));
   }

   /**
    * Gets the feature selection method enum.
    *
    * @param key the key
    *
    * @return the feature selection method enum
    */
   public FeatureSelectionMethodEnum getFeatureSelectionMethodEnum(final String key) {
      return FeatureSelectionMethodEnum.parse(get(key));
   }

   /**
    * Put.
    *
    * @param key the key
    * @param value the value
    *
    * @return the feature selection method enum
    */
   public FeatureSelectionMethodEnum put(final String key, final FeatureSelectionMethodEnum value) {
      return FeatureSelectionMethodEnum.parse(putObjectAsString(key, value));
   }

   /**
    * Parses the integer.
    *
    * @param value the value
    *
    * @return the integer
    */
   private Integer parseInteger(final String value) {
      if (value != null) {
         try {
            return Integer.valueOf(value);
         } catch (final NumberFormatException e) {
            // suppress exception
         }
      }

      return null;
   }

   /**
    * Gets the Integer value for the given <code>key</code> if available/possible, otherwise return <code>null</code>.
    *
    * @param key the key
    *
    * @return the Integer value if possible, otherwise <code>null</code>
    */
   public Integer getInteger(final String key) {
      return parseInteger(get(key));
   }

   /**
    * Put.
    *
    * @param key the key
    * @param value the value
    *
    * @return the integer
    */
   public Integer put(final String key, final Integer value) {
      return parseInteger(putObjectAsString(key, value));
   }

   /**
    * Gets the shuffle enum.
    *
    * @param key the key
    *
    * @return the shuffle enum
    */
   public ShuffleEnum getShuffleEnum(final String key) {
      return ShuffleEnum.parse(get(key));
   }

   /**
    * Put.
    *
    * @param key the key
    * @param value the value
    *
    * @return the shuffle enum
    */
   public ShuffleEnum put(final String key, final ShuffleEnum value) {
      return ShuffleEnum.parse(putObjectAsString(key, value));
   }

   /**
    * Gets the transform enum.
    *
    * @param key the key
    *
    * @return the transform enum
    */
   public TransformEnum getTransformEnum(final String key) {
      return TransformEnum.parse(get(key));
   }

   /**
    * Put.
    *
    * @param key the key
    * @param value the value
    *
    * @return the transform enum
    */
   public TransformEnum put(final String key, final TransformEnum value) {
      return TransformEnum.parse(putObjectAsString(key, value));
   }

   /**
    * Removes the key.
    *
    * @param key the key
    *
    * @return the string
    *
    * @see java.util.Map#remove(java.lang.Object)
    */
   public String remove(final String key) {
      return this.argumentMap.remove(key);
   }

   /**
    * This method converts the currently stored arguments into a list of the form
    * <code>[key1 value1, key2 value2, ...]</code>. Hence, the size of this list is same as the number of arguments
    * presently stored. To get on a list of all elements, use {@link #toList()}.
    *
    * @return the list of arguments
    *
    * @see #toList()
    */
   public List<String> toArgumentList() {
      final Set<Entry<String, String>> entrySet = this.argumentMap.entrySet();
      final List<String> allArgumentList = new ArrayList<String>(entrySet.size());

      for (final Entry<String, String> entry : entrySet) {
         allArgumentList.add(entry.getKey() + Constants.SPACE + entry.getValue());
      }

      return allArgumentList;
   }

   /**
    * This returns a string of the form <code>key1 value1 key2 value2 ...</code>.
    *
    * @return the argument string
    */
   public String toArgumentString() {
      final StringBuilder stringBuilder = new StringBuilder(Constants.EMPTY);
      final List<String> argumentsList = toArgumentList();
      for (final String argument : argumentsList) {
         stringBuilder.append(argument + Constants.SPACE);
      }
      return stringBuilder.toString().trim();
   }

   /**
    * This method converts the currently stored arguments into a list of the form
    * <code>[key1, value1, key2, value2, ...]</code>. Hence, the size of this list is twice the number of arguments
    * presently stored. To get a space separated list of arguments, use {@link #toArgumentList()}.
    *
    * @return the list of arguments
    *
    * @see #toArgumentList()
    */
   public List<String> toList() {
      final Set<Entry<String, String>> entrySet = this.argumentMap.entrySet();
      final List<String> allArgumentList = new ArrayList<String>(entrySet.size());

      for (final Entry<String, String> entry : entrySet) {
         allArgumentList.add(entry.getKey());
         allArgumentList.add(entry.getValue());
      }

      return allArgumentList;
   }

   /*
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      return toArgumentString();
   }

   /*
    * @see java.lang.Object#equals(java.lang.Object)
    *
    * @see java.util.Map#equals(java.lang.Object)
    */
   @Override
   public boolean equals(final Object otherObject) {
      return this.argumentMap.equals(otherObject);
   }

   /*
    * @see java.lang.Object#hashCode()
    *
    * @see java.util.Map#hashCode()
    */
   @Override
   public int hashCode() {
      return this.argumentMap.hashCode();
   }

}
