package model;

import java.io.IOException;

public class Model {
	
	 public void createIntegrante() {
	        Integrante integrante = new Integrante(0, "", "");
	       
	        integrante.asignarValor();
	        integrante.asignarRol();
	        
	        try {
	            integrante.getLineaRandomDelArchivo();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        System.out.println(integrante.getValor());
	        System.out.println(integrante.getRol());
	        System.out.println(integrante.getNombre());
	    }
	
}
