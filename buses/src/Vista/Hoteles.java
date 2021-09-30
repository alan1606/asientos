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
 * @author Alex Duarte
 */
public class Hoteles extends javax.swing.JFrame {

    /**
     * Creates new form Hoteles
     */
    public Hoteles() {
        initComponents();
        this.setLocationRelativeTo(null);
        backArrow();
        icono();
    }
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
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado al cargar los recursos");
            System.out.println("error");
        }

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
        jPanel2 = new javax.swing.JPanel();
        lbl_destino = new javax.swing.JLabel();
        comboDestino = new javax.swing.JComboBox<>();
        lbl_hotel = new javax.swing.JLabel();
        btnAnadir = new javax.swing.JButton();
        lbl_back = new javax.swing.JLabel();
        lbl_rergesar = new javax.swing.JLabel();
        comboHotel = new javax.swing.JComboBox<>();
        btnNuevoHotel = new javax.swing.JButton();
        lbl_hotel1 = new javax.swing.JLabel();
        txtModificar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        comboDestinoSearch = new javax.swing.JComboBox<>();
        lbl_destino1 = new javax.swing.JLabel();
        btnBorrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHoteles = new rojerusan.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hoteles");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_destino.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_destino.setText("Destino");
        jPanel2.add(lbl_destino, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 101, -1, -1));

        comboDestino.setBackground(new java.awt.Color(153, 204, 255));
        comboDestino.setNextFocusableComponent(comboHotel);
        jPanel2.add(comboDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 94, 179, 30));

        lbl_hotel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_hotel.setText("Hotel");
        jPanel2.add(lbl_hotel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        btnAnadir.setBackground(new java.awt.Color(5, 101, 249));
        btnAnadir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAnadir.setForeground(new java.awt.Color(255, 255, 255));
        btnAnadir.setText("Añadir");
        btnAnadir.setBorder(null);
        btnAnadir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAnadir.setNextFocusableComponent(comboDestino);
        jPanel2.add(btnAnadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 127, 30));

        lbl_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_backMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 30, 40));

        lbl_rergesar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_rergesar.setText("Regresar");
        jPanel2.add(lbl_rergesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 21, -1, 20));

        comboHotel.setBackground(new java.awt.Color(153, 204, 255));
        comboHotel.setNextFocusableComponent(txtModificar);
        jPanel2.add(comboHotel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 179, 30));

        btnNuevoHotel.setBackground(new java.awt.Color(5, 101, 249));
        btnNuevoHotel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNuevoHotel.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevoHotel.setText("Crear nuevo hotel");
        btnNuevoHotel.setBorder(null);
        btnNuevoHotel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevoHotel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoHotelActionPerformed(evt);
            }
        });
        jPanel2.add(btnNuevoHotel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 127, 30));

        lbl_hotel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_hotel1.setText("Modificar");
        jPanel2.add(lbl_hotel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        txtModificar.setNextFocusableComponent(btnAnadir);
        txtModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModificarActionPerformed(evt);
            }
        });
        jPanel2.add(txtModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 180, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 500));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hoteles ya registrados"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboDestinoSearch.setBackground(new java.awt.Color(153, 204, 255));
        jPanel4.add(comboDestinoSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 330, 30));

        lbl_destino1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_destino1.setText("Destino");
        jPanel4.add(lbl_destino1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 449, 105));

        btnBorrar.setBackground(new java.awt.Color(5, 101, 249));
        btnBorrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBorrar.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrar.setText("Borrar");
        btnBorrar.setBorder(null);
        btnBorrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 110, 30));

        btnModificar.setBackground(new java.awt.Color(5, 101, 249));
        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.setBorder(null);
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 110, 30));

        jScrollPane1.setViewportView(tableHoteles);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 122, 450, 266));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 490, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_backMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_backMouseClicked

    private void btnNuevoHotelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoHotelActionPerformed
  
    }//GEN-LAST:event_btnNuevoHotelActionPerformed

    private void txtModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModificarActionPerformed

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
            java.util.logging.Logger.getLogger(Hoteles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Hoteles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Hoteles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Hoteles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Hoteles().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAnadir;
    public javax.swing.JButton btnBorrar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnNuevoHotel;
    public javax.swing.JComboBox<String> comboDestino;
    public javax.swing.JComboBox<String> comboDestinoSearch;
    public javax.swing.JComboBox<String> comboHotel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lbl_back;
    private javax.swing.JLabel lbl_destino;
    private javax.swing.JLabel lbl_destino1;
    private javax.swing.JLabel lbl_hotel;
    private javax.swing.JLabel lbl_hotel1;
    private javax.swing.JLabel lbl_rergesar;
    public rojerusan.RSTableMetro tableHoteles;
    public javax.swing.JTextField txtModificar;
    // End of variables declaration//GEN-END:variables
}
