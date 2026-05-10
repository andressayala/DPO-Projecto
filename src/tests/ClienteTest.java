package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import modelo.Cliente;

public class ClienteTest {
	
	private Cliente cliente;
	
	@BeforeEach
	void setUp() throws Exception{
		cliente = new Cliente("cliente", "cliente", "cliente");
	}
	
	@Test
	void testAutenticar(){
		assertTrue(cliente.autenticar("cliente", "cliente"));
		assertFalse(cliente.autenticar("cliente", "mal"));
	}
	
	@Test
	void testAgregarPuntos(){
		cliente.agregarPuntos(1000);
		
		assertEquals(1, cliente.getPuntosFidelidad());
	}
	
	@Test
	void testUsarPuntos(){
		cliente.agregarPuntos(1000);
		
		assertTrue(cliente.usarPuntos(1));
		assertEquals(0, cliente.getPuntosFidelidad());
	}
	
	@Test
	void testUsarPuntosInsuficientes(){
		assertFalse(cliente.usarPuntos(1));
	}
}