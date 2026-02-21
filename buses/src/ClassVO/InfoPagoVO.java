package ClassVO;

/**
 * Value Object para información de pago de una venta
 * 
 * Contiene toda la información necesaria sobre el estado de pago
 * de un detalle/venta en una sola consulta optimizada.
 * 
 * @author alxyg
 */
public class InfoPagoVO {
    
    private Double costo;
    private Double totalAbonado;
    private Double saldoRestante;
    private Boolean liquidado;
    private Boolean anticipoMigrado;

    public InfoPagoVO() {
    }

    public InfoPagoVO(Double costo, Double totalAbonado, Double saldoRestante, Boolean liquidado) {
        this.costo = costo;
        this.totalAbonado = totalAbonado;
        this.saldoRestante = saldoRestante;
        this.liquidado = liquidado;
    }

    // ========== GETTERS Y SETTERS ==========
    
    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getTotalAbonado() {
        return totalAbonado;
    }

    public void setTotalAbonado(Double totalAbonado) {
        this.totalAbonado = totalAbonado;
    }

    public Double getSaldoRestante() {
        return saldoRestante;
    }

    public void setSaldoRestante(Double saldoRestante) {
        this.saldoRestante = saldoRestante;
    }

    public Boolean getLiquidado() {
        return liquidado;
    }
    
    public Boolean isLiquidado() {
        return liquidado;
    }

    public void setLiquidado(Boolean liquidado) {
        this.liquidado = liquidado;
    }

    public Boolean getAnticipoMigrado() {
        return anticipoMigrado;
    }

    public void setAnticipoMigrado(Boolean anticipoMigrado) {
        this.anticipoMigrado = anticipoMigrado;
    }

    // ========== MÉTODOS DE UTILIDAD ==========
    
    /**
     * Verifica si el saldo está completamente pagado (con tolerancia de 1 centavo)
     */
    public boolean estaPagado() {
        return saldoRestante != null && saldoRestante <= 0.01;
    }
    
    /**
     * Calcula el porcentaje pagado
     */
    public double getPorcentajePagado() {
        if (costo == null || costo == 0) return 0;
        return (totalAbonado / costo) * 100.0;
    }
    
    /**
     * Calcula el porcentaje restante
     */
    public double getPorcentajeRestante() {
        return 100.0 - getPorcentajePagado();
    }

    @Override
    public String toString() {
        return String.format("InfoPagoVO{costo=%.2f, abonado=%.2f, restante=%.2f, liquidado=%b}",
                             costo, totalAbonado, saldoRestante, liquidado);
    }
}