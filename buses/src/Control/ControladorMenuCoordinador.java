/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ClassVO.UsuarioVO;

import Vista.Clientes;
import Vista.Destinos;
import Vista.Hoteles;
import Vista.MenuCoordinador;
import Vista.Viajes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alanm
 */
public class ControladorMenuCoordinador implements ActionListener {

    private MenuCoordinador vista;
    private UsuarioVO usuario;

    public ControladorMenuCoordinador(MenuCoordinador vista, UsuarioVO usuario) {
        this.vista = vista;
        this.usuario = usuario;

        this.vista.btnAsientos.addActionListener(this);
        this.vista.btnDestinos.addActionListener(this);
        this.vista.btnViajes.addActionListener(this);
        this.vista.btnClientes.addActionListener(this);
        this.vista.btnDetalles.addActionListener(this);
        this.vista.btnHoteles.addActionListener(this);

        //Se agrega un action listener por cada objeto
    }

    public void iniciar() {
        vista.setTitle("Menú de coordinador");
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.btnAsientos) {
            abrirAsientos();
        }
        if (ae.getSource() == vista.btnDestinos) {
            abrirDestinos();
        }
        if (ae.getSource() == vista.btnViajes) {
            abrirViajes();
        }
        if (ae.getSource() == vista.btnClientes) {
            abrirClientes();
        }
        if (ae.getSource() == vista.btnDetalles) {
            abrirDetalles();
        }
        if(ae.getSource() == vista.btnHoteles){
            abrirHoteles();
        }
    }

    private void abrirAsientos() {

    }

    private void abrirHoteles(){
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

    }

}
