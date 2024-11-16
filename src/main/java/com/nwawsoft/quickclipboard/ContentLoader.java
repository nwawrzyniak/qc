package com.nwawsoft.quickclipboard;

import com.nwawsoft.util.SimpleLogger;
import com.nwawsoft.util.StandardStream;

import java.io.*;
import java.nio.file.Paths;

import static com.nwawsoft.quickclipboard.QCConstants.*;

/**
 * This class provides the functionality to load file content into RAM.
 */
public class ContentLoader {
  /**
   * Creates a new String containing the contents of the specified file.
   * If the operation fails the software exits with exit code -1.
   *
   * @param fileName Either the name of a file within ~/.qc/secrets or an absolute path.
   * @return The content of the specified file.
   */
  public static String load(final String fileName) {
    SimpleLogger sl = SimpleLogger.getInstance();
    StringBuilder outputBuilder = new StringBuilder();
    String printableFileName;
    printableFileName = fileName.replaceAll("\\\\\\\\", "/");
    printableFileName = printableFileName.replaceAll("\\\\", "/");
    if (printableFileName.contains("/")) {
      printableFileName = printableFileName.substring(printableFileName.lastIndexOf("/") + 1);
    }
    BufferedReader br = getSecretBufferedReader(fileName);
    if (br != null) {
      if (sl != null) sl.log("Secret \"" + printableFileName + "\" is being loaded...", StandardStream.OUT, true);
      String currentLine;
      try {
        while ((currentLine = br.readLine()) != null) {
          outputBuilder.append(currentLine);
        }
      } catch (final IOException e) {
        if (sl != null) sl.log("Could not read file content. Aborting.", StandardStream.ERR);
        System.exit(-1);
      }
    } else {
      if (sl != null) sl.log("BufferedReader is null. Aborting.", StandardStream.ERR);
      System.exit(-1);
    }
    if (sl != null) sl.log("Secret \"" + printableFileName + "\" was loaded successfully.", StandardStream.OUT);
    return outputBuilder.toString();
  }

  /**
   * Creates a BufferedReader for the specified secret file.
   * If the operation fails the software exits with exit code -1.
   *
   * @param fileName Either the name of a file within ~/.qc/secrets or an absolute path.
   * @return A BufferedReader with an open stream to the specified file.
   */
  private static BufferedReader getSecretBufferedReader(final String fileName) {
    SimpleLogger sl = SimpleLogger.getInstance();
    File f;
    if (Paths.get(fileName).isAbsolute()) {
      f = new File(fileName);
    } else {
      f = new File(HOME + SEP + DIRECTORY_NAME + SEP + SECRETS_DIR + SEP + fileName);
    }
    FileReader fr;
    if (f.exists()) {
      try {
        fr = new FileReader(f);
        return new BufferedReader(fr);
      } catch (final FileNotFoundException e) {
        if (sl != null) sl.log("Could not create FileReader.", StandardStream.ERR);
        System.exit(-1);
        return null;
      }
    } else {
      if (sl != null) sl.log("File " + f.getAbsolutePath() + " does not exist.", StandardStream.ERR);
      System.exit(-1);
      return null;
    }
  }
}
