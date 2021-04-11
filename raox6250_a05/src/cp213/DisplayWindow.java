package cp213;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class DisplayWindow extends JPanel {

	private static final long serialVersionUID = 3985695618112759559L;

    private final VendModel model;
    private final VendView view;

    private GridLayout layout;
    private Dimension imageSize;
    
	public DisplayWindow(VendView view) {
		super();
		
        this.model = view.getModel();
        this.view = view;

		this.layout = new GridLayout(4, 6);
		this.imageSize = new Dimension(75, 75);
		
	}
	
	public void init() 
			throws IOException {
        for(int i = 0; i < model.getStock().size(); i++) {
        	Product product = model.getStock().get(i);

        	BufferedImage buffer = ImageIO.read( new File("assets/images/" + product.imageFile) );
        	JLabel image = new JLabel(new ImageIcon( buffer.getScaledInstance(imageSize.width, imageSize.height, BufferedImage.SCALE_REPLICATE) ));
            image.setPreferredSize(imageSize);
            
            JLabel text = new JLabel((i + 1) + " : " + product.name);
            JLabel price = new JLabel(String.format("$ %.2f", product.price));
            JPanel panel = new JPanel();
            SpringLayout layout = new SpringLayout(); 
            
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, image, 0, SpringLayout.HORIZONTAL_CENTER, panel);
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, image, 0, SpringLayout.VERTICAL_CENTER, panel);
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, text, 0, SpringLayout.HORIZONTAL_CENTER, panel);
            layout.putConstraint(SpringLayout.SOUTH, text, 100, SpringLayout.NORTH, image);
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, price, 0, SpringLayout.HORIZONTAL_CENTER, panel);
            layout.putConstraint(SpringLayout.NORTH, price, 0, SpringLayout.SOUTH, text);

            panel.setLayout(layout);
            panel.add(image);
            panel.add(text);
            panel.add(price);
            super.add(panel);
        }
        
		super.setBackground(Color.GRAY);
		super.setPreferredSize(new Dimension(700,1000));
		super.setLayout(layout);
		super.setVisible(true);
	}
	
}