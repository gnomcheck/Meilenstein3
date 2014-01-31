package de.bht.fpa.mail.s786848.filter;

import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;

/**
 * This class represents the recipients filter
 */

public class Recipients implements IFilter {

  private final String recipient;
  private final FilterOperator filterOperator;

  public Recipients(String recipient, FilterOperator filterOperator) {
    this.recipient = recipient;
    this.filterOperator = filterOperator;
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    Set<Message> result = new HashSet<Message>();
    for (Message message : messagesToFilter) {
      for (Recipient recipients : message.getRecipients()) {
        if (StringCompareHelper.matches(recipients.getEmail(), recipient, filterOperator)
            || StringCompareHelper.matches(recipients.getPersonal(), recipient, filterOperator)) {
          result.add(message);
        }
      }
    }
    return result;
  }

}
