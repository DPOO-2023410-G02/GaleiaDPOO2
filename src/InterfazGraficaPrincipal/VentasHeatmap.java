package InterfazGraficaPrincipal;
import javax.swing.*;

import Model.GaleriaDeArte;
import Model.Inventario;
import Pieza.Pieza;
import Usuario.Cliente;

import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class VentasHeatmap extends JPanel {

    private Map<LocalDate, Integer> ventasPorDia;
    private int maxVentasEnUnDia;

    public VentasHeatmap(Map<LocalDate, Integer> ventasPorDia) {
        this.ventasPorDia = ventasPorDia;
        this.maxVentasEnUnDia = ventasPorDia.values().stream().mapToInt(Integer::intValue).max().orElse(1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = 20;
        int padding = 5;
        int rows = 7;
        int cols = 53; // Aproximadamente 52 semanas en un año más unos días extra

        LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1);
        LocalDate endDate = LocalDate.of(LocalDate.now().getYear(), Month.DECEMBER, 31);

        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        LocalDate firstDayOfYear = startDate.with(weekFields.dayOfWeek(), 1);

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            int weekOfYear = date.get(weekFields.weekOfYear());
            // Obtener el día de la semana (0 = Lunes, 1 = Martes, ..., 6 = Domingo)
            int dayOfWeek = date.getDayOfWeek().getValue() - 1;

            // Ajustar los índices para que se inicien en 0
            int col = weekOfYear - 1;
            int row = dayOfWeek - 1;
            row = (dayOfWeek == -1) ? rows - 1 : dayOfWeek;
            
            int ventasEnEsteDia = ventasPorDia.getOrDefault(date, 0);
            double intensity = (double) ventasEnEsteDia / 5;
            int colorValue = (int) (255 * (1.0 - intensity));
            g.setColor(new Color(colorValue, colorValue, colorValue));
            g.fillRect(col * (cellSize + padding), row * (cellSize + padding), cellSize, cellSize);
        }
    }
    

    public static void main(String[] args) {
        // Creacion Galeria 
        GaleriaDeArte galeria = new GaleriaDeArte();
        
        // Creacion Empleados
        galeria.AgregarAdministrador("Qwer1234", "Camilo2816", "Camilo Sanchez");
        galeria.AgregarCajero("Qwer1234", "Camilo2816", "Camilo Sanchez");
        galeria.AgregarAdministrador("Qwer1234", "Camilo2816", "Camilo Sanchez");
        
        // Clientes
        Cliente usuarioTest = new Cliente("Zaq*Mko123", "Andres21", "Andres Chaparro");
        Cliente usuarioTest2 = new Cliente("Zaq*Mko123", "Juan12", "Juan Rojas");
        Cliente usuarioTest3 = new Cliente("Zaq*Mko123", "Juan56", "Juana Rojas");
        
        galeria.AgregarUsuario(usuarioTest);
        galeria.AgregarUsuario(usuarioTest2);
        
        // Obtener inventario
        Inventario inventario = GaleriaDeArte.getInventario();
        
        // Registrar piezas y realizar consignaciones
        usuarioTest.registrarPieza("1999", "Pedro", "Roma", "TheBorn", 2000);
        usuarioTest.registrarPieza("1998", "Andres", "Italia", "TheBorn2", 1000);
        usuarioTest.RealizarConsignacion(usuarioTest.getPasadas().get(0).getCodigoPieza());
        usuarioTest.RealizarConsignacion(usuarioTest.getPasadas().get(1).getCodigoPieza());
        List<Pieza> piezasBodega = inventario.getPiezasBodega();
        Pieza piezaCompra = inventario.getPiezaBodega(piezasBodega.get(0).getCodigoPieza());
        Pieza piezaCompra2 = inventario.getPiezaBodega(piezasBodega.get(1).getCodigoPieza());
       
        //Vender piezas
        usuarioTest2.agregarSaldo(3001);
        usuarioTest2.realizarOfertaCompra(piezaCompra);       
        usuarioTest2.realizarOfertaCompra(piezaCompra2); 
        
        // Procesar ventas para el heatmap
        Map<LocalDate, Integer> ventasPorDia = galeria.contarVentasPorDia();
        
        // Crear ventana y mostrar heatmap
        JFrame frame = new JFrame("Ventas Heatmap");
        VentasHeatmap heatmapPanel = new VentasHeatmap(ventasPorDia);
        heatmapPanel.setPreferredSize(new Dimension(1200, 600));
        frame.add(heatmapPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


