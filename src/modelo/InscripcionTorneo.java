package modelo;

public class InscripcionTorneo {
	
	private int idInscripcion;
	private String fechaInscripcion;
	private boolean esFanatico;
	private boolean activa;
	private Cliente cliente;
	private Torneo torneo;
	
	public InscripcionTorneo(int idInscripcion, Cliente cliente, Torneo torneo, String fechaInscripcion, boolean esFanatico){
		this.idInscripcion = idInscripcion;
		this.cliente = cliente;
		this.torneo = torneo;
		this.fechaInscripcion = fechaInscripcion;
		this.esFanatico = esFanatico;
		this.activa = true;
	}
	
	public int getIdInscripcion(){
		return idInscripcion;
	}
	
	public String getFechaInscripcion(){
		return fechaInscripcion;
	}
	
	public boolean esFanatico(){
		return esFanatico;
	}
	
	public boolean estaActiva(){
		return activa;
	}
	
	public Cliente getCliente(){
		return cliente;
	}
	
	public Torneo getTorneo(){
		return torneo;
	}
	
	public void cancelar(){
		activa = false;
	}
}