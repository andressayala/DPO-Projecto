package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import modelo.Cliente;
import modelo.DulcesNDados;
import modelo.Torneo;
import modelo.Usuario;
import modelo.Venta;
import modelo.Cocinero;
import modelo.Mesero;
import modelo.TorneoAmistoso;
public class DulcesNDadosTest {
	
	private DulcesNDados sistema;
	
	@BeforeEach
	void setUp() throws Exception{
		sistema = new DulcesNDados();
	}
	
	@Test
	void testAutenticarUsuarioCorrecto(){
		Usuario usuario = sistema.autenticarUsuario("cliente", "cliente");
		
		assertNotNull(usuario);
		assertTrue(usuario instanceof Cliente);
	}
	
	@Test
	void testAutenticarUsuarioIncorrecto(){
		Usuario usuario = sistema.autenticarUsuario("cliente", "mal");
		
		assertNull(usuario);
	}
	
	@Test
	void testRegistrarCliente(){
		Cliente cliente = sistema.registrarCliente("nuevo", "nuevo", "nuevo");
		
		assertNotNull(cliente);
		assertEquals("nuevo", cliente.getLogin());
	}
	
	@Test
	void testRegistrarClienteRepetido(){
		Cliente cliente = sistema.registrarCliente("cliente", "cliente", "cliente");
		
		assertNull(cliente);
	}
	
	@Test
	void testRegistrarPrestamo(){
		boolean resultado = sistema.registrarPrestamo("cliente", 1, "dia");
		
		assertTrue(resultado);
		assertEquals(1, sistema.getPrestamos().size());
	}
	
	@Test
	void testNoRegistrarPrestamoMismaCopia(){
		boolean primero = sistema.registrarPrestamo("cliente", 1, "dia");
		boolean segundo = sistema.registrarPrestamo("cliente", 1, "dia");
		
		assertTrue(primero);
		assertFalse(segundo);
	}
	
	@Test
	void testRegistrarVenta(){
		Venta venta = sistema.registrarVenta("cliente", 1, "dia");
		
		assertNotNull(venta);
		assertEquals(1, sistema.getVentas().size());
	}
	
	@Test
	void testInscribirTorneoAbierto(){
		Torneo torneo = sistema.getTorneos().get(0);
		torneo.abrirInscripciones();
		
		boolean resultado = sistema.inscribirClienteTorneo("cliente", torneo.getIdTorneo(), true);
		
		assertTrue(resultado);
		assertEquals(1, sistema.getInscripcionesTorneo().size());
	}
	
	@Test
	void testNoInscribirTorneoCerrado(){
		Torneo torneo = sistema.getTorneos().get(0);
		
		boolean resultado = sistema.inscribirClienteTorneo("cliente", torneo.getIdTorneo(), true);
		
		assertFalse(resultado);
	}
	
	@Test
	void testSolicitarCambioTurno(){
		boolean resultado = sistema.solicitarCambioTurno(1, 1, 2, "motivo");
		
		assertTrue(resultado);
		assertEquals(1, sistema.getSolicitudesCambioTurno().size());
	}
	
	@Test
	void testAsignarEmpleadoATorneo(){
		boolean resultado = sistema.asignarEmpleadoATorneo(1, 1);
		
		assertTrue(resultado);
		assertEquals(1, sistema.getTorneos().get(0).getEmpleadosApoyo().size());
	}

	@Test
	void testNoAsignarEmpleadoATorneoInexistente(){
		boolean resultado = sistema.asignarEmpleadoATorneo(1, 99);
		
		assertFalse(resultado);
	}

	@Test
	void testRegistrarPremioTorneo(){
		Torneo torneo = sistema.getTorneos().get(0);
		int cantidadInicial = torneo.getPremios().size();
		
		boolean resultado = sistema.registrarPremioTorneo(1, "premio2", 1, 1);
		
		assertTrue(resultado);
		assertEquals(cantidadInicial + 1, torneo.getPremios().size());
	}

	@Test
	void testNoRegistrarPremioTorneoInexistente(){
		boolean resultado = sistema.registrarPremioTorneo(99, "premio", 1, 1);
		
		assertFalse(resultado);
	}

	@Test
	void testNoInscribirNormalSinCupoNormal(){
		Torneo torneo = sistema.getTorneos().get(0);
		torneo.abrirInscripciones();
		
		boolean resultado = sistema.inscribirClienteTorneo("cliente", torneo.getIdTorneo(), false);
		
		assertFalse(resultado);
	}
	
	@Test
	void testRegistrarMesero(){
		Mesero mesero = sistema.registrarMesero("m", "m", "m", 3);
		
		assertNotNull(mesero);
		assertEquals("m", mesero.getLogin());
	}

	@Test
	void testRegistrarCocinero(){
		Cocinero cocinero = sistema.registrarCocinero("c", "c", "c", 4);
		
		assertNotNull(cocinero);
		assertEquals("c", cocinero.getLogin());
	}

	@Test
	void testMarcarJuegoFavorito(){
		Cliente cliente = sistema.registrarCliente("nuevo", "nuevo", "nuevo");
		
		boolean resultado = sistema.marcarJuegoFavorito(cliente.getLogin(), 2, "dia");
		
		assertTrue(resultado);
		assertEquals(2, sistema.getFavoritosJuegos().size());
	}

	@Test
	void testClientePuedeTomarTresCupos(){
		Torneo torneo = new TorneoAmistoso(99, "torneo2", "dia", 3, 1, sistema.getJuegos().get(0), false);
		sistema.registrarTorneo(torneo);
		torneo.abrirInscripciones();
		
		boolean resultado = sistema.inscribirClienteTorneo("cliente", 99, 3);
		
		assertTrue(resultado);
		assertEquals(3, sistema.getInscripcionesTorneo().size());
	}

	@Test
	void testClienteNoPuedeTomarMasDeTresCupos(){
		Torneo torneo = new TorneoAmistoso(99, "torneo2", "dia", 4, 1, sistema.getJuegos().get(0), false);
		sistema.registrarTorneo(torneo);
		torneo.abrirInscripciones();
		
		boolean primero = sistema.inscribirClienteTorneo("cliente", 99, 3);
		boolean segundo = sistema.inscribirClienteTorneo("cliente", 99, 1);
		
		assertTrue(primero);
		assertFalse(segundo);
	}

	@Test
	void testEmpleadoNoPuedeInscribirseSiTieneTurno(){
		Torneo torneo = sistema.getTorneos().get(0);
		torneo.abrirInscripciones();
		
		boolean resultado = sistema.inscribirEmpleadoTorneo(1, 1);
		
		assertFalse(resultado);
	}

	@Test
	void testEmpleadoPuedeInscribirseSiNoTieneTurno(){
		Mesero mesero = sistema.registrarMesero("empleadoNuevo", "empleadoNuevo", "empleadoNuevo", 10);
		Torneo torneo = new TorneoAmistoso(100, "torneoEmpleado", "otroDia", 2, 1, sistema.getJuegos().get(0), false);
		
		sistema.registrarTorneo(torneo);
		torneo.abrirInscripciones();
		
		boolean resultado = sistema.inscribirEmpleadoTorneo(mesero.getIdEmpleado(), torneo.getIdTorneo());
		
		assertTrue(resultado);
		assertEquals(1, torneo.getEmpleadosParticipantes().size());
	}

	@Test
	void testGuardarBonoDescuentoCliente(){
		boolean resultado = sistema.guardarBonoDescuentoCliente("cliente", 1);
		Cliente cliente = (Cliente)sistema.autenticarUsuario("cliente", "cliente");
		
		assertTrue(resultado);
		assertTrue(cliente.usarBonoDescuento());
	}
}