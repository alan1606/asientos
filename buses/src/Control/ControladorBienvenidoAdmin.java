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
import java.util.logging.Level;
import java.util.logging.Logger;
import rojerusan.RSAnimation;

/**
 *
 * @author alanm
 */
public class ControladorBienvenidoAdmin implements ActionListener {

    private BienvenidoAdmin vista;
    private UsuarioVO usuario;

    public ControladorBienvenidoAdmin(BienvenidoAdmin vista, UsuarioVO usuario) {
        this.vista = vista;
        this.usuario = usuario;

        this.vista.btnAceptar.addActionListener(this);
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
            try {
                RSAnimation.setSubir(150, -230, 2, 2, vista);
                Thread.sleep(500);
                vista.dispose();
                MenuAdmin menu = new MenuAdmin();
                ControladorMenuAdmin controladorMenuAdmin = new ControladorMenuAdmin(menu, usuario);
                controladorMenuAdmin.iniciar();

            } catch (InterruptedException ex) {
                Logger.getLogger(BienvenidoAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
