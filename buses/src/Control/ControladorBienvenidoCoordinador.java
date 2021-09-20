/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ClassVO.UsuarioVO;
import Vista.BienvenidoCoordinador;
import Vista.MenuCoordinador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import rojerusan.RSAnimation;

/**
 *
 * @author alanm
 */
public class ControladorBienvenidoCoordinador implements ActionListener, KeyListener {

    private BienvenidoCoordinador vista;
    private UsuarioVO usuario;

    public ControladorBienvenidoCoordinador(BienvenidoCoordinador vista, UsuarioVO usuario) {
        this.vista = vista;
        this.usuario = usuario;

        this.vista.btnAceptar.addActionListener(this);
        this.vista.btnAceptar.addKeyListener(this);
        //Se agrega un action listener por cada objeto
    }

    public void iniciar() {
        vista.setTitle("Bienvendido coordinador");
        vista.lblNombre.setText(usuario.getNombre());
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.btnAceptar) {
            //Abrir menú
            abrirMenu();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            abrirMenu();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void abrirMenu() {
        try {
            int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
            RSAnimation.setSubir((alto / 2) - (vista.getHeight() / 2), -1 * vista.getHeight(), 2, 2, vista);
            Thread.sleep(500);
            vista.dispose();
            MenuCoordinador menu = new MenuCoordinador();
            ControladorMenuCoordinador controladorMenuCoordinador = new ControladorMenuCoordinador(menu, usuario);
            controladorMenuCoordinador.iniciar();

        } catch (InterruptedException ex) {
            Logger.getLogger(BienvenidoCoordinador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
