package modelo;

public class Premio {
	
	private int idPremio;
	private String descripcion;
	private double valor;
	private int puesto;
	
	public Premio(int idPremio, String descripcion, double valor, int puesto){
		this.idPremio = idPremio;
		this.descripcion = descripcion;
		this.valor = valor;
		this.puesto = puesto;
	}
	
	public int getIdPremio(){
		return idPremio;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public double getValor(){
		return valor;
	}
	
	public int getPuesto(){
		return puesto;
	}
}