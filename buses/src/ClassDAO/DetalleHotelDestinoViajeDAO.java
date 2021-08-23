package ClassDAO;

import ClassVO.DetalleHotelDestinoViajeVO;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DetalleHotelDestinoViajeDAO {

    private static final String SQL_SELECT = "SELECT * "
            + " FROM detalle_hotel_destino_viaje";

    private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM detalle_hotel_destino_viaje WHERE id=? ";
    
        private static final String SQL_SELECT_BY_ID_DETALLE = "SELECT * "
            + " FROM detalle_hotel_destino_viaje WHERE id_detalle=? ";

    /*private static final String SQL_SELECT_BY_NAME = "SELECT * "
            + " FROM categoria WHERE nombre = ?";*/
    private static final String SQL_UPDATE = "UPDATE detalle  "
            + " SET sube=?, hora = ?, liquidado = ? WHERE id_viaje=? and id_cliente=? and id_usuario=?";

    private static final String SQL_INSERT = "INSERT INTO detalle_hotel_destino_viaje values "
            + " (null,?, ?, ?)";

    private static final String SQL_DELETE = "DELETE FROM detalle WHERE id=? ";

    private static final String SQL_COUNT_HOTEL_DESTINO_VIAJE = " select sum(habitaciones) as encontrados  from detalle_hotel_destino_viaje where id_hotel_destino_viaje = ?";


    public ArrayList<DetalleHotelDestinoViajeVO> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleHotelDestinoViajeVO detalle = null;
        ArrayList<DetalleHotelDestinoViajeVO> detalles = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Long idDetalle = rs.getLong("id_detalle");
                int idHotelDestinoViaje = rs.getInt("id_hotel_destino_viaje");
                int habitaciones = rs.getInt("habitaciones");

                detalle = new DetalleHotelDestinoViajeVO(id, idDetalle, idHotelDestinoViaje, habitaciones);
                detalles.add(detalle);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return detalles;
    }

    public ArrayList<DetalleHotelDestinoViajeVO> encontrarDetalle(Long _idDetalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleHotelDestinoViajeVO detalle = null;
        ArrayList<DetalleHotelDestinoViajeVO> detalles = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_DETALLE);
            stmt.setLong(1, _idDetalle);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Long idDetalle = rs.getLong("id_detalle");
                int idHotelDestinoViaje = rs.getInt("id_hotel_destino_viaje");
                int habitaciones = rs.getInt("habitaciones");

                detalle = new DetalleHotelDestinoViajeVO(id, idDetalle, idHotelDestinoViaje, habitaciones);
                detalles.add(detalle);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return detalles;
    }
    
    public DetalleHotelDestinoViajeVO encontrar(int _id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleHotelDestinoViajeVO detalle = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, _id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                 int id = rs.getInt("id");
                Long idDetalle = rs.getLong("id_detalle");
                int idHotelDestinoViaje = rs.getInt("id_hotel_destino_viaje");
                int habitaciones = rs.getInt("habitaciones");

                detalle = new DetalleHotelDestinoViajeVO(id, idDetalle, idHotelDestinoViaje, habitaciones);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return detalle;
    }

    public int obtenerComprados(int _idHotelDestinoViaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int encontrados = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_COUNT_HOTEL_DESTINO_VIAJE);
            stmt.setInt(1, _idHotelDestinoViaje);
            rs = stmt.executeQuery();
            rs.first();
            encontrados = rs.getInt("encontrados");
            System.out.println(encontrados);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return encontrados;
    }

    public int insertar(DetalleHotelDestinoViajeVO detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setLong(1, detalle.getIdDetalle());
            stmt.setInt(2, detalle.getIdHotelDestinoViaje());
            stmt.setInt(3, detalle.getHabitaciones());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    /* public int actualizar(DetalleVO detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, detalle.getSube());
            stmt.setString(2, detalle.getHora());
            stmt.setBoolean(3, detalle.isLiquidado());
            stmt.setInt(4, detalle.getIdViaje());
            stmt.setInt(5, detalle.getIdCliente());
            stmt.setInt(6, detalle.getIdUsuario());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }*/

 /* public int eliminar(DetalleHoteVO detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, detalle.getIdViaje());
            stmt.setInt(2, detalle.getIdCliente());
            stmt.setInt(3, detalle.getIdUsuario());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }*/
}
