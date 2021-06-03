/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDAO;

import ClassVO.HotelEnDestinoEnViajeVO;
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
public class HotelEnDestinoEnViajeDAO {

    private static final String SQL_SELECT = "SELECT * "
            + " FROM hotel_destino_viaje";

    private static final String SQL_SELECT_BY_ID_HOTEL = "SELECT * "
            + " FROM hotel_destino_viaje WHERE id_hotel=?";

    private static final String SQL_SELECT_BY_ID_DESTINO = "SELECT * "
            + " FROM hotel_destino WHERE id_destino=?";
    
     private static final String SQL_SELECT_BY_ID_VIAJE = "SELECT * "
            + " FROM hotel_destino WHERE id_viaje=?";

    private static final String SQL_SELECT_BY_IDS = "SELECT * "
            + " FROM hotel_destino_viaje WHERE id_hotel = ? and id_destino=? and id_viaje=?";
    
    private static final String SQL_SELECT_BY_HOTEL_DESTINO = "SELECT * "
            + " FROM hotel_destino_viaje WHERE id_hotel = ? and id_destino=? ";

    /*private static final String SQL_SELECT_BY_NAME = "SELECT * "
            + " FROM categoria WHERE nombre = ?";*/
    private static final String SQL_UPDATE = "UPDATE hotel_destino_viaje "
            + " SET no_habitaciones = ? WHERE id_hotel=?  and id_destino= ? and id_viaje=?";

    private static final String SQL_INSERT = "INSERT INTO hotel_destino_viaje "
            + " VALUES(?,?,?,?)";

    private static final String SQL_DELETE = "DELETE FROM hotel_destino_viaje WHERE id_hotel=? and id_destino=? and id_viaje =?";

    public ArrayList<HotelEnDestinoEnViajeVO> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HotelEnDestinoEnViajeVO hotelEnDestinoEnViaje = null;
        ArrayList<HotelEnDestinoEnViajeVO> hotelesEnDestinoEnViaje = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idHotel = rs.getInt("id_hotel");
                int idDestino = rs.getInt("id_destino");
                int idViaje = rs.getInt("id_viaje");
                int noHabitaciones = rs.getInt("no_habiaciones");

                hotelEnDestinoEnViaje = new HotelEnDestinoEnViajeVO(idHotel, idDestino, idViaje, noHabitaciones);
                hotelesEnDestinoEnViaje.add(hotelEnDestinoEnViaje);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return hotelesEnDestinoEnViaje;
    }

    public HotelEnDestinoEnViajeVO encontrar(int _idHotel, int _idDestino) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HotelEnDestinoEnViajeVO hotelEnDestinoEnViaje = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_HOTEL_DESTINO);
            stmt.setInt(1, _idHotel);
            stmt.setInt(2, _idDestino);
            rs = stmt.executeQuery();
            if (rs.next()) {
               int idHotel = rs.getInt("id_hotel");
                int idDestino = rs.getInt("id_destino");
                int idViaje = rs.getInt("id_viaje");
                int noHabitaciones = rs.getInt("no_habiaciones");

                hotelEnDestinoEnViaje = new HotelEnDestinoEnViajeVO(idHotel, idDestino, idViaje, noHabitaciones);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return hotelEnDestinoEnViaje;
    }
    
     public HotelEnDestinoEnViajeVO encontrar(int _idHotel, int _idDestino, int _idViaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HotelEnDestinoEnViajeVO hotelEnDestinoEnViaje = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_IDS);
            stmt.setInt(1, _idHotel);
            stmt.setInt(2, _idDestino);
            stmt.setInt(3, _idViaje);
            rs = stmt.executeQuery();
            if (rs.next()) {
               int idHotel = rs.getInt("id_hotel");
                int idDestino = rs.getInt("id_destino");
                int idViaje = rs.getInt("id_viaje");
                int noHabitaciones = rs.getInt("no_habiaciones");

                hotelEnDestinoEnViaje = new HotelEnDestinoEnViajeVO(idHotel, idDestino, idViaje, noHabitaciones);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return hotelEnDestinoEnViaje;
    }
     
     public HotelEnDestinoEnViajeVO encontrarPorHotel(int _idHotel) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HotelEnDestinoEnViajeVO hotelEnDestinoEnViaje = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_HOTEL);
            stmt.setInt(1, _idHotel);

            rs = stmt.executeQuery();
            if (rs.next()) {
               int idHotel = rs.getInt("id_hotel");
                int idDestino = rs.getInt("id_destino");
                int idViaje = rs.getInt("id_viaje");
                int noHabitaciones = rs.getInt("no_habiaciones");

                hotelEnDestinoEnViaje = new HotelEnDestinoEnViajeVO(idHotel, idDestino, idViaje, noHabitaciones);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return hotelEnDestinoEnViaje;
    }

     public HotelEnDestinoEnViajeVO encontrarPorDestino(int _idDestino) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HotelEnDestinoEnViajeVO hotelEnDestinoEnViaje = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_DESTINO);
            stmt.setInt(1, _idDestino);

            rs = stmt.executeQuery();
            if (rs.next()) {
               int idHotel = rs.getInt("id_hotel");
                int idDestino = rs.getInt("id_destino");
                int idViaje = rs.getInt("id_viaje");
                int noHabitaciones = rs.getInt("no_habiaciones");

                hotelEnDestinoEnViaje = new HotelEnDestinoEnViajeVO(idHotel, idDestino, idViaje, noHabitaciones);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return hotelEnDestinoEnViaje;
    }
    
      public HotelEnDestinoEnViajeVO encontrarPorViaje(int _idViaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HotelEnDestinoEnViajeVO hotelEnDestinoEnViaje = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_VIAJE);
            stmt.setInt(1, _idViaje);

            rs = stmt.executeQuery();
            if (rs.next()) {
               int idHotel = rs.getInt("id_hotel");
                int idDestino = rs.getInt("id_destino");
                int idViaje = rs.getInt("id_viaje");
                int noHabitaciones = rs.getInt("no_habiaciones");

                hotelEnDestinoEnViaje = new HotelEnDestinoEnViajeVO(idHotel, idDestino, idViaje, noHabitaciones);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return hotelEnDestinoEnViaje;
    }
     
    public int insertar(HotelEnDestinoEnViajeVO hotelEnDestinoEnViaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, hotelEnDestinoEnViaje.getIdHotel());
            stmt.setInt(2, hotelEnDestinoEnViaje.getIdDestino());
            stmt.setInt(3, hotelEnDestinoEnViaje.getIdViaje());
            stmt.setInt(4, hotelEnDestinoEnViaje.getNoHabitaciones());

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
    public int eliminar(HotelEnDestinoEnViajeVO hotelEnDestinoEnViaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, hotelEnDestinoEnViaje.getIdHotel());
            stmt.setInt(2, hotelEnDestinoEnViaje.getIdDestino());
            stmt.setInt(3, hotelEnDestinoEnViaje.getIdViaje());

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
