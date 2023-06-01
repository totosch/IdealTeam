package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Solver {
	private List<Integrante> integrantes;
	private int tamanioEquipo;
	private Map<String, Integer> cantidadPorPuesto;

	private List<Integrante> mayor;
	private int mayorValor = 0;

	private List<Integrante> actual;

	private int generados;

	public Solver(List<Integrante> integrantes, int tamanioEquipo, Map<String, Integer> cantidadPorPuesto) {
		this.integrantes = integrantes;
		this.tamanioEquipo = tamanioEquipo;
		this.cantidadPorPuesto = cantidadPorPuesto;
	}

	public List<Integrante> resolver() {
		mayor = new ArrayList<Integrante>();
		actual = new ArrayList<Integrante>();

		generarDesde(0);
		return mayor;
	}

	private void generarDesde(int indice) {
		if (indice == integrantes.size()) {
			boolean esEquipoBienFormado = Auxiliares.esEquipoBienFormado(actual, cantidadPorPuesto, tamanioEquipo);
			int valorEquipoActual = Auxiliares.calcularValorEquipo(actual);
			if (esEquipoBienFormado && valorEquipoActual > mayorValor) {
				mayor = clonar(actual);
				mayorValor = valorEquipoActual;
				System.out.println("entra a la condicion");
			}
			generados++;
		} else {
			actual.add(integrantes.get(indice));
			generarDesde(indice + 1);

			actual.remove(integrantes.get(indice));
			generarDesde(indice + 1);
		}
	}

	private List<Integrante> clonar(List<Integrante> integrantes) {
		List<Integrante> clonIntegrantes = new ArrayList<Integrante>();
		for (Integrante integrante : integrantes) {
			clonIntegrantes.add(integrante);
		}
		
		return clonIntegrantes;
	}
	
	public List<Integrante> getMayor() {
		return mayor;
	}

	public int getGenerated() {
		return generados;
	}

}
