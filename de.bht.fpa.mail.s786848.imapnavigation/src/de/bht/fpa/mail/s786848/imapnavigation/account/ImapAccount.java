package de.bht.fpa.mail.s786848.imapnavigation.account;

import java.util.ArrayList;
import java.util.List;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;

public class ImapAccount implements ImapElement {

  private final Account acc;

  public ImapAccount(Account acc) {
    this.acc = acc;
  }

  @Override
  public List<ImapElement> getChildren() {
    ArrayList<ImapElement> imapList = new ArrayList<>();
    List<Folder> folders = acc.getFolders();
    for (Folder folder : folders) {
      imapList.add(new ImapFolder(folder));
    }
    return imapList;
  }

  @Override
  public boolean hasChildren() {
    return !acc.getFolders().isEmpty();
  }

  @Override
  public String getName() {
    return acc.getName();
  }

}
