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

    public DestinoVO() {
    }

    public DestinoVO(int id, String ciudad) {
        this.id = id;
        this.ciudad = ciudad;
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
        return  id + " : " + ciudad ;
    }
    
    
    
}
