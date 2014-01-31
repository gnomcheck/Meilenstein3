package de.bht.fpa.mail.s786848.fsnavigation.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import de.bht.fpa.mail.s786848.fsnavigation.BaseDirectory;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SetBaseDirectoryHandler extends AbstractHandler {
  /**
   * The constructor.
   */
  public SetBaseDirectoryHandler() {
  }

  /**
   * the command has been executed, so extract the needed information from the
   * application context.
   */
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
    DirectoryDialog dialog = new DirectoryDialog(window.getShell());

    try {
      BaseDirectory.getInstance().setPath(dialog.open());
    } catch (Exception e) {
      System.err.println("Chooser aborted!");
    }

    return null;
  }
}
