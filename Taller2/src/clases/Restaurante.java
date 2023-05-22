package clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Restaurante {
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<Producto> menuBase;
	private ArrayList<Combo> combos;
	private ArrayList<Pedido> pedidos;
	private Pedido pedidoCurso;

	public Restaurante() {
		
	}
	public void iniciarPedido(String nombre, String direccion) {
		Pedido pedido = new Pedido(nombre,direccion);
		this.pedidoCurso=pedido;
		this.pedidos.add(pedidoCurso);
	}
	public void agregarProductoPedido(Producto producto) {
		this.pedidoCurso.agregarProducto(producto);
	}
	
	public void cerrarYguardarPedido() {
		this.pedidoCurso.pedidoTerminado();		
		
	}
	
	public void getPedidoEnCurso () {
		if (this.pedidoCurso.getEnCurso()) {
			System.out.println(String.valueOf(pedidoCurso.getIdPedido()));
		}else {
			System.out.println("No se encontro pedido en curso");
		}
		
	}
	
	public Pedido buscarPedido (int id) {
		int index=0;
		for (Pedido pedido:this.pedidos) {
			if (pedido.getIdPedido()==id) {
				index=this.pedidos.indexOf(pedido);
			}
		}
		return this.pedidos.get(index);
	} 
	
	public ArrayList<Producto> getMenuBase() {
		return this.menuBase;
	}
	
	public Producto isInMenu(String nombre) {
		ArrayList<Producto> menu = getMenuBase();
		int index=0;
		
		for (Producto item:menu) {
			if (item.getNombre()==nombre) {
				index=menu.indexOf(item);
			}
		}
		return menu.get(index);
	}
	
	public Combo isInCombos(String nombre) {
		int index=0;
		
		for (Combo item:this.getCombos()) {
			if (item.getNombre()==nombre) {
				index=this.getCombos().indexOf(item);
			}
		}
		return this.getCombos().get(index);
	}
	
	public ArrayList<Ingrediente> getIngredientes () {
		return this.ingredientes;
	}
	
	public ArrayList<Combo> getCombos(){
		return this.combos;
	}
	
	public void cargarInformacionRestaurante() throws IOException, FileNotFoundException{
		cargarIngredientes();
		cargarMenu ();
		cargarCombos ();
	}
	
	private void cargarIngredientes() throws IOException, FileNotFoundException {
		
		FileReader archivoIngredientes = new FileReader ("/Users/catalinavillacris/Desktop/DPOO/Taller2/Taller 2 - Hamburguesas_esqueleto/data/ingredientes.txt");
		BufferedReader br= new BufferedReader (archivoIngredientes);
		try {
		String line= br.readLine();
		line=br.readLine();
		
		while (line != null) {
			String[] ing =line.split(";");
			
			String nombre= ing[0];
			int costo= Integer.parseInt(ing[1]);
			
			Ingrediente ingrediente = new Ingrediente (nombre,costo);
			this.ingredientes.add(ingrediente);
			line= br.readLine();	
		}
		br.close();
		
		} 
		
		catch (FileNotFoundException e) {
			System.out.println("No se encontró el archivo");
		}
	
	}
	private void cargarMenu () throws IOException, FileNotFoundException  {
		
		
		FileReader archivoMenu= new FileReader ("/Users/catalinavillacris/Desktop/DPOO/Taller2/Taller 2 - Hamburguesas_esqueleto/data/menu.txt");
		BufferedReader br= new BufferedReader (archivoMenu);
		try {
		String line= br.readLine();
		line=br.readLine();
		
		while (line != null) {
			String[] ing =line.split(";");
			
			String nombre= ing[0];
			int precio= Integer.parseInt(ing[1]);
			
			Producto producto = new ProductoMenu (nombre,precio);
			this.menuBase.add(producto);
			line= br.readLine();	
		}
		br.close();
		
		} 
		
		catch (FileNotFoundException e) {
			System.out.println("No se encontró el archivo");
		}
		
	}
	private void cargarCombos () throws IOException, FileNotFoundException  {
		
		
		FileReader archivoCombos= new FileReader ("/Users/catalinavillacris/Desktop/DPOO/Taller2/Taller 2 - Hamburguesas_esqueleto/data/combos.txt");
		BufferedReader br= new BufferedReader (archivoCombos);
		try {
		String line= br.readLine();
		line=br.readLine();
		
		while (line != null) {
			String[] ing =line.split(";");
			
			String nombre= ing[0];
			String[] x100= ing[1].split("%");
			double descuento= Integer.parseInt(x100[0])/100;
			
			Combo combo = new Combo (nombre,descuento);
			int i=2;
			ArrayList<String> item= new ArrayList<String>();
			while (i<=(ing.length-2)) {
				item.add(ing[i]);
			}
			
			for (String p:item) {
				Producto producto=isInMenu(p);
				combo.agregarItemACombo(producto);
			}
			this.combos.add(combo);
			line= br.readLine();	
		}
		br.close();
		
		} 
		
		catch (FileNotFoundException e) {
			System.out.println("No se encontró el archivo");
		}
				
	}
}
