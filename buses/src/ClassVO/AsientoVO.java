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
public class AsientoVO {
    private int numero;
    private int idViaje;
    private int idCliente;
    private boolean disponible;
    
    public AsientoVO() {
    }

    public AsientoVO(int numero, int idViaje, int idCliente, boolean disponible) {
        this.numero = numero;
        this.idViaje = idViaje;
        this.idCliente = idCliente;
        this.disponible = disponible;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    @Override
    public String toString() {
        return  "numero=" + numero + ", idViaje=" + idViaje + ", idCliente=" + idCliente;
    }
    
    
}
