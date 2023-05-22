package clases;

public class Ingrediente {
	
	private String nombre;
	private int costoAdicional;
	

	public Ingrediente(String nombre, int costoAdicional) {
		
		this.costoAdicional= costoAdicional;
		this.nombre=nombre;
	}
	public int getCostoAdicional() {
		return costoAdicional;
	}
	public String getNombre() {
		return nombre;
	}
	

}
