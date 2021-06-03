/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDAO;

import ClassVO.EstadoVO;
import Conexion.Conexion;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alanm
 */
public class EstadoDAO {

    private static final String SQL_SELECT = "SELECT * "
            + " FROM estado";

    private static final String SQL_SELECT_BY_ID_PAIS = "SELECT * "
            + " FROM estado WHERE id_pais=?";

    /*private static final String SQL_SELECT_BY_NAME = "SELECT * "
            + " FROM categoria WHERE nombre = ?";*/
    private static final String SQL_UPDATE = "UPDATE estado "
            + " SET id_pais = ?, nombre = ? WHERE id=?";

    private static final String SQL_INSERT = "INSERT INTO estado "
            + " VALUES(null,?, ?)";

    private static final String SQL_DELETE = "DELETE FROM estado WHERE id=?";

    public ArrayList<EstadoVO> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        EstadoVO estado = null;
        ArrayList<EstadoVO> estados = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idPais = rs.getInt("id_pais");
                String nombre = rs.getString("nombre");

                estado = new EstadoVO(id, idPais, nombre);
                estados.add(estado);
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

    public EstadoVO encontrar(int _idPais) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        EstadoVO estado = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_PAIS);
            stmt.setInt(1, _idPais);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int idPais = rs.getInt("id_pais");
                String nombre = rs.getString("nombre");

                estado = new EstadoVO(id, idPais, nombre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return estado;
    }

    public int insertar(EstadoVO estado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, estado.getIdPais());
            stmt.setString(2, estado.getNombre());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(EstadoVO estado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, estado.getIdPais());
            stmt.setString(2, estado.getNombre());
            stmt.setInt(3, estado.getId());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(EstadoVO estado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, estado.getId());

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
