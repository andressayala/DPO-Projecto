package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelo.Administrador;
import modelo.Cliente;
import modelo.DulcesNDados;
import modelo.Empleado;
import modelo.Usuario;

public class VentanaPrincipal extends JFrame {

    private DulcesNDados sistema;

    private JTextField campoLogin;

    private JTextField campoPassword;

    public VentanaPrincipal() {

        sistema = new DulcesNDados();

        mostrarLogin();
    }

    private void mostrarLogin() {

        setLayout(new BorderLayout());

        JPanel panelCentral =
                new JPanel();

        panelCentral.setLayout(
                new GridLayout(5,2)
        );

        JLabel lblTitulo =
                new JLabel(
                        "DULCES Y DADOS"
                );

        JLabel lblLogin =
                new JLabel(
                        "Login:"
                );

        JLabel lblPassword =
                new JLabel(
                        "Password:"
                );

        campoLogin =
                new JTextField();

        campoPassword =
                new JTextField();

        JButton btnAdmin =
                new JButton(
                        "Ingresar como administrador"
                );

        JButton btnCliente =
                new JButton(
                        "Ingresar como cliente"
                );

        JButton btnEmpleado =
                new JButton(
                        "Ingresar como empleado"
                );

        panelCentral.add(lblLogin);
        panelCentral.add(campoLogin);

        panelCentral.add(lblPassword);
        panelCentral.add(campoPassword);

        panelCentral.add(btnAdmin);
        panelCentral.add(btnCliente);

        panelCentral.add(
                new JLabel()
        );

        panelCentral.add(btnEmpleado);

        add(lblTitulo, BorderLayout.NORTH);

        add(panelCentral, BorderLayout.CENTER);

        btnAdmin.addActionListener(
                e -> loginAdministrador()
        );

        btnCliente.addActionListener(
                e -> loginCliente()
        );

        btnEmpleado.addActionListener(
                e -> loginEmpleado()
        );

        setTitle("Dulces y Dados");

        setSize(500,300);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setVisible(true);
    }

    private void loginAdministrador() {

        Usuario usuario =
                sistema.autenticarUsuario(
                        campoLogin.getText(),
                        campoPassword.getText()
                );

        if(usuario instanceof Administrador) {

            cambiarPanel(

                    new InterfazAdministrador(
                            sistema,
                            (Administrador) usuario
                    ),

                    "Administrador",
                    usuario.getNombre()
            );
        }
        else {

            mostrarError();
        }
    }

    private void loginCliente() {

        Usuario usuario =
                sistema.autenticarUsuario(
                        campoLogin.getText(),
                        campoPassword.getText()
                );

        if(usuario instanceof Cliente) {

            cambiarPanel(

                    new InterfazCliente(
                            sistema,
                            (Cliente) usuario
                    ),

                    "Cliente",
                    usuario.getNombre()
            );
        }
        else {

            mostrarError();
        }
    }

    private void loginEmpleado() {

        Usuario usuario =
                sistema.autenticarUsuario(
                        campoLogin.getText(),
                        campoPassword.getText()
                );

        if(usuario instanceof Empleado) {

            cambiarPanel(

                    new InterfazEmpleado(
                            sistema,
                            (Empleado) usuario
                    ),

                    "Empleado",
                    usuario.getNombre()
            );
        }
        else {

            mostrarError();
        }
    }

    private void cambiarPanel(
            JPanel panel,
            String tipo,
            String nombre) {

        JPanel principal =
                new JPanel();

        principal.setLayout(
                new BorderLayout()
        );

        JLabel etiqueta =
                new JLabel(

                        "Sesion iniciada como: "
                        + tipo
                        + " | Usuario: "
                        + nombre
                );

        principal.add(
                etiqueta,
                BorderLayout.NORTH
        );

        principal.add(
                panel,
                BorderLayout.CENTER
        );

        setContentPane(principal);

        revalidate();

        repaint();
    }

    private void mostrarError() {

        JOptionPane.showMessageDialog(
                this,
                "Credenciales invalidas"
        );
    }

    public static void main(String[] args) {

        new VentanaPrincipal();
    }
}