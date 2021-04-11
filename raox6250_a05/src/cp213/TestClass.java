package cp213;

import java.awt.Color;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

public class TestClass {
	
	public static void main(String... vargs) {
		Random random = new Random();
		
		JFrame frame = new JFrame("Title");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(false);
		frame.setResizable(true);
		frame.setSize(768, 480);
		frame.setVisible(true);
		
		SpringLayout layout = new SpringLayout();
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		
		JLabel label = new JLabel("This will change color!");

		JButton button = new JButton("Click Me!");
		button.addActionListener((event) -> {
			Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
			label.setForeground(color);
			
		});
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, button, 0, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, button, 0, SpringLayout.VERTICAL_CENTER, panel);
		layout.putConstraint(SpringLayout.NORTH, label, 50, SpringLayout.SOUTH, button);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label, 0, SpringLayout.HORIZONTAL_CENTER, panel);
		
		
		panel.setLayout(layout);
		panel.add(label);
		panel.add(button);
		frame.setContentPane(panel);

	}

}
