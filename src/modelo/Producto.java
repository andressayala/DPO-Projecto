package modelo;

public abstract class Producto {
	
	private int idProducto;
	private String nombre;
	private double precio;
	private int cantidadDisponible;
	
	public Producto(int idProducto, String nombre, double precio, int cantidadDisponible){
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.cantidadDisponible = cantidadDisponible;
	}
	
	public int getIdProducto(){
		return idProducto;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public double getPrecio(){
		return precio;
	}
	
	public int getCantidadDisponible(){
		return cantidadDisponible;
	}
	
	public void aumentarCantidad(int cantidad){
		cantidadDisponible += cantidad;
	}
	
	public boolean disminuirCantidad(int cantidad){
		if(cantidad <= cantidadDisponible){
			cantidadDisponible -= cantidad;
			return true;
		}
		return false;
	}
}