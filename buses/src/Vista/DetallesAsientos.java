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
 * @author alxyg
 */
public class DetallesAsientos extends javax.swing.JFrame {

    /**
     * Creates new form Detalles
     */
    public DetallesAsientos() {
        initComponents();
        this.setLocationRelativeTo(null);
        icono();
        backArrow();
    }

    private void icono(){
        try {
            setIconImage(new ImageIcon(getClass().getResource("/Assets/vista_ventana.jpg")).getImage());
        } catch (Exception e) {
        }
    }

    private void backArrow() {
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

        rSComboMetroBeanInfo1 = new rojerusan.RSComboMetroBeanInfo();
        jPanel1 = new javax.swing.JPanel();
        comboEstado = new javax.swing.JComboBox<>();
        comboCiudad = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        comboFecha = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        comboViaje = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbl_back = new javax.swing.JLabel();
        lbl_rergesar = new javax.swing.JLabel();
        btnConsultar = new rojerusan.RSButtonHover();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboEstado.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        comboEstado.setNextFocusableComponent(comboCiudad);
        jPanel1.add(comboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 240, -1));

        comboCiudad.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        comboCiudad.setNextFocusableComponent(comboFecha);
        jPanel1.add(comboCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 240, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Ciudad:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        comboFecha.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        comboFecha.setNextFocusableComponent(comboViaje);
        jPanel1.add(comboFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 240, -1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Fecha:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 60, -1));

        comboViaje.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        comboViaje.setNextFocusableComponent(btnConsultar);
        jPanel1.add(comboViaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 240, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Viaje:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Estado:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        lbl_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_backMouseClicked(evt);
            }
        });
        jPanel1.add(lbl_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 20, 30, 40));

        lbl_rergesar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_rergesar.setText("Regresar");
        jPanel1.add(lbl_rergesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 30, -1, 20));

        btnConsultar.setText("Consultar");
        btnConsultar.setNextFocusableComponent(comboEstado);
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        jPanel1.add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 240, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_backMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_backMouseClicked

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConsultarActionPerformed

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
            java.util.logging.Logger.getLogger(DetallesAsientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetallesAsientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetallesAsientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetallesAsientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetallesAsientos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public rojerusan.RSButtonHover btnConsultar;
    public javax.swing.JComboBox<String> comboCiudad;
    public javax.swing.JComboBox<String> comboEstado;
    public javax.swing.JComboBox<String> comboFecha;
    public javax.swing.JComboBox<String> comboViaje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel lbl_back;
    private javax.swing.JLabel lbl_rergesar;
    private rojerusan.RSComboMetroBeanInfo rSComboMetroBeanInfo1;
    // End of variables declaration//GEN-END:variables
}
