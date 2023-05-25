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
	private String nombreEmpleado;
	private int puntuacion;
	private String puesto;

	public TarjetaEmpleado(String url, String nombreEmpleado, int puntuacion, String puesto) {
		this.nombreEmpleado = nombreEmpleado;
		this.puntuacion = puntuacion;
		this.puesto = puesto;
		super.setPreferredSize(new Dimension(200, 200));
		super.setLayout(new GridLayout(1, 2));
		super.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel a = obtenerPanelImagen(url);
		a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JPanel panelOcupacion = new JPanel();
		panelOcupacion.setLayout(new GridLayout(2, 1));
		
		JLabel b = new JLabel("Nombre: " + nombreEmpleado);
		JLabel c = new JLabel("Valoración: " + Integer.toString(puntuacion) + " de 5");
		
		panelOcupacion.add(b);
		panelOcupacion.add(c);
		
		JLabel[] muchos = { a, b, c };
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
