package de.bht.fpa.mail.s786848.filter;

import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This class represents the importance filter
 */

public class Importances implements IFilter {

  private final Importance importance;

  public Importances(Importance importance) {
    this.importance = importance;
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    Set<Message> result = new HashSet<Message>();
    for (Message message : messagesToFilter) {
      if (message.getImportance() == importance) {
        result.add(message);
      }
    }
    return result;
  }

}
