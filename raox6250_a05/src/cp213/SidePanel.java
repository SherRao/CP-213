package cp213;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import cp213.VendModel.ButtonType;

public class SidePanel extends JPanel {

	private static final long serialVersionUID = 1769530202335411637L;

    private final VendModel model;
    private final VendView view;

	private JPanel numbers;
	private SpringLayout parentLayout;
	private GridLayout keypadLayout;
	
	private Font buttonFont;
	
	private JButton[] numberButtons;
	private JButton cashButton;
	private JButton creditButton;
	private JButton addItemButton;
	private JButton cancelButton;
	private JTextArea itemList;
	private JLabel output;
	
	public SidePanel(VendView view) {
		super();
		
        this.model = view.getModel();
		this.view = view;

		this.numbers = new JPanel();
		this.parentLayout = new SpringLayout();
		this.keypadLayout = new GridLayout(3, 3);
		
		this.buttonFont = super.getFont().deriveFont(Font.BOLD, 20);
		
		this.numberButtons = new JButton[9];
		this.cashButton = new JButton("Cash");
		this.creditButton = new JButton("Credit");
		this.addItemButton = new JButton("Add");
		this.cancelButton = new JButton("Cancel");
		this.itemList = new JTextArea("Items To Purchase:\n====================\n", 10, 1);
		this.output = new JLabel("Select Item: ");
		
	}
	
	public void init() {
		Dimension numberButtonSize = new Dimension(75, 75);
		for(int i = 1; i < 10; i++) {
			JButton button = new JButton(Integer.toString(i));
			button.setPreferredSize(numberButtonSize);
			button.setFont(buttonFont);
			button.addActionListener( event -> output.setText( model.buttonPress(ButtonType.NUMBER, button.getText()) ));

			numberButtons[i - 1] = button; 
		}
		
		numbers.setLayout(keypadLayout);
		for(JButton button : numberButtons)
			numbers.add(button);
            
		// Sets the three actionable buttons to respond to presses, and sets their size.
		Dimension buttonSize = new Dimension(75, 35);
		cashButton.setPreferredSize(buttonSize);
		cashButton.addActionListener( event -> output.setText( model.buttonPress(ButtonType.CASH) ));

		creditButton.setPreferredSize(buttonSize);
		creditButton.addActionListener( event -> output.setText( model.buttonPress(ButtonType.CARD) ));

		addItemButton.setPreferredSize(buttonSize);
		addItemButton.addActionListener( event -> {
			String result = model.buttonPress(ButtonType.ADD_ITEM);
			if(result.contains("Added")) {
				String product = result.substring(12);
				itemList.append("\n" + product);
				
			}
			
			output.setText(result);
		});
        
		cancelButton.setPreferredSize(buttonSize);
		cancelButton.addActionListener( event -> {
			clear();
			
		});

		// Decorate the item list TextArea.
		itemList.setEditable(false);
		itemList.setPreferredSize(new Dimension(200, 300));
		itemList.setBackground(Color.BLACK);
		itemList.setForeground(Color.WHITE);
		
		// Decorate the output Label.
		output.setOpaque(true);
		output.setPreferredSize(new Dimension(250, 50));
		output.setBackground(Color.BLACK);
		output.setForeground(Color.WHITE);

		// Centers the number keypad.
		parentLayout.putConstraint(SpringLayout.VERTICAL_CENTER, numbers, -200, SpringLayout.VERTICAL_CENTER, this);       	
		parentLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, numbers, 0, SpringLayout.HORIZONTAL_CENTER, this);
		
		// Link the first 2 buttons relative to the keypad, and the other 2 relative to the first two.
		parentLayout.putConstraint(SpringLayout.NORTH, cashButton, 50, SpringLayout.SOUTH, numbers); 	              
		parentLayout.putConstraint(SpringLayout.NORTH, creditButton, 50, SpringLayout.SOUTH, numbers); 	                 	
		parentLayout.putConstraint(SpringLayout.NORTH, addItemButton, 10, SpringLayout.SOUTH, cashButton); 	   
		parentLayout.putConstraint(SpringLayout.NORTH, cancelButton, 10, SpringLayout.SOUTH, creditButton); 
		
		parentLayout.putConstraint(SpringLayout.EAST, cashButton, 100, SpringLayout.HORIZONTAL_CENTER, this);			 	 
		parentLayout.putConstraint(SpringLayout.WEST, creditButton, -100, SpringLayout.HORIZONTAL_CENTER, this); 	     	 
		parentLayout.putConstraint(SpringLayout.EAST, addItemButton, 100, SpringLayout.HORIZONTAL_CENTER, this);
		parentLayout.putConstraint(SpringLayout.WEST, cancelButton, -100, SpringLayout.HORIZONTAL_CENTER, this);

		// Centers the output Label.
		parentLayout.putConstraint(SpringLayout.SOUTH, output, -50, SpringLayout.NORTH, numbers); 	                 	 
		parentLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, output, 0, SpringLayout.HORIZONTAL_CENTER, numbers);
		
		// Centres the item list TextArea.
		parentLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, itemList, 0, SpringLayout.HORIZONTAL_CENTER, this);
		parentLayout.putConstraint(SpringLayout.NORTH, itemList, 100, SpringLayout.SOUTH, addItemButton);

		super.add(numbers);
		super.add(cashButton);
		super.add(creditButton);
		super.add(addItemButton);
		super.add(cancelButton);
		super.add(output);
		super.add(itemList);
		
		super.setVisible(true);
		super.validate();
		super.setBackground(Color.BLUE);
		super.setLayout(parentLayout);
		
	}
	
	public void clear() {
		itemList.setText("Items To Purchase:\n====================\n");
		output.setText("Cancelled!");
		model.reset();
		
	}
	
	public JTextArea getProductList() {
		return this.itemList;
		
	}
	
}