/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDAO;

import ClassVO.PaisVO;
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
public class PaisDAO {
    private static final String SQL_SELECT = "SELECT * "
            + " FROM pais order by nombre";

    private static final String SQL_SELECT_BY_ID_PAIS = "SELECT * "
            + " FROM pais WHERE id=?";

    /*private static final String SQL_SELECT_BY_NAME = "SELECT * "
            + " FROM categoria WHERE nombre = ?";*/
    private static final String SQL_UPDATE = "UPDATE pais "
            + " SET nombre = ? WHERE id=?";

    private static final String SQL_INSERT = "INSERT INTO pais "
            + " VALUES(null,?)";

    private static final String SQL_DELETE = "DELETE FROM pais WHERE id=?";

    public ArrayList<PaisVO> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PaisVO pais = null;
        ArrayList<PaisVO> estados = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                pais = new PaisVO(id, nombre);
                estados.add(pais);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return estados;
    }

    public PaisVO encontrar(int _id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PaisVO pais = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_PAIS);
            stmt.setInt(1, _id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                pais = new PaisVO(id, nombre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return pais;
    }

    public int insertar(PaisVO pais) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, pais.getNombre());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(PaisVO pais) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, pais.getNombre());
            stmt.setInt(2, pais.getId());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(PaisVO pais) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, pais.getId());

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
