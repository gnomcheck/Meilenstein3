package de.bht.fpa.mail.s786848.filter;

import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This class represents the sender filter
 */

public class Sender implements IFilter {

  private final String sender;
  private final FilterOperator filterOperator;

  public Sender(String sender, FilterOperator filterOperator) {
    this.sender = sender;
    this.filterOperator = filterOperator;
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    Set<Message> result = new HashSet<Message>();
    for (Message message : messagesToFilter) {
      if (StringCompareHelper.matches(message.getSender().getEmail(), sender, filterOperator)
          || StringCompareHelper.matches(message.getSender().getPersonal(), sender, filterOperator)) {
        result.add(message);
      }
    }
    return result;
  }

}
