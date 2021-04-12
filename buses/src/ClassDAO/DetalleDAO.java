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

/**
 *
 * @author alanm
 */
public class DetalleDAO {

    private static final String SQL_SELECT = "SELECT * "
            + " FROM detalle";

    private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM detalle WHERE id_viaje=? and id_cliente = ? and id_usuario = ?";

    /*private static final String SQL_SELECT_BY_NAME = "SELECT * "
            + " FROM categoria WHERE nombre = ?";*/
    private static final String SQL_UPDATE = "UPDATE detalle  "
            + " SET personas = ?, sube=?, habitación = ?, costo = ? WHERE id_viaje=? amd id_cliente=? and id_usuario=?";

    private static final String SQL_INSERT = "INSERT INTO detalle values "
            + " VALUES(?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE = "DELETE FROM detalle WHERE id_viaje=? and id_cliente = ? and id_usuario = ?";

    public static ArrayList<DetalleVO> listar() {
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
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                int idUsuario = rs.getInt("id_usuario");
                int personas = rs.getInt("personas");
                String sube = rs.getString("sube");
                String habitacion = rs.getString("habitacion");
                double costo = rs.getDouble("costo");

                detalle = new DetalleVO(idViaje, idCliente, idUsuario, personas, sube, habitacion, costo);
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

    public static DetalleVO encontrar(int _idViaje, int _idCliente, int _idUsuario) {
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
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                int idUsuario = rs.getInt("id_usuario");
                int personas = rs.getInt("personas");
                String sube = rs.getString("sube");
                String habitacion = rs.getString("habitacion");
                double costo = rs.getDouble("costo");

                detalle = new DetalleVO(idViaje, idCliente, idUsuario, personas, sube, habitacion, costo);
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

    public static int insertar(DetalleVO detalle) {
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
            stmt.setString(6, detalle.getHabitacion());
            stmt.setDouble(7, detalle.getCosto());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public static int actualizar(DetalleVO detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, detalle.getPersonas());
            stmt.setString(2, detalle.getSube());
            stmt.setString(3, detalle.getHabitacion());
            stmt.setDouble(4, detalle.getCosto());
            stmt.setInt(5, detalle.getIdViaje());
            stmt.setInt(6, detalle.getIdCliente());
            stmt.setInt(7, detalle.getIdUsuario());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public static int eliminar(DetalleVO detalle) {
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
    }
}
