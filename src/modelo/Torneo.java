package modelo;

import java.util.ArrayList;

public abstract class Torneo {
	
	private int idTorneo;
	private String nombre;
	private String fecha;
	private int cupoMaximo;
	private int cuposFanaticos;
	private String estado;
	private Juego juego;
	private ArrayList<Empleado> empleadosApoyo;
	private ArrayList<Empleado> empleadosParticipantes;
	private ArrayList<Premio> premios;
	
	public Torneo(int idTorneo, String nombre, String fecha, int cupoMaximo, int cuposFanaticos, Juego juego){
		this.idTorneo = idTorneo;
		this.nombre = nombre;
		this.fecha = fecha;
		this.cupoMaximo = cupoMaximo;
		this.cuposFanaticos = cuposFanaticos;
		this.juego = juego;
		this.estado = "Cerrado";
		this.empleadosApoyo = new ArrayList<Empleado>();
		this.empleadosParticipantes = new ArrayList<Empleado>();
		this.premios = new ArrayList<Premio>();
	}
	
	public int getIdTorneo(){
		return idTorneo;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public String getFecha(){
		return fecha;
	}
	
	public int getCupoMaximo(){
		return cupoMaximo;
	}
	
	public int getCuposFanaticos(){
		return cuposFanaticos;
	}
	
	public String getEstado(){
		return estado;
	}
	
	public Juego getJuego(){
		return juego;
	}
	
	public ArrayList<Empleado> getEmpleadosApoyo(){
		return empleadosApoyo;
	}
	
	public ArrayList<Empleado> getEmpleadosParticipantes(){
		return empleadosParticipantes;
	}
	
	public ArrayList<Premio> getPremios(){
		return premios;
	}
	
	public boolean hayCupoDisponible(){
		return cupoMaximo > 0;
	}
	
	public void abrirInscripciones(){
		estado = "Abierto";
	}
	
	public void cerrarInscripciones(){
		estado = "Cerrado";
	}
	
	public boolean agregarEmpleadoApoyo(Empleado empleado){
		if(!empleadosApoyo.contains(empleado)){
			empleadosApoyo.add(empleado);
			return true;
		}
		return false;
	}
	
	public boolean agregarEmpleadoParticipante(Empleado empleado){
		if(!empleadosParticipantes.contains(empleado)){
			empleadosParticipantes.add(empleado);
			return true;
		}
		return false;
	}
	
	public boolean agregarPremio(Premio premio){
		if(!premios.contains(premio)){
			premios.add(premio);
			return true;
		}
		return false;
	}
}