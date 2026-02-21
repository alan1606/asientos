/**
 * CORRECCIÓN: ReporteDAO.java
 * 
 * PROBLEMA: El query SQL no trae el nombre del cliente, ciudad ni estado
 * 
 * SOLUCIÓN: Actualizar el SQL para incluir todos los JOINs necesarios
 */

package ClassDAO;

import ClassVO.ReporteVO;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {
    
    // ========== QUERY CORREGIDO CON TODOS LOS JOINS ==========
    
    /**
     * Query principal para obtener datos del ticket
     * 
     * IMPORTANTE: Este query incluye TODOS los datos necesarios:
     * - Datos de la venta (detalle)
     * - Nombre del cliente (JOIN cliente)
     * - Ciudad y estado (JOIN viaje, destino, estado)
     * - Asientos (GROUP_CONCAT con JOIN asiento)
     * - Usuario vendedor (JOIN usuario)
     */
    private static final String SQL_SELECT_DATOS_TICKET = 
        "SELECT " +
        "    d.id AS folio, " +
        "    d.costo, " +
        "    d.anticipo, " +
        "    d.sube, " +
        "    DATE_FORMAT(d.hora, '%I:%i %p') AS hora, " +
        "    GROUP_CONCAT(DISTINCT(a.numero) ORDER BY a.numero SEPARATOR ',') AS asientos, " +
        "    d.viaje, " +
        "    v.observaciones, " +
        "    d.pago, " +
        "    DATE_FORMAT(d.hora_regreso, '%I:%i %p') AS hora_regreso, " +
        "    DATE_FORMAT(d.fecha_regreso, '%d/%m/%Y') AS fecha_regreso, " +
        "    DATE_FORMAT(d.fecha_venta, '%d/%m/%Y') AS fecha_venta, " +
        "    d.personas AS numeroPersonas, " +
        "    c.telefono, " +
        "    u.nombre AS usuario, " +
        // ✅ CORREGIDO: Agregar estos campos que faltaban
        "    c.nombre AS nombreCliente, " +
        "    dest.ciudad AS ciudad, " +
        "    e.nombre AS estado " +
        "FROM detalle d " +
        "JOIN cliente c ON c.id = d.id_cliente " +                    // ✅ JOIN cliente
        "JOIN viaje v ON v.id = d.id_viaje " +
        "JOIN destino dest ON dest.id = v.id_destino " +              // ✅ JOIN destino
        "JOIN estado e ON e.id = dest.id_estado " +                   // ✅ JOIN estado
        "LEFT JOIN asiento a ON a.id_cliente = d.id_cliente " +
        "                    AND a.id_viaje = d.id_viaje " +
        "JOIN usuario u ON u.id = d.id_usuario " +
        "WHERE d.id_cliente = ? " +
        "  AND d.id_viaje = ? " +
        "  AND d.estado != 'CANCELADO' " +
        "  AND d.id = ? " +
        "GROUP BY d.id, d.costo, d.anticipo, d.sube, d.hora, d.viaje, " +
        "         v.observaciones, d.pago, d.hora_regreso, d.fecha_regreso, " +
        "         d.fecha_venta, d.personas, c.telefono, u.nombre, " +
        "         c.nombre, dest.ciudad, e.nombre";
    
    /**
     * Query para obtener habitaciones
     */
    private static final String SQL_SELECT_HABITACIONES = 
        "SELECT " +
        "    h.nombre AS hotel, " +
        "    dhdv.habitaciones " +
        "FROM detalle d " +
        "JOIN detalle_hotel_destino_viaje dhdv ON dhdv.id_detalle = d.id " +
        "JOIN hotel_destino_viaje hdv ON hdv.id = dhdv.id_hotel_destino_viaje " +
        "JOIN hotel h ON h.id = hdv.id_hotel " +
        "WHERE d.id_cliente = ? " +
        "  AND d.id_viaje = ? " +
        "  AND d.estado != 'CANCELADO' " +
        "  AND d.id = ?";
    
    /**
     * Obtiene los datos completos del ticket
     */
    public ReporteVO obtenerDatosTicket(int idCliente, int idViaje, Long idDetalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ReporteVO reporte = null;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_DATOS_TICKET);
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idViaje);
            stmt.setLong(3, idDetalle);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                reporte = new ReporteVO();
                reporte.setFolio(rs.getLong("folio"));
                reporte.setCosto(rs.getDouble("costo"));
                reporte.setAnticipo(rs.getDouble("anticipo"));
                reporte.setSube(rs.getString("sube"));
                reporte.setHora(rs.getString("hora"));
                reporte.setAsientos(rs.getString("asientos"));
                reporte.setViaje(rs.getString("viaje"));
                reporte.setObservaciones(rs.getString("observaciones"));
                reporte.setPago(rs.getString("pago"));
                reporte.setHoraRegreso(rs.getString("hora_regreso"));
                reporte.setFechaRegreso(rs.getString("fecha_regreso"));
                reporte.setFechaVenta(rs.getString("fecha_venta"));
                reporte.setNumeroPersonas(rs.getInt("numeroPersonas"));
                reporte.setTelefono(rs.getString("telefono"));
                reporte.setUsuario(rs.getString("usuario"));
                
                // ✅ CORREGIDO: Mapear los campos que faltaban
                reporte.setNombreCliente(rs.getString("nombreCliente"));
                reporte.setCiudad(rs.getString("ciudad"));
                reporte.setEstado(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return reporte;
    }
    
    /**
     * Obtiene las habitaciones del ticket
     */
    public List<ReporteVO.HabitacionVO> obtenerHabitaciones(int idCliente, int idViaje, Long idDetalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ReporteVO.HabitacionVO> habitaciones = new ArrayList<>();
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_HABITACIONES);
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idViaje);
            stmt.setLong(3, idDetalle);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                ReporteVO.HabitacionVO hab = new ReporteVO.HabitacionVO();
                hab.setNombreHotel(rs.getString("hotel"));
                hab.setCantidad(rs.getInt("habitaciones"));
                habitaciones.add(hab);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return habitaciones;
    }
    
    /**
     * Obtiene datos completos del ticket (con habitaciones)
     */
    public ReporteVO obtenerDatosTicketCompleto(int idCliente, int idViaje, Long idDetalle,
                                                String cliente, String ciudad, String estado) {
        // Obtener datos principales
        ReporteVO reporte = obtenerDatosTicket(idCliente, idViaje, idDetalle);
        
        if (reporte != null) {
            // Agregar habitaciones
            List<ReporteVO.HabitacionVO> habitaciones = obtenerHabitaciones(idCliente, idViaje, idDetalle);
            reporte.setHabitaciones(habitaciones);
            
            // ✅ VALIDACIÓN: Si los datos del query vienen null, usar los parámetros
            if (reporte.getNombreCliente() == null || reporte.getNombreCliente().isEmpty()) {
                reporte.setNombreCliente(cliente);
            }
            if (reporte.getCiudad() == null || reporte.getCiudad().isEmpty()) {
                reporte.setCiudad(ciudad);
            }
            if (reporte.getEstado() == null || reporte.getEstado().isEmpty()) {
                reporte.setEstado(estado);
            }
        }
        
        return reporte;
    }
    
    /**
     * Verifica si el detalle tiene habitaciones
     */
    public boolean tieneHabitaciones(int idCliente, int idViaje, Long idDetalle) {
        List<ReporteVO.HabitacionVO> habitaciones = obtenerHabitaciones(idCliente, idViaje, idDetalle);
        return habitaciones != null && !habitaciones.isEmpty();
    }
    
    /**
     * Verifica si es viaje redondo
     */
    public boolean esViajeRedondo(int idCliente, int idViaje, Long idDetalle) {
        ReporteVO reporte = obtenerDatosTicket(idCliente, idViaje, idDetalle);
        return reporte != null && "REDONDO".equalsIgnoreCase(reporte.getViaje());
    }
}