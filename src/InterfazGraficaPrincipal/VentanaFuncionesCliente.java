package InterfazGraficaPrincipal;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Model.GaleriaDeArte;
import Pieza.Pieza;
import Usuario.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaFuncionesCliente extends JFrame {
    public VentanaFuncionesCliente() {
        setSize(750, 650);
        setTitle("Funciones del Cliente");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear el mensaje de bienvenida
        JLabel lblMensaje = new JLabel("Bienvenido, Cliente");

        // Crear el JComboBox con opciones y ponerlo en un JPanel con borde titulado
        String[] opcionesCuenta = {
            "Ver Piezas Propias", "Ver Saldo Actual", "Agregar Saldo", 
            "Registrar Pieza Nueva", "Consignar pieza Galeria"
        };

        JComboBox<String> comboBoxOpcionesCuenta = new JComboBox<>(opcionesCuenta);
        JPanel panelComboBoxCuenta = new JPanel();
        panelComboBoxCuenta.setBorder(new TitledBorder("Opciones Cuenta"));
        panelComboBoxCuenta.add(comboBoxOpcionesCuenta);

        // Crear los botones de "Compra" y "Subasta"
        JButton btnCompra = new JButton("Compra");
        JButton btnSubasta = new JButton("Subasta");

        // Panel para el mensaje, el JComboBox y los botones
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(lblMensaje);
        panelSuperior.add(panelComboBoxCuenta);
        panelSuperior.add(btnCompra);
        panelSuperior.add(btnSubasta);

        // Agregar el panel superior al norte del BorderLayout
        add(panelSuperior, BorderLayout.NORTH);

        // Acción para el JComboBox
        comboBoxOpcionesCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) comboBoxOpcionesCuenta.getSelectedItem();
                if (seleccion.equals("Registrar Pieza Nueva")) {
                    VentanaRegistroPieza ventanaRegistroPieza = new VentanaRegistroPieza();
                    ventanaRegistroPieza.setLocationRelativeTo(null);
                    ventanaRegistroPieza.setVisible(true);
                } else if (seleccion.equals("Ver Saldo Actual")) {
                    // Aquí obtenemos el saldo actual del cliente y lo mostramos en un mensaje
                	String usuario = VentanaPrincipal.getPanelRegistroCliente().getUsuario();
                    Cliente cliente = (Cliente) GaleriaDeArte.getUsuario(usuario);
                    double saldo = cliente.getSaldo();
                    JOptionPane.showMessageDialog(null, "Su saldo actual es: " + saldo);}
                
                    else if (seleccion.equals("Ver Piezas Propias")) {
                        // Aquí obtenemos la lista de piezas del cliente
                    	String usuario = VentanaPrincipal.getPanelRegistroCliente().getUsuario();
                        List<Pieza> piezasPropias = GaleriaDeArte.getAdministrador().getPiezasCliente(usuario);

                        if (piezasPropias.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No posee piezas.");
                        } else {
                            // Crear una matriz para los datos de la tabla
                            String[][] datos = new String[piezasPropias.size()][4];
                            for (int i = 0; i < piezasPropias.size(); i++) {
                                Pieza pieza = piezasPropias.get(i);
                                datos[i][0] = pieza.getTitulo();
                                datos[i][1] = pieza.getAutor();
                                datos[i][2] = Integer.toString(pieza.getPrecioCompra());
                                datos[i][3] = pieza.getCodigoPieza();
                            }

                            // Crear un array con los nombres de las columnas
                            String[] columnas = {"Título", "Autor", "Tipo de Pieza", "Código"};

                            // Crear la tabla con los datos y columnas
                            JTable tablaPiezas = new JTable(datos, columnas);

                            // Crear un JScrollPane para la tabla
                            JScrollPane scrollPane = new JScrollPane(tablaPiezas);

                            // Mostrar el diálogo con la tabla
                            JOptionPane.showMessageDialog(null, scrollPane, "Piezas Propias", JOptionPane.PLAIN_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, seleccion + " seleccionada");
                    }
                }
            });

        // Acción para el botón "Compra"
        btnCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Función de compra seleccionada");
            }
        });

        // Acción para el botón "Subasta"
        btnSubasta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Función de subasta seleccionada");
            }
        });

        // Botón de salir
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

    public static void main(String[] args) {
        VentanaFuncionesCliente ventana = new VentanaFuncionesCliente();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}