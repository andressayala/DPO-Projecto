package consola;

import modelo.Administrador;
import modelo.Cocinero;
import modelo.DulcesNDados;
import modelo.Empleado;
import modelo.Juego;
import modelo.Mesero;
import modelo.Premio;
import modelo.SolicitudCambioTurno;
import modelo.Torneo;
import modelo.TorneoAmistoso;
import modelo.TorneoCompetitivo;
import modelo.Usuario;
import modelo.Venta;

public class ConsolaAdministrador extends Consola {
	
	private Administrador administrador;
	
	public ConsolaAdministrador(DulcesNDados sistema, Administrador administrador){
		super(sistema);
		this.administrador = administrador;
	}
	
	public Administrador getAdministrador(){
		return administrador;
	}

	public void setAdministrador(Administrador administrador){
		this.administrador = administrador;
	}

	public void mostrarMenu(){
		System.out.println("\nMENU ADMINISTRADOR");
		System.out.println("1. Ver usuarios");
		System.out.println("2. Ver juegos");
		System.out.println("3. Ver ventas");
		System.out.println("4. Ver torneos");
		System.out.println("5. Crear torneo amistoso");
		System.out.println("6. Crear torneo competitivo");
		System.out.println("7. Abrir torneo");
		System.out.println("8. Cerrar torneo");
		System.out.println("9. Ver solicitudes de turno");
		System.out.println("10. Aprobar solicitud de turno");
		System.out.println("11. Asignar empleado a torneo");
		System.out.println("12. Ver empleados de torneo");
		System.out.println("13. Registrar premio de torneo");
		System.out.println("14. Ver premios de torneo");
		System.out.println("15. Registrar mesero");
		System.out.println("16. Registrar cocinero");
		System.out.println("0. Salir");
	}
	
	public void ejecutarOpcion(int opcion){
		if(opcion == 1){
			verUsuarios();
		}
		else if(opcion == 2){
			verJuegos();
		}
		else if(opcion == 3){
			verVentas();
		}
		else if(opcion == 4){
			verTorneos();
		}
		else if(opcion == 5){
			crearTorneoAmistoso();
		}
		else if(opcion == 6){
			crearTorneoCompetitivo();
		}
		else if(opcion == 7){
			abrirTorneo();
		}
		else if(opcion == 8){
			cerrarTorneo();
		}
		else if(opcion == 9){
			verSolicitudesTurno();
		}
		else if(opcion == 10){
			aprobarSolicitudTurno();
		}
		else if(opcion == 11){
			asignarEmpleadoTorneo();
		}
		else if(opcion == 12){
			verEmpleadosTorneo();
		}
		else if(opcion == 13){
			registrarPremioTorneo();
		}
		else if(opcion == 14){
			verPremiosTorneo();
		}
		else if(opcion == 15){
			registrarMesero();
		}
		else if(opcion == 16){
			registrarCocinero();
		}
		else if(opcion != 0){
			System.out.println("Opcion invalida");
		}
	}
	
	private void verUsuarios(){
		System.out.println("\nUSUARIOS");
		
		for(Usuario usuario : sistema.getUsuarios()){
			System.out.println(usuario.getLogin() + " " + usuario.getNombre());
		}
	}
	
	private void verJuegos(){
		System.out.println("\nJUEGOS");
		
		for(Juego juego : sistema.getJuegos()){
			System.out.println("Juego " + juego.getIdJuego() + " " + juego.getNombre() + " Genero: " + juego.getGenero() + " Dificultad: " + juego.getDificultad());
		}
	}
	
	private void verVentas(){
		System.out.println("\nVENTAS");
		
		for(Venta venta : sistema.getVentas()){
			System.out.println("Venta " + venta.getIdVenta() + " Fecha: " + venta.getFecha() + " Total: " + venta.getTotal());
		}
	}
	
	private void verTorneos(){
		System.out.println("\nTORNEOS");
		
		for(Torneo torneo : sistema.getTorneos()){
			System.out.println("Torneo " + torneo.getIdTorneo() + " " + torneo.getNombre() + " Fecha: " + torneo.getFecha() + " Estado: " + torneo.getEstado());
		}
	}
	
	private void crearTorneoAmistoso(){
		int id = sistema.getTorneos().size() + 1;
		String nombre = leerTexto("Nombre del torneo");
		String fecha = leerTexto("Fecha/dia del torneo");
		int cupo = leerEntero("Cupo maximo");
		int idJuego = leerEntero("Id del juego");
		String respuesta = leerTexto("Permite equipos? (si/no)");
		
		Juego juego = buscarJuego(idJuego);
		
		if(juego == null){
			System.out.println("No existe ese juego");
			return;
		}
		
		int cuposFanaticos = (int)Math.ceil(cupo * 0.2);
		boolean permiteEquipos = respuesta.equalsIgnoreCase("si");
		
		TorneoAmistoso torneo = new TorneoAmistoso(id, nombre, fecha, cupo, cuposFanaticos, juego, permiteEquipos);
		sistema.registrarTorneo(torneo);
		
		System.out.println("Torneo amistoso creado");
	}
	
	private void crearTorneoCompetitivo(){
		int id = sistema.getTorneos().size() + 1;
		String nombre = leerTexto("Nombre del torneo");
		String fecha = leerTexto("Fecha o dia del torneo");
		int cupo = leerEntero("Cupo maximo");
		int idJuego = leerEntero("Id del juego");
		String nivel = leerTexto("Nivel minimo");
		int tarifa = leerEntero("Tarifa de entrada");
		
		Juego juego = buscarJuego(idJuego);
		
		if(juego == null){
			System.out.println("No existe ese juego");
			return;
		}
		
		int cuposFanaticos = (int)Math.ceil(cupo * 0.2);
		double premio = tarifa * cupo * 0.8;
		
		TorneoCompetitivo torneo = new TorneoCompetitivo(id, nombre, fecha, cupo, cuposFanaticos, juego, nivel, premio);
		sistema.registrarTorneo(torneo);
		
		System.out.println("Torneo competitivo creado");
	}
	
	private void abrirTorneo(){
		int id = leerEntero("Ingrese id del torneo");
		Torneo torneo = buscarTorneo(id);
		
		if(torneo != null){
			torneo.abrirInscripciones();
			System.out.println("Torneo abierto");
		}
		else{
			System.out.println("No existe ese torneo");
		}
	}
	
	private void cerrarTorneo(){
		int id = leerEntero("Ingrese id del torneo");
		Torneo torneo = buscarTorneo(id);
		
		if(torneo != null){
			torneo.cerrarInscripciones();
			System.out.println("Torneo cerrado");
		}
		else{
			System.out.println("No existe ese torneo");
		}
	}
	
	private void verSolicitudesTurno(){
		System.out.println("\nSOLICITUDES");
		
		for(SolicitudCambioTurno solicitud : sistema.getSolicitudesCambioTurno()){
			System.out.println("Solicitud " + solicitud.getIdSolicitud() + " Empleado: " + solicitud.getEmpleado().getNombre() + " Estado: " + solicitud.getEstado());
		}
	}
	
	private void aprobarSolicitudTurno(){
		int id = leerEntero("Ingrese id de la solicitud");
		
		boolean resultado = sistema.aprobarSolicitudCambioTurno(id);
		
		if(resultado){
			System.out.println("Solicitud aprobada");
		}
		else{
			System.out.println("No se pudo aprobar");
		}
	}
	
	private void asignarEmpleadoTorneo(){
		int idEmpleado = leerEntero("Ingrese id del empleado");
		int idTorneo = leerEntero("Ingrese id del torneo");
		
		boolean resultado = sistema.asignarEmpleadoATorneo(idEmpleado, idTorneo);
		
		if(resultado){
			System.out.println("Empleado asignado al torneo");
		}
		else{
			System.out.println("No se pudo asignar empleado");
		}
	}
	
	private void verEmpleadosTorneo(){
		int idTorneo = leerEntero("Ingrese id del torneo");
		Torneo torneo = buscarTorneo(idTorneo);
		
		if(torneo == null){
			System.out.println("No existe ese torneo");
			return;
		}
		
		System.out.println("\nEMPLEADOS DEL TORNEO");
		
		for(Empleado empleado : torneo.getEmpleadosApoyo()){
			System.out.println("Empleado " + empleado.getIdEmpleado() + " " + empleado.getNombre());
		}
	}
	
	private void registrarPremioTorneo(){
		int idTorneo = leerEntero("Ingrese id del torneo");
		String descripcion = leerTexto("Descripcion del premio");
		int valor = leerEntero("Valor del premio");
		int puesto = leerEntero("Puesto");
		
		boolean resultado = sistema.registrarPremioTorneo(idTorneo, descripcion, valor, puesto);
		
		if(resultado){
			System.out.println("Premio registrado");
		}
		else{
			System.out.println("No se pudo registrar el premio");
		}
	}
	
	private void verPremiosTorneo(){
		int idTorneo = leerEntero("Ingrese id del torneo");
		Torneo torneo = buscarTorneo(idTorneo);
		
		if(torneo == null){
			System.out.println("No existe ese torneo");
			return;
		}
		
		System.out.println("\nPREMIOS DEL TORNEO");
		
		for(Premio premio : torneo.getPremios()){
			System.out.println("Premio " + premio.getIdPremio() + " " + premio.getDescripcion() + " Valor: " + premio.getValor() + " Puesto: " + premio.getPuesto());
		}
	}
	
	private void registrarMesero(){
		String login = leerTexto("Login del mesero");
		String password = leerTexto("Clave del mesero");
		String nombre = leerTexto("Nombre del mesero");
		int idEmpleado = leerEntero("Id del empleado");
		
		Mesero mesero = sistema.registrarMesero(login, password, nombre, idEmpleado);
		
		if(mesero != null){
			System.out.println("Mesero registrado");
		}
		else{
			System.out.println("No se pudo registrar el mesero");
		}
	}
	
	private void registrarCocinero(){
		String login = leerTexto("Login del cocinero");
		String password = leerTexto("Clave del cocinero");
		String nombre = leerTexto("Nombre del cocinero");
		int idEmpleado = leerEntero("Id del empleado");
		
		Cocinero cocinero = sistema.registrarCocinero(login, password, nombre, idEmpleado);
		
		if(cocinero != null){
			System.out.println("Cocinero registrado");
		}
		else{
			System.out.println("No se pudo registrar el cocinero");
		}
	}
	
	private Juego buscarJuego(int idJuego){
		for(Juego juego : sistema.getJuegos()){
			if(juego.getIdJuego() == idJuego){
				return juego;
			}
		}
		return null;
	}
	
	private Torneo buscarTorneo(int idTorneo){
		for(Torneo torneo : sistema.getTorneos()){
			if(torneo.getIdTorneo() == idTorneo){
				return torneo;
			}
		}
		return null;
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
	
	public static void main(String[] args){
		DulcesNDados sistema = new DulcesNDados();
		Consola consolaBase = new ConsolaAdministrador(sistema, null);
		
		String login = consolaBase.leerTexto("Login administrador");
		String password = consolaBase.leerTexto("Password administrador");
		
		Usuario usuario = sistema.autenticarUsuario(login, password);
		
		if(usuario instanceof Administrador){
			Administrador administrador = (Administrador) usuario;
			ConsolaAdministrador consola = new ConsolaAdministrador(sistema, administrador);
			consola.iniciar();
		}
		else{
			System.out.println("Credenciales invalidas");
		}
	}
}