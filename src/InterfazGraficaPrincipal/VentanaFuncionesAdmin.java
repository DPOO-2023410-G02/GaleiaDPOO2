package InterfazGraficaPrincipal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
        VentanaFuncionesAdmin ventana = new VentanaFuncionesAdmin();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}


