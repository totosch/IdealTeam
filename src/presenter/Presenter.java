package presenter;

import model.Model;
import model.Integrante;
import view.EmpleadoView;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Presenter {
    private Model model;
    private View view;

    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
        
        HashMap<String, Integer> cantidadPorPuesto = new HashMap<String, Integer>();
        
        for (String rol: Integrante.roles) {
        	cantidadPorPuesto.put(rol, 1);
        }
        
        model.registrarCantidadPorPuesto(cantidadPorPuesto);
        
        view.agregarActionListenerBoton(new SolverListener(),view.getBotonCorrerSolver());
        view.agregarActionListenerBoton(new FetchListener(),view.getBotonBuscarEmpleados());
    }

    public void startGame() {
        view.inicializarView();
    }
    
    public class FetchListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				System.out.println("al menos esta entrando aca?????");
				List<Integrante> integrantes = model.crearIntegrantes();
				List<EmpleadoView> empleados = new ArrayList<EmpleadoView>();
				integrantes.forEach(integrante -> empleados.add(new EmpleadoView(
						integrante.getValor(), 
						integrante.getNombre(), 
						integrante.getRol())));
				
				view.popularEmpleadosTotales(empleados);
			} catch (Exception ex) {
				view.mostrarMensajeEmergente(ex.getMessage());
			}
		}
    	
    }
    
    public class SolverListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				
				model.resolverProblema();
			} catch (Exception ex) {
				view.mostrarMensajeEmergente(ex.getMessage());
			}
		}
    	
    }
}
