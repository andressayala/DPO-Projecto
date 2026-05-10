package modelo;

public class TorneoCompetitivo extends Torneo {
	
	private String nivelMinimo;
	private double premioMayor;
	
	public TorneoCompetitivo(int idTorneo, String nombre, String fecha, int cupoMaximo, int cuposFanaticos, Juego juego, String nivelMinimo, double premioMayor){
		super(idTorneo, nombre, fecha, cupoMaximo, cuposFanaticos, juego);
		this.nivelMinimo = nivelMinimo;
		this.premioMayor = premioMayor;
	}
	
	public String getNivelMinimo(){
		return nivelMinimo;
	}
	
	public double getPremioMayor(){
		return premioMayor;
	}
}