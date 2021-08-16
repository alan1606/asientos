package ClassVO;

/**
 *
 * @author alanm
 */
public class DetalleHotelDestinoViajeVO {

    private int id;
    private Long idDetalle;
    private int idHotelDestinoViaje;
    private int habitaciones;

    public DetalleHotelDestinoViajeVO() {
    }

    public DetalleHotelDestinoViajeVO(int id, Long idDetalle, int idHotelDestinoViaje, int habitaciones) {
        this.id = id;
        this.idDetalle = idDetalle;
        this.idHotelDestinoViaje = idHotelDestinoViaje;
        this.habitaciones = habitaciones;
    }

    public DetalleHotelDestinoViajeVO(Long idDetalle, int idHotelDestinoViaje, int habitaciones) {
        this.idDetalle = idDetalle;
        this.idHotelDestinoViaje = idHotelDestinoViaje;
        this.habitaciones = habitaciones;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdHotelDestinoViaje() {
        return idHotelDestinoViaje;
    }

    public void setIdHotelDestinoViaje(int idHotelDestinoViaje) {
        this.idHotelDestinoViaje = idHotelDestinoViaje;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    @Override
    public String toString() {
        return "DetalleHotelDestinoViaje{" + "id=" + id + ", idDetalle=" + idDetalle + ", idHotelDestinoViaje=" + idHotelDestinoViaje + ", habitaciones=" + habitaciones + '}';
    }

    
 
    
}
