package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Model {
	private List<Integrante> integrantes;
	private Map<String, Integer> cantidadPorPuesto;
	private boolean seRelacionoIntegrantes;

	public List<Integrante> crearIntegrantes(int cantidad) throws Exception {
		if (integrantes != null && !integrantes.isEmpty())
			throw new Exception("Ya se crearon todos los empleados");
		
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
		validarExistenciaIntegrantes("No hay integrantes sobre los que realizar relaciones");
		
		if (seRelacionoIntegrantes) {
			throw new Exception("Los integrantes ya fueron relacionados");
		}
		
		Random random = new Random();
		int size = integrantes.size();
		
		System.out.println(integrantes.size());

		for (int i = 0; i < size; i++) {
			Integrante integranteEje = integrantes.get(i);

			for (int j = 0; j < size; j++) {
				if (i != j) {
					Integrante integranteObjetivo = integrantes.get(j);
					boolean seLlevanBien = j % 25 == 0 ? random.nextBoolean(): true;
					integranteEje.addRelacion(integranteObjetivo, seLlevanBien);
				}
			}
		}
		
		this.seRelacionoIntegrantes = true;
	}
	
	public List<Integrante> resolverProblema() throws Exception {
		validarExistenciaIntegrantes("Cargue los integrantes para calcular el equipo ideal");
		
		if (cantidadPorPuesto == null || cantidadPorPuesto.isEmpty()) {
			throw new Exception("Ingrese la forma de su equipo para calcular el equipo ideal");
		}
		
		if (!seRelacionoIntegrantes){
			throw new Exception("Genere relaciones entre sus empleadas para calcular el equipo ideal");
		}

		int tamanioEquipo = cantidadPorPuesto.values().stream().mapToInt(Integer::intValue).sum();
		Solver solver = new Solver(integrantes, tamanioEquipo, cantidadPorPuesto);
		solver.resolver();

		return solver.getMayor();
	}
	
	private void validarExistenciaIntegrantes(String mensaje) throws Exception {
		if (integrantes == null || integrantes.isEmpty()) {
			throw new Exception(mensaje);
		}
	}
	
    protected Map<String, Integer> getCantidadPorPuesto() {
        return cantidadPorPuesto;
    }
    
    protected void setCantidadPorPuesto(Map<String, Integer> cantidadPorPuesto) {
        this.cantidadPorPuesto = cantidadPorPuesto;
    }
    
	public List<Integrante> getIntegrantes(){
		return integrantes;
	}

}