package com.smardec.mousegestures.test;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.smardec.mousegestures.MouseGestures;
import com.smardec.mousegestures.MouseGesturesListener;

public class test  {

	static JLabel label = new JLabel("");


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame j = new JFrame("test");
		
		j.setLocationRelativeTo(null);
	    j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	j.setSize(500, 100);
		j.setVisible(true);
		
		MouseGestures mouseGestures = new MouseGestures();
		mouseGestures.setMouseButton(MouseEvent.BUTTON3_MASK);
		
		
		
		mouseGestures.addMouseGesturesListener(new MouseGesturesListener() {
			
			public void gestureMovementRecognized(String currentGesture) {
		        if("DRU".equals(currentGesture)){
		            label.setText("    "  + currentGesture + " -   Wow, U have drawn 'U'");
		                    
		        }
		        else if("DRU".startsWith(currentGesture)){
		            label.setText("    "  + currentGesture + " -     You need to make a DRU");
		                      
		        } 
		        else{
		            label.setText("    "  + currentGesture + " -   Wrong gesture! release your mouse and try again");
		                        
		        } 
		    } 
		//This method is called when the user releases the mouse button finally
		//Just display the current message for a few milliseconds then
		//redisplay the original text
		    public void processGesture(String gesture) {
		        try { 
		            Thread.sleep(400);
		        } catch (InterruptedException e) {}
		        label.setText("t");
		    } 
		 }); 
		}
		
		
	}


