package de.bht.fpa.mail.s786848.imapnavigation.account;

import java.util.List;

public interface ImapElement {

  List<ImapElement> getChildren();

  boolean hasChildren();

  String getName();

}
