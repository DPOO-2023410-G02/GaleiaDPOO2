package InterfazGraficaPrincipal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaFuncionesCliente extends JFrame
{
    public VentanaFuncionesCliente()
    {
        setSize(750, 650);
        setTitle("Funciones del Cliente");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblMensaje = new JLabel("Bienvenido, Cliente");
        add(lblMensaje, BorderLayout.CENTER);

        JButton btnSalir = new JButton("SALIR");
        add(btnSalir, BorderLayout.SOUTH);

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
                ventanaPrincipal.setLocationRelativeTo(null);
                ventanaPrincipal.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args)
    {
        VentanaFuncionesCliente ventana = new VentanaFuncionesCliente();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
