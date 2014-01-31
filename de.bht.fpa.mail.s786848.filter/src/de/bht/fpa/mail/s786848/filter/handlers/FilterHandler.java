package de.bht.fpa.mail.s786848.filter.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import de.bht.fpa.mail.s000000.common.filter.FilterDialog;
import de.bht.fpa.mail.s000000.common.filter.FilterGroupType;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s786848.filter.Importances;
import de.bht.fpa.mail.s786848.filter.Intersection;
import de.bht.fpa.mail.s786848.filter.Read;
import de.bht.fpa.mail.s786848.filter.Recipients;
import de.bht.fpa.mail.s786848.filter.Sender;
import de.bht.fpa.mail.s786848.filter.Subject;
import de.bht.fpa.mail.s786848.filter.Text;
import de.bht.fpa.mail.s786848.filter.Union;

/**
 * This class represents the filter handler
 */

public class FilterHandler extends AbstractHandler {

  private IFilter[] filters;
  private final List<IFilter> filterlist = new ArrayList<IFilter>();

  public FilterHandler() {
  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
    FilterDialog filterDialog = new FilterDialog(window.getShell());
    filterlist.clear();

    if (filterDialog.open() == 0) {
      filters = new IFilter[filterDialog.getFilterCombinations().size()];
      for (int i = 0; i < filterDialog.getFilterCombinations().size(); i++) {

        // Sender
        if (filterDialog.getFilterCombinations().get(i).getFilterType().toString() == "SENDER") {
          filterlist.add(new Sender((String) filterDialog.getFilterCombinations().get(i).getFilterValue(), filterDialog
              .getFilterCombinations().get(i).getFilterOperator()));
        }

        // Importance
        if (filterDialog.getFilterCombinations().get(i).getFilterType().toString() == "IMPORTANCE") {
          filterlist.add(new Importances((Importance) filterDialog.getFilterCombinations().get(i).getFilterValue()));
        }

        // Read
        if (filterDialog.getFilterCombinations().get(i).getFilterType().toString() == "READ") {
          filterlist.add(new Read((Boolean) filterDialog.getFilterCombinations().get(i).getFilterValue()));
        }

        // Subject
        if (filterDialog.getFilterCombinations().get(i).getFilterType().toString() == "SUBJECT") {
          filterlist.add(new Subject(filterDialog.getFilterCombinations().get(i).getFilterValue().toString(),
              filterDialog.getFilterCombinations().get(i).getFilterOperator()));
        }

        // Text
        if (filterDialog.getFilterCombinations().get(i).getFilterType().toString() == "TEXT") {
          filterlist.add(new Text(filterDialog.getFilterCombinations().get(i).getFilterValue().toString(), filterDialog
              .getFilterCombinations().get(i).getFilterOperator()));
        }

        // Recipients
        if (filterDialog.getFilterCombinations().get(i).getFilterType().toString() == "RECIPIENTS") {
          filterlist.add(new Recipients(filterDialog.getFilterCombinations().get(i).getFilterValue().toString(),
              filterDialog.getFilterCombinations().get(i).getFilterOperator()));
        }
      }

      // Union
      if (filterDialog.getFilterGroupType() == FilterGroupType.UNION) {
        return new Union(filterlist.toArray(filters));
      }

      // Intersection
      if (filterDialog.getFilterGroupType() == FilterGroupType.INTERSECTION) {
        return new Intersection(filterlist.toArray(filters));
      }

    }

    return null;
  }
}
