package InterfazGraficaPrincipal;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelRegistro extends JPanel implements ActionListener {
    private JButton optAdmin;
    private JButton optOper;
    private JButton optCliente;
    private VentanaPrincipal ventanaPrincipal;

    public PanelRegistro(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "¿Como te quieres registrar?"));
        setLayout(new GridLayout(1, 3));

        optAdmin = new JButton("Administrador");
        optAdmin.addActionListener(this);
        add(optAdmin);

        optOper = new JButton("Operador");
        optOper.addActionListener(this);
        add(optOper);

        optCliente = new JButton("Cliente");
        optCliente.addActionListener(this);
        add(optCliente);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == optAdmin) {
            ventanaPrincipal.mostrarPanelRegistroAdmin();
        } else if (e.getSource() == optOper) {
            ventanaPrincipal.mostrarPanelRegistroOperador();
        } else if (e.getSource() == optCliente) {
            // Aquí deberías mostrar el panel de registro de cliente
        }
    }
}