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
import ClassDAO.EstadoDAO;
import ClassDAO.HotelDAO;
import ClassDAO.HotelEnDestinoEnViajeDAO;
import ClassDAO.ViajeDAO;
import ClassVO.AsientoVO;
import ClassVO.ClienteVO;
import ClassVO.DestinoVO;
import ClassVO.DetalleHotelDestinoViajeVO;
import ClassVO.DetalleVO;
import ClassVO.EstadoVO;
import ClassVO.HotelEnDestinoEnViajeVO;
import ClassVO.HotelVO;
import ClassVO.UsuarioVO;
import ClassVO.ViajeVO;
import Paneles.Panel47;
import Paneles.Panel64;
import Reports.GenerarReporte;
import Tables.TablaHoteles;
import Vista.Asientos;
import Vista.MenuAdmin;
import Vista.MenuCoordinador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//clean,registrar, habitacionesvalidas, hora valida
/**
 *
 * @author alanm
 */
public class ControladorAsientos implements ActionListener, KeyListener, MouseListener {

    private HotelEnDestinoEnViajeDAO modeloHotelDestinoViaje;
    private HotelDAO modeloHoteles;
    private Asientos vista;
    private ViajeDAO modeloViajes;
    private AsientoDAO modelo;
    private UsuarioVO usuario;
    private DestinoDAO modeloDestino;
    private ClienteDAO modeloCliente;
    private DetalleDAO modeloDetalle;
    private ArrayList<ViajeVO> viajesEnDestino;
    private int asientosAComprar = 0;
    private int asientosDisponibles = 0;
    private Panel47 panel47;
    private Panel64 panel64;
    ArrayList<AsientoVO> asientos;
    private boolean camion64 = false;
    private DetalleHotelDestinoViajeDAO modeloDetalleHotelDestinoViaje;
    private String fechaActual;
    private EstadoDAO modeloEstados;

    public ControladorAsientos(Asientos vista, UsuarioVO usuario) {
        this.vista = vista;
        this.usuario = usuario;

        modelo = new AsientoDAO();
        modeloEstados = new EstadoDAO();

        fechaActual = dateToString(new Date().getTime());

        this.vista.btnComprar.addActionListener(this);
        this.vista.comboTipoViaje.addActionListener(this);
        this.vista.comboFormaPago.addActionListener(this);
        this.vista.comboHoraRegreso.addActionListener(this);
        this.vista.comboDestino.addActionListener(this);
        this.vista.comboFecha.addActionListener(this);
        this.vista.comboNoAsientos.addActionListener(this);
        this.vista.comboNumeroHabitaciones.addActionListener(this);
        this.vista.comboId.addActionListener(this);
        this.vista.comboTipoCliente.addActionListener(this);
        this.vista.comboCliente.addActionListener(this);
        this.vista.comboAsientosAComprar.addActionListener(this);
        this.vista.comboHoteles.addActionListener(this);
        this.vista.btnAgregarHotel.addActionListener(this);
        this.vista.btnQuitarHotel.addActionListener(this);
        this.vista.lbl_back.addMouseListener(this);
        this.vista.comboHoraSalida.addActionListener(this);
        this.vista.txtSube.addKeyListener(this);
        this.vista.btnComprar.addKeyListener(this);

        //Se agrega un action listener por cada objeto
    }

    public void iniciar() {
        vista.setTitle("Venta de boletos");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        iniciarCombosVacios();
        iniciarTablaHabitaciones();
        cargarHoras();
        cargarTiposDeViaje();
        cargarFormasDePago();
        cargarHorasDeRegreso();
        deshabilitarElementosDebidoAViajeSencillo(false);
        vista.comboDestino.requestFocus();
    }

    private void deshabilitarElementosDebidoAViajeSencillo(boolean nuevoEstado) {
        this.vista.comboHoraRegreso.setEnabled(nuevoEstado);
        this.vista.comboHoteles.setEnabled(nuevoEstado);
        this.vista.comboNumeroHabitaciones.setEnabled(nuevoEstado);
        this.vista.btnAgregarHotel.setEnabled(nuevoEstado);
        this.vista.btnQuitarHotel.setEnabled(nuevoEstado);
        this.vista.tableHoteles.setEnabled(nuevoEstado);
        this.vista.dateFechaRegreso.setEnabled(nuevoEstado);
    }

    private void cargarTiposDeViaje() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            combo.addItem("REDONDO");
            combo.addItem("SENCILLO");
            vista.comboTipoViaje.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarFormasDePago() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            combo.addItem("EFECTIVO");
            combo.addItem("TARJETA");
            combo.addItem("DEPOSITO");
            vista.comboFormaPago.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarHorasDeRegreso() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            String hora = "";
            for (int i = 0; i < 24; i++) {
                for (int j = 0; j < 60; j += 15) {
                    if (i < 10) {
                        hora = ("0" + i);
                    } else {
                        hora = (i + "");
                    }
                    if (j == 0) {
                        hora += ":00";
                    } else {
                        hora += ":" + j;
                    }
                    combo.addItem(hora);
                    hora = "";
                }
            }
            vista.comboHoraRegreso.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void iniciarTablaHabitaciones() {

        DefaultTableModel dt = new DefaultTableModel();

        dt.addColumn("Hotel");
        dt.addColumn("Habitaciones");
        dt.addColumn("Id");

        vista.tableHoteles.setModel(dt);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.btnComprar) {
            if (datosValidos() && deseaComprar() == 0) {
                if (vista.comboTipoViaje.getSelectedIndex() == 1) {
                    registrarViajeRedondo();
                } else {
                    registrarViajeSencillo();
                }
                if (deseaVerTicket() == 0) {
                    mostrarTicket();
                }
                //Actualizar disponibilidad en los hoteles
                cargarPlantilla();
                clean();
            }
        } else if (ae.getSource() == vista.comboDestino) {
            if (vista.comboDestino.getSelectedIndex() != 0) {
                cargarFechas();
            }
            try {
                vista.comboFecha.setSelectedIndex(0);
                vista.comboNoAsientos.setSelectedIndex(0);
                vista.comboId.setSelectedIndex(0);
                vista.comboHoteles.setSelectedIndex(0);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }

        } else if (ae.getSource() == vista.comboFecha) {
            if (vista.comboFecha.getSelectedIndex() != 0) {
                cargarAsientos();

            }
            try {
                vista.comboNoAsientos.setSelectedIndex(0);
                vista.comboId.setSelectedIndex(0);
                vista.comboHoteles.setSelectedIndex(0);
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }

        } else if (ae.getSource() == vista.comboNoAsientos) {
            if (vista.comboNoAsientos.getSelectedIndex() != 0) {
                cargarIdsViajes();
            } else {
                try {
                    vista.comboId.setSelectedIndex(0);
                    vista.comboHoteles.setSelectedIndex(0);
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
            }

        } else if (ae.getSource() == vista.comboId) {
            if (vista.comboId.getSelectedIndex() != 0) {
                cargarPlantilla();
                cargarAsientosDisponibles();
            }
        } else if (ae.getSource() == vista.comboTipoCliente) {
            if (vista.comboTipoCliente.getSelectedIndex() != 0) {
                cargarClientes();
            }
        } else if (ae.getSource() == vista.comboAsientosAComprar) {
            if (vista.comboAsientosAComprar.getSelectedIndex() != 0) {
                cargarPlantilla();
                cargarHoteles((DestinoVO) vista.comboDestino.getSelectedItem(), Integer.parseInt(vista.comboId.getSelectedItem().toString()));
                asientosAComprar = Integer.parseInt(vista.comboAsientosAComprar.getSelectedItem().toString());
            }
        } else if (ae.getSource() == vista.comboHoteles) {
            if (vista.comboAsientosAComprar.getSelectedIndex() != 0) {
                if (vista.comboHoteles.getSelectedIndex() != 0) {
                    cargarHotelDestinoViaje((HotelVO) vista.comboHoteles.getSelectedItem(), (DestinoVO) vista.comboDestino.getSelectedItem(), Integer.parseInt(vista.comboId.getSelectedItem().toString()));
                }
            }
        } else if (ae.getSource() == vista.btnAgregarHotel) {
            if (vista.comboHoteles.getSelectedIndex() != 0 && vista.comboNumeroHabitaciones.getSelectedIndex() != 0 && vista.comboDestino.getSelectedIndex() != 0 && vista.comboId.getSelectedIndex() != 0) {

                agregarATablaHabitaciones(
                        (HotelVO) vista.comboHoteles.getSelectedItem(),
                        Integer.parseInt(vista.comboNumeroHabitaciones.getSelectedItem().toString()),
                        (DestinoVO) vista.comboDestino.getSelectedItem(),
                        Integer.parseInt(vista.comboId.getSelectedItem().toString())
                );
                try {
                    vista.comboHoteles.setSelectedIndex(0);
                    vista.comboNumeroHabitaciones.setSelectedIndex(0);
                } catch (Exception e) {
                }
            }
        } else if (ae.getSource() == vista.btnQuitarHotel) {
            if (vista.tableHoteles.getSelectedRow() != -1) {
                eliminarFilaLocal(vista.tableHoteles.getSelectedRow());
            }
        } else if (ae.getSource() == vista.comboTipoViaje) {
            if (vista.comboTipoViaje.getSelectedIndex() == 1) {
                deshabilitarElementosDebidoAViajeSencillo(true);
            } else {
                deshabilitarElementosDebidoAViajeSencillo(false);
            }
        }
    }

    private void eliminarFilaLocal(int fila) {
        DefaultTableModel dt = (DefaultTableModel) vista.tableHoteles.getModel();
        dt.removeRow(fila);
        vista.tableHoteles.setModel(dt);
    }

    private void agregarATablaHabitaciones(HotelVO hotelVO, int habitaciones, DestinoVO destino, int idViaje) {
        modeloHotelDestinoViaje = new HotelEnDestinoEnViajeDAO();
        HotelEnDestinoEnViajeVO hotelDestinoViaje = modeloHotelDestinoViaje.encontrar(hotelVO.getId(), destino.getId(), idViaje);

        boolean existeEnTabla = false;

        for (int i = 0; i < vista.tableHoteles.getRowCount(); i++) {
            if (Integer.parseInt(vista.tableHoteles.getValueAt(i, 2).toString()) == hotelDestinoViaje.getId()) {
                existeEnTabla = true;
                break;
            }
        }

        if (!existeEnTabla) {
            DefaultTableModel dt = (DefaultTableModel) vista.tableHoteles.getModel();

            Object[] hotel = new Object[3];
            hotel[0] = hotelVO.getNombre();
            hotel[1] = habitaciones;
            hotel[2] = hotelDestinoViaje.getId();

            dt.addRow(hotel);

            vista.tableHoteles.setModel(dt);
        }

    }

    private void cargarHotelDestinoViaje(HotelVO hotel, DestinoVO destino, int idViaje) {
        modeloHotelDestinoViaje = new HotelEnDestinoEnViajeDAO();
        HotelEnDestinoEnViajeVO hotelDestinoViaje = modeloHotelDestinoViaje.encontrar(hotel.getId(), destino.getId(), idViaje);
        if (hotelDestinoViaje.getHabitacionesDisponibles() >= 0) {
            cargarHabitaciones(hotelDestinoViaje.getHabitacionesDisponibles());
        }
    }

    private void cargarHabitaciones(int disponibles) {
        if (vista.tableHoteles.getRowCount() != 0) { //Ya hay habitaciones asignadas
            int habitacionesAsignadas = contarHabitacionesAsignadas();
            try {
                JComboBox combo = new JComboBox();
                combo.removeAllItems();
                combo.addItem("SELECCIONE UNA OPCIÓN");

                for (int i = 0; (i < disponibles && i < (asientosAComprar - habitacionesAsignadas)); i++) {
                    //Agregar a combo habitaciones i+1
                    combo.addItem(i + 1);
                }

                vista.comboNumeroHabitaciones.setModel(combo.getModel());
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {//Cargar hasta habitaciones disponibles o hasta cantidad a comprar
            try {
                JComboBox combo = new JComboBox();
                combo.removeAllItems();
                combo.addItem("SELECCIONE UNA OPCIÓN");

                for (int i = 0; (i < disponibles && i < asientosAComprar); i++) {
                    //Agregar a combo habitaciones i+1
                    combo.addItem(i + 1);
                }

                vista.comboNumeroHabitaciones.setModel(combo.getModel());
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    private int contarHabitacionesAsignadas() {
        int total = 0;
        for (int i = 0; i < vista.tableHoteles.getRowCount(); i++) {
            total += Integer.parseInt(vista.tableHoteles.getValueAt(i, 1).toString());
        }
        return total;
    }

    private void cargarHoras() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            String hora = "";
            for (int i = 0; i < 24; i++) {
                for (int j = 0; j < 60; j += 15) {
                    if (i < 10) {
                        hora = ("0" + i);
                    } else {
                        hora = (i + "");
                    }
                    if (j == 0) {
                        hora += ":00";
                    } else {
                        hora += ":" + j;
                    }
                    combo.addItem(hora);
                    hora = "";
                }
            }
            vista.comboHoraSalida.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void cargarHoteles(DestinoVO destino, int idViaje) {
        modeloHoteles = new HotelDAO();
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            for (HotelVO hotel : modeloHoteles.encontrarByDestinoViaje(destino.getId(), idViaje)) {
                combo.addItem(hotel);
            }
            vista.comboHoteles.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarAsientosDisponibles() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONA UNA OPCIÓN");
            for (int i = 1; i <= asientosDisponibles; i++) {
                combo.addItem(i);
            }
            vista.comboAsientosAComprar.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void cargarClientes() {
        modeloCliente = new ClienteDAO();
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            for (ClienteVO cliente : modeloCliente.encontrarPorTipo(vista.comboTipoCliente.getSelectedItem().toString().toUpperCase())) {
                if (cliente.getId() != 1) {
                    combo.addItem(cliente);
                }
            }
            vista.comboCliente.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarPlantilla() {
        int asientos = Integer.parseInt(vista.comboNoAsientos.getSelectedItem().toString());
        if (asientos == 47) {
            camion64 = false;
            cargarPanel47();
        } else if (asientos == 64) {
            camion64 = true;
            cargarPanel64();
        }
    }

    private void cargarPanel47() {
        panel47 = new Panel47();
        panel47.setSize(257, 670);
        panel47.setLocation(5, 5);
        camion47();
        lblArreglo47();
        cargarAsientos47();

        vista.contenedor.removeAll();
        vista.contenedor.add(panel47, BorderLayout.CENTER);
        vista.contenedor.revalidate();
        vista.contenedor.repaint();
    }

    private void cargarPanel64() {
        panel64 = new Panel64();
        panel64.setSize(480, 670);
        panel64.setLocation(5, 5);
        camion64();
        lblArreglo64();
        cargarAsientos64();
        panel64.addMouseListener(this);

        vista.contenedor.removeAll();
        vista.contenedor.add(panel64, BorderLayout.CENTER);
        vista.contenedor.revalidate();
        vista.contenedor.repaint();
    }

    private void cargarAsientos64() {
        asientosDisponibles = 64;
        asientos = modelo.encontrar(Integer.parseInt(vista.comboId.getSelectedItem().toString()));
        for (int i = 0; i < 64; i++) {
            if (asientos.get(i).isDisponible()) {
                setDisponible(panel64.arreglo[i]);
                panel64.arreglo[i].addMouseListener(this);
            } else {
                setOcupado(panel64.arreglo[i]);
                asientosDisponibles--;
            }
        }

    }

    private void cargarAsientos47() {
        asientosDisponibles = 47;
        asientos = modelo.encontrar(Integer.parseInt(vista.comboId.getSelectedItem().toString()));
        for (int i = 0; i < 47; i++) {
            if (asientos.get(i).isDisponible()) {
                setDisponible(panel47.arreglo[i]);
                panel47.arreglo[i].addMouseListener(this);
            } else {
                setOcupado(panel47.arreglo[i]);
                asientosDisponibles--;
            }
        }

    }

    private void setDisponible(JLabel label) {
        label.setBackground(new Color(51, 255, 51, 160));
        label.setOpaque(true);
    }

    private void setOcupado(JLabel label) {
        label.setBackground(new Color(255, 0, 0, 160));
        label.setOpaque(true);
    }

    private void setApartado(JLabel label) {
        label.setBackground(new Color(247, 255, 0, 160));
        label.setOpaque(true);

    }

    private void camion47() {
        ImageIcon fondo;
        fondo = new ImageIcon(getClass().getResource("/Assets/autobus47.png"));
        Icon fondoIcono = new ImageIcon(fondo.getImage().getScaledInstance(250, 650, Image.SCALE_SMOOTH));
        panel47.lbl_fondo.setIcon(fondoIcono);
    }

    private void camion64() {
        try {
            ImageIcon fondo;
            fondo = new ImageIcon(getClass().getResource("/Assets/autobus64.png"));
            Icon fondoIcono = new ImageIcon(fondo.getImage().getScaledInstance(480, 670, Image.SCALE_SMOOTH));
            panel64.lbl_fondo.setIcon(fondoIcono);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }

    private void lblArreglo64() {
        panel64.arreglo = new JLabel[64];
        panel64.arreglo[0] = panel64.lbl4;
        panel64.arreglo[1] = panel64.lbl5;
        panel64.arreglo[2] = panel64.lbl6;
        panel64.arreglo[3] = panel64.lbl7;
        panel64.arreglo[4] = panel64.lbl8;
        panel64.arreglo[5] = panel64.lbl9;
        panel64.arreglo[6] = panel64.lbl10;
        panel64.arreglo[7] = panel64.lbl11;
        panel64.arreglo[8] = panel64.lbl12;
        panel64.arreglo[9] = panel64.lbl13;
        panel64.arreglo[10] = panel64.lbl14;
        panel64.arreglo[11] = panel64.lbl15;
        panel64.arreglo[12] = panel64.lbl16;
        panel64.arreglo[13] = panel64.lbl17;
        panel64.arreglo[14] = panel64.lbl18;
        panel64.arreglo[15] = panel64.lbl19;
        panel64.arreglo[16] = panel64.lbl20;
        panel64.arreglo[17] = panel64.lbl21;
        panel64.arreglo[18] = panel64.lbl22;
        panel64.arreglo[19] = panel64.lbl23;
        panel64.arreglo[20] = panel64.lbl24;
        panel64.arreglo[21] = panel64.lbl25;
        panel64.arreglo[22] = panel64.lbl26;
        panel64.arreglo[23] = panel64.lbl27;
        panel64.arreglo[24] = panel64.lbl28;
        panel64.arreglo[25] = panel64.lbl29;
        panel64.arreglo[26] = panel64.lbl30;
        panel64.arreglo[27] = panel64.lbl31;
        panel64.arreglo[28] = panel64.lbl32;
        panel64.arreglo[29] = panel64.lbl33;
        panel64.arreglo[30] = panel64.lbl34;
        panel64.arreglo[31] = panel64.lbl35;
        panel64.arreglo[32] = panel64.lbl36;
        panel64.arreglo[33] = panel64.lbl37;
        panel64.arreglo[34] = panel64.lbl38;
        panel64.arreglo[35] = panel64.lbl39;
        panel64.arreglo[36] = panel64.lbl40;
        panel64.arreglo[37] = panel64.lbl41;
        panel64.arreglo[38] = panel64.lbl42;
        panel64.arreglo[39] = panel64.lbl43;
        panel64.arreglo[40] = panel64.lbl44;
        panel64.arreglo[41] = panel64.lbl45;
        panel64.arreglo[42] = panel64.lbl46;
        panel64.arreglo[43] = panel64.lbl47;
        panel64.arreglo[44] = panel64.lbl48;
        panel64.arreglo[45] = panel64.lbl49;
        panel64.arreglo[46] = panel64.lbl50;
        panel64.arreglo[47] = panel64.lbl51;
        panel64.arreglo[48] = panel64.lbl52;
        panel64.arreglo[49] = panel64.lbl53;
        panel64.arreglo[50] = panel64.lbl54;
        panel64.arreglo[51] = panel64.lbl55;
        panel64.arreglo[52] = panel64.lbl56;
        panel64.arreglo[53] = panel64.lbl57;
        panel64.arreglo[54] = panel64.lbl58;
        panel64.arreglo[55] = panel64.lbl59;
        panel64.arreglo[56] = panel64.lbl60;
        panel64.arreglo[57] = panel64.lbl61;
        panel64.arreglo[58] = panel64.lbl62;
        panel64.arreglo[59] = panel64.lbl63;
        panel64.arreglo[60] = panel64.lbl64;
        panel64.arreglo[61] = panel64.lbl65;
        panel64.arreglo[62] = panel64.lbl66;
        panel64.arreglo[63] = panel64.lbl67;
    }

    private void lblArreglo47() {
        panel47.arreglo = new JLabel[47];
        panel47.arreglo[0] = panel47.lbl1;
        panel47.arreglo[1] = panel47.lbl2;
        panel47.arreglo[2] = panel47.lbl3;
        panel47.arreglo[3] = panel47.lbl4;
        panel47.arreglo[4] = panel47.lbl5;
        panel47.arreglo[5] = panel47.lbl6;
        panel47.arreglo[6] = panel47.lbl7;
        panel47.arreglo[7] = panel47.lbl8;
        panel47.arreglo[8] = panel47.lbl9;
        panel47.arreglo[9] = panel47.lbl10;
        panel47.arreglo[10] = panel47.lbl11;
        panel47.arreglo[11] = panel47.lbl12;
        panel47.arreglo[12] = panel47.lbl13;
        panel47.arreglo[13] = panel47.lbl14;
        panel47.arreglo[14] = panel47.lbl15;
        panel47.arreglo[15] = panel47.lbl16;
        panel47.arreglo[16] = panel47.lbl17;
        panel47.arreglo[17] = panel47.lbl18;
        panel47.arreglo[18] = panel47.lbl19;
        panel47.arreglo[19] = panel47.lbl20;
        panel47.arreglo[20] = panel47.lbl21;
        panel47.arreglo[21] = panel47.lbl22;
        panel47.arreglo[22] = panel47.lbl23;
        panel47.arreglo[23] = panel47.lbl24;
        panel47.arreglo[24] = panel47.lbl25;
        panel47.arreglo[25] = panel47.lbl26;
        panel47.arreglo[26] = panel47.lbl27;
        panel47.arreglo[27] = panel47.lbl28;
        panel47.arreglo[28] = panel47.lbl29;
        panel47.arreglo[29] = panel47.lbl30;
        panel47.arreglo[30] = panel47.lbl31;
        panel47.arreglo[31] = panel47.lbl32;
        panel47.arreglo[32] = panel47.lbl33;
        panel47.arreglo[33] = panel47.lbl34;
        panel47.arreglo[34] = panel47.lbl35;
        panel47.arreglo[35] = panel47.lbl36;
        panel47.arreglo[36] = panel47.lbl37;
        panel47.arreglo[37] = panel47.lbl38;
        panel47.arreglo[38] = panel47.lbl39;
        panel47.arreglo[39] = panel47.lbl40;
        panel47.arreglo[40] = panel47.lbl41;
        panel47.arreglo[41] = panel47.lbl42;
        panel47.arreglo[42] = panel47.lbl43;
        panel47.arreglo[43] = panel47.lbl44;
        panel47.arreglo[44] = panel47.lbl45;
        panel47.arreglo[45] = panel47.lbl46;
        panel47.arreglo[46] = panel47.lbl47;

    }

    private void iniciarCombosVacios() {
        JComboBox combo = new JComboBox();
        combo.removeAllItems();
        combo.addItem("SELECCIONE UNA OPCIÓN");
        vista.comboFecha.setModel(combo.getModel());
        vista.comboNoAsientos.setModel(combo.getModel());
        vista.comboId.setModel(combo.getModel());
        vista.comboTipoCliente.setModel(combo.getModel());
        vista.comboCliente.setModel(combo.getModel());
        vista.comboAsientosAComprar.setModel(combo.getModel());
        vista.comboHoteles.setModel(combo.getModel());
        vista.comboNumeroHabitaciones.setModel(combo.getModel());
        vista.dateFechaRegreso.setLimpiarFecha(true);
        cargarDestinos();
        cargarTiposDeCliente();
    }

    /* private void cargarTabla() {
        TablaViajes tabla = new TablaViajes();
        tabla.cargarTabla(vista.tableViajes, modelo.listar());
    }*/
    private void cargarAsientos() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");

            modeloViajes = new ViajeDAO();
            DestinoVO destino = (DestinoVO) vista.comboDestino.getSelectedItem();
            viajesEnDestino = modeloViajes.encontrarByDestinoDate(destino.getId(), vista.comboFecha.getSelectedItem().toString());

            int actual = 0;
            int anterior = 0;

            combo.addItem(viajesEnDestino.get(0).getNoAsientos());

            for (int i = 1; i < viajesEnDestino.size(); i++) {
                actual = viajesEnDestino.get(i).getNoAsientos();
                anterior = viajesEnDestino.get(i - 1).getNoAsientos();
                if (actual != anterior) {
                    combo.addItem(actual);
                }
            }
            vista.comboNoAsientos.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarIdsViajes() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");

            modeloViajes = new ViajeDAO();
            DestinoVO destino = (DestinoVO) vista.comboDestino.getSelectedItem();
            viajesEnDestino = modeloViajes.encontrarByDestinoDateAsientos(destino.getId(), vista.comboFecha.getSelectedItem().toString(), Integer.parseInt(vista.comboNoAsientos.getSelectedItem().toString()));

            for (ViajeVO viaje : viajesEnDestino) {
                combo.addItem(viaje.getId());
            }

            vista.comboId.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarTiposDeCliente() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            combo.addItem("AGENCIA");
            combo.addItem("CLIENTE");
            vista.comboTipoCliente.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarFechas() {
        modeloViajes = new ViajeDAO();
        DestinoVO destino = (DestinoVO) vista.comboDestino.getSelectedItem();
        viajesEnDestino = modeloViajes.encontrarByDestino(destino.getId());
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");

            String actual = "";
            String anterior = "";
            combo.addItem(viajesEnDestino.get(0).getFecha());
            for (int i = 1; i < viajesEnDestino.size(); i++) {
                actual = viajesEnDestino.get(i).getFecha();
                anterior = viajesEnDestino.get(i - 1).getFecha();
                if (!actual.equals(anterior)) {
                    combo.addItem(viajesEnDestino.get(i).getFecha());
                }
            }
            vista.comboFecha.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private int deseaComprar() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Seguro que desea vender los boletos? ", "Vender", dialog));
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
        vista.comboDestino.setSelectedIndex(0);
        vista.comboAsientosAComprar.setSelectedIndex(0);
        vista.comboCliente.setSelectedIndex(0);
        vista.comboFecha.setSelectedIndex(0);
        vista.comboId.setSelectedIndex(0);
        vista.comboNoAsientos.setSelectedIndex(0);
        vista.comboTipoCliente.setSelectedIndex(0);
        vista.comboTipoViaje.setSelectedIndex(0);
        vista.comboFormaPago.setSelectedIndex(0);
        vista.comboHoraSalida.setSelectedIndex(0);
        vista.comboHoraRegreso.setSelectedIndex(0);
        vista.comboHoteles.setSelectedIndex(0);
        vista.comboNumeroHabitaciones.setSelectedIndex(0);
        limpiarTabla();
        vista.txtCosto.setText("");
        vista.txtAnticipo.setText("");
        vista.dateFechaRegreso.setLimpiarFecha(true);
        vista.txtSube.setText("");
        iniciarCombosVacios();

    }

    private void registrarViajeRedondo() {
        modelo = new AsientoDAO();
        modeloDetalle = new DetalleDAO();
        modeloDetalleHotelDestinoViaje = new DetalleHotelDestinoViajeDAO();
        ArrayList<AsientoVO> anteriores = modelo.encontrar(Integer.parseInt(vista.comboId.getSelectedItem().toString()));
        AsientoVO asiento;
        ClienteVO cliente = (ClienteVO) vista.comboCliente.getSelectedItem();
        int idViaje = Integer.parseInt(vista.comboId.getSelectedItem().toString());
        int satisfactorios = 0;
        //Registra los asientos al cliente x
        for (int i = 0; i < anteriores.size(); i++) {
            asiento = asientos.get(i);
            if (anteriores.get(i).isDisponible() != asiento.isDisponible()) {
                asiento.setIdCliente(cliente.getId());
                if (modelo.actualizar(asiento) == 0) {
                    JOptionPane.showMessageDialog(vista, "Ocurrió un error al comprar los asientos");
                    return;
                } else {
                    satisfactorios++;
                }
            }
        }
        //Registro del detalle
        String sube = vista.txtSube.getText();
        String hora = vista.comboHoraSalida.getSelectedItem().toString();
        Double costo = Double.parseDouble(vista.txtCosto.getText());
        Double anticipo = 0d;

        if (!this.vista.txtAnticipo.equals("")) {
            try {
                anticipo = Double.parseDouble(this.vista.txtAnticipo.getText());
            } catch (Exception e) {
            }
        }

        int habitaciones = calcularHabitacionesVendidas();

        String formaPago = this.vista.comboFormaPago.getSelectedItem().toString();
        String viaje = "REDONDO";
        String horaRegreso = vista.comboHoraRegreso.getSelectedItem().toString();
        String fechaRegreso = vista.dateFechaRegreso.getFechaSeleccionada();

        DetalleVO detalle = new DetalleVO(idViaje, cliente.getId(), usuario.getId(), satisfactorios, sube, hora, habitaciones, costo, anticipo, true, "VENDIDO", formaPago, viaje, horaRegreso, fechaRegreso, fechaActual);
        if (modeloDetalle.insertar(detalle) == 0) {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al registrar el detalle");
            return;
        } else {
            //Buscar detalle registrado
            crearDetalleCompraDeHabitaciones(modeloDetalle.encontrarUltimo().getId());
        }

    }

    private int calcularHabitacionesVendidas() {
        int habitaciones = 0;
        for (int i = 0; i < vista.tableHoteles.getRowCount(); i++) {
            habitaciones += Integer.parseInt(vista.tableHoteles.getValueAt(i, 1).toString());
            //Habitaciones en la tabla es la posicion 1, la dos es el id del hotel
        }
        return habitaciones;
    }

    private void registrarViajeSencillo() {
        modelo = new AsientoDAO();
        modeloDetalle = new DetalleDAO();
        ArrayList<AsientoVO> anteriores = modelo.encontrar(Integer.parseInt(vista.comboId.getSelectedItem().toString()));
        AsientoVO asiento;
        ClienteVO cliente = (ClienteVO) vista.comboCliente.getSelectedItem();
        int idViaje = Integer.parseInt(vista.comboId.getSelectedItem().toString());
        int satisfactorios = 0;
        for (int i = 0; i < anteriores.size(); i++) {
            asiento = asientos.get(i);
            if (anteriores.get(i).isDisponible() != asiento.isDisponible()) {
                asiento.setIdCliente(cliente.getId());
                if (modelo.actualizar(asiento) == 0) {
                    JOptionPane.showMessageDialog(vista, "Ocurrió un error al comprar los asientos");
                    break;
                } else {
                    satisfactorios++;
                }
            }
        }
        String sube = vista.txtSube.getText();
        String hora = vista.comboHoraSalida.getSelectedItem().toString();
        Double costo = Double.parseDouble(vista.txtCosto.getText());
        int habitaciones = 0;
        String formaPago = this.vista.comboFormaPago.getSelectedItem().toString();
        String viaje = "SENCILLO";
        String horaRegreso = "00:00";
        Double anticipo = 0d;

        if (!this.vista.txtAnticipo.equals("")) {
            try {
                anticipo = Double.parseDouble(this.vista.txtAnticipo.getText());
            } catch (Exception e) {
            }
        }

        DetalleVO detalle = new DetalleVO(idViaje, cliente.getId(), usuario.getId(), satisfactorios, sube, hora, habitaciones, costo, anticipo, true, "VENDIDO", formaPago, viaje, null, null, fechaActual);
        if (modeloDetalle.insertar(detalle) == 0) {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al registrar el detalle");
        }

    }

    private boolean datosValidos() {
        if (vista.comboDestino.getSelectedIndex() == 0) {
            return false;
        }
        if (vista.comboFecha.getSelectedIndex() == 0) {
            return false;
        }
        if (vista.comboNoAsientos.getSelectedIndex() == 0) {
            return false;
        }
        if (vista.comboId.getSelectedIndex() == 0) {
            return false;
        }
        if (vista.comboTipoCliente.getSelectedIndex() == 0) {
            return false;
        }
        if (vista.comboCliente.getSelectedIndex() == 0) {
            return false;
        }
        if (vista.comboAsientosAComprar.getSelectedIndex() == 0) {
            return false;
        }
        if (vista.txtSube.getText().length() == 0) {
            return false;
        }
        if (vista.txtCosto.getText().length() == 0) {
            return false;
        }
        if (asientosAComprar == Integer.parseInt(vista.comboAsientosAComprar.getSelectedItem().toString())) {
            return false;
        }
        if (!costoValido()) {
            return false;
        }
        if (!horaValida()) {
            return false;
        }
        if (vista.comboTipoViaje.getSelectedIndex() == 1) { ///Si va a ser un viaje redondo se debe seleccionar popr lo menos una habitación y la hora de regreso debe ser válida
            if (!fechaRegresoValida()) {
                return false;
            }
            if (!horaDeRegresoValida()) {
                return false;
            }
        }
        return true;
    }

    private boolean costoValido() {
        try {
            Double costo = Double.parseDouble(vista.txtCosto.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean horaValida() {
        if (this.vista.comboHoraSalida.getSelectedIndex() == 0) {
            return false;
        }
        return true;
    }

    private boolean horaDeRegresoValida() {
        if (vista.comboHoraRegreso.getSelectedIndex() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        /* if (me.getSource() == vista.tableViajes) {
            try {
                rellenarFormulario();
                habilitarBotones(true);
            } catch (ParseException ex) {
                ex.printStackTrace(System.out);
            }
        } else */
        if (me.getSource() == vista.lbl_back) {
            abrirMenu();
        } else if (camion64) {
            for (int i = 0; i < 64; i++) {
                if (me.getSource() == panel64.arreglo[i]) {
                    if (asientos.get(i).isDisponible()) {
                        if (asientosAComprar > 0) {
                            asientos.get(i).setDisponible(false);
                            asientosAComprar--;
                            setApartado(panel64.arreglo[i]);
                        }
                    } else {
                        asientos.get(i).setDisponible(true);
                        asientosAComprar++;
                        setDisponible(panel64.arreglo[i]);
                    }
                    camion64();
                }
            }
        } else if (!camion64) {
            for (int i = 0; i < 47; i++) {
                if (me.getSource() == panel47.arreglo[i]) {
                    if (asientos.get(i).isDisponible()) {
                        if (asientosAComprar > 0) {
                            asientos.get(i).setDisponible(false);
                            asientosAComprar--;
                            setApartado(panel47.arreglo[i]);
                        }
                    } else {
                        asientos.get(i).setDisponible(true);
                        asientosAComprar++;
                        setDisponible(panel47.arreglo[i]);
                    }
                    camion47();
                }
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
        if (ke.getSource() == vista.btnComprar && ke.getKeyCode() == KeyEvent.VK_ENTER) {
            if (datosValidos() && deseaComprar() == 0) {
                if (vista.comboTipoViaje.getSelectedIndex() == 1) {
                    registrarViajeRedondo();
                } else {
                    registrarViajeSencillo();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getSource() == vista.txtSube) {
            vista.txtSube.setText(vista.txtSube.getText().toUpperCase());
        }
    }

    private void limpiarTabla() {
        TablaHoteles tabla = new TablaHoteles();
        tabla.cargarTablaVacia(vista.tableHoteles);
    }

    private void crearDetalleCompraDeHabitaciones(Long detalleUltimo) {
        int idHotelDestinoViaje = 0;
        int habitaciones = 0;
        DetalleHotelDestinoViajeVO temporal = null;
        modeloDetalleHotelDestinoViaje = new DetalleHotelDestinoViajeDAO();

        for (int i = 0; i < vista.tableHoteles.getRowCount(); i++) {
            idHotelDestinoViaje = Integer.parseInt(vista.tableHoteles.getValueAt(i, 2).toString());
            habitaciones = Integer.parseInt(vista.tableHoteles.getValueAt(i, 1).toString());
            temporal = new DetalleHotelDestinoViajeVO(detalleUltimo, idHotelDestinoViaje, habitaciones);
            if (modeloDetalleHotelDestinoViaje.insertar(temporal) > 0) {
                //Actualizar datos de HotelDestinoViaje
                actualizarDatosHotelDestinoViaje(idHotelDestinoViaje, habitaciones);
            }
        }
    }

    private void actualizarDatosHotelDestinoViaje(int idHotelDestinoViaje, int habitaciones) {
        modeloHotelDestinoViaje = new HotelEnDestinoEnViajeDAO();
        HotelEnDestinoEnViajeVO temporal = modeloHotelDestinoViaje.encontrar(idHotelDestinoViaje);
        temporal.setHabitacionesDisponibles(temporal.getHabitacionesDisponibles() - habitaciones);
        if (modeloHotelDestinoViaje.actualizar(temporal) > 0) {
            System.out.println("se actualizo el hotel " + temporal.getId() + " a " + temporal.getHabitacionesDisponibles() + " habitaciones");
        } else {
            System.out.println("No se rebajó el número de habitaciones al hotal");
        }
    }

    private boolean fechaRegresoValida() {
        return !this.vista.dateFechaRegreso.getFechaSeleccionada().equals("");
    }

    private String dateToString(Long date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    private int deseaVerTicket() {
        int dialog = JOptionPane.YES_NO_OPTION;
        return (JOptionPane.showConfirmDialog(null, "¿Desea ver el ticket? ", "Ticket", dialog));
    }

    private void mostrarTicket() {
        ViajeVO viaje = modeloViajes.encontrar(Integer.parseInt(vista.comboId.getSelectedItem().toString()));
        ClienteVO cliente = (ClienteVO) vista.comboCliente.getSelectedItem();
        DestinoVO destino = (DestinoVO) vista.comboDestino.getSelectedItem();
        EstadoVO estado = modeloEstados.encontrarPorId(destino.getIdEstado());
        DetalleVO detalle = modeloDetalle.encontrar(viaje.getId(), cliente.getId(), usuario.getId());

        GenerarReporte.reporteTicket(cliente.getId(), viaje.getId(), cliente.getNombre(), destino.getCiudad(), estado.getNombre(), viaje.getFecha(), detalle.getId());
    }

}
