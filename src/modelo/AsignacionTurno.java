package modelo;

public class AsignacionTurno {
	
	private int idAsignacion;
	private boolean activa;
	
	private Empleado empleado;
	private Turno turno;
	
	public AsignacionTurno(int idAsignacion, Empleado empleado, Turno turno){
		this.idAsignacion = idAsignacion;
		this.empleado = empleado;
		this.turno = turno;
		this.activa = true;
	}
	
	public int getIdAsignacion(){
		return idAsignacion;
	}
	
	public boolean estaActiva(){
		return activa;
	}
	
	public Empleado getEmpleado(){
		return empleado;
	}
	
	public Turno getTurno(){
		return turno;
	}
	
	public void cancelar(){
		activa = false;
	}
}