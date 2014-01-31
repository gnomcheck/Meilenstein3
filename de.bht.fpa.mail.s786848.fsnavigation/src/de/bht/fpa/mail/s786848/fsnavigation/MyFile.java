package de.bht.fpa.mail.s786848.fsnavigation;

import java.io.File;

/**
 * This class represents a file
 */

public class MyFile extends FileSystem {

  public MyFile(File file) {
    super(file);
  }

  @Override
  public boolean hasChildren() {
    return false;
  }

  @Override
  public FileSystem[] getChildren() {
    return null;
  }
}
