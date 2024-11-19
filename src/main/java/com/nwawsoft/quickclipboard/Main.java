package com.nwawsoft.quickclipboard;

import com.nwawsoft.util.FileBrowser;
import com.nwawsoft.util.SimpleLogger;
import com.nwawsoft.util.StringArrayFunctions;

import java.io.File;

import static com.nwawsoft.quickclipboard.QCConstants.*;

/**
 * Main function of qc.
 * This class primarily handles the CLI capabilities of the software and leverages the corresponding module.
 */
public class Main {
  /**
   * The entry point into qc.
   * On first start of qc the software sets up the directories where the secrets and logs are stored and creates a default secret.
   * Whenever these directories and/or files are missing they are recreated on the next use.
   * If no arguments are passed the default secret ist loaded.
   * If two or more arguments are passed the software will inform the user that too many arguments were specified. Also, it exits with exit code -1.
   * If exactly one argument is passed the software will behave depending on this argument:
   * 1) If the only argument is "help" usage information will be printed and the software exits.
   * 2) If the only argument is "clear" the clipboard will be cleared (including the clipboard history on Windows systems).
   * 3) If the only argument is "browse" qc will open the secrets directory in the OS's file manager.
   * 4) Otherwise the argument is interpreted as a file name or path to a secrets file and its content will be loaded into the clipboard.
   *
   * @param args the arguments qc is called with. This should be "help", "clear", "browse" or the path or file name of a secret.
   */
  public static void main(final String[] args) {
    SimpleLogger.createInstance(new File(HOME + SEP + DIRECTORY_NAME + SEP + LOGS_DIR + SEP + LOG_FILE)); // Initializes the logger
    Setup.guaranteeDirectories();
    Setup.guaranteeDefaultSecret();
    if (args != null) {
      if (args.length == 0) { // no args
        ClipboardManager.copyToClipboard(ContentLoader.load(DEFAULT_SECRET));
        System.exit(0);
      }
      else { // args found
        if (args.length == 1) { // correct args amount (1)
          if (StringArrayFunctions.contains(HELP_STRINGS, args[0])) { // help requested
            Help.printHelp();
            System.exit(0);
          } else if (StringArrayFunctions.contains(CLEAR_STRINGS, args[0])) { // clipboard clear requested
            ClipboardManager.clearClipboard();
            System.exit(0);
          } else if (StringArrayFunctions.contains(BROWSE_STRINGS, args[0])) { // directory browsing requested
            FileBrowser.openDirectory(HOME + SEP + DIRECTORY_NAME + SEP + SECRETS_DIR);
            System.exit(0);
          }
          else { // load requested (argument is interpreted as the name or path of the secret to load)
            ClipboardManager.copyToClipboard(ContentLoader.load(args[0]));
            System.exit(0);
          }
        } else { // too many args
          System.err.println("Too many arguments. If your file name or file path contains spaces surround it with quotes (\"\").");
          System.exit(-1);
        }
      }
    }
  }
}
