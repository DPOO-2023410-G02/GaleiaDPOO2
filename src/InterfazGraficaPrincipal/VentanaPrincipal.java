package InterfazGraficaPrincipal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Model.GaleriaDeArte;

public class VentanaPrincipal extends JFrame {
    private PanelOpciones panelOpc;
    private PanelImagen panelImg;
    private PanelRegistroAdmin panelRegistroAdmin;
    private PanelRegistroOperador panelRegistroOperador;
    private PanelRegistro panelRegistro; // Panel con los tres botones
    private GaleriaDeArte modelo;

    public VentanaPrincipal() {
        setSize(750, 650);
        setTitle("Galeria De Arte");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            modelo = new GaleriaDeArte();
            modelo.AgregarAdministrador("admin23", "Qwer1234", "Camilo");
            modelo.AgregarCajero("cajero23", "Qwer1234", "Ernesto");
            modelo.AgregarOperador("operador23", "Qwer1234", "Arturo");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el museo", "Error", JOptionPane.ERROR_MESSAGE);
        }

        panelImg = new PanelImagen();
        add(panelImg, BorderLayout.NORTH);

        panelOpc = new PanelOpciones(this);
        add(panelOpc, BorderLayout.SOUTH);

        panelRegistroAdmin = new PanelRegistroAdmin();
        panelRegistroAdmin.setActionListener(e -> registrarAdministrador());
        add(panelRegistroAdmin, BorderLayout.CENTER);
        panelRegistroAdmin.setVisible(false);

        panelRegistroOperador = new PanelRegistroOperador();
        panelRegistroOperador.setActionListener(e -> mostrarVentanaRegistroOperador());
        add(panelRegistroOperador, BorderLayout.CENTER);
        panelRegistroOperador.setVisible(false);

        panelRegistro = new PanelRegistro(this); // Inicializar el nuevo panel
        add(panelRegistro, BorderLayout.CENTER);
        panelRegistro.setVisible(false);
    }

    public void mostrarPanelRegistroAdmin() {
        panelRegistroAdmin.setVisible(true);
        panelRegistroOperador.setVisible(false);
        panelRegistro.setVisible(false);
    }

    public void mostrarPanelRegistro() {
        panelRegistro.setVisible(true);
        panelRegistroAdmin.setVisible(false);
        panelRegistroOperador.setVisible(false);
    }

    public void mostrarPanelRegistroOperador() {
        panelRegistroOperador.setVisible(true);
        panelRegistroAdmin.setVisible(false);
        panelRegistro.setVisible(false);
    }

    private void mostrarVentanaRegistroOperador() {
        JFrame ventanaRegistro = new JFrame("Registro de Operador");
        PanelRegistroOperador panelRegistroOperador = new PanelRegistroOperador();
        panelRegistroOperador.setActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = panelRegistroOperador.getUsuario();
                String contrasena = panelRegistroOperador.getContrasena();

                if (modelo.iniciarSesionOperador(usuario, contrasena)) {
                    JOptionPane.showMessageDialog(ventanaRegistro, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    VentanaFuncionesOperador ventanaFuncionesOperador = new VentanaFuncionesOperador();
                    ventanaFuncionesOperador.setLocationRelativeTo(null);
                    ventanaFuncionesOperador.setVisible(true);
                    ventanaRegistro.dispose();
                    VentanaPrincipal.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(ventanaRegistro, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ventanaRegistro.add(panelRegistroOperador);
        ventanaRegistro.setSize(400, 200);
        ventanaRegistro.setLocationRelativeTo(null);
        ventanaRegistro.setVisible(true);
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