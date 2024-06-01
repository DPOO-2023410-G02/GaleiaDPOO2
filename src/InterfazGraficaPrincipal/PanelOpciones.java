package InterfazGraficaPrincipal;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOpciones extends JPanel implements ActionListener
{
    private JButton optAdmin;
    private JButton optOper;
    private JButton optCliente;
    private VentanaPrincipal ventanaPrincipal;

    public PanelOpciones(VentanaPrincipal ventanaPrincipal)
    {
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new GridLayout(1, 3));
        setBorder(new TitledBorder("Opciones"));

        optAdmin = new JButton("Administrador");
        optAdmin.setBackground(new Color(255, 255, 255));
        optAdmin.setForeground(Color.RED);
        optAdmin.addActionListener(this);
        add(optAdmin);

        optOper = new JButton("Operador");
        optOper.setBackground(new Color(123, 56, 20));
        optOper.setForeground(Color.WHITE);
        optOper.addActionListener(this);
        add(optOper);

        optCliente = new JButton("Cliente");
        optCliente.setBackground(new Color(13, 56, 120));
        optCliente.setForeground(Color.GREEN);
        add(optCliente);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == optAdmin)
        {
            ventanaPrincipal.mostrarPanelRegistroAdmin();
        }
        else if (e.getSource() == optOper)
        {
            ventanaPrincipal.mostrarPanelRegistroOperador();
        }
    }
}
