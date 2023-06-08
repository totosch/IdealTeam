package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.swing.*;

import model.Integrante.Rol;

public class View {

	private JFrame frame;
	
	
	private JPanel panelPrincipal;
	private JProgressBar barraProgresoResolver;
	private JPanel containerEquipo;
	private JButton botonRegistrarCantidades;
	private JButton botonCorrerSolver = new JButton();;
	
	private JTextField developerInput;
	private JTextField pmInput;
	private JTextField testerInput;
	private JTextField liderInput;
	
	private JButton botonBuscarEmpleados;
	private JPanel containerEmpleados;
	private JProgressBar barraProgresoEmpleados;
	private JLabel labelEmpleados;
	
	private JButton botonBuscarIncompatibilidades;
	private JPanel containerIncompatibilidades;
	private JProgressBar barraProgresoIncompatiblidades;
	private JLabel labelIncompatibilidades;
	
	private HashMap<Rol, Integer> cantidadPorRol;

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
	
	public JButton getBotonRegistrarCantidades() {
		return botonRegistrarCantidades;
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
	
	public JProgressBar getBarraProgresoResolver() {
		return barraProgresoResolver;
	}
	
	public JLabel getLabelEmpleados() {
		return labelEmpleados;
	}

	public JLabel getLabelIncompatiblidades() {
		return labelIncompatibilidades;
	}
	
	public HashMap<Rol, Integer> getCantidadPorRol() {
		return cantidadPorRol;
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
		panelPrincipal = crearPanelCentrado(200, 100);
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel menuTitle = new JLabel("Bienvenido a Equipo Ideal!");
	    menuTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    panelTitulo.add(menuTitle);
	    
	    container.add(panelTitulo);
	  
	    JLabel developerLabel = new JLabel("Ingrese cantidad de Developers");
	    developerInput = new JTextField("3");
	    JLabel pmLabel = new JLabel("Ingrese cantidad de Project Managers");
	    pmInput = new JTextField("1");
	    JLabel testerLabel = new JLabel("Ingrese cantidad de Testers");
	    testerInput = new JTextField("3");
	    JLabel liderLabel = new JLabel("Ingrese cantidad de Lideres");
	    liderInput = new JTextField("2");

	    container.add(crearPanelInput(developerLabel, developerInput));
	    container.add(crearPanelInput(pmLabel, pmInput));
	    container.add(crearPanelInput(testerLabel, testerInput));
	    container.add(crearPanelInput(liderLabel, liderInput));
	    
	    JPanel panelBoton = new JPanel();
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		botonRegistrarCantidades = new JButton("Registrar cantidades");
		
		panelBoton.add(botonRegistrarCantidades);
		
		container.add(panelBoton);
		panelPrincipal.add(container);
		
		return panelPrincipal;
	}
	
	public void transformarInputsAInvariables() {
		for (Component c : panelPrincipal.getComponents()) {
			panelPrincipal.remove(c);
			panelPrincipal.revalidate();
		}
		
		this.containerEquipo = new JPanel(new GridLayout(0, 1, 10, 10));
		
		JPanel informacionContainer = new JPanel();
		informacionContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		JPanel cantidadContainer = new JPanel();
		cantidadContainer.setLayout(new BoxLayout(cantidadContainer, BoxLayout.Y_AXIS));
		
		JLabel developerLabel = new JLabel("Cantidad de Developers: " + cantidadPorRol.get(Rol.Developer));
	    JLabel pmLabel = new JLabel("Cantidad de Project Managers: " + cantidadPorRol.get(Rol.PM));
	    JLabel testerLabel = new JLabel("Cantidad de Testers: " + cantidadPorRol.get(Rol.Tester));
	    JLabel liderLabel = new JLabel("Cantidad de Lideres: " + cantidadPorRol.get(Rol.Lider));
	  
	    cantidadContainer.add(developerLabel);
	    cantidadContainer.add(pmLabel);
	    cantidadContainer.add(testerLabel);
	    cantidadContainer.add(liderLabel);
	    
	    informacionContainer.add(cantidadContainer);
	    
	    botonCorrerSolver.setText("Consegui el mejor equipo!");;
	    informacionContainer.add(botonCorrerSolver);
	    
	    barraProgresoResolver = new JProgressBar();
	    informacionContainer.add(barraProgresoResolver);
	    
	    this.containerEquipo.add(informacionContainer);
	    
	    JScrollPane panelScroll = new JScrollPane(this.containerEquipo);
	    panelScroll.setPreferredSize(new Dimension(1280, 800));
	    
	    panelPrincipal.add(panelScroll);
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
	
	public void popularEquipoGanador(List<IntegranteView> empleados) {
		if (empleados == null) {
			JLabel textoError = new JLabel("No se ha podido conseguir el equipo ideal. Reinicie el programa y cambie sus condiciones");
			textoError.setHorizontalAlignment(SwingConstants.CENTER);
			containerEquipo.add(textoError);
			containerEquipo.revalidate();
			
			return;
		}
		
		empleados.forEach(empleado -> this.containerEquipo.add(new TarjetaEmpleado(
				"https://qotoqot.com/sad-animations/img/200/silent_tears/silent_tears.png", 
				empleado.getNombre(),
				empleado.getValor(), 
				empleado.getRol())));
		
		containerEquipo.revalidate();
	}
	
	public void calcularCantidadesPorRol(){
		try {
			HashMap<Rol, Integer> cantidadPorRol = new HashMap<Rol, Integer>();
			
			cantidadPorRol.put(Rol.Developer, Integer.parseInt(developerInput.getText()));
			cantidadPorRol.put(Rol.PM, Integer.parseInt(pmInput.getText()));
			cantidadPorRol.put(Rol.Tester, Integer.parseInt(testerInput.getText()));
			cantidadPorRol.put(Rol.Lider, Integer.parseInt(liderInput.getText()));
			
			this.cantidadPorRol = cantidadPorRol;
		} catch (NullPointerException e) {
			mostrarMensajeEmergente("Ingrese valores para todos los roles");
		}
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
