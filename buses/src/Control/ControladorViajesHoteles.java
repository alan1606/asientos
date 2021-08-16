package Control;

import ClassDAO.DestinoDAO;
import ClassDAO.DetalleHotelDestinoViajeDAO;
import ClassDAO.HotelDAO;
import ClassDAO.HotelEnDestinoEnViajeDAO;
import ClassDAO.ViajeDAO;
import ClassVO.DestinoVO;
import ClassVO.HotelEnDestinoEnViajeVO;
import ClassVO.HotelVO;
import ClassVO.UsuarioVO;
import ClassVO.ViajeVO;
import Tables.TablaHotelDestinoViaje;
import Vista.MenuAdmin;
import Vista.MenuCoordinador;

import Vista.ViajesHoteles;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorViajesHoteles implements ActionListener, MouseListener, KeyListener {

    private ViajesHoteles vista;
    private HotelEnDestinoEnViajeDAO modelo;
    private UsuarioVO usuario;
    private DestinoDAO modeloDestino;
    private ViajeDAO modeloViaje;
    private HotelDAO modeloHoteles;
    private DetalleHotelDestinoViajeDAO modeloDetalles;

    public ControladorViajesHoteles(ViajesHoteles vista, UsuarioVO usuario) {
        this.vista = vista;
        this.usuario = usuario;

        modelo = new HotelEnDestinoEnViajeDAO();

        this.vista.btnCrear.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.comboDestino.addActionListener(this);
        this.vista.comboFecha.addActionListener(this);
        this.vista.comboViaje.addActionListener(this);
        this.vista.comboHotel.addActionListener(this);
        this.vista.tableHoteles.addMouseListener(this);
        this.vista.lbl_back.addMouseListener(this);
        //Se agrega un action listener por cada objeto
    }

    public void iniciar() {
        vista.setTitle("Hoteles en viajes");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);

        cargarDestinos();
        habilitarBotones(false);
        habilitarEntradaCantidad(false);
        habilitarBotonAgregar(false);
    }

    private void cargarTabla() {
        TablaHotelDestinoViaje tabla = new TablaHotelDestinoViaje();
        tabla.cargarTabla(vista.tableHoteles, modelo.encontrarPorDestinoViaje(((DestinoVO) vista.comboDestino.getSelectedItem()).getId(), ((ViajeVO) vista.comboViaje.getSelectedItem()).getId()));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.comboDestino) {
            if (vista.comboDestino.getSelectedIndex() != 0) {
                cargarComboConFechas((DestinoVO) vista.comboDestino.getSelectedItem());
                cargarHoteles((DestinoVO) vista.comboDestino.getSelectedItem());
            }
        } else if (ae.getSource() == vista.comboFecha) {
            if (vista.comboFecha.getSelectedIndex() != 0) {
                cargarViajeSeleccionado((DestinoVO) vista.comboDestino.getSelectedItem(), (String) vista.comboFecha.getSelectedItem());
            }
        } else if (ae.getSource() == vista.comboViaje) {
            if (vista.comboViaje.getSelectedIndex() != 0) {
                cargarTabla();
            }
        } else if (ae.getSource() == vista.comboHotel) {
            //Necesitamos ver si ya está en la tabla, si está, te dice que le sumes al que ya está, si no está, si no, te habilita el numero para que introduzcas
            if (vista.comboHotel.getSelectedIndex() != 0) {
                habilitarEntradaCantidad(true);
                habilitarBotones(false);
                habilitarBotonAgregar(true);
            }
        } else if (ae.getSource() == vista.btnCrear) {
            if (hotelExisteEnTabla(((HotelVO) vista.comboHotel.getSelectedItem()).getId())) {
                JOptionPane.showMessageDialog(vista, "Modifique el hotel desde la tabla");
            } else if (datosValidos()) {
                System.out.println("aaa");
                agregarElementoATabla();
            }
            clean();
        } else if (ae.getSource() == vista.btnEliminar) {
            eliminar();
        } else if (ae.getSource() == vista.btnGuardar) {
            if (vista.tableHoteles.getRowCount() > 0) {
                guardarCambios();
                resetAll();
            }
        } else if (ae.getSource() == vista.btnModificar) {
            modificar();
        }

    }

    private void resetAll() {
        resetTabla();
        clean();
        habilitarBotonAgregar(false);
        habilitarBotones(false);
        habilitarEntradaCantidad(false);
        resetCombos();
    }

    private void resetCombos() {
        vista.comboDestino.setSelectedIndex(0);
        vista.comboFecha.setSelectedIndex(0);
        vista.comboHotel.setSelectedIndex(0);
        vista.comboViaje.setSelectedIndex(0);
    }

    private void resetTabla() {
        TablaHotelDestinoViaje tabla = new TablaHotelDestinoViaje();
        tabla.cargarTablaVacia(vista.tableHoteles);
    }

    private void guardarCambios() {
        Object[] fila;
        int opcion;
        for (int i = 0; i < vista.tableHoteles.getRowCount(); i++) {
            fila = obtenerElementoDePosicion(i);
            opcion = (int) fila[5];
            if (opcion == 0) { //Insertar en la base de datos
                insertarBaseDeDatos(i);
            } else if (opcion == -1) { //Eliminar de la base de datos
                eliminarDeBaseDeDatos(i);
            } else if (opcion == 1) {//Modificar en base de datos
                modificarBaseDeDatos(i);
            }
        }
    }

    private void modificarBaseDeDatos(int fila) {
        int id = Integer.parseInt(vista.tableHoteles.getValueAt(fila, 0).toString());
        int habitaciones = Integer.parseInt(vista.tableHoteles.getValueAt(fila, 2).toString());
        int disponibles = Integer.parseInt(vista.tableHoteles.getValueAt(fila, 3).toString());
        HotelEnDestinoEnViajeVO temp = new HotelEnDestinoEnViajeVO(id, habitaciones, disponibles);
        if (modelo.actualizar(temp) > 0) {
            System.out.println("Registro actualizado");
        }
    }

    private void eliminarDeBaseDeDatos(int fila) {
        int id = Integer.parseInt(vista.tableHoteles.getValueAt(fila, 0).toString());
        if (modelo.eliminar(id) > 0) {
            System.out.println("Registro eliminado");
        }
    }

    private void insertarBaseDeDatos(int fila) {
        HotelEnDestinoEnViajeVO registro;
        int habitaciones = Integer.parseInt(vista.tableHoteles.getValueAt(fila, 2).toString());
        int idHotel = Integer.parseInt(vista.tableHoteles.getValueAt(fila, 4).toString());
        registro = new HotelEnDestinoEnViajeVO(
                idHotel,
                ((DestinoVO) vista.comboDestino.getSelectedItem()).getId(),
                ((ViajeVO) vista.comboViaje.getSelectedItem()).getId(),
                habitaciones,
                habitaciones);
        if (modelo.insertar(registro) > 0) {
            System.out.println("Registro insertado");
        }
    }

    private void agregarElementoATabla() {
        DefaultTableModel dt = (DefaultTableModel) vista.tableHoteles.getModel();

        Object[] hotel = new Object[6];
        hotel[0] = "-1";
        hotel[1] = ((HotelVO) vista.comboHotel.getSelectedItem()).getNombre();
        hotel[2] = Integer.parseInt(vista.txtCantidad.getText());
        hotel[3] = Integer.parseInt(vista.txtCantidad.getText());
        hotel[4] = ((HotelVO) vista.comboHotel.getSelectedItem()).getId();
        hotel[5] = "0";

        dt.addRow(hotel);

        vista.tableHoteles.setModel(dt);
    }

    private void habilitarBotonAgregar(boolean valor) {
        vista.btnCrear.setEnabled(valor);
    }

    private void habilitarEntradaCantidad(boolean valor) {
        vista.txtCantidad.setEnabled(valor);
    }

    private boolean hotelExisteEnTabla(int idHotel) {
        System.out.println(idHotel);
        for (int i = 0; i < vista.tableHoteles.getRowCount(); i++) {
            int hotelTabla = (int) vista.tableHoteles.getValueAt(i, 4);
            if (idHotel == hotelTabla) {
                return true;
            }
        }
        return false;
    }

    private void cargarComboConFechas(DestinoVO destino) {
        modeloViaje = new ViajeDAO();
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            for (Object viaje : modeloViaje.encontrarByDestinoDistinctDate(destino.getId())) {
                combo.addItem(viaje);
            }
            vista.comboFecha.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarViajeSeleccionado(DestinoVO destino, String fecha) {
        modeloViaje = new ViajeDAO();
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            ArrayList<ViajeVO> viajes = modeloViaje.encontrarByDestinoDate(destino.getId(), fecha);
            for (int i = 0; i < viajes.size(); i++) {
                combo.addItem(viajes.get(i));
            }

            vista.comboViaje.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarHoteles(DestinoVO destino) {
        modeloHoteles = new HotelDAO();
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            ArrayList<HotelVO> hoteles = modeloHoteles.encontrarByDestino(destino.getId());
            for (HotelVO hotel : hoteles) {
                combo.addItem(hotel);
            }
            vista.comboHotel.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void eliminar() {
        int habitaciones = Integer.parseInt(vista.tableHoteles.getValueAt(vista.tableHoteles.getSelectedRow(), 2).toString());
        int disponibles = Integer.parseInt(vista.tableHoteles.getValueAt(vista.tableHoteles.getSelectedRow(), 3).toString());
        int registrado = Integer.parseInt(vista.tableHoteles.getValueAt(vista.tableHoteles.getSelectedRow(), 5).toString());

        if (registrado == 0) {
            //eliminar fila localmente
            eliminarFilaLocal(vista.tableHoteles.getSelectedRow());
        } else if (registrado == 1) {
            ///Va a permitir eliminar siempre y cuando no haya registros con asientos comprados
            //Busca detalle_hotel_destino_viaje por id, si encuentra por lo menos uno, dice que ya hay boletos vendidos en ese hotel, entonces no puede eliminar
            //Si va a eliminar, setea el último id en -1 para indicar que se eliminará
            modeloDetalles = new DetalleHotelDestinoViajeDAO();
            if (modeloDetalles.obtenerComprados(Integer.parseInt(vista.tableHoteles.getValueAt(vista.tableHoteles.getSelectedRow(), 0).toString())) == 0) {
                JOptionPane.showMessageDialog(vista, "Se eliminará el hotel");
                seteaEliminar(vista.tableHoteles.getSelectedRow());
            } else {
                JOptionPane.showMessageDialog(vista, "No se puede eliminar porque existen boletos vendidos en ese hotel");
            }
        }
        clean();
    }

    private void seteaEliminar(int fila) {
        int valor = -1;
        vista.tableHoteles.setValueAt(valor, fila, 5);
        habilitarBotonAgregar(true);
        habilitarBotones(false);
        habilitarEntradaCantidad(false);
    }

    private void eliminarFilaLocal(int fila) {
        DefaultTableModel dt = (DefaultTableModel) vista.tableHoteles.getModel();
        dt.removeRow(fila);
        vista.tableHoteles.setModel(dt);
    }

    private int deseaEliminar() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el viaje? ", "Eliminar", dialog));
    }

    private void modificar() {
        //Este hay que checarlo
        modeloDetalles = new DetalleHotelDestinoViajeDAO();
        int id = Integer.parseInt(vista.tableHoteles.getValueAt(vista.tableHoteles.getSelectedRow(), 0).toString());
        int habitaciones = Integer.parseInt(vista.tableHoteles.getValueAt(vista.tableHoteles.getSelectedRow(), 2).toString());
        int disponibles = Integer.parseInt(vista.tableHoteles.getValueAt(vista.tableHoteles.getSelectedRow(), 3).toString());
        if (esNumero(vista.txtCantidad.getText())) {
            int ingresado = Integer.parseInt(vista.txtCantidad.getText());
            if (ingresado == 0) {
                JOptionPane.showMessageDialog(vista, "Ya mejor elimínalo");
            } else if (id > 0) { //Si el registro viene desde la base de datos
                int comprados = modeloDetalles.obtenerComprados(id);
                JOptionPane.showMessageDialog(null, comprados);
                if (ingresado >= comprados) {
                    int nuevosDisponibles = ingresado - comprados;
                    vista.tableHoteles.setValueAt(ingresado, vista.tableHoteles.getSelectedRow(), 2);
                    vista.tableHoteles.setValueAt(nuevosDisponibles, vista.tableHoteles.getSelectedRow(), 3);
                } else {
                    JOptionPane.showMessageDialog(vista, "Hay vendidos más boletos que esta cantidad");
                }

            } else {//Si no simplemente lo modifico
                vista.tableHoteles.setValueAt(ingresado, vista.tableHoteles.getSelectedRow(), 2);
                vista.tableHoteles.setValueAt(ingresado, vista.tableHoteles.getSelectedRow(), 3);
            }
            habilitarBotonAgregar(true);
            habilitarBotones(false);
            habilitarEntradaCantidad(false);
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

    private void clean() {
        vista.txtCantidad.setText("");
        vista.comboHotel.setSelectedIndex(0);

    }

    private void registrar() throws ParseException {
        /*DestinoVO destino = (DestinoVO) vista.comboDestino.getSelectedItem();
        ViajeVO viaje = new ViajeVO(destino.getId(), vista.dateFecha.getFechaSeleccionada(), Integer.parseInt(vista.comboAsientos.getSelectedItem().toString()));
        if (modelo.insertar(viaje) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha registrado el viaje");
            cargarTabla();
            crearAsientos(viaje, 1, viaje.getNoAsientos());
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al registrar el viaje");
        }
        clean();*/
    }

    private boolean datosValidos() {

        if (vista.comboDestino.getSelectedIndex() == 0) {

            return false;
        }
        if (vista.comboFecha.getSelectedIndex() == 0) {

            return false;
        }
        if (vista.comboViaje.getSelectedIndex() == 0) {

            return false;
        }
        if (vista.comboHotel.getSelectedIndex() == 0) {

            return false;
        }
        if (!esNumero(vista.txtCantidad.getText())) {

            return false;
        }
        return true;

    }

    private boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == vista.tableHoteles) {
            try {
                rellenarFormulario();
                habilitarBotones(true);
                habilitarBotonAgregar(false);
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
        Object[] fila = obtenerElementoDePosicion(vista.tableHoteles.getSelectedRow());
        setComboHotel(fila[1].toString());
        vista.txtCantidad.setText(fila[2] + "");
    }

    private void setComboHotel(String nombre) {
        HotelVO hotel;
        for (int i = 1; i < vista.comboHotel.getItemCount(); i++) {
            vista.comboHotel.setSelectedIndex(i);
            hotel = (HotelVO) vista.comboHotel.getSelectedItem();
            if (hotel.getNombre().equals(nombre)) {
                break;
            }
        }
    }

    private Object[] obtenerElementoDePosicion(int fila) {
        Object[] temp = new Object[6];
        temp[0] = (Integer.parseInt(vista.tableHoteles.getValueAt(fila, 0).toString()));
        temp[1] = (vista.tableHoteles.getValueAt(fila, 1).toString());
        temp[2] = Integer.parseInt(vista.tableHoteles.getValueAt(fila, 2).toString());
        temp[3] = (Integer.parseInt(vista.tableHoteles.getValueAt(fila, 3).toString()));
        temp[4] = (Integer.parseInt(vista.tableHoteles.getValueAt(fila, 4).toString()));
        temp[5] = (Integer.parseInt(vista.tableHoteles.getValueAt(fila, 5).toString()));
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

}
