package Reports;

import ClassDAO.DetalleDAO;
import ClassDAO.DetalleHotelDestinoViajeDAO;
import ClassVO.DetalleHotelDestinoViajeVO;
import ClassVO.DetalleVO;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.*;
import net.sf.jasperreports.view.JasperViewer;

public class GenerarReporte {

    public static void reporteTicket(int idCliente, int idViaje, String cliente, String ciudad, String estado, String fecha, Long idDetalle) {
        String reporteDir = "ReporteTicket.jasper"; //El reporte por defecto, es viaje redondo, sin habitaciones
        DetalleDAO modelo = new DetalleDAO(); //Conector a detalles 
        DetalleVO detalle = modelo.encontrar(idDetalle); //Encontramos ese detalle para poder saber si es viaje redondo o sencillo
        DetalleHotelDestinoViajeDAO modeloHabitaciones = new DetalleHotelDestinoViajeDAO(); //Conector hacia esa tabla de la db, nos va a arrojar cuántas hanitaciones compró en x hotel
        List<DetalleHotelDestinoViajeVO> detalleHabitaciones = modeloHabitaciones.encontrarDetalle(idDetalle); //Encontramos ese detalle
        
        Connection conn = null;
        if (detalle.getViaje().equals("SENCILLO")) {
            reporteDir = "ReporteTicketSencillo.jasper";
        }
        //Es viaje redondo, es un hecho, sólo hay que saber si tiene por lo menos una habitación
        else if(!detalleHabitaciones.isEmpty()){  //Si no está vacio el detalle de habitaciones
            reporteDir = "ReporteTicketRedondoConHabitaciones.jasper";//Entonces va a ser un ticket con habitaciones
        }
        try {
            conn = Conexion.getConnection();
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "\\src\\Reports\\" + reporteDir);
            //JasperReport reporte = (JasperReport) JRLoader.loadObject(System.getProperty("user.dir" + "\\src\\Reports\\ReporteTixket.jasper"));

            Map parametro = new HashMap();
            parametro.put("idCliente", idCliente);
            parametro.put("idViaje", idViaje);
            parametro.put("cliente", cliente);
            parametro.put("ciudad", ciudad);
            parametro.put("estado", estado);
            parametro.put("fecha", fecha);
            parametro.put("idDetalle", idDetalle);
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

    public static void reporteViaje(String destino, String fecha, int idViaje) {
        Connection conn = null;
        try {
            conn = Conexion.getConnection();
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(System.getProperty("user.dir") + "\\src\\Reports\\ReporteAutobus.jasper");

            Map parametro = new HashMap();
            parametro.put("idViaje", idViaje);
            parametro.put("destino", destino);
            parametro.put("fecha", fecha);

            JasperPrint j = JasperFillManager.fillReport(reporte, parametro, conn);
            JasperViewer jv = new JasperViewer(j, false);
            jv.setTitle("Asientos");
            jv.setVisible(true);
        } catch (SQLException | JRException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar el reporte " + e.getMessage());
        } finally {
            Conexion.close(conn);
        }
    }

}
