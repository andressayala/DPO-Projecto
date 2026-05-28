package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modelo.DulcesNDados;
import modelo.Empleado;
import modelo.Torneo;
import modelo.Turno;

public class InterfazEmpleado extends Interfaz {

    private Empleado empleado;

    private JTextArea areaTexto;

    public InterfazEmpleado(
            DulcesNDados sistema,
            Empleado empleado) {

        super(sistema);

        this.empleado = empleado;

        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();

        panelBotones.setLayout(
                new GridLayout(0,1)
        );

        JButton btnTorneos =
                new JButton(
                        "Ver torneos"
                );

        JButton btnTurnos =
                new JButton(
                        "Ver turnos"
                );

        JButton btnSolicitud =
                new JButton(
                        "Solicitar cambio turno"
                );

        JButton btnInscribirse =
                new JButton(
                        "Inscribirse torneo"
                );

        JButton btnInscritos =
                new JButton(
                        "Ver torneos inscritos"
                );

        panelBotones.add(btnTorneos);
        panelBotones.add(btnTurnos);
        panelBotones.add(btnSolicitud);
        panelBotones.add(btnInscribirse);
        panelBotones.add(btnInscritos);

        add(panelBotones, BorderLayout.WEST);

        areaTexto = new JTextArea();

        JScrollPane scroll =
                new JScrollPane(areaTexto);

        add(scroll, BorderLayout.CENTER);

        btnTorneos.addActionListener(
                e -> verTorneos()
        );

        btnTurnos.addActionListener(
                e -> verTurnos()
        );

        btnSolicitud.addActionListener(
                e -> solicitarCambioTurno()
        );

        btnInscribirse.addActionListener(
                e -> inscribirseATorneo()
        );

        btnInscritos.addActionListener(
                e -> verTorneosInscritos()
        );
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

    private void verTurnos() {

        areaTexto.setText(
                "TURNOS\n\n"
        );

        for(Turno turno :
                sistema.getTurnos()) {

            areaTexto.append(
                    "Turno "
                    + turno.getIdTurno()
                    + " Dia: "
                    + turno.getDia()
                    + " Hora: "
                    + turno.getHoraInicio()
                    + " - "
                    + turno.getHoraFin()
                    + "\n"
            );
        }
    }

    private void solicitarCambioTurno() {

        int idTurnoActual =
                leerEntero(
                        "Id turno actual"
                );

        int idTurnoSolicitado =
                leerEntero(
                        "Id turno solicitado"
                );

        String motivo =
                leerTexto(
                        "Motivo"
                );

        boolean resultado =
                sistema.solicitarCambioTurno(
                        empleado.getIdEmpleado(),
                        idTurnoActual,
                        idTurnoSolicitado,
                        motivo
                );

        if(resultado) {

            mostrarMensaje(
                    "Solicitud registrada"
            );
        }
        else {

            mostrarMensaje(
                    "No se pudo registrar"
            );
        }
    }

    private void inscribirseATorneo() {

        int idTorneo =
                leerEntero(
                        "Id torneo"
                );

        boolean resultado =
                sistema.inscribirEmpleadoTorneo(
                        empleado.getIdEmpleado(),
                        idTorneo
                );

        if(resultado) {

            mostrarMensaje(
                    "Empleado inscrito"
            );
        }
        else {

            mostrarMensaje(
                    "No se pudo inscribir"
            );
        }
    }

    private void verTorneosInscritos() {

        areaTexto.setText(
                "TORNEOS INSCRITOS\n\n"
        );

        for(Torneo torneo :
                sistema.getTorneos()) {

            if(torneo
                    .getEmpleadosParticipantes()
                    .contains(empleado)) {

                areaTexto.append(
                        "Torneo "
                        + torneo.getIdTorneo()
                        + " "
                        + torneo.getNombre()
                        + " Fecha: "
                        + torneo.getFecha()
                        + "\n"
                );
            }
        }
    }
}