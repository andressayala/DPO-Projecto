package modelo;

public class Turno {
	
	private int idTurno;
	private String dia;
	private String horaInicio;
	private String horaFin;
	
	public Turno(int idTurno, String dia, String horaInicio, String horaFin){
		this.idTurno = idTurno;
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	public int getIdTurno(){
		return idTurno;
	}
	
	public String getDia(){
		return dia;
	}
	
	public String getHoraInicio(){
		return horaInicio;
	}
	
	public String getHoraFin(){
		return horaFin;
	}
	
	public boolean coincideCon(String fecha){
		return fecha.toLowerCase().contains(dia.toLowerCase());
	}
}