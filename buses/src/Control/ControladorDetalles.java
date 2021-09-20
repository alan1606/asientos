/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ClassDAO.ClienteDAO;
import ClassDAO.DestinoDAO;
import ClassDAO.DetalleDAO;
import ClassDAO.EstadoDAO;
import ClassDAO.ViajeDAO;
import ClassVO.ClienteVO;
import ClassVO.DestinoVO;
import ClassVO.DetalleVO;
import ClassVO.EstadoVO;

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
    private DetalleDAO modeloDetalles;

    public ControladorDetalles(Detalles menu, UsuarioVO usuario, DetallesAsientos asientos, DetallesTickets tickets) {
        this.usuario = usuario;
        this.menu = menu;
        this.asientos = asientos;
        this.tickets = tickets;

        modeloEstados = new EstadoDAO();
        modeloDestinos = new DestinoDAO();
        modeloViajes = new ViajeDAO();
        modeloClientes = new ClienteDAO();
        modeloDetalles = new DetalleDAO();

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
        this.tickets.comboDetalles.addActionListener(this);

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
        try {
            cargarComboVacio(tickets.comboDetalles);
            cargarComboVacio(tickets.comboViaje);
            cargarComboVacio(tickets.comboFecha);
            cargarComboVacio(tickets.comboCiudad);
            cargarComboVacio(tickets.comboCliente);
        } catch (Exception e) {
        }
        
        try {
            cargarComboVacio(asientos.comboViaje);
            cargarComboVacio(asientos.comboFecha);
            cargarComboVacio(asientos.comboCiudad);
        } catch (Exception e) {
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.btnTickets) {
            cargarEstados();
            abrirTickets();
        } else if (e.getSource() == menu.btnAsientos) {
            cargarEstadosAsientos();
            abrirAasientos();
        } else if (e.getSource() == tickets.comboEstado) {
            if (tickets.comboEstado.getSelectedIndex() != 0) {
                cargarDestinos();
                try {
                    reiniciarCombo(tickets.comboDetalles);
                    reiniciarCombo(tickets.comboViaje);
                    reiniciarCombo(tickets.comboFecha);
                    reiniciarCombo(tickets.comboCliente);
                } catch (Exception ex) {
                }
            } else {
                try {
                    reiniciarCombo(tickets.comboCiudad);
                    reiniciarCombo(tickets.comboDetalles);
                    reiniciarCombo(tickets.comboViaje);
                    reiniciarCombo(tickets.comboFecha);
                    reiniciarCombo(tickets.comboCliente);
                } catch (Exception ex) {
                }
            }
        } else if (e.getSource() == tickets.comboCiudad) {
            if (tickets.comboCiudad.getSelectedIndex() != 0) {
                cargarFechas();
                try {
                    reiniciarCombo(tickets.comboDetalles);
                    reiniciarCombo(tickets.comboViaje);
                    reiniciarCombo(tickets.comboCliente);
                } catch (Exception ex) {
                }
            } else {
                try {
                    reiniciarCombo(tickets.comboDetalles);
                    reiniciarCombo(tickets.comboViaje);
                    reiniciarCombo(tickets.comboFecha);
                    reiniciarCombo(tickets.comboCliente);
                } catch (Exception ex) {
                }
            }
        } else if (e.getSource() == tickets.comboFecha) {
            if (tickets.comboFecha.getSelectedIndex() != 0) {
                cargarViajes();
                try {
                    reiniciarCombo(tickets.comboDetalles);
                    reiniciarCombo(tickets.comboCliente);
                } catch (Exception ex) {
                }
            } else {
                try {
                    reiniciarCombo(tickets.comboDetalles);
                    reiniciarCombo(tickets.comboViaje);
                    reiniciarCombo(tickets.comboCliente);
                } catch (Exception ex) {
                }
            }
        } else if (e.getSource() == tickets.comboViaje) {
            if (tickets.comboViaje.getSelectedIndex() != 0) {
                cargarClientes();
                try {
                    reiniciarCombo(tickets.comboDetalles);
                } catch (Exception ex) {
                }
            } else {
                try {
                    reiniciarCombo(tickets.comboDetalles);
                    reiniciarCombo(tickets.comboCliente);
                } catch (Exception ex) {
                }
            }
        } else if (e.getSource() == tickets.btnConsultar) {
            if (datosValidos()) {
                mostrarTickets();
            }
        } else if (e.getSource() == tickets.comboCliente) {
            cargarTickets();
        } else if (e.getSource() == asientos.comboEstado) {
            if (asientos.comboEstado.getSelectedIndex() != 0) {
                cargarDestinosAsientos();
                try {
                    reiniciarCombo(asientos.comboViaje);
                    reiniciarCombo(asientos.comboFecha);
                } catch (Exception ex) {
                }
            } else {
                try {
                    reiniciarCombo(asientos.comboCiudad);
                    reiniciarCombo(asientos.comboViaje);
                    reiniciarCombo(asientos.comboFecha);
                } catch (Exception ex) {
                }
            }
        } else if (e.getSource() == asientos.comboCiudad) {
            if (asientos.comboCiudad.getSelectedIndex() != 0) {
                cargarFechasAsientos();
                try {
                    reiniciarCombo(asientos.comboViaje);
                } catch (Exception ex) {
                }
            } else {
                try {
                    reiniciarCombo(asientos.comboFecha);
                    reiniciarCombo(asientos.comboViaje);
                } catch (Exception ex) {
                }
            }
        } else if (e.getSource() == asientos.comboFecha) {
            if (asientos.comboFecha.getSelectedIndex() != 0) {
                cargarViajesAsientos();
            } else {
                try {
                    reiniciarCombo(tickets.comboViaje);
                } catch (Exception ex) {
                }
            }
        } else if (e.getSource() == asientos.btnConsultar) {
            if (datosValidosAsientos()) {
                mostrarAsientos();
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
        if (tickets.comboDetalles.getSelectedIndex() == 0) {
            return false;
        }
        return true;
    }

    private void mostrarTickets() {
        ViajeVO viaje = (ViajeVO) tickets.comboViaje.getSelectedItem();
        ClienteVO cliente = (ClienteVO) tickets.comboCliente.getSelectedItem();
        EstadoVO estado = (EstadoVO) tickets.comboEstado.getSelectedItem();
        DestinoVO destino = (DestinoVO) tickets.comboCiudad.getSelectedItem();
        DetalleVO detalle = (DetalleVO) tickets.comboDetalles.getSelectedItem();

        GenerarReporte.reporteTicket(cliente.getId(), viaje.getId(), cliente.getNombre(), destino.getCiudad(), estado.getNombre(), viaje.getFecha(), detalle.getId());
    }

    private void cargarTickets() {

        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");

            ClienteVO cliente = (ClienteVO) tickets.comboCliente.getSelectedItem();

            ViajeVO viaje = (ViajeVO) tickets.comboViaje.getSelectedItem();

            for (DetalleVO detalle : modeloDetalles.encontrar(viaje.getId(), cliente.getId())) {
                combo.addItem(detalle);
            }

            tickets.comboDetalles.setModel(combo.getModel());
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

    private void cargarEstadosAsientos() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            for (EstadoVO estado : modeloEstados.encontrar(42)) {//El pais con el id 42 es méxico
                combo.addItem(estado);
            }
            asientos.comboEstado.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarDestinosAsientos() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");
            EstadoVO estado = (EstadoVO) asientos.comboEstado.getSelectedItem();
            for (DestinoVO destino : modeloDestinos.encontrarEstado(estado.getId())) {
                combo.addItem(destino);
            }
            asientos.comboCiudad.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarFechasAsientos() {
        DestinoVO destino = (DestinoVO) asientos.comboCiudad.getSelectedItem();
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
            asientos.comboFecha.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargarViajesAsientos() {
        try {
            JComboBox combo = new JComboBox();
            combo.removeAllItems();
            combo.addItem("SELECCIONE UNA OPCIÓN");

            DestinoVO destino = (DestinoVO) asientos.comboCiudad.getSelectedItem();
            ArrayList<ViajeVO> viajesEnDestino = modeloViajes.encontrarByDestinoDate(destino.getId(), asientos.comboFecha.getSelectedItem().toString());

            for (ViajeVO viaje : viajesEnDestino) {
                combo.addItem(viaje);
            }

            asientos.comboViaje.setModel(combo.getModel());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private boolean datosValidosAsientos() {
        if (asientos.comboEstado.getSelectedIndex() == 0) {
            return false;
        }
        if (asientos.comboCiudad.getSelectedIndex() == 0) {
            return false;
        }
        if (asientos.comboFecha.getSelectedIndex() == 0) {
            return false;
        }
        if (asientos.comboViaje.getSelectedIndex() == 0) {
            return false;
        }
        return true;
    }

    private void mostrarAsientos() {
        ViajeVO viaje = (ViajeVO) asientos.comboViaje.getSelectedItem();
        DestinoVO destino = (DestinoVO) asientos.comboCiudad.getSelectedItem();

        GenerarReporte.reporteViaje(destino.getCiudad(), viaje.getFecha(), viaje.getId());
    }

    private void reiniciarCombo(JComboBox combo) {
        combo.setSelectedIndex(0);
    }
}
