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
public class Clientes extends javax.swing.JFrame {

    /**
     * Creates new form ClientesAdmin
     */
    public Clientes() {
        initComponents();
        this.setLocationRelativeTo(null);
        backArrow();
        //bgImage();
        icono();
    }
    private void icono(){
        try {
            setIconImage(new ImageIcon(getClass().getResource("/Assets/vista_ventana.jpg")).getImage());
        } catch (Exception e) {
        }
    }
    private void bgImage(){
        try {
             ImageIcon bg_img;
             bg_img = new ImageIcon(getClass().getResource("/Assets/bg.png"));
             Icon bgIcon = new ImageIcon(bg_img.getImage().getScaledInstance(lbl_bg.getWidth(),
                     lbl_bg.getHeight(), Image.SCALE_SMOOTH));
             lbl_bg.setIcon(bgIcon);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado al cargar los recursos 2");
             System.out.println("error");
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
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        radioNombre = new javax.swing.JRadioButton();
        radioTelefono = new javax.swing.JRadioButton();
        radioCorreo = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox<>();
        lbl_rergesar = new javax.swing.JLabel();
        lbl_back = new javax.swing.JLabel();
        btnAnadir = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        lbl_bg = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        txtNombre.setNextFocusableComponent(txtTelefono);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 64, 197, -1));

        txtTelefono.setNextFocusableComponent(txtCorreo);
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 125, 197, -1));

        txtCorreo.setNextFocusableComponent(comboTipo);
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 187, 197, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Buscar");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 40, -1));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 570, -1));

        jScrollPane1.setViewportView(tablaClientes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 620, 320));

        radioNombre.setBackground(new java.awt.Color(255, 255, 255));
        radioNombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        radioNombre.setText("Nombre");
        jPanel1.add(radioNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, -1));

        radioTelefono.setBackground(new java.awt.Color(255, 255, 255));
        radioTelefono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        radioTelefono.setText("Teléfono");
        jPanel1.add(radioTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, -1, -1));

        radioCorreo.setBackground(new java.awt.Color(255, 255, 255));
        radioCorreo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        radioCorreo.setText("Correo");
        jPanel1.add(radioCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Tipo");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        comboTipo.setNextFocusableComponent(btnAnadir);
        jPanel1.add(comboTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 190, -1));

        lbl_rergesar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_rergesar.setText("Regresar");
        jPanel1.add(lbl_rergesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 22, -1, 20));

        lbl_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_backMouseClicked(evt);
            }
        });
        jPanel1.add(lbl_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 30, 40));

        btnAnadir.setBackground(new java.awt.Color(5, 101, 249));
        btnAnadir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAnadir.setForeground(new java.awt.Color(255, 255, 255));
        btnAnadir.setText("Añadir");
        btnAnadir.setBorder(null);
        btnAnadir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAnadir.setNextFocusableComponent(txtNombre);
        jPanel1.add(btnAnadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 87, 30));

        btnModificar.setBackground(new java.awt.Color(5, 101, 249));
        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.setBorder(null);
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 87, 28));
        jPanel1.add(lbl_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 470));

        btnEliminar.setBackground(new java.awt.Color(5, 101, 249));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(null);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 87, 28));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1029, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_backMouseClicked
      
    }//GEN-LAST:event_lbl_backMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyReleased

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAnadir;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lbl_back;
    private javax.swing.JLabel lbl_bg;
    private javax.swing.JLabel lbl_rergesar;
    public javax.swing.JRadioButton radioCorreo;
    public javax.swing.JRadioButton radioNombre;
    public javax.swing.JRadioButton radioTelefono;
    public javax.swing.JTable tablaClientes;
    public javax.swing.JTextField txtBuscar;
    public javax.swing.JTextField txtCorreo;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
