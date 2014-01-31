package de.bht.fpa.mail.s786848.filter;

import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This class represents the union filter
 */

public class Union extends SpecialFilter {

  public Union(IFilter... filters) {
    super(filters);
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    if (messagesToFilter == null) {
      return null;
    }

    Set<Message> result = new HashSet<Message>();
    for (IFilter filter : getFilters()) {
      for (Message message : filter.filter(messagesToFilter)) {
        result.add(message);
      }
    }

    return result;
  }
}
