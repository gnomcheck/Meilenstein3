package de.bht.fpa.mail.s786848.filter;

import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

/**
 * This class represents the special filter, it is used to build multiple filter
 */

public class SpecialFilter implements IFilter {

  private final IFilter[] filters;

  public IFilter[] getFilters() {
    return filters;
  }

  public SpecialFilter(IFilter[] filters) {
    this.filters = filters;
  }

  @Override
  public Set<Message> filter(Iterable<Message> messagesToFilter) {
    // TODO Auto-generated method stub
    return null;
  }

}
