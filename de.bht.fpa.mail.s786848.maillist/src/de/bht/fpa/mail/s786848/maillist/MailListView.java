package de.bht.fpa.mail.s786848.maillist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.part.ViewPart;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;
import de.bht.fpa.mail.s000000.common.mail.model.Sender;
import de.bht.fpa.mail.s000000.common.rcp.selection.SelectionHelper;
import de.bht.fpa.mail.s000000.common.table.MessageValues;
import de.bht.fpa.mail.s786848.common.IMailDirectory;
import de.ralfebert.rcputils.tables.ColumnBuilder;
import de.ralfebert.rcputils.tables.ICellFormatter;
import de.ralfebert.rcputils.tables.TableViewerBuilder;
import de.ralfebert.rcputils.tables.format.Formatter;
import de.ralfebert.rcputils.tables.format.StringValueFormatter;

/**
 * This class represents the MailListView
 */

public class MailListView extends ViewPart implements IExecutionListener {

  private TableViewerBuilder t;
  private ViewerFilter adapter;
  private final ArrayList<ViewerFilter> filters = new ArrayList<>();
  private final int readWidth = 38;
  private final int dateWidth = 68;
  private final int subjectWidth = 173;
  private final int importanceWidth = 73;
  private final ISelectionListener listener = new ISelectionListener() {

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
      IMailDirectory dir = SelectionHelper.handleStructuredSelection(selection, IMailDirectory.class);
      if (dir instanceof IMailDirectory) {
        Collection<Message> coll = dir.getMessages();
        t.getTableViewer().setInput(coll);
        System.out.println("Table filled");
      }
    }
  };

  @Override
  public void createPartControl(Composite parent) {
    Composite container = new Composite(parent, SWT.NONE);
    container.setLayout(new GridLayout(1, false));

    // Creates the table
    Composite table = new Composite(container, SWT.BORDER | SWT.FULL_SELECTION);
    table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
    t = new TableViewerBuilder(table, SWT.BORDER | SWT.FULL_SELECTION);

    // Builds the importance column
    ColumnBuilder importance = t.createColumn("Importance");
    importance.bindToValue(MessageValues.IMPORTANCE);
    importance.setPixelWidth(importanceWidth);
    importance.build();

    // Builds the received column
    ColumnBuilder date = t.createColumn("Received");
    StringValueFormatter dateFormat = Formatter.forDate(SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM));
    date.format(dateFormat);
    date.useAsDefaultSortColumn();
    date.setPixelWidth(dateWidth);
    date.bindToValue(MessageValues.RECEIVED).build();

    // Builds the read column
    ColumnBuilder read = t.createColumn("Read");
    read.bindToValue(MessageValues.READ);
    read.setPixelWidth(readWidth);
    read.build();

    // Builds the send column
    ColumnBuilder sender = t.createColumn("Sender");
    sender.bindToValue(MessageValues.SENDER);
    sender.format(new ICellFormatter() {
      @Override
      public void formatCell(ViewerCell cell, Object value) {
        Sender sender = (Sender) value;
        cell.setText(sender.getEmail());
      }
    });
    sender.build();

    // Builds the recipient column
    ColumnBuilder recipient = t.createColumn("Recipient");
    recipient.bindToValue(MessageValues.RECIPIENT);
    recipient.format(new ICellFormatter() {
      @Override
      public void formatCell(ViewerCell cell, Object value) {
        @SuppressWarnings("unchecked")
        List<Recipient> list = (List<Recipient>) value;
        String rec = "";
        for (Recipient recipient : list) {
          rec += recipient.getEmail() + ", ";
        }
        cell.setText(rec);
      }
    });
    recipient.build();

    // Builds the subject column
    ColumnBuilder subject = t.createColumn("Subject");
    subject.setPixelWidth(subjectWidth);
    subject.bindToValue(MessageValues.SUBJECT).build();
    getSite().setSelectionProvider(t.getTableViewer());
    getSite().getPage().addSelectionListener(listener);
    t.setInput(null);

    IWorkbench workbench = PlatformUI.getWorkbench();
    ICommandService commandService = (ICommandService) workbench.getService(ICommandService.class);
    commandService.addExecutionListener(this);
  }

  @Override
  public void dispose() {
    getSite().getPage().removeSelectionListener(listener);
  }

  @Override
  public void setFocus() {
    t.getTableViewer().getTable().setFocus();
  }

  @Override
  public void notHandled(String commandId, NotHandledException exception) {
  }

  @Override
  public void postExecuteFailure(String commandId, ExecutionException exception) {
  }

  @Override
  public void postExecuteSuccess(String commandId, Object returnValue) {
    if (commandId.equals("de.bht.fpa.mail.s786848.filter.commands.filterCommand")) {
      if (returnValue instanceof IFilter) {
        IFilter adapterFilter = (IFilter) returnValue;
        adapter = new Adapter(adapterFilter);
        filters.add(adapter);
        t.getTableViewer().addFilter(adapter);
        t.getTableViewer().refresh();
      }
    }
    if (commandId.equals("de.bht.fpa.mail.s786848.filter.commands.clearfilterCommand")) {
      for (ViewerFilter v : filters) {
        t.getTableViewer().removeFilter(v);
      }
      t.getTableViewer().refresh();
    }
  }

  @Override
  public void preExecute(String commandId, ExecutionEvent event) {
  }
}
