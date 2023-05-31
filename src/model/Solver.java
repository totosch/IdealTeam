package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Solver {
	private Set integrantes;

	private Set mayor;

	private Set actual;

	private int generados;

	public Solver(Set integrantesSolver) {
		integrantes = integrantesSolver;
	}

	public Set resolver() {
		mayor = new HashSet();
		actual = new HashSet();

		generarDesde(0);
		return mayor;
	}

	private void generarDesde(Integrante index) {
		ArrayList<Integrante> equipo = new ArrayList<Integrante>();
		if (0 == integrantes.size()) {
			if (Auxiliares.esEquipoBienFormado(actual) && actual.size() > mayor.size()) {
				mayor = clonar(actual);
			}
			generados++;
		} else {
			actual.add(index);
			generarDesde(index + 1);

			actual.remove(index);
			generarDesde(index + 1);
		}
	}

	private Set clonar(Set set) {
		Set setClonado = new HashSet();
		for (Integrante integrante : setClonado) {
			setClonado.add(integrante);
		}
		return setClonado;
	}

	public int getGenerated() {
		return generados;
	}

}
