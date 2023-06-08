package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import model.Integrante.Rol;

public class ModelTest {

	@Test
	public void seCreaIntegrante() throws Exception {
		Model model = new Model();
		List<Integrante> integrantes = model.crearIntegrantes(0);
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
		Map<Rol, Integer> cantidadPorPuesto = new HashMap<Rol, Integer>();
		
        cantidadPorPuesto.put(Rol.PM, 5);
        cantidadPorPuesto.put(Rol.Developer, 4);
        cantidadPorPuesto.put(Rol.Tester, 2);
        cantidadPorPuesto.put(Rol.Lider, 3);
        
        try {
        	model.registrarCantidadPorPuesto(cantidadPorPuesto);
        } catch(IllegalArgumentException e) {
        	e.getMessage();
        }
        
        assertEquals(cantidadPorPuesto, model.getCantidadPorPuesto());
	}
	
	@Test
	public void seEstablecenRelaciones() throws Exception {
		Model model = new Model();
		
		model.crearIntegrantes(4);
		model.establecerRelaciones();

		for(Integrante integrante : model.getIntegrantes()) {
			assertEquals(3, integrante.getRelaciones().size());
		}		
	}
	
	@Test
	public void establecerRelacionesModificaBoolean() throws Exception {
		Model model = new Model();
		
		model.crearIntegrantes(4);
		model.establecerRelaciones();

		assertEquals(model.getSeRelacionoIntegrantes(), true);
		
	}
	
	@Test
	public void seResuelveProblema() {
		Model model = new Model();
		Map<Rol, Integer> cantidadPorPuesto = new HashMap<Rol, Integer>();
		
        cantidadPorPuesto.put(Rol.PM, 5);
        cantidadPorPuesto.put(Rol.Developer, 4);
        cantidadPorPuesto.put(Rol.Tester, 2);
        cantidadPorPuesto.put(Rol.Lider, 3);
		
        model.setCantidadPorPuesto(cantidadPorPuesto);

        try {
            model.resolverProblema();
        } catch (Exception e) {
            e.getMessage();
        }
	}
	
	@Test(expected = Exception.class)
	public void resolverProblemaSinIntegrantes() throws Exception {
		Model model = new Model();
		
		model.resolverProblema();
	}
	
	@Test(expected = Exception.class)
	public void resolverProblemaSinCantidadPorPuesto() throws Exception {
		Model model = new Model();
		
		model.crearIntegrantes(4);
		
		model.resolverProblema();
	}
	
	@Test(expected = Exception.class)
	public void resolverProblemaSinRelaciones() throws Exception {
		Model model = new Model();
		
		model.crearIntegrantes(4);
		Map<Rol, Integer> cantidadPorPuesto = new HashMap<Rol, Integer>();
        cantidadPorPuesto.put(Rol.PM, 5);
        cantidadPorPuesto.put(Rol.Developer, 4);
        cantidadPorPuesto.put(Rol.Tester, 2);
        cantidadPorPuesto.put(Rol.Lider, 3);
		
		
		model.resolverProblema();
	}
	
	@Test(expected = Exception.class)
	public void repetirCreacionIntegrantes() throws Exception {
		Model model = new Model();
		
		model.crearIntegrantes(4);
		model.crearIntegrantes(4);
	}
	
	@Test(expected = Exception.class)
	public void repetirRelacionIntegrantes() throws Exception {
		Model model = new Model();
		
		model.crearIntegrantes(4);
		model.establecerRelaciones();
		model.establecerRelaciones();
	}
}
