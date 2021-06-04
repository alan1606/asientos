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
public class EstadoVO {

    private int id;
    private int idPais;
    private String nombre;

    public EstadoVO(int id, int idPais, String nombre) {
        this.id = id;
        this.idPais = idPais;
        this.nombre = nombre;
    }

    public EstadoVO() {
    }

    public EstadoVO(int idPais, String nombre) {
        this.idPais = idPais;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return  nombre;
    }
    
    
}
