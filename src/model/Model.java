package model;

import java.io.IOException;
import java.util.ArrayList;

public class Model {

	public ArrayList<Integrante> createIntegrantes(int numberOfIntegrantes) {
		ArrayList<Integrante> integrantes = new ArrayList<Integrante>();

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
		System.out.println(integrantes);
		return integrantes;
	}

}