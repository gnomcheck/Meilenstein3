package de.bht.fpa.mail.s786848.fsnavigation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.JAXB;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s786848.common.IMailDirectory;

/**
 * The DirectoryClass is for catching the files from directory and figure out if
 * it
 */

public class Directory extends FileSystem implements IMailDirectory {

  public Directory(File file) {
    super(file);
  }

  @Override
  public FileSystem[] getChildren() {

    if (file.listFiles() == null) {
      return null;
    }
    ArrayList<Directory> list = new ArrayList<>();

    for (File f : file.listFiles()) {
      if (f.isDirectory()) {
        list.add(new Directory(f));
      }
    }

    return list.toArray(new Directory[0]);
  }

  @Override
  public boolean hasChildren() {
    if (file.listFiles().length == 0 || file.listFiles() == null) {
      return false;
    }
    return true;
  }

  @Override
  public Collection<Message> getMessages() {
    final File[] files = file.listFiles();
    if (files == null || files.length == 0) {
      return new ArrayList<Message>();
    }

    ArrayList<Message> messages = new ArrayList<Message>();

    for (File f : files) {
      if (f.toString().endsWith(".xml")) {
        Message message = JAXB.unmarshal(f, Message.class);
        if (message.getId() != null) {
          messages.add(message);
        }
      }
    }
    System.out.println("Messages created");
    return messages;
  }

}
