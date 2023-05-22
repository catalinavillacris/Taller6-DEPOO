package clases;
import java.util.ArrayList;

public class Combo implements Producto{
	
	private double descuento;
	private String nombreCombo;
	private ArrayList<Producto> items;

	public Combo(String nombre, double descuento) {
		this.descuento= descuento;
		this.nombreCombo=nombre;
	}
	
	public void agregarItemACombo(Producto itemCombo) {
		this.items.add(itemCombo);
	}
	public ArrayList<Producto> getItems(){
		return this.items;
	}
	
	@Override
	public int getPrecio() {
		int total =0;
		
		for (Producto item:items) {
			total+=item.getPrecio();
		}
		
		total *= descuento;
		
		return total;
	}
	@Override
	public String getNombre() {
		return this.nombreCombo;
	}
	
	@Override
	public String generarTextoFactura() {
		String factura =null;
		
		factura += getNombre();
		factura += " ";
		factura += String.valueOf(getPrecio());
		factura += "\n";
		
		factura += "IVA";
		factura += " ";
		factura += String.valueOf(getPrecio()*0.19);
		factura += "\n";
		
		factura += "TOTAL";
		factura += " ";
		factura += String.valueOf(getPrecio()+getPrecio()*0.19);
		factura += "\n";
		
		return factura;
	}
	
}
