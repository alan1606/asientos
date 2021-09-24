package Vista;

import javax.swing.ImageIcon;
import rojerusan.RSAnimation;

/**
 *
 * @author Alex Duarte
 */
public class MenuAdmin extends javax.swing.JFrame {


    /**
     * Creates new form MenuAdmin
     */
    public MenuAdmin() {
        initComponents();
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        RSAnimation.setBajar(-230, (alto/2)-(this.getHeight()/2), 2, 2, this);
        this.setLocationRelativeTo(this);
        //icono();
    }
    private void icono(){
        setIconImage(new ImageIcon(getClass().getResource("../Assets/vista_ventana.jpg")).getImage());
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
        btnAsientos = new javax.swing.JButton();
        btnDestinos = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnDetalles = new javax.swing.JButton();
        btnViajes = new javax.swing.JButton();
        btnAnadir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnHoteles = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        btnHotelesEnViaje = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btnCancelaciones = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        btn_info = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú del administrador");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAsientos.setBackground(new java.awt.Color(255, 255, 255));
        btnAsientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/005-asientos.png"))); // NOI18N
        btnAsientos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAsientos.setNextFocusableComponent(btnDestinos);
        btnAsientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsientosActionPerformed(evt);
            }
        });
        jPanel1.add(btnAsientos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        btnDestinos.setBackground(new java.awt.Color(255, 255, 255));
        btnDestinos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/002-destino.png"))); // NOI18N
        btnDestinos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDestinos.setNextFocusableComponent(btnViajes);
        btnDestinos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDestinosActionPerformed(evt);
            }
        });
        jPanel1.add(btnDestinos, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));

        btnClientes.setBackground(new java.awt.Color(255, 255, 255));
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/003-cliente.png"))); // NOI18N
        btnClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnClientes.setNextFocusableComponent(btnDetalles);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        jPanel1.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        btnDetalles.setBackground(new java.awt.Color(255, 255, 255));
        btnDetalles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/006-informacion.png"))); // NOI18N
        btnDetalles.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDetalles.setNextFocusableComponent(btnAnadir);
        btnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesActionPerformed(evt);
            }
        });
        jPanel1.add(btnDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));

        btnViajes.setBackground(new java.awt.Color(255, 255, 255));
        btnViajes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/007-viajes.png"))); // NOI18N
        btnViajes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnViajes.setNextFocusableComponent(btnHoteles);
        btnViajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViajesActionPerformed(evt);
            }
        });
        jPanel1.add(btnViajes, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        btnAnadir.setBackground(new java.awt.Color(255, 255, 255));
        btnAnadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/008-anadir.png"))); // NOI18N
        btnAnadir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAnadir.setNextFocusableComponent(btnHotelesEnViaje);
        btnAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirActionPerformed(evt);
            }
        });
        jPanel1.add(btnAnadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, -1, -1));

        jLabel3.setText("Asientos");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        jLabel4.setText("Destinos");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, -1, -1));

        jLabel5.setText("Viajes");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 40, -1));

        jLabel6.setText("Clientes");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));

        jLabel7.setText("Detalles de");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, -1, -1));

        jLabel8.setText("Compra");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, -1, -1));

        jLabel9.setText("Gestionar");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 60, 20));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Usuarios");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 70, -1));

        btnHoteles.setBackground(new java.awt.Color(255, 255, 255));
        btnHoteles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/hotel.png"))); // NOI18N
        btnHoteles.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnHoteles.setNextFocusableComponent(btnCancelaciones);
        btnHoteles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHotelesActionPerformed(evt);
            }
        });
        jPanel1.add(btnHoteles, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

        jLabel11.setText("Cancelaciones");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 80, -1));

        btnHotelesEnViaje.setBackground(new java.awt.Color(255, 255, 255));
        btnHotelesEnViaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/hotel-viajes.png"))); // NOI18N
        btnHotelesEnViaje.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnHotelesEnViaje.setNextFocusableComponent(btnAsientos);
        btnHotelesEnViaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHotelesEnViajeActionPerformed(evt);
            }
        });
        jPanel1.add(btnHotelesEnViaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, -1, -1));

        jLabel12.setText("Hoteles en viaje");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, -1, -1));

        btnCancelaciones.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/cancelar_2.png"))); // NOI18N
        btnCancelaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCancelaciones.setNextFocusableComponent(btnClientes);
        btnCancelaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelacionesActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, -1, -1));

        jLabel13.setText("Hoteles");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 50, -1));

        btn_info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/002-info.png"))); // NOI18N
        btn_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_infoActionPerformed(evt);
            }
        });
        jPanel1.add(btn_info, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 250, 20, 20));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Seleccione la opción a elegir");

        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N
        jLabel2.setText("Menú");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(319, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(341, 341, 341))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAsientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsientosActionPerformed
   
    }//GEN-LAST:event_btnAsientosActionPerformed

    private void btnDestinosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDestinosActionPerformed
 
    }//GEN-LAST:event_btnDestinosActionPerformed

    private void btnViajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViajesActionPerformed

    }//GEN-LAST:event_btnViajesActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed

    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDetallesActionPerformed

    private void btnAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnadirActionPerformed

    private void btnHotelesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHotelesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHotelesActionPerformed

    private void btnHotelesEnViajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHotelesEnViajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHotelesEnViajeActionPerformed

    private void btnCancelacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelacionesActionPerformed

    private void btn_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_infoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_infoActionPerformed

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
            java.util.logging.Logger.getLogger(MenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAnadir;
    public javax.swing.JButton btnAsientos;
    public javax.swing.JButton btnCancelaciones;
    public javax.swing.JButton btnClientes;
    public javax.swing.JButton btnDestinos;
    public javax.swing.JButton btnDetalles;
    public javax.swing.JButton btnHoteles;
    public javax.swing.JButton btnHotelesEnViaje;
    public javax.swing.JButton btnViajes;
    private javax.swing.JButton btn_info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
