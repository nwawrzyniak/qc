package com.nwawsoft.quickclipboard;

/**
 * This class holds information about the build.
 * Currently, this is only the version number.
 */
public class BuildInfo {
  private static final char VERSION_PREFIX = 'v';
  private static final char VERSION_SPACER = '.';
  private static final int VERSION_MAJOR   = 2;
  private static final int VERSION_MINOR = 0;
  private static final int VERSION_PATCH = 0;
  private static final String VERSION_SUFFIX = "-rc2"; // Should start with "-" if not empty

  /**
   * Builds the version number from its component fields and ensures semantic versioning.
   * The format is as follows:
   * [VERSION_PREFIX][VERSION_MAJOR][VERSION_SPACER][VERSION_MINOR][VERSION_SPACER][VERSION_PATCH][VERSION_SUFFIX]
   * As VERSION_PREFIX we chose "v" and VERSION_SPACER is "." (dot).
   * VERSION_SUFFIX may either be a String starting with "-" (e.g. "-rc1) or empty.
   * Examples: "v1.2.3" or "v2.0.0-rc1".
   *
   * @return The version number in a human-readable format.
   */
  public static String getVersion() {
    StringBuilder sb = new StringBuilder();
    sb.append(VERSION_PREFIX);
    sb.append(VERSION_MAJOR);
    sb.append(VERSION_SPACER);
    sb.append(VERSION_MINOR);
    sb.append(VERSION_SPACER);
    sb.append(VERSION_PATCH);
    sb.append(VERSION_SUFFIX);
    return sb.toString();
  }
}
