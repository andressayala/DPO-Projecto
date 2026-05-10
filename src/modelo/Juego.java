package modelo;

public class Juego{
	
	private int idJuego;
	private String nombre;
	private String genero;
	private String dificultad;
	private int minJugadores;
	private int maxJugadores;
	private boolean esParaVenta;
	
	public Juego(int idJuego, String nombre, String genero, String dificultad, int minJugadores, int maxJugadores, boolean esParaVenta){
		this.idJuego = idJuego;
		this.nombre = nombre;
		this.genero = genero;
		this.dificultad = dificultad;
		this.minJugadores = minJugadores;
		this.maxJugadores = maxJugadores;
		this.esParaVenta = esParaVenta;
	}
	
	public int getIdJuego(){
		return idJuego;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public String getGenero(){
		return genero;
	}
	
	public String getDificultad(){
		return dificultad;
	}
	
	public int getMinJugadores(){
		return minJugadores;
	}
	
	public int getMaxJugadores(){
		return maxJugadores;
	}
	
	public boolean esParaVenta(){
		return esParaVenta;
	}
}