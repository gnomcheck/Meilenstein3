package de.bht.fpa.mail.s786848.filter.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import de.bht.fpa.mail.s000000.common.filter.NullFilter;

/**
 * This class represents the ClearFilterHandler, it is used to reset the filters
 */

public class ClearFilterHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    return new NullFilter();
  }
}
