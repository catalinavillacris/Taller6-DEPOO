package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Ingrediente;
import modelo.ProductoAjustado;
import modelo.ProductoMenu;

public class ProductoAjustadoTest {
	
	private static ProductoMenu producto = new ProductoMenu("corralita",13000,320);
	private static Ingrediente piña=new Ingrediente("piña",2500);
	private static Ingrediente tomate=new Ingrediente("tomate",1000);
	private static ArrayList<Ingrediente> agregados = new ArrayList<Ingrediente> ();
	private static ArrayList<Ingrediente> eliminados = new ArrayList<Ingrediente> ();
	private static ProductoAjustado prueba = new ProductoAjustado(producto,agregados,eliminados);
	
	@BeforeClass
	public void setUp() {
		this.agregados.add(piña);
		this.eliminados.add(tomate);
		
	}
	
	@Test
	public void testGetPrecio() {
		assertEquals(16500,prueba.getPrecio());
	}
	
	@Test
	public void testGetCalorias() {
		assertEquals(320,prueba.getCalorias());
	}
	
	@Test
	public void testGenerarTextoFactura() {
		String factura= "corralita	13000\npiña		2500\ntomate";
		assertEquals(factura,prueba.generarTextoFactura());
	}

}
