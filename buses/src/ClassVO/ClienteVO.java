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
public class ClienteVO {
    private int id;
    private String nombre;
    private String telefono;
    private String correo;
    private String tipo;

    public ClienteVO() {
    }

    public ClienteVO(int id, String nombre, String telefono, String correo, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.tipo = tipo;
    }

    public ClienteVO(String nombre, String telefono, String correo, String tipo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.tipo = tipo;
    }

 
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
    @Override
    public String toString() {
        return nombre + " : " + telefono;
    }
    
    
}
