/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDAO;

import ClassVO.UsuarioVO;
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
public class UsuarioDAO {

    private static final String SQL_SELECT = "SELECT * "
            + " FROM usuario";

    private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM usuario WHERE id = ?";
    
    private static final String SQL_SELECT_BY_USER = "SELECT * "
            + " FROM usuario WHERE usuario = ?";

    /*private static final String SQL_SELECT_BY_NAME = "SELECT * "
            + " FROM categoria WHERE nombre = ?";*/
    private static final String SQL_UPDATE = "UPDATE usuario "
            + " SET pass= ?, nombre=?, tipo=? WHERE id =?";

    private static final String SQL_INSERT = "INSERT INTO usuario "
            + " VALUES(null,?,?,?, ?)";

    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id=?";

    public static ArrayList<UsuarioVO> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioVO usuario = null;
        ArrayList<UsuarioVO> usuarios = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String usuarioName = rs.getString("usuario");
                String pass = rs.getString("pass");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");

                usuario = new UsuarioVO(id, usuarioName, pass, nombre, tipo);
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuarios;
    }

    public static UsuarioVO encontrar(int _id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioVO usuario = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, _id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String usuarioName = rs.getString("usuario");
                String pass = rs.getString("pass");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");

                usuario = new UsuarioVO(id, usuarioName, pass, nombre, tipo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuario;
    }

    public static UsuarioVO encontrar(String _usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioVO usuario = new UsuarioVO();
        usuario.setId(-1);
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_USER);
            stmt.setString(1, _usuario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String usuarioName = rs.getString("usuario");
                String pass = rs.getString("pass");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");

                usuario = new UsuarioVO(id, usuarioName, pass, nombre, tipo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuario;
    }
    
    public static int insertar(UsuarioVO usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getUsuario());
            stmt.setString(3, usuario.getPass());
            stmt.setString(4, usuario.getNombre());
            stmt.setString(5, usuario.getTipo());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public static int actualizar(UsuarioVO usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuario.getPass());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getTipo());
            stmt.setInt(4, usuario.getId());
            
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public static int eliminar(UsuarioVO usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getId());

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
