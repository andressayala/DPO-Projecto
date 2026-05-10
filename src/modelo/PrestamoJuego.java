package modelo;

public class PrestamoJuego {
	
	private int idPrestamo;
	private String fechaInicio;
	private String fechaFin;
	private boolean activo;
	
	private Cliente cliente;
	private CopiaJuego copia;
	
	public PrestamoJuego(int idPrestamo, Cliente cliente, CopiaJuego copia, String fechaInicio){
		this.idPrestamo = idPrestamo;
		this.cliente = cliente;
		this.copia = copia;
		this.fechaInicio = fechaInicio;
		this.fechaFin = "";
		this.activo = true;
		copia.prestar();
	}
	
	public int getIdPrestamo(){
		return idPrestamo;
	}
	
	public String getFechaInicio(){
		return fechaInicio;
	}
	
	public String getFechaFin(){
		return fechaFin;
	}
	
	public boolean estaActivo(){
		return activo;
	}
	
	public Cliente getCliente(){
		return cliente;
	}
	
	public CopiaJuego getCopia(){
		return copia;
	}
	
	public void finalizarPrestamo(String fechaFin){
		this.fechaFin = fechaFin;
		this.activo = false;
		copia.devolver();
	}
}