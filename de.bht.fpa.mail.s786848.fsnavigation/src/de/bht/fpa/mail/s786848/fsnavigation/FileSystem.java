package de.bht.fpa.mail.s786848.fsnavigation;

import java.io.File;

/**
 * This class represents a file and can give back the name of it
 */

public abstract class FileSystem {

  protected final File file;
  private Directory directory;

  public FileSystem(File file) {
    this.file = file;
  }

  public Directory getParent() {
    return directory;
  }

  public void setParent(Directory parent) {
    this.directory = parent;
  }

  public abstract FileSystem[] getChildren();

  public abstract boolean hasChildren();

  @Override
  public String toString() {
    return this.file.getName();
  }

  public String getAbsolutePath() {
    return file.getAbsolutePath();
  }

}