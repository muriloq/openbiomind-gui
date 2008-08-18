/* $Id$ */
/*
 *  OpenBiomind-GUI: GUI for OpenBiomind
 *  Copyright (C) 2008  Bhavesh Sanghvi
 *
 *  This file (Utility.java) is part of OpenBiomind-GUI.
 *
 *  OpenBiomind-GUI is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenBiomind-GUI is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenBiomind-GUI.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Please visit the following pages to contact the author(s):
 *  Homepage: http://code.google.com/p/openbiomind-gui/
 *  Mailing list: http://groups.google.com/group/openbiomind-gui/
 */

package openbiomind.gui.util;

import java.io.Closeable;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import openbiomind.gui.common.Constants;
import openbiomind.gui.console.Console;

/**
 * The class Utility.
 * 
 * @author bsanghvi
 * @since Jun 14, 2008
 * @version Aug 18, 2008
 */
public final class Utility implements Constants {

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

   /**
    * Checks if is empty or existing file.
    * 
    * @param filePath the file path
    * 
    * @return true, if is empty or existing file
    */
   public static boolean isEmptyOrExistingFile(final String filePath) {
      return (isEmpty(filePath) || fileExists(filePath));
   }

   /**
    * Specifies if a file or directory specified by pathname exists or not?.
    * 
    * @param pathName the path name
    * 
    * @return true, if successful
    */
   public static boolean exists(final String pathName) {
      return (!isEmpty(pathName) && new File(pathName).exists());
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
         valid = directoryExists(file);
      }
      return valid;
   }

   /**
    * Directory exists.
    * 
    * @param directory the directory
    * 
    * @return true, if successful
    */
   public static boolean directoryExists(final File directory) {
      return directory.exists() && directory.isDirectory();
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
    * Extract directory. If the given <code>pathName</code> is a possible directory then it is returned. If it is a
    * file, then its parent is returned.
    * 
    * @param pathName the path name
    * 
    * @return the string
    */
   public static String extractDirectory(final String pathName) {
      if (isEmpty(pathName)) {
         return EMPTY;
      }
      if (!isPossibleDirectory(pathName)) {
         return new File(pathName).getParent();
      } else {
         return pathName;
      }
   }

   /**
    * Extract name of the file or the directory from the path. The file name includes extension.
    * 
    * @param pathName the path name
    * 
    * @return the string
    */
   public static String extractFullName(final String pathName) {
      if (isEmpty(pathName)) {
         return EMPTY;
      }
      return new File(pathName).getName();
   }

   /**
    * Extract name of the file without the extension.
    * 
    * @param filePath the file path
    * 
    * @return the string
    */
   public static String extractFileName(final String filePath) {
      if (isEmpty(filePath)) {
         return EMPTY;
      }
      final String name = extractFullName(filePath);
      final int lastIndexOfDot = name.lastIndexOf(DOT); // to remove the extension
      return (lastIndexOfDot > -1 ? name.substring(0, lastIndexOfDot) : name);
   }

   /**
    * List the names of file in the given <code>directory</code> such that the names of the files begin with
    * <code>startsWith</code> and ends with <code>endsWith</code>.
    * 
    * @param directory the directory
    * @param startsWith the starts with
    * @param endsWith the ends with
    * 
    * @return the string[]
    */
   public static String[] listFileNames(final File directory, final String startsWith, final String endsWith) {
      // TODO Check if a check directory.isDirectory() required?
      return directory.list(new FilenameFilter() {

         @Override
         public boolean accept(final File dir, final String name) {
            return (name.startsWith(startsWith) && name.endsWith(endsWith));
         }

      });
   }

   /**
    * Close the closeable resource.
    * 
    * @param closeable the resource to be closed
    * 
    * @return true, if no exception occurred while closing
    */
   public static boolean close(final Closeable closeable) {
      boolean success = true;
      if (closeable != null) {
         try {
            closeable.close();
         } catch (final IOException e) {
            success = false;
            Console.debug(e);
         }
      }

      return success;
   }

}
