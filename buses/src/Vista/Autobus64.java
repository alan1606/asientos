/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex Duarte
 */
public class Autobus64 extends javax.swing.JFrame {

    /**
     * Creates new form Autobus64
     */
    private JLabel[] arreglo;
    public Autobus64() {
        initComponents();
        lblArreglo();
        setDesignLabels();
        camion();
    }
    
    private void camion(){
     ImageIcon fondo;
        fondo = new ImageIcon(getClass().getResource("/Assets/autobus64.png"));
        Icon fondoIcono = new ImageIcon(fondo.getImage().getScaledInstance(lbl_fondo.getWidth(),
                lbl_fondo.getHeight(), Image.SCALE_SMOOTH));
        lbl_fondo.setIcon(fondoIcono);
    }
    private void lblArreglo(){
        arreglo = new JLabel[64];
        arreglo[0] = lbl1;
        arreglo[1] = lbl2;
        arreglo[2] = lbl3;
        arreglo[3] = lbl4;
        arreglo[4] = lbl5;
        arreglo[5] = lbl6;
        arreglo[6] = lbl7;
        arreglo[7] = lbl8;
        arreglo[8] = lbl9;
        arreglo[9] = lbl10;
        arreglo[10] = lbl11;
        arreglo[11] = lbl12;
        arreglo[12] = lbl13;
        arreglo[13] = lbl14;
        arreglo[14] = lbl15;
        arreglo[15] = lbl16;
        arreglo[16] = lbl17;
        arreglo[17] = lbl18;
        arreglo[18] = lbl19;
        arreglo[19] = lbl20;
        arreglo[20] = lbl21;
        arreglo[21] = lbl22;
        arreglo[22] = lbl23;
        arreglo[23] = lbl24;
        arreglo[24] = lbl25;
        arreglo[25] = lbl26;
        arreglo[26] = lbl27;
        arreglo[27] = lbl28;
        arreglo[28] = lbl29;
        arreglo[29] = lbl30;
        arreglo[30] = lbl31;
        arreglo[31] = lbl32;
        arreglo[32] = lbl33;
        arreglo[33] = lbl34;
        arreglo[34] = lbl35;
        arreglo[35] = lbl36;
        arreglo[36] = lbl37;
        arreglo[37] = lbl38;
        arreglo[38] = lbl39;
        arreglo[39] = lbl40;
        arreglo[40] = lbl41;
        arreglo[41] = lbl42;
        arreglo[42] = lbl43;
        arreglo[43] = lbl44;
        arreglo[44] = lbl45;
        arreglo[45] = lbl46;
        arreglo[46] = lbl47;
        arreglo[47] = lbl48;
        arreglo[48] = lbl49;
        arreglo[49] = lbl50;
        arreglo[50] = lbl51;
        arreglo[51] = lbl52;
        arreglo[52] = lbl53;
        arreglo[53] = lbl54;
        arreglo[54] = lbl55;
        arreglo[55] = lbl56;
        arreglo[56] = lbl57;
        arreglo[57] = lbl58;
        arreglo[58] = lbl59;
        arreglo[59] = lbl60;
        arreglo[60] = lbl61;
        arreglo[61] = lbl62;
        arreglo[62] = lbl63;
        arreglo[63] = lbl64;
    }
    private void setDesignLabels(){
        for (int i = 0; i < 64; i++) {
            this.arreglo[i].setBackground(new Color(51, 255, 51, 160));
            this.arreglo[i].setOpaque(true);
        }
    }
        private void setDesignOcupado(){
        for (int i = 0; i < 64; i++) {
            this.arreglo[i].setBackground(new Color(255, 0, 0, 160));
            this.arreglo[i].setOpaque(true);
        }
    }
    private void setDesignApartado(){
        for (int i = 0; i < 65; i++){
            this.arreglo[i].setBackground(new Color(247, 255, 0, 160));
            this.arreglo[i].setOpaque(true);
        }
    }
    public boolean eliminar(){
        Object[] opcion = { "Eliminar", "Cancelar" }; 
    int op = JOptionPane.showOptionDialog(null, "¿Esta seguro que desea eliminar la información de este elemento?", 
            "Confirmar Eliminar", 
    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
    opcion, opcion[0]);
        if (op == 0) {
            return true;
        }else if(op == 1){
        return false;
        }
        return false;
    }
    public boolean vendido(){
        Object[] opcion = { "Vender", "Eliminar" }; 
    int op = JOptionPane.showOptionDialog(null, "¿Esta seguro de querer vender este lugar?", 
            "Confirmar Vender", 
    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
    opcion, opcion[0]);
        if (op == 0) {
            return true;
        }else if(op == 1){
        return false;
        }
        return false;
    }
    public boolean dialogo(){
        Object[] opcion = { "Apartar", "Vender", "Eliminar" }; 
    int op = JOptionPane.showOptionDialog(null, "¿Que desea hacer?", "Formulario", 
    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
    opcion, opcion[0]);
        if(op == 1){
        setDesignOcupado();
        }else if(op == 0){
           setDesignApartado();
        }else if(op==2){
            if(eliminar()==true){
                setDesignLabels();
            }
        }
        return true;
    }
    String [] arreglolbl = {"1","2","3","4","5","6","7","8","9","10",};
    private void pane(){
        for (String i : arreglolbl) {
            JOptionPane.showMessageDialog(null, "Jalando label" + i);
        }
//        JOptionPane.showMessageDialog(null, "Jalando label");
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
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lbl6 = new javax.swing.JLabel();
        lbl7 = new javax.swing.JLabel();
        lbl8 = new javax.swing.JLabel();
        lbl9 = new javax.swing.JLabel();
        lbl10 = new javax.swing.JLabel();
        lbl11 = new javax.swing.JLabel();
        lbl12 = new javax.swing.JLabel();
        lbl13 = new javax.swing.JLabel();
        lbl14 = new javax.swing.JLabel();
        lbl15 = new javax.swing.JLabel();
        lbl16 = new javax.swing.JLabel();
        lbl17 = new javax.swing.JLabel();
        lbl18 = new javax.swing.JLabel();
        lbl19 = new javax.swing.JLabel();
        lbl20 = new javax.swing.JLabel();
        lbl21 = new javax.swing.JLabel();
        lbl22 = new javax.swing.JLabel();
        lbl23 = new javax.swing.JLabel();
        lbl24 = new javax.swing.JLabel();
        lbl25 = new javax.swing.JLabel();
        lbl26 = new javax.swing.JLabel();
        lbl27 = new javax.swing.JLabel();
        lbl28 = new javax.swing.JLabel();
        lbl29 = new javax.swing.JLabel();
        lbl30 = new javax.swing.JLabel();
        lbl31 = new javax.swing.JLabel();
        lbl32 = new javax.swing.JLabel();
        lbl33 = new javax.swing.JLabel();
        lbl34 = new javax.swing.JLabel();
        lbl35 = new javax.swing.JLabel();
        lbl36 = new javax.swing.JLabel();
        lbl37 = new javax.swing.JLabel();
        lbl38 = new javax.swing.JLabel();
        lbl39 = new javax.swing.JLabel();
        lbl40 = new javax.swing.JLabel();
        lbl41 = new javax.swing.JLabel();
        lbl42 = new javax.swing.JLabel();
        lbl43 = new javax.swing.JLabel();
        lbl44 = new javax.swing.JLabel();
        lbl45 = new javax.swing.JLabel();
        lbl46 = new javax.swing.JLabel();
        lbl47 = new javax.swing.JLabel();
        lbl48 = new javax.swing.JLabel();
        lbl49 = new javax.swing.JLabel();
        lbl50 = new javax.swing.JLabel();
        lbl51 = new javax.swing.JLabel();
        lbl52 = new javax.swing.JLabel();
        lbl53 = new javax.swing.JLabel();
        lbl54 = new javax.swing.JLabel();
        lbl55 = new javax.swing.JLabel();
        lbl56 = new javax.swing.JLabel();
        lbl57 = new javax.swing.JLabel();
        lbl58 = new javax.swing.JLabel();
        lbl59 = new javax.swing.JLabel();
        lbl60 = new javax.swing.JLabel();
        lbl61 = new javax.swing.JLabel();
        lbl62 = new javax.swing.JLabel();
        lbl63 = new javax.swing.JLabel();
        lbl64 = new javax.swing.JLabel();
        lbl_fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl1.setBackground(new java.awt.Color(255, 204, 0));
        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl1MouseClicked(evt);
            }
        });
        jPanel1.add(lbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 40, 60));

        lbl2.setBackground(new java.awt.Color(255, 204, 0));
        lbl2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl2MouseClicked(evt);
            }
        });
        jPanel1.add(lbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 40, 60));

        lbl3.setBackground(new java.awt.Color(255, 204, 0));
        lbl3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl3MouseClicked(evt);
            }
        });
        jPanel1.add(lbl3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 40, 60));

        lbl4.setBackground(new java.awt.Color(255, 204, 0));
        lbl4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl4MouseClicked(evt);
            }
        });
        jPanel1.add(lbl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 40, 60));

        lbl5.setBackground(new java.awt.Color(255, 204, 0));
        lbl5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl5MouseClicked(evt);
            }
        });
        jPanel1.add(lbl5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 40, 60));

        lbl6.setBackground(new java.awt.Color(255, 204, 0));
        lbl6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl6MouseClicked(evt);
            }
        });
        jPanel1.add(lbl6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 40, 60));

        lbl7.setBackground(new java.awt.Color(255, 204, 0));
        lbl7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl7MouseClicked(evt);
            }
        });
        jPanel1.add(lbl7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 41, 50));

        lbl8.setBackground(new java.awt.Color(255, 204, 0));
        lbl8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl8MouseClicked(evt);
            }
        });
        jPanel1.add(lbl8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 41, 50));

        lbl9.setBackground(new java.awt.Color(255, 204, 0));
        lbl9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl9MouseClicked(evt);
            }
        });
        jPanel1.add(lbl9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 40, 50));

        lbl10.setBackground(new java.awt.Color(255, 204, 0));
        lbl10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl10MouseClicked(evt);
            }
        });
        jPanel1.add(lbl10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 40, 50));

        lbl11.setBackground(new java.awt.Color(255, 204, 0));
        lbl11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl11MouseClicked(evt);
            }
        });
        jPanel1.add(lbl11, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 41, 41));

        lbl12.setBackground(new java.awt.Color(255, 204, 0));
        lbl12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl12MouseClicked(evt);
            }
        });
        jPanel1.add(lbl12, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 41, 41));

        lbl13.setBackground(new java.awt.Color(255, 204, 0));
        lbl13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl13MouseClicked(evt);
            }
        });
        jPanel1.add(lbl13, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 40, 40));

        lbl14.setBackground(new java.awt.Color(255, 204, 0));
        lbl14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl14MouseClicked(evt);
            }
        });
        jPanel1.add(lbl14, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 40, 40));

        lbl15.setBackground(new java.awt.Color(255, 204, 0));
        lbl15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl15MouseClicked(evt);
            }
        });
        jPanel1.add(lbl15, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 41, 41));

        lbl16.setBackground(new java.awt.Color(255, 204, 0));
        lbl16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl16MouseClicked(evt);
            }
        });
        jPanel1.add(lbl16, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 41, 41));

        lbl17.setBackground(new java.awt.Color(255, 204, 0));
        lbl17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl17MouseClicked(evt);
            }
        });
        jPanel1.add(lbl17, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 40, 40));

        lbl18.setBackground(new java.awt.Color(255, 204, 0));
        lbl18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl18MouseClicked(evt);
            }
        });
        jPanel1.add(lbl18, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 40, 40));

        lbl19.setBackground(new java.awt.Color(255, 204, 0));
        lbl19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl19MouseClicked(evt);
            }
        });
        jPanel1.add(lbl19, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 41, 41));

        lbl20.setBackground(new java.awt.Color(255, 204, 0));
        lbl20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl20MouseClicked(evt);
            }
        });
        jPanel1.add(lbl20, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 41, 41));

        lbl21.setBackground(new java.awt.Color(255, 204, 0));
        lbl21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl21MouseClicked(evt);
            }
        });
        jPanel1.add(lbl21, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 40, 40));

        lbl22.setBackground(new java.awt.Color(255, 204, 0));
        lbl22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl22.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl22MouseClicked(evt);
            }
        });
        jPanel1.add(lbl22, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 40, 40));

        lbl23.setBackground(new java.awt.Color(255, 204, 0));
        lbl23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl23MouseClicked(evt);
            }
        });
        jPanel1.add(lbl23, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 41, 41));

        lbl24.setBackground(new java.awt.Color(255, 204, 0));
        lbl24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl24.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl24MouseClicked(evt);
            }
        });
        jPanel1.add(lbl24, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 41, 41));

        lbl25.setBackground(new java.awt.Color(255, 204, 0));
        lbl25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl25MouseClicked(evt);
            }
        });
        jPanel1.add(lbl25, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 40, 50));

        lbl26.setBackground(new java.awt.Color(255, 204, 0));
        lbl26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl26.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl26MouseClicked(evt);
            }
        });
        jPanel1.add(lbl26, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 40, 50));

        lbl27.setBackground(new java.awt.Color(255, 204, 0));
        lbl27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl27.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl27MouseClicked(evt);
            }
        });
        jPanel1.add(lbl27, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 41, 41));

        lbl28.setBackground(new java.awt.Color(255, 204, 0));
        lbl28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl28.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl28MouseClicked(evt);
            }
        });
        jPanel1.add(lbl28, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 41, 41));

        lbl29.setBackground(new java.awt.Color(255, 204, 0));
        lbl29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl29.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl29MouseClicked(evt);
            }
        });
        jPanel1.add(lbl29, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 40, 40));

        lbl30.setBackground(new java.awt.Color(255, 204, 0));
        lbl30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl30.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl30MouseClicked(evt);
            }
        });
        jPanel1.add(lbl30, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, 40, 40));

        lbl31.setBackground(new java.awt.Color(255, 204, 0));
        lbl31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl31.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl31MouseClicked(evt);
            }
        });
        jPanel1.add(lbl31, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 41, 50));

        lbl32.setBackground(new java.awt.Color(255, 204, 0));
        lbl32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl32.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl32MouseClicked(evt);
            }
        });
        jPanel1.add(lbl32, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 41, 50));

        lbl33.setBackground(new java.awt.Color(255, 204, 0));
        lbl33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl33.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl33MouseClicked(evt);
            }
        });
        jPanel1.add(lbl33, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 40, 40));

        lbl34.setBackground(new java.awt.Color(255, 204, 0));
        lbl34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl34.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl34MouseClicked(evt);
            }
        });
        jPanel1.add(lbl34, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 40, 40));

        lbl35.setBackground(new java.awt.Color(255, 204, 0));
        lbl35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl35.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl35MouseClicked(evt);
            }
        });
        jPanel1.add(lbl35, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 41, 41));

        lbl36.setBackground(new java.awt.Color(255, 204, 0));
        lbl36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl36.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl36MouseClicked(evt);
            }
        });
        jPanel1.add(lbl36, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 41, 41));

        lbl37.setBackground(new java.awt.Color(255, 204, 0));
        lbl37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl37.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl37MouseClicked(evt);
            }
        });
        jPanel1.add(lbl37, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 40, 40));

        lbl38.setBackground(new java.awt.Color(255, 204, 0));
        lbl38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl38.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl38MouseClicked(evt);
            }
        });
        jPanel1.add(lbl38, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 270, 40, 40));

        lbl39.setBackground(new java.awt.Color(255, 204, 0));
        lbl39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl39.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl39MouseClicked(evt);
            }
        });
        jPanel1.add(lbl39, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 41, 50));

        lbl40.setBackground(new java.awt.Color(255, 204, 0));
        lbl40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl40.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl40MouseClicked(evt);
            }
        });
        jPanel1.add(lbl40, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 310, 40, 50));

        lbl41.setBackground(new java.awt.Color(255, 204, 0));
        lbl41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl41.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl41MouseClicked(evt);
            }
        });
        jPanel1.add(lbl41, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 360, 40, 40));

        lbl42.setBackground(new java.awt.Color(255, 204, 0));
        lbl42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl42.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl42MouseClicked(evt);
            }
        });
        jPanel1.add(lbl42, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, 40, 40));

        lbl43.setBackground(new java.awt.Color(255, 204, 0));
        lbl43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl43.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl43MouseClicked(evt);
            }
        });
        jPanel1.add(lbl43, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 400, 41, 41));

        lbl44.setBackground(new java.awt.Color(255, 204, 0));
        lbl44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl44.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl44MouseClicked(evt);
            }
        });
        jPanel1.add(lbl44, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 400, 40, 41));

        lbl45.setBackground(new java.awt.Color(255, 204, 0));
        lbl45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl45.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl45MouseClicked(evt);
            }
        });
        jPanel1.add(lbl45, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 440, 40, 40));

        lbl46.setBackground(new java.awt.Color(255, 204, 0));
        lbl46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl46.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl46MouseClicked(evt);
            }
        });
        jPanel1.add(lbl46, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 440, 40, 40));

        lbl47.setBackground(new java.awt.Color(255, 204, 0));
        lbl47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl47.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl47MouseClicked(evt);
            }
        });
        jPanel1.add(lbl47, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, 40, 40));

        lbl48.setBackground(new java.awt.Color(255, 204, 0));
        lbl48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl48.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl48MouseClicked(evt);
            }
        });
        jPanel1.add(lbl48, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 440, 40, 40));

        lbl49.setBackground(new java.awt.Color(255, 204, 0));
        lbl49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl49.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl49MouseClicked(evt);
            }
        });
        jPanel1.add(lbl49, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 480, 40, 40));

        lbl50.setBackground(new java.awt.Color(255, 204, 0));
        lbl50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl50.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl50MouseClicked(evt);
            }
        });
        jPanel1.add(lbl50, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 480, 40, 40));

        lbl51.setBackground(new java.awt.Color(255, 204, 0));
        lbl51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl51.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl51MouseClicked(evt);
            }
        });
        jPanel1.add(lbl51, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 480, 40, 40));

        lbl52.setBackground(new java.awt.Color(255, 204, 0));
        lbl52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl52.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl52MouseClicked(evt);
            }
        });
        jPanel1.add(lbl52, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 480, 40, 40));

        lbl53.setBackground(new java.awt.Color(255, 204, 0));
        lbl53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl53.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl53MouseClicked(evt);
            }
        });
        jPanel1.add(lbl53, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 520, 40, 50));

        lbl54.setBackground(new java.awt.Color(255, 204, 0));
        lbl54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl54.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl54MouseClicked(evt);
            }
        });
        jPanel1.add(lbl54, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 520, 40, 50));

        lbl55.setBackground(new java.awt.Color(255, 204, 0));
        lbl55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl55.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl55MouseClicked(evt);
            }
        });
        jPanel1.add(lbl55, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 520, 40, 50));

        lbl56.setBackground(new java.awt.Color(255, 204, 0));
        lbl56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl56.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl56MouseClicked(evt);
            }
        });
        jPanel1.add(lbl56, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 520, 40, 50));

        lbl57.setBackground(new java.awt.Color(255, 204, 0));
        lbl57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl57.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl57MouseClicked(evt);
            }
        });
        jPanel1.add(lbl57, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 570, 40, 40));

        lbl58.setBackground(new java.awt.Color(255, 204, 0));
        lbl58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl58.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl58MouseClicked(evt);
            }
        });
        jPanel1.add(lbl58, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 570, 40, 40));

        lbl59.setBackground(new java.awt.Color(255, 204, 0));
        lbl59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl59.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl59.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl59MouseClicked(evt);
            }
        });
        jPanel1.add(lbl59, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 570, 40, 40));

        lbl60.setBackground(new java.awt.Color(255, 204, 0));
        lbl60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl60.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl60.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl60MouseClicked(evt);
            }
        });
        jPanel1.add(lbl60, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 570, 40, 40));

        lbl61.setBackground(new java.awt.Color(255, 204, 0));
        lbl61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl61.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl61.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl61MouseClicked(evt);
            }
        });
        jPanel1.add(lbl61, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 610, 40, 40));

        lbl62.setBackground(new java.awt.Color(255, 204, 0));
        lbl62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl62.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl62MouseClicked(evt);
            }
        });
        jPanel1.add(lbl62, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 610, 40, 40));

        lbl63.setBackground(new java.awt.Color(255, 204, 0));
        lbl63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl63.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl63MouseClicked(evt);
            }
        });
        jPanel1.add(lbl63, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 610, 40, 40));

        lbl64.setBackground(new java.awt.Color(255, 204, 0));
        lbl64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl64.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl64MouseClicked(evt);
            }
        });
        jPanel1.add(lbl64, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 610, 40, 40));
        jPanel1.add(lbl_fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 670));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl1MouseClicked
        if(lbl1.isEnabled()){

        }
    }//GEN-LAST:event_lbl1MouseClicked

    private void lbl2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl2MouseClicked

    private void lbl3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl3MouseClicked

    private void lbl4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl4MouseClicked

    private void lbl5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl5MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl5MouseClicked

    private void lbl6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl6MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl6MouseClicked

    private void lbl7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl7MouseClicked

    private void lbl8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl8MouseClicked

    private void lbl9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl9MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl9MouseClicked

    private void lbl10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl10MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl10MouseClicked

    private void lbl11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl11MouseClicked

    private void lbl12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl12MouseClicked

    private void lbl13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl13MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl13MouseClicked

    private void lbl14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl14MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl14MouseClicked

    private void lbl15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl15MouseClicked

    private void lbl16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl16MouseClicked

    private void lbl17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl17MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl17MouseClicked

    private void lbl18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl18MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl18MouseClicked

    private void lbl19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl19MouseClicked

    private void lbl20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl20MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl20MouseClicked

    private void lbl21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl21MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl21MouseClicked

    private void lbl22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl22MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl22MouseClicked

    private void lbl23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl23MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl23MouseClicked

    private void lbl24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl24MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl24MouseClicked

    private void lbl25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl25MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl25MouseClicked

    private void lbl26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl26MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl26MouseClicked

    private void lbl27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl27MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl27MouseClicked

    private void lbl28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl28MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl28MouseClicked

    private void lbl29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl29MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl29MouseClicked

    private void lbl30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl30MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl30MouseClicked

    private void lbl31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl31MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl31MouseClicked

    private void lbl32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl32MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl32MouseClicked

    private void lbl33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl33MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl33MouseClicked

    private void lbl34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl34MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl34MouseClicked

    private void lbl35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl35MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl35MouseClicked

    private void lbl36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl36MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl36MouseClicked

    private void lbl37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl37MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl37MouseClicked

    private void lbl38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl38MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl38MouseClicked

    private void lbl39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl39MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl39MouseClicked

    private void lbl40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl40MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl40MouseClicked

    private void lbl41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl41MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl41MouseClicked

    private void lbl42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl42MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl42MouseClicked

    private void lbl43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl43MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl43MouseClicked

    private void lbl44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl44MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl44MouseClicked

    private void lbl45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl45MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl45MouseClicked

    private void lbl46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl46MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl46MouseClicked

    private void lbl47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl47MouseClicked
        // TODO add your handling code here:
        pane();
    }//GEN-LAST:event_lbl47MouseClicked

    private void lbl48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl48MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl48MouseClicked

    private void lbl49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl49MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl49MouseClicked

    private void lbl50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl50MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl50MouseClicked

    private void lbl51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl51MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl51MouseClicked

    private void lbl52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl52MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl52MouseClicked

    private void lbl53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl53MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl53MouseClicked

    private void lbl54MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl54MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl54MouseClicked

    private void lbl55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl55MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl55MouseClicked

    private void lbl56MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl56MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl56MouseClicked

    private void lbl57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl57MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl57MouseClicked

    private void lbl58MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl58MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl58MouseClicked

    private void lbl59MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl59MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl59MouseClicked

    private void lbl60MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl60MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl60MouseClicked

    private void lbl61MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl61MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl61MouseClicked

    private void lbl62MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl62MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl62MouseClicked

    private void lbl63MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl63MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl63MouseClicked

    private void lbl64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl64MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl64MouseClicked

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
            java.util.logging.Logger.getLogger(Autobus64.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Autobus64.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Autobus64.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Autobus64.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Autobus64().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl10;
    private javax.swing.JLabel lbl11;
    private javax.swing.JLabel lbl12;
    private javax.swing.JLabel lbl13;
    private javax.swing.JLabel lbl14;
    private javax.swing.JLabel lbl15;
    private javax.swing.JLabel lbl16;
    private javax.swing.JLabel lbl17;
    private javax.swing.JLabel lbl18;
    private javax.swing.JLabel lbl19;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl20;
    private javax.swing.JLabel lbl21;
    private javax.swing.JLabel lbl22;
    private javax.swing.JLabel lbl23;
    private javax.swing.JLabel lbl24;
    private javax.swing.JLabel lbl25;
    private javax.swing.JLabel lbl26;
    private javax.swing.JLabel lbl27;
    private javax.swing.JLabel lbl28;
    private javax.swing.JLabel lbl29;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl30;
    private javax.swing.JLabel lbl31;
    private javax.swing.JLabel lbl32;
    private javax.swing.JLabel lbl33;
    private javax.swing.JLabel lbl34;
    private javax.swing.JLabel lbl35;
    private javax.swing.JLabel lbl36;
    private javax.swing.JLabel lbl37;
    private javax.swing.JLabel lbl38;
    private javax.swing.JLabel lbl39;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl40;
    private javax.swing.JLabel lbl41;
    private javax.swing.JLabel lbl42;
    private javax.swing.JLabel lbl43;
    private javax.swing.JLabel lbl44;
    private javax.swing.JLabel lbl45;
    private javax.swing.JLabel lbl46;
    private javax.swing.JLabel lbl47;
    private javax.swing.JLabel lbl48;
    private javax.swing.JLabel lbl49;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl50;
    private javax.swing.JLabel lbl51;
    private javax.swing.JLabel lbl52;
    private javax.swing.JLabel lbl53;
    private javax.swing.JLabel lbl54;
    private javax.swing.JLabel lbl55;
    private javax.swing.JLabel lbl56;
    private javax.swing.JLabel lbl57;
    private javax.swing.JLabel lbl58;
    private javax.swing.JLabel lbl59;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl60;
    private javax.swing.JLabel lbl61;
    private javax.swing.JLabel lbl62;
    private javax.swing.JLabel lbl63;
    private javax.swing.JLabel lbl64;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lbl9;
    private javax.swing.JLabel lbl_fondo;
    // End of variables declaration//GEN-END:variables
}
