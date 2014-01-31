package de.bht.fpa.mail.s786848.maillist;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class Adapter extends ViewerFilter {

  private final IFilter filter;

  public Adapter(IFilter filter) {
    this.filter = filter;
  }

  @Override
  public boolean select(Viewer viewer, Object parentElement, Object element) {

    if (element instanceof Message) {
      Set<Message> messages = new HashSet<Message>();
      messages.add((Message) element);
      if (!filter.filter(messages).isEmpty()) {
        return true;
      }
    }
    return false;
  }

}
