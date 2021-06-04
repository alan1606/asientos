/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ClassDAO.DestinoDAO;
import ClassDAO.EstadoDAO;
import ClassDAO.PaisDAO;
import ClassVO.DestinoVO;
import ClassVO.EstadoVO;
import ClassVO.PaisVO;
import ClassVO.UsuarioVO;
import Tables.TablaDestinos;
import Vista.Destinos;
import Vista.MenuAdmin;
import Vista.MenuCoordinador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author alanm
 */
public class ControladorDestinos implements ActionListener, MouseListener, KeyListener {

    private Destinos vista;
    private DestinoDAO modelo;
    private UsuarioVO usuario;
    private PaisDAO modeloPais;
    private EstadoDAO modeloEstado;

    public ControladorDestinos(Destinos vista, UsuarioVO usuario) {
        this.vista = vista;
        this.usuario = usuario;

        modelo = new DestinoDAO();

        this.vista.btnAnadir.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.comboEstado.addActionListener(this);
        this.vista.comboPais.addActionListener(this);
        this.vista.tablaDestinos.addMouseListener(this);
        this.vista.lbl_back.addMouseListener(this);
        this.vista.txtBuscar.addKeyListener(this);

        //Se agrega un action listener por cada objeto
    }

    public void iniciar() {
        vista.setTitle("Destinos");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);

        cargarPaises();
        habilitarBotones(false);
        cargarTabla();
    }

    private void cargarTabla() {
        TablaDestinos tabla = new TablaDestinos();
        tabla.cargarTabla(vista.tablaDestinos, modelo.listar());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.btnAnadir) {
            if (datosValidos() && deseaRegistrar() == 0) {
                registrar();
                habilitarBotones(false);
            }
        } else if (ae.getSource() == vista.btnModificar) {
            if (vista.txtCiudad.getText().length() != 0 && deseaModificar() == 0) {
                modificar();
                habilitarBotones(false);
            }
        }
        if (ae.getSource() == vista.comboPais) {
            if (vista.comboPais.getSelectedIndex() != 0) {
                cargarEstados((PaisVO) vista.comboPais.getSelectedItem());
            }
        }
        if (ae.getSource() == vista.btnEliminar) {
            if (vista.tablaDestinos.getSelectedRow()!=-1 && deseaEliminar() == 0) {
                eliminar();
                habilitarBotones(false);
            }
        }
    }

    private void eliminar() {
        DestinoVO destino = new DestinoVO();
        int id = Integer.parseInt(vista.tablaDestinos.getValueAt(vista.tablaDestinos.getSelectedRow(), 0).toString());
        destino.setId(id);
        if (modelo.eliminar(destino) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha eliminado el destino");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al eliminar el destino");
        }
        clean();
    }

    private int deseaEliminar() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el destino? ", "Eliminar", dialog));
    }

    private void modificar() {
        int id = Integer.parseInt(vista.tablaDestinos.getValueAt(vista.tablaDestinos.getSelectedRow(), 0).toString());
        int idEstado = Integer.parseInt(vista.tablaDestinos.getValueAt(vista.tablaDestinos.getSelectedRow(), 2).toString());
        DestinoVO destino = new DestinoVO(id, vista.txtCiudad.getText(), idEstado);
        if (modelo.actualizar(destino) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha modificado el destino");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al modificar el destino");
        }
        clean();
    }

    private void cargarPaises() {
        modeloPais = new PaisDAO();
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            for (PaisVO pais : modeloPais.listar()) {
                combo.addItem(pais);
            }
            vista.comboPais.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarEstados(PaisVO pais) {
        modeloEstado = new EstadoDAO();
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            for (EstadoVO estado : modeloEstado.encontrar(pais.getId())) {
                combo.addItem(estado);
            }
            vista.comboEstado.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void clean() {
        vista.txtCiudad.setText("");
        vista.comboPais.setSelectedIndex(0);
        vista.txtBuscar.setText("");
    }

    private void registrar() {
        EstadoVO estado = (EstadoVO) vista.comboEstado.getSelectedItem();
        DestinoVO destino = new DestinoVO(vista.txtCiudad.getText(), estado.getId());
        if (modelo.insertar(destino) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha registrado el destino");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al registrar el destino");
        }
        clean();
    }

    private boolean datosValidos() {
        if (vista.txtCiudad.getText().length() == 0) {
            return false;
        }
        if (vista.comboEstado.getSelectedIndex() == 0) {
            return false;
        }
        if (vista.comboPais.getSelectedIndex() == 0) {
            return false;
        }
        return true;
    }

    private int deseaRegistrar() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea registrar el destino? ", "Registrar", dialog));
    }

    private int deseaModificar() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea modificar el destino? ", "Modificar", dialog));
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == vista.tablaDestinos) {
            rellenarFormulario();
            habilitarBotones(true);
        } else if (me.getSource() == vista.lbl_back) {
            abrirMenu();
        }
    }

    private void habilitarBotones(boolean estado) {
        vista.btnModificar.setEnabled(estado);
        vista.btnEliminar.setEnabled(estado);
    }

    private void rellenarFormulario() {
        DestinoVO destino = obtenerElementoDePosicion(vista.tablaDestinos.getSelectedRow());
        vista.txtCiudad.setText(destino.getCiudad());
        vista.comboPais.setSelectedIndex(0);
    }

    private DestinoVO obtenerElementoDePosicion(int fila) {
        DestinoVO temp = new DestinoVO();
        temp.setId(Integer.parseInt(vista.tablaDestinos.getValueAt(fila, 0).toString()));
        temp.setCiudad(vista.tablaDestinos.getValueAt(fila, 1).toString());
        temp.setIdEstado(Integer.parseInt(vista.tablaDestinos.getValueAt(fila, 2).toString()));
        return temp;
    }

    private void abrirMenu() {
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

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getSource() == vista.txtBuscar) {
            if (vista.txtBuscar.getText().equals("")) {
                cargarTabla();
            }
            else{
                buscarPorNombre();
            }
        }
    }
    
    private void buscarPorNombre() {
        TablaDestinos tabla = new TablaDestinos();
        tabla.cargarTabla(vista.tablaDestinos, modelo.encontrar(vista.txtBuscar.getText()));
    }
}
