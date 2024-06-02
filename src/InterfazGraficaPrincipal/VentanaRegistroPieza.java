package InterfazGraficaPrincipal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistroPieza extends JFrame {
    private JTextField txtNombre;
    private JTextField txtAutor;
    private JTextField txtAño;
    private JTextField txtEstilo;
    private JTextField txtPrecio;
    private JTextField txtDescripcion;
    private JButton btnRegistrar;

    public VentanaRegistroPieza() {
        setSize(400, 400);
        setTitle("Registrar Pieza Nueva");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();

        JLabel lblAutor = new JLabel("Autor:");
        txtAutor = new JTextField();

        JLabel lblAño = new JLabel("Año:");
        txtAño = new JTextField();

        JLabel lblEstilo = new JLabel("Estilo:");
        txtEstilo = new JTextField();

        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField();

        JLabel lblDescripcion = new JLabel("Descripción:");
        txtDescripcion = new JTextField();

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para guardar la pieza
                String nombre = txtNombre.getText();
                String autor = txtAutor.getText();
                String año = txtAño.getText();
                String estilo = txtEstilo.getText();
                String precio = txtPrecio.getText();
                String descripcion = txtDescripcion.getText();

                // Aquí puedes agregar la lógica para almacenar esta información
                JOptionPane.showMessageDialog(null, "Pieza registrada con éxito!");

                // Cierra la ventana de registro de piezas
                dispose();
            }
        });

        add(lblNombre);
        add(txtNombre);
        add(lblAutor);
        add(txtAutor);
        add(lblAño);
        add(txtAño);
        add(lblEstilo);
        add(txtEstilo);
        add(lblPrecio);
        add(txtPrecio);
        add(lblDescripcion);
        add(txtDescripcion);
        add(new JLabel()); // Espacio vacío para alineación
        add(btnRegistrar);
    }
}