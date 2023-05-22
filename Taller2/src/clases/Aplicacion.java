package clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Aplicacion {
	
	private Restaurante restaurante;

	public Aplicacion() {
		// TODO Auto-generated constructor stub
	}
	
	public void ejecutarAplicacion() throws FileNotFoundException, IOException
	{

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					ejecutarCargarInformacion();
				else if (opcion_seleccionada == 2 && restaurante != null)
					ejecutarMenu();
				else if (opcion_seleccionada == 3 && restaurante != null)
					ejecutarNuevoPedido();
				else if (opcion_seleccionada == 4 && restaurante != null)
					ejecutarAgregarElementoPedido();
				else if (opcion_seleccionada == 5 && restaurante != null)
					ejecutarConsultarPedido();
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else if (restaurante == null)
				{
					System.out.println("Para poder ejecutar esta opción primero debe cargar el restaurante");
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

	private String input(String mensaje) {
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

	public void mostrarMenu()
	{
		System.out.println("1. Cargar informacion restaurante");
		System.out.println("2. Consultar menu restaurante");
		System.out.println("3. Iniciar nuevo pedido");
		System.out.println("4. Agregar elemento a pedido");
		System.out.println("5. Consultar pedido");
		System.out.println("6. Salir de la aplicación\n");
		
	}
	
	private void ejecutarCargarInformacion() throws FileNotFoundException, IOException {
		this.restaurante=new Restaurante();
		this.restaurante.cargarInformacionRestaurante();
	}

	private void ejecutarMenu() {
		int opcion_seleccionada = Integer.parseInt(input("Seleccione el menu que quiere consultar"));
		System.out.println("1. Menu productos");
		System.out.println("2. Menu combos");
		if (opcion_seleccionada == 1) {
			for(Producto i:restaurante.getMenuBase()) {
				System.out.println(i.getNombre());
				System.out.println("\n");
			}
		}
			
		else if (opcion_seleccionada == 2) {
			for (Combo i:restaurante.getCombos()) {
				System.out.println(i.getNombre());
				System.out.println("Items");
				for (Producto p:i.getItems()) {
					System.out.println(p.getNombre());
				}
				System.out.println("\n");
			}
		}
			
	}
	
	private void ejecutarNuevoPedido() {
		String nombre= input("Ingrese su nombre");
		String direccion= input("Ingrese su direccion");
		this.restaurante.iniciarPedido(nombre, direccion);
	}
	private void ejecutarAgregarElementoPedido() {
		int opcion_seleccionada = Integer.parseInt(input("Seleccione el menu del que quiere pedir"));
		System.out.println("1. Menu productos");
		System.out.println("2. Menu combos");
		
		if (opcion_seleccionada == 1) {
			String seleccion=input("Ingrese el nombre del producto");
			Producto producto=restaurante.isInMenu(seleccion);
			restaurante.agregarProductoPedido(producto);
		}
		else if (opcion_seleccionada == 2) {
			String seleccion=input("Ingrese el nombre del combo");
			Combo combo=restaurante.isInCombos(seleccion);
			restaurante.agregarProductoPedido(combo);
		}
	}
	private void ejecutarConsultarPedido() {
		int id = Integer.parseInt(input("El id del pedido"));
		Pedido pedido =restaurante.buscarPedido(id);
		System.out.print(pedido);
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Aplicacion aplicacion= new Aplicacion();
		aplicacion.ejecutarAplicacion();
	}

}
