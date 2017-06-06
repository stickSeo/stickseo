

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.TransferHandler;

/**
 * This class demonstrates basic use of the clipboard and drag and drop.
 * Drag and drop will allow you to drag an image from another application
 * into the image box, and drag the image to another running instance of 
 * this class.
 */
public class ClipboardDemo extends JPanel implements ClipboardOwner {

    private JTextArea textArea;
    private JLabel imageLabel;
    private Image image;
    
    public ClipboardDemo() {
        
        this.setLayout(new FlowLayout());

        // Create a text area for copy and paste tests
        // Note that by default, text widgets will support keyboard shortcuts
        // for copy/paste
        textArea = new JTextArea();
        textArea.setMinimumSize(new Dimension(300, 300));
        textArea.setPreferredSize(textArea.getMinimumSize());
        this.add(textArea);
        
        
        // Create some copy/paste buttons to support manual copying and pasting
        JButton copyButton = new JButton("Copy Text");
        JButton pasteButton = new JButton("Paste Text");
        this.add(copyButton);
        this.add(pasteButton);
        
        
        // Add action listeners for actually performing the copy/paste
        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doCopy();
            }
        });
        pasteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doPaste();
            }
        });
        
        
        // Create a label that will display an image
        imageLabel = new JLabel();
        imageLabel.setMinimumSize(new Dimension(200, 200));
        imageLabel.setPreferredSize(imageLabel.getMinimumSize());
        // Create a titled border to make its area in the interface clear
        imageLabel.setBorder(BorderFactory.createTitledBorder("Image Drop Area"));
        this.add(imageLabel);
        
        // Set up drop portion of drag and drop
        this.setupDrop();
        
        // Set up drag portion of drag and drop
        this.setupDrag();
        
    }
    
    private void setupDrop() {
        // To support drops on our imageLabel, we need to create a drop target for it.
        // A DropTarget provides hooks that indicate when a drag-n-drop operation
        // comes into play over our component.
        // Note that this example overrides DropTarget's methods. An alternative is
        // to create a listener that will be notified of these events.
        DropTarget dropTarget = new DropTarget() {
            
            public synchronized void dragEnter(DropTargetDragEvent dtde) {
                System.out.println("Drag enter");
                
                // Check the data format and reject if we can't accept it
                if (!dtde.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    dtde.rejectDrag();
                } else {
                    // Otherwise, indicate that we can take the operation as a copy
                    dtde.acceptDrag(DnDConstants.ACTION_COPY);
                }
            }
            
            public synchronized void drop(DropTargetDropEvent dtde) {
                System.out.println("Drop");
                
                // Verify we can accept the data format
                if (dtde.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    
                    // Indicate we'll accept the drop
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    
                    try {
                        // Get the data and set our label's image icon to the new image.
                        // Save a copy of the image so we can support dragging it out
                        image = (Image)dtde.getTransferable().getTransferData(DataFlavor.imageFlavor);
                        imageLabel.setIcon(new ImageIcon(image));
                    } catch (UnsupportedFlavorException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            
            public void dragExit(DropTargetEvent dte) {
                System.out.println("Drag exit");
            }
            
            public void dragOver(DropTargetDragEvent dtde) {
                System.out.println("Drag over");
            }
            
            public void dropActionChanged(DropTargetDragEvent dtde) {
                System.out.println("Drop action changed");
            }
        };
        // Register the dropTarget with our image label
        imageLabel.setDropTarget(dropTarget);
    }
    
    private void setupDrag() {
        
        // Create drag gesture recognizer that will feed the image to DnD
        DragSource dragSource = new DragSource();
        
        // Create a drag gesture recognizer. This will recognize when the
        // user starts a drag, freeing us from having to detect this using
        // mouse events
        dragSource.createDefaultDragGestureRecognizer(imageLabel, DnDConstants.ACTION_COPY, new DragGestureListener() {
            public void dragGestureRecognized(DragGestureEvent dge) {
                
                System.out.println("Gesture recognized");
                
                // Start the drag (though it is recognized, we have to say we want to
                // actually start dragging). To start the drag, we need to create a 
                // Transferable that holds the data we are copying.
                dge.startDrag(DragSource.DefaultCopyDrop, new Transferable() {
                    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                        if (flavor.equals(DataFlavor.imageFlavor)) {
                            return image;
                        }
                        throw new UnsupportedFlavorException(flavor);
                    }
                    public DataFlavor[] getTransferDataFlavors() {
                        return new DataFlavor[] { DataFlavor.imageFlavor };
                    }
                    public boolean isDataFlavorSupported(DataFlavor flavor) {
                        return flavor.equals(DataFlavor.imageFlavor);
                    }
                });
            }
            });
    }
    
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        System.out.println("Lost ownership of clipboard");
    }
    
    private void doCopy() {
        
        // Get the system clipboard
        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        
        // Create a transferable object encapsulating all the info for the copy
        Transferable transferObject = new Transferable() {
            
            // Returns the copy data
            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException ,IOException {
                if (flavor.equals(DataFlavor.stringFlavor)) {
                    return textArea.getText();
                }
                throw new UnsupportedFlavorException(flavor);
            }
            
            // Returns the set of data formats we can provide
            public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[] { DataFlavor.stringFlavor };
            }
            
            // Indicates whether we can provide data in the specified format
            public boolean isDataFlavorSupported(DataFlavor flavor) {
                return flavor.equals(DataFlavor.stringFlavor);
            }
        };
        
        // Now set the contents of the clipboard to our transferable object
        systemClipboard.setContents(transferObject, this);
    }
    
    private void doPaste() {
        
        // Grab system clipboard
        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        
        // For our own edification, print out the data formats available on the clipboard
        for (DataFlavor flavor : systemClipboard.getAvailableDataFlavors()) {
            System.out.println("Flavor: " + flavor);
        }
        
        // Check if we can get the data as a string
        if (systemClipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
            try {
                // Grab the data, set our text area to the data
                String theText = (String)systemClipboard.getData(DataFlavor.stringFlavor);
                textArea.setText(theText);
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Clipboard demo");
        f.getContentPane().add(new ClipboardDemo());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

}