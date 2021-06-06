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
public class DestinoVO {
    private int id;
    private String ciudad;
    private int idEstado;
    
    public DestinoVO() {
    }

    public DestinoVO(int id, String ciudad, int idEstado) {
        this.id = id;
        this.ciudad = ciudad;
        this.idEstado = idEstado;
    }

    public DestinoVO(String ciudad, int idEstado) {
        this.ciudad = ciudad;
        this.idEstado = idEstado;
    }

    
    
    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return ciudad ;
    }
    
    
    
}
