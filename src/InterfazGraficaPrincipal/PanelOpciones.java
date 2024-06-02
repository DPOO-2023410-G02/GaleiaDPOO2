package InterfazGraficaPrincipal;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOpciones extends JPanel implements ActionListener {
    private JButton optReg; // Nuevo botón para mostrar el panel de tres botones
    private VentanaPrincipal ventanaPrincipal;

    public PanelOpciones(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new GridLayout(1, 1)); // GridLayout con una columna para el botón
        setBorder(new TitledBorder("Opciones"));

        optReg = new JButton("Registrarse"); // Texto del botón para mostrar el panel de tres botones
        optReg.setBackground(new Color(255, 255, 255));
        optReg.setForeground(Color.RED);
        optReg.addActionListener(this);
        add(optReg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == optReg) {
            ventanaPrincipal.mostrarPanelRegistro(); // Llama al método para mostrar el panel de tres botones
        }
    }
}