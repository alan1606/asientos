/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDAO;

import ClassVO.HotelEnDestinoVO;
import ClassVO.HotelVO;
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
public class HotelDAO {

    private static final String SQL_SELECT = "SELECT * "
            + " FROM hotel";

    private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM hotel WHERE id=?";


    /*private static final String SQL_SELECT_BY_NAME = "SELECT * "
            + " FROM categoria WHERE nombre = ?";*/
    private static final String SQL_UPDATE = "UPDATE hotel "
            + " SET nombre = ? WHERE id=?";

    private static final String SQL_INSERT = "INSERT INTO hotel "
            + " VALUES(null,?)";

    private static final String SQL_DELETE = "DELETE FROM hotel WHERE id=?";

    private static final String SQL_SELECT_ON_DESTINATION = "select hotel.id as id, hotel.nombre as nombre from hotel_destino "
            +  " join hotel on hotel.id = hotel_destino.id_hotel where id_destino =  ?;";
  

    public ArrayList<HotelVO> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HotelVO hotel = null;
        ArrayList<HotelVO> hoteles = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                hotel = new HotelVO(id, nombre);
                hoteles.add(hotel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return hoteles;
    }

    public HotelVO encontrar(int _id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HotelVO hotel = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, _id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                hotel = new HotelVO(id, nombre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return hotel;
    }

 public ArrayList encontrarByDestino(int _id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HotelVO hotel = null;
        ArrayList<HotelVO> hoteles = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ON_DESTINATION);
            stmt.setInt(1, _id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idHotel = rs.getInt("id");
                String nombre = rs.getString("nombre");

                hotel = new HotelVO(idHotel, nombre);
                hoteles.add(hotel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return hoteles;
    }

    public int insertar(HotelVO hotel) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, hotel.getNombre());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(HotelVO hotel) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, hotel.getNombre());
            stmt.setInt(2, hotel.getId());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(HotelVO hotel) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, hotel.getId());

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
