package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import modelo.Administrador;
import modelo.Cocinero;
import modelo.DulcesNDados;
import modelo.Empleado;
import modelo.Juego;
import modelo.Mesero;
import modelo.Premio;
import modelo.SolicitudCambioTurno;
import modelo.Torneo;
import modelo.TorneoAmistoso;
import modelo.TorneoCompetitivo;
import modelo.Usuario;
import modelo.Venta;

public class InterfazAdministrador extends Interfaz {

    private Administrador administrador;

    private JTextArea areaTexto;

    public InterfazAdministrador(
            DulcesNDados sistema,
            Administrador administrador) {

        super(sistema);

        this.administrador = administrador;

        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();

        panelBotones.setLayout(new GridLayout(0,1));

        JButton btnUsuarios = new JButton("Ver usuarios");
        JButton btnJuegos = new JButton("Ver juegos");
        JButton btnVentas = new JButton("Ver ventas");
        JButton btnTorneos = new JButton("Ver torneos");

        JButton btnCrearAmistoso =
                new JButton("Crear torneo amistoso");

        JButton btnCrearCompetitivo =
                new JButton("Crear torneo competitivo");

        JButton btnAbrir =
                new JButton("Abrir torneo");

        JButton btnCerrar =
                new JButton("Cerrar torneo");

        JButton btnSolicitudes =
                new JButton("Ver solicitudes");

        JButton btnAprobar =
                new JButton("Aprobar solicitud");

        JButton btnAsignar =
                new JButton("Asignar empleado");

        JButton btnEmpleados =
                new JButton("Ver empleados torneo");

        JButton btnPremio =
                new JButton("Registrar premio");

        JButton btnVerPremios =
                new JButton("Ver premios");

        JButton btnMesero =
                new JButton("Registrar mesero");

        JButton btnCocinero =
                new JButton("Registrar cocinero");

        panelBotones.add(btnUsuarios);
        panelBotones.add(btnJuegos);
        panelBotones.add(btnVentas);
        panelBotones.add(btnTorneos);
        panelBotones.add(btnCrearAmistoso);
        panelBotones.add(btnCrearCompetitivo);
        panelBotones.add(btnAbrir);
        panelBotones.add(btnCerrar);
        panelBotones.add(btnSolicitudes);
        panelBotones.add(btnAprobar);
        panelBotones.add(btnAsignar);
        panelBotones.add(btnEmpleados);
        panelBotones.add(btnPremio);
        panelBotones.add(btnVerPremios);
        panelBotones.add(btnMesero);
        panelBotones.add(btnCocinero);

        add(panelBotones, BorderLayout.WEST);

        areaTexto = new JTextArea();

        JScrollPane scroll =
                new JScrollPane(areaTexto);

        add(scroll, BorderLayout.CENTER);

        btnUsuarios.addActionListener(
                e -> verUsuarios());

        btnJuegos.addActionListener(
                e -> verJuegos());

        btnVentas.addActionListener(
                e -> verVentas());

        btnTorneos.addActionListener(
                e -> verTorneos());

        btnCrearAmistoso.addActionListener(
                e -> crearTorneoAmistoso());

        btnCrearCompetitivo.addActionListener(
                e -> crearTorneoCompetitivo());

        btnAbrir.addActionListener(
                e -> abrirTorneo());

        btnCerrar.addActionListener(
                e -> cerrarTorneo());

        btnSolicitudes.addActionListener(
                e -> verSolicitudesTurno());

        btnAprobar.addActionListener(
                e -> aprobarSolicitudTurno());

        btnAsignar.addActionListener(
                e -> asignarEmpleadoTorneo());

        btnEmpleados.addActionListener(
                e -> verEmpleadosTorneo());

        btnPremio.addActionListener(
                e -> registrarPremioTorneo());

        btnVerPremios.addActionListener(
                e -> verPremiosTorneo());

        btnMesero.addActionListener(
                e -> registrarMesero());

        btnCocinero.addActionListener(
                e -> registrarCocinero());
    }

    private void verUsuarios() {

        areaTexto.setText("USUARIOS\n\n");

        for(Usuario usuario : sistema.getUsuarios()) {

            areaTexto.append(
                    usuario.getLogin()
                    + " "
                    + usuario.getNombre()
                    + "\n");
        }
    }

    private void verJuegos() {

        areaTexto.setText("JUEGOS\n\n");

        for(Juego juego : sistema.getJuegos()) {

            areaTexto.append(
                    "Juego "
                    + juego.getIdJuego()
                    + " "
                    + juego.getNombre()
                    + " Genero: "
                    + juego.getGenero()
                    + " Dificultad: "
                    + juego.getDificultad()
                    + "\n");
        }
    }

    private void verVentas() {

        areaTexto.setText("VENTAS\n\n");

        for(Venta venta : sistema.getVentas()) {

            areaTexto.append(
                    "Venta "
                    + venta.getIdVenta()
                    + " Fecha: "
                    + venta.getFecha()
                    + " Total: "
                    + venta.getTotal()
                    + "\n");
        }
    }

    private void verTorneos() {

        areaTexto.setText("TORNEOS\n\n");

        for(Torneo torneo : sistema.getTorneos()) {

            areaTexto.append(
                    "Torneo "
                    + torneo.getIdTorneo()
                    + " "
                    + torneo.getNombre()
                    + " Fecha: "
                    + torneo.getFecha()
                    + " Estado: "
                    + torneo.getEstado()
                    + "\n");
        }
    }

    private void crearTorneoAmistoso() {

        int id =
                sistema.getTorneos().size() + 1;

        String nombre =
                leerTexto("Nombre del torneo");

        String fecha =
                leerTexto("Fecha del torneo");

        int cupo =
                leerEntero("Cupo maximo");

        int idJuego =
                leerEntero("Id del juego");

        String respuesta =
                leerTexto("Permite equipos?");

        Juego juego = buscarJuego(idJuego);

        if(juego == null) {

            mostrarMensaje("No existe ese juego");
            return;
        }

        int cuposFanaticos =
                (int)Math.ceil(cupo * 0.2);

        boolean permiteEquipos =
                respuesta.equalsIgnoreCase("si");

        TorneoAmistoso torneo =
                new TorneoAmistoso(
                        id,
                        nombre,
                        fecha,
                        cupo,
                        cuposFanaticos,
                        juego,
                        permiteEquipos
                );

        sistema.registrarTorneo(torneo);

        mostrarMensaje(
                "Torneo amistoso creado");
    }

    private void crearTorneoCompetitivo() {

        int id =
                sistema.getTorneos().size() + 1;

        String nombre =
                leerTexto("Nombre del torneo");

        String fecha =
                leerTexto("Fecha del torneo");

        int cupo =
                leerEntero("Cupo maximo");

        int idJuego =
                leerEntero("Id del juego");

        String nivel =
                leerTexto("Nivel minimo");

        int tarifa =
                leerEntero("Tarifa");

        Juego juego = buscarJuego(idJuego);

        if(juego == null) {

            mostrarMensaje("No existe ese juego");
            return;
        }

        int cuposFanaticos =
                (int)Math.ceil(cupo * 0.2);

        double premio =
                tarifa * cupo * 0.8;

        TorneoCompetitivo torneo =
                new TorneoCompetitivo(
                        id,
                        nombre,
                        fecha,
                        cupo,
                        cuposFanaticos,
                        juego,
                        nivel,
                        premio
                );

        sistema.registrarTorneo(torneo);

        mostrarMensaje(
                "Torneo competitivo creado");
    }

    private void abrirTorneo() {

        int id =
                leerEntero("Id torneo");

        Torneo torneo =
                buscarTorneo(id);

        if(torneo != null) {

            torneo.abrirInscripciones();

            mostrarMensaje("Torneo abierto");
        }
        else {

            mostrarMensaje(
                    "No existe torneo");
        }
    }

    private void cerrarTorneo() {

        int id =
                leerEntero("Id torneo");

        Torneo torneo =
                buscarTorneo(id);

        if(torneo != null) {

            torneo.cerrarInscripciones();

            mostrarMensaje("Torneo cerrado");
        }
        else {

            mostrarMensaje(
                    "No existe torneo");
        }
    }

    private void verSolicitudesTurno() {

        areaTexto.setText(
                "SOLICITUDES\n\n");

        for(SolicitudCambioTurno solicitud :
                sistema.getSolicitudesCambioTurno()) {

            areaTexto.append(
                    "Solicitud "
                    + solicitud.getIdSolicitud()
                    + " Empleado: "
                    + solicitud.getEmpleado()
                    .getNombre()
                    + " Estado: "
                    + solicitud.getEstado()
                    + "\n");
        }
    }

    private void aprobarSolicitudTurno() {

        int id =
                leerEntero("Id solicitud");

        boolean resultado =
                sistema.aprobarSolicitudCambioTurno(id);

        if(resultado) {

            mostrarMensaje(
                    "Solicitud aprobada");
        }
        else {

            mostrarMensaje(
                    "No se pudo aprobar");
        }
    }

    private void asignarEmpleadoTorneo() {

        int idEmpleado =
                leerEntero("Id empleado");

        int idTorneo =
                leerEntero("Id torneo");

        boolean resultado =
                sistema.asignarEmpleadoATorneo(
                        idEmpleado,
                        idTorneo);

        if(resultado) {

            mostrarMensaje(
                    "Empleado asignado");
        }
        else {

            mostrarMensaje(
                    "No se pudo asignar");
        }
    }

    private void verEmpleadosTorneo() {

        int idTorneo =
                leerEntero("Id torneo");

        Torneo torneo =
                buscarTorneo(idTorneo);

        if(torneo == null) {

            mostrarMensaje(
                    "No existe torneo");

            return;
        }

        areaTexto.setText(
                "EMPLEADOS TORNEO\n\n");

        for(Empleado empleado :
                torneo.getEmpleadosApoyo()) {

            areaTexto.append(
                    "Empleado "
                    + empleado.getIdEmpleado()
                    + " "
                    + empleado.getNombre()
                    + "\n");
        }
    }

    private void registrarPremioTorneo() {

        int idTorneo =
                leerEntero("Id torneo");

        String descripcion =
                leerTexto("Descripcion");

        int valor =
                leerEntero("Valor");

        int puesto =
                leerEntero("Puesto");

        boolean resultado =
                sistema.registrarPremioTorneo(
                        idTorneo,
                        descripcion,
                        valor,
                        puesto);

        if(resultado) {

            mostrarMensaje(
                    "Premio registrado");
        }
        else {

            mostrarMensaje(
                    "No se pudo registrar");
        }
    }

    private void verPremiosTorneo() {

        int idTorneo =
                leerEntero("Id torneo");

        Torneo torneo =
                buscarTorneo(idTorneo);

        if(torneo == null) {

            mostrarMensaje(
                    "No existe torneo");

            return;
        }

        areaTexto.setText(
                "PREMIOS\n\n");

        for(Premio premio :
                torneo.getPremios()) {

            areaTexto.append(
                    "Premio "
                    + premio.getIdPremio()
                    + " "
                    + premio.getDescripcion()
                    + " Valor: "
                    + premio.getValor()
                    + " Puesto: "
                    + premio.getPuesto()
                    + "\n");
        }
    }

    private void registrarMesero() {

        String login =
                leerTexto("Login");

        String password =
                leerTexto("Password");

        String nombre =
                leerTexto("Nombre");

        int idEmpleado =
                leerEntero("Id empleado");

        Mesero mesero =
                sistema.registrarMesero(
                        login,
                        password,
                        nombre,
                        idEmpleado);

        if(mesero != null) {

            mostrarMensaje(
                    "Mesero registrado");
        }
        else {

            mostrarMensaje(
                    "No se pudo registrar");
        }
    }

    private void registrarCocinero() {

        String login =
                leerTexto("Login");

        String password =
                leerTexto("Password");

        String nombre =
                leerTexto("Nombre");

        int idEmpleado =
                leerEntero("Id empleado");

        Cocinero cocinero =
                sistema.registrarCocinero(
                        login,
                        password,
                        nombre,
                        idEmpleado);

        if(cocinero != null) {

            mostrarMensaje(
                    "Cocinero registrado");
        }
        else {

            mostrarMensaje(
                    "No se pudo registrar");
        }
    }

    private Juego buscarJuego(int idJuego) {

        for(Juego juego :
                sistema.getJuegos()) {

            if(juego.getIdJuego() == idJuego) {

                return juego;
            }
        }

        return null;
    }

    private Torneo buscarTorneo(int idTorneo) {

        for(Torneo torneo :
                sistema.getTorneos()) {

            if(torneo.getIdTorneo() == idTorneo) {

                return torneo;
            }
        }

        return null;
    }
}