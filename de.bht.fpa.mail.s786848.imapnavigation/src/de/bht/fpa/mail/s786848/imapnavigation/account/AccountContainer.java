package de.bht.fpa.mail.s786848.imapnavigation.account;

import java.util.ArrayList;
import java.util.List;

public class AccountContainer implements ImapElement {

  private final List<ImapAccount> acc;

  public AccountContainer(List<ImapAccount> acc) {
    this.acc = acc;
  }

  @Override
  public List<ImapElement> getChildren() {
    List<ImapElement> accs = new ArrayList<>();
    for (ImapAccount a : acc) {
      accs.add(a);
    }
    return accs;
  }

  public void addAccount(ImapAccount account) {
    acc.add(account);
  }

  public void removeAccount(String accName) {
    acc.remove(0);
  }

  @Override
  public boolean hasChildren() {
    return !acc.isEmpty();
  }

  @Override
  public String getName() {
    return null;
  }

}
