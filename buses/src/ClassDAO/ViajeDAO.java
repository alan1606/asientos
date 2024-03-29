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
    
    private static final String SQL_SELECT = "SELECT id, id_destino, fecha, no_asientos, observaciones "
            + " FROM viaje";
    
    private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM viaje WHERE id = ?";
    
    private static final String SQL_SELECT_DESTINATION_ID = "SELECT * "
            + " FROM viaje WHERE id_destino = ? order by fecha";
    
    private static final String SQL_SELECT_DESTINATION_ID_DISTINCT_DATE = "SELECT distinct(fecha) as fecha "
            + " FROM viaje WHERE id_destino = ?";
    
    private static final String SQL_SELECT_DESTINATION_ID_DATE = "SELECT * "
            + " FROM viaje WHERE id_destino = ? and fecha = ? order by id desc";
    
    private static final String SQL_SELECT_DESTINATION_ID_DATE_ASIENTOS = "SELECT * "
            + " FROM viaje WHERE id_destino = ? and fecha = ? and no_asientos = ? order by id desc";
    
    private static final String SQL_SELECT_BY_DATE = "SELECT * "
            + " FROM viaje WHERE fecha = ?";
    
    private static final String SQL_UPDATE = "UPDATE viaje "
            + " SET fecha= ?, no_asientos=?, observaciones = ? WHERE id =?";
    
    private static final String SQL_INSERT = "INSERT INTO viaje "
            + " VALUES(null,?,?,?,?)";
    
    private static final String SQL_DELETE = "DELETE FROM viaje WHERE id=?";
    
   private static final String SQL_SELECT_BY_CLIENT = "SELECT distinct(viaje.id), viaje.id_destino, viaje.fecha, viaje.no_asientos, viaje.observaciones "
            + " from viaje join detalle on viaje.id = detalle.id_viaje where detalle.id_cliente = ?;";
    
    public ArrayList<ViajeVO> listar() {
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
                String fecha = rs.getString("fecha");
                int noAsientos = rs.getInt("no_asientos");
                String observaciones = rs.getString("observaciones");
                
                viaje = new ViajeVO(id, idDestino, fecha, noAsientos, observaciones);
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
    
    public ViajeVO encontrar(int _id) {
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
                String fecha = rs.getString("fecha");
                int noAsientos = rs.getInt("no_asientos");
                String observaciones = rs.getString("observaciones");
                
                viaje = new ViajeVO(id, idDestino, fecha, noAsientos, observaciones);
                
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
    
    public ArrayList<ViajeVO> encontrarByDestino(int _id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ViajeVO viaje = null;
        ArrayList<ViajeVO> viajes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_DESTINATION_ID);
            stmt.setInt(1, _id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idDestino = rs.getInt("id_destino");
                String fecha = rs.getString("fecha");
                int noAsientos = rs.getInt("no_asientos");
                String observaciones = rs.getString("observaciones");
                
                viaje = new ViajeVO(id, idDestino, fecha, noAsientos, observaciones);
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
    
    public ArrayList<ViajeVO> encontrarByCliente(int _idCliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ViajeVO viaje = null;
        ArrayList<ViajeVO> viajes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_CLIENT);
            stmt.setInt(1, _idCliente);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idDestino = rs.getInt("id_destino");
                String fecha = rs.getString("fecha");
                int noAsientos = rs.getInt("no_asientos");
                String observaciones = rs.getString("observaciones");
                
                viaje = new ViajeVO(id, idDestino, fecha, noAsientos, observaciones);
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
    
    public ArrayList<Object> encontrarByDestinoDistinctDate(int _id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ViajeVO viaje = null;
        ArrayList<Object> viajes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_DESTINATION_ID_DISTINCT_DATE);
            stmt.setInt(1, _id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                String fecha = rs.getString("fecha");
                
                viajes.add(fecha);
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
    
    public ArrayList<ViajeVO> encontrarByDestinoDate(int _id, String date) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ViajeVO viaje = null;
        ArrayList<ViajeVO> viajes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_DESTINATION_ID_DATE);
            stmt.setInt(1, _id);
            stmt.setString(2, date);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idDestino = rs.getInt("id_destino");
                String fecha = rs.getString("fecha");
                int noAsientos = rs.getInt("no_asientos");
                String observaciones = rs.getString("observaciones");
                
                viaje = new ViajeVO(id, idDestino, fecha, noAsientos, observaciones);
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
    
    public ArrayList encontrarByDestinoDateAsientos(int _id, String date, int _noAsientos) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ViajeVO viaje = null;
        ArrayList<ViajeVO> viajes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_DESTINATION_ID_DATE_ASIENTOS);
            stmt.setInt(1, _id);
            stmt.setString(2, date);
            stmt.setInt(3, _noAsientos);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idDestino = rs.getInt("id_destino");
                String fecha = rs.getString("fecha");
                int noAsientos = rs.getInt("no_asientos");
                String observaciones = rs.getString("observaciones");
                
                viaje = new ViajeVO(id, idDestino, fecha, noAsientos, observaciones);
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
    
    public ArrayList encontrarByFecha(String _fecha) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ViajeVO viaje = null;
        ArrayList<ViajeVO> viajes = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_DATE);
            stmt.setString(1, _fecha);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idDestino = rs.getInt("id_destino");
                String fecha = rs.getString("fecha");
                int noAsientos = rs.getInt("no_asientos");
                String observaciones = rs.getString("observaciones");
                
                viaje = new ViajeVO(id, idDestino, fecha, noAsientos, observaciones);
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
    
    public int insertar(ViajeVO viaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, viaje.getIdDestino());
            stmt.setString(2, viaje.getFecha());
            stmt.setInt(3, viaje.getNoAsientos());
            stmt.setString(4, viaje.getObservaciones());
            
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int actualizar(ViajeVO viaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, viaje.getFecha());
            stmt.setInt(2, viaje.getNoAsientos());
            stmt.setString(3, viaje.getObservaciones());
            stmt.setInt(4, viaje.getId());
            
            rows = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int eliminar(ViajeVO viaje) {
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
