package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import modelo.DulcesNDados;
import persistencia.ArchivoDatos;

public class ArchivoDatosTest {
	
	private DulcesNDados sistema;
	private ArchivoDatos archivoDatos;
	
	@BeforeEach
	void setUp() throws Exception{
		sistema = new DulcesNDados();
		archivoDatos = new ArchivoDatos();
		sistema.guardarDatos();
	}
	
	@Test
	void testGuardarUsuarios(){
		File archivo = new File(archivoDatos.getRutaUsuarios());
		
		assertTrue(archivo.exists());
	}
	
	@Test
	void testGuardarJuegos(){
		File archivo = new File(archivoDatos.getRutaJuegos());
		
		assertTrue(archivo.exists());
	}
	
	@Test
	void testGuardarProductos(){
		File archivo = new File(archivoDatos.getRutaProductos());
		
		assertTrue(archivo.exists());
	}
	
	@Test
	void testGuardarTorneos(){
		File archivo = new File(archivoDatos.getRutaTorneos());
		
		assertTrue(archivo.exists());
	}
	
	@Test
	void testGuardarTurnos(){
		File archivo = new File(archivoDatos.getRutaTurnos());
		
		assertTrue(archivo.exists());
	}
}