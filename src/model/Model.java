package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Model {

	public static ArrayList createIntegrantes(int numberOfIntegrantes) {
		ArrayList<Integrante> integrantes = new<Integrante> ArrayList();

		for (int i = 0; i < numberOfIntegrantes; i++) {
			Integrante integrante = new Integrante(0, "", "");

			integrante.asignarValor();
			integrante.asignarRol();

			try {
				integrante.getLineaRandomDelArchivo();
			} catch (IOException e) {
				e.printStackTrace();
			}

			integrantes.add(integrante);
		}
		return integrantes;
	}

	public static void establecerRelaciones(ArrayList<Integrante> integrantes) {
		Random random = new Random();
		int size = integrantes.size();

		for (int i = 0; i < size; i++) {
			Integrante integranteEje = integrantes.get(i);

			for (int j = 0; j < size; j++) {
				if (i != j) {
					Integrante integranteObjetivo = integrantes.get(j);
					boolean seLlevanBien = random.nextBoolean();
					integranteEje.addRelacion(integranteObjetivo, seLlevanBien);
				}
			}
		}
	}

}