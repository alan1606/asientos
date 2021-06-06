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
public class ViajeVO {
    private int id;
    private int idDestino;
    private String fecha;
    private int noAsientos; 

    public ViajeVO() {
    }

    public ViajeVO(int id, int idDestino, String fecha, int noAsientos) {
        this.id = id;
        this.idDestino = idDestino;
        this.fecha = fecha;
        this.noAsientos = noAsientos;
    }

    public ViajeVO(int idDestino, String fecha, int noAsientos) {
        this.idDestino = idDestino;
        this.fecha = fecha;
        this.noAsientos = noAsientos;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNoAsientos() {
        return noAsientos;
    }

    public void setNoAsientos(int noAsientos) {
        this.noAsientos = noAsientos;
    }

    @Override
    public String toString() {
        return "idDestino=" + idDestino + " : " + fecha + ", " + noAsientos + " asientos";
    }
    
    
}
