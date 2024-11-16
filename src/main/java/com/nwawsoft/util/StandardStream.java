package com.nwawsoft.util;

/**
 * An enum that contains possible streams.
 * This enum is used for SimpleLogger which only needs to be capable to *write* to a stream, not read from it.
 * This is why IN is not defined as a StandardStream.
 */
public enum StandardStream {
  ERR, // The error output stream
  OUT // the default output stream
}
