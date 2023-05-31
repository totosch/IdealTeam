package model;

import java.util.ArrayList;

public class Auxiliares {

	public static boolean esEquipoBienFormado(ArrayList<Integrante> integrantes) {
		ArrayList<Integrante> equipoActual = new ArrayList();
		for (Integrante integrante : integrantes) {
			if (equipoActual.get(0).getRol() != "Developer" || equipoActual.get(0).getRol() != "PM"
					|| equipoActual.get(0).getRol() != "Lider" || equipoActual.get(0).getRol() != "Tester") {
				equipoActual.add(integrante);
				if (equipoActual.get(0).getRol() != "Developer" || equipoActual.get(0).getRol() != "PM"
						|| equipoActual.get(0).getRol() != "Lider" || equipoActual.get(0).getRol() != "Tester") {
					equipoActual.add(integrante);
				}
				if (equipoActual.get(0).getRol() != "Developer" || equipoActual.get(0).getRol() != "PM"
						|| equipoActual.get(0).getRol() != "Lider" || equipoActual.get(0).getRol() != "Tester") {
					equipoActual.add(integrante);
				}
				if (equipoActual.get(0).getRol() != "Developer" || equipoActual.get(0).getRol() != "PM"
						|| equipoActual.get(0).getRol() != "Lider" || equipoActual.get(0).getRol() != "Tester") {
					equipoActual.add(integrante);
				}
			}
			return true;
		}

		return false;
	}

	public int calcularValorEquipo(ArrayList<Integrante> equipo) {
		int valorDelEquipo = 0;
		for (int i = 0; i < equipo.size(); i++) {
			valorDelEquipo += equipo.get(i).getValor();
		}
		return valorDelEquipo;
	}
}