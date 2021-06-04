/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDAO;

import ClassVO.DestinoVO;
import ClassVO.EstadoVO;
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
public class DestinoDAO {

    private static final String SQL_SELECT = "SELECT * "
            + " FROM destino";

    private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM destino WHERE id=?";

    private static final String SQL_SELECT_BY_ID_ESTADO = "SELECT * "
            + " FROM destino WHERE id_estado=?";

    /*private static final String SQL_SELECT_BY_NAME = "SELECT * "
            + " FROM categoria WHERE nombre = ?";*/
    private static final String SQL_UPDATE = "UPDATE destino "
            + " SET ciudad= ?, id_estado = ? WHERE id=?";

    private static final String SQL_INSERT = "INSERT INTO destino "
            + " VALUES(null,?, ?)";

    private static final String SQL_DELETE = "DELETE FROM destino WHERE id=?";

    public ArrayList<DestinoVO> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DestinoVO destino = null;
        ArrayList<DestinoVO> destinos = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ciudad = rs.getString("ciudad");
                int idEstado = rs.getInt("id_estado");

                destino = new DestinoVO(id, ciudad, idEstado);
                destinos.add(destino);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return destinos;
    }

    public DestinoVO encontrar(int _id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DestinoVO destino = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, _id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String ciudad = rs.getString("ciudad");
                int idEstado = rs.getInt("id_estado");

                destino = new DestinoVO(id, ciudad, idEstado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return destino;
    }

    public ArrayList<DestinoVO> encontrar(String _nombre) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DestinoVO destino = null;
        ArrayList<DestinoVO> destinos = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM destino where ciudad like '"+ _nombre +"%'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ciudad = rs.getString("ciudad");
                int idEstado = rs.getInt("id_estado");

                destino = new DestinoVO(id, ciudad, idEstado);
                destinos.add(destino);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return destinos;
    }

    public DestinoVO encontrarEstado(int _idEstado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DestinoVO destino = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_ESTADO);
            stmt.setInt(1, _idEstado);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String ciudad = rs.getString("ciudad");
                int idEstado = rs.getInt("id_estado");

                destino = new DestinoVO(id, ciudad, idEstado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return destino;
    }

    public int insertar(DestinoVO destino) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, destino.getCiudad());
            stmt.setInt(2, destino.getIdEstado());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(DestinoVO destino) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, destino.getCiudad());
            stmt.setInt(2, destino.getIdEstado());
            stmt.setInt(3, destino.getId());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(DestinoVO destino) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, destino.getId());

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
