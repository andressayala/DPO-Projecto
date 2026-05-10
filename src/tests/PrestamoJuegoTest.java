package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import modelo.Cliente;
import modelo.CopiaJuego;
import modelo.PrestamoJuego;

public class PrestamoJuegoTest {
	
	private Cliente cliente;
	private CopiaJuego copia;
	private PrestamoJuego prestamo;
	
	@BeforeEach
	void setUp() throws Exception{
		cliente = new Cliente("cliente", "cliente", "cliente");
		copia = new CopiaJuego(1, "estado");
		prestamo = new PrestamoJuego(1, cliente, copia, "dia");
	}
	
	@Test
	void testCrearPrestamo(){
		assertEquals(1, prestamo.getIdPrestamo());
		assertEquals("dia", prestamo.getFechaInicio());
		assertTrue(prestamo.estaActivo());
	}
	
	@Test
	void testCopiaNoDisponibleAlPrestar(){
		assertFalse(copia.estaDisponible());
	}
	
	@Test
	void testFinalizarPrestamo(){
		prestamo.finalizarPrestamo("dia");
		
		assertFalse(prestamo.estaActivo());
		assertTrue(copia.estaDisponible());
		assertEquals("dia", prestamo.getFechaFin());
	}
}