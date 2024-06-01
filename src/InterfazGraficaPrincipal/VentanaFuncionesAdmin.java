package InterfazGraficaPrincipal;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaFuncionesAdmin extends JFrame
{
    public VentanaFuncionesAdmin()
    {
        setSize(750, 650);
        setTitle("Funciones del Administrador");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblMensaje = new JLabel("Bienvenido, Administrador");
        add(lblMensaje);
    }

    public static void main(String[] args)
    {
        VentanaFuncionesAdmin ventana = new VentanaFuncionesAdmin();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}


