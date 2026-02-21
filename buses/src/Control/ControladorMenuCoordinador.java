/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ClassVO.UsuarioVO;
import Vista.Abonos;
import Vista.Asientos;

import Vista.Clientes;
import Vista.Destinos;
import Vista.Hoteles;
import Vista.Inforomacion;
import Vista.MenuCoordinador;
import Vista.Detalles;
import Vista.DetallesAsientos;
import Vista.DetallesTickets;
import components.InfoMenu;
import components.controllers.ControllerInfoMenu;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;

/**
 *
 * @author alanm
 */
public class ControladorMenuCoordinador implements ActionListener, KeyListener {

    private final MenuCoordinador vista;
    private final UsuarioVO usuario;
 
    public ControladorMenuCoordinador(MenuCoordinador vista, UsuarioVO usuario) {
        this.vista = vista;
        this.usuario = usuario;

        this.vista.btnAsientos.addActionListener(this);
        //this.vista.btnDestinos.addActionListener(this);
        //this.vista.btnViajes.addActionListener(this);
        this.vista.btnClientes.addActionListener(this);
        this.vista.btnDetalles.addActionListener(this);
        this.vista.btnAbonos.addActionListener(this);
        this.vista.btn_info.addActionListener(this);
        //Se agrega un action listener por cada objeto
    }

    public void iniciar() {
        vista.setTitle("Menú de coordinador");
        vista.setVisible(true);
        vista.btnAsientos.requestFocus();
        cargarUsuario();
        cargarLandingPanel();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.btnAsientos) {
            abrirAsientos();
        }
//        if (ae.getSource() == vista.btnDestinos) {
//            abrirDestinos();
//        }
//        if (ae.getSource() == vista.btnViajes) {
//            abrirViajes();
//        }
        if (ae.getSource() == vista.btnClientes) {
            abrirClientes();
        }
        if (ae.getSource() == vista.btnAbonos) {
            abrirAbonos();
        }
        if (ae.getSource() == vista.btnDetalles) {
            abrirDetalles();
        }
        if (ae.getSource() == vista.btn_info){
            abrirInfo();
        }
        
    }
    private void cargarUsuario(){
        vista.lblUsername.setText(this.usuario.getNombre());
    }
    private void cargarLandingPanel(){
        Component c = new InfoMenu();
        //ControllerInfoMenu infoMenuPanel = new ControllerInfoMenu((InfoMenu) c);
        ControllerInfoMenu panelVentas = new ControllerInfoMenu((InfoMenu) c);
        panelVentas.iniciar();
        vista.landingPanel.removeAll();
        //vista.landingPanel.add(infoMenuPane, BorderLayout.CENTER);
        vista.landingPanel.add(c, BorderLayout.CENTER);
        vista.landingPanel.revalidate();
        vista.landingPanel.repaint();
    }

    private void abrirInfo(){
        vista.dispose();
        ControladorInfo info = new ControladorInfo(new Inforomacion(), usuario);
        info.Iniciar();
    }
    private void abrirAsientos() {
        vista.dispose();
        ControladorAsientos asientos = new ControladorAsientos(new Asientos(), usuario);
        asientos.iniciar();
    }
    private void abrirAbonos(){
        vista.dispose();
        ControladorAbonos abono = new ControladorAbonos(new Abonos(), usuario);
        abono.iniciar();
    }

//    private void abrirHoteles() {
//        vista.dispose();
//        ControladorHoteles hoteles = new ControladorHoteles(new Hoteles(), usuario);
//        hoteles.iniciar();
//    }
//
//    private void abrirDestinos() {
//        vista.dispose();
//        ControladorDestinos destinos = new ControladorDestinos(new Destinos(), usuario);
//        destinos.iniciar();
//    }
//
//    private void abrirViajes() {
//        vista.dispose();
//        ControladorViajes viajes = new ControladorViajes(new Viajes(), usuario);
//        viajes.iniciar();
//    }

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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
