import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class LoginForm extends JFrame implements ActionListener {
	JTextArea TA1 = new JTextArea();
	JTextField T1 = new JTextField();
	JTextField T2 = new JTextField();
	int count;

	public LoginForm() {
		Timer time = new Timer(500, new TimerListener());
		time.start();

		JLabel L2 = new JLabel("ID");
		JLabel L3 = new JLabel("PASSWORD");

		JButton B1 = new JButton("로그인");

		JPanel P1 = new JPanel();
		JPanel P2 = new JPanel();
		JPanel P2_1 = new JPanel();
		JPanel P2_2 = new JPanel();
		JPanel P2_3 = new JPanel();
		JPanel P2_4 = new JPanel();

		setLayout(new BorderLayout());
		P1.setLayout(new GridLayout(1, 1));
		P2.setLayout(new GridLayout(1, 4));
		P2_1.setLayout(new GridLayout(1, 1));
		P2_2.setLayout(new GridLayout(2, 1));
		P2_3.setLayout(new GridLayout(2, 1));
		P2_4.setLayout(new GridLayout(1, 1));

		B1.addActionListener(this);

		P1.add(TA1);
		P2_2.add(L2);
		P2_2.add(L3);
		P2_3.add(T1);
		P2_3.add(T2);
		P2_4.add(B1);

		P2.add(P2_1);
		P2.add(P2_2);
		P2.add(P2_3);
		P2.add(P2_4);
		add(P1, BorderLayout.NORTH);
		add(P2, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		LoginForm frame = new LoginForm();
		frame.setTitle("LoginForm");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 100);
		frame.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		TA1.setText(T1.getText() + " 님이 로그인하셨습니다.");
		TA1.setBackground(Color.YELLOW);

	}

	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			count++;
			if (count % 2 == 0)
				TA1.setBackground(Color.YELLOW);
			else if (count % 2 == 1)
				TA1.setBackground(Color.RED);
		}
	}

}
