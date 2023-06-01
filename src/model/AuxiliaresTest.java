package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class AuxiliaresTest {

	@Test
	public void esValorEquipo() {
		Auxiliares auxiliares = new Auxiliares();
		List<Integrante> equipo = new ArrayList<Integrante>();
		
		Integrante integrante1 = new Integrante(3, null, null);
		Integrante integrante2 = new Integrante(3, null, null);
		Integrante integrante3 = new Integrante(3, null, null);
		
		equipo.add(integrante1);
		equipo.add(integrante2);
		equipo.add(integrante3);
		
		assertEquals(9, auxiliares.calcularValorEquipo(equipo));
	}
	
	@Test
	public void estaBienFormadoElEquipo() {
		Auxiliares auxiliares = new Auxiliares();
		
		List<Integrante> equipo = new ArrayList<Integrante>();
		Map<String, Integer> cantidadPorPuesto = new HashMap<>();
		
		Integrante integrante1 = new Integrante(3, null, "PM");
		Integrante integrante2 = new Integrante(2, null, "Lider");
		Integrante integrante3 = new Integrante(1, null, "Developer");
		Integrante integrante4 = new Integrante(5, null, "Tester");
		
		integrante1.addRelacion(integrante2, true);
		integrante1.addRelacion(integrante3, true);
		integrante1.addRelacion(integrante4, true);
		
		integrante2.addRelacion(integrante1, true);
		integrante2.addRelacion(integrante3, true);
		integrante2.addRelacion(integrante4, true);
		
		integrante3.addRelacion(integrante1, true);
		integrante3.addRelacion(integrante2, true);
		integrante3.addRelacion(integrante4, true);
		
		integrante4.addRelacion(integrante1, true);
		integrante4.addRelacion(integrante2, true);
		integrante4.addRelacion(integrante3, true);
		
		equipo.add(integrante1);
		equipo.add(integrante2);
		equipo.add(integrante3);
		equipo.add(integrante4);
		
		
        cantidadPorPuesto.put("PM", 1);
        cantidadPorPuesto.put("Developer", 1);
        cantidadPorPuesto.put("Tester", 1);
        cantidadPorPuesto.put("Lider", 1);
        
        
        assertTrue(auxiliares.esEquipoBienFormado(equipo, cantidadPorPuesto, 4));
	}

}
