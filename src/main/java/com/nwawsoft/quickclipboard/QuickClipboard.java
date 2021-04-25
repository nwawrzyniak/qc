package com.nwawsoft.quickclipboard;

import java.io.*;

public class QuickClipboard {
  public static final String DEFAULT_SECRET = "default_secret";
  public static final String[] helpStrings = {"-help", "--help", "--h", "--help", "/h", "/H"};
  
  public static String load() {
    return load(DEFAULT_SECRET);
  }
  
  public static String load(final String fileName) {
    String output = "";
    try {
      File f = new File(System.getProperty("user.home") + "/.qc/" + fileName);
      FileReader fr = new FileReader(f);
      BufferedReader br = new BufferedReader(fr);
      String currentLine;
      while ((currentLine = br.readLine()) != null) {
        output += currentLine;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return output;
  }
  
  public static void saveDefaultSecret() {
    try {
      String filePath = System.getProperty("user.home") + "/.qc/" + DEFAULT_SECRET;
      File f = new File(filePath);
      FileWriter fw = new FileWriter(f);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("This is the default secret, located in " + System.getProperty("user.home") + "/.qc/" +
          DEFAULT_SECRET + ". You may change the content of this file or create a new one.");
      bw.flush();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void guaranteeDirectory() throws IOException {
    File d = new File(System.getProperty("user.home") + "/.qc/");
    if (!d.exists()) {
      if (!d.mkdir()) {
        throw new IOException();
      }
    }
  }
  
  public static void guaranteeDefaultSecret() {
    File f = new File(System.getProperty("user.home") + "/.qc/" + DEFAULT_SECRET);
    if (!f.exists()) {
      saveDefaultSecret();
    }
  }
  
  public static void printHelp() {
    System.out.println("Help for qc (quick-clipboard):");
    System.out.println("  Usage: java -jar qc.jar [fileName]");
    String path = System.getProperty("user.home") + "/.qc";
    path = path.replaceAll("\\\\", "/");
    System.out.println("  Where [fileName] is any file within the directory " + path);
  }
  
  public static void main(String[] args) {
    try {
      guaranteeDirectory();
      guaranteeDefaultSecret();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (args.length >= 1) {
      if (StringArrayFunctions.contains(helpStrings, args[0])) {
        printHelp();
      } else {
        ClipboardManager.copyToClipboard(load(args[0]));
      }
    } else {
      ClipboardManager.copyToClipboard(load());
    }
  }
}
