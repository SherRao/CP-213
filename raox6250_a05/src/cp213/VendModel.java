package cp213;

import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Vending machine model. Contains the algorithms for vending products, handling
 * change and inventory, and working with credit. Should not perform any GUI
 * work of any kind.
 *
 * @author Nausher Rao
 * @version 2021-03-19
 */
public class VendModel {

	public enum ButtonType { NUMBER, CASH, CARD, ADD_ITEM, CANCEL };
	
	private ExecutorService executors;
    private Random random;
	
    private ArrayList<Product> stock;
    private int row;
    private int columns;
    private String[] denominations;
    private float totalCash;
    
    private int inputSlot;
    private ArrayList<Product> purchaseList;
    private double totalCost;

    /** Try to set the look and feel to WinUI */
    static {
    	 try { 
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
             
         } catch (Exception e) { 
        	 System.err.println("Couldn't change the System look and feel. Check the stacktrace.");
        	 e.printStackTrace(); 
        	 
         }
    }
    
    public VendModel() {
    	this.executors = Executors.newSingleThreadExecutor();
        this.random = new Random(System.currentTimeMillis());	
        
    	this.stock = new ArrayList<>();
        this.row = 4;
        this.columns = 6;
        this.denominations = new String[] {"5c", "10c", "25c", "50c", "$1", "$2", "$5", "$10", "Done" };
        
        this.inputSlot = 0;
        this.purchaseList = new ArrayList<>();
        
        for(int i = 0; i < row * columns; i++) {
        	int index = random.nextInt(Product.ITEM_NAMES.length);
        	stock.add(new Product( Product.ITEM_NAMES[index], random.nextInt(11), 1 + random.nextFloat() * 9, Product.ITEM_IMAGES[index] ));
        
        }
        
        this.totalCash = (random.nextFloat() * 10000) + 1000;
    }
    
	public String buttonPress(ButtonType type, String... args) {
		switch(type) {
			case NUMBER:
				if(inputSlot == 0 || inputSlot >= 10) {
					inputSlot = Integer.parseInt(args[0]);
				
				} else {
					inputSlot *= 10;
					inputSlot += Integer.parseInt(args[0]);

				}  
				
				return "Select Item: " + inputSlot;
				
			case CASH:
				if(purchaseList.size() > 0) {
					payByCash();
					return " ";
					
				} else {
					Toolkit.getDefaultToolkit().beep();
					return "No items to purchase!";
				
				} 
				
			case CARD:
				if(purchaseList.size() > 0) {
					return payByCard();
					
				} else {
					Toolkit.getDefaultToolkit().beep();
					return "No items to purchase!";
				
				}
				
			case ADD_ITEM:
				if(inputSlot > 0 && inputSlot <= 24) {
					Product product = stock.get(inputSlot - 1);
					if(product.amount > 0) {
						product.amount -= 1;
						stock.set(inputSlot - 1, product);
						purchaseList.add(product);
						totalCost += product.price * 100;
						inputSlot = 0;
						
						return "Added Item: " + product.name;
						
					} else {
						Toolkit.getDefaultToolkit().beep();
						return "Out of stock!";
					}
					
					
				} else {
					Toolkit.getDefaultToolkit().beep();
					return "Invalid Input: " + inputSlot;
					
				}
				
			default:
				return null;
		}
	}
	
	public void payByCash() {
		totalCost = Math.round(totalCost * 100.0) / 100.0;
		SwingUtilities.invokeLater( () -> {
			boolean run = true;
			int cents = 0;

			while(run) {
				int input = JOptionPane.showOptionDialog(null, String.format("Cost: $%.2f\nWhat cash would you like to use?\nCurrent: $%.2f", (totalCost / 100.00), (cents / 100.00)), "Pay By Cash", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, denominations, null);
				switch(input) {
					case 8:
						run = false;
						break;
						
					case 0:
						cents += 5;
						break;
						
					case 1:
						cents += 10;
						break;		
						
					case 2:
						cents += 25;
						break;		
						
					case 3:
						cents += 50;
						break;		
						
					case 4:
						cents += 100;
						break;		
						
					case 5:
						cents += 200;
						break;	
						
					case 6:
						cents += 500;
						break;	
						
					case 7:
						cents += 1000;
						break;
				}
			}
			
			if(cents >= totalCost) {
				JOptionPane.showMessageDialog(null, "Your transaction was sucessful!");
				calculateChange(cents);
				System.exit(0);
				
			} else {
				JOptionPane.showMessageDialog(null, "You didn't deposit enough money!");
				payByCash();
				
			}
			
		} );
		
	}
	
	public String payByCard() {
		totalCost = Math.round(totalCost * 100.0) / 100.0;
		int totalDelay = random.nextInt(20000) + 20000;
		boolean success = false;
		JOptionPane.showMessageDialog(null, String.format("Cost: $%.2f\nPress \"OK\" to proceed with transaction. This can take up to 40 seconds - please wait for the popup!", totalCost / 100) );
		try {
			success = executors.submit( () -> {
				Thread.sleep(totalDelay);
				return random.nextFloat() > 0.10;
				
			} ).get();
			
		} catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }

		if(success) {
			JOptionPane.showMessageDialog(null, "Your transaction was successful!");
			System.exit(0);
			return ":)";
			
		} else {
			JOptionPane.showMessageDialog(null, "Your transaction could not be processed! Press 'OK' to try again!");
			return payByCard();
			
		}
	}
	
	public void calculateChange(int cents) {
 		cents -= totalCost;
		int ten = cents / 1000;
		cents %= 1000;
		
		int five = cents / 500;
		cents %= 500;
		
		int two = cents / 200;
		cents %= 200;
		
		int one = cents / 100;
		cents %= 100;
		
		int fifties = cents / 50;
		cents %= 50;
		
		int quarters = cents / 25;
		cents %= 25;
		
		int tens = cents / 10;
		cents %= 10;
		
		int fives = cents / 5;
		cents %= 5;
		
		JOptionPane.showMessageDialog(null, String.format("Here is your change\nTen Dollars: %d\nFive Dollars: %d\nTwo Dollars %d\nOne Dollar: %d\nFifty Cents: %d\nQuarters: %d\nTen Cents: %d\nFive Cents: %d\nOne Cents: %d", ten, five, two, one, fifties, quarters, tens, fives, cents) );
	}
	
	public void reset() {
		totalCash += totalCost;
		totalCost = 0;
		
		// Reset quantities
		for(int i = 0; i < stock.size(); i++) {
			Product p = stock.get(i);
			p.amount = p.initialAmount;
			stock.set(i, p);
			
		}
	}
	
	
    public ArrayList<Product> getStock() {
        return this.stock;

    }
    
}