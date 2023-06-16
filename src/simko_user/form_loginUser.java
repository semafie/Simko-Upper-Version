package simko_user;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import simko_fiks.config;
import static simko_user.beranda_user.lblnama;
import static simko_user.beranda_user.lblnama1;
import static simko_user.form_historyUser.lbl_idpenghuni;
import static simko_user.form_historyUser.lbl_nama2;
import static simko_user.form_komplainUser.lbl_nama3;
import static simko_user.form_komplainUser.lbl_noKamar;


public class form_loginUser extends javax.swing.JFrame {

    
    public form_loginUser() {
        initComponents();
        addplaceholderstyle(txtfkodePenghuni);
        this.unhide.setVisible(false);
    }

    public void addplaceholderstyle(JTextField textField){
        Font font= textField.getFont();
        font = font.deriveFont(Font.PLAIN);
        textField.setFont(font);
        textField.setForeground(Color.gray);
    }
    
    public void removeplaceholderstyle (JTextField textField) {
        Font font= textField.getFont();
        font = font.deriveFont(Font.PLAIN);
        textField.setFont(font);
        textField.setForeground(Color.black);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtfpass = new javax.swing.JPasswordField();
        lblLP = new javax.swing.JLabel();
        lbllogin = new javax.swing.JLabel();
        unhide = new javax.swing.JLabel();
        hide = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        txtfkodePenghuni.setFont(new java.awt.Font("Microsoft Tai Le", 0, 36)); // NOI18N
        txtfkodePenghuni.setForeground(new java.awt.Color(153, 153, 153));
        txtfkodePenghuni.setText("Masukkan ID Penghuni anda");
        txtfkodePenghuni.setBorder(null);
        txtfkodePenghuni.setOpaque(false);
        txtfkodePenghuni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtfkodePenghuniFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtfkodePenghuniFocusLost(evt);
            }
        });
        getContentPane().add(txtfkodePenghuni);
        txtfkodePenghuni.setBounds(1170, 490, 550, 60);

        txtfpass.setFont(new java.awt.Font("Microsoft Tai Le", 0, 36)); // NOI18N
        txtfpass.setBorder(null);
        txtfpass.setOpaque(false);
        txtfpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfpassActionPerformed(evt);
            }
        });
        getContentPane().add(txtfpass);
        txtfpass.setBounds(1160, 620, 540, 50);

        lblLP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLPMouseClicked(evt);
            }
        });
        getContentPane().add(lblLP);
        lblLP.setBounds(1560, 700, 220, 40);

        lbllogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn masuk normal.png"))); // NOI18N
        lbllogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblloginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblloginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblloginMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblloginMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblloginMouseReleased(evt);
            }
        });
        getContentPane().add(lbllogin);
        lbllogin.setBounds(1290, 760, 280, 90);

        unhide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/unhide.png"))); // NOI18N
        unhide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                unhideMousePressed(evt);
            }
        });
        getContentPane().add(unhide);
        unhide.setBounds(1720, 610, 60, 70);

        hide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hide.png"))); // NOI18N
        hide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hideMousePressed(evt);
            }
        });
        getContentPane().add(hide);
        hide.setBounds(1720, 620, 60, 54);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/form_loginUser1.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1980, 1080);

        setSize(new java.awt.Dimension(1998, 1127));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtfkodePenghuniFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfkodePenghuniFocusGained
        if(txtfkodePenghuni.getText().equals("Masukkan ID Penghuni anda")){
            txtfkodePenghuni.setText(null);
            txtfkodePenghuni.requestFocus();
            removeplaceholderstyle(txtfkodePenghuni);
        }
    }//GEN-LAST:event_txtfkodePenghuniFocusGained

    private void txtfkodePenghuniFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfkodePenghuniFocusLost
        if(txtfkodePenghuni.getText().length()==0){

            addplaceholderstyle(txtfkodePenghuni);
            txtfkodePenghuni.setText("Masukkan ID Penghuni anda");
        }
    }//GEN-LAST:event_txtfkodePenghuniFocusLost

    private void txtfpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfpassActionPerformed

    }//GEN-LAST:event_txtfpassActionPerformed

    private void lblLPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLPMouseClicked
        this.setVisible(false);
        new form_lupapasswordUser().setVisible(true);
    }//GEN-LAST:event_lblLPMouseClicked

    private void lblloginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblloginMouseClicked
        try {
            String sql = "SELECT * FROM tb_user "
            + "where id_penghuni = '"+txtfkodePenghuni.getText()+"' "
            + "AND password = '"+txtfpass.getText()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);

            if(rs.next()){
                if(txtfkodePenghuni.getText().equals(rs.getString("id_penghuni"))
                    && txtfpass.getText().equals(rs.getString("password"))){
                    this.setVisible(false);
                    
                this.setVisible(false);
                new beranda_user().setVisible(true);
                lbl_idpenghuni.setText(rs.getString(1));
                lblnama.setText(rs.getString(2));
                lblnama1.setText(rs.getString(2));
                lbl_nama2.setText(rs.getString(2));
                lbl_nama3.setText(rs.getString(2));
                lbl_noKamar.setText(rs.getString(3));
                }
            }else{
                JOptionPane.showMessageDialog(null, "email atau password salah");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_lblloginMouseClicked

    private void unhideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unhideMousePressed
        hide.setVisible(true);
        unhide.setVisible(false);
        txtfpass.setEchoChar('*');
    }//GEN-LAST:event_unhideMousePressed

    private void hideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hideMousePressed
        unhide.setVisible(true);
        hide.setVisible(false);
        txtfpass.setEchoChar((char)0);
    }//GEN-LAST:event_hideMousePressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(form_loginUser.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void lblloginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblloginMouseEntered
        lbllogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn masuk entered.png")));
    }//GEN-LAST:event_lblloginMouseEntered

    private void lblloginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblloginMouseExited
        lbllogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn masuk normal.png")));
    }//GEN-LAST:event_lblloginMouseExited

    private void lblloginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblloginMousePressed
        lbllogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn masuk press.png")));
    }//GEN-LAST:event_lblloginMousePressed

    private void lblloginMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblloginMouseReleased
        lbllogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn masuk entered.png")));
    }//GEN-LAST:event_lblloginMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_loginUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel hide;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblLP;
    private javax.swing.JLabel lbllogin;
    public static final javax.swing.JTextField txtfkodePenghuni = new javax.swing.JTextField();
    private javax.swing.JPasswordField txtfpass;
    private javax.swing.JLabel unhide;
    // End of variables declaration//GEN-END:variables
}
