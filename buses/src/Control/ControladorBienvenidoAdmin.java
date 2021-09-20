/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ClassVO.UsuarioVO;
import Vista.BienvenidoAdmin;
import Vista.MenuAdmin;
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
public class ControladorBienvenidoAdmin implements ActionListener, KeyListener {

    private BienvenidoAdmin vista;
    private UsuarioVO usuario;

    public ControladorBienvenidoAdmin(BienvenidoAdmin vista, UsuarioVO usuario) {
        this.vista = vista;
        this.usuario = usuario;

        this.vista.btnAceptar.addActionListener(this);
        this.vista.btnAceptar.addKeyListener(this);
        //Se agrega un action listener por cada objeto
    }

    public void iniciar() {
        vista.setTitle("Bienvendido admin");
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

    private void abrirMenu(){
    try {
                int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
                int centro=(alto / 2) - (vista.getHeight() / 2);
                RSAnimation.setSubir(centro, -1*vista.getHeight(), 2, 2, vista);
                Thread.sleep(500);
                vista.dispose();
                MenuAdmin menu = new MenuAdmin();
                ControladorMenuAdmin controladorMenuAdmin = new ControladorMenuAdmin(menu, usuario);
                controladorMenuAdmin.iniciar();

            } catch (InterruptedException ex) {
                Logger.getLogger(BienvenidoAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            abrirMenu();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
