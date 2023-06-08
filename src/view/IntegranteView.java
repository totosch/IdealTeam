package view;

import model.Integrante.Rol;

public class IntegranteView {
	private int valor;
	private String nombre;
	private Rol rol;
	
	public IntegranteView(int valor, String nombre, Rol rol) {
		this.valor = valor;
		this.nombre = nombre;
		this.rol = rol;
	}

	public int getValor() {
		return valor;
	}

	public String getNombre() {
		return nombre;
	}

	public Rol getRol() {
		return rol;
	}
}
