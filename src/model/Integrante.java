package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Integrante {
	
	private int valor;
	private String nombre;
	private Rol rol;
	private Map<Integrante, Boolean> relaciones;
	
	public static enum Rol {
		Developer,
		PM,
		Tester,
		Lider
	}
	
	
	public Integrante(int valor, String nombre, Rol rol) {
		this.valor = valor;
		this.nombre = nombre;
		this.rol = rol;
		this.relaciones = new HashMap<>();
	}
	
	public void asignarValor() {
		Random valorRandom = new Random();
		this.valor = valorRandom.nextInt(5) + 1;		
	}
	
	public void asignarRol() {
		Random rolRandom = new Random();
		int i = rolRandom.nextInt(Rol.values().length);
		
		this.rol = Rol.values()[i];
	}
	
	public void getLineaRandomDelArchivo() throws IOException {
	    ArrayList<String> lineaDelArchivo = new ArrayList<>();

	    String srcPath = System.getProperty("user.dir") + "/src";
	    String archivoPath = srcPath + "/" + "first-names.txt";

	    BufferedReader lector = new BufferedReader(new FileReader(archivoPath));
	    String line;
	    while ((line = lector.readLine()) != null) {
	        lineaDelArchivo.add(line);
	    }
	    lector.close();
	    
	    Random random = new Random();
	    random.nextBoolean();
	    int indiceRandom = random.nextInt(lineaDelArchivo.size());
	    String lineaRandom = lineaDelArchivo.get(indiceRandom);

	    this.nombre = lineaRandom;
	}
	
    public void addRelacion(Integrante integrante, boolean seLlevanBien) {
        relaciones.put(integrante, seLlevanBien);
    }
    
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Integrante integrante = (Integrante) o;
        return Objects.equals(nombre, integrante.nombre) && valor == integrante.valor;
    }
    
    public int getValor() {
        return valor;
    }

    public Rol getRol() {
        return rol;
    }
    
    public String getNombre() {
    	return nombre;
    }
    
    public Map<Integrante, Boolean> getRelaciones() {
    	return relaciones;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nombre, valor);
    }
    
    @Override
    public String toString() {
        StringBuilder printFinal = new StringBuilder();
        printFinal.append("Integrante ").append(nombre).append(": ")
                .append("Valor: ").append(valor)
                .append(", Rol: ").append(rol)
                .append(", Relaciones: ");

        int numRelaciones = relaciones.size();
        int count = 0;

        for (Map.Entry<Integrante, Boolean> entry : relaciones.entrySet()) {
            Integrante otherIntegrante = entry.getKey();
            boolean seLlevanBien = entry.getValue();
            printFinal.append(otherIntegrante.getNombre()).append(": ").append(seLlevanBien ? "se llevan bien" : "se llevan mal");
            count++;

            if (count < numRelaciones) {
            	printFinal.append(", ");
            }
        }
        return printFinal.toString();
    }
}
