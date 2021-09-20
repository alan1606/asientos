/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ClassDAO.UsuarioDAO;
import ClassVO.UsuarioVO;
import Vista.BienvenidoAdmin;
import Vista.BienvenidoCoordinador;
import Vista.VistaLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

/**
 *
 * @author alanm
 */
public class ControladorLogin implements ActionListener, KeyListener {

    private VistaLogin vista;
    private UsuarioDAO modelo;
    private UsuarioVO usuario;

    public ControladorLogin(VistaLogin vista, UsuarioDAO modelo, UsuarioVO usuario) {
        this.vista = vista;
        this.modelo = modelo;
        this.usuario = usuario;
        
        this.vista.btnIngresar.addActionListener(this);
        this.vista.txtPass.addKeyListener(this);
        this.vista.btnIngresar.addKeyListener(this);
        //Se agrega un action listener por cada objeto
    }

    public void iniciar() {
        vista.setTitle("Inicio");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.btnIngresar) {
            if (datosValidos()) {
                checaLogin();
            }
        }
    }

    private boolean datosValidos() {
        if (vista.txtUsuario.getText().length() == 0) {
            return false;
        }
        if (vista.txtPass.getText().length() == 0) {
            return false;
        }
        return true;
    }

    private void checaLogin() {
        if (existeUsuario()) {
            if (passwordEsCorrecta()) {
                if (esAdmin()) {
                    vista.dispose();
                    abrirBienvenidoAdmin();
                } else {
                    vista.dispose();
                    abrirBienvenidoCoordinador();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                vista.txtPass.setText("");
                vista.txtPass.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no está registrado");
            clean();
        }
    }

    private boolean existeUsuario() {
        usuario = modelo.encontrar(vista.txtUsuario.getText());
        if (usuario.getId() == -1) {
            return false;
        }
        return true;
    }

    private boolean esAdmin() {
        if ("Administrador".equals(usuario.getTipo())) {
            return true;
        }
        return false;
    }

    private boolean passwordEsCorrecta() {
        if (usuario.getUsuario().equals(vista.txtUsuario.getText()) && usuario.getPass().equals(usuario.getMD5(vista.txtPass.getText()))) {
            return true;
        }
        return false;
    }

    private void abrirBienvenidoAdmin() { //Debería instanciar controladores y vistas, pasar "cookie de usuario"
        BienvenidoAdmin vAdmin = new BienvenidoAdmin(vista, true);
        ControladorBienvenidoAdmin controladorBienvenidoAdmin = new ControladorBienvenidoAdmin(vAdmin, usuario);
        controladorBienvenidoAdmin.iniciar();
    }

    private void abrirBienvenidoCoordinador() { //Debería instanciar controladores y vistas, pasar "cookie de usuario"
        BienvenidoCoordinador vCoordinador = new BienvenidoCoordinador(vista, true);
        ControladorBienvenidoCoordinador controladorBienvenidoCoordinador= new ControladorBienvenidoCoordinador(vCoordinador, usuario);
        controladorBienvenidoCoordinador.iniciar();
    }

    private void clean() {
        vista.txtUsuario.setText("");
        vista.txtPass.setText("");
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER && (e.getSource() == vista.txtPass || e.getSource() == vista.btnIngresar)){
            if (datosValidos()) {
                checaLogin();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
