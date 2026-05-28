package interfaz;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Scanner;
import modelo.DulcesNDados;

public abstract class Interfaz extends JPanel {

    protected DulcesNDados sistema;

    public Interfaz(DulcesNDados sistema) {

        this.sistema = sistema;
    }

    public int leerEntero(String mensaje) {

        int valor = -1;
        boolean valido = false;

        while(!valido) {

            try {

                String texto = JOptionPane.showInputDialog(
                    this,
                    mensaje
                );

                valor = Integer.parseInt(texto);

                valido = true;
            }
            catch(NumberFormatException e) {

                JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar un numero entero"
                );
            }
        }

        return valor;
    }

    public String leerTexto(String mensaje) {

        return JOptionPane.showInputDialog(
            this,
            mensaje
        );
    }

    public void mostrarMensaje(String mensaje) {

        JOptionPane.showMessageDialog(
            this,
            mensaje
        );
    }
}
