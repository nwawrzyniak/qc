package com.nwawsoft.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * This class provides functionality to engage with the file manager of the user's OS.
 */
public class FileBrowser {
  /**
   * Opens the specified directory in the file manager.
   * If the software is unable to open the directory it exits with exit code -1.
   *
   * @param path The path of the directory to open in the file browser.
   */
  public static void openDirectory(final String path) {
    try {
      File dir = new File(path);
      if (dir.exists() && dir.isDirectory()) {
        Desktop.getDesktop().open(dir);
      } else {
        System.err.println("The \"secrets\" directory does not exist, is not in its expected place, does not grant permission to enter or the specified path leads to a file instead of a directory.");
      }
    } catch (final IOException e) {
      System.err.println("Could not open directory " + path + " in the file manager.");
      System.exit(-1);
    }
  }
}
