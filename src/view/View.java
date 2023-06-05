package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.*;

public class View {

	private JFrame frame;
	private JButton botonCorrerSolver;
	
	private JLabel developerLabel;
	private JTextField developerInput;
	private JLabel pmLabel;
	private JTextField pmInput;
	private JLabel testerLabel;
	private JTextField testerInput;
	private JLabel liderLabel;
	private JTextField liderInput;
	
	private JButton botonBuscarEmpleados;
	private JPanel containerEmpleados;
	private JProgressBar barraProgresoEmpleados;
	private JLabel labelEmpleados;
	
	private JButton botonBuscarIncompatibilidades;
	private JPanel containerIncompatibilidades;
	private JProgressBar barraProgresoIncompatiblidades;
	private JLabel labelIncompatibilidades;

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
	
	public JButton getBotonIncompatibilidades() {
		return botonBuscarIncompatibilidades;
	}
	
	public JProgressBar getBarraProgresoEmpleados() {
		return barraProgresoEmpleados;
	}

	public JProgressBar getBarraProgresoIncompatiblidades() {
		return barraProgresoIncompatiblidades;
	}
	
	public JLabel getLabelEmpleados() {
		return labelEmpleados;
	}

	public JLabel getLabelIncompatiblidades() {
		return labelIncompatibilidades;
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
		JPanel panelCentradoPrincipal = crearPanelCentrado(400, 200);
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel menuTitle = new JLabel("Bienvenido a Equipo Ideal!");
	    menuTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    panelTitulo.add(menuTitle);
	    
	    container.add(panelTitulo);
	  
	    developerLabel = new JLabel("Ingrese cantidad de Developers");
	    developerInput = new JTextField("5");
	    pmLabel = new JLabel("Ingrese cantidad de Project Managers");
	    pmInput = new JTextField("5");
	    testerLabel = new JLabel("Ingrese cantidad de Testers");
	    testerInput = new JTextField("5");
	    liderLabel = new JLabel("Ingrese cantidad de Lideres");
	    liderInput = new JTextField("5");

	    container.add(crearPanelInput(developerLabel, developerInput));
	    container.add(crearPanelInput(pmLabel, pmInput));
	    container.add(crearPanelInput(testerLabel, testerInput));
	    container.add(crearPanelInput(liderLabel, liderInput));
	    
	    JPanel panelBoton = new JPanel();
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	    
		botonCorrerSolver = new JButton("Esto corre el solver");
		
		panelBoton.add(botonCorrerSolver);
		
		container.add(panelBoton);
		panelCentradoPrincipal.add(container);
		
		return panelCentradoPrincipal;
	}
	
	private JPanel crearPanelInput(JLabel label, JTextField input) {
	    JPanel panelInput = new JPanel();
		panelInput.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	    input.setPreferredSize(new Dimension(100, 20));
	    panelInput.add(label);
	    panelInput.add(input);
	    
	    return panelInput;
	}
	
	private JScrollPane construirVistaEmpleadosTotales() {
		containerEmpleados = new JPanel(new GridLayout(0, 3, 10, 10));
		barraProgresoEmpleados = new JProgressBar();
		
		botonBuscarEmpleados = new JButton("conseguir empleados");
		
		containerEmpleados.add(botonBuscarEmpleados);
		containerEmpleados.add(barraProgresoEmpleados);
        
		JScrollPane panelPrincipal = new JScrollPane(containerEmpleados);
		panelPrincipal.setPreferredSize(new Dimension(1280, 800));
		
		return panelPrincipal;
	}
	
	private JScrollPane construirVistaIncompatibilidades() {
		containerIncompatibilidades = new JPanel(new GridLayout(0, 1, 10, 10));
		barraProgresoIncompatiblidades = new JProgressBar();
		
		botonBuscarIncompatibilidades = new JButton("conseguir incompatibilidades");
		
		containerIncompatibilidades.add(botonBuscarIncompatibilidades);
		containerIncompatibilidades.add(barraProgresoIncompatiblidades);
		
        JPanel panelCentrado = crearPanelCentrado(100, 50);		
        panelCentrado.add(containerIncompatibilidades, BorderLayout.CENTER);
		
		JScrollPane panelSecundario = new JScrollPane(panelCentrado);
		panelSecundario.setPreferredSize(new Dimension(1280, 800));
		
		return panelSecundario;
	}
	
	public void popularEmpleadosTotales(List<IntegranteView> empleados){
		empleados.forEach(empleado -> containerEmpleados.add(new TarjetaEmpleado(
				"https://qotoqot.com/sad-animations/img/200/silent_tears/silent_tears.png", 
				empleado.getNombre(),
				empleado.getValor(), 
				empleado.getRol())));
		
		containerEmpleados.revalidate();
	}
	
	public void popularIncompatibilidades(HashSet<HashSet<IntegranteView>> relaciones) {
		relaciones.forEach(tupla -> {
			ArrayList<TarjetaEmpleado> tarjetas = new ArrayList<TarjetaEmpleado>();
			for (IntegranteView integrante: tupla) {
				tarjetas.add(new TarjetaEmpleado(
						"https://qotoqot.com/sad-animations/img/200/silent_tears/silent_tears.png",
						integrante.getNombre(),
						integrante.getValor(),
						integrante.getRol()));
			}
			
			JPanel p = new JPanel(new GridLayout(1, 5, 10, 10));
			JLabel textoUnion = new JLabel("es incompatible con:");
            textoUnion.setHorizontalAlignment(SwingConstants.CENTER);
            
            p.add(tarjetas.get(0));
            p.add(textoUnion);
            p.add(tarjetas.get(1));
            
            containerIncompatibilidades.add(p);
		});
		
		containerIncompatibilidades.revalidate();
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
