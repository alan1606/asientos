/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ClassVO.UsuarioVO;
import Vista.Asientos;
import Vista.Cancelaciones;
import Vista.Clientes;
import Vista.Destinos;
import Vista.Detalles;
import Vista.DetallesAsientos;
import Vista.DetallesTickets;
import Vista.Hoteles;
import Vista.Inforomacion;
import Vista.MenuAdmin;
import Vista.Usuarios;
import Vista.Viajes;
import Vista.ViajesHoteles;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author alanm
 */
public class ControladorMenuAdmin implements ActionListener, KeyListener {

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
        this.vista.btnHoteles.addActionListener(this);
        this.vista.btnHotelesEnViaje.addActionListener(this);
        this.vista.btnCancelaciones.addActionListener(this);
        this.vista.btn_info.addActionListener(this);

        //Se agrega un action listener por cada objeto
    }

    public void iniciar() {
        vista.setTitle("Menú de administrador");
        vista.setVisible(true);
        vista.btnAsientos.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.btnAsientos) {
            abrirAsientos();
        }
        else if (ae.getSource() == vista.btnDestinos) {
            abrirDestinos();
        }
        else if (ae.getSource() == vista.btnViajes) {
            abrirViajes();
        }
        else if (ae.getSource() == vista.btnClientes) {
            abrirClientes();
        }
        else if (ae.getSource() == vista.btnDetalles) {
            abrirDetalles();
        }
        else if (ae.getSource() == vista.btnAnadir) {
            abrirAnadir();
        }
        else if (ae.getSource() == vista.btnHoteles) {
            abrirHoteles();
        }
        else if(ae.getSource() == vista.btnHotelesEnViaje){
            abrirHotelesEnViaje();
        }
        else if(ae.getSource() == vista.btnCancelaciones){
            abrirCancelaciones();
        }
        else if (ae.getSource() == vista.btn_info){
            abrirInfo();
        }
    }
    private void abrirInfo(){
        vista.dispose();
        ControladorInfo info = new ControladorInfo(new Inforomacion(), usuario);
        info.Iniciar();
    }
    private void abrirHotelesEnViaje(){
        vista.dispose();
        ControladorViajesHoteles viajesHoteles = new ControladorViajesHoteles(new ViajesHoteles(), usuario);
        viajesHoteles.iniciar();
    }
    
    private void abrirAsientos() {
        vista.dispose();
        ControladorAsientos asientos = new ControladorAsientos(new Asientos(), usuario);
        asientos.iniciar();
    }

    private void abrirHoteles() {
        vista.dispose();
        ControladorHoteles hoteles = new ControladorHoteles(new Hoteles(), usuario);
        hoteles.iniciar();
    }

    private void abrirDestinos() {
        vista.dispose();
        ControladorDestinos destinos = new ControladorDestinos(new Destinos(), usuario);
        destinos.iniciar();
    }

    private void abrirViajes() {
        vista.dispose();
        ControladorViajes viajes = new ControladorViajes(new Viajes(), usuario);
        viajes.iniciar();
    }

    private void abrirClientes() {
        vista.dispose();
        ControladorClientes clientes = new ControladorClientes(new Clientes(), usuario);
        clientes.iniciar();
    }

    private void abrirDetalles() {
        vista.dispose();
        ControladorDetalles detalles = new ControladorDetalles(new Detalles(), usuario, new DetallesAsientos(), new DetallesTickets());
        detalles.iniciar();
        
    }

    private void abrirAnadir() {
        vista.dispose();
        ControladorUsuarios usuarios = new ControladorUsuarios(new Usuarios(), usuario);
        usuarios.iniciar();
    }

    private void abrirCancelaciones() {
        vista.dispose();
        ControladorCancelaciones cancelaciones = new ControladorCancelaciones(new Cancelaciones(), usuario);
        cancelaciones.iniciar();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
    
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
