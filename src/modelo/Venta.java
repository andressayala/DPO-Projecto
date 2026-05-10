package modelo;

import java.util.ArrayList;

public class Venta {
	
	private int idVenta;
	private String fecha;
	private double subtotal;
	private double impuesto;
	private double propina;
	private double total;
	private Cliente cliente;
	private Empleado empleado;
	private ArrayList<DetalleVenta> detalles;
	
	public Venta(int idVenta, Cliente cliente, Empleado empleado, String fecha){
		this.idVenta = idVenta;
		this.cliente = cliente;
		this.empleado = empleado;
		this.fecha = fecha;
		this.subtotal = 0;
		this.impuesto = 0;
		this.propina = 0;
		this.total = 0;
		this.detalles = new ArrayList<DetalleVenta>();
	}
	
	public boolean agregarDetalle(Producto producto, int cantidad){
		if(producto.disminuirCantidad(cantidad)){
			DetalleVenta detalle = new DetalleVenta(producto, cantidad, producto.getPrecio());
			detalles.add(detalle);
			calcularTotal();
			return true;
		}
		return false;
	}
	
	public double calcularSubtotal(){
		subtotal = 0;
		for(DetalleVenta detalle : detalles){
			subtotal += detalle.calcularSubtotal();
		}
		return subtotal;
	}
	
	public double calcularImpuesto(){
		impuesto = calcularSubtotal() * 0.19;
		return impuesto;
	}
	
	public double calcularTotal(){
		subtotal = calcularSubtotal();
		impuesto = calcularImpuesto();
		propina = subtotal * 0.10;
		total = subtotal + impuesto + propina;
		return total;
	}
	
	public int getIdVenta(){
		return idVenta;
	}
	
	public double getTotal(){
		return total;
	}
	
	public String getFecha(){
		return fecha;
	}
	
	public Cliente getCliente(){
		return cliente;
	}
	
	public Empleado getEmpleado(){
		return empleado;
	}
	
	public ArrayList<DetalleVenta> getDetalles(){
		return detalles;
	}
	
	public void cargarTotalGuardado(double total){
		this.total = total;
	}
}