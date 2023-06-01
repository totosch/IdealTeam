package presenter;

import model.Model;
import model.Integrante;
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

        model.crearIntegrantes(10);
        try {
			model.establecerRelaciones();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        HashMap<String, Integer> cantidadPorPuesto = new HashMap<String, Integer>();
        
        for (String rol: Integrante.roles) {
        	cantidadPorPuesto.put(rol, 1);
        }
        
        model.registrarCantidadPorPuesto(cantidadPorPuesto);
        
        view.agregarActionListenerBoton(new SolverListener(),view.getBotonCorrerSolver());
    }

    public void startGame() {
        view.inicializarView();
    }
    
    public class SolverListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.resolverProblema();
		}
    	
    }
}
