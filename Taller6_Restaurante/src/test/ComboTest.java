package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Combo;
import modelo.Producto;
import modelo.ProductoMenu;

public class ComboTest {
	
	private static ArrayList<Producto> items= new ArrayList<Producto>();
	private static Producto especial= new ProductoMenu("especial",24000,681);
	private static Producto papas= new ProductoMenu("papas medianas",5500,450);
	private static Combo prueba= new Combo(0.07,"combo especial",items);
	
	@BeforeClass
	public void setUp() {
		this.items.add(especial);
		this.items.add(papas);	
	}
	
	@Test
	public void testGetPrecio() {
		assertEquals(29500,prueba.getPrecio());
	}
	
	@Test
	public void testGetCalorias() {
		assertEquals(1131,prueba.getCalorias());
	}
	
	@Test
	public void testGenerarTextoFactura() {
		String factura="combo especial\nespecial		24000\npapas medianas		5500\nDESCUENTO		0.07\nPRECIO		27435";
		assertEquals(factura,prueba.generarTextoFactura());
	}
			

}
