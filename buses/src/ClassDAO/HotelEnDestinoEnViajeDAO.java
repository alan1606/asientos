/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDAO;

import ClassVO.DetalleVO;
import ClassVO.HotelEnDestinoEnViajeVO;
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
public class HotelEnDestinoEnViajeDAO {

    private static final String SQL_SELECT = "SELECT * "
            + " FROM hotel_destino_viaje";

    private static final String SQL_SELECT_BY_ID_HOTEL = "SELECT * "
            + " FROM hotel_destino_viaje WHERE id_hotel=?";
    
        private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM hotel_destino_viaje WHERE id=?";

    private static final String SQL_SELECT_BY_ID_DESTINO = "SELECT * "
            + " FROM hotel_destino WHERE id_destino=?";

    private static final String SQL_SELECT_BY_ID_DESTINO_VIAJE = "SELECT hotel_destino_viaje.id as id, hotel.nombre as nombre, no_habitaciones, habitaciones_disponibles,id_hotel "
            + " FROM hotel_destino_viaje JOIN hotel on hotel.id = hotel_destino_viaje.id_hotel WHERE id_destino=? and id_viaje = ?";

    private static final String SQL_SELECT_BY_ID_VIAJE = "SELECT * "
            + " FROM hotel_destino WHERE id_viaje=?";

    private static final String SQL_SELECT_BY_IDS = "SELECT * "
            + " FROM hotel_destino_viaje WHERE id_hotel = ? and id_destino=? and id_viaje=?";

    private static final String SQL_SELECT_BY_HOTEL_DESTINO = "SELECT * "
            + " FROM hotel_destino_viaje WHERE id_hotel = ? and id_destino=? ";

    /*private static final String SQL_SELECT_BY_NAME = "SELECT * "
            + " FROM categoria WHERE nombre = ?";*/
    
    private static final String SQL_UPDATE = "UPDATE hotel_destino_viaje "
            + " SET no_habitaciones = ?, habitaciones_disponibles = ? WHERE id=?";

    private static final String SQL_INSERT = "INSERT INTO hotel_destino_viaje "
            + " VALUES(null, ?,?,?,?,?)";

    private static final String SQL_DELETE = "DELETE FROM hotel_destino_viaje WHERE id=?";

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
                int noHabitaciones = rs.getInt("no_habitaciones");
                int habitacionesDisponibles = rs.getInt("habitaciones_disponibles");

                hotelEnDestinoEnViaje = new HotelEnDestinoEnViajeVO(idHotel, idDestino, idViaje, noHabitaciones, habitacionesDisponibles);
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
                int id = rs.getInt("id");
                int idHotel = rs.getInt("id_hotel");
                int idDestino = rs.getInt("id_destino");
                int idViaje = rs.getInt("id_viaje");
                int noHabitaciones = rs.getInt("no_habitaciones");
                int habitacionesDisponibles = rs.getInt("habitaciones_disponibles");

                hotelEnDestinoEnViaje = new HotelEnDestinoEnViajeVO(id, idHotel, idDestino, idViaje, noHabitaciones, habitacionesDisponibles);
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
                int id = rs.getInt("id");
                int idHotel = rs.getInt("id_hotel");
                int idDestino = rs.getInt("id_destino");
                int idViaje = rs.getInt("id_viaje");
                int noHabitaciones = rs.getInt("no_habitaciones");
                int habitacionesDisponibles = rs.getInt("habitaciones_disponibles");

                hotelEnDestinoEnViaje = new HotelEnDestinoEnViajeVO(id, idHotel, idDestino, idViaje, noHabitaciones, habitacionesDisponibles);
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
                int noHabitaciones = rs.getInt("no_habitaciones");
                int habitacionesDisponibles = rs.getInt("habitaciones_disponibles");

                hotelEnDestinoEnViaje = new HotelEnDestinoEnViajeVO(idHotel, idDestino, idViaje, noHabitaciones, habitacionesDisponibles);
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
                int noHabitaciones = rs.getInt("no_habitaciones");
                int habitacionesDisponibles = rs.getInt("habitaciones_disponibles");

                hotelEnDestinoEnViaje = new HotelEnDestinoEnViajeVO(idHotel, idDestino, idViaje, noHabitaciones, habitacionesDisponibles);
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

    public ArrayList<Object[]> encontrarPorDestinoViaje(int _idDestino, int _idViaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HotelEnDestinoEnViajeVO hotelEnDestinoEnViaje = null;
        ArrayList<Object[]> objetos = new ArrayList<>();
        Object[] objeto = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_DESTINO_VIAJE);
            stmt.setInt(1, _idDestino);
            stmt.setInt(2, _idViaje);

            rs = stmt.executeQuery();
            while (rs.next()) {
                int idD = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int noHabitaciones = rs.getInt("no_habitaciones");
                int habitacionesDisponibles = rs.getInt("habitaciones_disponibles");
                int idHotel = rs.getInt("id_hotel");
                int registrado = 1;

                objeto = new Object[6];
                objeto[0] = idD;
                objeto[1] = nombre;
                objeto[2] = noHabitaciones;
                objeto[3] = habitacionesDisponibles;
                objeto[4] = idHotel;
                objeto[5] = registrado;

                objetos.add(objeto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return objetos;
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
                int noHabitaciones = rs.getInt("no_habitaciones");
                int habitacionesDisponibles = rs.getInt("habitaciones_disponibles");

                hotelEnDestinoEnViaje = new HotelEnDestinoEnViajeVO(idHotel, idDestino, idViaje, noHabitaciones, habitacionesDisponibles);
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
            stmt.setInt(5, hotelEnDestinoEnViaje.getHabitacionesDisponibles());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public static int actualizar(HotelEnDestinoEnViajeVO hotelEnDestinoEnViaje) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, hotelEnDestinoEnViaje.getNoHabitaciones());
            stmt.setInt(2, hotelEnDestinoEnViaje.getHabitacionesDisponibles());
            stmt.setInt(3, hotelEnDestinoEnViaje.getId());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public HotelEnDestinoEnViajeVO encontrar(int _idHotelDestinoViaje) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HotelEnDestinoEnViajeVO hotelEnDestinoEnViaje = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, _idHotelDestinoViaje);

            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int idHotel = rs.getInt("id_hotel");
                int idDestino = rs.getInt("id_destino");
                int idViaje = rs.getInt("id_viaje");
                int noHabitaciones = rs.getInt("no_habitaciones");
                int habitacionesDisponibles = rs.getInt("habitaciones_disponibles");

                hotelEnDestinoEnViaje = new HotelEnDestinoEnViajeVO(id, idHotel, idDestino, idViaje, noHabitaciones, habitacionesDisponibles);
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
    
       

    public int eliminar(DetalleVO detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setLong(1, detalle.getId());

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
