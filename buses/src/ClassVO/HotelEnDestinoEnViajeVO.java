/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassVO;

/**
 *
 * @author alanm
 */
public class HotelEnDestinoEnViajeVO {
    private int idHotel;
    private int idDestino;
    private int idViaje;
    private int noHabitaciones;

    public HotelEnDestinoEnViajeVO(int idHotel, int idDestino, int idViaje, int noHabitaciones) {
        this.idHotel = idHotel;
        this.idDestino = idDestino;
        this.idViaje = idViaje;
        this.noHabitaciones = noHabitaciones;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public int getNoHabitaciones() {
        return noHabitaciones;
    }

    public void setNoHabitaciones(int noHabitaciones) {
        this.noHabitaciones = noHabitaciones;
    }

    @Override
    public String toString() {
        return "HotelEnDestinoEnViajeVO{" + "idHotel=" + idHotel + ", idDestino=" + idDestino + ", idViaje=" + idViaje + ", noHabitaciones=" + noHabitaciones + '}';
    }
    
    
}
