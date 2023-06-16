/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simko_user;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import simko_fiks.config;

/**
 *
 * @author Farhan Ariyanto
 */
public class beranda_user extends javax.swing.JFrame {

    
    public beranda_user() {
        initComponents();
        Tampil_jam();
        Tampil_tanggal();
        jumlahtagihan();
        jumlahkomplain();
        totalpendapatan();
        
        logout.setVisible(false);
        logout.setBackground(new Color(0,0,0,200));
    }

    public void Tampil_jam(){
        ActionListener taskPerformer = new ActionListener() {
            @Override
           public void actionPerformed(ActionEvent evt){
               String nol_jam = "", nol_menit = "", nol_detik = "";
               
              java.util.Date dateTime = new java.util.Date();
              int nilai_jam = dateTime.getHours();
              int nilai_menit = dateTime.getMinutes();
              int nilai_detik = dateTime.getSeconds();
              
              if(nilai_jam <= 9 ) nol_jam = "0";
              if(nilai_menit <= 9 ) nol_menit = "0";
              if(nilai_detik <= 9 ) nol_detik = "0";
              
              String jam = nol_jam + Integer.toString(nilai_jam);
              String menit = nol_menit + Integer.toString(nilai_menit);
              String detik = nol_detik + Integer.toString(nilai_detik);
              
              lbl_jam.setText(jam + ":" + menit + ":" + detik + "");              
           }
        };
        new Timer(1000, taskPerformer).start();
    }
    
    public void Tampil_tanggal(){
        java.util.Date tglskrng = new java.util.Date();
        SimpleDateFormat smpdtfrmt = new SimpleDateFormat("dd MMMM YYY", Locale.getDefault());
        String tanggal =  smpdtfrmt.format(tglskrng);
        lbl_tanggal.setText(tanggal);
    }
    
    public void jumlahtagihan(){
        try{
            java.sql.Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery("select count(*) as jumlah from tb_history_tagihan "
                    + "where id_penghuni = '"+form_loginUser.txtfkodePenghuni.getText()+"'");
            
            while (result.next()) {
     
                jmlhtotalTransaksi.setText(result.getString("jumlah"));
            }
             
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    public void jumlahkomplain(){
        try{
            java.sql.Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery("select count(*) as jumlah from tb_komplain where id_penghuni = '"+form_loginUser.txtfkodePenghuni.getText()+"'");
            
            while (result.next()) {
     
                jmlhtotalLaporan.setText(result.getString("jumlah"));
            }
             
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    
    public static String formatDec(int val) {
        return String.format("%,d", val).replace(',', '.');
    }
    
    public void totalpendapatan(){
        try{
            java.sql.Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery("select sum(bayar) as jumlah from tb_tagihan where id_penghuni = '"+form_loginUser.txtfkodePenghuni.getText()+"' AND status = 'Lunas'");
            
            while (result.next()) {
     
//                lbltotalpendapatan.setText(result.getString("jumlah"));
            jmlhtotalTagihan.setText("Rp "+formatDec(result.getInt("jumlah")));
            }
             
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jmlhtotalTransaksi = new javax.swing.JLabel();
        jmlhtotalLaporan = new javax.swing.JLabel();
        jmlhtotalTagihan = new javax.swing.JLabel();
        btn_beranda = new javax.swing.JLabel();
        btn_history = new javax.swing.JLabel();
        link_keluar = new javax.swing.JLabel();
        btn_komplain = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jam_icon = new javax.swing.JLabel();
        lbl_jam = new javax.swing.JLabel();
        tanggal_icon = new javax.swing.JLabel();
        lbl_tanggal = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        logout = new javax.swing.JPanel();
        btnX = new javax.swing.JLabel();
        btn_keluar = new javax.swing.JLabel();
        btn_logout = new javax.swing.JLabel();
        bg_logout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jmlhtotalTransaksi.setFont(new java.awt.Font("Arial", 1, 80)); // NOI18N
        jmlhtotalTransaksi.setForeground(new java.awt.Color(0, 153, 153));
        jmlhtotalTransaksi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jmlhtotalTransaksi.setText(".");
        jmlhtotalTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jmlhtotalTransaksiMouseClicked(evt);
            }
        });
        getContentPane().add(jmlhtotalTransaksi);
        jmlhtotalTransaksi.setBounds(650, 320, 130, 80);

        jmlhtotalLaporan.setFont(new java.awt.Font("Arial", 1, 80)); // NOI18N
        jmlhtotalLaporan.setForeground(new java.awt.Color(0, 153, 153));
        jmlhtotalLaporan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jmlhtotalLaporan.setText(".");
        jmlhtotalLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jmlhtotalLaporanMouseClicked(evt);
            }
        });
        getContentPane().add(jmlhtotalLaporan);
        jmlhtotalLaporan.setBounds(880, 310, 130, 90);

        jmlhtotalTagihan.setFont(new java.awt.Font("Arial", 1, 40)); // NOI18N
        jmlhtotalTagihan.setForeground(new java.awt.Color(0, 153, 153));
        jmlhtotalTagihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jmlhtotalTagihan.setText(".");
        jmlhtotalTagihan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jmlhtotalTagihanMouseClicked(evt);
            }
        });
        getContentPane().add(jmlhtotalTagihan);
        jmlhtotalTagihan.setBounds(1290, 330, 280, 90);

        btn_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarUser/tab beranda normal.png"))); // NOI18N
        btn_beranda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_berandaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_berandaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_berandaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_berandaMouseReleased(evt);
            }
        });
        getContentPane().add(btn_beranda);
        btn_beranda.setBounds(-40, 257, 353, 69);

        btn_history.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarUser/tab history none.png"))); // NOI18N
        btn_history.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_historyMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_historyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_historyMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_historyMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_historyMouseReleased(evt);
            }
        });
        getContentPane().add(btn_history);
        btn_history.setBounds(-40, 328, 353, 69);

        link_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab keluar none.png"))); // NOI18N
        link_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                link_keluarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                link_keluarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                link_keluarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                link_keluarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                link_keluarMouseReleased(evt);
            }
        });
        getContentPane().add(link_keluar);
        link_keluar.setBounds(-76, 960, 382, 74);

        btn_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarUser/tab komplain none.png"))); // NOI18N
        btn_komplain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_komplainMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_komplainMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_komplainMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_komplainMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_komplainMouseReleased(evt);
            }
        });
        getContentPane().add(btn_komplain);
        btn_komplain.setBounds(-40, 400, 353, 69);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tutupan.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(241, 950, 126, 96);

        lblnama.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        lblnama.setForeground(new java.awt.Color(0, 153, 153));
        lblnama.setText(".");
        getContentPane().add(lblnama);
        lblnama.setBounds(1090, 570, 270, 50);

        lblnama1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        lblnama1.setForeground(new java.awt.Color(102, 102, 102));
        getContentPane().add(lblnama1);
        lblnama1.setBounds(1750, 40, 130, 40);

        jam_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clock icon.png"))); // NOI18N
        getContentPane().add(jam_icon);
        jam_icon.setBounds(1170, 40, 40, 40);

        lbl_jam.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        lbl_jam.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lbl_jam);
        lbl_jam.setBounds(1230, 40, 110, 40);

        tanggal_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/calendar  icon.png"))); // NOI18N
        getContentPane().add(tanggal_icon);
        tanggal_icon.setBounds(1369, 40, 40, 40);

        lbl_tanggal.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        lbl_tanggal.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lbl_tanggal);
        lbl_tanggal.setBounds(1420, 40, 230, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/beranda_user.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -4, 1980, 1080);

        logout.setBackground(new java.awt.Color(0, 0, 0));
        logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXMouseClicked(evt);
            }
        });
        logout.add(btnX, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 431, 40, 30));

        btn_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_keluarMouseClicked(evt);
            }
        });
        logout.add(btn_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 575, 130, 40));

        btn_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_logoutMouseClicked(evt);
            }
        });
        logout.add(btn_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 575, 110, 40));

        bg_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pop up keluar2.png"))); // NOI18N
        logout.add(bg_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 418, -1, -1));

        getContentPane().add(logout);
        logout.setBounds(0, 0, 1920, 1080);

        setSize(new java.awt.Dimension(1920, 1080));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jmlhtotalLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmlhtotalLaporanMouseClicked

    }//GEN-LAST:event_jmlhtotalLaporanMouseClicked

    private void jmlhtotalTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmlhtotalTransaksiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jmlhtotalTransaksiMouseClicked

    private void btn_historyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_historyMouseClicked
        this.setVisible(false);
        new form_historyUser().setVisible(true);
    }//GEN-LAST:event_btn_historyMouseClicked

    private void btn_komplainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_komplainMouseClicked
        this.setVisible(false);
        new form_komplainUser().setVisible(true);
    }//GEN-LAST:event_btn_komplainMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(beranda_user.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void btn_berandaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_berandaMouseEntered
        btn_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarUser/tab beranda entered.png")));
    }//GEN-LAST:event_btn_berandaMouseEntered

    private void btn_berandaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_berandaMouseExited
        btn_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarUser/tab beranda normal.png")));
    }//GEN-LAST:event_btn_berandaMouseExited

    private void btn_berandaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_berandaMousePressed
        btn_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarUser/tab beranda press.png")));
    }//GEN-LAST:event_btn_berandaMousePressed

    private void btn_berandaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_berandaMouseReleased
        btn_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarUser/tab beranda entered.png")));
    }//GEN-LAST:event_btn_berandaMouseReleased

    private void btn_historyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_historyMouseEntered
         btn_history.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarUser/tab history entered.png")));
    }//GEN-LAST:event_btn_historyMouseEntered

    private void btn_historyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_historyMouseExited
         btn_history.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarUser/tab history none.png")));
    }//GEN-LAST:event_btn_historyMouseExited

    private void btn_historyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_historyMousePressed
         btn_history.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarUser/tab history press.png")));
    }//GEN-LAST:event_btn_historyMousePressed

    private void btn_historyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_historyMouseReleased
         btn_history.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarUser/tab history entered.png")));
    }//GEN-LAST:event_btn_historyMouseReleased

    private void btn_komplainMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_komplainMouseEntered
        btn_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarUser/tab komplain entered.png")));
    }//GEN-LAST:event_btn_komplainMouseEntered

    private void btn_komplainMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_komplainMouseExited
        btn_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarUser/tab komplain none.png")));
    }//GEN-LAST:event_btn_komplainMouseExited

    private void btn_komplainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_komplainMousePressed
        btn_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarUser/tab komplain press.png")));
    }//GEN-LAST:event_btn_komplainMousePressed

    private void btn_komplainMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_komplainMouseReleased
        btn_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarUser/tab komplain entered.png")));
    }//GEN-LAST:event_btn_komplainMouseReleased

    private void link_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_keluarMouseClicked
        logout.setVisible(true);
    }//GEN-LAST:event_link_keluarMouseClicked

    private void link_keluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_keluarMouseEntered
        link_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab keluar entered.png")));
    }//GEN-LAST:event_link_keluarMouseEntered

    private void link_keluarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_keluarMouseExited
        link_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab keluar none.png")));
    }//GEN-LAST:event_link_keluarMouseExited

    private void link_keluarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_keluarMousePressed
        link_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab keluar press.png")));
    }//GEN-LAST:event_link_keluarMousePressed

    private void link_keluarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_keluarMouseReleased
        link_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab keluar entered.png")));
    }//GEN-LAST:event_link_keluarMouseReleased

    private void btnXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXMouseClicked
        // TODO add your handling code here:
        logout.setVisible(false);
    }//GEN-LAST:event_btnXMouseClicked

    private void btn_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_keluarMouseClicked

    private void btn_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new form_loginUser().setVisible(true);
    }//GEN-LAST:event_btn_logoutMouseClicked

    private void jmlhtotalTagihanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmlhtotalTagihanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jmlhtotalTagihanMouseClicked

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
            java.util.logging.Logger.getLogger(beranda_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(beranda_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(beranda_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(beranda_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new beranda_user().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg_logout;
    private javax.swing.JLabel btnX;
    private javax.swing.JLabel btn_beranda;
    private javax.swing.JLabel btn_history;
    private javax.swing.JLabel btn_keluar;
    private javax.swing.JLabel btn_komplain;
    private javax.swing.JLabel btn_logout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jam_icon;
    private javax.swing.JLabel jmlhtotalLaporan;
    private javax.swing.JLabel jmlhtotalTagihan;
    private javax.swing.JLabel jmlhtotalTransaksi;
    private javax.swing.JLabel lbl_jam;
    private javax.swing.JLabel lbl_tanggal;
    public static final javax.swing.JLabel lblnama = new javax.swing.JLabel();
    public static final javax.swing.JLabel lblnama1 = new javax.swing.JLabel();
    private javax.swing.JLabel link_keluar;
    private javax.swing.JPanel logout;
    private javax.swing.JLabel tanggal_icon;
    // End of variables declaration//GEN-END:variables
}
