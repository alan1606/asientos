/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDAO;

import ClassVO.ClienteVO;
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
public class ClienteDAO {

    private static final String SQL_SELECT = "SELECT * "
            + " FROM cliente";

    private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM cliente WHERE id=?";
    
    private static final String SQL_SELECT_IDS_IN_TRAVEL = "SELECT distinct(cliente.id) "
            + " from detalle join cliente on cliente.id = detalle.id_cliente where detalle.id_viaje = ?";
    
      private static final String SQL_SELECT_BY_TYPE = "SELECT * "
            + " FROM cliente WHERE tipo=? order by nombre";

    /*private static final String SQL_SELECT_BY_NAME = "SELECT * "
            + " FROM categoria WHERE nombre = ?";*/
    private static final String SQL_UPDATE = "UPDATE cliente "
            + " SET nombre= ?, telefono=?, correo=?, tipo=? WHERE id=?";

    private static final String SQL_INSERT = "INSERT INTO cliente "
            + " VALUES(null,?,?,?,?)";

    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id=?";

    public ArrayList<ClienteVO> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClienteVO cliente = null;
        ArrayList<ClienteVO> clientes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String tipo = rs.getString("tipo");

                cliente = new ClienteVO(id, nombre, telefono, correo, tipo);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return clientes;
    }

    public ClienteVO encontrar(int _id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClienteVO cliente = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, _id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String tipo = rs.getString("tipo");

                cliente = new ClienteVO(id, nombre, telefono, correo, tipo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return cliente;
    }

    
    public ArrayList<ClienteVO> encontrarViaje(int _idViaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClienteVO cliente = null;
        ArrayList<ClienteVO> clientes = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement( SQL_SELECT_IDS_IN_TRAVEL);
            stmt.setInt(1, _idViaje);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                ids.add(id);
            }
            for(Integer temporal: ids){
                clientes.add(encontrar(temporal));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return clientes;
    }
    
    public ArrayList<ClienteVO> encontrarNombre(String _nombre) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClienteVO cliente = null;
        ArrayList<ClienteVO> clientes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(  "SELECT * FROM cliente WHERE nombre like '" + _nombre +"%'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String tipo = rs.getString("tipo");

                cliente = new ClienteVO(id, nombre, telefono, correo, tipo);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return clientes;
    }
     
     
     public ArrayList<ClienteVO> encontrarTelefono(String _telefono) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClienteVO cliente = null;
        ArrayList<ClienteVO> clientes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(  "SELECT * FROM cliente WHERE telefono like '" + _telefono +"%'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String tipo = rs.getString("tipo");

                cliente = new ClienteVO(id, nombre, telefono, correo, tipo);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return clientes;
    }
    
     public ArrayList<ClienteVO> encontrarPorTipo(String _tipo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClienteVO cliente = null;
        ArrayList<ClienteVO> clientes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_TYPE);
            stmt.setString(1, _tipo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String tipo = rs.getString("tipo");

                cliente = new ClienteVO(id, nombre, telefono, correo, tipo);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return clientes;
    }
     
     public ArrayList<ClienteVO> encontrarCorreo(String _correo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClienteVO cliente = null;
        ArrayList<ClienteVO> clientes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(  "SELECT * FROM cliente WHERE correo like '" + _correo +"%'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String tipo = rs.getString("tipo");

                cliente = new ClienteVO(id, nombre, telefono, correo, tipo);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return clientes;
    }
     
    public int insertar(ClienteVO cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getCorreo());
            stmt.setString(4, cliente.getTipo());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(ClienteVO cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getCorreo());
            stmt.setString(4, cliente.getTipo());
            stmt.setInt(5, cliente.getId());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(ClienteVO cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, cliente.getId());

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
