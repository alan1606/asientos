/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ClassDAO.AsientoDAO;
import ClassDAO.DestinoDAO;
import ClassDAO.UsuarioDAO;
import ClassVO.UsuarioVO;
import Tables.TablaUsuarios;
import Vista.MenuAdmin;
import Vista.MenuCoordinador;
import Vista.Usuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author alanm
 */
public class ControladorUsuarios implements ActionListener, MouseListener, KeyListener {
    
    private Usuarios vista;
    private UsuarioDAO modelo;
    private UsuarioVO usuario;
    
    public ControladorUsuarios(Usuarios vista, UsuarioVO usuario) {
        this.vista = vista;
        this.usuario = usuario;
        
        modelo = new UsuarioDAO();
        
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.comboTipo.addActionListener(this);
        this.vista.lbl_back.addMouseListener(this);
        this.vista.tUsuarios.addMouseListener(this);
        this.vista.txtSearch.addMouseListener(this);
        this.vista.radioAdministrador.addActionListener(this);
        this.vista.radioCoordinador.addActionListener(this);
        this.vista.txtSearch.addKeyListener(this);
        //Se agrega un action listener por cada objeto
    }
    
    public void iniciar() {
        vista.setTitle("Usuarios");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        
        cargarTiposUsuarios();
        habilitarBotones(false);
        cargarTabla();
    }
    
    private void cargarTabla() {
        TablaUsuarios tabla = new TablaUsuarios();
        tabla.cargarTabla(vista.tUsuarios, modelo.listar());
    }
    
    private void cargarTiposUsuarios() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            combo.addItem("Administrador");
            combo.addItem("Coordinador");
            vista.comboTipo.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.btnRegistrar) {
            if (datosValidos() && deseaRegistrar() == 0) {
                try {
                    registrar();
                    habilitarBotones(false);
                } catch (ParseException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        } else if (ae.getSource() == vista.btnModificar) {
            if (datosValidos() && deseaModificar() == 0) {
                modificar();
                habilitarBotones(false);
            }
        } else if (ae.getSource() == vista.btnEliminar) {
            if (vista.tUsuarios.getSelectedRow() != -1 && deseaEliminar() == 0) {
                eliminar();
                habilitarBotones(false);
            }
        } else if (ae.getSource() == vista.radioAdministrador) {
            vista.radioCoordinador.setSelected(false);
            vista.radioAdministrador.setSelected(true);
             buscarPorTipo("Administrador");
        } else if (ae.getSource() == vista.radioCoordinador) {
            vista.radioCoordinador.setSelected(true);
            vista.radioAdministrador.setSelected(false);
            buscarPorTipo("Coordinador");
        }
    }
    
    private void eliminar() {
        UsuarioVO usuario = new UsuarioVO();
        int id = Integer.parseInt(vista.tUsuarios.getValueAt(vista.tUsuarios.getSelectedRow(), 0).toString());
        usuario.setId(id);
        if (modelo.eliminar(usuario) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha eliminado el usuario");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al eliminar el usuario");
        }
        clean();
    }
    
    private int deseaEliminar() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el usuario? ", "Eliminar", dialog));
    }
    
    private void modificar() {
        int id = Integer.parseInt(vista.tUsuarios.getValueAt(vista.tUsuarios.getSelectedRow(), 0).toString());
        UsuarioVO usuario = new UsuarioVO(id, vista.txtUsuario.getText(), vista.txtPasswd.getText(), vista.txtNombre.getText(), vista.comboTipo.getSelectedItem().toString(), vista.txtTelefono.getText());
        usuario.setPass(usuario.getMD5(usuario.getPass()));
        if (modelo.actualizar(usuario) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha modificado el usuario");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al modificar el usuario");
        }
        clean();
    }
    
    private void clean() {
        vista.txtUsuario.setText("");
        vista.txtPasswd.setText("");
        vista.txtNombre.setText("");
        vista.txtSearch.setText("");
        vista.txtTelefono.setText("");
        vista.comboTipo.setSelectedIndex(0);
        vista.radioAdministrador.setSelected(false);
        vista.radioCoordinador.setSelected(false);
    }
    
    private void registrar() throws ParseException {
        UsuarioVO usuario = new UsuarioVO(vista.txtUsuario.getText(), vista.txtPasswd.getText(), vista.txtNombre.getText(), vista.comboTipo.getSelectedItem().toString(), vista.txtTelefono.getText());
        usuario.setPass(usuario.getMD5(usuario.getPass()));
        if (modelo.insertar(usuario) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha registrado el usuario");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al registrar el usuario");
        }
        clean();
    }
    
    private boolean datosValidos() {
        if (vista.txtUsuario.getText().length() == 0) {
            return false;
        }
        
        if (vista.txtNombre.getText().length() == 0) {
            return false;
        }
        if (vista.txtPasswd.getText().length() == 0) {
            return false;
        }
        if (vista.comboTipo.getSelectedIndex() == 0) {
            return false;
        }
        if(vista.txtTelefono.getText().equals("")){
            return false;
        }
        return true;
    }
    
    private int deseaRegistrar() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea registrar el usuario? ", "Registrar", dialog));
    }
    
    private int deseaModificar() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea modificar el usuario? ", "Modificar", dialog));
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == vista.tUsuarios) {
            try {
                rellenarFormulario();
                habilitarBotones(true);
            } catch (ParseException ex) {
                ex.printStackTrace(System.out);
            }
        } else if (me.getSource() == vista.lbl_back) {
            abrirMenu();
        }
    }
    
    private void habilitarBotones(boolean estado) {
        vista.btnModificar.setEnabled(estado);
        vista.btnEliminar.setEnabled(estado);
    }
    
    private void rellenarFormulario() throws ParseException {
        UsuarioVO usuario = obtenerElementoDePosicion(vista.tUsuarios.getSelectedRow());
        vista.txtUsuario.setText(usuario.getUsuario());
        vista.txtNombre.setText(usuario.getNombre());
        vista.comboTipo.setSelectedItem(usuario.getTipo());
        vista.txtTelefono.setText(usuario.getTelefono());
    }
    
    private UsuarioVO obtenerElementoDePosicion(int fila) throws ParseException {
        int id = (Integer.parseInt(vista.tUsuarios.getValueAt(fila, 0).toString()));
        UsuarioVO temp = modelo.encontrar(id);
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
        if (ke.getSource() == vista.txtSearch) {
            UsuarioVO buscar;
            if (vista.radioAdministrador.isSelected() && vista.txtSearch.getText().length() != 0) {
                buscar = new UsuarioVO();
                buscar.setNombre(vista.txtSearch.getText());
                buscar.setTipo("Administrador");
                buscarPorNombreTipo(buscar);
                
            } else if (vista.radioCoordinador.isSelected() && vista.txtSearch.getText().length() != 0) {
                buscar = new UsuarioVO();
                buscar.setNombre(vista.txtSearch.getText());
                buscar.setTipo("Coordinador");
                buscarPorNombreTipo(buscar);
                
    
            } else if (vista.txtSearch.getText().length() != 0) {
                buscar = new UsuarioVO();
                buscar.setNombre(vista.txtSearch.getText());
                buscarPorNombre(buscar);
               

            } else {
                cargarTabla();
            }
        }
    }
    
    private void buscarPorNombre(UsuarioVO recibido) {
        TablaUsuarios tabla = new TablaUsuarios();
        tabla.cargarTabla(vista.tUsuarios, modelo.encontrarLikeNombre(recibido.getNombre()));
    }
    
    private void buscarPorNombreTipo(UsuarioVO recibido) {
        TablaUsuarios tabla = new TablaUsuarios();
        tabla.cargarTabla(vista.tUsuarios, modelo.encontrarLikeNombreTipo(recibido.getNombre(), recibido.getTipo()));
    }
    
     private void buscarPorTipo(String tipo) {
        TablaUsuarios tabla = new TablaUsuarios();
        tabla.cargarTabla(vista.tUsuarios, modelo.encontrarTipo(tipo));
    }
    
}
