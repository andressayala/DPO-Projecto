package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modelo.Cliente;
import modelo.CopiaJuego;
import modelo.DulcesNDados;
import modelo.InscripcionTorneo;
import modelo.Juego;
import modelo.Torneo;

public class InterfazCliente extends Interfaz {

    private Cliente cliente;

    private JTextArea areaTexto;

    public InterfazCliente(
            DulcesNDados sistema,
            Cliente cliente) {

        super(sistema);

        this.cliente = cliente;

        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();

        panelBotones.setLayout(
                new GridLayout(0,1)
        );

        JButton btnJuegos =
                new JButton(
                        "Ver juegos disponibles"
                );

        JButton btnPedir =
                new JButton(
                        "Pedir juego"
                );

        JButton btnDevolver =
                new JButton(
                        "Devolver juego"
                );

        JButton btnTorneos =
                new JButton(
                        "Ver torneos"
                );

        JButton btnInscribirse =
                new JButton(
                        "Inscribirse torneo"
                );

        JButton btnCancelar =
                new JButton(
                        "Cancelar inscripcion"
                );

        JButton btnInscripciones =
                new JButton(
                        "Ver inscripciones"
                );

        JButton btnFavorito =
                new JButton(
                        "Juego favorito"
                );

        JButton btnBono =
                new JButton(
                        "Guardar bono"
                );

        panelBotones.add(btnJuegos);
        panelBotones.add(btnPedir);
        panelBotones.add(btnDevolver);
        panelBotones.add(btnTorneos);
        panelBotones.add(btnInscribirse);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnInscripciones);
        panelBotones.add(btnFavorito);
        panelBotones.add(btnBono);

        add(panelBotones, BorderLayout.WEST);

        areaTexto = new JTextArea();

        JScrollPane scroll =
                new JScrollPane(areaTexto);

        add(scroll, BorderLayout.CENTER);

        btnJuegos.addActionListener(
                e -> verJuegosDisponibles()
        );

        btnPedir.addActionListener(
                e -> pedirJuegoPrestado()
        );

        btnDevolver.addActionListener(
                e -> devolverPrestamo()
        );

        btnTorneos.addActionListener(
                e -> verTorneos()
        );

        btnInscribirse.addActionListener(
                e -> inscribirseATorneo()
        );

        btnCancelar.addActionListener(
                e -> cancelarInscripcion()
        );

        btnInscripciones.addActionListener(
                e -> verMisInscripciones()
        );

        btnFavorito.addActionListener(
                e -> marcarJuegoFavorito()
        );

        btnBono.addActionListener(
                e -> guardarBonoDescuento()
        );
    }

    private void verJuegosDisponibles() {

        areaTexto.setText(
                "COPIAS DE JUEGOS\n\n"
        );

        for(CopiaJuego copia :
                sistema.getCopiasJuegos()) {

            areaTexto.append(
                    "Copia "
                    + copia.getIdCopia()
                    + " Estado: "
                    + copia.getEstado()
                    + " Disponible: "
                    + copia.estaDisponible()
                    + "\n"
            );
        }
    }

    private void pedirJuegoPrestado() {

        int idCopia =
                leerEntero(
                        "Id de la copia"
                );

        String fecha =
                leerTexto(
                        "Fecha del prestamo"
                );

        boolean resultado =
                sistema.registrarPrestamo(
                        cliente.getLogin(),
                        idCopia,
                        fecha
                );

        if(resultado) {

            mostrarMensaje(
                    "Prestamo registrado"
            );
        }
        else {

            mostrarMensaje(
                    "No se pudo registrar"
            );
        }
    }

    private void devolverPrestamo() {

        int idPrestamo =
                leerEntero(
                        "Id del prestamo"
                );

        String fecha =
                leerTexto(
                        "Fecha devolucion"
                );

        boolean resultado =
                sistema.finalizarPrestamo(
                        idPrestamo,
                        fecha
                );

        if(resultado) {

            mostrarMensaje(
                    "Prestamo finalizado"
            );
        }
        else {

            mostrarMensaje(
                    "No se pudo finalizar"
            );
        }
    }

    private void verTorneos() {

        areaTexto.setText(
                "TORNEOS\n\n"
        );

        for(Torneo torneo :
                sistema.getTorneos()) {

            areaTexto.append(
                    "Torneo "
                    + torneo.getIdTorneo()
                    + " "
                    + torneo.getNombre()
                    + " Fecha: "
                    + torneo.getFecha()
                    + " Estado: "
                    + torneo.getEstado()
                    + "\n"
            );
        }
    }

    private void inscribirseATorneo() {

        int idTorneo =
                leerEntero(
                        "Id torneo"
                );

        int cantidad =
                leerEntero(
                        "Cantidad cupos"
                );

        boolean resultado =
                sistema.inscribirClienteTorneo(
                        cliente.getLogin(),
                        idTorneo,
                        cantidad
                );

        if(resultado) {

            mostrarMensaje(
                    "Inscripcion registrada"
            );
        }
        else {

            mostrarMensaje(
                    "No se pudo registrar"
            );
        }
    }

    private void cancelarInscripcion() {

        int idTorneo =
                leerEntero(
                        "Id torneo"
                );

        boolean resultado =
                sistema.cancelarInscripcion(
                        cliente.getLogin(),
                        idTorneo
                );

        if(resultado) {

            mostrarMensaje(
                    "Inscripcion cancelada"
            );
        }
        else {

            mostrarMensaje(
                    "No existe inscripcion"
            );
        }
    }

    private void verMisInscripciones() {

        areaTexto.setText(
                "MIS INSCRIPCIONES\n\n"
        );

        for(InscripcionTorneo inscripcion :
                sistema.getInscripcionesTorneo()) {

            if(inscripcion.getCliente()
                    == cliente) {

                areaTexto.append(
                        "Inscripcion "
                        + inscripcion
                        .getIdInscripcion()
                        + " Torneo: "
                        + inscripcion
                        .getTorneo()
                        .getNombre()
                        + " Activa: "
                        + inscripcion
                        .estaActiva()
                        + "\n"
                );
            }
        }
    }

    private void marcarJuegoFavorito() {

        areaTexto.setText(
                "JUEGOS\n\n"
        );

        for(Juego juego :
                sistema.getJuegos()) {

            areaTexto.append(
                    "Juego "
                    + juego.getIdJuego()
                    + " "
                    + juego.getNombre()
                    + "\n"
            );
        }

        int idJuego =
                leerEntero(
                        "Id juego favorito"
                );

        String fecha =
                leerTexto(
                        "Fecha"
                );

        boolean resultado =
                sistema.marcarJuegoFavorito(
                        cliente.getLogin(),
                        idJuego,
                        fecha
                );

        if(resultado) {

            mostrarMensaje(
                    "Favorito registrado"
            );
        }
        else {

            mostrarMensaje(
                    "No se pudo registrar"
            );
        }
    }

    private void guardarBonoDescuento() {

        int idTorneo =
                leerEntero(
                        "Id torneo"
                );

        boolean resultado =
                sistema.guardarBonoDescuentoCliente(
                        cliente.getLogin(),
                        idTorneo
                );

        if(resultado) {

            mostrarMensaje(
                    "Bono guardado"
            );
        }
        else {

            mostrarMensaje(
                    "No se pudo guardar"
            );
        }
    }
}