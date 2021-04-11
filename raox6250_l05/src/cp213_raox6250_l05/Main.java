package cp213;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

public class Main extends JFrame {

	private static final long serialVersionUID = -7689231952635936828L;

	private JPanel panel;
	private SpringLayout layout;
	
	private JLabel image;
	private JTextField input;
	private JPanel buttonGroup;
	private JButton outputButton;
	
	public Main() {
		super();
		this.panel = new JPanel();
		this.layout = new SpringLayout();
		
		this.image = new JLabel();
		this.input = new JTextField();
		this.outputButton = new JButton();
		this.buttonGroup = new JPanel();
		
	}
	
	public void init()
			throws IOException {
		BufferedImage buffer = ImageIO.read(new File("image.png"));
		image.setIcon(new ImageIcon( buffer.getScaledInstance((int) (buffer.getWidth() * .25), (int) (buffer.getHeight() * .25), BufferedImage.SCALE_SMOOTH) ));
		
		input.setText("Type here");
		input.setPreferredSize(new Dimension(200, 50));
		outputButton.setText("Show Message");
		outputButton.addActionListener( event -> {
			JOptionPane.showMessageDialog(this, input.getText());
			
		} );
		
		buttonGroup.setLayout(new GridBagLayout());
		GridBagConstraints constr = new GridBagConstraints();
		constr.fill = GridBagConstraints.HORIZONTAL;
		constr.weightx = 0.5;
		for(int i = 0; i < 3; i++ ) {
			for(int j = 0; j < 3; j++) {
				JButton button = new JButton(Integer.toString( (i + 1) * (j + 1) ));
				constr.gridx = j;
				constr.gridy = i;
				
				buttonGroup.add(button, constr);
			}
		}
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, buttonGroup, 0, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, buttonGroup, 0, SpringLayout.VERTICAL_CENTER, panel);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, image, 0, SpringLayout.VERTICAL_CENTER, panel);
		layout.putConstraint(SpringLayout.EAST, image, -100, SpringLayout.WEST, buttonGroup);

		layout.putConstraint(SpringLayout.VERTICAL_CENTER, outputButton, 0, SpringLayout.VERTICAL_CENTER, panel);
		layout.putConstraint(SpringLayout.WEST, outputButton, 50, SpringLayout.EAST, buttonGroup);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, input, 0, SpringLayout.HORIZONTAL_CENTER, panel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, input, 100, SpringLayout.VERTICAL_CENTER, panel);
		
		panel.setLayout(layout);
		panel.add(image);
		panel.add(input);
		panel.add(outputButton);
		panel.add(buttonGroup);
		
		super.setLayout(layout);
		super.setContentPane(panel);
		super.setAlwaysOnTop(true);
		super.setTitle("CP 213 Lab 5 - Nausher Rao 190906250");
		super.setResizable(false);
		super.setSize(700, 400);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		super.setVisible(true);
	}
	
}
