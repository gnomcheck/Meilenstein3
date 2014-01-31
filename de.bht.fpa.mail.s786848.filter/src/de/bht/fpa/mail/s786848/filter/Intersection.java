package de.bht.fpa.mail.s786848.filter;

import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This class represents the intersection filter
 */

public class Intersection extends SpecialFilter {

  public Intersection(IFilter... filters) {
    super(filters);
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    if (messagesToFilter == null) {
      return null;
    }

    Set<Message> result = new HashSet<Message>();
    for (Message message : messagesToFilter) {
      result.add(message);
    }

    for (IFilter filter : getFilters()) {
      result = filter.filter(result);
    }

    return result;
  }

}
