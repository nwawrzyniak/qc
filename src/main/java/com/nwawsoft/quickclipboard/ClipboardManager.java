package com.nwawsoft.quickclipboard;

import java.awt.*;
import java.awt.datatransfer.*;

/**
 * Provides functions to copy and paste to and from the clipboard.
 * Can paste to all awt, swing and javafx components that can handle text.
 */
public class ClipboardManager {
  /**
   * Writes a String to the system's/user's clipboard.
   *
   * @param clipboardText the text to copy to the clipboard
   */
  public static void copyToClipboard(final String clipboardText) {
    try {
      Thread.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    getSystemClipboard().setContents(new StringSelection(clipboardText), null);
  }

  /**
   * Returns the Clipboard object that Java keeps synchronized with the OS's clipboard.
   * This is just a wrapper to not call Toolkit explicitly.
   *
   * @return the Clipboard object
   */
  public static Clipboard getSystemClipboard() {
    return Toolkit.getDefaultToolkit().getSystemClipboard();
  }
}
