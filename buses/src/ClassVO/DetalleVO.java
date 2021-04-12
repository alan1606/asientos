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
public class DetalleVO {
    private int idViaje;
    private int idCliente;
    private int idUsuario;
    private int personas;
    private String sube;
    private String habitacion;
    private double costo;

    public DetalleVO() {
    }

    
    
    public DetalleVO(int idViaje, int idCliente, int idUsuario, int personas, String sube, String habitacion, double costo) {
        this.idViaje = idViaje;
        this.idCliente = idCliente;
        this.personas = personas;
        this.sube = sube;
        this.habitacion = habitacion;
        this.costo = costo;
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }


    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public String getSube() {
        return sube;
    }

    public void setSube(String sube) {
        this.sube = sube;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "idViaje=" + idViaje + ", idCliente=" + idCliente + ", personas=" + personas + ", sube=" + sube + ", habitacion=" + habitacion + ", costo=" + costo ;
    }
    
    
}
