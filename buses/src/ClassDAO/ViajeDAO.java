/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDAO;

import ClassVO.ViajeVO;
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
public class ViajeDAO {

    private static final String SQL_SELECT = "SELECT * "
            + " FROM viaje";

    private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM viaje WHERE id = ?";

    private static final String SQL_SELECT_DESTINATION_ID = "SELECT * "
            + " FROM viaje WHERE id_destino = ?";

    private static final String SQL_UPDATE = "UPDATE viaje "
            + " SET fecha= ?, no_asientos=? WHERE id =?";

    private static final String SQL_INSERT = "INSERT INTO viaje "
            + " VALUES(null,?,?,?)";

    private static final String SQL_DELETE = "DELETE FROM viaje WHERE id=?";

    public static ArrayList<ViajeVO> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ViajeVO viaje = null;
        ArrayList<ViajeVO> viajes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idDestino = rs.getInt("id_destino");
                java.sql.Date fecha = rs.getDate("fecha");
                int noAsientos = rs.getInt("no_asientos");

                viaje = new ViajeVO(id, idDestino, fecha, noAsientos);
                viajes.add(viaje);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return viajes;
    }

    public static ViajeVO encontrar(int _id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ViajeVO viaje = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, _id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int idDestino = rs.getInt("id_destino");
                java.sql.Date fecha = rs.getDate("fecha");
                int noAsientos = rs.getInt("no_asientos");
                viaje = new ViajeVO(id, idDestino, fecha, noAsientos);

            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return viaje;
    }
    
    public static ViajeVO encontrarByDestino(int _id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ViajeVO viaje = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_DESTINATION_ID);
            stmt.setInt(1, _id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int idDestino = rs.getInt("id_destino");
                java.sql.Date fecha = rs.getDate("fecha");
                int noAsientos = rs.getInt("no_asientos");
                viaje = new ViajeVO(id, idDestino, fecha, noAsientos);

            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return viaje;
    }

    public static int insertar(ViajeVO viaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, viaje.getIdDestino());
            stmt.setDate(2, viaje.getFecha());
            stmt.setInt(3, viaje.getNoAsientos());


            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public static int actualizar(ViajeVO viaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setDate(1, viaje.getFecha());
            stmt.setInt(2, viaje.getNoAsientos());
            stmt.setInt(3, viaje.getId());
            
            rows = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public static int eliminar(ViajeVO viaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, viaje.getId());

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