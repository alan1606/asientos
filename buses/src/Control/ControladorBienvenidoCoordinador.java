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
import java.util.logging.Level;
import java.util.logging.Logger;
import rojerusan.RSAnimation;

/**
 *
 * @author alanm
 */
public class ControladorBienvenidoCoordinador implements ActionListener {
    private BienvenidoCoordinador vista;
    private UsuarioVO usuario;

    public ControladorBienvenidoCoordinador(BienvenidoCoordinador vista, UsuarioVO usuario) {
        this.vista = vista;
        this.usuario = usuario;

        this.vista.btnAceptar.addActionListener(this);
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
            try {
                RSAnimation.setSubir(150, -230, 2, 2, vista);
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
}
