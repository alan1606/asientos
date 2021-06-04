/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import ClassDAO.UsuarioDAO;
import ClassVO.UsuarioVO;
import Control.ControladorLogin;
import Vista.VistaLogin;

/**
 *
 * @author alanm
 */
public class Main {

    public static void main(String[] Args) {
        UsuarioVO usuario= new UsuarioVO();
        UsuarioDAO modelo= new UsuarioDAO();
        VistaLogin vistaLogin = new VistaLogin();
        ControladorLogin controladorLogin = new ControladorLogin(vistaLogin, modelo, usuario);
        controladorLogin.iniciar();
        
    }
}
