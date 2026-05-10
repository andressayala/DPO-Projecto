package modelo;

import java.util.ArrayList;

public class Mesero extends Empleado{
	
	private ArrayList<Juego> juegosConocidos;
	
	public Mesero(String login, String password, String nombre, int idEmpleado){
		super(login, password, nombre, idEmpleado);
		this.juegosConocidos = new ArrayList<Juego>();
	}
	
	public void agregarJuegoConocido(Juego juego){
		if(!juegosConocidos.contains(juego)){
			juegosConocidos.add(juego);
		}
	}
	
	public boolean conoceJuego(Juego juego){
		return juegosConocidos.contains(juego);
	}
	
	public ArrayList<Juego> getJuegosConocidos(){
		return juegosConocidos;
	}
}