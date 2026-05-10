package modelo;

public class DetalleVenta {
	
	private int cantidad;
	private double precioUnitario;
	private Producto producto;
	
	public DetalleVenta(Producto producto, int cantidad, double precioUnitario){
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
	}
	
	public int getCantidad(){
		return cantidad;
	}
	
	public double getPrecioUnitario(){
		return precioUnitario;
	}
	
	public Producto getProducto(){
		return producto;
	}
	
	public double calcularSubtotal(){
		return cantidad * precioUnitario;
	}
}