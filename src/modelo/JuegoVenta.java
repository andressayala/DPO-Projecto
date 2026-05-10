package modelo;

public class JuegoVenta extends Producto {
	
	private String editorial;
	private int edadRecomendada;
	
	public JuegoVenta(int idProducto, String nombre, double precio, int cantidadDisponible, String editorial, int edadRecomendada){
		super(idProducto, nombre, precio, cantidadDisponible);
		this.editorial = editorial;
		this.edadRecomendada = edadRecomendada;
	}
	
	public String getEditorial(){
		return editorial;
	}
	
	public int getEdadRecomendada(){
		return edadRecomendada;
	}
}