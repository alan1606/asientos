/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ClassDAO.ClienteDAO;
import ClassVO.ClienteVO;
import ClassVO.UsuarioVO;
import Tables.TablaClientes;
import Vista.Clientes;
import Vista.MenuAdmin;
import Vista.MenuCoordinador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author alanm
 */
public class ControladorClientes implements ActionListener, MouseListener, KeyListener {

    private Clientes vista;
    private ClienteDAO modelo;
    private UsuarioVO usuario;

    public ControladorClientes(Clientes vista, UsuarioVO usuario) {
        this.vista = vista;
        this.usuario = usuario;

        modelo = new ClienteDAO();

        this.vista.btnAnadir.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.comboTipo.addActionListener(this);
        this.vista.radioCorreo.addActionListener(this);
        this.vista.radioNombre.addActionListener(this);
        this.vista.radioTelefono.addActionListener(this);
        this.vista.tablaClientes.addMouseListener(this);
        this.vista.lbl_back.addMouseListener(this);
        this.vista.txtBuscar.addKeyListener(this);

        //Se agrega un action listener por cada objeto
    }

    public void iniciar() {
        vista.setTitle("Clientes");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);

        cargarCombobox();
        cargarTabla();
        habilitarBotones(false);

    }

    private void cargarCombobox() {
        vista.comboTipo.addItem("SELECCIONE UNA OPCIÓN");
        vista.comboTipo.addItem("CLIENTE");
        vista.comboTipo.addItem("AGENCIA");
    }

    private void cargarTabla() {
        TablaClientes tabla = new TablaClientes();
        tabla.cargarTabla(vista.tablaClientes, modelo.listar());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.btnAnadir) {
            if (datosValidos() && deseaRegistrar() == 0) {
                registrar();
                habilitarBotones(false);
            }
        } else if (ae.getSource() == vista.btnModificar) {
            if (datosValidos() && deseaModificar() == 0) {
                modificar();
                habilitarBotones(false);
            }
        } else if (ae.getSource() == vista.btnEliminar) {
            if (datosValidos() && deseaEliminar() == 0) {
                eliminar();
                habilitarBotones(false);
            }
        } else if (ae.getSource() == vista.radioCorreo) {
            setRadios(false, false, true);
        } else if (ae.getSource() == vista.radioNombre) {
            setRadios(true, false, false);
        } else if (ae.getSource() == vista.radioTelefono) {
            setRadios(false, true, false);
        }

    }

    private void buscarPorNombre() {
        TablaClientes tabla = new TablaClientes();
        tabla.cargarTabla(vista.tablaClientes, modelo.encontrarNombre(vista.txtBuscar.getText()));
    }

    private void buscarPorTelefono() {
        TablaClientes tabla = new TablaClientes();
        tabla.cargarTabla(vista.tablaClientes, modelo.encontrarTelefono(vista.txtBuscar.getText()));
    }

    private void buscarPorCorreo() {
        TablaClientes tabla = new TablaClientes();
        tabla.cargarTabla(vista.tablaClientes, modelo.encontrarCorreo(vista.txtBuscar.getText()));
    }

    private void setRadios(boolean radioNombre, boolean radioTelefono, boolean radioCorreo) {
        vista.radioNombre.setSelected(radioNombre);
        vista.radioTelefono.setSelected(radioTelefono);
        vista.radioCorreo.setSelected(radioCorreo);
    }

    private void habilitarBotones(boolean estado) {
        vista.btnModificar.setEnabled(estado);
        vista.btnEliminar.setEnabled(estado);
        setRadios(true, false, false);
    }

    private void modificar() {
        ClienteVO cliente = new ClienteVO(vista.txtNombre.getText(), vista.txtTelefono.getText(), vista.txtCorreo.getText(), vista.comboTipo.getSelectedItem().toString());
        int id = Integer.parseInt(vista.tablaClientes.getValueAt(vista.tablaClientes.getSelectedRow(), 0).toString());
        cliente.setId(id);
        if (modelo.actualizar(cliente) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha modificado el cliente");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al modificar el cliente");
        }
        clean();
    }

    private int deseaModificar() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea modificar el cliente? ", "Modificar", dialog));
    }

    private int deseaEliminar() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el cliente? ", "Eliminar", dialog));
    }

    private void eliminar() {
        ClienteVO cliente = new ClienteVO();
        int id = Integer.parseInt(vista.tablaClientes.getValueAt(vista.tablaClientes.getSelectedRow(), 0).toString());
        cliente.setId(id);
        if (modelo.eliminar(cliente) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha eliminado el cliente");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al eliminar el cliente");
        }
        clean();
    }

    private int deseaRegistrar() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea registrar el cliente? ", "Registrar", dialog));
    }

    private boolean datosValidos() {
        if (vista.txtNombre.getText().length() == 0) {
            return false;
        }
        if (vista.txtTelefono.getText().length() == 0) {
            return false;
        }
        if (vista.comboTipo.getSelectedIndex() == 0) {
            return false;
        }
        return true;
    }

    private void clean() {
        vista.txtNombre.setText("");
        vista.txtTelefono.setText("");
        vista.txtCorreo.setText("");
        vista.comboTipo.setSelectedIndex(0);
        vista.txtBuscar.setText("");
    }

    private void registrar() {
        ClienteVO cliente = new ClienteVO(vista.txtNombre.getText(), vista.txtTelefono.getText(), vista.txtCorreo.getText(), vista.comboTipo.getSelectedItem().toString());
        if (modelo.insertar(cliente) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha registrado el cliente");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al registrar el cliente");
        }
        clean();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == vista.tablaClientes) {
            rellenarFormulario();
            habilitarBotones(true);
        } else if (me.getSource() == vista.lbl_back) {
            abrirMenu();
        }
    }

    private void rellenarFormulario() {
        ClienteVO cliente = obtenerElementoDePosicion(vista.tablaClientes.getSelectedRow());
        vista.txtNombre.setText(cliente.getNombre());
        vista.txtCorreo.setText(cliente.getCorreo());
        vista.txtTelefono.setText(cliente.getTelefono());
        vista.comboTipo.setSelectedItem(cliente.getTipo());

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

    private ClienteVO obtenerElementoDePosicion(int fila) {
        ClienteVO temp = new ClienteVO();
        temp.setId(Integer.parseInt(vista.tablaClientes.getValueAt(fila, 0).toString()));
        temp.setNombre(vista.tablaClientes.getValueAt(fila, 1).toString());
        temp.setTelefono(vista.tablaClientes.getValueAt(fila, 2).toString());
        temp.setCorreo(vista.tablaClientes.getValueAt(fila, 3).toString());
        temp.setTipo(vista.tablaClientes.getValueAt(fila, 4).toString());
        return temp;
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
            } else if (vista.radioCorreo.isSelected()) {
                buscarPorCorreo();
            } else if (vista.radioNombre.isSelected()) {
                buscarPorNombre();
            } else {
                buscarPorTelefono();
            }
        }
    }
}
