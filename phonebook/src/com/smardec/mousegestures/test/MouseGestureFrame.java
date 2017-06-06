package com.smardec.mousegestures.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Book.DeleteEventHandler;
import Paint.PolyLine;


import com.smardec.mousegestures.MouseGestures;
import com.smardec.mousegestures.MouseGesturesListener;

public class MouseGestureFrame extends JFrame{
	
	public static final Color LINE_COLOR = Color.RED;
	   public static final int CANVAS_WIDTH = 500;
	   public static final int CANVAS_HEIGHT = 300;
	   public static final String text = "";
	   public static final JLabel label = new JLabel(text);
	   public static final int choice = 1+(int)(Math.random() * 2);
	  public static  int count = 0;
	  private List<PolyLine> lines = new ArrayList<PolyLine>();
	   private PolyLine currentLine;  // the current line (for capturing)
	
	public void init(){
		
		 
		
		
		 
		 
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
	          
	            
	            repaint();  // invoke paintComponent()
	         }
	      });
	 
	      setContentPane(canvas);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setTitle("Paint");
	      pack();
	      setVisible(true);
		 
	      switch(choice){
      	
      	case 1:
      		label.setText("DownRightUp순으로 그리세요");
      		break;
      		
      	case 2:
      		
      		label.setText("RightUp순으로 그리세요");
      		break;
      	}
		
		
	
		 getContentPane().add(label);
		 
		 MouseGestures mouseGestures = new MouseGestures();
		 mouseGestures.setMouseButton(MouseEvent.BUTTON1_MASK);

		 mouseGestures.addMouseGesturesListener(new MouseGesturesListener() {
        public void gestureMovementRecognized(String currentGesture) {
        	label.setText("    " + currentGesture);
        	
        	switch(choice){
        	
        	case 1:
        		test1(currentGesture);
        		break;
        		
        	case 2:
        		
        		test2(currentGesture);
        		break;
        	
        	}
   		    //dispose();

        	
        	/*	if("DownRightUp".equals(currentGesture)){
        		label.setText("    "  + currentGesture + " -true");
        		System.out.println(currentGesture);
        	}
        	
        	else if("UpUp".equals(currentGesture)){
        		label.setText("    "  + currentGesture + " - 테스트입니다.");
        		System.out.println(currentGesture);
        	}
        	
        	else if("DRU".startsWith(currentGesture)){
        		label.setText("    "  + currentGesture + " - You need to make a DRU");
        		System.out.println(currentGesture);
        	}
        	else{
        		//dispose();
        	}  */
        	
        
        	
        	
        }
        
     
        
        
        public void processGesture(String gesture) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {}
            label.setText(text);
            label.setForeground(Color.BLACK);
        }
 });

		 mouseGestures.start();
		 setVisible(true);

		 
	}
	
	
public void test1(String currentGesture){
	
	

	
	
  	if("DownRightUp".equals(currentGesture)){
		label.setText("    "  + currentGesture + " -true");
		System.out.println(currentGesture);
		
	
		count = 1;
		// setVisible(false);
		 dispose();
				
	}
  	
  	

	
  	
  	
	
	
}
	

public void test2(String currentGesture){
	
	

	
	
  	if("RightUp".equals(currentGesture)){
		label.setText("    "  + currentGesture + " -true");
		System.out.println(currentGesture);
		count = 1;
		 dispose();
	}
  	
	
	
	
}

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
	
	
	public static void main(String[] args){
		final MouseGestureFrame frame = new MouseGestureFrame();
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				frame.init();

			}
		});
	}
	
	public static void exit(){
		final MouseGestureFrame frame = new MouseGestureFrame();

		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
			    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			   // frame.setVisible(false);
				frame.dispose();
				
			}
		});
	}
}


