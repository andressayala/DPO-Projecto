package modelo;

public class CopiaJuego {
	
	private int idCopia;
	private String estado;
	private boolean disponible;
	
	public CopiaJuego(int idCopia, String estado){
		this.idCopia = idCopia;
		this.estado = estado;
		this.disponible = true;
	}
	
	public int getIdCopia(){
		return idCopia;
	}
	
	public String getEstado(){
		return estado;
	}
	
	public boolean estaDisponible(){
		return disponible;
	}
	
	public void prestar(){
		disponible = false;
	}
	
	public void devolver(){
		disponible = true;
	}
	
	public void marcarMantenimiento(){
		estado = "Mantenimiento";
		disponible = false;
	}
}