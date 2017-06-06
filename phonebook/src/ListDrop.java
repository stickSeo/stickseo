import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;


public class ListDrop extends JFrame {

    JTextField field;
    DefaultListModel model;

    public ListDrop() {

        setTitle("ListDrop");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));

        JScrollPane pane = new JScrollPane();
        pane.setPreferredSize(new Dimension(180, 150));

        model = new DefaultListModel();
        JList list = new JList(model);

        list.setDropMode(DropMode.INSERT);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setTransferHandler(new ListHandler());

        field = new JTextField("");
        field.setPreferredSize(new Dimension(150, 25));
        field.setDragEnabled(true);

        panel.add(field);
        pane.getViewport().add(list); 
        panel.add(pane);

        add(panel);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private class ListHandler extends TransferHandler {
        public boolean canImport(TransferSupport support) {
             if (!support.isDrop()) {
                 return false;
             }

             return support.isDataFlavorSupported(DataFlavor.stringFlavor);
         }

         public boolean importData(TransferSupport support) {
             if (!canImport(support)) {
               return false;
             }

             Transferable transferable = support.getTransferable();
             String line;
             try {
               line = (String) transferable.getTransferData(DataFlavor.stringFlavor);
             } catch (Exception e) {
               return false;
             }

             JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
             int index = dl.getIndex();

             String[] data = line.split(",");
             for (String item: data) {
                 if (!item.isEmpty())
                    model.add(index++, item.trim());
             }
             return true;
         }
    }

    public static void main(String[] args) {
        new ListDrop();
    }
}

