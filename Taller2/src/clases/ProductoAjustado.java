package clases;

public class ProductoAjustado implements Producto {
	
	private int Precio;
	private String Nombre;
	private Ingrediente[] Agregados;
	private Ingrediente[] Eliminados;
	
	public ProductoAjustado(ProductoMenu base,Ingrediente[] agregados,Ingrediente[] eliminados) {
		
		this.Nombre=base.getNombre();
		this.Precio=base.getPrecio();
		if(agregados.length!=0) {
			this.Agregados=agregados;
		}
		if(eliminados.length!=0) {
			this.Eliminados=eliminados;
		}
	}

	@Override
	public int getPrecio() {
		int precio=this.Precio;
		if (this.Agregados.length!=0) {
			for (Ingrediente agregado:Agregados) {
				precio += agregado.getCostoAdicional();
			}
		}
		return precio;
	}

	@Override
	public String getNombre() {
		String nombre =this.Nombre;
		
		if (this.Agregados.length!=0) {
			nombre += "con adición de: ";
			for (Ingrediente agregado:Agregados) {
				nombre += agregado.getNombre();
				nombre += " ";
			}
		}
		if (this.Eliminados.length!=0) {
			nombre += "sin: ";
			for (Ingrediente agregado:Agregados) {
				nombre += agregado.getNombre();
				nombre += " ";
			}
		}
		return nombre;
	}

	@Override
	public String generarTextoFactura() {
		String factura = null;
		
		String valorBase= String.valueOf(Precio);
		factura += this.Nombre;
		factura += " ";
		factura += valorBase;
		
		if (this.Agregados.length!=0) {
			for (Ingrediente agregado:Agregados) {
				factura += "\n";
				factura += agregado.getNombre();
				factura += " ";
				factura += String.valueOf(agregado.getCostoAdicional());
			}
		}
		String totalNeto= String.valueOf(getPrecio());
		factura += "\n";
		factura += totalNeto;
		
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
