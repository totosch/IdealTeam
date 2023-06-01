package presenter;

import model.Model;
import model.Auxiliares;
import model.Integrante;
import view.IntegranteView;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
				List<IntegranteView> empleados = new ArrayList<IntegranteView>();
				integrantes.forEach(integrante -> empleados.add(new IntegranteView(
						integrante.getValor(), 
						integrante.getNombre(), 
						integrante.getRol())));
				
				view.popularEmpleadosTotales(empleados);
			} catch (Exception ex) {
				view.mostrarMensajeEmergente(ex.getMessage());
			}
		}
    	
    }
    
    public class RelacionesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				model.establecerRelaciones();
				List<Integrante> integrantes = model.getIntegrantes();
				Set<Set<Integrante>> relacionesMalasSet = Auxiliares.parseRelaciones(integrantes);
				
			
				HashSet<HashSet<IntegranteView>> relacionesMalasView = new HashSet<HashSet<IntegranteView>>();
				relacionesMalasSet.forEach(tuplaIntegrante -> {
					HashSet<Integrante> tuplaIntegranteParseada = new HashSet<Integrante>(tuplaIntegrante);
					HashSet<IntegranteView> tuplaIntegranteView = new HashSet<IntegranteView>();
					
					tuplaIntegranteParseada.forEach(integrante -> tuplaIntegranteView.add(new IntegranteView(
							integrante.getValor(), 
							integrante.getNombre(), 
							integrante.getRol())));
					
					relacionesMalasView.add(tuplaIntegranteView);
				});
				
				List<IntegranteView> empleados = new ArrayList<IntegranteView>();
				
				view.popularIncompatibilidades(relacionesMalasView);
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
