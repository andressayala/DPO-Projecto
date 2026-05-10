package modelo;

import java.util.ArrayList;
import persistencia.ArchivoDatos;

public class DulcesNDados {
	
	private ArrayList<Usuario> usuarios;
	private ArrayList<Juego> juegos;
	private ArrayList<CopiaJuego> copiasJuegos;
	private ArrayList<Producto> productos;
	private ArrayList<Venta> ventas;
	private ArrayList<PrestamoJuego> prestamos;
	private ArrayList<Torneo> torneos;
	private ArrayList<Turno> turnos;
	private ArrayList<AsignacionTurno> asignacionesTurno;
	private ArrayList<SolicitudCambioTurno> solicitudesCambioTurno;
	private ArrayList<InscripcionTorneo> inscripcionesTorneo;
	private ArrayList<FavoritoJuego> favoritosJuegos;
	private ArchivoDatos archivoDatos;
	
	public DulcesNDados(){
		this.usuarios = new ArrayList<Usuario>();
		this.juegos = new ArrayList<Juego>();
		this.copiasJuegos = new ArrayList<CopiaJuego>();
		this.productos = new ArrayList<Producto>();
		this.ventas = new ArrayList<Venta>();
		this.prestamos = new ArrayList<PrestamoJuego>();
		this.torneos = new ArrayList<Torneo>();
		this.turnos = new ArrayList<Turno>();
		this.asignacionesTurno = new ArrayList<AsignacionTurno>();
		this.solicitudesCambioTurno = new ArrayList<SolicitudCambioTurno>();
		this.inscripcionesTorneo = new ArrayList<InscripcionTorneo>();
		this.favoritosJuegos = new ArrayList<FavoritoJuego>();
		this.archivoDatos = new ArchivoDatos();
		
		cargarDatos();
		
		if(usuarios.size() == 0){
			crearDatosIniciales();
			guardarDatos();
		}
		
		if(copiasJuegos.size() == 0){
			crearCopiasIniciales();
		}
		
		if(asignacionesTurno.size() == 0){
			crearAsignacionesIniciales();
		}
		
		if(favoritosJuegos.size() == 0){
			crearFavoritosIniciales();
		}
	}
	
	private void crearDatosIniciales(){
		Administrador admin = new Administrador("admin", "admin", "admin");
		Cliente cliente = new Cliente("cliente", "cliente", "cliente");
		Mesero mesero = new Mesero("mesero", "mesero", "mesero", 1);
		Cocinero cocinero = new Cocinero("cocinero", "cocinero", "cocinero", 2);
		
		usuarios.add(admin);
		usuarios.add(cliente);
		usuarios.add(mesero);
		usuarios.add(cocinero);
		
		Juego juego1 = new Juego(1, "juego1", "genero", "facil", 1, 1, false);
		Juego juego2 = new Juego(2, "juego2", "genero", "facil", 1, 1, false);
		
		juegos.add(juego1);
		juegos.add(juego2);
		
		crearCopiasIniciales();
		
		productos.add(new ProductoCafeteria(1, "producto1", 1, 1, "categoria"));
		productos.add(new ProductoCafeteria(2, "producto2", 1, 1, "categoria"));
		productos.add(new JuegoVenta(3, "juego", 1, 1, "editorial", 1));
		
		Turno turno1 = new Turno(1, "dia", "1", "1");
		Turno turno2 = new Turno(2, "otro", "1", "1");
		
		turnos.add(turno1);
		turnos.add(turno2);
		
		Torneo torneo = new TorneoAmistoso(1, "torneo", "dia", 1, 1, juego1, false);
		torneo.agregarPremio(new Premio(1, "premio", 1, 1));
		torneos.add(torneo);
	}
	
	private void crearCopiasIniciales(){
		copiasJuegos.add(new CopiaJuego(1, "estado"));
		copiasJuegos.add(new CopiaJuego(2, "estado"));
		copiasJuegos.add(new CopiaJuego(3, "estado"));
	}
	
	private void crearAsignacionesIniciales(){
		Empleado empleado1 = buscarEmpleadoPorId(1);
		Empleado empleado2 = buscarEmpleadoPorId(2);
		Turno turno1 = buscarTurnoPorId(1);
		Turno turno2 = buscarTurnoPorId(2);
		
		if(empleado1 != null && turno1 != null){
			asignacionesTurno.add(new AsignacionTurno(1, empleado1, turno1));
		}
		
		if(empleado2 != null && turno2 != null){
			asignacionesTurno.add(new AsignacionTurno(2, empleado2, turno2));
		}
	}
	
	private void crearFavoritosIniciales(){
		Cliente cliente = buscarClientePorLogin("cliente");
		Juego juego = buscarJuegoPorId(1);
		
		if(cliente != null && juego != null){
			favoritosJuegos.add(new FavoritoJuego(cliente, juego, "dia"));
		}
	}
	
	public Usuario autenticarUsuario(String login, String password){
		for(Usuario usuario : usuarios){
			if(usuario.autenticar(login, password)){
				return usuario;
			}
		}
		return null;
	}
	
	public Cliente registrarCliente(String login, String password, String nombre){
		if(buscarUsuarioPorLogin(login) != null){
			return null;
		}
		
		Cliente cliente = new Cliente(login, password, nombre);
		usuarios.add(cliente);
		return cliente;
	}
	
	public Mesero registrarMesero(String login, String password, String nombre, int idEmpleado){
		if(buscarUsuarioPorLogin(login) != null){
			return null;
		}
		
		if(buscarEmpleadoPorId(idEmpleado) != null){
			return null;
		}
		
		Mesero mesero = new Mesero(login, password, nombre, idEmpleado);
		usuarios.add(mesero);
		return mesero;
	}
	
	public Cocinero registrarCocinero(String login, String password, String nombre, int idEmpleado){
		if(buscarUsuarioPorLogin(login) != null){
			return null;
		}
		
		if(buscarEmpleadoPorId(idEmpleado) != null){
			return null;
		}
		
		Cocinero cocinero = new Cocinero(login, password, nombre, idEmpleado);
		usuarios.add(cocinero);
		return cocinero;
	}
	
	public boolean marcarJuegoFavorito(String loginCliente, int idJuego, String fecha){
		Cliente cliente = buscarClientePorLogin(loginCliente);
		Juego juego = buscarJuegoPorId(idJuego);
		
		if(cliente == null || juego == null){
			return false;
		}
		
		if(esJuegoFavorito(cliente, juego)){
			return false;
		}
		
		FavoritoJuego favorito = new FavoritoJuego(cliente, juego, fecha);
		favoritosJuegos.add(favorito);
		return true;
	}
	
	public boolean registrarPrestamo(String loginCliente, int idCopia, String fecha){
		Cliente cliente = buscarClientePorLogin(loginCliente);
		CopiaJuego copia = buscarCopiaPorId(idCopia);
		
		if(cliente == null || copia == null){
			return false;
		}
		
		if(!copia.estaDisponible()){
			return false;
		}
		
		int idPrestamo = prestamos.size() + 1;
		PrestamoJuego prestamo = new PrestamoJuego(idPrestamo, cliente, copia, fecha);
		prestamos.add(prestamo);
		return true;
	}
	
	public boolean finalizarPrestamo(int idPrestamo, String fechaFin){
		PrestamoJuego prestamo = buscarPrestamoPorId(idPrestamo);
		
		if(prestamo == null){
			return false;
		}
		
		if(!prestamo.estaActivo()){
			return false;
		}
		
		prestamo.finalizarPrestamo(fechaFin);
		return true;
	}
	
	public Venta registrarVenta(String loginCliente, int idEmpleado, String fecha){
		Cliente cliente = buscarClientePorLogin(loginCliente);
		Empleado empleado = buscarEmpleadoPorId(idEmpleado);
		
		if(cliente == null || empleado == null){
			return null;
		}
		
		Venta venta = new Venta(ventas.size() + 1, cliente, empleado, fecha);
		ventas.add(venta);
		return venta;
	}
	
	public boolean agregarProductoAVenta(int idVenta, int idProducto, int cantidad){
		Venta venta = buscarVentaPorId(idVenta);
		Producto producto = buscarProductoPorId(idProducto);
		
		if(venta == null || producto == null){
			return false;
		}
		
		return venta.agregarDetalle(producto, cantidad);
	}
	
	public void registrarTorneo(Torneo torneo){
		torneos.add(torneo);
	}
	
	public boolean inscribirClienteTorneo(String loginCliente, int idTorneo, boolean esFanatico){
		Cliente cliente = buscarClientePorLogin(loginCliente);
		Torneo torneo = buscarTorneoPorId(idTorneo);
		
		if(cliente == null || torneo == null){
			return false;
		}
		
		if(!torneo.getEstado().equals("Abierto")){
			return false;
		}
		
		if(contarCuposClienteEnTorneo(cliente, torneo) >= 3){
			return false;
		}
		
		if(!hayCupoEnTorneo(torneo)){
			return false;
		}
		
		boolean cupoFanatico = false;
		
		if(esFanatico && hayCupoFanatico(torneo)){
			cupoFanatico = true;
		}
		else if(!hayCupoRegular(torneo)){
			return false;
		}
		
		InscripcionTorneo inscripcion = new InscripcionTorneo(inscripcionesTorneo.size() + 1, cliente, torneo, torneo.getFecha(), cupoFanatico);
		inscripcionesTorneo.add(inscripcion);
		return true;
	}
	
	public boolean inscribirClienteTorneo(String loginCliente, int idTorneo, int cantidadParticipantes){
		Cliente cliente = buscarClientePorLogin(loginCliente);
		Torneo torneo = buscarTorneoPorId(idTorneo);
		
		if(cliente == null || torneo == null){
			return false;
		}
		
		if(cantidadParticipantes < 1 || cantidadParticipantes > 3){
			return false;
		}
		
		if(!torneo.getEstado().equals("Abierto")){
			return false;
		}
		
		if(contarCuposClienteEnTorneo(cliente, torneo) + cantidadParticipantes > 3){
			return false;
		}
		
		if(contarCuposTorneo(torneo) + cantidadParticipantes > torneo.getCupoMaximo()){
			return false;
		}
		
		boolean esFavorito = esJuegoFavorito(cliente, torneo.getJuego());
		
		for(int i = 0; i < cantidadParticipantes; i++){
			boolean cupoFanatico = false;
			
			if(esFavorito && hayCupoFanatico(torneo)){
				cupoFanatico = true;
			}
			else if(!hayCupoRegular(torneo)){
				return false;
			}
			
			InscripcionTorneo inscripcion = new InscripcionTorneo(inscripcionesTorneo.size() + 1, cliente, torneo, torneo.getFecha(), cupoFanatico);
			inscripcionesTorneo.add(inscripcion);
		}
		
		return true;
	}
	
	public boolean cancelarInscripcion(String loginCliente, int idTorneo){
		Cliente cliente = buscarClientePorLogin(loginCliente);
		Torneo torneo = buscarTorneoPorId(idTorneo);
		
		if(cliente == null || torneo == null){
			return false;
		}
		
		boolean cancelo = false;
		
		for(InscripcionTorneo inscripcion : inscripcionesTorneo){
			if(inscripcion.getCliente() == cliente && inscripcion.getTorneo() == torneo && inscripcion.estaActiva()){
				inscripcion.cancelar();
				cancelo = true;
			}
		}
		
		return cancelo;
	}
	
	public boolean solicitarCambioTurno(int idEmpleado, int idTurnoActual, int idTurnoSolicitado, String motivo){
		Empleado empleado = buscarEmpleadoPorId(idEmpleado);
		Turno turnoActual = buscarTurnoPorId(idTurnoActual);
		Turno turnoSolicitado = buscarTurnoPorId(idTurnoSolicitado);
		
		if(empleado == null || turnoActual == null || turnoSolicitado == null){
			return false;
		}
		
		SolicitudCambioTurno solicitud = new SolicitudCambioTurno(solicitudesCambioTurno.size() + 1, empleado, turnoActual, turnoSolicitado, motivo);
		solicitudesCambioTurno.add(solicitud);
		return true;
	}
	
	public boolean aprobarSolicitudCambioTurno(int idSolicitud){
		SolicitudCambioTurno solicitud = buscarSolicitudPorId(idSolicitud);
		
		if(solicitud == null){
			return false;
		}
		
		solicitud.aprobar();
		return true;
	}
	
	public boolean asignarEmpleadoATorneo(int idEmpleado, int idTorneo){
		Empleado empleado = buscarEmpleadoPorId(idEmpleado);
		Torneo torneo = buscarTorneoPorId(idTorneo);
		
		if(empleado == null || torneo == null){
			return false;
		}
		
		if(!empleadoTieneTurnoParaTorneo(empleado, torneo)){
			return false;
		}
		
		return torneo.agregarEmpleadoApoyo(empleado);
	}
	
	public boolean inscribirEmpleadoTorneo(int idEmpleado, int idTorneo){
		Empleado empleado = buscarEmpleadoPorId(idEmpleado);
		Torneo torneo = buscarTorneoPorId(idTorneo);
		
		if(empleado == null || torneo == null){
			return false;
		}
		
		if(!torneo.getEstado().equals("Abierto")){
			return false;
		}
		
		if(empleadoTieneTurnoParaTorneo(empleado, torneo)){
			return false;
		}
		
		if(!hayCupoEnTorneo(torneo)){
			return false;
		}
		
		return torneo.agregarEmpleadoParticipante(empleado);
	}
	
	public boolean registrarPremioTorneo(int idTorneo, String descripcion, double valor, int puesto){
		Torneo torneo = buscarTorneoPorId(idTorneo);
		
		if(torneo == null){
			return false;
		}
		
		Premio premio = new Premio(torneo.getPremios().size() + 1, descripcion, valor, puesto);
		return torneo.agregarPremio(premio);
	}
	
	public boolean guardarBonoDescuentoCliente(String loginCliente, int idTorneo){
		Cliente cliente = buscarClientePorLogin(loginCliente);
		Torneo torneo = buscarTorneoPorId(idTorneo);
		
		if(cliente == null || torneo == null){
			return false;
		}
		
		if(!(torneo instanceof TorneoAmistoso)){
			return false;
		}
		
		cliente.guardarBonoDescuento();
		return true;
	}
	
	public void cargarDatos(){
		usuarios = archivoDatos.cargarUsuarios();
		juegos = archivoDatos.cargarJuegos();
		productos = archivoDatos.cargarProductos();
		ventas = archivoDatos.cargarVentas(usuarios);
		torneos = archivoDatos.cargarTorneos(juegos);
		turnos = archivoDatos.cargarTurnos();
	}
	
	public void guardarDatos(){
		archivoDatos.guardarUsuarios(usuarios);
		archivoDatos.guardarJuegos(juegos);
		archivoDatos.guardarProductos(productos);
		archivoDatos.guardarVentas(ventas);
		archivoDatos.guardarTorneos(torneos);
		archivoDatos.guardarTurnos(turnos);
	}
	
	private Usuario buscarUsuarioPorLogin(String login){
		for(Usuario usuario : usuarios){
			if(usuario.getLogin().equals(login)){
				return usuario;
			}
		}
		return null;
	}
	
	private Cliente buscarClientePorLogin(String login){
		Usuario usuario = buscarUsuarioPorLogin(login);
		
		if(usuario instanceof Cliente){
			return (Cliente) usuario;
		}
		
		return null;
	}
	
	private Empleado buscarEmpleadoPorId(int idEmpleado){
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
	
	private Juego buscarJuegoPorId(int idJuego){
		for(Juego juego : juegos){
			if(juego.getIdJuego() == idJuego){
				return juego;
			}
		}
		return null;
	}
	
	private CopiaJuego buscarCopiaPorId(int idCopia){
		for(CopiaJuego copia : copiasJuegos){
			if(copia.getIdCopia() == idCopia){
				return copia;
			}
		}
		return null;
	}
	
	private PrestamoJuego buscarPrestamoPorId(int idPrestamo){
		for(PrestamoJuego prestamo : prestamos){
			if(prestamo.getIdPrestamo() == idPrestamo){
				return prestamo;
			}
		}
		return null;
	}
	
	private Venta buscarVentaPorId(int idVenta){
		for(Venta venta : ventas){
			if(venta.getIdVenta() == idVenta){
				return venta;
			}
		}
		return null;
	}
	
	private Producto buscarProductoPorId(int idProducto){
		for(Producto producto : productos){
			if(producto.getIdProducto() == idProducto){
				return producto;
			}
		}
		return null;
	}
	
	private Torneo buscarTorneoPorId(int idTorneo){
		for(Torneo torneo : torneos){
			if(torneo.getIdTorneo() == idTorneo){
				return torneo;
			}
		}
		return null;
	}
	
	private Turno buscarTurnoPorId(int idTurno){
		for(Turno turno : turnos){
			if(turno.getIdTurno() == idTurno){
				return turno;
			}
		}
		return null;
	}
	
	private SolicitudCambioTurno buscarSolicitudPorId(int idSolicitud){
		for(SolicitudCambioTurno solicitud : solicitudesCambioTurno){
			if(solicitud.getIdSolicitud() == idSolicitud){
				return solicitud;
			}
		}
		return null;
	}
	
	private boolean hayCupoEnTorneo(Torneo torneo){
		return contarCuposTorneo(torneo) < torneo.getCupoMaximo();
	}
	
	private int contarCuposTorneo(Torneo torneo){
		int total = torneo.getEmpleadosParticipantes().size();
		
		for(InscripcionTorneo inscripcion : inscripcionesTorneo){
			if(inscripcion.getTorneo() == torneo && inscripcion.estaActiva()){
				total++;
			}
		}
		
		return total;
	}
	
	private int contarCuposClienteEnTorneo(Cliente cliente, Torneo torneo){
		int total = 0;
		
		for(InscripcionTorneo inscripcion : inscripcionesTorneo){
			if(inscripcion.getCliente() == cliente && inscripcion.getTorneo() == torneo && inscripcion.estaActiva()){
				total++;
			}
		}
		
		return total;
	}
	
	private boolean hayCupoFanatico(Torneo torneo){
		int fanaticos = 0;
		
		for(InscripcionTorneo inscripcion : inscripcionesTorneo){
			if(inscripcion.getTorneo() == torneo && inscripcion.estaActiva() && inscripcion.esFanatico()){
				fanaticos++;
			}
		}
		
		return fanaticos < torneo.getCuposFanaticos();
	}
	
	private boolean hayCupoRegular(Torneo torneo){
		int cuposRegulares = torneo.getCupoMaximo() - torneo.getCuposFanaticos();
		int ocupadosRegulares = torneo.getEmpleadosParticipantes().size();
		
		for(InscripcionTorneo inscripcion : inscripcionesTorneo){
			if(inscripcion.getTorneo() == torneo && inscripcion.estaActiva() && !inscripcion.esFanatico()){
				ocupadosRegulares++;
			}
		}
		
		return ocupadosRegulares < cuposRegulares;
	}
	
	private boolean esJuegoFavorito(Cliente cliente, Juego juego){
		for(FavoritoJuego favorito : favoritosJuegos){
			if(favorito.getCliente() == cliente && favorito.getJuego() == juego){
				return true;
			}
		}
		return false;
	}
	
	private boolean empleadoTieneTurnoParaTorneo(Empleado empleado, Torneo torneo){
		for(AsignacionTurno asignacion : asignacionesTurno){
			if(asignacion.getEmpleado() == empleado && asignacion.estaActiva()){
				if(asignacion.getTurno().coincideCon(torneo.getFecha())){
					return true;
				}
			}
		}
		return false;
	}
	
	public ArrayList<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public ArrayList<Juego> getJuegos(){
		return juegos;
	}
	
	public ArrayList<CopiaJuego> getCopiasJuegos(){
		return copiasJuegos;
	}
	
	public ArrayList<Producto> getProductos(){
		return productos;
	}
	
	public ArrayList<Venta> getVentas(){
		return ventas;
	}
	
	public ArrayList<PrestamoJuego> getPrestamos(){
		return prestamos;
	}
	
	public ArrayList<Torneo> getTorneos(){
		return torneos;
	}
	
	public ArrayList<Turno> getTurnos(){
		return turnos;
	}
	
	public ArrayList<AsignacionTurno> getAsignacionesTurno(){
		return asignacionesTurno;
	}
	
	public ArrayList<SolicitudCambioTurno> getSolicitudesCambioTurno(){
		return solicitudesCambioTurno;
	}
	
	public ArrayList<InscripcionTorneo> getInscripcionesTorneo(){
		return inscripcionesTorneo;
	}
	
	public ArrayList<FavoritoJuego> getFavoritosJuegos(){
		return favoritosJuegos;
	}
}