package de.bht.fpa.mail.s786848.imapnavigation;

import java.util.ArrayList;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.imapsync.ImapHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.builder.AccountBuilder;
import de.bht.fpa.mail.s786848.imapnavigation.account.AccountContainer;
import de.bht.fpa.mail.s786848.imapnavigation.account.ImapAccount;
import de.bht.fpa.mail.s786848.imapnavigation.handlers.SyncJob;

public class ImapView extends ViewPart implements IJobChangeListener {

  public static final String ID = "de.bht.fpa.s786848.imapnavigation.ImapView";
  private TreeViewer viewer;
  private AccountContainer accountContainer;

  @Override
  public void createPartControl(Composite parent) {
    viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
    viewer.setContentProvider(new ViewContentProvider());
    viewer.setLabelProvider(new ViewLabelProvider());
    createModel();
    viewer.setInput(accountContainer);
    getSite().setSelectionProvider(viewer);
  }

  // creates the imap model
  private void createModel() {
    AccountBuilder acc = AccountBuilder.newAccountBuilder();
    // @formatter:off
    Account account = acc.name("FPA-Mail")
    .host("imap.gmail.com")
    .username("s786848.fpa@gmail.com")
    .password("schniedel")
    // @formatter:on
        .build();
    ArrayList<ImapAccount> accounts = new ArrayList<>();
    accounts.add(new ImapAccount(account));
    ImapHelper.saveAccount(account);
    accountContainer = new AccountContainer(accounts);

    Job.getJobManager().addJobChangeListener(this);
  }

  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }

  @Override
  public void aboutToRun(IJobChangeEvent event) {

  }

  @Override
  public void awake(IJobChangeEvent event) {

  }

  @Override
  public void done(IJobChangeEvent event) {
    if (event.getJob() instanceof SyncJob) {
      Account acc = ImapHelper.getAccount(event.getJob().getName());
      accountContainer.removeAccount(acc.getName());
      accountContainer.addAccount(new ImapAccount(acc));
      Display.getDefault().asyncExec(new Runnable() {
        @Override
        public void run() {
          viewer.setInput(accountContainer);
          viewer.refresh();
        }
      });

    }
  }

  @Override
  public void running(IJobChangeEvent event) {
  }

  @Override
  public void scheduled(IJobChangeEvent event) {

  }

  @Override
  public void sleeping(IJobChangeEvent event) {

  }

}
