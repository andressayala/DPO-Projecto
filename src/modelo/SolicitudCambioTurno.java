package modelo;

public class SolicitudCambioTurno {
	
	private int idSolicitud;
	private String motivo;
	private String estado;
	
	private Empleado empleado;
	private Turno turnoActual;
	private Turno turnoSolicitado;
	
	public SolicitudCambioTurno(int idSolicitud, Empleado empleado, Turno turnoActual, Turno turnoSolicitado, String motivo){
		this.idSolicitud = idSolicitud;
		this.empleado = empleado;
		this.turnoActual = turnoActual;
		this.turnoSolicitado = turnoSolicitado;
		this.motivo = motivo;
		this.estado = "Pendiente";
	}
	
	public int getIdSolicitud(){
		return idSolicitud;
	}
	
	public String getMotivo(){
		return motivo;
	}
	
	public String getEstado(){
		return estado;
	}
	
	public Empleado getEmpleado(){
		return empleado;
	}
	
	public Turno getTurnoActual(){
		return turnoActual;
	}
	
	public Turno getTurnoSolicitado(){
		return turnoSolicitado;
	}
	
	public void aprobar(){
		estado = "Aprobada";
	}
	
	public void rechazar(){
		estado = "Rechazada";
	}
}