/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



/**
 *
 * @author alanm
 */
public class Asientos extends javax.swing.JFrame {

    /**
     * Creates new form Asientos
     */
    public Asientos() {
        initComponents();
        backArrow();
        icono();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_back = new javax.swing.JLabel();
        lbl_rergesar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        contenedor = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSube = new javax.swing.JTextField();
        lblCosto = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        btnComprar = new rojerusan.RSButtonMetro();
        jLabel11 = new javax.swing.JLabel();
        comboDestino = new javax.swing.JComboBox<>();
        comboAsientosAComprar = new javax.swing.JComboBox<>();
        comboFecha = new javax.swing.JComboBox<>();
        comboNoAsientos = new javax.swing.JComboBox<>();
        comboId = new javax.swing.JComboBox<>();
        comboTipoCliente = new javax.swing.JComboBox<>();
        comboCliente = new javax.swing.JComboBox<>();
        comboHoteles = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHoteles = new javax.swing.JTable();
        btnAgregarHotel = new javax.swing.JButton();
        comboNumeroHabitaciones = new javax.swing.JComboBox<>();
        btnQuitarHotel = new javax.swing.JButton();
        comboHoraSalida = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        comboFormaPago = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        comboTipoViaje = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        comboHoraRegreso = new javax.swing.JComboBox<>();
        lblCosto1 = new javax.swing.JLabel();
        txtAnticipo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        dateFechaRegreso = new rojerusan.RSDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_backMouseClicked(evt);
            }
        });
        jPanel1.add(lbl_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 30, 40));

        lbl_rergesar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_rergesar.setText("Regresar");
        jPanel1.add(lbl_rergesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 22, -1, 20));

        jLabel1.setText("Destino");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel2.setText("Id del viaje");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 90, 20));

        jLabel3.setText("Fecha");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, 10));

        jLabel4.setText("Número de asientos del viaje");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 190, 20));

        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(contenedor);
        contenedor.setLayout(contenedorLayout);
        contenedorLayout.setHorizontalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        contenedorLayout.setVerticalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );

        jPanel1.add(contenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, 480, 670));

        jLabel5.setText("Número de asientos a comprar");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 180, 20));

        jLabel6.setText("Tipo de cliente");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, 10));

        jLabel7.setText("Cliente");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 50, 20));

        jLabel8.setText("Habitaciones");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 290, 180, 20));

        jLabel9.setText("Sube(n) en");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 80, 20));

        txtSube.setNextFocusableComponent(comboTipoViaje);
        jPanel1.add(txtSube, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 330, 30));

        lblCosto.setText("Costo");
        jPanel1.add(lblCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 80, 20));

        txtCosto.setNextFocusableComponent(txtAnticipo);
        jPanel1.add(txtCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 140, 30));

        btnComprar.setText("Comprar");
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });
        jPanel1.add(btnComprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 600, 320, 90));

        jLabel11.setText("Hora de salida");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, 80, 20));

        comboDestino.setNextFocusableComponent(comboFecha);
        jPanel1.add(comboDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 330, -1));

        comboAsientosAComprar.setNextFocusableComponent(txtSube);
        jPanel1.add(comboAsientosAComprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 330, -1));

        comboFecha.setNextFocusableComponent(comboNoAsientos);
        jPanel1.add(comboFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 330, -1));

        comboNoAsientos.setNextFocusableComponent(comboId);
        jPanel1.add(comboNoAsientos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 330, -1));

        comboId.setNextFocusableComponent(comboTipoCliente);
        jPanel1.add(comboId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 330, -1));

        comboTipoCliente.setNextFocusableComponent(comboCliente);
        jPanel1.add(comboTipoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 330, -1));

        comboCliente.setNextFocusableComponent(comboAsientosAComprar);
        jPanel1.add(comboCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 330, -1));

        comboHoteles.setNextFocusableComponent(comboNumeroHabitaciones);
        jPanel1.add(comboHoteles, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 240, -1));

        jScrollPane1.setViewportView(tableHoteles);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 400, 320, 110));

        btnAgregarHotel.setBackground(new java.awt.Color(5, 101, 249));
        btnAgregarHotel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregarHotel.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarHotel.setText("Agregar");
        btnAgregarHotel.setBorder(null);
        btnAgregarHotel.setNextFocusableComponent(btnComprar);
        jPanel1.add(btnAgregarHotel, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 350, 70, 30));

        comboNumeroHabitaciones.setNextFocusableComponent(btnAgregarHotel);
        jPanel1.add(comboNumeroHabitaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 310, 70, -1));

        btnQuitarHotel.setBackground(new java.awt.Color(5, 101, 249));
        btnQuitarHotel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnQuitarHotel.setForeground(new java.awt.Color(255, 255, 255));
        btnQuitarHotel.setText("Quitar");
        btnQuitarHotel.setBorder(null);
        jPanel1.add(btnQuitarHotel, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 530, 80, 30));

        comboHoraSalida.setNextFocusableComponent(dateFechaRegreso);
        jPanel1.add(comboHoraSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, 140, -1));

        jLabel13.setText("Forma de pago");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, 80, 20));

        comboFormaPago.setNextFocusableComponent(comboHoraSalida);
        jPanel1.add(comboFormaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, 140, 30));

        jLabel14.setText("Tipo de viaje");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 80, 20));

        comboTipoViaje.setNextFocusableComponent(txtCosto);
        jPanel1.add(comboTipoViaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 330, -1));

        jLabel12.setText("Hora de regreso");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 230, 80, 20));

        comboHoraRegreso.setNextFocusableComponent(comboHoteles);
        jPanel1.add(comboHoraRegreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, 140, -1));

        lblCosto1.setText("Anticipo");
        jPanel1.add(lblCosto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 80, 20));

        txtAnticipo.setNextFocusableComponent(comboFormaPago);
        jPanel1.add(txtAnticipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 130, 30));

        jLabel10.setText("Fecha de regreso");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, -1, 10));

        dateFechaRegreso.setNextFocusableComponent(comboHoraRegreso);
        jPanel1.add(dateFechaRegreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_backMouseClicked

    }//GEN-LAST:event_lbl_backMouseClicked

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnComprarActionPerformed

    private void icono(){
        try {
            setIconImage(new ImageIcon(getClass().getResource("/Assets/vista_ventana.jpg")).getImage());
        } catch (Exception e) {
        }
    }
    
    private void backArrow(){
        try {
             ImageIcon arrow;
             arrow = new ImageIcon(getClass().getResource("/Assets/back_arrow.png"));
             Icon arrowIcon = new ImageIcon(arrow.getImage().getScaledInstance(lbl_back.getWidth(),
                     lbl_back.getHeight(), Image.SCALE_SMOOTH));
             lbl_back.setIcon(arrowIcon);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado al cargar los recursos 2");
             System.out.println("error");
         }

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Asientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Asientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Asientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Asientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Asientos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarHotel;
    public rojerusan.RSButtonMetro btnComprar;
    public javax.swing.JButton btnQuitarHotel;
    public javax.swing.JComboBox<String> comboAsientosAComprar;
    public javax.swing.JComboBox<String> comboCliente;
    public javax.swing.JComboBox<String> comboDestino;
    public javax.swing.JComboBox<String> comboFecha;
    public javax.swing.JComboBox<String> comboFormaPago;
    public javax.swing.JComboBox<String> comboHoraRegreso;
    public javax.swing.JComboBox<String> comboHoraSalida;
    public javax.swing.JComboBox<String> comboHoteles;
    public javax.swing.JComboBox<String> comboId;
    public javax.swing.JComboBox<String> comboNoAsientos;
    public javax.swing.JComboBox<String> comboNumeroHabitaciones;
    public javax.swing.JComboBox<String> comboTipoCliente;
    public javax.swing.JComboBox<String> comboTipoViaje;
    public javax.swing.JPanel contenedor;
    public rojerusan.RSDateChooser dateFechaRegreso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCosto;
    private javax.swing.JLabel lblCosto1;
    public javax.swing.JLabel lbl_back;
    private javax.swing.JLabel lbl_rergesar;
    public javax.swing.JTable tableHoteles;
    public javax.swing.JTextField txtAnticipo;
    public javax.swing.JTextField txtCosto;
    public javax.swing.JTextField txtSube;
    // End of variables declaration//GEN-END:variables
}
