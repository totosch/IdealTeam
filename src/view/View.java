package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

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
		frame.setBounds(0, 0, 1280, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabs = new JTabbedPane();
		
		JPanel container = new JPanel(new GridLayout(0, 3, 10, 10));
		
        for (int i = 1; i <= 40; i++) {
            TarjetaEmpleado tarjeta = new TarjetaEmpleado("https://qotoqot.com/sad-animations/img/200/silent_tears/silent_tears.png","santi" + i + "", i, "jeje");

            container.add(tarjeta);
        }
        
		JScrollPane panelPrincipal = new JScrollPane(container);
		panelPrincipal.setPreferredSize(new Dimension(1280, 800));
		
		JPanel container2 = new JPanel(new GridLayout(0, 1, 10, 10));
		
        for (int i = 1; i <= 10; i++) {
            TarjetaEmpleado tarjeta = new TarjetaEmpleado("https://qotoqot.com/sad-animations/img/200/silent_tears/silent_tears.png","santi" + i + "", i, "jeje");
            TarjetaEmpleado tarjeta2 = new TarjetaEmpleado("https://qotoqot.com/sad-animations/img/200/silent_tears/silent_tears.png","santi" + i + "", i, "jeje");
            
            JPanel p = new JPanel(new GridLayout(1, 3, 10, 10));
            
            p.add(tarjeta);
            JLabel textoUnion = new JLabel("es incompatible con:");
            textoUnion.setHorizontalAlignment(SwingConstants.CENTER);
            p.add(textoUnion);
            p.add(tarjeta2);

            container2.add(p);
        }
		
		JScrollPane panelSecundario = new JScrollPane(container2);
		panelSecundario.setPreferredSize(new Dimension(1280, 800));
		
		tabs.add("Empleados disponibles", panelPrincipal);
		tabs.add("Empleados incompatibles", panelSecundario);
		
		frame.getContentPane().add(tabs);
		frame.pack();
	}

	public void inicializarView() {
		this.frame.setVisible(true);
	}
}
