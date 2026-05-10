package modelo;

public class FavoritoJuego {
	
	private String fechaRegistro;
	
	private Cliente cliente;
	private Juego juego;
	
	public FavoritoJuego(Cliente cliente, Juego juego, String fechaRegistro){
		this.cliente = cliente;
		this.juego = juego;
		this.fechaRegistro = fechaRegistro;
	}
	
	public String getFechaRegistro(){
		return fechaRegistro;
	}
	
	public Cliente getCliente(){
		return cliente;
	}
	
	public Juego getJuego(){
		return juego;
	}
}