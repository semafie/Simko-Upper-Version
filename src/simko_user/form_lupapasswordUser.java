
package simko_user;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import simko_fiks.config;


public class form_lupapasswordUser extends javax.swing.JFrame {

    
    public form_lupapasswordUser() {
        initComponents();
        addplaceholderstyle(txtf_idPenghuni);
        addplaceholderstyle(txtf_pertanyaan);
        addplaceholderstyle1(txtf_pass);
        addplaceholderstyle1(txtf_pass1);
        this.unhide.setVisible(false);
        this.unhide1.setVisible(false);
        
        pop_up_ubahPass.setVisible(false);
        pop_up_ubahPass.setBackground(new Color(0,0,0,0));
    }

    public void addplaceholderstyle(JTextField textField){
        Font font= textField.getFont();
        font = font.deriveFont(Font.PLAIN);
        textField.setFont(font);
        textField.setForeground(Color.gray);
        
    }
    public void addplaceholderstyle1(JTextField textField){
        Font font= textField.getFont();
        font = font.deriveFont(Font.PLAIN);
        textField.setFont(font);
        textField.setForeground(Color.black);
        
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

        pop_up_ubahPass = new javax.swing.JPanel();
        btn_oke = new javax.swing.JLabel();
        btnX = new javax.swing.JLabel();
        bg_ubahPass = new javax.swing.JLabel();
        txtf_idPenghuni = new javax.swing.JTextField();
        txtf_pertanyaan = new javax.swing.JTextField();
        txtf_pass = new javax.swing.JPasswordField();
        txtf_pass1 = new javax.swing.JPasswordField();
        unhide = new javax.swing.JLabel();
        hide = new javax.swing.JLabel();
        unhide1 = new javax.swing.JLabel();
        hide1 = new javax.swing.JLabel();
        btn_batal = new javax.swing.JLabel();
        btn_ubah = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        pop_up_ubahPass.setBackground(new java.awt.Color(0, 0, 0));
        pop_up_ubahPass.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_oke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_okeMouseClicked(evt);
            }
        });
        pop_up_ubahPass.add(btn_oke, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 575, 120, 35));

        btnX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXMouseClicked(evt);
            }
        });
        pop_up_ubahPass.add(btnX, new org.netbeans.lib.awtextra.AbsoluteConstraints(1161, 429, 31, 31));

        bg_ubahPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup_berhasilUbahPass.png"))); // NOI18N
        pop_up_ubahPass.add(bg_ubahPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 418, 511, 243));

        getContentPane().add(pop_up_ubahPass);
        pop_up_ubahPass.setBounds(0, 0, 1920, 1080);

        txtf_idPenghuni.setBackground(new Color(0,0,0,0));
        txtf_idPenghuni.setFont(new java.awt.Font("Microsoft Tai Le", 0, 36)); // NOI18N
        txtf_idPenghuni.setForeground(new java.awt.Color(153, 153, 153));
        txtf_idPenghuni.setText("masukkan ID Penghuni anda");
        txtf_idPenghuni.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtf_idPenghuni.setOpaque(false);
        txtf_idPenghuni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtf_idPenghuniFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtf_idPenghuniFocusLost(evt);
            }
        });
        txtf_idPenghuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_idPenghuniActionPerformed(evt);
            }
        });
        getContentPane().add(txtf_idPenghuni);
        txtf_idPenghuni.setBounds(680, 340, 640, 70);

        txtf_pertanyaan.setBackground(new Color(0,0,0,0));
        txtf_pertanyaan.setFont(new java.awt.Font("Microsoft Tai Le", 0, 36)); // NOI18N
        txtf_pertanyaan.setForeground(new java.awt.Color(153, 153, 153));
        txtf_pertanyaan.setText("Masukkan PIN anda");
        txtf_pertanyaan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtf_pertanyaan.setOpaque(false);
        txtf_pertanyaan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtf_pertanyaanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtf_pertanyaanFocusLost(evt);
            }
        });
        txtf_pertanyaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_pertanyaanActionPerformed(evt);
            }
        });
        getContentPane().add(txtf_pertanyaan);
        txtf_pertanyaan.setBounds(680, 470, 640, 70);

        txtf_pass.setBackground(new Color(0,0,0,0));
        txtf_pass.setFont(new java.awt.Font("Microsoft Tai Le", 0, 36)); // NOI18N
        txtf_pass.setBorder(null);
        txtf_pass.setOpaque(false);
        txtf_pass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtf_passFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtf_passFocusLost(evt);
            }
        });
        txtf_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_passActionPerformed(evt);
            }
        });
        getContentPane().add(txtf_pass);
        txtf_pass.setBounds(680, 610, 610, 70);

        txtf_pass1.setBackground(new Color(0,0,0,0));
        txtf_pass1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 36)); // NOI18N
        txtf_pass1.setBorder(null);
        txtf_pass1.setOpaque(false);
        txtf_pass1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtf_pass1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtf_pass1FocusLost(evt);
            }
        });
        txtf_pass1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_pass1ActionPerformed(evt);
            }
        });
        getContentPane().add(txtf_pass1);
        txtf_pass1.setBounds(680, 750, 610, 70);

        unhide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/unhide.png"))); // NOI18N
        unhide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                unhideMousePressed(evt);
            }
        });
        getContentPane().add(unhide);
        unhide.setBounds(1290, 610, 60, 70);

        hide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hide.png"))); // NOI18N
        hide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hideMousePressed(evt);
            }
        });
        getContentPane().add(hide);
        hide.setBounds(1290, 620, 60, 54);

        unhide1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/unhide.png"))); // NOI18N
        unhide1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                unhide1MousePressed(evt);
            }
        });
        getContentPane().add(unhide1);
        unhide1.setBounds(1290, 750, 60, 70);

        hide1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hide.png"))); // NOI18N
        hide1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hide1MousePressed(evt);
            }
        });
        getContentPane().add(hide1);
        hide1.setBounds(1290, 760, 60, 54);

        btn_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn batal press.png"))); // NOI18N
        btn_batal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_batalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_batalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_batalMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_batalMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_batalMouseReleased(evt);
            }
        });
        getContentPane().add(btn_batal);
        btn_batal.setBounds(660, 890, 284, 80);

        btn_ubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn ubah normal.png"))); // NOI18N
        btn_ubah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ubahMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ubahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ubahMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ubahMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_ubahMouseReleased(evt);
            }
        });
        getContentPane().add(btn_ubah);
        btn_ubah.setBounds(970, 890, 290, 80);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Lupa password.png"))); // NOI18N
        getContentPane().add(bg);
        bg.setBounds(0, 0, 1980, 1070);

        setSize(new java.awt.Dimension(1998, 1127));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtf_idPenghuniFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtf_idPenghuniFocusGained
        if(txtf_idPenghuni.getText().equals("masukkan ID Penghuni anda")){
            txtf_idPenghuni.setText(null);
            txtf_idPenghuni.requestFocus();
            removeplaceholderstyle(txtf_idPenghuni);
        }
    }//GEN-LAST:event_txtf_idPenghuniFocusGained

    private void txtf_idPenghuniFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtf_idPenghuniFocusLost
        if(txtf_idPenghuni.getText().length()==0){

            addplaceholderstyle(txtf_idPenghuni);
            txtf_idPenghuni.setText("masukkan ID Penghuni anda");
        }
    }//GEN-LAST:event_txtf_idPenghuniFocusLost

    private void txtf_idPenghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_idPenghuniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_idPenghuniActionPerformed

    private void txtf_pertanyaanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtf_pertanyaanFocusGained
        if(txtf_pertanyaan.getText().equals("Masukkan PIN anda")){
            txtf_pertanyaan.setText(null);
            txtf_pertanyaan.requestFocus();
            removeplaceholderstyle(txtf_pertanyaan);
        }
    }//GEN-LAST:event_txtf_pertanyaanFocusGained

    private void txtf_pertanyaanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtf_pertanyaanFocusLost
        if(txtf_pertanyaan.getText().length()==0){

            addplaceholderstyle(txtf_pertanyaan);
            txtf_pertanyaan.setText("Masukkan PIN anda");
        }
    }//GEN-LAST:event_txtf_pertanyaanFocusLost

    private void txtf_pertanyaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_pertanyaanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_pertanyaanActionPerformed

    private void txtf_passFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtf_passFocusGained
        if(txtf_pass.getText().equals("masukkan password baru")){
            txtf_pass.setText(null);
            txtf_pass.requestFocus();
            removeplaceholderstyle(txtf_pass);
        }
    }//GEN-LAST:event_txtf_passFocusGained

    private void txtf_passFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtf_passFocusLost
        if(txtf_pass.getText().length()==0){

            addplaceholderstyle(txtf_pass);
            txtf_pass.setText("masukkan password baru");
        }
    }//GEN-LAST:event_txtf_passFocusLost

    private void txtf_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_passActionPerformed

    private void txtf_pass1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtf_pass1FocusGained
        if(txtf_pass1.getText().equals("confirm password baru")){
            txtf_pass1.setText(null);
            txtf_pass1.requestFocus();
            removeplaceholderstyle(txtf_pass);
        }
    }//GEN-LAST:event_txtf_pass1FocusGained

    private void txtf_pass1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtf_pass1FocusLost
        if(txtf_pass1.getText().length()==0){

            addplaceholderstyle(txtf_pass1);
            txtf_pass1.setText("confirm password baru");
        }
    }//GEN-LAST:event_txtf_pass1FocusLost

    private void txtf_pass1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_pass1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_pass1ActionPerformed

    private void hideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hideMousePressed
        unhide.setVisible(true);
        hide.setVisible(false);
        txtf_pass.setEchoChar((char)0);
    }//GEN-LAST:event_hideMousePressed

    private void unhideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unhideMousePressed
        hide.setVisible(true);
        unhide.setVisible(false);
        txtf_pass.setEchoChar('*');
    }//GEN-LAST:event_unhideMousePressed

    private void unhide1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unhide1MousePressed
        hide1.setVisible(true);
        unhide1.setVisible(false);
        txtf_pass1.setEchoChar('*');
    }//GEN-LAST:event_unhide1MousePressed

    private void hide1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hide1MousePressed
        unhide1.setVisible(true);
        hide1.setVisible(false);
        txtf_pass1.setEchoChar((char)0);
    }//GEN-LAST:event_hide1MousePressed

    private void btn_ubahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ubahMouseClicked
        if(txtf_pass.getText().equals("")){
                JOptionPane.showMessageDialog(null, "password baru tidak boleh kosong");
            }else if((txtf_pass1.getText().equals(""))){
                JOptionPane.showMessageDialog(null, "confirm password baru tidak boleh kosong");
            }else if(txtf_idPenghuni.getText().equals("")){
            JOptionPane.showMessageDialog(null, "kode penghuni tidak boleh kosong");  
            }
            else if(txtf_pass1.getText().equals(txtf_pass.getText())) {
                try{
                    java.sql.Connection conn = (Connection)config.configDB();
                    java.sql.Statement stm = conn.createStatement();
                    ResultSet rs = stm.executeQuery("select * from tb_user where id_penghuni = '"
                            +txtf_idPenghuni.getText()+"' AND pin = "+txtf_pertanyaan.getText()+"");
                        if(rs.next()){
                           try{
                               String sql = "update tb_user set password = '"+ txtf_pass.getText()
                                       +"' where id_penghuni = '" + txtf_idPenghuni.getText()
                                       +"' and pin ="+txtf_pertanyaan.getText()+"";
                               java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                                pst.execute();
                                pop_up_ubahPass.setVisible(true); 
                           }catch(Exception e){
                               JOptionPane.showMessageDialog(null, "gagal ubah password");
                           }
                        }else{
                            JOptionPane.showMessageDialog(null, "kode penghuni atau pin tidak sesuai");
                        }
        //            pop_up_ubahPass.setVisible(true);
                    }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "kode penghuni salah" + e);
                    }
            }
            else{ JOptionPane.showMessageDialog(null, "masukkan kode penghuni, pin dan password secara benar");
            }
    }//GEN-LAST:event_btn_ubahMouseClicked

    private void btn_batalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_batalMouseClicked
        this.setVisible(false);
        new form_loginUser().setVisible(true);
    }//GEN-LAST:event_btn_batalMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(form_lupapasswordUser.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void btn_ubahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ubahMouseEntered
        btn_ubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn ubah entered.png")));
    }//GEN-LAST:event_btn_ubahMouseEntered

    private void btn_ubahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ubahMouseExited
        btn_ubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn ubah normal.png")));
    }//GEN-LAST:event_btn_ubahMouseExited

    private void btn_ubahMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ubahMousePressed
        btn_ubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn ubah press.png")));
    }//GEN-LAST:event_btn_ubahMousePressed

    private void btn_ubahMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ubahMouseReleased
        btn_ubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn ubah entered.png")));
    }//GEN-LAST:event_btn_ubahMouseReleased

    private void btn_batalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_batalMouseEntered
        btn_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn batal entered.png")));
    }//GEN-LAST:event_btn_batalMouseEntered

    private void btn_batalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_batalMouseExited
        btn_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn batal press.png")));
    }//GEN-LAST:event_btn_batalMouseExited

    private void btn_batalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_batalMousePressed
        btn_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn batal normal.png")));
    }//GEN-LAST:event_btn_batalMousePressed

    private void btn_batalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_batalMouseReleased
        btn_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn batal entered.png")));
    }//GEN-LAST:event_btn_batalMouseReleased

    private void btn_okeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_okeMouseClicked
        // TODO add your handling code here:
        pop_up_ubahPass.setVisible(false);
        new form_loginUser().setVisible(true);
    }//GEN-LAST:event_btn_okeMouseClicked

    private void btnXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXMouseClicked
        // TODO add your handling code here:
        pop_up_ubahPass.setVisible(false);
        new form_loginUser().setVisible(true);
    }//GEN-LAST:event_btnXMouseClicked

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
            java.util.logging.Logger.getLogger(form_lupapasswordUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_lupapasswordUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_lupapasswordUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_lupapasswordUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_lupapasswordUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_ubahPass;
    private javax.swing.JLabel btnX;
    private javax.swing.JLabel btn_batal;
    private javax.swing.JLabel btn_oke;
    private javax.swing.JLabel btn_ubah;
    private javax.swing.JLabel hide;
    private javax.swing.JLabel hide1;
    private javax.swing.JPanel pop_up_ubahPass;
    private javax.swing.JTextField txtf_idPenghuni;
    private javax.swing.JPasswordField txtf_pass;
    private javax.swing.JPasswordField txtf_pass1;
    private javax.swing.JTextField txtf_pertanyaan;
    private javax.swing.JLabel unhide;
    private javax.swing.JLabel unhide1;
    // End of variables declaration//GEN-END:variables
}
