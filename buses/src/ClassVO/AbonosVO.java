package ClassVO;

/**
 * Value Object para Abonos - VERSIÓN ACTUALIZADA
 * 
 * CAMBIOS:
 * - Agregado campo esMigracion para identificar abonos migrados
 * - Métodos de utilidad mejorados
 * 
 * @author alxyg
 */
public class AbonosVO {
    private Long id;
    private Double monto;
    private String metodoPago;
    private String fechaAbono;
    private Long idDetalle;
    private Integer idUsuario;
    
    // Join a tabla usuario
    private String nombreUsuario;
    


    // ========== CONSTRUCTORES ==========
    
    public AbonosVO() {
    }

    public AbonosVO(Long id, Double monto, String metodoPago, String fechaAbono, Long idDetalle, String nombreUsuario) {
        this.id = id;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaAbono = fechaAbono;
        this.idDetalle = idDetalle;
        this.nombreUsuario = nombreUsuario;

    }

    public AbonosVO(Double monto, String metodoPago, String fechaAbono, Long idDetalle, Integer idUsuario) {
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaAbono = fechaAbono;
        this.idDetalle = idDetalle;
        this.idUsuario = idUsuario;
    }

    public AbonosVO(Long id, Double monto, String metodoPago, String fechaAbono, Long idDetalle, Integer idUsuario) {
        this.id = id;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaAbono = fechaAbono;
        this.idDetalle = idDetalle;
        this.idUsuario = idUsuario;
    }
    
    // ========== GETTERS Y SETTERS ==========

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getFechaAbono() {
        return fechaAbono;
    }

    public void setFechaAbono(String fechaAbono) {
        this.fechaAbono = fechaAbono;
    }

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


    
    // ========== MÉTODOS DE UTILIDAD ==========
    

    /**
     * Retorna una descripción del método de pago
     */
    public String getMetodoPagoDescripcion() {
        return metodoPago;
    }

    @Override
    public String toString() {
        return String.format("AbonosVO{id=%d, monto=%.2f, metodo=%s, fecha=%s, detalle=%d}",
                             id, monto, metodoPago, fechaAbono, idDetalle);
    }
}