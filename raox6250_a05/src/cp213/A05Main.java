package cp213;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Main class for the Vending Machine.
 *
 * @author Nausher Rao
 * @version 2021-03-19
 */
public class A05Main {

    public static void main(String[] args) {
        VendModel model = new VendModel();
        VendView vv = new VendView(model);
        final JFrame frame = new JFrame();
        
        frame.setContentPane(vv);
        frame.setSize(new Dimension(1000, 1000));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setAlwaysOnTop(false);
        frame.setTitle("Nausher Rao - Vending Machine"); 
        frame.validate();

    }
}