package ClassDAO;

import ClassVO.AbonosVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Conexion.Conexion;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DAO de Abonos - REFACTORIZADO CON SEPARACIÓN DE RESPONSABILIDADES
 * 
 * RESPONSABILIDADES:
 * - Todas las consultas SQL relacionadas con abonos
 * - Lógica de registro transaccional de abonos
 * - Actualización del anticipo en detalle
 * - Cálculos de totales
 * 
 * @author alxyg
 */
public class AbonosDAO {
    
    // ========== CONSULTAS SQL ==========
    
    private static final String SQL_INSERT_ABONO = 
        "INSERT INTO abonos (monto, metodo_pago, id_detalle, id_usuario) " +
        "VALUES (?, ?, ?, ?)";
    
    private static final String SQL_UPDATE_ANTICIPO = 
        "UPDATE detalle SET anticipo = anticipo + ?, liquidado = ? WHERE id = ?";
    
    private static final String SQL_SELECT_BY_DETALLE = 
        "SELECT a.id, a.monto, a.metodo_pago, " +
        "       DATE_FORMAT(a.fecha, '%d/%m/%Y %H:%i') as fecha, " +
        "       a.id_detalle, u.nombre " +
        "FROM abonos a " +
        "LEFT JOIN usuario AS u ON u.id = a.id_usuario " +
        "WHERE id_detalle = ? " +
        "ORDER BY a.fecha ASC";
    
    private static final String SQL_CALCULAR_TOTAL = 
        "SELECT COALESCE(SUM(monto), 0) as total FROM abonos WHERE id_detalle = ?";
    
    private static final String SQL_CONTAR_ABONOS = 
        "SELECT COUNT(*) as total FROM abonos WHERE id_detalle = ?";
    
    // ========== CLASE INTERNA PARA RESULTADO DE REGISTRO ==========
    
    /**
     * Clase para retornar el resultado del registro de abono
     */
    public static class ResultadoAbono {
        private boolean exito;
        private String mensaje;
        private double nuevoAnticipo;
        private double nuevoSaldo;
        private boolean liquidado;
        
        public ResultadoAbono() {}
        
        public ResultadoAbono(boolean exito, String mensaje) {
            this.exito = exito;
            this.mensaje = mensaje;
        }
        
        // Getters y Setters
        public boolean isExito() { return exito; }
        public void setExito(boolean exito) { this.exito = exito; }
        
        public String getMensaje() { return mensaje; }
        public void setMensaje(String mensaje) { this.mensaje = mensaje; }
        
        public double getNuevoAnticipo() { return nuevoAnticipo; }
        public void setNuevoAnticipo(double nuevoAnticipo) { this.nuevoAnticipo = nuevoAnticipo; }
        
        public double getNuevoSaldo() { return nuevoSaldo; }
        public void setNuevoSaldo(double nuevoSaldo) { this.nuevoSaldo = nuevoSaldo; }
        
        public boolean isLiquidado() { return liquidado; }
        public void setLiquidado(boolean liquidado) { this.liquidado = liquidado; }
    }
    
    // ========== MÉTODOS PRINCIPALES ==========
    
    /**
     * ✅ REFACTORIZADO: Registra un abono y actualiza el anticipo en una transacción
     * 
     * Este método encapsula TODA la lógica de registro de abono:
     * 1. INSERT en tabla abonos
     * 2. UPDATE anticipo en detalle
     * 3. UPDATE liquidado en detalle
     * 
     * @param abono Datos del abono a registrar
     * @param costoTotal Costo total de la venta (para calcular liquidado)
     * @param anticipoActual Anticipo actual de la venta
     * @return ResultadoAbono con el resultado de la operación
     */
    public ResultadoAbono registrarAbonoTransaccional(AbonosVO abono, double costoTotal, double anticipoActual) {
        Connection conn = null;
        PreparedStatement stmtAbono = null;
        PreparedStatement stmtDetalle = null;
        ResultadoAbono resultado = new ResultadoAbono();
        
        try {
            conn = Conexion.getConnection();
            conn.setAutoCommit(false); // INICIAR TRANSACCIÓN
            
            // ========== 1. INSERTAR ABONO ==========
            stmtAbono = conn.prepareStatement(SQL_INSERT_ABONO);
            stmtAbono.setDouble(1, abono.getMonto());
            stmtAbono.setString(2, abono.getMetodoPago());
            stmtAbono.setLong(3, abono.getIdDetalle());
            stmtAbono.setInt(4, abono.getIdUsuario());
            
            int rowsAbono = stmtAbono.executeUpdate();
            
            if (rowsAbono == 0) {
                conn.rollback();
                resultado.setExito(false);
                resultado.setMensaje("Error al insertar el abono");
                return resultado;
            }
            
            // ========== 2. CALCULAR NUEVO ANTICIPO Y LIQUIDADO ==========
            double nuevoAnticipo = anticipoActual + abono.getMonto();
            double nuevoSaldo = costoTotal - nuevoAnticipo;
            boolean liquidado = nuevoSaldo <= 0.01; // Tolerancia de 1 centavo
            
            // ========== 3. ACTUALIZAR ANTICIPO Y LIQUIDADO EN DETALLE ==========
            stmtDetalle = conn.prepareStatement(SQL_UPDATE_ANTICIPO);
            stmtDetalle.setDouble(1, abono.getMonto());  // Suma al anticipo actual
            stmtDetalle.setBoolean(2, liquidado);
            stmtDetalle.setLong(3, abono.getIdDetalle());
            
            int rowsDetalle = stmtDetalle.executeUpdate();
            
            if (rowsDetalle == 0) {
                conn.rollback();
                resultado.setExito(false);
                resultado.setMensaje("Error al actualizar el anticipo");
                return resultado;
            }
            
            // ========== 4. COMMIT ==========
            conn.commit();
            
            // ========== 5. PREPARAR RESULTADO EXITOSO ==========
            resultado.setExito(true);
            resultado.setMensaje(liquidado ? 
                "¡Venta liquidada completamente!" : 
                "Abono registrado correctamente");
            resultado.setNuevoAnticipo(nuevoAnticipo);
            resultado.setNuevoSaldo(Math.max(0, nuevoSaldo));
            resultado.setLiquidado(liquidado);
            
            System.out.println("[DAO] Abono registrado: $" + abono.getMonto() + 
                               " | Nuevo anticipo: $" + nuevoAnticipo +
                               " | Saldo: $" + resultado.getNuevoSaldo() +
                               (liquidado ? " [LIQUIDADO]" : ""));
            
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.err.println("[DAO ROLLBACK] Error al registrar abono: " + e.getMessage());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            resultado.setExito(false);
            resultado.setMensaje("Error de base de datos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Conexion.close(stmtAbono);
            Conexion.close(stmtDetalle);
            try {
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Conexion.close(conn);
        }
        
        return resultado;
    }
    
    /**
     * ✅ SIMPLE: Inserta un abono sin actualizar el detalle
     * (Usar solo para casos especiales, normalmente usar registrarAbonoTransaccional)
     */
    public int insertar(AbonosVO abono) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_ABONO);
            stmt.setDouble(1, abono.getMonto());
            stmt.setString(2, abono.getMetodoPago());
            stmt.setLong(3, abono.getIdDetalle());
            stmt.setInt(4, abono.getIdUsuario());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    /**
     * Lista todos los abonos de un detalle específico
     */
    public List<AbonosVO> listarPorDetalle(Long idDetalleBusqueda) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AbonosVO> abonos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_DETALLE);
            stmt.setLong(1, idDetalleBusqueda);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                Double monto = rs.getDouble("monto");
                String metodo = rs.getString("metodo_pago");
                String fecha = rs.getString("fecha");
                Long idDetalle = rs.getLong("id_detalle");
                String nombreUsuario = rs.getString("nombre");

                AbonosVO abono = new AbonosVO(id, monto, metodo, fecha, idDetalle, nombreUsuario);
                abonos.add(abono);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return abonos;
    }
    
    /**
     * Calcula el total abonado para un detalle
     */
    public Double calcularTotalAbonado(Long idDetalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Double total = 0.0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CALCULAR_TOTAL);
            stmt.setLong(1, idDetalle);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                total = rs.getDouble("total");
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
     * Cuenta cuántos abonos tiene un detalle
     */
    public int contarAbonosPorDetalle(Long idDetalle) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int count = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_CONTAR_ABONOS);
            stmt.setLong(1, idDetalle);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return count;
    }
}