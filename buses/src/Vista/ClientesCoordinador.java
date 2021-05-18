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
public class ClientesCoordinador extends javax.swing.JFrame {

    /**
     * Creates new form Clientes
     */
    public ClientesCoordinador() {
        initComponents();
        this.setLocationRelativeTo(null);
        backArrow();
        icono();
    }
    private void icono(){
        setIconImage(new ImageIcon(getClass().getResource("../Assets/logo3.png")).getImage());
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        lbl_rergesar = new javax.swing.JLabel();
        lbl_back = new javax.swing.JLabel();
        lbl_rergesar1 = new javax.swing.JLabel();
        lbl_back1 = new javax.swing.JLabel();
        btn_añadir1 = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Clientes");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 67, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Teléfono");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 128, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Correo");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 193, -1, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 64, 197, -1));
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 125, 197, -1));
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 187, 197, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Buscar");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 40, -1));
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 360, -1));

        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 410, 320));

        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRadioButton1.setText("Nombre");
        jPanel1.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, -1));

        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRadioButton2.setText("Teléfono");
        jPanel1.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, -1, -1));

        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRadioButton3.setText("Correo");
        jPanel1.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, -1, -1));

        lbl_rergesar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_rergesar.setText("Regresar");
        jPanel1.add(lbl_rergesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 22, -1, 20));

        lbl_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_backMouseClicked(evt);
            }
        });
        jPanel1.add(lbl_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 30, 40));

        lbl_rergesar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_rergesar1.setText("Regresar");
        jPanel1.add(lbl_rergesar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 22, -1, 20));

        lbl_back1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_back1MouseClicked(evt);
            }
        });
        jPanel1.add(lbl_back1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 30, 40));

        btn_añadir1.setBackground(new java.awt.Color(5, 101, 249));
        btn_añadir1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_añadir1.setForeground(new java.awt.Color(255, 255, 255));
        btn_añadir1.setText("Añadir");
        btn_añadir1.setBorder(null);
        btn_añadir1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(btn_añadir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 87, 30));

        btn_modificar.setBackground(new java.awt.Color(5, 101, 249));
        btn_modificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_modificar.setForeground(new java.awt.Color(255, 255, 255));
        btn_modificar.setText("Modificar");
        btn_modificar.setBorder(null);
        btn_modificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(btn_modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 87, 28));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_backMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_backMouseClicked

    private void lbl_back1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_back1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_back1MouseClicked

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
            java.util.logging.Logger.getLogger(ClientesCoordinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientesCoordinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientesCoordinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientesCoordinador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientesCoordinador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_añadir1;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel lbl_back;
    private javax.swing.JLabel lbl_back1;
    private javax.swing.JLabel lbl_rergesar;
    private javax.swing.JLabel lbl_rergesar1;
    // End of variables declaration//GEN-END:variables
}
