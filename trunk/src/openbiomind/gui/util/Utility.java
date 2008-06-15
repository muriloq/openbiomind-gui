/**
 * Utility.java
 *
 * The file Utility.java.
 *
 * $Id$
 */
package openbiomind.gui.util;

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
      return new File(pathname).exists();
   }

}
