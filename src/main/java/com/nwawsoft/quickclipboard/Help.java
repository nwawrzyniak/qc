package com.nwawsoft.quickclipboard;

import static com.nwawsoft.quickclipboard.QCConstants.SOFTWARE_LONG_NAME;
import static com.nwawsoft.quickclipboard.QCConstants.SOFTWARE_SHORT_NAME;

/**
 * This class provides the help text functionality.
 */
public class Help {
  /**
   * Prints usage information about this software to stdout.
   */
  public static void printHelp() {
    System.out.println(SOFTWARE_SHORT_NAME + " (" + SOFTWARE_LONG_NAME + ") " + BuildInfo.getVersion() + ":\n");
    System.out.println("Usage:");
    System.out.println("qc [fileName] - Loads the content of a file within ~/.qc/secrets");
    System.out.println("                (names that contain spaces must be quoted with \"\")");
    System.out.println("qc [filePath] - Loads the content of any file");
    System.out.println("                (paths that contain spaces must be quoted with \"\")");
    System.out.println("qc browse     - Opens the file manager in the secrets directory");
    System.out.println("qc clear      - Clears the clipboard");
    System.out.println("qc help       - Displays this help message");
  }
}
