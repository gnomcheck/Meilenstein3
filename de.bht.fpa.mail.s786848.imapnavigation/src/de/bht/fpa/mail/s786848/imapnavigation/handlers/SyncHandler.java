package de.bht.fpa.mail.s786848.imapnavigation.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SyncHandler extends AbstractHandler {

  /**
   * The constructor.
   */
  public SyncHandler() {
  }

  /**
   * the command has been executed, so extract extract the needed information
   * from the application context.
   */
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {

    final SyncJob job = new SyncJob("BHT-Mail");
    job.setUser(true);
    job.schedule();
    return null;
  }
}
