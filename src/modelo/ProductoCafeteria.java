package modelo;

public class ProductoCafeteria extends Producto {
	
	private String categoria;
	
	public ProductoCafeteria(int idProducto, String nombre, double precio, int cantidadDisponible, String categoria){
		super(idProducto, nombre, precio, cantidadDisponible);
		this.categoria = categoria;
	}
	
	public String getCategoria(){
		return categoria;
	}
}