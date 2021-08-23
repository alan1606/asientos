/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDAO;

import ClassVO.AsientoVO;
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
public class AsientoDAO {

    private static final String SQL_SELECT = "SELECT * "
            + " FROM asiento";

    private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM asiento WHERE numero = ? and id_viaje = ?";

    private static final String SQL_SELECT_BY_ID_VIAJE = "SELECT * "
            + " FROM asiento WHERE  id_viaje = ?";
    
    private static final String SQL_SELECT_BY_ID_VIAJE_CLIENTE = "SELECT * "
            + " FROM asiento WHERE  id_viaje = ? and id_cliente = ? and disponible=false";

    private static final String SQL_SELECT_NO_ASIENTOS = "SELECT count(*) as comprados "
            + " FROM asiento WHERE  id_cliente = ? and id_viaje = ? and disponible=false";
    
    private static final String SQL_SELECT_BY_LAST_VIAJE = "SELECT * "
            + " FROM asiento WHERE  id_viaje = ? and numero > 47 and numero < 65";

    /*private static final String SQL_SELECT_BY_NAME = "SELECT * "
            + " FROM categoria WHERE nombre = ?";*/
    private static final String SQL_UPDATE = "UPDATE asiento "
            + " SET id_cliente= ?, disponible=? WHERE id =?";

    private static final String SQL_INSERT = "INSERT INTO asiento "
            + " VALUES(NULL, ?,?,?,?)";

    private static final String SQL_DELETE = "DELETE FROM asiento WHERE numero = ? and id_viaje=?";

    private static final String SQL_DELETE_VIAJE = "DELETE FROM asiento WHERE id_viaje=?";
    
    private static final String SQL_DELETE_VIAJE_LIMITS = "DELETE FROM asiento WHERE id_viaje=? and numero >=? and numero <=?";

    public ArrayList<AsientoVO> listar() {
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
                int id = rs.getInt("id");
                int numero = rs.getInt("numero");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                boolean disponible = rs.getBoolean("disponible");

                asiento = new AsientoVO(id, numero, idViaje, idCliente, disponible);
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

    public AsientoVO encontrar(int _numero, int _idViaje) {
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
                int id = rs.getInt("id");
                int numero = rs.getInt("numero");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                boolean disponible = rs.getBoolean("disponible");

                asiento = new AsientoVO(id, numero, idViaje, idCliente, disponible);
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

    public ArrayList<AsientoVO> encontrar(int _idViaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AsientoVO asiento = null;
        ArrayList<AsientoVO> asientos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_VIAJE);
            stmt.setInt(1, _idViaje);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int numero = rs.getInt("numero");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                boolean disponible = rs.getBoolean("disponible");

                asiento = new AsientoVO(id, numero, idViaje, idCliente, disponible);
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
    
    public ArrayList<AsientoVO> encontrarViajeCliente(int _idViaje, int _idCliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AsientoVO asiento = null;
        ArrayList<AsientoVO> asientos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_VIAJE_CLIENTE);
            stmt.setInt(1, _idViaje);
            stmt.setInt(2, _idCliente);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int numero = rs.getInt("numero");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                boolean disponible = rs.getBoolean("disponible");

                asiento = new AsientoVO(id, numero, idViaje, idCliente, disponible);
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
    
    public int encontrarComprados(int _idViaje, int _idCliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int asientos = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_NO_ASIENTOS);
            stmt.setInt(1, _idCliente);
            stmt.setInt(2, _idViaje);
            rs = stmt.executeQuery();
            if (rs.next()) {
                asientos = rs.getInt("comprados");
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

    public ArrayList<AsientoVO> encontrarUltimos(ViajeVO viaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AsientoVO asiento = null;
        ArrayList<AsientoVO> asientos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_LAST_VIAJE);
            stmt.setInt(1, viaje.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int numero = rs.getInt("numero");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                boolean disponible = rs.getBoolean("disponible");

                asiento = new AsientoVO(id, numero, idViaje, idCliente, disponible);
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

    public int insertar(AsientoVO asiento) {
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

    public int insertar(ViajeVO viaje, int inicio, int fin) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        AsientoVO temp;
        try {
            conn = Conexion.getConnection();
            for (int i = inicio; i <= fin; i++) {
                stmt = conn.prepareStatement(SQL_INSERT);
                temp = new AsientoVO(i, viaje.getId(), 1, true);
                stmt.setInt(1, temp.getNumero());
                stmt.setInt(2, temp.getIdViaje());
                stmt.setInt(3, temp.getIdCliente());
                stmt.setBoolean(4, temp.isDisponible());
                rows += stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(AsientoVO asiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, asiento.getIdCliente());
            stmt.setBoolean(2, asiento.isDisponible());
            stmt.setLong(3, asiento.getId());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(AsientoVO asiento) {
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

    public int eliminar(int idViaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_VIAJE);
            stmt.setInt(1, idViaje);

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int eliminar(int idViaje, int inicio, int fin) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_VIAJE_LIMITS);
            stmt.setInt(1, idViaje);
            stmt.setInt(2, inicio);
            stmt.setInt(3, fin);
            
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
