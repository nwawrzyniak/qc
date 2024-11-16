package com.nwawsoft.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

/**
 * This class provides a basic logger.
 * NOTE: Logging should not start before a directory has been created and the necessary write permissions have been checked.
 */
public class SimpleLogger {
  private static SimpleLogger logger = null;
  private final File logFile;

  /**
   * Creates a Logger that writes to the specified file.
   *
   * @param logFile the file to write to.
   */
  private SimpleLogger(final File logFile) {
    this.logFile = logFile;
  }

  /**
   * Creates and/or returns the SimpleLogger instance.
   * Calling this function again fails, resulting in the call being ignored.
   *
   * @param logFile The file to write to.
   */
  public static void createInstance(final File logFile) {
    if (logger == null) {
      logger = new SimpleLogger(logFile);
    } else {
      System.err.println("SimpleLogger instance has already been created. Ignoring call.");
      System.out.println("Existing log file: " + logger.logFile.getAbsolutePath());
    }
  }

  /**
   * Returns the SimpleLogger instance.
   * If createInstance(File) has not been called previously the operation fails and the software exits with exit code -1.
   *
   * @return The SimpleLogger instance.
   */
  public static SimpleLogger getInstance() {
    if (logger != null) {
      return logger;
    } else {
      System.err.println("SimpleLogger.getInstance() may only be used without arguments if a logFile has been passed previously. Aborting.");
      System.exit(-1);
      return null;
    }
  }

  /**
   * Appends text to the log file.
   * If stream is StandardStream.ERR the error will also be displayed to the user.
   *
   * @param text The text to write to the log file.
   * @param stream The standard stream to write to (either "StandardStream.OUT" or "StandardStream.ERR").
   */
  public void log(final String text, final StandardStream stream) {
    if (stream == StandardStream.OUT) {
      log(text, stream, false);
    } else if (stream == StandardStream.ERR) {
      log(text, stream, true);
    } else {
      System.err.println("Invalid standard stream for logging: " + stream.name());
    }
  }

  /**
   * Appends text to the log file.
   * If show is true the message will also be displayed to the user on the specified standard stream.
   * If an illegal StandardStream is passed the software exits with exit code -1.
   *
   * @param text The text to write to the log file.
   * @param stream The standard stream to write to (either "StandardStream.OUT" or "StandardStream.ERR").
   * @param show Whether output should be shown for the user.
   */
  public void log(final String text, final StandardStream stream, final boolean show) {
    if (show) {
      if (stream == StandardStream.OUT) System.out.println(text);
      else if (stream == StandardStream.ERR) System.err.println(text);
      else {
        System.err.println("Unknown StandardStream: " + stream.name() + ". Aborting.");
        System.exit(-1);
      }
    }
    try (FileWriter fw = new FileWriter(logFile.getAbsolutePath(), true)) {
      Instant timestamp = Instant.now();
      String infoType;
      if (stream == StandardStream.OUT) infoType = "[INFO]";
      else infoType = "[ERROR]";
      fw.write(timestamp + ": " + infoType + ": " + text + "\n");
    } catch (final IOException e) {
      System.err.println("Could not write to the log file.");
    }
  }
}
