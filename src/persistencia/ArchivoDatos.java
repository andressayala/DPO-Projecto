package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import modelo.Administrador;
import modelo.Cliente;
import modelo.Cocinero;
import modelo.Empleado;
import modelo.Juego;
import modelo.JuegoVenta;
import modelo.Mesero;
import modelo.Producto;
import modelo.ProductoCafeteria;
import modelo.Torneo;
import modelo.TorneoAmistoso;
import modelo.TorneoCompetitivo;
import modelo.Turno;
import modelo.Usuario;
import modelo.Venta;

public class ArchivoDatos {
	
	private String rutaCarpeta;
	private String rutaUsuarios;
	private String rutaJuegos;
	private String rutaProductos;
	private String rutaVentas;
	private String rutaTorneos;
	private String rutaTurnos;
	
	public ArchivoDatos(){
		this.rutaCarpeta = "data";
		this.rutaUsuarios = "data/usuarios.txt";
		this.rutaJuegos = "data/juegos.txt";
		this.rutaProductos = "data/productos.txt";
		this.rutaVentas = "data/ventas.txt";
		this.rutaTorneos = "data/torneos.txt";
		this.rutaTurnos = "data/turnos.txt";
		crearCarpetaDatos();
	}
	
	private void crearCarpetaDatos(){
		File carpeta = new File(rutaCarpeta);
		
		if(!carpeta.exists()){
			carpeta.mkdirs();
		}
	}
	
	public ArrayList<Usuario> cargarUsuarios(){
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		File archivo = new File(rutaUsuarios);
		
		if(!archivo.exists()){
			return usuarios;
		}
		
		try{
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			String linea = lector.readLine();
			
			while(linea != null){
				String[] partes = linea.split(";");
				
				if(partes.length >= 4){
					String tipo = partes[0];
					String login = partes[1];
					String password = partes[2];
					String nombre = partes[3];
					
					if(tipo.equals("Cliente")){
						usuarios.add(new Cliente(login, password, nombre));
					}
					else if(tipo.equals("Administrador")){
						usuarios.add(new Administrador(login, password, nombre));
					}
					else if(tipo.equals("Mesero") && partes.length >= 5){
						int idEmpleado = Integer.parseInt(partes[4]);
						usuarios.add(new Mesero(login, password, nombre, idEmpleado));
					}
					else if(tipo.equals("Cocinero") && partes.length >= 5){
						int idEmpleado = Integer.parseInt(partes[4]);
						usuarios.add(new Cocinero(login, password, nombre, idEmpleado));
					}
				}
				
				linea = lector.readLine();
			}
			
			lector.close();
		}
		catch(Exception e){
			System.out.println("Error cargando usuarios");
		}
		
		return usuarios;
	}
	
	public ArrayList<Juego> cargarJuegos(){
		ArrayList<Juego> juegos = new ArrayList<Juego>();
		File archivo = new File(rutaJuegos);
		
		if(!archivo.exists()){
			return juegos;
		}
		
		try{
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			String linea = lector.readLine();
			
			while(linea != null){
				String[] partes = linea.split(";");
				
				if(partes.length >= 7){
					int idJuego = Integer.parseInt(partes[0]);
					String nombre = partes[1];
					String genero = partes[2];
					String dificultad = partes[3];
					int minJugadores = Integer.parseInt(partes[4]);
					int maxJugadores = Integer.parseInt(partes[5]);
					boolean esParaVenta = Boolean.parseBoolean(partes[6]);
					
					juegos.add(new Juego(idJuego, nombre, genero, dificultad, minJugadores, maxJugadores, esParaVenta));
				}
				
				linea = lector.readLine();
			}
			
			lector.close();
		}
		catch(Exception e){
			System.out.println("Error cargando juegos");
		}
		
		return juegos;
	}
	
	public ArrayList<Producto> cargarProductos(){
		ArrayList<Producto> productos = new ArrayList<Producto>();
		File archivo = new File(rutaProductos);
		
		if(!archivo.exists()){
			return productos;
		}
		
		try{
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			String linea = lector.readLine();
			
			while(linea != null){
				String[] partes = linea.split(";");
				
				if(partes.length >= 6){
					String tipo = partes[0];
					int idProducto = Integer.parseInt(partes[1]);
					String nombre = partes[2];
					double precio = Double.parseDouble(partes[3]);
					int cantidadDisponible = Integer.parseInt(partes[4]);
					
					if(tipo.equals("ProductoCafeteria")){
						String categoria = partes[5];
						productos.add(new ProductoCafeteria(idProducto, nombre, precio, cantidadDisponible, categoria));
					}
					else if(tipo.equals("JuegoVenta") && partes.length >= 7){
						String editorial = partes[5];
						int edadRecomendada = Integer.parseInt(partes[6]);
						productos.add(new JuegoVenta(idProducto, nombre, precio, cantidadDisponible, editorial, edadRecomendada));
					}
				}
				
				linea = lector.readLine();
			}
			
			lector.close();
		}
		catch(Exception e){
			System.out.println("Error cargando productos");
		}
		
		return productos;
	}
	
	public ArrayList<Venta> cargarVentas(ArrayList<Usuario> usuarios){
		ArrayList<Venta> ventas = new ArrayList<Venta>();
		File archivo = new File(rutaVentas);
		
		if(!archivo.exists()){
			return ventas;
		}
		
		try{
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			String linea = lector.readLine();
			
			while(linea != null){
				String[] partes = linea.split(";");
				
				if(partes.length >= 5){
					int idVenta = Integer.parseInt(partes[0]);
					String fecha = partes[1];
					String loginCliente = partes[2];
					int idEmpleado = Integer.parseInt(partes[3]);
					double total = Double.parseDouble(partes[4]);
					
					Cliente cliente = buscarClientePorLogin(usuarios, loginCliente);
					Empleado empleado = buscarEmpleadoPorId(usuarios, idEmpleado);
					
					if(cliente != null && empleado != null){
						Venta venta = new Venta(idVenta, cliente, empleado, fecha);
						venta.cargarTotalGuardado(total);
						ventas.add(venta);
					}
				}
				
				linea = lector.readLine();
			}
			
			lector.close();
		}
		catch(Exception e){
			System.out.println("Error cargando ventas");
		}
		
		return ventas;
	}
	
	public ArrayList<Torneo> cargarTorneos(ArrayList<Juego> juegos){
		ArrayList<Torneo> torneos = new ArrayList<Torneo>();
		File archivo = new File(rutaTorneos);
		
		if(!archivo.exists()){
			return torneos;
		}
		
		try{
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			String linea = lector.readLine();
			
			while(linea != null){
				String[] partes = linea.split(";");
				
				if(partes.length >= 8){
					String tipo = partes[0];
					int idTorneo = Integer.parseInt(partes[1]);
					String nombre = partes[2];
					String fecha = partes[3];
					int cupoMaximo = Integer.parseInt(partes[4]);
					int cuposFanaticos = Integer.parseInt(partes[5]);
					int idJuego = Integer.parseInt(partes[6]);
					Juego juego = buscarJuegoPorId(juegos, idJuego);
					
					if(juego != null){
						if(tipo.equals("TorneoAmistoso")){
							boolean permiteEquipos = Boolean.parseBoolean(partes[7]);
							torneos.add(new TorneoAmistoso(idTorneo, nombre, fecha, cupoMaximo, cuposFanaticos, juego, permiteEquipos));
						}
						else if(tipo.equals("TorneoCompetitivo") && partes.length >= 9){
							String nivelMinimo = partes[7];
							double premioMayor = Double.parseDouble(partes[8]);
							torneos.add(new TorneoCompetitivo(idTorneo, nombre, fecha, cupoMaximo, cuposFanaticos, juego, nivelMinimo, premioMayor));
						}
					}
				}
				
				linea = lector.readLine();
			}
			
			lector.close();
		}
		catch(Exception e){
			System.out.println("Error cargando torneos");
		}
		
		return torneos;
	}
	
	public ArrayList<Turno> cargarTurnos(){
		ArrayList<Turno> turnos = new ArrayList<Turno>();
		File archivo = new File(rutaTurnos);
		
		if(!archivo.exists()){
			return turnos;
		}
		
		try{
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			String linea = lector.readLine();
			
			while(linea != null){
				String[] partes = linea.split(";");
				
				if(partes.length >= 4){
					int idTurno = Integer.parseInt(partes[0]);
					String dia = partes[1];
					String horaInicio = partes[2];
					String horaFin = partes[3];
					
					turnos.add(new Turno(idTurno, dia, horaInicio, horaFin));
				}
				
				linea = lector.readLine();
			}
			
			lector.close();
		}
		catch(Exception e){
			System.out.println("Error cargando turnos");
		}
		
		return turnos;
	}
	
	public void guardarUsuarios(ArrayList<Usuario> usuarios){
		try{
			PrintWriter escritor = new PrintWriter(rutaUsuarios);
			
			for(Usuario usuario : usuarios){
				if(usuario instanceof Cliente){
					escritor.println("Cliente;" + usuario.getLogin() + ";" + usuario.getPassword() + ";" + usuario.getNombre());
				}
				else if(usuario instanceof Administrador){
					escritor.println("Administrador;" + usuario.getLogin() + ";" + usuario.getPassword() + ";" + usuario.getNombre());
				}
				else if(usuario instanceof Mesero){
					Mesero mesero = (Mesero) usuario;
					escritor.println("Mesero;" + mesero.getLogin() + ";" + mesero.getPassword() + ";" + mesero.getNombre() + ";" + mesero.getIdEmpleado());
				}
				else if(usuario instanceof Cocinero){
					Cocinero cocinero = (Cocinero) usuario;
					escritor.println("Cocinero;" + cocinero.getLogin() + ";" + cocinero.getPassword() + ";" + cocinero.getNombre() + ";" + cocinero.getIdEmpleado());
				}
			}
			
			escritor.close();
		}
		catch(Exception e){
			System.out.println("Error guardando usuarios");
		}
	}
	
	public void guardarJuegos(ArrayList<Juego> juegos){
		try{
			PrintWriter escritor = new PrintWriter(rutaJuegos);
			
			for(Juego juego : juegos){
				escritor.println(juego.getIdJuego() + ";" + juego.getNombre() + ";" + juego.getGenero() + ";" + juego.getDificultad() + ";" + juego.getMinJugadores() + ";" + juego.getMaxJugadores() + ";" + juego.esParaVenta());
			}
			
			escritor.close();
		}
		catch(Exception e){
			System.out.println("Error guardando juegos");
		}
	}
	
	public void guardarProductos(ArrayList<Producto> productos){
		try{
			PrintWriter escritor = new PrintWriter(rutaProductos);
			
			for(Producto producto : productos){
				if(producto instanceof ProductoCafeteria){
					ProductoCafeteria productoCafeteria = (ProductoCafeteria) producto;
					escritor.println("ProductoCafeteria;" + productoCafeteria.getIdProducto() + ";" + productoCafeteria.getNombre() + ";" + productoCafeteria.getPrecio() + ";" + productoCafeteria.getCantidadDisponible() + ";" + productoCafeteria.getCategoria());
				}
				else if(producto instanceof JuegoVenta){
					JuegoVenta juegoVenta = (JuegoVenta) producto;
					escritor.println("JuegoVenta;" + juegoVenta.getIdProducto() + ";" + juegoVenta.getNombre() + ";" + juegoVenta.getPrecio() + ";" + juegoVenta.getCantidadDisponible() + ";" + juegoVenta.getEditorial() + ";" + juegoVenta.getEdadRecomendada());
				}
			}
			
			escritor.close();
		}
		catch(Exception e){
			System.out.println("Error guardando productos");
		}
	}
	
	public void guardarVentas(ArrayList<Venta> ventas){
		try{
			PrintWriter escritor = new PrintWriter(rutaVentas);
			
			for(Venta venta : ventas){
				escritor.println(venta.getIdVenta() + ";" + venta.getFecha() + ";" + venta.getCliente().getLogin() + ";" + venta.getEmpleado().getIdEmpleado() + ";" + venta.getTotal());
			}
			
			escritor.close();
		}
		catch(Exception e){
			System.out.println("Error guardando ventas");
		}
	}
	
	public void guardarTorneos(ArrayList<Torneo> torneos){
		try{
			PrintWriter escritor = new PrintWriter(rutaTorneos);
			
			for(Torneo torneo : torneos){
				if(torneo instanceof TorneoAmistoso){
					TorneoAmistoso amistoso = (TorneoAmistoso) torneo;
					escritor.println("TorneoAmistoso;" + amistoso.getIdTorneo() + ";" + amistoso.getNombre() + ";" + amistoso.getFecha() + ";" + amistoso.getCupoMaximo() + ";" + amistoso.getCuposFanaticos() + ";" + amistoso.getJuego().getIdJuego() + ";" + amistoso.permiteEquipos());
				}
				else if(torneo instanceof TorneoCompetitivo){
					TorneoCompetitivo competitivo = (TorneoCompetitivo) torneo;
					escritor.println("TorneoCompetitivo;" + competitivo.getIdTorneo() + ";" + competitivo.getNombre() + ";" + competitivo.getFecha() + ";" + competitivo.getCupoMaximo() + ";" + competitivo.getCuposFanaticos() + ";" + competitivo.getJuego().getIdJuego() + ";" + competitivo.getNivelMinimo() + ";" + competitivo.getPremioMayor());
				}
			}
			
			escritor.close();
		}
		catch(Exception e){
			System.out.println("Error guardando torneos");
		}
	}
	
	public void guardarTurnos(ArrayList<Turno> turnos){
		try{
			PrintWriter escritor = new PrintWriter(rutaTurnos);
			
			for(Turno turno : turnos){
				escritor.println(turno.getIdTurno() + ";" + turno.getDia() + ";" + turno.getHoraInicio() + ";" + turno.getHoraFin());
			}
			
			escritor.close();
		}
		catch(Exception e){
			System.out.println("Error guardando turnos");
		}
	}
	
	private Juego buscarJuegoPorId(ArrayList<Juego> juegos, int idJuego){
		for(Juego juego : juegos){
			if(juego.getIdJuego() == idJuego){
				return juego;
			}
		}
		return null;
	}
	
	private Cliente buscarClientePorLogin(ArrayList<Usuario> usuarios, String login){
		for(Usuario usuario : usuarios){
			if(usuario instanceof Cliente && usuario.getLogin().equals(login)){
				return (Cliente) usuario;
			}
		}
		return null;
	}
	
	private Empleado buscarEmpleadoPorId(ArrayList<Usuario> usuarios, int idEmpleado){
		for(Usuario usuario : usuarios){
			if(usuario instanceof Empleado){
				Empleado empleado = (Empleado) usuario;
				
				if(empleado.getIdEmpleado() == idEmpleado){
					return empleado;
				}
			}
		}
		return null;
	}
	
	public String getRutaUsuarios(){
		return rutaUsuarios;
	}
	
	public String getRutaJuegos(){
		return rutaJuegos;
	}
	
	public String getRutaProductos(){
		return rutaProductos;
	}
	
	public String getRutaVentas(){
		return rutaVentas;
	}
	
	public String getRutaTorneos(){
		return rutaTorneos;
	}
	
	public String getRutaTurnos(){
		return rutaTurnos;
	}
}