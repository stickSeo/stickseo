package Paint;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
/**
 * Custom Graphics Example: Paint
 */
public class MyPaint extends JFrame {
   // Name-constants for the various dimensions
   public static final int CANVAS_WIDTH = 500;
   public static final int CANVAS_HEIGHT = 300;
   public static final Color LINE_COLOR = Color.RED;
 
   // Lines, consists of a List of PolyLine instances
   private List<PolyLine> lines = new ArrayList<PolyLine>();
   private PolyLine currentLine;  // the current line (for capturing)
 
   /** Constructor to set up the GUI */
   public MyPaint() {
      DrawCanvas canvas = new DrawCanvas();
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
      canvas.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            // Begin a new line
            currentLine = new PolyLine();
            lines.add(currentLine);
            currentLine.addPoint(e.getX(), e.getY());
         }
      });
      canvas.addMouseMotionListener(new MouseMotionAdapter() {
         @Override
         public void mouseDragged(MouseEvent e) {
            currentLine.addPoint(e.getX(), e.getY());
            System.out.println("x 좌표 :" + e.getX() + "y 좌표 : "+e.getY());
            
            repaint();  // invoke paintComponent()
         }
      });
 
      setContentPane(canvas);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("Paint");
      pack();
      setVisible(true);
   }
 
   /** DrawCanvas (inner class) is a JPanel used for custom drawing */
   private class DrawCanvas extends JPanel {
      @Override
      protected void paintComponent(Graphics g) { // called back via repaint()
         super.paintComponent(g);
         g.setColor(LINE_COLOR);
         for (PolyLine line: lines) {
            line.draw(g);
         }
      }
   }
 
   /** The entry main method */
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         // Run the GUI codes on the Event-Dispatching thread for thread safety
         @Override
         public void run() {
            new MyPaint(); // Let the constructor do the job
         }
      });
   }
}