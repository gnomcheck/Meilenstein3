package de.bht.fpa.mail.s786848.filter;

import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This class represents the read filter
 */

public class Read implements IFilter {

  private final boolean read;

  public Read(final boolean read) {
    this.read = read;
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    Set<Message> result = new HashSet<Message>();
    for (Message message : messagesToFilter) {
      if (message.isRead() == read) {
        result.add(message);
      }
    }

    return result;
  }
}
