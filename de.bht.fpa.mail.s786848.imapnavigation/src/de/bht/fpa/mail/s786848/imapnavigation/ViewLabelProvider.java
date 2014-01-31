package de.bht.fpa.mail.s786848.imapnavigation;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.bht.fpa.mail.s786848.imapnavigation.account.ImapAccount;
import de.bht.fpa.mail.s786848.imapnavigation.account.ImapElement;

public class ViewLabelProvider extends LabelProvider {
  @Override
  public String getText(Object element) {
    // here you decide for each tree item which text to show. You usually do a
    // bunch on instanceof checks for every possible type in your tree.
    if (element instanceof ImapElement) {
      return ((ImapElement) element).getName();
    }

    return super.getText(element);
  }

  @Override
  public Image getImage(Object element) {
    // here you decide for each tree item which icon to show. You usually do a
    // bunch on instanceof checks for every possible type in your tree.
    if (!(element instanceof ImapAccount)) {
      return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/folder.png").createImage();
    } else {
      return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/file.png").createImage();
    }
  }
}
