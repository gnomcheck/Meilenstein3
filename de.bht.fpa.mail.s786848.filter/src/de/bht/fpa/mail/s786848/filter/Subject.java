package de.bht.fpa.mail.s786848.filter;

import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This class represents the subject filter
 */

public class Subject implements IFilter {

  private final String subject;
  private final FilterOperator filterOperator;

  public Subject(String subject, FilterOperator filterOperator) {
    super();
    this.subject = subject;
    this.filterOperator = filterOperator;
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    Set<Message> result = new HashSet<Message>();
    for (Message message : messagesToFilter) {
      if (StringCompareHelper.matches(message.getSubject(), subject, filterOperator)) {
        result.add(message);
      }
    }
    return result;
  }

}
