package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


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
		return integrantes;
	}
	
    public void makeRelationships(ArrayList<Integrante> integrantes) {
        Random random = new Random();
        int size = integrantes.size();

        for (int i = 0; i < size; i++) {
            Integrante integrante1 = integrantes.get(i);

            for (int j = 0; j < size; j++) {
                if (i != j) {
                    Integrante integrante2 = integrantes.get(j);
                    boolean seLlevanBien = random.nextBoolean();
                    integrante1.addRelacion(integrante2, seLlevanBien);
                }
            }
        }
    }

}