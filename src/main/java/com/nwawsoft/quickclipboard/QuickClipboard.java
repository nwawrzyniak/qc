package com.nwawsoft.quickclipboard;

import java.io.*;
import java.nio.file.Paths;

public class QuickClipboard {
  private static final int VERSION_MAJOR = 1;
  private static final int VERSION_FEATURE = 1;
  private static final int VERSION_FIX = 0;
  private static final char VERSION_SPACER = '.';
  private static final String VERSION = "" + VERSION_MAJOR + VERSION_SPACER + VERSION_FEATURE + VERSION_SPACER +
      VERSION_FIX;
  private static final String DEFAULT_SECRET = "default_secret";
  private static final String[] HELP_STRINGS = {"-help", "--help", "-h", "--h", "/h", "/H"};
  private static final char HIDDEN_DIRECTORY_PREFIX = '.';
  private static final String SOFTWARE_SHORT_NAME = "qc";
  private static final String DIRECTORY_NAME = HIDDEN_DIRECTORY_PREFIX + SOFTWARE_SHORT_NAME;
  
  public static String load(final Mode mode, final String fileName) {
    StringBuilder output = new StringBuilder();
    try {
      File f;
      if (mode.equals(Mode.RELATIVE)) {
        f = new File(System.getProperty("user.home") + System.getProperty("file.separator") + DIRECTORY_NAME +
            System.getProperty("file.separator") + fileName);
      } else if (mode.equals(Mode.ABSOLUTE)) {
        f = new File(fileName);
      } else {
        throw new IllegalArgumentException(mode + " is neither absolute nor relative.");
      }
      FileReader fr;
      if (f.exists()) {
        fr = new FileReader(f);
      } else {
        throw new FileNotFoundException("File " + f.getAbsolutePath() + " does not exist.");
      }
      BufferedReader br = new BufferedReader(fr);
      String currentLine;
      while ((currentLine = br.readLine()) != null) {
        output.append(currentLine);
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
    return output.toString();
  }
  
  public static void saveDefaultSecret() {
    try {
      String filePath = System.getProperty("user.home") + System.getProperty("file.separator") + DIRECTORY_NAME +
          System.getProperty("file.separator") + DEFAULT_SECRET;
      File f = new File(filePath);
      FileWriter fw = new FileWriter(f);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("This is the default secret, located in " + System.getProperty("user.home") +
          System.getProperty("file.separator") + DIRECTORY_NAME + System.getProperty("file.separator") +
          DEFAULT_SECRET + ". You may change the content of this file or create a new one.");
      bw.flush();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }
  
  public static void guaranteeDirectory() throws IOException {
    File d = new File(System.getProperty("user.home") + System.getProperty("file.separator") + DIRECTORY_NAME +
        System.getProperty("file.separator"));
    if (!d.exists()) {
      if (!d.mkdir()) {
        throw new IOException();
      }
    }
  }
  
  public static void guaranteeDefaultSecret() {
    File f = new File(System.getProperty("user.home") + System.getProperty("file.separator") + DIRECTORY_NAME +
        System.getProperty("file.separator") + DEFAULT_SECRET);
    if (!f.exists()) {
      saveDefaultSecret();
    }
  }
  
  public static void printHelp() {
    System.out.println("Help for qc (quick-clipboard) v" + VERSION + ":");
    System.out.println("  Usage: java -jar qc.jar [fileName]");
    String path = System.getProperty("user.home") + System.getProperty("file.separator") + DIRECTORY_NAME;
    path = path.replaceAll("\\\\", "/");
    System.out.println("  fileName may be either relative to " + path + " or absolute.");
    System.out.println("  File names or paths containing spaces have to be surrounded by quotes (\"\").");
  }
  
  public static void main(String[] args) {
    try {
      guaranteeDirectory();
      guaranteeDefaultSecret();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
    if (args != null) {
      if (args.length == 0) { // no args
        ClipboardManager.copyToClipboard(load(Mode.RELATIVE, DEFAULT_SECRET));
      } else { // args found
        if (args.length == 1) { // correct args amount (1)
          if (StringArrayFunctions.contains(HELP_STRINGS, args[0])) { // help mode
            printHelp();
          } else { // copy mode
            if (Paths.get(args[0]).isAbsolute()) { // copy mode, file mode absolute
              ClipboardManager.copyToClipboard(load(Mode.ABSOLUTE, args[0]));
            } else { // copy mode, file mode relative
              ClipboardManager.copyToClipboard(load(Mode.RELATIVE, args[0]));
            }
          }
        } else { // too many args
          System.err.println("You specified too many arguments. If your file name contains spaces, please surround " +
              "it with quotes (\"\").");
          System.exit(-1);
        }
      }
    }
  }

  private enum Mode {
    ABSOLUTE, RELATIVE
  }
}
