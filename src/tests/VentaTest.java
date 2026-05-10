package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import modelo.Cliente;
import modelo.Mesero;
import modelo.ProductoCafeteria;
import modelo.Venta;

public class VentaTest {
	
	private Cliente cliente;
	private Mesero mesero;
	private ProductoCafeteria producto;
	private Venta venta;
	
	@BeforeEach
	void setUp() throws Exception{
		cliente = new Cliente("cliente", "cliente", "cliente");
		mesero = new Mesero("mesero", "mesero", "mesero", 1);
		producto = new ProductoCafeteria(1, "producto", 1, 1, "categoria");
		venta = new Venta(1, cliente, mesero, "dia");
	}
	
	@Test
	void testAgregarDetalle(){
		boolean agregado = venta.agregarDetalle(producto, 1);
		
		assertTrue(agregado);
		assertEquals(1, venta.getDetalles().size());
	}
	
	@Test
	void testDisminuirCantidadProducto(){
		venta.agregarDetalle(producto, 1);
		
		assertEquals(0, producto.getCantidadDisponible());
	}
	
	@Test
	void testNoAgregarSinCantidad(){
		venta.agregarDetalle(producto, 1);
		
		boolean agregado = venta.agregarDetalle(producto, 1);
		
		assertFalse(agregado);
	}
	
	@Test
	void testCalcularTotal(){
		venta.agregarDetalle(producto, 1);
		
		assertTrue(venta.getTotal() > 1);
	}
}