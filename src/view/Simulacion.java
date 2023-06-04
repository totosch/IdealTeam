package view;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class Simulacion<T> extends SwingWorker<T, T> {
	private JProgressBar barraProgreso;
	private AccionSimultanea<T> accionSimultanea;
	
	public Simulacion(JProgressBar barra, AccionSimultanea<T> accionSimultanea) {
		this.barraProgreso = barra;
		this.accionSimultanea = accionSimultanea;
	}

	@Override
	protected T doInBackground() throws Exception {
		barraProgreso.setIndeterminate(true);
		T resultado = accionSimultanea.accion();
		
		return resultado;
	}
	
	@Override
	public void done() {
		if (!this.isCancelled()) {
			barraProgreso.setIndeterminate(false);
		}
	}
}
