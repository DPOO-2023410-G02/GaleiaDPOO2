package InterfazGraficaPrincipal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Model.Compra;
import Model.GaleriaDeArte;
import Pieza.Pieza;

public class VentanaFuncionesAdmin extends JFrame {

    public VentanaFuncionesAdmin() {
        setSize(850, 650);
        setTitle("Funciones del Administrador");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Hola Administrador");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo, BorderLayout.NORTH);

        // Crear JComboBox con las opciones
        String[] opciones = {"Ver historial compras", "Ver piezas", "Ver valor total de colección"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        JPanel panelOpciones = new JPanel();
        panelOpciones.add(new JLabel("Manejo Clientes"));
        panelOpciones.add(comboBox);
        add(panelOpciones, BorderLayout.CENTER);

        // Botones adicionales
        JButton btnVerificarConsignacion = new JButton("Verificar consignación piezas");
        JButton btnVerCatalogo = new JButton("Ver catálogo galería");
        JButton btnVerTodosClientes = new JButton("Ver todos los clientes");

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(btnVerificarConsignacion);
        panelBotones.add(btnVerCatalogo);
        panelBotones.add(btnVerTodosClientes);
        add(panelBotones, BorderLayout.WEST);

     // Acción para el JComboBox
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) comboBox.getSelectedItem();
                if (seleccion.equals("Ver historial compras")) {
                    // Solicitar login del cliente
                    String loginCliente = JOptionPane.showInputDialog(null, "Ingrese el login del cliente:");
                    if (loginCliente != null && !loginCliente.isEmpty()) {
                        // Mostrar historial de compras
                        mostrarHistorialCompras(loginCliente);
                    } else {
                        JOptionPane.showMessageDialog(null, "Login inválido.");
                    }
                } else if (seleccion.equals("Ver piezas")) {
                    // Solicitar login del cliente
                    String loginClientePieza = JOptionPane.showInputDialog(null, "Ingrese el login del cliente:");
                    if (loginClientePieza != null && !loginClientePieza.isEmpty()) {
                        // Mostrar piezas del cliente
                        mostrarPiezasCliente(loginClientePieza);
                    } else {
                        JOptionPane.showMessageDialog(null, "Login inválido.");
                    }
                } else if (seleccion.equals("Ver valor total de colección")) {
                    // Solicitar login del cliente
                    String loginClienteValor = JOptionPane.showInputDialog(null, "Ingrese el login del cliente:");
                    if (loginClienteValor != null && !loginClienteValor.isEmpty()) {
                        // Mostrar valor total de colección
                        mostrarValorColeccion(loginClienteValor);
                    } else {
                        JOptionPane.showMessageDialog(null, "Login inválido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Opción seleccionada: " + seleccion);
                }
            }
        });
        
     // Acción para el botón "Verificar consignación piezas"
        btnVerificarConsignacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar consignación de piezas
            	if ( GaleriaDeArte.getInventario() == null) {
                    JOptionPane.showMessageDialog(null, "No se hay piezas en inventario.", "Error", JOptionPane.ERROR_MESSAGE);
            	} else {
                GaleriaDeArte.getInventario().verificarConsignacionPiezas();

                // Mostrar mensaje de verificación exitosa
                JOptionPane.showMessageDialog(null, "Se verificó la consignación correctamente!");
            }}
        });
        
        // Acción para el botón "SALIR"
        JButton btnSalir = new JButton("SALIR");
        add(btnSalir, BorderLayout.SOUTH);
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
                ventanaPrincipal.setLocationRelativeTo(null);
                ventanaPrincipal.setVisible(true);
                dispose();
            }
        });
    }
    
    private void mostrarValorColeccion(String loginClienteValor) {
        // Obtener el valor total de la colección del cliente
        int valorPiezas = GaleriaDeArte.getAdministrador().getValorColeccion(loginClienteValor);

        // Mostrar el valor en un cuadro de diálogo
        JOptionPane.showMessageDialog(null, "El valor total de la colección es: " + valorPiezas, "Valor Total de Colección", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarPiezasCliente(String loginClientePieza) {
        List<Pieza> piezasCliente = GaleriaDeArte.getAdministrador().getPiezasCliente(loginClientePieza);

        // Crear modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Título");
        model.addColumn("Precio");

        // Llenar la tabla con los datos de las piezas del cliente
        for (Pieza pieza : piezasCliente) {
            model.addRow(new Object[]{pieza.getTitulo(), pieza.getPrecioCompra()});
        }

        // Crear tabla con el modelo
        JTable table = new JTable(model);

        // Mostrar la tabla en un JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Piezas del Cliente",
                JOptionPane.PLAIN_MESSAGE);
    }
    
    private void mostrarHistorialCompras(String loginCliente) {
        List<Compra> comprasCliente = GaleriaDeArte.getAdministrador().getHistorial(loginCliente);

        // Crear modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Título");
        model.addColumn("Precio");
        model.addColumn("Fecha");

        // Llenar la tabla con los datos del historial de compras
        for (Compra compra : comprasCliente) {
            model.addRow(new Object[]{compra.getPieza().getTitulo(), compra.getPrecio(), compra.getFecha()});
        }

        // Crear tabla con el modelo
        JTable table = new JTable(model);

        // Mostrar la tabla en un JOptionPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Historial de compras",
                JOptionPane.PLAIN_MESSAGE);
    }
    public static void main(String[] args) {
        VentanaFuncionesAdmin ventana = new VentanaFuncionesAdmin();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}