/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassVO;


import java.math.BigInteger;
import java.security.MessageDigest;


/**
 *
 * @author alanm
 */
public class UsuarioVO {
    private int id;
    private String usuario;
    private String pass;
    private String nombre;
    private String tipo;

    public String getMD5(String input) {
         try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] encBytes = md.digest(input.getBytes());
            BigInteger numero = new BigInteger(1, encBytes);
            String encString = numero.toString(16);
            while (encString.length() < 32) {
                encString = "0" + encString;
            }
            return encString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public UsuarioVO() {
    }

    public UsuarioVO(int id, String usuario, String pass, String nombre, String tipo) {
        this.id = id;
        this.usuario = usuario;
        this.pass = pass;
        this.tipo = tipo;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return (usuario + " : " + tipo);
    }
    
    
}
