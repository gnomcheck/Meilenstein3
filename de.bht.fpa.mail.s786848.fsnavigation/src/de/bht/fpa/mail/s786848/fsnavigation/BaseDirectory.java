package de.bht.fpa.mail.s786848.fsnavigation;

import java.util.Observable;

/**
 * The BaseDirectoryClass sets the new base directory changed by the user, using
 * the Observer
 */

public class BaseDirectory extends Observable {

  private static final BaseDirectory INSTANCE = new BaseDirectory();

  public BaseDirectory() {
    super();
  }

  public void setPath(String path) {
    System.out.println(path);
    setChanged();
    this.notifyObservers(path);
  }

  public static BaseDirectory getInstance() {
    return BaseDirectory.INSTANCE;
  }

}