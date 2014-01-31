package de.bht.fpa.mail.s786848.fsnavigation;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.xml.bind.JAXB;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.rcp.selection.SelectionHelper;

public class NavigationView extends ViewPart implements Observer {
  public static final String ID = "de.bht.fpa.s786848.fsnavigation.NavigationView";
  private TreeViewer viewer;

  /**
   * This is a callback that will allow us to create the viewer and initialize
   * it.
   */

  @Override
  public void createPartControl(Composite parent) {
    // a TreeViewer is a Jface widget, which shows trees
    viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

    // We set the ContentProvider for the TreeViewer. This class prepares data
    // to be shown by the TreeViewer.
    viewer.setContentProvider(new ViewContentProvider());

    // We set the LabelProvider. This class decides how elements in the tree are
    // shown by returning a text and an optional icon.
    viewer.setLabelProvider(new ViewLabelProvider());

    // Here we set the root of the tree. The framework will ask for more data
    // when the user expands tree items.
    viewer.setInput(createModel());
    getSite().setSelectionProvider(viewer);

    // the SelectionChangedListener gets initialized
    viewer.addSelectionChangedListener(new ISelectionChangedListener() {

      /*
       * This method gets active every time when the selection is changed. If a
       * folder is clicked it returns the name of the selected folder. If a
       * folder holding *.xml files is clicked the files will get get parsed and
       * checked for correctness. In case of no errors the parsed *.xml files
       * will be write down to the console.
       */
      @Override
      public void selectionChanged(SelectionChangedEvent event) {

        try {

          if (SelectionHelper.handleStructuredSelectionEvent(event, Directory.class) != null) {
            System.out.println(SelectionHelper.handleStructuredSelectionEvent(event, Directory.class)
                + " folder clicked");
          }

          Directory x = SelectionHelper.handleStructuredSelectionEvent(event, Directory.class);
          FileSystem[] list = x.getChildren();

          ArrayList<Message> listX = new ArrayList<Message>();

          for (FileSystem file : list) {
            if (file.toString().endsWith(".xml")) {
              Message message = JAXB.unmarshal(file.file, Message.class);
              if (message.getId() != null) {
                listX.add(message);
              }
            }
          }

          if (listX.size() > 0) {
            System.out.println("Number of Messages: " + listX.size());
          }

          for (int i = 0; i < listX.size(); i++) {
            System.out.println(listX.get(i));
          }

        } catch (Exception e) {
          System.out.println("Leeren Ordner ausgewählt");
        }
      }
    });

    BaseDirectory.getInstance().addObserver(this);
  }

  /**
   * We will set up a model to initialize tree hierarchy.
   */
  private Object createModel() {
    // Our root item is simply a dummy Object. Here you need to provide your own
    // root class.
    return new Directory(new File(System.getProperty("user.home")));
  }

  /**
   * Passing the focus request to the viewer's control.
   */
  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }

  /*
   * This method updates the base directory using the Observer
   */
  @Override
  public void update(Observable baseDirectory, Object path) {
    System.out.println("base directory updated");
    viewer.setInput(new Directory(new File((String) path)));
  }
}