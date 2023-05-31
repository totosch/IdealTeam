package view;

import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class Simulacion extends SwingWorker<Integer, Integer> {
	private JProgressBar barraProgreso;
	private JLabel label;
	private int numero;
	
	public Simulacion(JProgressBar barra, JLabel label, int numero) {
		this.barraProgreso = barra;
		this.label = label;
		this.numero = numero;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		// TODO Auto-generated method stub
		barraProgreso.setIndeterminate(true);
		int i = 0;
		for (i = 0; i < numero; i++) {
			Thread.sleep(100);
		}
		
		return i;
	}
	
	@Override
	public void done() {
		try {
			if (!this.isCancelled()) {
				label.setText(get().toString());
				barraProgreso.setIndeterminate(false);
			}
			
		} catch (InterruptedException ex) {
			label.setText("error interrumpitivo");
			
		} catch (ExecutionException ex) {
			label.setText("error ejecutativo");
		}
	}
}
