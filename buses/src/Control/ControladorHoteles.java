/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ClassDAO.DestinoDAO;
import ClassDAO.HotelDAO;
import ClassDAO.HotelEnDestinoDAO;
import ClassVO.DestinoVO;
import ClassVO.HotelEnDestinoVO;
import ClassVO.HotelVO;
import ClassVO.UsuarioVO;
import Tables.TablaHoteles;
import Vista.Hoteles;
import Vista.MenuAdmin;
import Vista.MenuCoordinador;
import Vista.NuevoHotel;
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
public class ControladorHoteles implements ActionListener, KeyListener, MouseListener {

    private Hoteles vista;
    private NuevoHotel vistaNuevoHotel;
    private HotelDAO modelo;
    private UsuarioVO usuario;
    private DestinoDAO modeloDestino;
    private HotelEnDestinoDAO modeloHotelEnDestino;

    public ControladorHoteles(Hoteles vista, UsuarioVO usuario) {
        this.vista = vista;
        this.usuario = usuario;

        modelo = new HotelDAO();

        vistaNuevoHotel = new NuevoHotel();

        this.vista.btnAnadir.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnBorrar.addActionListener(this);
        this.vista.comboDestino.addActionListener(this);
        this.vista.comboHotel.addActionListener(this);
        this.vista.tableHoteles.addMouseListener(this);
        this.vista.lbl_back.addMouseListener(this);
        this.vista.comboDestinoSearch.addActionListener(this);
        this.vista.btnNuevoHotel.addActionListener(this);
        this.vistaNuevoHotel.btnGuardar.addActionListener(this);
        //Se agrega un action listener por cada objeto
    }

    public void iniciar() {
        vista.setTitle("Hoteles");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);

        cargarDestinos();
        cargarHoteles();
        cargarBusquedaDestinos();
        habilitarBotones(false);
        cargarTabla();
        vista.comboDestino.requestFocus();
    }

    private void cargarTabla() {
        TablaHoteles tabla = new TablaHoteles();
        tabla.cargarTabla(vista.tableHoteles, modelo.listar());
    }

    private void cargarHoteles() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            for (HotelVO hotel : modelo.listar()) {
                combo.addItem(hotel);
            }
            vista.comboHotel.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.btnAnadir) {
            if (datosValidos() && deseaRegistrar() == 0) {
                registrar();
                habilitarBotones(false);
            }
        } else if (ae.getSource() == vista.btnModificar) {
            if (datosValidosModificacion() && deseaModificar() == 0) {
                modificar();
                habilitarBotones(false);
            }
        } else if (ae.getSource() == vista.comboDestinoSearch) {
            if (vista.comboDestinoSearch.getSelectedIndex() != 0) {
                buscarPorDestino((DestinoVO) vista.comboDestinoSearch.getSelectedItem());
            } else {
                cargarTabla();
            }
            cleanMinusSearch();
            habilitarBotones(false);
        } else if (ae.getSource() == vista.btnBorrar) {
            if (vista.tableHoteles.getSelectedRow() != -1) {
                eliminar();
                habilitarBotones(false);
            }
        } else if (ae.getSource() == vista.btnNuevoHotel) {
            vistaNuevoHotel.setVisible(true);
        } else if (ae.getSource() == vistaNuevoHotel.btnGuardar) {
            if (vistaNuevoHotel.txtHotel.getText().length() != 0 && deseaRegistrarHotel() == 0) {
                registrarHotel();
            }
            vistaNuevoHotel.txtHotel.setText("");
            vistaNuevoHotel.dispose();
            iniciar();
        }
    }

    private void eliminar() {
        int idHotel = Integer.parseInt(vista.tableHoteles.getValueAt(vista.tableHoteles.getSelectedRow(), 0).toString());
        if (vista.comboDestinoSearch.getSelectedIndex() != 0) { //Se va a eliminar una instancia de hotelEnDestino
            if (deseaEliminarHotelEnDestino() == 0) {
                HotelEnDestinoVO hotelEnDestino = new HotelEnDestinoVO(idHotel, ((DestinoVO) vista.comboDestinoSearch.getSelectedItem()).getId());
                eliminarHotelEnDestino(hotelEnDestino);
            }
        } else if (deseaEliminarHotel() == 0) {
            HotelVO hotel = new HotelVO();
            hotel.setId(idHotel);
            eliminarHotel(hotel);
        }
        clean();
    }

    private void eliminarHotel(HotelVO hotel) {
        if (modelo.eliminar(hotel) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha eliminado el hotel");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al eliminar el hotel");
        }
    }

    private void eliminarHotelEnDestino(HotelEnDestinoVO hotelEnDestino) {
        modeloHotelEnDestino = new HotelEnDestinoDAO();
        if (modeloHotelEnDestino.eliminar(hotelEnDestino) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha eliminado el hotel en ese destino");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al eliminar el hotel en ese destino");
        }
    }

    private int deseaEliminarHotel() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el hotel? ", "Eliminar", dialog));
    }

    private int deseaEliminarHotelEnDestino() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el hotel en ese destino? ", "Eliminar", dialog));
    }

    private void modificar() {
        int id = Integer.parseInt(vista.tableHoteles.getValueAt(vista.tableHoteles.getSelectedRow(), 0).toString());
        HotelVO hotel = new HotelVO(id, vista.txtModificar.getText());
        if (modelo.actualizar(hotel) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha modificado el hotel");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al modificar el hotel");
        }
        clean();
    }

    private void cargarDestinos() {
        modeloDestino = new DestinoDAO();
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            for (DestinoVO destino : modeloDestino.listar()) {
                combo.addItem(destino);
            }
            vista.comboDestino.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarBusquedaDestinos() {
        modeloDestino = new DestinoDAO();
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            for (DestinoVO destino : modeloDestino.listar()) {
                combo.addItem(destino);
            }
            vista.comboDestinoSearch.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void clean() {
        vista.comboHotel.setSelectedIndex(0);
        vista.comboDestino.setSelectedIndex(0);
        vista.comboDestinoSearch.setSelectedIndex(0);
        vista.txtModificar.setText("");
    }

    private void cleanMinusSearch() {
        vista.comboHotel.setSelectedIndex(0);
        vista.comboDestino.setSelectedIndex(0);
        vista.txtModificar.setText("");
    }

    private void registrar() {
        modeloHotelEnDestino = new HotelEnDestinoDAO();
        int idHotel = ((HotelVO) (vista.comboHotel.getSelectedItem())).getId();
        int idDestino = ((DestinoVO) (vista.comboDestino.getSelectedItem())).getId();
        HotelEnDestinoVO hotelEnDestino = new HotelEnDestinoVO(idHotel, idDestino);
        System.out.println(hotelEnDestino.getIdHotel() + " " + hotelEnDestino.getIdDestino());
        if (modeloHotelEnDestino.insertar(hotelEnDestino) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha registrado el hotel en el destino");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al registrar el hotel en el destino");
        }
        clean();
    }

    private void registrarHotel() {
        HotelVO hotel = new HotelVO(vistaNuevoHotel.txtHotel.getText());
        if (modelo.insertar(hotel) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha registrado el hotel");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al registrar el hotel");
        }
        clean();
    }

    private boolean datosValidos() {
        if (vista.comboDestino.getSelectedIndex() == 0) {
            return false;
        }
        if (vista.comboHotel.getSelectedIndex() == 0) {
            return false;
        }
        return true;
    }

    private boolean datosValidosModificacion() {
        if (vista.txtModificar.getText().length() == 0) {
            return false;
        }
        if (vista.tableHoteles.getSelectedRow() == -1) {
            return false;
        }
        return true;
    }

    private int deseaRegistrar() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea registrar el hoel en el destino? ", "Registrar", dialog));
    }

    private int deseaRegistrarHotel() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea registrar el hoel? ", "Registrar", dialog));
    }

    private int deseaModificar() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea modificar el nombre del hotel? ", "Modificar", dialog));
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == vista.tableHoteles) {
            rellenarFormulario();
            habilitarBotones(true);
        } else if (me.getSource() == vista.lbl_back) {
            abrirMenu();
        }
    }

    private void habilitarBotones(boolean estado) {
        vista.btnModificar.setEnabled(estado);
        vista.btnBorrar.setEnabled(estado);
        vista.txtModificar.setEnabled(estado);
    }

    private void rellenarFormulario() {
        HotelVO hotel = obtenerElementoDePosicion(vista.tableHoteles.getSelectedRow());
        vista.txtModificar.setText(hotel.getNombre());
        vista.comboDestino.setSelectedIndex(0);
        vista.comboHotel.setSelectedIndex(0);
    }

    private HotelVO obtenerElementoDePosicion(int fila) {
        HotelVO temp = new HotelVO();
        temp.setId(Integer.parseInt(vista.tableHoteles.getValueAt(fila, 0).toString()));
        temp.setNombre(vista.tableHoteles.getValueAt(fila, 1).toString());
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

    }

    private void buscarPorDestino(DestinoVO destino) {
        TablaHoteles tabla = new TablaHoteles();
        tabla.cargarTabla(vista.tableHoteles, modelo.encontrarByDestino(destino.getId()));
    }

}
