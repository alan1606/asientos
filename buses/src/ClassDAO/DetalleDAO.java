/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDAO;

import ClassVO.DetalleVO;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author alanm
 */
public class DetalleDAO {

    private static final String SQL_SELECT = "SELECT * "
            + " FROM detalle";

    private static final String SQL_SELECT_LAST = "SELECT * "
            + " FROM detalle order by id desc limit 1";

    private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM detalle WHERE id_viaje=? and id_cliente = ? and id_usuario = ?";

    private static final String SQL_SELECT_BY_ID_VIAJE_CLIENTE = "SELECT * "
            + " FROM detalle WHERE id_viaje=? and id_cliente = ? and estado = 'VENDIDO'";

    private static final String SQL_SELECT_BY_ID_UNIQUE = "SELECT * "
            + " FROM detalle WHERE id = ?";

    /*private static final String SQL_SELECT_BY_NAME = "SELECT * "
            + " FROM categoria WHERE nombre = ?";*/
    private static final String SQL_UPDATE = "UPDATE detalle  "
            + " SET sube=?, hora = ?, liquidado = ?, estado = ?, hora_regreso=? WHERE id=?";

    private static final String SQL_INSERT = "INSERT INTO detalle values "
            + " (null,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE = "DELETE FROM detalle WHERE id = ?";

    public ArrayList<DetalleVO> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleVO detalle = null;
        ArrayList<DetalleVO> detalles = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                int idUsuario = rs.getInt("id_usuario");
                int personas = rs.getInt("personas");
                String sube = rs.getString("sube");
                String hora = rs.getString("hora");
                int habitaciones = rs.getInt("habitacion");
                double costo = rs.getDouble("costo");
                boolean liquidado = rs.getBoolean("liquidado");
                String estado = rs.getString("estado");
                String pago = rs.getString("pago");
                String viaje = rs.getString("viaje");
                String horaRegreso = rs.getString("hora_regreso");

                detalle = new DetalleVO(id, idViaje, idCliente, idUsuario, personas, sube, hora, habitaciones, 0, liquidado, estado, pago, viaje, horaRegreso);
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

    public ArrayList<DetalleVO> encontrar(int _idViaje, int _idCliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleVO detalle = null;
        ArrayList<DetalleVO> detalles = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_VIAJE_CLIENTE);
            stmt.setInt(1, _idViaje);
            stmt.setInt(2, _idCliente);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                int idUsuario = rs.getInt("id_usuario");
                int personas = rs.getInt("personas");
                String sube = rs.getString("sube");
                String hora = rs.getString("hora");
                int habitaciones = rs.getInt("habitacion");
                double costo = rs.getDouble("costo");
                boolean liquidado = rs.getBoolean("liquidado");
                String estado = rs.getString("estado");
                String pago = rs.getString("pago");
                String viaje = rs.getString("viaje");
                String horaRegreso = rs.getString("hora_regreso");

                detalle = new DetalleVO(id, idViaje, idCliente, idUsuario, personas, sube, hora, habitaciones, 0, liquidado, estado, pago, viaje, horaRegreso);
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

    public DetalleVO encontrar(int _idViaje, int _idCliente, int _idUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleVO detalle = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, _idViaje);
            stmt.setInt(2, _idCliente);
            stmt.setInt(3, _idUsuario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                int idUsuario = rs.getInt("id_usuario");
                int personas = rs.getInt("personas");
                String sube = rs.getString("sube");
                String hora = rs.getString("hora");
                int habitaciones = rs.getInt("habitacion");
                double costo = rs.getDouble("costo");
                boolean liquidado = rs.getBoolean("liquidado");
                String estado = rs.getString("estado");
                String pago = rs.getString("pago");
                String viaje = rs.getString("viaje");
                String horaRegreso = rs.getString("hora_regreso");

                detalle = new DetalleVO(id, idViaje, idCliente, idUsuario, personas, sube, hora, habitaciones, 0, liquidado, estado, pago, viaje, horaRegreso);
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

    public DetalleVO encontrar(int _id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleVO detalle = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_UNIQUE);
            stmt.setInt(1, _id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                int idUsuario = rs.getInt("id_usuario");
                int personas = rs.getInt("personas");
                String sube = rs.getString("sube");
                String hora = rs.getString("hora");
                int habitaciones = rs.getInt("habitacion");
                double costo = rs.getDouble("costo");
                boolean liquidado = rs.getBoolean("liquidado");
                String estado = rs.getString("estado");
                String pago = rs.getString("pago");
                String viaje = rs.getString("viaje");
                String horaRegreso = rs.getString("hora_regreso");

                detalle = new DetalleVO(id, idViaje, idCliente, idUsuario, personas, sube, hora, habitaciones, 0, liquidado, estado, pago, viaje, horaRegreso);
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

    public int insertar(DetalleVO detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, detalle.getIdViaje());
            stmt.setInt(2, detalle.getIdCliente());
            stmt.setInt(3, detalle.getIdUsuario());
            stmt.setInt(4, detalle.getPersonas());
            stmt.setString(5, detalle.getSube());
            stmt.setString(6, detalle.getHora());
            stmt.setInt(7, detalle.getHabitaciones());
            stmt.setDouble(8, detalle.getCosto());
            stmt.setBoolean(9, detalle.isLiquidado());
            stmt.setString(10, detalle.getEstado());
            stmt.setString(11, detalle.getPago());
            stmt.setString(12, detalle.getViaje());
            stmt.setString(13, detalle.getHoraRegreso());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(DetalleVO detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, detalle.getSube());
            stmt.setString(2, detalle.getHora());
            stmt.setBoolean(3, detalle.isLiquidado());
            stmt.setString(4, detalle.getEstado());
            stmt.setString(5, detalle.getHoraRegreso());
            stmt.setLong(6, detalle.getId());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(DetalleVO detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setLong(1, detalle.getId());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public DetalleVO encontrarUltimo() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleVO detalle = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_LAST);
            rs = stmt.executeQuery();
            if (rs.next()) {

                Long id = rs.getLong("id");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                int idUsuario = rs.getInt("id_usuario");
                int personas = rs.getInt("personas");
                String sube = rs.getString("sube");
                String hora = rs.getString("hora");
                int habitaciones = rs.getInt("habitacion");
                double costo = rs.getDouble("costo");
                boolean liquidado = rs.getBoolean("liquidado");
                String estado = rs.getString("estado");
                String pago = rs.getString("pago");
                String viaje = rs.getString("viaje");
                String horaRegreso = rs.getString("hora_regreso");

                detalle = new DetalleVO(id, idViaje, idCliente, idUsuario, personas, sube, hora, habitaciones, 0, liquidado, estado, pago, viaje, horaRegreso);
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
}
