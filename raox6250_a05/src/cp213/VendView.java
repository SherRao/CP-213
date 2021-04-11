package cp213;

import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Handles the GUI portion of the vending machine. May use other GUI classes for
 * individual elements of the vending machine. Should use the VendModel for all
 * control logic.
 *
 * @author Nausher Rao
 * @version 2021-03-19
 */
public class VendView extends JPanel {

	private static final long serialVersionUID = -6113518497638086292L;

	private VendModel model;
    private SidePanel sidePanel;
    private DisplayWindow display;
    
    public VendView(VendModel model) {
    	super();
    	
    	this.model = model;
    	this.sidePanel = new SidePanel(this);
    	this.display = new DisplayWindow(this);
    	
    	try {
    		sidePanel.init();
			display.init();
		
    	} catch (IOException e) {
			e.printStackTrace();
			
    	}
    	
        super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        super.setVisible(true);
    	super.add(display);
    	super.add(sidePanel);
    
    }

	public VendModel getModel() {
		return model;
	
	}
	
	public SidePanel getSidePanel() {
		return this.sidePanel;
		
	}
	
	public DisplayWindow getDisplayWindow() {
		return this.display;
		
	}

}