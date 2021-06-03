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
public class HotelEnDestinoVO {
    private int idHotel;
    private int idDestino;

    public HotelEnDestinoVO(int idHotel, int idDestino) {
        this.idHotel = idHotel;
        this.idDestino = idDestino;
    }

    public HotelEnDestinoVO() {
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

    @Override
    public String toString() {
        return "HoteEnDestinoVO{" + "idHotel=" + idHotel + ", idDestino=" + idDestino + '}';
    }
    
    
}
