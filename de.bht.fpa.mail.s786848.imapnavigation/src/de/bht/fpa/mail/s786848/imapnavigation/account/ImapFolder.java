package de.bht.fpa.mail.s786848.imapnavigation.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.bht.fpa.mail.s000000.common.mail.model.Folder;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s786848.common.IMailDirectory;

public class ImapFolder implements ImapElement, IMailDirectory {

  private final Folder folder;

  public ImapFolder(Folder folder) {
    this.folder = folder;
  }

  @Override
  public List<ImapElement> getChildren() {
    ArrayList<ImapElement> imapList = new ArrayList<>();
    List<Folder> folders = folder.getFolders();
    for (Folder f : folders) {
      imapList.add(new ImapFolder(f));
    }
    return imapList;
  }

  @Override
  public boolean hasChildren() {
    return !folder.getFolders().isEmpty();
  }

  @Override
  public String getName() {
    return folder.getFullName();
  }

  @Override
  public Collection<Message> getMessages() {
    return folder.getMessages();
  }

}
