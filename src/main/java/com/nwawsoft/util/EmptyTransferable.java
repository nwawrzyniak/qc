package com.nwawsoft.util;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

/**
 * This class provides an empty Transferable.
 */
public class EmptyTransferable implements Transferable {
  @Override
  public DataFlavor[] getTransferDataFlavors() {
    return new DataFlavor[0];
  }

  @Override
  public boolean isDataFlavorSupported(final DataFlavor flavor) {
    return false;
  }

  @Override
  public Object getTransferData(final DataFlavor flavor) {
    //noinspection DataFlowIssue
    return null;
  }
}
