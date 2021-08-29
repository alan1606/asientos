/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ClassDAO.AsientoDAO;
import ClassDAO.DestinoDAO;
import ClassDAO.ViajeDAO;
import ClassVO.AsientoVO;
import ClassVO.DestinoVO;
import ClassVO.UsuarioVO;
import ClassVO.ViajeVO;
import Conexion.Conexion;
import Tables.TablaViajes;
import Vista.MenuAdmin;
import Vista.MenuCoordinador;
import Vista.Viajes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author alanm
 */
public class ControladorViajes implements ActionListener, MouseListener, KeyListener, PropertyChangeListener {

    private Viajes vista;
    private ViajeDAO modelo;
    private UsuarioVO usuario;
    private DestinoDAO modeloDestino;
    private AsientoDAO modeloAsientos;

    public ControladorViajes(Viajes vista, UsuarioVO usuario) {
        this.vista = vista;
        this.usuario = usuario;

        modelo = new ViajeDAO();

        this.vista.btnCrear.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.comboDestino.addActionListener(this);
        this.vista.comboAsientos.addActionListener(this);
        this.vista.tableViajes.addMouseListener(this);
        this.vista.lbl_back.addMouseListener(this);
        this.vista.comboDestinoSearch.addActionListener(this);
        this.vista.dateSearch.addPropertyChangeListener(this);
        //Se agrega un action listener por cada objeto
    }

    public void iniciar() {
        vista.setTitle("Viajes");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);

        cargarDestinos();
        cargarBusquedaDestinos();
        cargarAsientos();
        habilitarBotones(false);
        cargarTabla();
        vista.comboDestino.requestFocus();
    }

    private void cargarTabla() {
        TablaViajes tabla = new TablaViajes();
        tabla.cargarTabla(vista.tableViajes, modelo.listar());
    }

    private void cargarAsientos() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            combo.addItem("47");
            combo.addItem("64");
            vista.comboAsientos.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.btnCrear) {
            if (datosValidos() && deseaRegistrar() == 0) {
                try {
                    registrar();
                    habilitarBotones(false);
                } catch (ParseException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        } else if (ae.getSource() == vista.btnModificar) {
            if (datosValidosModificacion() && deseaModificar() == 0) {
                try {
                    ViajeVO viaje = obtenerElementoDePosicion(vista.tableViajes.getSelectedRow());
                    int asientosAnteriores = viaje.getNoAsientos();
                    int asientosNuevos = Integer.parseInt(vista.comboAsientos.getSelectedItem().toString());

                    if (asientosAnteriores == asientosNuevos) {
                        modificar();
                    } else if (asientosNuevos > asientosAnteriores) {
                        crearAsientos(viaje, asientosAnteriores + 1, asientosNuevos);
                        modificar();
                    } else {
                        if (!seHanCompradoUltimosAsientos(viaje)) {
                            eliminar(viaje, asientosNuevos + 1, asientosAnteriores);
                            modificar();
                        } else {
                            JOptionPane.showMessageDialog(vista, "No se ha podido cambiar el número de asientos porque hay boletos comprados en los últimos lugares");
                        }
                    }
                } catch (ParseException ex) {
                    ex.printStackTrace(System.out);
                }
                habilitarBotones(false);
            }
        }
        if (ae.getSource() == vista.comboDestinoSearch) {
            if (vista.comboDestinoSearch.getSelectedIndex() != 0) {
                buscarPorDestino((DestinoVO) vista.comboDestinoSearch.getSelectedItem());
            } else {
                cargarTabla();
            }
        }
        if (ae.getSource() == vista.btnEliminar) {
            if (vista.tableViajes.getSelectedRow() != -1 && deseaEliminar() == 0) {
                try {
                    if (!seHanCompradoAsientos()) {
                        eliminar();
                        habilitarBotones(false);
                    } else {
                        JOptionPane.showMessageDialog(vista, "No puede eliminar un viaje que tiene vendidos boletos");
                        clean();
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorViajes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private boolean seHanCompradoUltimosAsientos(ViajeVO viaje) {
        modeloAsientos = new AsientoDAO();
        for (AsientoVO asiento : modeloAsientos.encontrarUltimos(viaje)) {
            if (!asiento.isDisponible()) {
                return true;
            }
        }
        return false;
    }

    private boolean seHanCompradoAsientos() throws ParseException {
        ViajeVO viaje = obtenerElementoDePosicion(vista.tableViajes.getSelectedRow());
        modeloAsientos = new AsientoDAO();
        for (AsientoVO asiento : modeloAsientos.encontrar(viaje.getId())) {
            if (!asiento.isDisponible()) {
                return true;
            }
        }
        return false;
    }

    private void eliminar() {
        eliminarAsientos();
        ViajeVO viaje = new ViajeVO();
        int id = Integer.parseInt(vista.tableViajes.getValueAt(vista.tableViajes.getSelectedRow(), 0).toString());
        viaje.setId(id);
        if (modelo.eliminar(viaje) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha eliminado el viaje");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al eliminar el viaje");
        }
        clean();
    }

    private void eliminar(ViajeVO viaje, int inicio, int fin) {
        modeloAsientos = new AsientoDAO();
        int cantidadElminados = modeloAsientos.eliminar(viaje.getId(), inicio, fin);
        if (cantidadElminados > 0) {
            System.out.println(cantidadElminados + " asientos eliminados");
        } else {
            System.out.println("Error");
        }
    }

    private void eliminarAsientos() {
        modeloAsientos = new AsientoDAO();
        int id = Integer.parseInt(vista.tableViajes.getValueAt(vista.tableViajes.getSelectedRow(), 0).toString());
        if (modeloAsientos.eliminar(id) > 0) {
            System.out.println("Asientos eliminados");
        } else {
            System.out.println("Error");
        }
    }

    private int deseaEliminar() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el viaje? ", "Eliminar", dialog));
    }

    private void modificar() throws ParseException {
        int id = Integer.parseInt(vista.tableViajes.getValueAt(vista.tableViajes.getSelectedRow(), 0).toString());
        int idDestino = Integer.parseInt(vista.tableViajes.getValueAt(vista.tableViajes.getSelectedRow(), 1).toString());
        ViajeVO viaje = new ViajeVO(id, idDestino, vista.dateFecha.getFechaSeleccionada(), Integer.parseInt(vista.comboAsientos.getSelectedItem().toString()), vista.txtObservaciones.getText());
        if (modelo.actualizar(viaje) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha modificado el viaje");
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al modificar el viaje");
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
        vista.comboDestino.setSelectedIndex(0);
        vista.dateFecha.setLimpiarFecha(true);
        vista.comboDestinoSearch.setSelectedIndex(0);
        vista.comboAsientos.setSelectedIndex(0);
        vista.txtObservaciones.setText("");
    }

    private void registrar() throws ParseException {
        DestinoVO destino = (DestinoVO) vista.comboDestino.getSelectedItem();
        ViajeVO viaje = new ViajeVO(destino.getId(), vista.dateFecha.getFechaSeleccionada(), Integer.parseInt(vista.comboAsientos.getSelectedItem().toString()), vista.txtObservaciones.getText());
        if (modelo.insertar(viaje) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha registrado el viaje");
            cargarTabla();
            crearAsientos(viaje, 1, viaje.getNoAsientos());
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al registrar el viaje");
        }
        clean();
    }

    private void crearAsientos(ViajeVO _viaje, int inicio, int fin) {
        ViajeVO viaje = (ViajeVO) modelo.encontrarByDestinoDate(_viaje.getIdDestino(), _viaje.getFecha()).get(0);
        modeloAsientos = new AsientoDAO();
        try {
            int insertados = modeloAsientos.insertar(viaje, inicio, fin);
            if (insertados > 0) {
                System.out.println("Se insertaron " + insertados + " asientos");
            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    private boolean datosValidos() {
        if (vista.comboDestino.getSelectedIndex() == 0) {
            return false;
        }
        if (vista.comboAsientos.getSelectedIndex() == 0) {
            return false;
        }
        if (vista.dateFecha.getFechaSeleccionada().equals("")) {
            return false;
        }
        return true;
    }

    private boolean datosValidosModificacion() {
        if (vista.comboAsientos.getSelectedIndex() == 0) {
            return false;
        }
        if (vista.dateFecha.getFechaSeleccionada().equals("")) {
            return false;
        }
        if (vista.tableViajes.getSelectedRow() == -1) {
            return false;
        }
        return true;
    }

    private int deseaRegistrar() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea registrar el viaje? ", "Registrar", dialog));
    }

    private int deseaModificar() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea modificar el viaje? ", "Modificar", dialog));
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == vista.tableViajes) {
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
        ViajeVO viaje = obtenerElementoDePosicion(vista.tableViajes.getSelectedRow());
        vista.comboDestino.setSelectedItem(viaje);
        vista.comboAsientos.setSelectedItem(viaje.getNoAsientos() + "");
        vista.dateFecha.setTextoFecha(viaje.getFecha().toString());
        vista.txtObservaciones.setText(viaje.getObservaciones());
    }

    private ViajeVO obtenerElementoDePosicion(int fila) throws ParseException {
        try {
            ViajeVO temp = new ViajeVO();
            temp.setId(Integer.parseInt(vista.tableViajes.getValueAt(fila, 0).toString()));
            temp.setIdDestino(Integer.parseInt(vista.tableViajes.getValueAt(fila, 1).toString()));
            temp.setFecha(vista.tableViajes.getValueAt(fila, 2).toString());
            temp.setNoAsientos(Integer.parseInt(vista.tableViajes.getValueAt(fila, 3).toString()));
            temp.setObservaciones(vista.tableViajes.getValueAt(fila, 4).toString());
            return temp;
        } catch (Exception e) {
        }
        return null;
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
        TablaViajes tabla = new TablaViajes();
        tabla.cargarTabla(vista.tableViajes, modelo.encontrarByDestino(destino.getId()));
    }

    private void buscarPorFecha(String fecha) {
        TablaViajes tabla = new TablaViajes();
        tabla.cargarTabla(vista.tableViajes, modelo.encontrarByFecha(fecha));
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getSource() == vista.dateSearch) {
            try {
                buscarPorFecha(Conexion.aSqlDate(vista.dateSearch.getDate()).toString());
            } catch (Exception e) {
            }
        }
    }

}
