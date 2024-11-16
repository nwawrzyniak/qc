package com.nwawsoft.quickclipboard;

import com.nwawsoft.util.SimpleLogger;
import com.nwawsoft.util.StandardStream;

import java.io.*;

import static com.nwawsoft.quickclipboard.QCConstants.*;

/**
 * This class provides functions that ensure a functioning system state when running the software for the first time.
 * Specifically, this means creating directories for the secrets and log files and creating a default secret.
 */
public class Setup {
  /**
   * Creates a default secret that is loaded when calling qc without additional arguments.
   * If the operation fails the software exits with exit code -1.
   */
  public static void saveDefaultSecret() {
    SimpleLogger sl = SimpleLogger.getInstance();
    try {
      String filePath = HOME + SEP + DIRECTORY_NAME + SEP + SECRETS_DIR + SEP + DEFAULT_SECRET;
      File f = new File(filePath);
      FileWriter fw = new FileWriter(f);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("This is the default secret, located in \"" + HOME + SEP + DIRECTORY_NAME + SEP + SECRETS_DIR + SEP + DEFAULT_SECRET + "\". It is loaded when qc is started without additional arguments. You may change the contents of this file if you like, but it is recommended to create a new secret instead of overwriting this one.");
      bw.flush();
      bw.close();
      if (sl != null) sl.log("Created default secret.", StandardStream.OUT);
    } catch (final IOException e) {
      if (sl != null) sl.log("Could not store default secret.", StandardStream.ERR);
      System.exit(-1);
    }
  }

  /**
   * Ensures that the ".qc" directory and the "secrets" and "logs" directories within it exist by creating them if they do not exist already.
   * If an operation fails the software exits with exit code -1.
   */
  public static void guaranteeDirectories() {
    File baseDir = new File(HOME + SEP + DIRECTORY_NAME + SEP);
    if (!baseDir.exists() && !baseDir.mkdir()) {
      // Can't log yet, because directory does not exist.
      System.err.println("Directory \"" + baseDir.getAbsolutePath() + "\" does not exist and could not be created. Aborting.");
      System.exit(-1);
    }
    File logsDir = new File(HOME + SEP + DIRECTORY_NAME + SEP + LOGS_DIR + SEP);
    if (!logsDir.exists() && !logsDir.mkdir()) {
      // Can't log yet, because directory does not exist.
      System.err.println("Directory \"" + logsDir.getAbsolutePath() + "\" does not exist and could not be created. Aborting.");
      System.exit(-1);
    }
    File secretsDir = new File(HOME + SEP + DIRECTORY_NAME + SEP + SECRETS_DIR + SEP);
    if (!secretsDir.exists() && !secretsDir.mkdir()) {
      SimpleLogger sl = SimpleLogger.getInstance();
      if (sl != null) sl.log("Directory \"" + secretsDir.getAbsolutePath() + "\" does not exist and could not be created. Aborting.", StandardStream.ERR);
      System.exit(-1);
    }
  }

  /**
   * Ensures that a default secret exists by creating one if there is none already.
   */
  public static void guaranteeDefaultSecret() {
    File f = new File(HOME + SEP + DIRECTORY_NAME + SEP + SECRETS_DIR + SEP + DEFAULT_SECRET);
    if (!f.exists()) saveDefaultSecret();
  }
}
