/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDAO;

import ClassVO.AsientoVO;
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
public class AsientoDAO {

    private static final String SQL_SELECT = "SELECT * "
            + " FROM asiento";

    private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM asiento WHERE numero = ? and id_viaje = ?";

    /*private static final String SQL_SELECT_BY_NAME = "SELECT * "
            + " FROM categoria WHERE nombre = ?";*/
    private static final String SQL_UPDATE = "UPDATE asiento "
            + " SET id_cliente= ?, disponible=? WHERE numero=? and id_viaje =?";

    private static final String SQL_INSERT = "INSERT INTO asiento "
            + " VALUES(?,?,?,?)";

    private static final String SQL_DELETE = "DELETE FROM asiento WHERE numero = ? and id_viaje=?";

    public static ArrayList<AsientoVO> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AsientoVO asiento = null;
        ArrayList<AsientoVO> asientos = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int numero = rs.getInt("numero");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                boolean disponible = rs.getBoolean("disponible");

                asiento = new AsientoVO(numero, idViaje, idCliente, disponible);
                asientos.add(asiento);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return asientos;
    }

    public static AsientoVO encontrar(int _numero, int _idViaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AsientoVO asiento = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, _numero);
            stmt.setInt(2, _idViaje);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int numero = rs.getInt("numero");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                boolean disponible = rs.getBoolean("disponible");

                asiento = new AsientoVO(numero, idViaje, idCliente, disponible);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return asiento;
    }

    public static int insertar(AsientoVO asiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, asiento.getNumero());
            stmt.setInt(2, asiento.getIdViaje());
            stmt.setInt(3, asiento.getIdCliente());
            stmt.setBoolean(4, asiento.isDisponible());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    
    public static int actualizar(AsientoVO asiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, asiento.getIdCliente());
            stmt.setBoolean(2, asiento.isDisponible());
            stmt.setInt(3, asiento.getNumero());
            stmt.setInt(4, asiento.getIdViaje());

            
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
     
    
    public static int eliminar(AsientoVO asiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, asiento.getNumero());
            stmt.setInt(2, asiento.getIdViaje());

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
