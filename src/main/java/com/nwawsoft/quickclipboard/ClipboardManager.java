package com.nwawsoft.quickclipboard;

import com.nwawsoft.util.EmptyTransferable;
import com.nwawsoft.util.SimpleLogger;
import com.nwawsoft.util.Sleep;
import com.nwawsoft.util.StandardStream;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
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
    Sleep.sleep1ms(); // Important on Windows because the clipboard may not be instantly available.
    SimpleLogger sl = SimpleLogger.getInstance();
    Transferable transferable = new StringSelection(clipboardText);
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    try {
      clipboard.setContents(transferable, null);
    } catch (final IllegalStateException e) {
      if (sl != null) sl.log("", StandardStream.ERR);
    }
    if (sl != null) sl.log("Loaded text into clipboard.", StandardStream.OUT, true);
    Sleep.sleep1ms(); // Important on Linux with X11 or Wayland. Without this sleep the operation will likely fail.
  }

  /**
   * Clears the clipboard completely.
   * On Windows this also clears the "Clipboard History".
   */
  public static void clearClipboard() {
    Sleep.sleep1ms(); // Important on Windows because the clipboard may not be instantly available.
    SimpleLogger sl = SimpleLogger.getInstance();
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(new EmptyTransferable(), null);
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
    Sleep.sleep1ms(); // Important on Linux with X11 or Wayland. Without this sleep the operation will likely fail.
  }
}
