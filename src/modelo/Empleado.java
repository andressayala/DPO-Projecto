package modelo;

public abstract class Empleado extends Usuario{
	
	private int idEmpleado;
	private double descuentoEmpleado;
	
	public Empleado(String login, String password, String nombre, int idEmpleado){
		super(login, password, nombre);
		this.idEmpleado = idEmpleado;
		this.descuentoEmpleado = 0.1;
	}
	
	public int getIdEmpleado(){
		return idEmpleado;
	}
	
	public double getDescuentoEmpleado(){
		return descuentoEmpleado;
	}
}