package view;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class View {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    View window = new View();
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
    public View() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Load and display the image
        try {
            URL imageURL = new URL("https://media.tycsports.com/files/2023/02/10/532928/lionel-messi_862x485.webp"); // Replace with your image URL
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(imageURL));
            JLabel label = new JLabel(imageIcon);
            frame.getContentPane().add(label);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	public void inicializarView() {
		this.frame.setVisible(true);
	}
}
