package com.nwawsoft.quickclipboard;

import java.nio.file.FileSystems;

/**
 * Provides constants that define the software's name, behaviour and storage locations.
 */
public class QCConstants {
  public static final String SOFTWARE_SHORT_NAME = "qc";
  public static final String SOFTWARE_LONG_NAME = "quick-clipboard";
  public static final String DEFAULT_SECRET = "default_secret";
  public static final String[] HELP_STRINGS = {"help", "-help", "--help", "-h", "--h", "/h", "/H"};
  public static final String[] CLEAR_STRINGS = {"clear", "clean", "--clear", "--clean"};
  public static final String[] BROWSE_STRINGS = {"browse", "open", "--browse", "--open"};
  public static final char HIDDEN_DIRECTORY_PREFIX = '.';
  public static final String HOME = System.getProperty("user.home");
  public static final String SEP = FileSystems.getDefault().getSeparator();
  public static final String SECRETS_DIR = "secrets";
  public static final String LOGS_DIR = "logs";
  public static final String LOG_FILE = "qc_log.txt";
  public static final String DIRECTORY_NAME = HIDDEN_DIRECTORY_PREFIX + SOFTWARE_SHORT_NAME;
}
