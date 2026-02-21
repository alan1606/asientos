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
    private String observaciones;
    //Modificacion para mostrar destino de viaje
    private String nombreDestino;

   
    
    public ViajeVO() {
    }


    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ViajeVO(int idDestino, String fecha, int noAsientos, String observaciones) {
        this.idDestino = idDestino;
        this.fecha = fecha;
        this.noAsientos = noAsientos;
        this.observaciones = observaciones;
    }

    public ViajeVO(int id, int idDestino, String fecha, int noAsientos, String observaciones) {
        this.id = id;
        this.idDestino = idDestino;
        this.fecha = fecha;
        this.noAsientos = noAsientos;
        this.observaciones = observaciones;
    }
//constructor con todos los campos
    public ViajeVO(int id, String nombreDestino, int idDestino, String fecha, int noAsientos, String observaciones ) {
        this.id = id;
        this.nombreDestino = nombreDestino;
        this.idDestino = idDestino;
        this.fecha = fecha;
        this.noAsientos = noAsientos;
        this.observaciones = observaciones;
    }

    public ViajeVO(int id, String nombreDestino, String fecha, int noAsientos) {
        this.id = id;
        this.fecha = fecha;
        this.nombreDestino = nombreDestino;
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
     public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    @Override
    public String toString() {
        return  id + " : "  + noAsientos + " asientos";
    }

    
    
    
}
