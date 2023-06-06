package view;

public class IntegranteView {
	private int valor;
	private String nombre;
	private String rol;
	
	public IntegranteView(int valor, String nombre, String rol) {
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

	public String getRol() {
		return rol;
	}
}
