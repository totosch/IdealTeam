package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

public class View {

	private JFrame frame;
	private JButton botonCorrerSolver;
	private JButton botonBuscarEmpleados;
	private JPanel containerEmpleados;

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
	
	public JButton getBotonCorrerSolver() {
		return botonCorrerSolver;
	}
	
	public JButton getBotonBuscarEmpleados() {
		return botonBuscarEmpleados;
	}

	public View() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1280, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabs = new JTabbedPane();
		
		tabs.add("Dashboard", construirDashboard());
		tabs.add("Empleados disponibles", construirVistaEmpleadosTotales());
		tabs.add("Empleados incompatibles", construirVistaIncompatibilidades());
		
		frame.getContentPane().add(tabs);
		frame.pack();
	}
	
	private JPanel construirDashboard() {
		JPanel panelCentradoPrincipal = crearPanelCentrado(100, 50);
		JPanel container = new JPanel(new GridLayout(0, 3, 10, 10));
		
		JButton botonSim = new JButton("Iniciala papu");
		JLabel diceAlgo = new JLabel("cambiare");
		JProgressBar a = new JProgressBar();
		
		botonSim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Simulacion s = new Simulacion(a, diceAlgo, 20);
				s.execute();
			}
		});
		
		container.add(botonSim);
		container.add(diceAlgo);
		container.add(a);
		
		botonCorrerSolver = new JButton("Esto corre el solver");
		
		container.add(botonCorrerSolver);
		panelCentradoPrincipal.add(container);
		
		return panelCentradoPrincipal;
	}
	
	private JScrollPane construirVistaEmpleadosTotales() {
		containerEmpleados = new JPanel(new GridLayout(0, 3, 10, 10));
		
		botonBuscarEmpleados = new JButton("conseguir empleados");
		
		containerEmpleados.add(botonBuscarEmpleados);
        
		JScrollPane panelPrincipal = new JScrollPane(containerEmpleados);
		panelPrincipal.setPreferredSize(new Dimension(1280, 800));
		
		return panelPrincipal;
	}
	
	public void popularEmpleadosTotales(List<EmpleadoView> empleados){
		empleados.forEach(empleado -> System.out.println(empleado.getNombre()));
		empleados.forEach(empleado -> containerEmpleados.add(new TarjetaEmpleado(
				"https://qotoqot.com/sad-animations/img/200/silent_tears/silent_tears.png", 
				empleado.getNombre(),
				empleado.getValor(), 
				empleado.getRol())));
		
		containerEmpleados.revalidate();
	}
	
	private JScrollPane construirVistaIncompatibilidades() {
		JPanel container2 = new JPanel(new GridLayout(0, 1, 10, 10));
		
        for (int i = 1; i <= 10; i++) {
            TarjetaEmpleado tarjeta = new TarjetaEmpleado("https://qotoqot.com/sad-animations/img/200/silent_tears/silent_tears.png","santi" + i + "", i, "jeje");
            TarjetaEmpleado tarjeta2 = new TarjetaEmpleado("https://qotoqot.com/sad-animations/img/200/silent_tears/silent_tears.png","santi" + i + "", i, "jeje");
            
            JPanel p = new JPanel(new GridLayout(1, 5, 10, 10));
            
            p.add(tarjeta);
            JLabel textoUnion = new JLabel("es incompatible con:");
            textoUnion.setHorizontalAlignment(SwingConstants.CENTER);
            p.add(textoUnion);
            p.add(tarjeta2);

            container2.add(p);
        }
        
		
        JPanel panelCentrado = crearPanelCentrado(100, 50);		
        panelCentrado.add(container2, BorderLayout.CENTER);
		
		JScrollPane panelSecundario = new JScrollPane(panelCentrado);
		panelSecundario.setPreferredSize(new Dimension(1280, 800));
		
		return panelSecundario;
	}
	
	private JPanel crearPanelCentrado(int margenX, int margenY) {
		JPanel panelConMargen = new JPanel(new BorderLayout());
		panelConMargen.setBorder(BorderFactory.createEmptyBorder(margenY, margenX, margenY, margenX));
		
		return panelConMargen;
	}
	
	public void agregarActionListenerBoton(ActionListener listener, JButton button) {
		button.addActionListener(listener);		
	}
	
	public void mostrarMensajeEmergente(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

	public void inicializarView() {
		this.frame.setVisible(true);
	}
}
