package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Model {
	private List<Integrante> integrantes;
	private static int cantidad = 10;
	private Map<String, Integer> cantidadPorPuesto;
	private boolean seRelacionoIntegrantes;
	
	public List<Integrante> getIntegrantes(){
		return integrantes;
	}

	public List<Integrante> crearIntegrantes() {
		integrantes = new ArrayList<Integrante>();

		for (int i = 0; i < cantidad; i++) {
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
	
	public void registrarCantidadPorPuesto(Map<String, Integer> cantidadPorPuesto) throws IllegalArgumentException {
		for (String rol : Integrante.roles) {
			if (cantidadPorPuesto.get(rol) == null) {
				throw new IllegalArgumentException("El siguiente rol ingresado no existe: " + rol);
			}
		}
		
		this.cantidadPorPuesto = cantidadPorPuesto;
	}

	public void establecerRelaciones() throws Exception {
		if (integrantes.isEmpty()) {
			throw new Exception("No hay integrantes sobre los que realizar relaciones");
		}
		
		if (seRelacionoIntegrantes) {
			throw new Exception("Los integrantes ya fueron relacionados");
		}
		
		Random random = new Random();
		int size = integrantes.size();

		for (int i = 0; i < size; i++) {
			Integrante integranteEje = integrantes.get(i);

			for (int j = 0; j < size; j++) {
				if (i != j) {
					Integrante integranteObjetivo = integrantes.get(j);
					boolean seLlevanBien = j % 3 == 0 ? random.nextBoolean(): true;
					integranteEje.addRelacion(integranteObjetivo, seLlevanBien);
				}
			}
		}
		
		this.seRelacionoIntegrantes = true;
	}
	
	public void resolverProblema() throws Exception {
		if (cantidadPorPuesto == null || cantidadPorPuesto.isEmpty()) {
			throw new Exception("Ingrese la forma de su equipo para calcular el equipo ideal");
		}
		
		if (integrantes == null || integrantes.isEmpty()) {
			throw new Exception("Cargue los integrantes para calcular el equipo ideal");
		}
		
		int tamanioEquipo = cantidadPorPuesto.values().stream().mapToInt(Integer::intValue).sum();
		Solver solver = new Solver(integrantes, tamanioEquipo, cantidadPorPuesto);
		solver.resolver();
		
		System.out.println(solver.getGenerated());
		Integrante.printIntegrantes(solver.getMayor());
	}

}