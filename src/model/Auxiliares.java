package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Integrante.Rol;

public class Auxiliares {

	public static boolean esEquipoBienFormado(List<Integrante> integrantes, Map<Rol, Integer> cantidadPorPuesto, int tamanioEquipo) {
		if (integrantes.size() != tamanioEquipo) {
			return false;
		}
		
		HashMap<Rol, Integer> cantidadActualPorPuesto = new HashMap<Rol, Integer>();
		
		cantidadPorPuesto.keySet().forEach((puesto) -> cantidadActualPorPuesto.put(puesto, 0));
		for (Integrante integrante : integrantes) {
			int cantidadRolDeActual = cantidadActualPorPuesto.get(integrante.getRol());
			cantidadActualPorPuesto.put(integrante.getRol(), cantidadRolDeActual + 1);
		}
		
		boolean tieneCantidadesCorrectas = cantidadActualPorPuesto
				.keySet()
				.stream()
				.allMatch((puesto) -> cantidadActualPorPuesto.get(puesto) == cantidadPorPuesto.get(puesto));
			
		if (!tieneCantidadesCorrectas) {
			return false;
		}
		
		boolean todosCompatibles = true;
		
		for (int i = 0; i < integrantes.size(); i++) {
			for (int j = 0; j < integrantes.size(); j++) {
				if (i != j) {
					todosCompatibles = todosCompatibles && integrantes.get(i).getRelaciones().get(integrantes.get(j));
				}
			}
		}
		
		return todosCompatibles;
	}

	public static int calcularValorEquipo(List<Integrante> equipo) {
		return equipo.stream().mapToInt(Integrante::getValor).sum();
	}
	
	public static Set<Set<Integrante>> parseRelaciones(List<Integrante> integrantes) {
		Set<Set<Integrante>> relacionesMalas = new HashSet<Set<Integrante>>();
		
		for (Integrante integrante: integrantes) {
			integrante.getRelaciones()
					.entrySet()
					.forEach(entry -> {
						if (!entry.getValue()) {
							Set<Integrante> integrantesMalLlevados = new HashSet<Integrante>();
							integrantesMalLlevados.add(integrante);
							integrantesMalLlevados.add(entry.getKey());
														
							relacionesMalas.add(integrantesMalLlevados);	
						}
					});
		}
		
		return relacionesMalas;
	}
}