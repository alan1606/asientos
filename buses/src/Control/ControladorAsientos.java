/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import ClassDAO.AsientoDAO;
import ClassDAO.ClienteDAO;
import ClassDAO.DestinoDAO;
import ClassDAO.ViajeDAO;
import ClassVO.AsientoVO;
import ClassVO.DestinoVO;
import ClassVO.UsuarioVO;
import ClassVO.ViajeVO;
import Paneles.Panel47;
import Paneles.Panel64;
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
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author alanm
 */
public class ControladorAsientos implements ActionListener, KeyListener, MouseListener {

    private Asientos vista;
    private ViajeDAO modeloViajes;
    private AsientoDAO modelo;
    private UsuarioVO usuario;
    private DestinoDAO modeloDestino;
    private ClienteDAO modeloCliente;
    private ArrayList<ViajeVO> viajesEnDestino;
    private int asientosSeleccionados = 0;
    private int asientosAComprar = 0;
    private Panel47 panel47;
    private Panel64 panel64;
    private boolean camion64 = false;

    public ControladorAsientos(Asientos vista, UsuarioVO usuario) {
        this.vista = vista;
        this.usuario = usuario;

        modelo = new AsientoDAO();

        this.vista.btnComprar.addActionListener(this);
        this.vista.comboDestino.addActionListener(this);
        this.vista.comboFecha.addActionListener(this);
        this.vista.comboNoAsientos.addActionListener(this);
        this.vista.comboId.addActionListener(this);
        this.vista.comboTipoCliente.addActionListener(this);
        this.vista.comboCliente.addActionListener(this);
        this.vista.comboAsientosAComprar.addActionListener(this);
        this.vista.lbl_back.addMouseListener(this);

        //Se agrega un action listener por cada objeto
    }

    public void iniciar() {
        vista.setTitle("Venta de boletos");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        iniciarCombosVacios();

        cargarDestinos();

        cargarTiposDeCliente();
        //cargarBusquedaDestinos();
        habilitarBotones(false);
        // cargarTabla();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.btnComprar) {
            if (datosValidos() && deseaComprar() == 0) {
                try {
                    registrar();
                    habilitarBotones(false);
                } catch (ParseException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        } else if (ae.getSource() == vista.comboDestino) {
            if (vista.comboDestino.getSelectedIndex() != 0) {
                cargarFechas();
            }
            try {
                vista.comboFecha.setSelectedIndex(0);
                vista.comboNoAsientos.setSelectedIndex(0);
                vista.comboId.setSelectedIndex(0);
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
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }

        } else if (ae.getSource() == vista.comboNoAsientos) {
            if (vista.comboNoAsientos.getSelectedIndex() != 0) {
                cargarIdsViajes();

            } else {
                try {
                    vista.comboId.setSelectedIndex(0);
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
            }

        } else if (ae.getSource() == vista.comboId) {
            if (vista.comboId.getSelectedIndex() != 0) {
                cargarPlantilla();
            }
        } else if (ae.getSource() == vista.comboTipoCliente) {
            if (vista.comboTipoCliente.getSelectedIndex() != 0) {
                // cargarClientes();
            }
        } else if (ae.getSource() == vista.comboAsientosAComprar) {
            /* if (vista.comboDestinoSearch.getSelectedIndex() != 0) {
                cargarNoAsientosDisponibles();
            }*/
        }
    }

    private void cargarPlantilla() {
        int asientos = Integer.parseInt(vista.comboNoAsientos.getSelectedItem().toString());
        if (asientos == 47) {
            camion64 = false;
            cargarPanel47();

        } else {
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
        ArrayList<AsientoVO> asientos = modelo.encontrar(Integer.parseInt(vista.comboId.getSelectedItem().toString()));
        for (int i = 0; i < 64; i++) {
            if (asientos.get(i).isDisponible()) {
                setDisponible(panel64.arreglo[i]);
                panel64.arreglo[i].addMouseListener(this);
            } else {
                setOcupado(panel64.arreglo[i]);
            }
        }

    }

    private void cargarAsientos47() {
        ArrayList<AsientoVO> asientos = modelo.encontrar(Integer.parseInt(vista.comboId.getSelectedItem().toString()));
        for (int i = 0; i < 47; i++) {
            if (asientos.get(i).isDisponible()) {
                setDisponible(panel47.arreglo[i]);
                panel47.arreglo[i].addMouseListener(this);
            } else {
                setOcupado(panel47.arreglo[i]);
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
                System.out.println("actual: " + actual);
                System.out.println("anterior: " + anterior);
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
        vista.txtCosto.setText("");
        vista.txtHabitaciones.setText("");
        vista.txtHora.setText("");
        vista.txtSube.setText("");
    }

    private void registrar() throws ParseException {
        /* DestinoVO destino = (DestinoVO) vista.comboDestino.getSelectedItem();
        ViajeVO viaje = new ViajeVO(destino.getId(), vista.dateFecha.getFechaSeleccionada(), Integer.parseInt(vista.comboAsientos.getSelectedItem().toString()));
        if (modelo.insertar(viaje) > 0) {
            JOptionPane.showMessageDialog(vista, "Se ha registrado el viaje");
            //cargarTabla();
            crearAsientos(viaje, 1, viaje.getNoAsientos());
        } else {
            JOptionPane.showMessageDialog(vista, "Ha ocurrido un error al registrar el viaje");
        }
        clean();*/
    }

    private void crearAsientos(ViajeVO _viaje, int inicio, int fin) {
        /*ViajeVO viaje = (ViajeVO) modelo.encontrarByDestinoDate(_viaje.getIdDestino(), _viaje.getFecha()).get(0);
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
        }*/
    }

    private boolean datosValidos() {
        /* if (vista.comboDestino.getSelectedIndex() == 0) {
            return false;
        }
        if (vista.comboAsientos.getSelectedIndex() == 0) {
            return false;
        }
        if (vista.dateFecha.getFechaSeleccionada().equals("")) {
            return false;
        }*/
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
                    JOptionPane.showMessageDialog(vista, i + 1);
                }
            }
        } else if (!camion64) {
            for (int i = 0; i < 47; i++) {
                if (me.getSource() == panel47.arreglo[i]) {
                    JOptionPane.showMessageDialog(vista, i + 1);

                }
            }
        }

    }

    private void habilitarBotones(boolean estado) {
        vista.btnComprar.setEnabled(estado);
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
