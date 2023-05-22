package clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Pedido {
	
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;
	private Boolean enCurso;

	public Pedido(String nombre, String direccion) {
		// TODO Auto-generated constructor stub
		this.nombreCliente=nombre;
		this.direccionCliente=direccion;
		this.enCurso=true;
		idPedido ++;	
	}
	
	public int getIdPedido() {
		return idPedido;
	}
	
	public void agregarProducto (Producto nuevoItem) {
		this.itemsPedido.add(nuevoItem);
	}
	
	public void pedidoTerminado() {
		this.enCurso=false;
	}
	
	public Boolean getEnCurso() {
		return this.enCurso;
	}
	
	private int getPrecioNetoPedido() {
		int neto=0;
		
		for (Producto item: itemsPedido) {
			neto += item.getPrecio();
		}

		return neto;
	}
	
	private double getPrecioIva() {
		int neto= getPrecioNetoPedido();
		double iva= neto*(0.19);
		
		return iva;
	}
	
	private double getPrecioTotal() {
		int neto= getPrecioNetoPedido();
		double iva= getPrecioIva();
		
		return neto + iva;
	}
	
	private String generarTextoFactura() {
		
		int neto= getPrecioNetoPedido();
		double iva= getPrecioIva();
		double total= getPrecioTotal();
		
		String factura = " Factura \n Productos \n ";
		
		for (Producto p : itemsPedido) {
			factura+=p.getNombre();
			factura+=" ";
			factura+=String.valueOf(p.getPrecio());
			factura+="\n";
		}
		
		factura+= "TOTAL NETO";
		factura+= " ";
		factura += String.valueOf(neto);
		factura+="\n";
		
		factura+= "IVA";
		factura+= " ";
		factura += String.valueOf(iva);
		factura+="\n";
		
		factura+= "TOTAL";
		factura+= " ";
		factura += String.valueOf(total);
			
		
		return factura;
	}
	
	public void guardarFactura(File archivo) {
		try {
			FileWriter writer = new FileWriter(archivo);
            BufferedWriter buff= new BufferedWriter(writer);
            
            buff.write(generarTextoFactura());
            buff.newLine();
            buff.close();
            
		}catch (Exception e){
            System.out.println("No se pudo guardar la factura");
        }
		
		
	}
	
	

}
