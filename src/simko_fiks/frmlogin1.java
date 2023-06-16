package simko_fiks;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
//import static simko_fiks.FormKamar.lblnama3;
import static simko_fiks.beranda.lblnama;
import static simko_fiks.beranda.lblnama1;
import static simko_fiks.form_akunPenghuni.lblnama7;
import static simko_fiks.form_editTagihan.lblnama4_1;
import static simko_fiks.form_komplain.lblnama6;
//import static simko_fiks.form_penghuni.lblnama2;
import static simko_fiks.form_tagihan.lblnama4;
import static simko_fiks.form_laporan.lblnama5;



public class frmlogin1 extends javax.swing.JFrame {

    
    public frmlogin1() {
        initComponents();
        addplaceholderstyle(txtfusername);
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

        txtfusername = new javax.swing.JTextField();
        txtfpass = new javax.swing.JPasswordField();
        lbllogin = new javax.swing.JLabel();
        lbldaftar = new javax.swing.JLabel();
        lblLP = new javax.swing.JLabel();
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

        txtfusername.setFont(new java.awt.Font("Microsoft Tai Le", 0, 36)); // NOI18N
        txtfusername.setForeground(new java.awt.Color(153, 153, 153));
        txtfusername.setText("Masukkan EMAIL");
        txtfusername.setBorder(null);
        txtfusername.setOpaque(false);
        txtfusername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtfusernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtfusernameFocusLost(evt);
            }
        });
        txtfusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfusernameActionPerformed(evt);
            }
        });
        getContentPane().add(txtfusername);
        txtfusername.setBounds(1180, 490, 550, 60);

        txtfpass.setFont(new java.awt.Font("Microsoft Tai Le", 0, 36)); // NOI18N
        txtfpass.setBorder(null);
        txtfpass.setOpaque(false);
        txtfpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfpassActionPerformed(evt);
            }
        });
        getContentPane().add(txtfpass);
        txtfpass.setBounds(1180, 620, 500, 60);

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
        lbllogin.setBounds(1300, 760, 270, 90);

        lbldaftar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldaftarMouseClicked(evt);
            }
        });
        getContentPane().add(lbldaftar);
        lbldaftar.setBounds(190, 600, 310, 100);

        lblLP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLPMouseClicked(evt);
            }
        });
        getContentPane().add(lblLP);
        lblLP.setBounds(1530, 710, 240, 40);

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/form_loginAdmin1.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1920, 1080);

        setSize(new java.awt.Dimension(1938, 1127));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbldaftarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldaftarMouseClicked
//        this.setVisible(false);
//        new frmregister().setVisible(true);
    }//GEN-LAST:event_lbldaftarMouseClicked

    private void lblloginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblloginMouseClicked
        try {
            String sql = "SELECT * FROM tb_login "
                    + "where email = '"+txtfusername.getText()+"' "
                    + "AND passwords = '"+txtfpass.getText()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
        
        if(rs.next()){
                if(txtfusername.getText().equals(rs.getString("email"))
                        && txtfpass.getText().equals(rs.getString("passwords"))){
                    this.setVisible(false);
                    new beranda().setVisible(true);
                    lblnama.setText(rs.getString(2));
                    lblnama1.setText(rs.getString(2));
//                    lblnama2.setText(rs.getString(2));
                    lblnama4_1.setText(rs.getString(2));
                    lblnama4.setText(rs.getString(2));
                    lblnama5.setText(rs.getString(2));
                    lblnama6.setText(rs.getString(2));
                    lblnama7.setText(rs.getString(2));
                    
                }
        }else{
            JOptionPane.showMessageDialog(null, "email atau password salah");
        }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_lblloginMouseClicked

    private void txtfpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfpassActionPerformed
       
    }//GEN-LAST:event_txtfpassActionPerformed

    private void txtfusernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfusernameFocusGained
        if(txtfusername.getText().equals("Masukkan EMAIL")){
            txtfusername.setText(null);
            txtfusername.requestFocus();
            removeplaceholderstyle(txtfusername);
        }
    }//GEN-LAST:event_txtfusernameFocusGained

    private void txtfusernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfusernameFocusLost
        if(txtfusername.getText().length()==0){

            addplaceholderstyle(txtfusername);
            txtfusername.setText("Masukkan EMAIL");
        }
    }//GEN-LAST:event_txtfusernameFocusLost

    private void lblLPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLPMouseClicked
        this.setVisible(false);
        new lupapassword1().setVisible(true);
    }//GEN-LAST:event_lblLPMouseClicked

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
        setExtendedState(frmlogin1.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void txtfusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfusernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfusernameActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmlogin1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel hide;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblLP;
    private javax.swing.JLabel lbldaftar;
    private javax.swing.JLabel lbllogin;
    private javax.swing.JPasswordField txtfpass;
    private javax.swing.JTextField txtfusername;
    private javax.swing.JLabel unhide;
    // End of variables declaration//GEN-END:variables
}
