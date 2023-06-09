package presenter;

import model.Model;
import model.Auxiliares;
import model.Integrante;
import view.AccionSimultanea;
import view.IntegranteView;
import view.Simulacion;
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
        
        view.agregarActionListenerBoton(new SolverListener(),view.getBotonCorrerSolver());
        view.agregarActionListenerBoton(new FetchListener(),view.getBotonBuscarEmpleados());
        view.agregarActionListenerBoton(new RelacionesListener(),view.getBotonIncompatibilidades());
        view.agregarActionListenerBoton(new RegisterListener(),view.getBotonRegistrarCantidades());
    }

    public void startGame() {
        view.inicializarView();
    }
    
    public class FetchListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Simulacion simulacion = new Simulacion(
					view.getBarraProgresoEmpleados(), 
					new AccionSimultanea() {
						@Override
						public List<IntegranteView> accion() {
							// TODO Auto-generated method stub
							return enviarEmpleados();
						}
			});
			
			simulacion.execute();
		}
		
		private List<IntegranteView> enviarEmpleados(){
			try {
				List<Integrante> integrantes = model.crearIntegrantes(30);
				List<IntegranteView> empleados = new ArrayList<IntegranteView>();
				integrantes.forEach(integrante -> empleados.add(new IntegranteView(
						integrante.getValor(), 
						integrante.getNombre(), 
						integrante.getRol())));
				
				view.popularEmpleadosTotales(empleados);
				
				return empleados;
			} catch (Exception ex) {
				view.mostrarMensajeEmergente(ex.getMessage());
			}
			return null;
		}
    	
    }
    
    public class RelacionesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Simulacion simulacion = new Simulacion(
					view.getBarraProgresoIncompatiblidades(), 
					new AccionSimultanea() {
						@Override
						public HashSet<HashSet<IntegranteView>> accion() {
							// TODO Auto-generated method stub
							return enviarRelaciones();
						}
			});
			
			simulacion.execute();
		}
		
		private HashSet<HashSet<IntegranteView>> enviarRelaciones() {
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
				
				view.popularIncompatibilidades(relacionesMalasView);
				
				return relacionesMalasView;
			} catch (Exception ex) {
				view.mostrarMensajeEmergente(ex.getMessage());
			}
			return null;
		}
    	
    }
    
    public class SolverListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
				Simulacion simulacion = new Simulacion(view.getBarraProgresoResolver(),
						new AccionSimultanea() {

							@Override
							public Object accion() {
								try {
								List<Integrante> equipoIdeal = model.resolverProblema();
								
								if (equipoIdeal.size() < 1) {
									view.popularEquipoGanador(null);
									
									return null;
								}
								
								List<IntegranteView> equipoView = new ArrayList<IntegranteView>();
								equipoIdeal.forEach(integrante -> equipoView.add(new IntegranteView(
										integrante.getValor(), 
										integrante.getNombre(), 
										integrante.getRol())));
								
								view.popularEquipoGanador(equipoView);
								} catch (Exception ex) {
									view.mostrarMensajeEmergente(ex.getMessage());
								}
								return null;
							}
				});
				
				simulacion.execute();
		}
    	
    }
    
    public class RegisterListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				view.calcularCantidadesPorRol();
				model.registrarCantidadPorPuesto(view.getCantidadPorRol());
				view.transformarInputsAInvariables();
			} catch (Exception ex) {
				view.mostrarMensajeEmergente(ex.getMessage());
			}
		}
    	
    }
}
