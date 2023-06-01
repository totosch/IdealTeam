package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ModelTest {

	@Test
	public void seCreaIntegrante() {
		Model model = new Model();
		List<Integrante> integrantes = model.crearIntegrantes();
		assertNotNull(integrantes);
		
		for(Integrante integrante : integrantes) {
			integrante.asignarValor();
			assertNotEquals(null, integrante.getValor());
		}
		
		for(Integrante integrante : integrantes) {
			integrante.asignarRol();
			assertNotEquals("", integrante.getRol());
		}
		
        for (Integrante integrante : integrantes) {
            assertNotNull(integrante.getNombre());
            assertFalse(integrante.getNombre().isEmpty());
        }
	}
	
	@Test
	public void seRegistraCantidadPorRol() {
		Model model = new Model();
		Map<String, Integer> cantidadPorPuesto = new HashMap<>();
		
        cantidadPorPuesto.put("PM", 5);
        cantidadPorPuesto.put("Developer", 4);
        cantidadPorPuesto.put("Tester", 2);
        cantidadPorPuesto.put("Lider", 3);
        
        try {
        	model.registrarCantidadPorPuesto(cantidadPorPuesto);
        } catch(IllegalArgumentException e) {
        	e.getMessage();
        }
        
        assertEquals(cantidadPorPuesto, model.getCantidadPorPuesto());
	}
	
	@Test
	public void seEstablecenRelaciones() {
		Model model = new Model();
		List<Integrante> integrantes = model.crearIntegrantes();

	}
	
	@Test
	public void seResuelveProblema() {
		Model model = new Model();
		Map<String, Integer> cantidadPorPuesto = new HashMap<>();
		
        cantidadPorPuesto.put("PM", 5);
        cantidadPorPuesto.put("Developer", 4);
        cantidadPorPuesto.put("Tester", 2);
        cantidadPorPuesto.put("Lider", 3);
		
        model.setCantidadPorPuesto(cantidadPorPuesto);

        try {
            model.resolverProblema();
        } catch (Exception e) {
            e.getMessage();
        }
	}
	

}
