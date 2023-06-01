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
	
	public Model() {
		this.cantidadPorPuesto = new HashMap<String, Integer>();
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
		if (integrantes.size() == 0) {
			throw new Exception("No hay integrantes sobre los que realizar relaciones");
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
	}
	
	public void resolverProblema() {
		int tamanioEquipo = cantidadPorPuesto.values().stream().mapToInt(Integer::intValue).sum();
		Solver solver = new Solver(integrantes, tamanioEquipo, cantidadPorPuesto);
		solver.resolver();
		
		System.out.println(solver.getGenerated());
		Integrante.printIntegrantes(solver.getMayor());
	}
	
    public Map<String, Integer> getCantidadPorPuesto() {
        return cantidadPorPuesto;
    }
    
    public void setCantidadPorPuesto(Map<String, Integer> cantidadPorPuesto) {
        this.cantidadPorPuesto = cantidadPorPuesto;
    }

}