/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ClassVO.UsuarioVO;
import Vista.Clientes;
import Vista.Destinos;
import Vista.MenuAdmin;
import Vista.Viajes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alanm
 */
public class ControladorMenuAdmin implements ActionListener{
  
    private MenuAdmin vista;
    private UsuarioVO usuario;
    
    public ControladorMenuAdmin(MenuAdmin vista, UsuarioVO usuario) {
        this.vista = vista;
        this.usuario = usuario;
        
        this.vista.btnAsientos.addActionListener(this);
        this.vista.btnDestinos.addActionListener(this);
        this.vista.btnViajes.addActionListener(this);
        this.vista.btnClientes.addActionListener(this);
        this.vista.btnDetalles.addActionListener(this);
        this.vista.btnAnadir.addActionListener(this);
        //Se agrega un action listener por cada objeto
    }
    
     public void iniciar() {
        vista.setTitle("Menú de administrador");
        vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == vista.btnAsientos){
            abrirAsientos();
        }
        if(ae.getSource() == vista.btnDestinos){
            abrirDestinos();
        }
        if(ae.getSource() == vista.btnViajes){
            abrirViajes();
        }
        if(ae.getSource() == vista.btnClientes){
            abrirClientes();
        }
        if(ae.getSource() == vista.btnDetalles){
            abrirDetalles();
        }
        if(ae.getSource() == vista.btnAnadir){
            abrirAnadir();
        }
    }
    
    private void abrirAsientos(){
        
    }
    private void abrirDestinos(){
        
    }
    private void abrirViajes(){
       
    }
    private void abrirClientes(){
        vista.dispose();
        ControladorClientes clientes = new ControladorClientes(new Clientes(), usuario);
        clientes.iniciar();
    }
    private void abrirDetalles(){
       
    }
    private void abrirAnadir(){
        
    }
    
}
