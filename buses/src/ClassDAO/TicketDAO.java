/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClassDAO;

import ClassVO.TicketVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alxyg
 */
public class TicketDAO {
   public TicketVO obtenerDatosParaReporte(long idDetalle) {
        TicketVO vo = new TicketVO();
        String sqlMain = "SELECT d.id, d.fecha_venta, c.nombre, c.telefono, d.viaje, ds.ciudad, e.nombre as estado, " +
                         "d.sube, DATE_FORMAT(d.hora, '%h:%i %p') as hora, d.personas, v.observaciones, d.pago, " +
                         "DATE_FORMAT(d.fecha_regreso, '%d/%m/%Y') as f_regreso, DATE_FORMAT(d.hora_regreso, '%h:%i %p') as h_regreso, " +
                         "d.costo, d.anticipo, u.nombre as vendedor " +
                         "FROM detalle d " +
                         "JOIN cliente c ON d.id_cliente = c.id " +
                         "JOIN viaje v ON d.id_viaje = v.id " +
                         "JOIN destino ds ON v.id_destino = ds.id " +
                         "JOIN estado e ON ds.id_estado = e.id " +
                         "JOIN usuario u ON d.id_usuario = u.id " +
                         "WHERE d.id = ?";
        
        String sqlHoteles = "SELECT h.nombre, dh.habitaciones FROM detalle_hotel_destino_viaje dh " +
                            "JOIN hotel_destino_viaje hdv ON dh.id_hotel_destino = hdv.id " +
                            "JOIN hotel h ON hdv.id_hotel = h.id WHERE dh.id_detalle = ?";

        try (Connection conn = Conexion.Conexion.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sqlMain);
            ps.setLong(1, idDetalle);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                vo.setIdDetalle(rs.getLong("id"));
                vo.setFechaVenta(rs.getString("fecha_venta"));
                vo.setCliente(rs.getString("nombre"));
                vo.setTelefono(rs.getString("telefono"));
                vo.setViaje(rs.getString("viaje"));
                vo.setCiudad(rs.getString("ciudad"));
                vo.setEstado(rs.getString("estado"));
                vo.setSube(rs.getString("sube"));
                vo.setHora(rs.getString("hora"));
                vo.setPersonas(rs.getInt("personas"));
                vo.setObservaciones(rs.getString("observaciones"));
                vo.setPago(rs.getString("pago"));
                vo.setFechaRegreso(rs.getString("f_regreso"));
                vo.setHoraRegreso(rs.getString("h_regreso"));
                vo.setCosto(rs.getDouble("costo"));
                vo.setAnticipo(rs.getDouble("anticipo"));
                vo.setVendedor(rs.getString("vendedor"));
            }

            // Cargar habitaciones (Si existen)
            PreparedStatement psH = conn.prepareStatement(sqlHoteles);
            psH.setLong(1, idDetalle);
            ResultSet rsH = psH.executeQuery();
            while (rsH.next()) {
                vo.getHabitaciones().add(new TicketVO.Habitacion(rsH.getString("nombre"), rsH.getInt("habitaciones")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return vo;
    }
}
