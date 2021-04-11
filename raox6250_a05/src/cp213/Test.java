package cp213;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel display = new JPanel();
		display.setBackground(Color.GREEN);
		panel.add(display);
		
		JPanel keypad = new JPanel();
		keypad.setBackground(Color.CYAN);
		panel.add(keypad);
		keypad.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton = new JButton("New button");
		keypad.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		keypad.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		keypad.add(btnNewButton_2);
	}

}
