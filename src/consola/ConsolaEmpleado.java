package consola;

import modelo.DulcesNDados;
import modelo.Empleado;
import modelo.Torneo;
import modelo.Turno;
import modelo.Usuario;

public class ConsolaEmpleado extends Consola {
	
	private Empleado empleado;
	public ConsolaEmpleado(DulcesNDados sistema, Empleado empleado){
		super(sistema);
		this.empleado = empleado;
	}
	
	public void mostrarMenu(){
		System.out.println("\nMENU EMPLEADO");
		System.out.println("1. Ver torneos");
		System.out.println("2. Ver turnos");
		System.out.println("3. Solicitar cambio de turno");
		System.out.println("4. Inscribirse a torneo");
		System.out.println("5. Ver torneos inscritos");
		System.out.println("0. Salir");
	}
	
	public void ejecutarOpcion(int opcion){
		if(opcion == 1){
			verTorneos();
		}
		else if(opcion == 2){
			verTurnos();
		}
		else if(opcion == 3){
			solicitarCambioTurno();
		}
		else if(opcion == 4){
			inscribirseATorneo();
		}
		else if(opcion == 5){
			verTorneosInscritos();
		}
		else if(opcion != 0){
			System.out.println("Opcion invalida");
		}
	}
	
	private void verTorneos(){
		System.out.println("\nTORNEOS");
		
		for(Torneo torneo : sistema.getTorneos()){
			System.out.println("Torneo " + torneo.getIdTorneo() + " " + torneo.getNombre() + " Fecha: " + torneo.getFecha() + " Estado: " + torneo.getEstado());
		}
	}
	
	private void verTurnos(){
		System.out.println("\nTURNOS");
		
		for(Turno turno : sistema.getTurnos()){
			System.out.println("Turno " + turno.getIdTurno() + " Dia: " + turno.getDia() + " Hora: " + turno.getHoraInicio() + " - " + turno.getHoraFin());
		}
	}
	
	private void solicitarCambioTurno(){
		int idTurnoActual = leerEntero("Ingrese el id del turno actual");
		int idTurnoSolicitado = leerEntero("Ingrese el id del turno solicitado");
		String motivo = leerTexto("Ingrese el motivo");
		
		boolean resultado = sistema.solicitarCambioTurno(empleado.getIdEmpleado(), idTurnoActual, idTurnoSolicitado, motivo);
		
		if(resultado){
			System.out.println("Solicitud registrada correctamente");
		}
		else{
			System.out.println("No se pudo registrar la solicitud");
		}
	}
	
	private void inscribirseATorneo(){
		int idTorneo = leerEntero("Ingrese id del torneo");
		
		boolean resultado = sistema.inscribirEmpleadoTorneo(empleado.getIdEmpleado(), idTorneo);
		
		if(resultado){
			System.out.println("Empleado inscrito al torneo");
		}
		else{
			System.out.println("No se pudo inscribir al torneo");
		}
	}
	
	private void verTorneosInscritos(){
		System.out.println("\nTORNEOS INSCRITOS");
		
		for(Torneo torneo : sistema.getTorneos()){
			if(torneo.getEmpleadosParticipantes().contains(empleado)){
				System.out.println("Torneo " + torneo.getIdTorneo() + " " + torneo.getNombre() + " Fecha: " + torneo.getFecha());
			}
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
	
	public static void main(String[] args){
		DulcesNDados sistema = new DulcesNDados();
		Consola consolaBase = new ConsolaEmpleado(sistema, null);
		
		String login = consolaBase.leerTexto("Login empleado");
		String password = consolaBase.leerTexto("Password empleado");
		
		Usuario usuario = sistema.autenticarUsuario(login, password);
		
		if(usuario instanceof Empleado){
			Empleado empleado = (Empleado) usuario;
			ConsolaEmpleado consola = new ConsolaEmpleado(sistema, empleado);
			consola.iniciar();
		}
		else{
			System.out.println("Credenciales invalidas");
		}
	}
}