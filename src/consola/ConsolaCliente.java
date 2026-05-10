package consola;

import modelo.Cliente;
import modelo.CopiaJuego;
import modelo.DulcesNDados;
import modelo.InscripcionTorneo;
import modelo.Juego;
import modelo.Torneo;
import modelo.Usuario;

public class ConsolaCliente extends Consola {
	
	private Cliente cliente;
	
	public ConsolaCliente(DulcesNDados sistema, Cliente cliente){
		super(sistema);
		this.cliente = cliente;
	}
	
	public void mostrarMenu(){
		System.out.println("\nMENU CLIENTE");
		System.out.println("1. Ver juegos disponibles para prestamo");
		System.out.println("2. Pedir juego prestado");
		System.out.println("3. Devolver juego prestado");
		System.out.println("4. Ver torneos");
		System.out.println("5. Inscripcion torneo");
		System.out.println("6. Cancelar inscripcion a torneo");
		System.out.println("7. Ver inscripciones");
		System.out.println("8. Marcar juego favorito");
		System.out.println("9. Guardar bono descuento");
		System.out.println("0. Salir");
	}
	
	public void ejecutarOpcion(int opcion){
		if(opcion == 1){
			verJuegosDisponibles();
		}
		else if(opcion == 2){
			pedirJuegoPrestado();
		}
		else if(opcion == 3){
			devolverPrestamo();
		}
		else if(opcion == 4){
			verTorneos();
		}
		else if(opcion == 5){
			inscribirseATorneo();
		}
		else if(opcion == 6){
			cancelarInscripcion();
		}
		else if(opcion == 7){
			verMisInscripciones();
		}
		else if(opcion == 8){
			marcarJuegoFavorito();
		}
		else if(opcion == 9){
			guardarBonoDescuento();
		}
		else if(opcion != 0){
			System.out.println("Opcion invalida");
		}
	}
	
	private void verJuegosDisponibles(){
		System.out.println("\nCOPIAS DE JUEGOS");
		
		for(CopiaJuego copia : sistema.getCopiasJuegos()){
			System.out.println("Copia " + copia.getIdCopia() + "Estado: " + copia.getEstado() + "Disponible: " + copia.estaDisponible());
		}
	}
	
	private void pedirJuegoPrestado(){
		int idCopia = leerEntero("Ingrese el id de la copia que quiere pedir");
		String fecha = leerTexto("Ingrese la fecha/dia del prestamo");
		
		boolean resultado = sistema.registrarPrestamo(cliente.getLogin(), idCopia, fecha);
		
		if(resultado){
			System.out.println("Prestamo registrado correctamente");
		}
		else{
			System.out.println("No se pudo registrar el prestamo");
		}
	}
	
	private void devolverPrestamo(){
		int idPrestamo = leerEntero("Ingrese el id del prestamo a devolver");
		String fechaFin = leerTexto("Ingrese la fecha de devolucion");
		
		boolean resultado = sistema.finalizarPrestamo(idPrestamo, fechaFin);
		
		if(resultado){
			System.out.println("Prestamo finalizado correctamente");
		}
		else{
			System.out.println("No se pudo finalizar el prestamo");
		}
	}
	
	private void verTorneos(){
		System.out.println("\nTORNEOS");
		
		for(Torneo torneo : sistema.getTorneos()){
			System.out.println("Torneo " + torneo.getIdTorneo() + " " + torneo.getNombre() + "Fecha: " + torneo.getFecha() + " Estado: " + torneo.getEstado());
		}
	}
	
	private void inscribirseATorneo(){
		int idTorneo = leerEntero("Ingrese el id del torneo");
		int cantidad = leerEntero("Ingrese cantidad de cupos a tomar maximo 3");
		
		boolean resultado = sistema.inscribirClienteTorneo(cliente.getLogin(), idTorneo, cantidad);
		
		if(resultado){
			System.out.println("Inscripcion registrada correctamente");
		}
		else{
			System.out.println("No se pudo registrar la inscripcion");
		}
	}
	
	private void cancelarInscripcion(){
		int idTorneo = leerEntero("Ingrese el id del torneo");
		
		boolean resultado = sistema.cancelarInscripcion(cliente.getLogin(), idTorneo);
		
		if(resultado){
			System.out.println("Inscripcion cancelada correctamente");
		}
		else{
			System.out.println("No se encontro una inscripcion activa");
		}
	}
	
	private void verMisInscripciones(){
		System.out.println("\nMIS INSCRIPCIONES");
		
		for(InscripcionTorneo inscripcion : sistema.getInscripcionesTorneo()){
			if(inscripcion.getCliente() == cliente){
				System.out.println("Inscripcion " + inscripcion.getIdInscripcion() + " Torneo: " + inscripcion.getTorneo().getNombre() + " Activa: " + inscripcion.estaActiva());
			}
		}
	}
	
	private void marcarJuegoFavorito(){
		System.out.println("\nJUEGOS");
		
		for(Juego juego : sistema.getJuegos()){
			System.out.println("Juego " + juego.getIdJuego() + " " + juego.getNombre());
		}
		
		int idJuego = leerEntero("Ingrese el id del juego favorito");
		String fecha = leerTexto("Ingrese la fecha/dia");
		
		boolean resultado = sistema.marcarJuegoFavorito(cliente.getLogin(), idJuego, fecha);
		
		if(resultado){
			System.out.println("Juego favorito registrado");
		}
		else{
			System.out.println("No se pudo registrar el favorito");
		}
	}
	
	private void guardarBonoDescuento(){
		int idTorneo = leerEntero("Ingrese id del torneo amistoso ganado");
		
		boolean resultado = sistema.guardarBonoDescuentoCliente(cliente.getLogin(), idTorneo);
		
		if(resultado){
			System.out.println("Bono guardado correctamente");
		}
		else{
			System.out.println("No se pudo guardar el bono");
		}
	}
	
	public void iniciar(){
		int opcion = -1;
		
		while(opcion != 0){
			mostrarMenu();
			opcion = leerEntero("Seleccione una opcion");
			ejecutarOpcion(opcion);
		}
		
		sistema.guardarDatos();
		System.out.println("Sesion finalizada");
	}
	
	private static void registrarClienteNuevo(DulcesNDados sistema, Consola consolaBase){
		String login = consolaBase.leerTexto("Nuevo login");
		String password = consolaBase.leerTexto("Nueva clave");
		String nombre = consolaBase.leerTexto("Nombre");
		
		Cliente cliente = sistema.registrarCliente(login, password, nombre);
		
		if(cliente != null){
			sistema.guardarDatos();
			System.out.println("Cliente registrado correctamente");
		}
		else{
			System.out.println("No se pudo registrar el cliente");
		}
	}
	
	public static void main(String[] args){
		DulcesNDados sistema = new DulcesNDados();
		Consola consolaBase = new ConsolaCliente(sistema, null);
		
		int opcion = -1;
		
		while(opcion != 0){
			System.out.println("\nINICIO CLIENTE");
			System.out.println("1. Iniciar sesion");
			System.out.println("2. Registrarse");
			System.out.println("0. Salir");
			
			opcion = consolaBase.leerEntero("Seleccione una opcion");
			
			if(opcion == 1){
				String login = consolaBase.leerTexto("Login cliente");
				String password = consolaBase.leerTexto("Password cliente");
				
				Usuario usuario = sistema.autenticarUsuario(login, password);
				
				if(usuario instanceof Cliente){
					Cliente cliente = (Cliente) usuario;
					ConsolaCliente consola = new ConsolaCliente(sistema, cliente);
					consola.iniciar();
				}
				else{
					System.out.println("Credenciales invalidas");
				}
			}
			else if(opcion == 2){
				registrarClienteNuevo(sistema, consolaBase);
			}
			else if(opcion != 0){
				System.out.println("Opcion invalida");
			}
		}
	}
}