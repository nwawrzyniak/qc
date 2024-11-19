package com.nwawsoft.util;

/**
 * This class provides wrapped sleep functions.
 */
public class Sleep {
  /**
   * Puts the Thread to sleep for 1ms.
   */
  public static void sleep1ms() {
    SimpleLogger sl = SimpleLogger.getInstance();
    try {
      Thread.sleep(1);
    } catch (final InterruptedException e) {
      if (sl != null) sl.log("Sleep was interrupted. Aborting.", StandardStream.ERR);
      System.exit(-1);
    }
  }
}
