import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

public class FigureReport extends JFrame implements ActionListener {
	private ButtonGroup G1, G2;
	private JRadioButton jrbRed, jrbYellow, jrbBlack, jrbGray, jrbGreen;
	private JRadioButton jrbLine, jrbRectangle, jrbOval;
	private JCheckBox jrbFilled;
	private Color color;
	private boolean full;
	private int type;

	public FigureReport() {
		G1 = new ButtonGroup();
		G2 = new ButtonGroup();

		jrbRed = new JRadioButton("Red");
		jrbYellow = new JRadioButton("Yellow");
		jrbBlack = new JRadioButton("Black");
		jrbGray = new JRadioButton("Gray");
		jrbGreen = new JRadioButton("Green");

		jrbLine = new JRadioButton("Line");
		jrbRectangle = new JRadioButton("Rectangle");
		jrbOval = new JRadioButton("Oval");
		jrbFilled = new JCheckBox("Filled");

		Panel P1 = new Panel();
		Panel P2 = new Panel();
		Panel P3 = new Panel();
		PaintPanel P4 = new PaintPanel();
		

		setLayout(new BorderLayout());
		P1.setLayout(new FlowLayout());
		P2.setLayout(new BorderLayout());
		P3.setLayout(new FlowLayout());

		G1.add(jrbRed);
		G1.add(jrbYellow);
		G1.add(jrbBlack);
		G1.add(jrbGray);
		G1.add(jrbGreen);

		G2.add(jrbLine);
		G2.add(jrbRectangle);
		G2.add(jrbOval);

		P1.add(jrbRed);
		P1.add(jrbYellow);
		P1.add(jrbBlack);
		P1.add(jrbGray);
		P1.add(jrbGreen);
		
		P2.add(P4,BorderLayout.CENTER);
		
		P3.add(jrbLine);
		P3.add(jrbRectangle);
		P3.add(jrbOval);
		P3.add(jrbFilled);

		add(P1, BorderLayout.NORTH);
		add(P2, BorderLayout.CENTER);
		add(P3, BorderLayout.SOUTH);
		
		jrbRed.addActionListener(this);
		jrbYellow.addActionListener(this);
		jrbBlack.addActionListener(this);
		jrbGray.addActionListener(this);
		jrbGreen.addActionListener(this);

		jrbLine.addActionListener(this);
		jrbRectangle.addActionListener(this);
		jrbOval.addActionListener(this);
		jrbFilled.addActionListener(this);

	}

	public static void main(String[] args) {
		FigureReport frame = new FigureReport();
		frame.setTitle("FigureReport");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (jrbRed.isSelected())
			color = Color.RED;
		else if (jrbYellow.isSelected())
			color = Color.YELLOW;
		else if (jrbBlack.isSelected())
			color = Color.BLACK;
		else if (jrbGray.isSelected())
			color = Color.GRAY;
		else if (jrbGreen.isSelected())
			color = Color.GREEN;

		if (jrbLine.isSelected())
			type = 1;
		else if (jrbRectangle.isSelected())
			type = 2;
		else if (jrbOval.isSelected())
			type = 3;

		if (jrbFilled.isSelected())
			full = true;
		else
			full = false;

	}

	class PaintPanel extends JPanel {
		public PaintPanel() {
			Timer timer= new Timer(100,new TimeListener());
			timer.start();		
			
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(color);
			if (full) {
				if (type == 1) {
					g.drawLine(getWidth()/4, getHeight()/4, getWidth()-getWidth()/4, getHeight()-getHeight()/4);
					g.drawLine(getWidth()-getWidth()/4,getHeight()/4,getWidth()/4,getHeight()-getHeight()/4);
					} else if (type == 2)
					g.fillRect(getWidth() / 4, getHeight() / 4, getWidth()/2, getHeight()/2);
				else if (type == 3)
					g.fillOval(getWidth() / 4, getHeight() / 4, getWidth() / 2,
							getHeight() / 2);
			} else {
				if (type == 1) {
					g.drawLine(getWidth()/4, getHeight()/4, getWidth()-getWidth()/4, getHeight()-getHeight()/4);
					g.drawLine(getWidth()-getWidth()/4,getHeight()/4,getWidth()/4,getHeight()-getHeight()/4);
				} else if (type == 2)
					g.drawRect(getWidth() / 4, getHeight() / 4, getWidth()/2, getHeight()/2);
				else if (type == 3)
					g.drawOval(getWidth() / 4, getHeight() / 4, getWidth() / 2,
							getHeight() / 2);
			}
		}
		class TimeListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				repaint();				
			}
		}

	}

}
