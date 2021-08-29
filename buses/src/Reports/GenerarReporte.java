package Reports;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.*;
import net.sf.jasperreports.view.JasperViewer;


public class GenerarReporte {

    public static void reporteTicket(int idCliente, int idViaje, String cliente, String ciudad, String estado, String fecha) {
        Connection conn = null;
        try {
            conn = Conexion.getConnection();
            JasperReport reporte = (JasperReport) JRLoader.loadObject(System.getProperty("user.dir") + "\\src\\Reports\\ReporteTicket.jasper");
            
            
            Map parametro = new HashMap();
            parametro.put("idCliente", idCliente);
            parametro.put("idViaje", idViaje);
            parametro.put("cliente", cliente);
            parametro.put("ciudad", ciudad);
            parametro.put("estado", estado);
            parametro.put("fecha", fecha);
            JasperPrint j = JasperFillManager.fillReport(reporte, parametro, conn);
            JasperViewer jv = new JasperViewer(j, false);
            jv.setTitle("Ticket");
            jv.setVisible(true);
        } catch (SQLException | JRException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar el reporte " + e.getMessage());
        } finally {
            Conexion.close(conn);
        }
    }

    
}
