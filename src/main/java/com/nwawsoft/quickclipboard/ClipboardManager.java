package com.nwawsoft.quickclipboard;

import com.nwawsoft.util.SimpleLogger;
import com.nwawsoft.util.StandardStream;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

/**
 * This class provides functions to copy Strings to the clipboard or delete the clipboard's content.
 */
public class ClipboardManager {
  /**
   * Writes a String to the clipboard.
   *
   * @param clipboardText The text to copy to the clipboard.
   */
  public static void copyToClipboard(final String clipboardText) {
    SimpleLogger sl = SimpleLogger.getInstance();
    try {
      Thread.sleep(1);
    } catch (final InterruptedException e) {
      if (sl != null) sl.log("Sleep was interrupted.", StandardStream.ERR, false);
    }
    getSystemClipboard().setContents(new StringSelection(clipboardText), null);
    if (sl != null) sl.log("Loaded text into clipboard.", StandardStream.OUT, true);
  }

  /**
   * Returns the Clipboard object that Java keeps synchronized with the OS's clipboard.
   * This is just a wrapper to not call Toolkit explicitly.
   *
   * @return The default Clipboard object.
   */
  private static Clipboard getSystemClipboard() {
    return Toolkit.getDefaultToolkit().getSystemClipboard();
  }

  /**
   * Clears the clipboard completely.
   * On Windows this also clears the "Clipboard History".
   */
  public static void clearClipboard() {
    SimpleLogger sl = SimpleLogger.getInstance();
    getSystemClipboard().setContents(new StringSelection(""), null);
    if (sl != null) sl.log("Clipboard cleared.", StandardStream.OUT, true);
    String os = System.getProperty("os.name").toLowerCase();
    if (os.contains("win")) {
      ProcessBuilder pb = new ProcessBuilder(
        "C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe",
        "-Command",
        "[Windows.ApplicationModel.DataTransfer.Clipboard, Windows, ContentType = WindowsRuntime]::ClearHistory()",
        "|",
        "Out-Null"
      );
      try {
        pb.start();
        if (sl != null) sl.log("Clipboard history cleared.", StandardStream.OUT, true);
      } catch (final IOException e) {
        if (sl != null) sl.log("Could not clear clipboard history.", StandardStream.ERR);
        System.exit(-1);
      }
    }
  }
}
