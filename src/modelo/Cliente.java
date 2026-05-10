package modelo;

public class Cliente extends Usuario{
	
	private int puntosFidelidad;
	private boolean tieneBonoDescuento;
	
	public Cliente(String login, String password, String nombre){
		super(login, password, nombre);
		this.puntosFidelidad = 0;
		this.tieneBonoDescuento = false;
	}
	
	public int getPuntosFidelidad(){
		return puntosFidelidad;
	}
	
	public void agregarPuntos(double valorCompra){
		int puntosNuevos= (int)(valorCompra/1000);
		puntosFidelidad += puntosNuevos;
	}
	
	public boolean usarPuntos(int cantidad){
		if(cantidad <= puntosFidelidad){
			puntosFidelidad -= cantidad;
			return true;
		}
		return false;
	}
	
	public void guardarBonoDescuento(){
		tieneBonoDescuento = true;
	}
	
	public boolean usarBonoDescuento(){
		if(tieneBonoDescuento){
			tieneBonoDescuento = false;
			return true;
		}
		return false;
	}
}