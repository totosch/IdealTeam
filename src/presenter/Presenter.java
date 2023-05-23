package presenter;

import model.Model;
import view.View;

public class Presenter {
	private Model model;
	private View view;
	
	public Presenter(View view, Model model) {
		this.view = view;
		this.model = model;
		
		model.createIntegrante();
	}
	public void startGame() {
		view.inicializarView();
	}
	
	
}