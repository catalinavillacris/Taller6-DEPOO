package clases;

public class ProductoMenu implements Producto {
	
	private String nombre;
	private int precioBase;

	public ProductoMenu(String nombre, int precio) {
		this.nombre=nombre;
		this.precioBase=precio;
	}

	@Override
	public int getPrecio() {
		return this.precioBase;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String generarTextoFactura() {
		String factura = null;
		
		String valorBase= String.valueOf(precioBase);
		factura += this.nombre;
		factura += " ";
		factura += valorBase;
		
		
		factura += "\n";
		factura += "IVA";
		factura += " ";
		factura += String.valueOf(getPrecio()*0.19);
		
		double total =getPrecio() +(getPrecio()*0.19);
		factura += "\n";
		factura += "TOTAL";
		factura += " ";
		factura += String.valueOf(total);
		
		return factura;
	}

}
