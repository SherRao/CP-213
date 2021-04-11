package cp213;

/**
 * 
 * Represents an item in the vending machine
 * 
 * @author Nausher Rao
 * 
 */
public class Product {

    public static final String[] ITEM_NAMES = {"Coca Cola", "Monster Energy", "Pepsi", "Sprite", "7Up", "Cheetos", "Oreo Thins", "Milk2Go", 
        "Doritos", "Ice Tea", "Apple", "Pear", "Red Bull", "Sun Chips", "Twinkies", "Poland Spring", "Dasani", "Voss", "Pop Rocks",
        "Ice", "Pizza", "Soup", "Chocolate Bar", "Bitcoin", "Nestea", "Yacht" };
    
    public static final String[] ITEM_IMAGES = {"coke.png", "monster.png", "pepsi.png", "sprite.png", "7up.png", "cheetos.png", "oreo.png", "milk.png", 
            "doritos.png", "icetea.png", "apple.png", "pear.png", "redbull.png", "sunchips.png", "twinkies.jpg", "poland.png", "dasani.png", "voss.png", "pop.png",
            "ice.png", "pizza.png", "soup.png", "choc.png", "bitcoin.png", "nestea.png", "yacht.png" };

    public String name;
    public int amount;
    public int initialAmount;
    public float price;
    public String imageFile;

    public Product(String name, int amount, float price, String imageFile) {
        this.name = name;
        this.amount = amount;
        this.initialAmount = amount;
        this.price = price;
        this.imageFile = imageFile;
        
    }  
    
    @Override
    public boolean equals(Object other) {
    	if(!(other instanceof Product))
    		return false;
    	
    	Product product = (Product) other;
    	return this.name.equals(product.name) && this.price == product.price;
    }
    
}