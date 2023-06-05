package view;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class Simulacion extends SwingWorker<Object, Object> {
	private JProgressBar barraProgreso;
	private AccionSimultanea accionSimultanea;
	
	public Simulacion(JProgressBar barra, AccionSimultanea accionSimultanea) {
		this.barraProgreso = barra;
		this.accionSimultanea = accionSimultanea;
	}

	@Override
	protected Object doInBackground() throws Exception {
		barraProgreso.setIndeterminate(true);
		Object resultado = accionSimultanea.accion();
		
		return resultado;
	}
	
	@Override
	public void done() {
		if (!this.isCancelled()) {
			barraProgreso.setIndeterminate(false);
		}
	}
}
