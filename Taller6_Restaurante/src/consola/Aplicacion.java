package consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

import modelo.Combo;
import modelo.Ingrediente;
import modelo.Pedido;
import modelo.Producto;
import modelo.ProductoAjustado;
import modelo.ProductoMenu;
import modelo.Restaurante;
import test.IngredienteRepetidoException;
import test.ProductoRepetidoException;

public class Aplicacion {
	
	private Restaurante restaurante;
	
	private void ejecutarCargarInfo() throws IOException, ProductoRepetidoException, IngredienteRepetidoException {
		File ingredientes= new File("/Users/catalinavillacris/Desktop/DPOO_wks/Taller2_Restaurante/data/ingredientes.txt");
		File menu=new File("/Users/catalinavillacris/Desktop/DPOO_wks/Taller2_Restaurante/data/menu.txt");
		File combos=new File ("/Users/catalinavillacris/Desktop/DPOO_wks/Taller2_Restaurante/data/combos.txt");
		File bebidas=new File ("/Users/catalinavillacris/Desktop/DPOO_wks/Taller2_Restaurante/data/bebidas.txt");
		
		
		restaurante.cargarInformacionRestaurante(ingredientes,combos,menu,bebidas);		
	}
	
	private void ejecutarMenu() {
		ArrayList<Producto> menu = restaurante.getMenuBase();
		for(Producto item:menu) {
			System.out.println(item.generarTextoFactura());
		}
	}
	
	private void ejecutarMenuCombos() {
		ArrayList<Combo> menu = restaurante.getMenuCombos();
		for(Combo item:menu) {
			System.out.println(item.generarTextoFactura());
		}
	}
	
	private void ejecutarHacerPedido() throws Exception {
		String nombre = input("Ingrese su nombre: ");
		String direccion = input("Ingrese su direccion: ");
		
		restaurante.iniciarPedido(nombre,direccion);
		
		System.out.println("\nEscoga el menu que desea \n");
		System.out.println("1 Menu Base			2 Menu Combos");
		
		String seleccion=input("Ingrese su seleccion" );
		
		if ("1".equals(seleccion)) {
			System.out.println("\nEscoga que desea pedir \n");
			ArrayList<Producto> menu = restaurante.getMenuBase();
			
			
			for(int i=1;i<= menu.size();i++) {
				System.out.println(String.valueOf(i)+menu.get(i).toString());
			}
			
			int index= Integer.parseInt(input("Ingrese el número del producto"));
			System.out.println("\n¿Desea añadir algun ingrediente? \n");
			String add= input("Si		No");
			
			System.out.println("\n¿Desea quitar algun ingrediente? \n");
			String sus= input("Si		No");
			ArrayList<Ingrediente> ingrediente= restaurante.getIngredientes();
			
			if ("Si".equals(add) && "Si".equals(sus)) {
				
				
				for(int i=1;i<=ingrediente.size();i++) {
					System.out.println(String.valueOf(i)+ingrediente.get(i).toString());
				}
			String[] añadidos= input("Por favor ingrese el numero. En caso de ser más de una adicion separe los numeros con - sin dejar espacios").split("-");
			ArrayList<Ingrediente> añadir=new ArrayList<Ingrediente>();
			for(String x:añadidos) {
				añadir.add(ingrediente.get(Integer.parseInt(x)-1));
			}
			
			for(int i=0; i<= ingrediente.size(); i=i+1) {
				Ingrediente item=ingrediente.get(i);
				System.out.println(String.valueOf(i+1)+item.getNombre());
			}
			String[] eliminados= input("Por favor ingrese el numero. En caso de ser más de una restricción separe los numeros con - sin dejar espacios").split("-");
			ArrayList<Ingrediente> eliminar=new ArrayList<Ingrediente>();
			for(String j:eliminados) {
				eliminar.add(ingrediente.get(Integer.parseInt(j)-1));
			}		
			
			Producto pedido= new ProductoAjustado(menu.get(index-1),añadir,eliminar);
			if(pedido.getPrecio()<150) {
				restaurante.getPedidoEnCurso().agregarProducto(pedido);
			}else {
				throw new Exception("Su pedido excede los $150.000");
			}
			
			
			}else if("Si".equals(add) && "No".equals(sus)) {
				for(int i=0; i<= ingrediente.size(); i=i+1) {
					Ingrediente item=ingrediente.get(i);
					System.out.println(String.valueOf(i+1)+item.toString());
			}
			String[] añadidos= input("Por favor ingrese el numero. En caso de ser más de una adicion separe los numeros con - sin dejar espacios").split("-");
			ArrayList<Ingrediente> añadir=new ArrayList<Ingrediente>();
			for(String j:añadidos) {
				añadir.add(ingrediente.get(Integer.parseInt(j)-1));
			}
			Producto pedido= new ProductoAjustado(menu.get(index-1),añadir,null);
			if(pedido.getPrecio()<150) {
				restaurante.getPedidoEnCurso().agregarProducto(pedido);
			}else {
				throw new Exception("Su pedido excede los $150.000");
			}
			
			}else if("No".equals(add) && "Si".equals(sus)){
				for(int i=0; i<= ingrediente.size(); i=i+1) {
					Ingrediente item=ingrediente.get(i);
					System.out.println(String.valueOf(i+1)+item.getNombre());
				}
				String[] eliminados= input("Por favor ingrese el numero. En caso de ser más de una restricción separe los numeros con - sin dejar espacios").split("-");
				ArrayList<Ingrediente> eliminar=new ArrayList<Ingrediente>();
				for(String j:eliminados) {
					eliminar.add(ingrediente.get(Integer.parseInt(j)-1));
			}
				Producto pedido= new ProductoAjustado(menu.get(index-1),null,eliminar);
				if(pedido.getPrecio()<150) {
					restaurante.getPedidoEnCurso().agregarProducto(pedido);
				}else {
					throw new Exception("Su pedido excede los $150.000");
				}
			
		}else if("No".equals(add) && "No".equals(sus)) {
			Producto pedido=new ProductoMenu(menu.get(index-1).getNombre(),menu.get(index-1).getPrecio(),menu.get(index-1).getCalorias());
			if(pedido.getPrecio()<150) {
				restaurante.getPedidoEnCurso().agregarProducto(pedido);
			}else {
				throw new Exception("Su pedido excede los $150.000");
			}
		}else {
			System.out.println("la Seleccion no existe");
		}
	}else if ("2".equals(seleccion)) {
		System.out.println("\nEscoga que desea pedir \n");
		ArrayList<Combo> combos = restaurante.getMenuCombos();
		for(int i=0; i<= combos.size(); i=i+1) {
			Producto item=combos.get(i);
			System.out.println(String.valueOf(i+1)+item.getNombre());}
		int index= Integer.parseInt(input("Ingrese el número del producto"));
		if(combos.get(index-1).getPrecio()<150) {
			restaurante.getPedidoEnCurso().agregarProducto(combos.get(index-1));
		}else {
			throw new Exception("Su pedido excede los $150.000");
		}
		
		
	}else {
		System.out.println("\nLa seleccion no existe ");
	}
		
		System.out.println("\nSu pedido ha sido agregado ");
		System.out.println(restaurante.getPedidoEnCurso().toString());
	}
	

	
	private void ejecutarTerminarPedido() {
		String idCliente=input("Ingrese el id de su pedido");
		Pedido curso=restaurante.getPedidoEnCurso();
		
		if(idCliente.equals(String.valueOf(curso.getIdPedido()))) {
			File facturas=new File("/Users/catalinavillacris/Desktop/DPOO_wks/Taller2_Restaurante/data/facturas.txt");
			boolean equal=restaurante.cerrarYGuardarPedido(facturas);
			String verEqual= input("¿Desea consultar si existe un pedido igual?");
				if (verEqual.equals("Si")) {
					if (equal) {
						System.out.println("\nSe encontro al menos un pedido identico ");
					}else {
						System.out.println("\nNo se encontraron pedido similares ");
					}
				}
		}else {
			System.out.println("\nNo se enconto este pedido en curso");
		}
		
	}
	
	public void mostrarMenu () {
		
		System.out.println("\n¡Bienvenido! Porfavor seleccione una opcion  P\n");
		System.out.println("1. Ver menu Productos");
		System.out.println("2. Ver menu Combos");
		System.out.println("3. Hacer pedido");
		System.out.println("4. Terminar pedido");
		System.out.println("5. Salir de la aplicacion\n");
	}
	
	public void ejecutarAplicacion() throws IOException
	{
		System.out.println("RustiCabra\n");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				try {
					ejecutarCargarInfo();
				} catch (ProductoRepetidoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IngredienteRepetidoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					ejecutarMenu();
				else if (opcion_seleccionada == 2 && restaurante != null)
					ejecutarMenuCombos();
				else if (opcion_seleccionada == 3 && restaurante != null)
					try {
						ejecutarHacerPedido();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else if (opcion_seleccionada == 4 && restaurante != null)
					ejecutarTerminarPedido();
				else if (opcion_seleccionada == 5)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else if (restaurante == null)
				{
					System.out.println("Parece que no see ha cargado la informacion");
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	public Aplicacion() {
		this.restaurante=new Restaurante();
	}



	public static void main(String[] args) throws IOException {
		Aplicacion app= new Aplicacion();
		app.ejecutarAplicacion();

	}

}
