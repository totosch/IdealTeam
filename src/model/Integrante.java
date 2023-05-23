package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Integrante {
	
	private int valor;
	private String nombre;
	private String rol;
	
	public Integrante(int valor, String nombre, String rol) {
		this.valor = valor;
		this.nombre = nombre;
		this.rol = rol;
	}
	
	public void asignarValor() {
		Random valorRandom = new Random();
		this.valor = valorRandom.nextInt(5) + 1;		
	}
	
	public void asignarRol() {
		Random rolRandom = new Random();
		String[] roles = { "Developer", "PM", "Tester", "Lider", "Arquitecto" };
		int i = rolRandom.nextInt(roles.length);
		this.rol = roles[i];
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
	    int indiceRandom = random.nextInt(lineaDelArchivo.size());
	    String lineaRandom = lineaDelArchivo.get(indiceRandom);

	    this.nombre = lineaRandom;
	}

	
    public int getValor() {
        return valor;
    }

    public String getRol() {
        return rol;
    }
    
    public String getNombre() {
    	return nombre;
    }
}
