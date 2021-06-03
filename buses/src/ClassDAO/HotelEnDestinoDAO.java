/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDAO;

import ClassVO.HotelEnDestinoVO;
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
public class HotelEnDestinoDAO {

    private static final String SQL_SELECT = "SELECT * "
            + " FROM hotel_destino";

    private static final String SQL_SELECT_BY_ID_HOTEL = "SELECT * "
            + " FROM hotel_destino WHERE id_hotel=?";

    private static final String SQL_SELECT_BY_ID_DESTINO = "SELECT * "
            + " FROM hotel_destino WHERE id_destino=?";

    private static final String SQL_SELECT_BY_IDS = "SELECT * "
            + " FROM hotel_destino WHERE id_hotel = ? and id_destino=?";

    /*private static final String SQL_SELECT_BY_NAME = "SELECT * "
            + " FROM categoria WHERE nombre = ?";*/
 /* private static final String SQL_UPDATE = "UPDATE hotel_destino "
            + " SET nombre = ? WHERE id=?";*/
    private static final String SQL_INSERT = "INSERT INTO hotel_destino "
            + " VALUES(?,?)";

    private static final String SQL_DELETE = "DELETE FROM hotel_destino WHERE id_hotel=? and id_destino=?";

    public ArrayList<HotelEnDestinoVO> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HotelEnDestinoVO hotelEnDestino = null;
        ArrayList<HotelEnDestinoVO> hoteles = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idHotel = rs.getInt("id_hotel");
                int idDestino = rs.getInt("id_destino");

                hotelEnDestino = new HotelEnDestinoVO(idHotel, idDestino);
                hoteles.add(hotelEnDestino);
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

    public HotelEnDestinoVO encontrar(int _idHotel, int _idDestino) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HotelEnDestinoVO hotel = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_IDS);
            stmt.setInt(1, _idHotel);
            stmt.setInt(2, _idDestino);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int idHotel = rs.getInt("id_hotel");
                int idDestino = rs.getInt("id_destino");

                hotel = new HotelEnDestinoVO(idHotel, idDestino);
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
    
    public HotelEnDestinoVO encontrar(int _idHotel) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HotelEnDestinoVO hotel = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_HOTEL);
            stmt.setInt(1, _idHotel);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int idHotel = rs.getInt("id_hotel");
                int idDestino = rs.getInt("id_destino");

                hotel = new HotelEnDestinoVO(idHotel, idDestino);
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

    public int insertar(HotelEnDestinoVO hotelEnDestino) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, hotelEnDestino.getIdHotel());
            stmt.setInt(2, hotelEnDestino.getIdDestino());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    /*public static int actualizar(HotelEnDestinoVO hotelEnDestino) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, hotelEnDestino.getNombre());
            stmt.setInt(2, hotelEnDestino.getId());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }*/
    public int eliminar(HotelEnDestinoVO hotelEnDestino) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, hotelEnDestino.getIdHotel());
            stmt.setInt(2, hotelEnDestino.getIdDestino());

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
