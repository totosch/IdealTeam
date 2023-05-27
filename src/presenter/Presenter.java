package presenter;

import model.Model;
import model.Integrante;
import view.View;

import java.util.ArrayList;

public class Presenter {
    private Model model;
    private View view;

    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;

        //este numero tiene que ser el mismo que usas en el view para la cantidad de tarjetitas
        ArrayList<Integrante> integrantes = model.createIntegrantes(5);
        model.establecerRelaciones(integrantes);

        printIntegrantes(integrantes);
    }

    public void startGame() {
        view.inicializarView();
    }

    private void printIntegrantes(ArrayList<Integrante> integrantes) {
        for (Integrante integrante : integrantes) {
            System.out.println(integrante.toString());
        }
    }
}
