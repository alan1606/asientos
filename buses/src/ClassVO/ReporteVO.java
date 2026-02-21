package ClassVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Value Object para almacenar los datos de reportes de tickets
 * Contiene toda la información necesaria para generar reportes con OpenPDF
 * 
 * @author alxyg
 */
public class ReporteVO {
    
    // Datos principales del ticket
    private Long folio;
    private Double costo;
    private Double anticipo;
    private String sube;
    private String hora;
    private String asientos;
    private String viaje;
    private String observaciones;
    private String pago;
    private String horaRegreso;
    private String fechaRegreso;
    private String fechaVenta;
    private Integer numeroPersonas;
    private String telefono;
    private String usuario;
    
    // Datos del cliente y destino (de parámetros)
    private String nombreCliente;
    private String ciudad;
    private String estado;
    
    // Lista de habitaciones (para tickets con habitaciones)
    private List<HabitacionVO> habitaciones;
    
    /**
     * Constructor vacío
     */
    public ReporteVO() {
        this.habitaciones = new ArrayList<>();
    }
    
    /**
     * Constructor completo para ticket básico
     */
    public ReporteVO(Long folio, Double costo, Double anticipo, String sube, String hora, 
                     String asientos, String viaje, String observaciones, String pago, 
                     String horaRegreso, String fechaRegreso, String fechaVenta, 
                     Integer numeroPersonas, String telefono, String usuario) {
        this.folio = folio;
        this.costo = costo;
        this.anticipo = anticipo;
        this.sube = sube;
        this.hora = hora;
        this.asientos = asientos;
        this.viaje = viaje;
        this.observaciones = observaciones;
        this.pago = pago;
        this.horaRegreso = horaRegreso;
        this.fechaRegreso = fechaRegreso;
        this.fechaVenta = fechaVenta;
        this.numeroPersonas = numeroPersonas;
        this.telefono = telefono;
        this.usuario = usuario;
        this.habitaciones = new ArrayList<>();
    }

    // Getters y Setters
    public Long getFolio() {
        return folio;
    }

    public void setFolio(Long folio) {
        this.folio = folio;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getAnticipo() {
        return anticipo;
    }

    public void setAnticipo(Double anticipo) {
        this.anticipo = anticipo;
    }

    public String getSube() {
        return sube;
    }

    public void setSube(String sube) {
        this.sube = sube;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAsientos() {
        return asientos;
    }

    public void setAsientos(String asientos) {
        this.asientos = asientos;
    }

    public String getViaje() {
        return viaje;
    }

    public void setViaje(String viaje) {
        this.viaje = viaje;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getHoraRegreso() {
        return horaRegreso;
    }

    public void setHoraRegreso(String horaRegreso) {
        this.horaRegreso = horaRegreso;
    }

    public String getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(String fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Integer getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(Integer numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<HabitacionVO> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<HabitacionVO> habitaciones) {
        this.habitaciones = habitaciones;
    }
    
    /**
     * Calcula el monto restante
     */
    public Double getRestante() {
        if (costo != null && anticipo != null) {
            return costo - anticipo;
        }
        return 0.0;
    }
    
    /**
     * Clase interna para representar habitaciones en el ticket
     */
    public static class HabitacionVO {
        private String nombreHotel;
        private Integer cantidad;
        
        public HabitacionVO() {}
        
        public HabitacionVO(String nombreHotel, Integer cantidad) {
            this.nombreHotel = nombreHotel;
            this.cantidad = cantidad;
        }

        public String getNombreHotel() {
            return nombreHotel;
        }

        public void setNombreHotel(String nombreHotel) {
            this.nombreHotel = nombreHotel;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }
    }
    
    @Override
    public String toString() {
        return "ReporteVO{" +
                "folio=" + folio +
                ", cliente='" + nombreCliente + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", viaje='" + viaje + '\'' +
                ", personas=" + numeroPersonas +
                ", costo=" + costo +
                ", anticipo=" + anticipo +
                '}';
    }
}