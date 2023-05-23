package view;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class View {

    private JFrame frame;

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

    public View() {
        initialize();
    }


    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try {
            URL imageURL = new URL("https://i.imgur.com/HzPdWG5.png");
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
