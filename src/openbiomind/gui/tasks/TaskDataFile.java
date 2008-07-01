/**
 * TaskDataFile.java
 *
 * The file TaskDataFile.java.
 *
 * $Id$
 */
package openbiomind.gui.tasks;

import static openbiomind.gui.util.Constants.EMPTY;

/**
 * The class TaskDataFile.
 *
 * @author bsanghvi
 * @since Jun 30, 2008
 * @version Jun 30, 2008
 */
public class TaskDataFile {

   /** The pathname. */
   private String pathname = null;

   /** The auto open. */
   private boolean autoOpen = false;

   /**
    * Instantiates a new task data file.
    */
   public TaskDataFile() {
      this(EMPTY, false);
   }

   /**
    * Instantiates a new task data file and sets autoOpen to <code>false</code>.
    *
    * @param pathname the pathname
    */
   public TaskDataFile(final String pathname) {
      this(pathname, false);
   }

   /**
    * The constructor.
    *
    * @param pathname the pathname
    * @param autoOpen the auto open
    */
   public TaskDataFile(final String pathname, final boolean autoOpen) {
      setPathname(pathname);
      this.autoOpen = autoOpen;
   }

   /**
    * Gets the pathname.
    *
    * @return the pathname
    */
   public String getPathname() {
      return this.pathname;
   }

   /**
    * Sets the pathname.
    *
    * @param pathname the pathname to set
    */
   public void setPathname(final String pathname) {
      if (pathname != null) {
         this.pathname = pathname;
      } else {
         this.pathname = EMPTY;
      }
   }

   /**
    * Checks if is auto open.
    *
    * @return the autoOpen
    */
   public boolean isAutoOpen() {
      return this.autoOpen;
   }

   /**
    * Sets the auto open.
    *
    * @param autoOpen the autoOpen to set
    */
   public void setAutoOpen(final boolean autoOpen) {
      this.autoOpen = autoOpen;
   }

   /*
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      return getPathname();
   }

   /*
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(final Object otherObject) {
      if (otherObject != null && otherObject instanceof TaskDataFile) {
         return getPathname().equals(((TaskDataFile) otherObject).getPathname());
      }

      return false;
   }

}
