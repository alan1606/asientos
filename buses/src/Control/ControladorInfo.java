package Control;
import ClassVO.UsuarioVO;
import Vista.MenuAdmin;
import Vista.MenuCoordinador;
import Vista.Inforomacion;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author alxyg
 */
public class ControladorInfo implements MouseListener{
    private Inforomacion vista;
    private UsuarioVO usuario;
    
    public ControladorInfo (Inforomacion vista, UsuarioVO usuario){
        this.vista = vista;
        this.usuario = usuario;
        
        this.vista.lbl_back.addMouseListener(this);
    }
    public void Iniciar(){
        vista.setTitle("Información del Sistema");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
    @Override
    public void mouseClicked(MouseEvent me) {

    }
    public void abrirMenu(){
            if (usuario.getTipo().equals("Administrador")) {
            ControladorMenuAdmin controladorMenuAdmin = new ControladorMenuAdmin(new MenuAdmin(), usuario);
            controladorMenuAdmin.iniciar();
        } else {
            ControladorMenuCoordinador controladorMenuCoordinador = new ControladorMenuCoordinador(new MenuCoordinador(), usuario);
            controladorMenuCoordinador.iniciar();
        }
        vista.dispose();
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getSource() == vista.lbl_back) {
            abrirMenu();
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
}
