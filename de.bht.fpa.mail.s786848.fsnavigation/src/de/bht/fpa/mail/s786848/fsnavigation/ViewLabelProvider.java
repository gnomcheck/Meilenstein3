package de.bht.fpa.mail.s786848.fsnavigation;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class ViewLabelProvider extends LabelProvider {
  @Override
  public String getText(Object element) {
    // here you decide for each tree item which text to show. You usually do a
    // bunch on instanceof checks for every possible type in your tree.
    return super.getText(element);
  }

  /*
   * This method figures out the instance of the given element and returns the
   * right icon for it
   */
  @Override
  public Image getImage(Object element) {
    if (element instanceof Directory) {
      return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/folder.png").createImage();
    } else {
      return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/file.png").createImage();
    }
  }
}
