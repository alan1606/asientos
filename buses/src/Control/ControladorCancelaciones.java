/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ClassDAO.AsientoDAO;
import ClassDAO.ClienteDAO;
import ClassDAO.DestinoDAO;
import ClassDAO.DetalleDAO;
import ClassDAO.DetalleHotelDestinoViajeDAO;
import ClassDAO.HotelEnDestinoEnViajeDAO;
import ClassDAO.ViajeDAO;
import ClassVO.AsientoVO;
import ClassVO.ClienteVO;
import ClassVO.DestinoVO;
import ClassVO.DetalleHotelDestinoViajeVO;
import ClassVO.DetalleVO;
import ClassVO.HotelEnDestinoEnViajeVO;
import ClassVO.UsuarioVO;
import ClassVO.ViajeVO;
import Tables.TablaClientes;
import Vista.Cancelaciones;
import Vista.MenuAdmin;
import Vista.MenuCoordinador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author alanm
 */
public class ControladorCancelaciones implements ActionListener, MouseListener, KeyListener {

    private Cancelaciones vista;
    private UsuarioVO usuario;
    private ClienteDAO modeloCliente;
    private DetalleDAO modeloDetalles;
    private ViajeDAO modeloViajes;
    private AsientoDAO modeloAsientos;
    private HotelEnDestinoEnViajeDAO modeloHotelDestinoViaje;
    private DetalleHotelDestinoViajeDAO modeloDetalleHotelDestinoViaje;
    private DestinoDAO modeloDestinos;

    public ControladorCancelaciones(Cancelaciones vista, UsuarioVO usuario) {
        this.vista = vista;
        this.usuario = usuario;

        this.modeloCliente = new ClienteDAO();
        this.modeloDetalles = new DetalleDAO();
        this.modeloViajes = new ViajeDAO();
        this.modeloAsientos = new AsientoDAO();
        this.modeloHotelDestinoViaje = new HotelEnDestinoEnViajeDAO();
        this.modeloDestinos = new DestinoDAO();
        this.modeloDetalleHotelDestinoViaje = new DetalleHotelDestinoViajeDAO();

        this.vista.txtBuscar.addKeyListener(this);
        this.vista.radioCorreo.addActionListener(this);
        this.vista.radioNombre.addActionListener(this);
        this.vista.radioTelefono.addActionListener(this);
        this.vista.comboViaje.addActionListener(this);
        this.vista.lbl_back.addMouseListener(this);
        this.vista.tablaClientes.addMouseListener(this);
        this.vista.btnCancelar.addActionListener(this);
        //No voy a poner listeners sobre clientes ni sobre asientos, porque son datos obtenidos

    }

    public void iniciar() {
        vista.setTitle("Cancelaciones");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);

        cargarTabla();
        habilitarBotones(false);
        this.vista.txtCliente.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.radioCorreo) {
            setRadios(false, false, true);
        } else if (e.getSource() == vista.radioNombre) {
            setRadios(true, false, false);
        } else if (e.getSource() == vista.radioTelefono) {
            setRadios(false, true, false);
        } else if (e.getSource() == vista.comboViaje) {
            if (vista.comboViaje.getSelectedIndex() != 0) {
                int idViaje = obtenerIdDeComboViaje();
                int idCliente = obtenerIdDeTxtCliente();
                if (idViaje != 0 && idCliente != 0) {
                    cargarTickets(idViaje, idCliente);
                }
            }
        } else if (e.getSource() == vista.btnCancelar) {
            if (vista.comboTicket.getSelectedIndex() != 0) {
                if (deseaProceder() == 0) {
                    DetalleVO detalle = (DetalleVO) vista.comboTicket.getSelectedItem();
                    procesar(detalle, obtenerIdDeComboViaje(), obtenerIdDeTxtCliente());
                }
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.lbl_back) {
            abrirMenu();
        } else if (e.getSource() == vista.tablaClientes) {
            rellenarFormulario();
            habilitarBotones(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vista.txtBuscar) {
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

    private void buscarPorNombre() {
        TablaClientes tabla = new TablaClientes();
        tabla.cargarTabla(vista.tablaClientes, modeloCliente.encontrarNombre(vista.txtBuscar.getText()));
    }

    private void buscarPorTelefono() {
        TablaClientes tabla = new TablaClientes();
        tabla.cargarTabla(vista.tablaClientes, modeloCliente.encontrarTelefono(vista.txtBuscar.getText()));
    }

    private void buscarPorCorreo() {
        TablaClientes tabla = new TablaClientes();
        tabla.cargarTabla(vista.tablaClientes, modeloCliente.encontrarCorreo(vista.txtBuscar.getText()));
    }

    private void cargarTabla() {
        TablaClientes tabla = new TablaClientes();
        tabla.cargarTabla(vista.tablaClientes, modeloCliente.listar());
    }

    private void habilitarBotones(boolean estado) {
        vista.btnCancelar.setEnabled(estado);
        setRadios(true, false, false);
    }

    private void rellenarFormulario() {
        ClienteVO cliente = obtenerElementoDePosicion(vista.tablaClientes.getSelectedRow());
        if (cliente.getId() != 1) {
            vista.txtCliente.setText(cliente.getId() + " : " + cliente.getNombre());
            cargarViajesCliente(cliente.getId());
        } else {
            vista.txtCliente.setText("");
            cargarComboVacio(this.vista.comboViaje);
            cargarComboVacio(vista.comboTicket);
        }
    }

    private void setRadios(boolean radioNombre, boolean radioTelefono, boolean radioCorreo) {
        vista.radioNombre.setSelected(radioNombre);
        vista.radioTelefono.setSelected(radioTelefono);
        vista.radioCorreo.setSelected(radioCorreo);
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

    private void cargarViajesCliente(int id) {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            for (ViajeVO viaje : modeloViajes.encontrarByCliente(id)) {
                DestinoVO destino = modeloDestinos.encontrar(viaje.getIdDestino());
                combo.addItem(viaje.getId() + " : " + destino.getCiudad() + " - " + viaje.getFecha());
            }
            vista.comboViaje.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarComboVacio(JComboBox combo) {
        try {
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            combo.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private int obtenerIdDeComboViaje() {
        int i = 0;
        int res = 0;
        String numero = "";
        String comboViaje = vista.comboViaje.getSelectedItem().toString();
        do {
            numero += comboViaje.charAt(i);
            i++;
        } while (comboViaje.charAt(i) != ' ');
        try {
            res = Integer.parseInt(numero);
        } catch (Exception e) {
        }
        return res;
    }

    private int obtenerIdDeTxtCliente() {
        int i = 0;
        int res = 0;
        String numero = "";
        String txtCliente = vista.txtCliente.getText();
        do {
            numero += txtCliente.charAt(i);
            i++;
        } while (txtCliente.charAt(i) != ' ');
        try {
            res = Integer.parseInt(numero);
        } catch (Exception e) {
        }
        return res;
    }

    private void cargarTickets(int idViaje, int idCliente) {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            for (DetalleVO detalle : modeloDetalles.encontrar(idViaje, idCliente)) {
                combo.addItem(detalle);
            }
            vista.comboTicket.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void procesar(DetalleVO detalle, int idViaje, int idCliente) {
        int eliminados = 0;
        int asientosComprados = modeloAsientos.encontrarComprados(idViaje, idCliente);
        if (asientosComprados == detalle.getPersonas()) {
            eliminarTodosAsientos(modeloAsientos.encontrarViajeCliente(idViaje, idCliente));
            eliminados = asientosComprados;
        }//
        else {
            JOptionPane.showMessageDialog(null, "El cliente ha comprado " + asientosComprados + " asientos en el viaje\nSeleccione los asientos a liberar");
            eliminados = eliminarAsientos(modeloAsientos.encontrarViajeCliente(idViaje, idCliente), detalle.getPersonas());
        }

        if (eliminados != 0) {
            JOptionPane.showMessageDialog(null, "Cancelación correcta");

            //Actualización del estado del detalle a cancelado
            detalle.setEstado("CANCELADO");
            modeloDetalles.actualizar(detalle);
            
            if (detalle.getHabitaciones() != 0) {//Hay que liberar habitaciones
                liberarHabitaciones(detalle.getId());
            }
        }

    }

    private int deseaProceder() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea proceder con la cancelación? ", "Cancelación", dialog));
    }

    private void eliminarTodosAsientos(ArrayList<AsientoVO> asientos) {
        for (AsientoVO asiento : asientos) {
            asiento.setIdCliente(1);
            asiento.setDisponible(true);
            modeloAsientos.actualizar(asiento);
            System.out.println(asiento);
        }
    }

    private void eliminarAsiento(AsientoVO asiento) {
        asiento.setIdCliente(1);
        asiento.setDisponible(true);
        modeloAsientos.actualizar(asiento);
        System.out.println("actualizado " + asiento);
    }

    private int eliminarAsientos(ArrayList<AsientoVO> asientos, int personas) {
        int eliminados = 0;
        for (int i = 0; i < asientos.size(); i++) {
            if (eliminados < personas) {
                if (deseaEliminarAsiento(asientos.get(i)) == 0) {
                    eliminarAsiento(asientos.get(i));
                    eliminados++;
                }
            } else if (eliminados == personas) {
                break;
            }
        }
        return eliminados;
    }

    private int deseaEliminarAsiento(AsientoVO asiento) {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Desea liberar el asiento " + asiento.getNumero() + "?", "Liberar", dialog));
    }

    private void liberarHabitaciones(Long id) {
        for (DetalleHotelDestinoViajeVO detalle : modeloDetalleHotelDestinoViaje.encontrarDetalle(id)) {
            HotelEnDestinoEnViajeVO hotelDestino = modeloHotelDestinoViaje.encontrar(detalle.getIdHotelDestinoViaje());
            hotelDestino.setHabitacionesDisponibles((hotelDestino.getHabitacionesDisponibles() + detalle.getHabitaciones()));
            modeloHotelDestinoViaje.actualizar(hotelDestino);
            System.out.println("actualizado \n" + hotelDestino);
        }
    }

}
