package de.bht.fpa.mail.s786848.imapnavigation.handlers;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import de.bht.fpa.mail.s000000.common.mail.imapsync.ImapHelper;
import de.bht.fpa.mail.s000000.common.mail.imapsync.SynchronizationException;
import de.bht.fpa.mail.s000000.common.mail.model.Account;

public class SyncJob extends Job {

  public SyncJob(String accName) {
    super(accName);
  }

  @Override
  protected IStatus run(IProgressMonitor monitor) {
    IStatus status = Status.OK_STATUS;
    Account accName = ImapHelper.getAccount(getName());

    try {
      ImapHelper.syncAllFoldersToAccount(accName, monitor);
    } catch (SynchronizationException e) {
      status = Status.CANCEL_STATUS;
    }

    return status;
  }
}
