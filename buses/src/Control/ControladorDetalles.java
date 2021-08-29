/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ClassDAO.ClienteDAO;
import ClassDAO.DestinoDAO;
import ClassDAO.EstadoDAO;
import ClassDAO.ViajeDAO;
import ClassVO.ClienteVO;
import ClassVO.DestinoVO;
import ClassVO.EstadoVO;
import ClassVO.PaisVO;
import ClassVO.UsuarioVO;
import ClassVO.ViajeVO;
import Reports.GenerarReporte;
import Vista.Detalles;
import Vista.DetallesAsientos;
import Vista.DetallesTickets;
import Vista.MenuAdmin;
import Vista.MenuCoordinador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author alanm
 */
public class ControladorDetalles implements ActionListener, MouseListener {
//Estado, ciudad, fecha, viaje son iguales para ambas pantallas

    private UsuarioVO usuario;
    private Detalles menu;
    private DetallesTickets tickets;
    private DetallesAsientos asientos;
    private EstadoDAO modeloEstados;
    private DestinoDAO modeloDestinos;
    private ViajeDAO modeloViajes;
    private ClienteDAO modeloClientes;

    public ControladorDetalles(Detalles menu, UsuarioVO usuario, DetallesAsientos asientos, DetallesTickets tickets) {
        this.usuario = usuario;
        this.menu = menu;
        this.asientos = asientos;
        this.tickets = tickets;

        modeloEstados = new EstadoDAO();
        modeloDestinos = new DestinoDAO();
        modeloViajes = new ViajeDAO();
        modeloClientes = new ClienteDAO();

        this.menu.btnTickets.addActionListener(this);
        this.menu.btnAsientos.addActionListener(this);
        this.menu.lbl_back.addMouseListener(this);

        this.tickets.btnConsultar.addActionListener(this);
        this.tickets.comboEstado.addActionListener(this);
        this.tickets.comboCiudad.addActionListener(this);
        this.tickets.comboCliente.addActionListener(this);
        this.tickets.comboFecha.addActionListener(this);
        this.tickets.comboViaje.addActionListener(this);
        this.tickets.lbl_back.addMouseListener(this);

        this.asientos.btnConsultar.addActionListener(this);
        this.asientos.comboCiudad.addActionListener(this);
        this.asientos.comboEstado.addActionListener(this);
        this.asientos.comboFecha.addActionListener(this);
        this.asientos.comboViaje.addActionListener(this);
        this.asientos.lbl_back.addMouseListener(this);
    }

    public void iniciar() {
        menu.setTitle("Detalles");
        tickets.setTitle("Tickets");
        asientos.setTitle("Detalles");
        menu.setLocationRelativeTo(null);
        tickets.setLocationRelativeTo(null);
        asientos.setLocationRelativeTo(null);
        menu.setVisible(true);

        menu.btnTickets.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.btnTickets) {
            cargarEstados();
            abrirTickets();
        } else if (e.getSource() == menu.btnAsientos) {
            abrirAasientos();
        } else if (e.getSource() == tickets.comboEstado) {
            if (tickets.comboEstado.getSelectedIndex() != 0) {
                cargarDestinos();
            }
        } else if (e.getSource() == tickets.comboCiudad) {
            if (tickets.comboCiudad.getSelectedIndex() != 0) {
                cargarFechas();
            }
        } else if (e.getSource() == tickets.comboFecha) {
            if (tickets.comboFecha.getSelectedIndex() != 0) {
                cargarViajes();
            }
        } else if (e.getSource() == tickets.comboViaje) {
            if (tickets.comboViaje.getSelectedIndex() != 0) {
                cargarClientes();
            }
        } else if (e.getSource() == tickets.btnConsultar) {
            if (datosValidos()) {
                mostrarTickets();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == menu.lbl_back) {
            abrirMenu();
        } else if (e.getSource() == tickets.lbl_back) {
            abrirDetalles("tickets");
        } else if (e.getSource() == asientos.lbl_back) {
            abrirDetalles("asientos");
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

    private void abrirMenu() {
        if (usuario.getTipo().equals("Administrador")) {
            ControladorMenuAdmin controladorMenuAdmin = new ControladorMenuAdmin(new MenuAdmin(), usuario);
            controladorMenuAdmin.iniciar();
        } else {
            ControladorMenuCoordinador controladorMenuCoordinador = new ControladorMenuCoordinador(new MenuCoordinador(), usuario);
            controladorMenuCoordinador.iniciar();
        }
        menu.dispose();
    }

    private void abrirTickets() {
        menu.dispose();
        tickets.setVisible(true);
        tickets.comboEstado.requestFocus();
    }

    private void abrirAasientos() {
        menu.dispose();
        asientos.setVisible(true);
        asientos.comboEstado.requestFocus();
    }

    private void abrirDetalles(String fuente) {
        if (fuente.equals("tickets")) {
            tickets.dispose();
        } else {
            asientos.dispose();
        }
        menu.setVisible(true);
    }

    private void cargarEstados() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            for (EstadoVO estado : modeloEstados.encontrar(42)) {//El pais con el id 42 es méxico
                combo.addItem(estado);
            }
            tickets.comboEstado.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarDestinos() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            EstadoVO estado = (EstadoVO) tickets.comboEstado.getSelectedItem();
            for (DestinoVO destino : modeloDestinos.encontrarEstado(estado.getId())) {
                combo.addItem(destino);
            }
            tickets.comboCiudad.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarFechas() {
        DestinoVO destino = (DestinoVO) tickets.comboCiudad.getSelectedItem();
        ArrayList<ViajeVO> viajesEnDestino = modeloViajes.encontrarByDestino(destino.getId());
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
            tickets.comboFecha.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarViajes() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");

            DestinoVO destino = (DestinoVO) tickets.comboCiudad.getSelectedItem();
            ArrayList<ViajeVO> viajesEnDestino = modeloViajes.encontrarByDestinoDate(destino.getId(), tickets.comboFecha.getSelectedItem().toString());

            for (ViajeVO viaje : viajesEnDestino) {
                combo.addItem(viaje);
            }

            tickets.comboViaje.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarClientes() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");

            ViajeVO viaje = (ViajeVO) tickets.comboViaje.getSelectedItem();

            for (ClienteVO cliente : modeloClientes.encontrarViaje(viaje.getId())) {
                combo.addItem(cliente);
            }

            tickets.comboCliente.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private boolean datosValidos() {
        if (tickets.comboCiudad.getSelectedIndex() == 0) {
            return false;
        }
        if (tickets.comboCliente.getSelectedIndex() == 0) {
            return false;
        }
        if (tickets.comboEstado.getSelectedIndex() == 0) {
            return false;
        }
        if (tickets.comboFecha.getSelectedIndex() == 0) {
            return false;
        }
        if (tickets.comboViaje.getSelectedIndex() == 0) {
            return false;
        }
        return true;
    }

    private void mostrarTickets() {
        ViajeVO viaje = (ViajeVO) tickets.comboViaje.getSelectedItem();
        ClienteVO cliente = (ClienteVO) tickets.comboCliente.getSelectedItem();
        EstadoVO estado = (EstadoVO) tickets.comboEstado.getSelectedItem();
        DestinoVO destino = (DestinoVO) tickets.comboCiudad.getSelectedItem();
 
        GenerarReporte.reporteTicket(cliente.getId(), viaje.getId(), cliente.getNombre(), destino.getCiudad(), estado.getNombre(), viaje.getFecha());
    }
}
