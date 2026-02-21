package Reports;

import ClassDAO.DetalleDAO;
import ClassDAO.DetalleHotelDestinoViajeDAO;
import ClassDAO.ReporteDAO;
import ClassVO.DetalleHotelDestinoViajeVO;
import ClassVO.DetalleVO;
import ClassVO.ReporteVO;
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

/**
 * Clase Facade para generación de reportes
 * 
 * MIGRACIÓN A OPENPDF:
 * Esta clase mantiene la misma interfaz pública que antes, pero ahora usa
 * OpenPDF internamente para generar los tickets, eliminando la dependencia
 * de JasperReports para los reportes de tickets.
 * 
 * COMPATIBILIDAD:
 * - La firma del método reporteTicket() permanece exactamente igual
 * - El método reporteViaje() sigue usando JasperReports (no migrado)
 * - No requiere cambios en el código que llama a estos métodos
 * 
 * VENTAJAS:
 * - Sin cambios en el código existente del proyecto
 * - Migración transparente a OpenPDF
 * - Menor tamaño de dependencias
 * - Mejor rendimiento en generación de tickets
 * 
 * @author alxyg
 */
public class GenerarReporte {

    // ==========================================
    // CONFIGURACIÓN DE MIGRACIÓN
    // ==========================================
    
    /**
     * Flag para habilitar/deshabilitar el uso de OpenPDF
     * 
     * true  = Usa OpenPDF (nuevo, recomendado)
     * false = Usa JasperReports (legacy)
     * 
     * Puedes cambiar esto a 'false' temporalmente si necesitas 
     * volver al sistema anterior durante la transición
     */
    private static final boolean USAR_OPENPDF = true;
    
    /**
     * Flag para mostrar mensajes de depuración
     * Útil durante la migración para verificar qué motor se está usando
     */
    private static final boolean DEBUG_MODE = true;
    
    // ==========================================
    // MÉTODO PRINCIPAL: REPORTE DE TICKETS
    // ==========================================
    
    /**
     * Genera el reporte de ticket de viaje
     * 
     * IMPORTANTE: Este método mantiene la misma firma que antes para
     * compatibilidad total con el código existente.
     * 
     * Internamente decide automáticamente qué tipo de ticket generar:
     * - Ticket Sencillo (solo ida)
     * - Ticket Redondo (ida y vuelta)
     * - Ticket Redondo con Habitaciones (ida, vuelta y hoteles)
     * 
     * @param idCliente ID del cliente
     * @param idViaje ID del viaje
     * @param cliente Nombre del cliente
     * @param ciudad Ciudad de destino
     * @param estado Estado de destino
     * @param fecha Fecha de salida
     * @param idDetalle Folio del ticket (ID del detalle)
     */
    public static void reporteTicket(int idCliente, int idViaje, String cliente, 
                                     String ciudad, String estado, String fecha, Long idDetalle) {
        
        if (USAR_OPENPDF) {
            // ========== NUEVO: USAR OPENPDF ==========
            reporteTicketOpenPDF(idCliente, idViaje, cliente, ciudad, estado, fecha, idDetalle);
        } else {
            // ========== LEGACY: USAR JASPERREPORTS ==========
            reporteTicketJasper(idCliente, idViaje, cliente, ciudad, estado, fecha, idDetalle);
        }
    }
    
    // ==========================================
    // IMPLEMENTACIÓN CON OPENPDF (NUEVO)
    // ==========================================
    
    /**
     * Implementación del reporte de ticket usando OpenPDF
     * 
     * Esta es la nueva implementación que reemplaza a JasperReports.
     * Utiliza la clase GenerarReporteOpenPDF que contiene toda la
     * lógica de generación de PDFs.
     * 
     * @param idCliente ID del cliente
     * @param idViaje ID del viaje
     * @param cliente Nombre del cliente
     * @param ciudad Ciudad de destino
     * @param estado Estado de destino
     * @param fecha Fecha de salida
     * @param idDetalle Folio del ticket
     */
    private static void reporteTicketOpenPDF(int idCliente, int idViaje, String cliente, 
                                             String ciudad, String estado, String fecha, Long idDetalle) {
        try {
            if (DEBUG_MODE) {
                System.out.println("=== GENERANDO TICKET CON OPENPDF ===");
                System.out.println("Folio: " + idDetalle);
                System.out.println("Cliente: " + cliente);
                System.out.println("Destino: " + ciudad + ", " + estado);
            }
            
            // Validaciones básicas
            if (idDetalle == null) {
                throw new IllegalArgumentException("El ID del detalle no puede ser null");
            }
            
            // Verificar que el detalle existe
            DetalleDAO detalleDAO = new DetalleDAO();
            DetalleVO detalle = detalleDAO.encontrar(idDetalle);
            
            if (detalle == null) {
                throw new IllegalArgumentException("No se encontró el detalle con ID: " + idDetalle);
            }
            
            if ("CANCELADO".equals(detalle.getEstado())) {
                throw new IllegalStateException("El ticket está CANCELADO y no se puede imprimir");
            }
            
            // Llamar al método automático de GenerarReporteOpenPDF
            // Este método decide automáticamente qué tipo de ticket generar
            GenerarReporteOpenPDF.reporteTicketAutomatico(
                idCliente, 
                idViaje, 
                cliente, 
                ciudad, 
                estado, 
                fecha, 
                idDetalle
            );
            
            if (DEBUG_MODE) {
                System.out.println("✓ Ticket generado exitosamente con OpenPDF");
            }
            
        } catch (IllegalArgumentException | IllegalStateException e) {
            // Errores de validación
            String mensaje = "Error de validación: " + e.getMessage();
            System.err.println(mensaje);
            JOptionPane.showMessageDialog(
                null, 
                mensaje,
                "Error de Validación",
                JOptionPane.WARNING_MESSAGE
            );
        } catch (Exception e) {
            // Errores generales
            String mensaje = "Error al generar el ticket: " + e.getMessage();
            System.err.println(mensaje);
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null, 
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    // ==========================================
    // IMPLEMENTACIÓN CON JASPERREPORTS (LEGACY)
    // ==========================================
    
    /**
     * Implementación LEGACY del reporte de ticket usando JasperReports
     * 
     * Esta es la implementación original que se mantiene como respaldo
     * durante la transición. Se puede activar cambiando USAR_OPENPDF = false
     * 
     * @param idCliente ID del cliente
     * @param idViaje ID del viaje
     * @param cliente Nombre del cliente
     * @param ciudad Ciudad de destino
     * @param estado Estado de destino
     * @param fecha Fecha de salida
     * @param idDetalle Folio del ticket
     */
    private static void reporteTicketJasper(int idCliente, int idViaje, String cliente, 
                                            String ciudad, String estado, String fecha, Long idDetalle) {
        
        if (DEBUG_MODE) {
            System.out.println("=== GENERANDO TICKET CON JASPERREPORTS (LEGACY) ===");
            System.out.println("ADVERTENCIA: Usando sistema antiguo de JasperReports");
        }
        
        String reporteDir = "ReporteTicket.jasper"; // Reporte por defecto: viaje redondo sin habitaciones
        DetalleDAO modelo = new DetalleDAO();
        DetalleVO detalle = modelo.encontrar(idDetalle);
        DetalleHotelDestinoViajeDAO modeloHabitaciones = new DetalleHotelDestinoViajeDAO();
        List<DetalleHotelDestinoViajeVO> detalleHabitaciones = modeloHabitaciones.encontrarDetalle(idDetalle);
        
        Connection conn = null;
        
        // Determinar qué reporte usar según el tipo de viaje y habitaciones
        if (detalle.getViaje().equals("SENCILLO")) {
            reporteDir = "ReporteTicketSencillo.jasper";
        } else if (!detalleHabitaciones.isEmpty()) {
            reporteDir = "ReporteTicketRedondoConHabitaciones.jasper";
        }
        
        try {
            conn = Conexion.getConnection();
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(
                System.getProperty("user.dir") + "/src/Reports/" + reporteDir
            );

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
    
    // ==========================================
    // OTROS REPORTES (SIN MIGRAR)
    // ==========================================
    
    /**
     * Genera el reporte de viaje (asientos del autobús)
     * 
     * NOTA: Este reporte NO ha sido migrado a OpenPDF todavía.
     * Sigue usando JasperReports.
     * 
     * @param destino Destino del viaje
     * @param fecha Fecha del viaje
     * @param idViaje ID del viaje
     */
    public static void reporteViaje(String destino, String fecha, int idViaje) {
        Connection conn = null;
        try {
            conn = Conexion.getConnection();
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(
                System.getProperty("user.dir") + "/src/Reports/ReporteAutobus.jasper"
            );

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
    
    // ==========================================
    // MÉTODOS AUXILIARES
    // ==========================================
    
    /**
     * Método de utilidad para obtener información del ticket sin generarlo
     * Útil para debugging o validaciones previas
     * 
     * @param idDetalle Folio del ticket
     * @return ReporteVO con los datos del ticket, o null si no existe
     */
    public static ReporteVO obtenerDatosTicket(Long idDetalle, int idCliente, int idViaje,
                                               String cliente, String ciudad, String estado) {
        try {
            ReporteDAO reporteDAO = new ReporteDAO();
            return reporteDAO.obtenerDatosTicketCompleto(
                idCliente, idViaje, idDetalle, cliente, ciudad, estado
            );
        } catch (Exception e) {
            System.err.println("Error al obtener datos del ticket: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Verifica si el sistema está usando OpenPDF o JasperReports
     * 
     * @return true si está usando OpenPDF, false si está usando JasperReports
     */
    public static boolean estaUsandoOpenPDF() {
        return USAR_OPENPDF;
    }
    
    /**
     * Método de utilidad para verificar qué tipo de ticket se generaría
     * sin generar el PDF
     * 
     * @param idDetalle Folio del ticket
     * @return String describiendo el tipo de ticket
     */
    public static String obtenerTipoTicket(Long idDetalle) {
        try {
            DetalleDAO detalleDAO = new DetalleDAO();
            DetalleVO detalle = detalleDAO.encontrar(idDetalle);
            
            if (detalle == null) {
                return "ERROR: Ticket no encontrado";
            }
            
            if ("CANCELADO".equals(detalle.getEstado())) {
                return "CANCELADO";
            }
            
            DetalleHotelDestinoViajeDAO habitacionesDAO = new DetalleHotelDestinoViajeDAO();
            List<DetalleHotelDestinoViajeVO> habitaciones = habitacionesDAO.encontrarDetalle(idDetalle);
            
            boolean esRedondo = "REDONDO".equalsIgnoreCase(detalle.getViaje());
            boolean tieneHabitaciones = !habitaciones.isEmpty();
            
            if (esRedondo && tieneHabitaciones) {
                return "TICKET REDONDO CON HABITACIONES";
            } else if (esRedondo) {
                return "TICKET REDONDO";
            } else {
                return "TICKET SENCILLO";
            }
            
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}