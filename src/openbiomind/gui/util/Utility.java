/**
 * Utility.java
 *
 * The file Utility.java.
 *
 * $Id$
 */
package openbiomind.gui.util;

import static openbiomind.gui.util.Constants.EMPTY;

import java.io.File;

/**
 * The class Utility.
 *
 * @author bsanghvi
 * @since Jun 14, 2008
 * @version Jun 14, 2008
 */
public final class Utility {

   /**
    * Specifies if a file or directory specified by pathname exists or not?
    *
    * @param pathname the pathname
    *
    * @return true, if successful
    */
   public static boolean exists(final String pathname) {
      return (!isEmpty(pathname) && new File(pathname).exists());
   }

   /**
    * Extract directory name.
    *
    * @param pathname the pathname
    *
    * @return the string
    */
   public static String extractDirectoryName(final String pathname) {
      if (isEmpty(pathname)) {
         return EMPTY;
      }
      if (!isPossibleDirectory(pathname)) {
         return new File(pathname).getParent();
      } else {
         return pathname;
      }
   }

   /**
    * File exists.
    *
    * @param pathname the pathname
    *
    * @return true, if successful
    */
   public static boolean fileExists(final String pathname) {
      boolean valid = false;
      if (!isEmpty(pathname)) {
         final File file = new File(pathname);
         valid = file.exists() && file.isFile();
      }
      return valid;
   }

   /**
    * Directory exists.
    *
    * @param pathname the pathname
    *
    * @return true, if successful
    */
   public static boolean directoryExists(final String pathname) {
      boolean valid = false;
      if (!isEmpty(pathname)) {
         final File file = new File(pathname);
         valid = file.exists() && file.isDirectory();
      }
      return valid;
   }

   /**
    * Checks if pathname can be a file or not?
    *
    * @param pathname the pathname
    *
    * @return true, if pathname can be a file
    */
   public static boolean isPossibleFile(final String pathname) {
      return (!isEmpty(pathname) && isPossibleFile(new File(pathname)));
   }

   /**
    * Checks if is possible file.
    *
    * @param file the file
    *
    * @return true, if is possible file
    */
   public static boolean isPossibleFile(final File file) {
      return !file.isDirectory();
   }

   /**
    * Checks if pathname can be a directory or not?
    *
    * @param pathname the pathname
    *
    * @return true, if pathname can be a directory
    */
   public static boolean isPossibleDirectory(final String pathname) {
      return (!isEmpty(pathname) && isPossibleDirectory(new File(pathname)));
   }

   /**
    * Checks if is possible directory.
    *
    * @param file the file
    *
    * @return true, if is possible directory
    */
   public static boolean isPossibleDirectory(final File file) {
      return !file.isFile();
   }

   /**
    * Checks if is the string empty.
    *
    * @param string the string
    *
    * @return true, if is empty
    */
   public static boolean isEmpty(final String string) {
      return (string == null || string.trim().isEmpty());
   }

}
