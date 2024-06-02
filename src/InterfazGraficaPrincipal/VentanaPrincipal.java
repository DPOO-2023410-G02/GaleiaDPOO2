package InterfazGraficaPrincipal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.GaleriaDeArte;

public class VentanaPrincipal extends JFrame {
    private PanelOpciones panelOpc;
    private PanelImagen panelImg;
    private PanelRegistroAdmin panelRegistroAdmin;
    private PanelRegistroOperador panelRegistroOperador;
    private PanelRegistro panelRegistro; // Panel con los tres botones
    private JPanel panelContenedor; // Contenedor para los paneles de registro
    private CardLayout cardLayout; // CardLayout para alternar entre paneles
    private GaleriaDeArte modelo;

    public VentanaPrincipal() {
        setSize(750, 650);
        setTitle("Galeria De Arte");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            modelo = new GaleriaDeArte();
            modelo.AgregarAdministrador("1234", "admin23", "Camilo");
            modelo.AgregarCajero("1234", "cajero23", "Ernesto");
            modelo.AgregarOperador("1234", "operador23", "Arturo");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el museo", "Error", JOptionPane.ERROR_MESSAGE);
        }

        panelImg = new PanelImagen();
        add(panelImg, BorderLayout.NORTH);

        panelOpc = new PanelOpciones(this);
        add(panelOpc, BorderLayout.SOUTH);

        // Crear el contenedor con CardLayout
        panelContenedor = new JPanel();
        cardLayout = new CardLayout();
        panelContenedor.setLayout(cardLayout);

        // Crear los paneles de registro
        panelRegistroAdmin = new PanelRegistroAdmin();
        panelRegistroAdmin.setActionListener(e -> registrarAdministrador());

        panelRegistroOperador = new PanelRegistroOperador();
        panelRegistroOperador.setActionListener(e -> registrarOperador());

        panelRegistro = new PanelRegistro(this); // Inicializar el nuevo panel

        // Añadir los paneles al contenedor
        panelContenedor.add(panelRegistro, "PanelRegistro");
        panelContenedor.add(panelRegistroAdmin, "PanelRegistroAdmin");
        panelContenedor.add(panelRegistroOperador, "PanelRegistroOperador");

        // Añadir el contenedor al centro del BorderLayout
        add(panelContenedor, BorderLayout.CENTER);

        // Mostrar el panel de registro por defecto
        cardLayout.show(panelContenedor, "PanelRegistro");
    }

    public void mostrarPanelRegistroAdmin() {
        cardLayout.show(panelContenedor, "PanelRegistroAdmin");
    }

    public void mostrarPanelRegistro() {
        cardLayout.show(panelContenedor, "PanelRegistro");
    }

    public void mostrarPanelRegistroOperador() {
        cardLayout.show(panelContenedor, "PanelRegistroOperador");
    }

    private void registrarOperador() {
        String usuario = panelRegistroOperador.getUsuario();
        String contrasena = panelRegistroOperador.getContrasena();

        if (modelo.iniciarSesionAdmin(usuario, contrasena)) {
            JOptionPane.showMessageDialog(this, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            VentanaFuncionesAdmin ventanaFuncionesAdmin = new VentanaFuncionesAdmin();
            ventanaFuncionesAdmin.setLocationRelativeTo(null);
            ventanaFuncionesAdmin.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarAdministrador() {
        String usuario = panelRegistroAdmin.getUsuario();
        String contrasena = panelRegistroAdmin.getContrasena();

        if (modelo.iniciarSesionAdmin(usuario, contrasena)) {
            JOptionPane.showMessageDialog(this, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            VentanaFuncionesAdmin ventanaFuncionesAdmin = new VentanaFuncionesAdmin();
            ventanaFuncionesAdmin.setLocationRelativeTo(null);
            ventanaFuncionesAdmin.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        VentanaPrincipal principal = new VentanaPrincipal();
        principal.setLocationRelativeTo(null);
        principal.setVisible(true);
    }
}