package modelo;

public class TorneoAmistoso extends Torneo {
	
	private boolean permiteEquipos;
	
	public TorneoAmistoso(int idTorneo, String nombre, String fecha, int cupoMaximo, int cuposFanaticos, Juego juego, boolean permiteEquipos){
		super(idTorneo, nombre, fecha, cupoMaximo, cuposFanaticos, juego);
		this.permiteEquipos = permiteEquipos;
	}
	
	public boolean permiteEquipos(){
		return permiteEquipos;
	}
}