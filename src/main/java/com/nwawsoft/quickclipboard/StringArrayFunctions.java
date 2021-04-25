package com.nwawsoft.quickclipboard;

public class StringArrayFunctions {
  public static boolean contains(final String[] strings, final String pattern) {
    if (strings != null && pattern != null) {
      for (String string : strings) {
        if (string.equals(pattern)) {
          return true;
        }
      }
    }
    return false;
  }
}
