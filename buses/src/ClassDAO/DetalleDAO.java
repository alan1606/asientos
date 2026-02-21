/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDAO;

import ClassVO.DetalleVO;
import ClassVO.InfoPagoVO;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author alanm
 */
public class DetalleDAO {

    private static final String SQL_SELECT = "SELECT * "
            + " FROM detalle";

    private static final String SQL_SELECT_LAST = "SELECT * "
            + " FROM detalle order by id desc limit 1";

    private static final String SQL_SELECT_BY_ID = "SELECT * "
            + " FROM detalle WHERE id_viaje=? and id_cliente = ? and id_usuario = ? order by id desc";

    private static final String SQL_SELECT_BY_ID_VIAJE_CLIENTE = "SELECT * "
            + " FROM detalle WHERE id_viaje=? and id_cliente = ? and estado = 'VENDIDO'";

    private static final String SQL_SELECT_BY_ID_UNIQUE = "SELECT * "
            + " FROM detalle WHERE id = ?";

    /*private static final String SQL_SELECT_BY_NAME = "SELECT * "
            + " FROM categoria WHERE nombre = ?";*/
    private static final String SQL_UPDATE = "UPDATE detalle  "
            + " SET sube=?, hora = ?, liquidado = ?, estado = ?, hora_regreso=? WHERE id=?";

    private static final String SQL_INSERT = "INSERT INTO detalle values "
            + " (null,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE = "DELETE FROM detalle WHERE id = ?";

    private static final String SQL_SELECT_ALL_FOR_SALES = "SELECT "
            + " d.id, d.fecha_venta, de.ciudad, c.nombre, d.personas, "
            + " d.fecha_regreso, d.costo, d.anticipo, u.nombre as vendedor "
            + " FROM detalle d "
            + " LEFT JOIN cliente AS c ON c.id = d.id_cliente "
            + " LEFT JOIN usuario AS u ON u.id = d.id_usuario "
            + " LEFT JOIN viaje AS v ON v.id = d.id_viaje "
            + " LEFT JOIN destino AS de ON de.id = v.id_destino "
            + " WHERE d.estado != 'CANCELADO' ";
    // Constante para búsqueda (se le concatenará el filtro en el método)
    private static final String SQL_SEARCH_SALES = SQL_SELECT_ALL_FOR_SALES
            + " AND (d.id = ? OR c.nombre LIKE ?) ";
    public ArrayList<DetalleVO> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleVO detalle = null;
        ArrayList<DetalleVO> detalles = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                int idUsuario = rs.getInt("id_usuario");
                int personas = rs.getInt("personas");
                String sube = rs.getString("sube");
                String hora = rs.getString("hora");
                int habitaciones = rs.getInt("habitacion");
                double costo = rs.getDouble("costo");
                double anticipo = rs.getDouble("anticipo");
                boolean liquidado = rs.getBoolean("liquidado");
                String estado = rs.getString("estado");
                String pago = rs.getString("pago");
                String viaje = rs.getString("viaje");
                String horaRegreso = rs.getString("hora_regreso");
                String fechaRegreso = rs.getString("fecha_regreso");
                String fechaVenta = rs.getString("fecha_venta");

                detalle = new DetalleVO(id, idViaje, idCliente, idUsuario, personas, sube, hora, habitaciones, costo, anticipo, liquidado, estado, pago, viaje, horaRegreso, fechaRegreso, fechaVenta);
                detalles.add(detalle);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return detalles;
    }

    public ArrayList<DetalleVO> encontrar(int _idViaje, int _idCliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleVO detalle = null;
        ArrayList<DetalleVO> detalles = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_VIAJE_CLIENTE);
            stmt.setInt(1, _idViaje);
            stmt.setInt(2, _idCliente);
            rs = stmt.executeQuery();
            while (rs.next()) {
               Long id = rs.getLong("id");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                int idUsuario = rs.getInt("id_usuario");
                int personas = rs.getInt("personas");
                String sube = rs.getString("sube");
                String hora = rs.getString("hora");
                int habitaciones = rs.getInt("habitacion");
                double costo = rs.getDouble("costo");
                double anticipo = rs.getDouble("anticipo");
                boolean liquidado = rs.getBoolean("liquidado");
                String estado = rs.getString("estado");
                String pago = rs.getString("pago");
                String viaje = rs.getString("viaje");
                String horaRegreso = rs.getString("hora_regreso");
                String fechaRegreso = rs.getString("fecha_regreso");
                String fechaVenta = rs.getString("fecha_venta");

                detalle = new DetalleVO(id, idViaje, idCliente, idUsuario, personas, sube, hora, habitaciones, costo, anticipo, liquidado, estado, pago, viaje, horaRegreso, fechaRegreso, fechaVenta);
                detalles.add(detalle);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return detalles;
    }

    public DetalleVO encontrar(int _idViaje, int _idCliente, int _idUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleVO detalle = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, _idViaje);
            stmt.setInt(2, _idCliente);
            stmt.setInt(3, _idUsuario);
            rs = stmt.executeQuery();
            if (rs.next()) {
               Long id = rs.getLong("id");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                int idUsuario = rs.getInt("id_usuario");
                int personas = rs.getInt("personas");
                String sube = rs.getString("sube");
                String hora = rs.getString("hora");
                int habitaciones = rs.getInt("habitacion");
                double costo = rs.getDouble("costo");
                double anticipo = rs.getDouble("anticipo");
                boolean liquidado = rs.getBoolean("liquidado");
                String estado = rs.getString("estado");
                String pago = rs.getString("pago");
                String viaje = rs.getString("viaje");
                String horaRegreso = rs.getString("hora_regreso");
                String fechaRegreso = rs.getString("fecha_regreso");
                String fechaVenta = rs.getString("fecha_venta");

                detalle = new DetalleVO(id, idViaje, idCliente, idUsuario, personas, sube, hora, habitaciones, costo, anticipo, liquidado, estado, pago, viaje, horaRegreso, fechaRegreso, fechaVenta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return detalle;
    }

    public DetalleVO encontrar(Long _id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleVO detalle = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID_UNIQUE);
            stmt.setLong(1, _id);
            rs = stmt.executeQuery();
            if (rs.next()) {
               Long id = rs.getLong("id");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                int idUsuario = rs.getInt("id_usuario");
                int personas = rs.getInt("personas");
                String sube = rs.getString("sube");
                String hora = rs.getString("hora");
                int habitaciones = rs.getInt("habitacion");
                double costo = rs.getDouble("costo");
                double anticipo = rs.getDouble("anticipo");
                boolean liquidado = rs.getBoolean("liquidado");
                String estado = rs.getString("estado");
                String pago = rs.getString("pago");
                String viaje = rs.getString("viaje");
                String horaRegreso = rs.getString("hora_regreso");
                String fechaRegreso = rs.getString("fecha_regreso");
                String fechaVenta = rs.getString("fecha_venta");

                detalle = new DetalleVO(id, idViaje, idCliente, idUsuario, personas, sube, hora, habitaciones, costo, anticipo, liquidado, estado, pago, viaje, horaRegreso, fechaRegreso, fechaVenta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return detalle;
    }

    public int insertar(DetalleVO detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, detalle.getIdViaje());
            stmt.setInt(2, detalle.getIdCliente());
            stmt.setInt(3, detalle.getIdUsuario());
            stmt.setInt(4, detalle.getPersonas());
            stmt.setString(5, detalle.getSube());
            stmt.setString(6, detalle.getHora());
            stmt.setInt(7, detalle.getHabitaciones());
            stmt.setDouble(8, detalle.getCosto());
            stmt.setDouble(9, detalle.getAnticipo());
            stmt.setBoolean(10, detalle.isLiquidado());
            stmt.setString(11, detalle.getEstado());
            stmt.setString(12, detalle.getPago());
            stmt.setString(13, detalle.getViaje());
            stmt.setString(14, detalle.getHoraRegreso());
            stmt.setString(15, detalle.getFechaRegreso());
            stmt.setString(16, detalle.getFechaVenta());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(DetalleVO detalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, detalle.getSube());
            stmt.setString(2, detalle.getHora());
            stmt.setBoolean(3, detalle.isLiquidado());
            stmt.setString(4, detalle.getEstado());
            stmt.setString(5, detalle.getHoraRegreso());
            stmt.setLong(6, detalle.getId());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
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

    public DetalleVO encontrarUltimo() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleVO detalle = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_LAST);
            rs = stmt.executeQuery();
            if (rs.next()) {

                Long id = rs.getLong("id");
                int idViaje = rs.getInt("id_viaje");
                int idCliente = rs.getInt("id_cliente");
                int idUsuario = rs.getInt("id_usuario");
                int personas = rs.getInt("personas");
                String sube = rs.getString("sube");
                String hora = rs.getString("hora");
                int habitaciones = rs.getInt("habitacion");
                double costo = rs.getDouble("costo");
                double anticipo = rs.getDouble("anticipo");
                boolean liquidado = rs.getBoolean("liquidado");
                String estado = rs.getString("estado");
                String pago = rs.getString("pago");
                String viaje = rs.getString("viaje");
                String horaRegreso = rs.getString("hora_regreso");
                String fechaRegreso = rs.getString("fecha_regreso");
                String fechaVenta = rs.getString("fecha_venta");

                detalle = new DetalleVO(id, idViaje, idCliente, idUsuario, personas, sube, hora, habitaciones, costo, anticipo, liquidado, estado, pago, viaje, horaRegreso, fechaRegreso, fechaVenta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return detalle;
    }
    /**
     * Lista todas las ventas activas con información de cliente y destino
     */
    public ArrayList<DetalleVO> listarVentasParaTabla() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<DetalleVO> detalles = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ALL_FOR_SALES + " ORDER BY d.id DESC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                detalles.add(mapearVenta(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return detalles;
    }

  /**
 * AGREGAR ESTOS MÉTODOS A DetalleDAO.java
 * 
 * Métodos para paginación optimizada de ventas
 */

/**
 * ✅ NUEVO: Lista ventas con paginación (20 registros)
 * 
 * @param offset Desde qué registro empezar (0, 20, 40, ...)
 * @param limit Cuántos registros traer (20)
 * @return Lista de ventas paginadas
 */
public ArrayList<DetalleVO> listarVentasPaginadas(int offset, int limit) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    ArrayList<DetalleVO> ventas = new ArrayList<>();
    
    try {
        conn = Conexion.getConnection();
        
        // Query optimizado con LIMIT y OFFSET
        String sql = 
            "SELECT " +
            "    d.id, " +
            "    DATE_FORMAT(d.fecha_venta, '%d/%m/%Y') as fecha_venta, " +
            "    dest.ciudad, " +
            "    c.nombre as nombreCliente, " +
            "    d.personas, " +
            "    DATE_FORMAT(d.fecha_regreso, '%d/%m/%Y') as fecha_regreso, " +
            "    d.costo, " +
            "    d.anticipo, " +
            "    u.nombre as nombreVendedor " +
            "FROM detalle d " +
            "JOIN cliente c ON c.id = d.id_cliente " +
            "JOIN viaje v ON v.id = d.id_viaje " +
            "JOIN destino dest ON dest.id = v.id_destino " +
            "JOIN usuario u ON u.id = d.id_usuario " +
            "WHERE d.estado = 'VENDIDO' " +
            "ORDER BY d.id DESC " +        // Orden descendente (más recientes primero)
            "LIMIT ? OFFSET ?";
        
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, limit);
        stmt.setInt(2, offset);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            DetalleVO vo = new DetalleVO();
            vo.setId(rs.getLong("id"));
            vo.setFechaVenta(rs.getString("fecha_venta"));
            vo.setCiudad(rs.getString("ciudad"));
            vo.setNombreCliente(rs.getString("nombreCliente"));
            vo.setPersonas(rs.getInt("personas"));
            vo.setFechaRegreso(rs.getString("fecha_regreso"));
            vo.setCosto(rs.getDouble("costo"));
            vo.setAnticipo(rs.getDouble("anticipo"));
            vo.setNombreVendedor(rs.getString("nombreVendedor"));
            ventas.add(vo);
        }
        
    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    } finally {
        Conexion.close(rs);
        Conexion.close(stmt);
        Conexion.close(conn);
    }
    
    return ventas;
}

/**
 * ✅ NUEVO: Cuenta el total de ventas activas
 * 
 * Usado para calcular el número total de páginas
 */
public int contarVentasActivas() {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int total = 0;
    
    try {
        conn = Conexion.getConnection();
        
        String sql = "SELECT COUNT(*) as total FROM detalle WHERE estado = 'VENDIDO'";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        
        if (rs.next()) {
            total = rs.getInt("total");
        }
        
    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    } finally {
        Conexion.close(rs);
        Conexion.close(stmt);
        Conexion.close(conn);
    }
    
    return total;
}

/**
 * ✅ MEJORADO: Buscar ventas por criterio (sin paginación en búsquedas)
 * 
 * Este método ya debe existir, pero asegúrate que incluya el anticipo
 */
public ArrayList<DetalleVO> buscarVentas(String criterio) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    ArrayList<DetalleVO> ventas = new ArrayList<>();
    
    try {
        conn = Conexion.getConnection();
        
        // Busca por folio o nombre de cliente
        String sql = 
            "SELECT " +
            "    d.id, " +
            "    DATE_FORMAT(d.fecha_venta, '%d/%m/%Y') as fecha_venta, " +
            "    dest.ciudad, " +
            "    c.nombre as nombreCliente, " +
            "    d.personas, " +
            "    DATE_FORMAT(d.fecha_regreso, '%d/%m/%Y') as fecha_regreso, " +
            "    d.costo, " +
            "    d.anticipo, " +  // ✅ Asegurarse que incluye anticipo
            "    u.nombre as nombreVendedor " +
            "FROM detalle d " +
            "JOIN cliente c ON c.id = d.id_cliente " +
            "JOIN viaje v ON v.id = d.id_viaje " +
            "JOIN destino dest ON dest.id = v.id_destino " +
            "JOIN usuario u ON u.id = d.id_usuario " +
            "WHERE d.estado = 'VENDIDO' " +
            "  AND (d.id LIKE ? OR c.nombre LIKE ?) " +
            "ORDER BY d.id DESC " +
            "LIMIT 50";  // Límite de seguridad en búsquedas
        
        String criterioBusqueda = "%" + criterio + "%";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, criterioBusqueda);
        stmt.setString(2, criterioBusqueda);
        rs = stmt.executeQuery();
        
        while (rs.next()) {
            DetalleVO vo = new DetalleVO();
            vo.setId(rs.getLong("id"));
            vo.setFechaVenta(rs.getString("fecha_venta"));
            vo.setCiudad(rs.getString("ciudad"));
            vo.setNombreCliente(rs.getString("nombreCliente"));
            vo.setPersonas(rs.getInt("personas"));
            vo.setFechaRegreso(rs.getString("fecha_regreso"));
            vo.setCosto(rs.getDouble("costo"));
            vo.setAnticipo(rs.getDouble("anticipo"));
            vo.setNombreVendedor(rs.getString("nombreVendedor"));
            ventas.add(vo);
        }
        
    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    } finally {
        Conexion.close(rs);
        Conexion.close(stmt);
        Conexion.close(conn);
    }
    
    return ventas;
}

/**
 * ✅ MEJORADO: Método encontrar() debe incluir todos los campos necesarios
 * 
 * Este método se usa para obtener los datos de una venta específica
 * Asegúrate que incluya los campos para generar el ticket
 */
public DetalleVO encontrarParaTicket(Long id) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    DetalleVO detalle = null;
    
    try {
        conn = Conexion.getConnection();
        
        String sql = 
            "SELECT " +
            "    d.*, " +
            "    c.nombre as nombreCliente, " +
            "    c.telefono, " +
            "    dest.ciudad, " +
            "    e.nombre as estado, " +
            "    u.nombre as nombreVendedor " +
            "FROM detalle d " +
            "JOIN cliente c ON c.id = d.id_cliente " +
            "JOIN viaje v ON v.id = d.id_viaje " +
            "JOIN destino dest ON dest.id = v.id_destino " +
            "JOIN estado e ON e.id = dest.id_estado " +
            "JOIN usuario u ON u.id = d.id_usuario " +
            "WHERE d.id = ?";
        
        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, id);
        rs = stmt.executeQuery();
        
        if (rs.next()) {
            detalle = new DetalleVO();
            detalle.setId(rs.getLong("id"));
            detalle.setIdCliente(rs.getInt("id_cliente"));
            detalle.setIdViaje(rs.getInt("id_viaje"));
            detalle.setIdUsuario(rs.getInt("id_usuario"));
            detalle.setPersonas(rs.getInt("personas"));
            detalle.setSube(rs.getString("sube"));
            detalle.setHora(rs.getString("hora"));
            detalle.setHabitaciones(rs.getInt("habitacion"));
            detalle.setCosto(rs.getDouble("costo"));
            detalle.setAnticipo(rs.getDouble("anticipo"));
            detalle.setLiquidado(rs.getBoolean("liquidado"));
            detalle.setEstado(rs.getString("estado"));
            detalle.setPago(rs.getString("pago"));
            detalle.setViaje(rs.getString("viaje"));
            detalle.setHoraRegreso(rs.getString("hora_regreso"));
            detalle.setFechaRegreso(rs.getString("fecha_regreso"));
            detalle.setFechaVenta(rs.getString("fecha_venta"));
            
            // Campos adicionales del JOIN
            detalle.setNombreCliente(rs.getString("nombreCliente"));
            detalle.setTelefono(rs.getString("telefono"));
            detalle.setCiudad(rs.getString("ciudad"));
            detalle.setEstado(rs.getString("estado"));  // Sobrescribe el ENUM con el nombre real
            detalle.setNombreVendedor(rs.getString("nombreVendedor"));
        }
        
    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    } finally {
        Conexion.close(rs);
        Conexion.close(stmt);
        Conexion.close(conn);
    }
    
    return detalle;
}

    /**
     * Método privado para reutilizar la creación del objeto DetalleVO
     * con los campos del JOIN
     */
    private DetalleVO mapearVenta(ResultSet rs) throws SQLException {
        DetalleVO d = new DetalleVO();
        d.setId(rs.getLong("id"));
        d.setFechaVenta(rs.getString("fecha_venta"));
        d.setCiudad(rs.getString("ciudad")); // Requiere campo 'ciudad' en VO
        d.setNombreCliente(rs.getString("nombre")); // Requiere campo 'nombreCliente' en VO
        d.setPersonas(rs.getInt("personas"));
        d.setFechaRegreso(rs.getString("fecha_regreso"));
        d.setCosto(rs.getDouble("costo"));
        d.setAnticipo(rs.getDouble("anticipo"));
        d.setNombreVendedor(rs.getString("vendedor")); // Requiere campo 'nombreVendedor' en VO
        return d;
    }
     
}
