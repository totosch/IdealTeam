package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TarjetaEmpleado extends JPanel {
	public TarjetaEmpleado(String url, String nombreEmpleado, int puntuacion, String puesto) {
		super.setPreferredSize(new Dimension(200, 200));
		super.setLayout(new GridLayout(1, 2));
		super.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel a = obtenerPanelImagen(url);
		a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JPanel panelOcupacion = new JPanel();
		panelOcupacion.setLayout(new GridLayout(3, 1));
		
		JLabel b = new JLabel("Nombre: " + nombreEmpleado);
		JLabel c = new JLabel("Valoración: " + Integer.toString(puntuacion) + " de 5");
		JLabel d = new JLabel("Rol: " + puesto);
		
		panelOcupacion.add(b);
		panelOcupacion.add(c);
		panelOcupacion.add(d);
		
		JLabel[] muchos = { a, b, c, d };
		for (JLabel l : muchos) {
			l.setFont(new Font("Tahoma", Font.PLAIN, 20));
			l.setHorizontalAlignment(SwingConstants.CENTER);
		}

		super.add(a);
		super.add(panelOcupacion);
	}

	private JLabel obtenerPanelImagen(String url) {
		try {
			URL urlImagen = new URL(url);

			JLabel label = new JLabel(new ImageIcon(ImageIO.read(urlImagen)));

			return label;
		} catch (IOException e) {
			return new JLabel("No encontrada");
		}
	}
}
