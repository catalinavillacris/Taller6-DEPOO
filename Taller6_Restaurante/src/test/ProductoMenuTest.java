package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import modelo.ProductoMenu;

public class ProductoMenuTest {

	private static ProductoMenu prueba = new ProductoMenu("corralita",13000,320);
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetPrecio() {
		
		assertEquals(13000,prueba.getPrecio());
	}
	
	@Test
	public void testGetCalorias(){
		assertEquals(320,prueba.getCalorias());
	}
	
	@Test
	public void  testGenerarTextoFactura() {
		assertEquals("corralita		13000",prueba.generarTextoFactura());
	}
	

}
