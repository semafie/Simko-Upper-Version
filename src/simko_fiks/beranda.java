/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package simko_fiks;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import static simko_fiks.form_laporan.tabel_uatama;

/**
 *
 * @author nikof
 */
public class beranda extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    public beranda() {
        initComponents();
        Tampil_jam();
        Tampil_tanggal();
        circle.setVisible(false);
        lbl_belum_terbaca.setVisible(false);
        jumlahkomplain();
        jumlahpenghuni();
        jumlahkamar();
        jumlahtagihan();
        totalpendapatan();
        getData2();
        
        logout1.setVisible(false);
        logout1.setBackground(new Color(0,0,0,200));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_belum_terbaca = new javax.swing.JLabel();
        circle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        link_keluar = new javax.swing.JLabel();
        lbl_beranda = new javax.swing.JLabel();
        link_akunPenghuni = new javax.swing.JLabel();
        link_laporan = new javax.swing.JLabel();
        link_tagihan = new javax.swing.JLabel();
        link_penghuniKost = new javax.swing.JLabel();
        link_kamarKost = new javax.swing.JLabel();
        link_komplain = new javax.swing.JLabel();
        lbljmlhpenghuni = new javax.swing.JLabel();
        lbljmlhkamar = new javax.swing.JLabel();
        lbljmlhkomplain = new javax.swing.JLabel();
        lbljmlhtagihan = new javax.swing.JLabel();
        lbltotalpendapatan = new javax.swing.JLabel();
        lbl_tanggal = new javax.swing.JLabel();
        lbl_jam = new javax.swing.JLabel();
        jam_icon = new javax.swing.JLabel();
        tanggal_icon = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        logout1 = new javax.swing.JPanel();
        btnX1 = new javax.swing.JLabel();
        btn_keluar = new javax.swing.JLabel();
        btn_logout = new javax.swing.JLabel();
        bg_logout = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_belum_terbaca.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbl_belum_terbaca.setForeground(new java.awt.Color(255, 255, 255));
        lbl_belum_terbaca.setText("0");
        getContentPane().add(lbl_belum_terbaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 645, 70, 30));

        circle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Ellipse 4.png"))); // NOI18N
        getContentPane().add(circle, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 642, 40, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tutupan.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 951, 126, 96));

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
        getContentPane().add(link_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(-35, 960, 382, 74));

        lbl_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab beranda normal.png"))); // NOI18N
        lbl_beranda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_berandaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_berandaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_berandaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbl_berandaMouseReleased(evt);
            }
        });
        getContentPane().add(lbl_beranda, new org.netbeans.lib.awtextra.AbsoluteConstraints(-35, 270, 353, 69));

        link_akunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni none.png"))); // NOI18N
        link_akunPenghuni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                link_akunPenghuniMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                link_akunPenghuniMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                link_akunPenghuniMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                link_akunPenghuniMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                link_akunPenghuniMouseReleased(evt);
            }
        });
        getContentPane().add(link_akunPenghuni, new org.netbeans.lib.awtextra.AbsoluteConstraints(-35, 696, 353, 69));

        link_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan none.png"))); // NOI18N
        link_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                link_laporanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                link_laporanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                link_laporanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                link_laporanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                link_laporanMouseReleased(evt);
            }
        });
        getContentPane().add(link_laporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(-35, 554, 353, 69));

        link_tagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan none.png"))); // NOI18N
        link_tagihan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                link_tagihanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                link_tagihanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                link_tagihanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                link_tagihanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                link_tagihanMouseReleased(evt);
            }
        });
        getContentPane().add(link_tagihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(-35, 483, 353, 69));

        link_penghuniKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab penghuni kost none.png"))); // NOI18N
        link_penghuniKost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                link_penghuniKostMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                link_penghuniKostMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                link_penghuniKostMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                link_penghuniKostMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                link_penghuniKostMouseReleased(evt);
            }
        });
        getContentPane().add(link_penghuniKost, new org.netbeans.lib.awtextra.AbsoluteConstraints(-35, 412, 353, 69));
        link_penghuniKost.getAccessibleContext().setAccessibleDescription("");

        link_kamarKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab kamar kos none.png"))); // NOI18N
        link_kamarKost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                link_kamarKostMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                link_kamarKostMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                link_kamarKostMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                link_kamarKostMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                link_kamarKostMouseReleased(evt);
            }
        });
        getContentPane().add(link_kamarKost, new org.netbeans.lib.awtextra.AbsoluteConstraints(-35, 341, 353, 69));

        link_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain none.png"))); // NOI18N
        link_komplain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                link_komplainMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                link_komplainMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                link_komplainMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                link_komplainMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                link_komplainMouseReleased(evt);
            }
        });
        getContentPane().add(link_komplain, new org.netbeans.lib.awtextra.AbsoluteConstraints(-35, 625, 353, 69));

        lblnama1.setBackground(new java.awt.Color(255, 255, 255));
        lblnama1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 48)); // NOI18N
        lblnama1.setForeground(new java.awt.Color(44, 141, 141));
        lblnama1.setText("farhan");
        getContentPane().add(lblnama1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 620, 240, 60));

        lblnama.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N
        lblnama.setForeground(new java.awt.Color(153, 153, 153));
        lblnama.setText(".");
        getContentPane().add(lblnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(1750, 40, 140, 40));

        lbljmlhpenghuni.setFont(new java.awt.Font("Microsoft Tai Le", 1, 71)); // NOI18N
        lbljmlhpenghuni.setForeground(new java.awt.Color(44, 141, 141));
        lbljmlhpenghuni.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbljmlhpenghuni.setText("1");
        lbljmlhpenghuni.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbljmlhpenghuni, new org.netbeans.lib.awtextra.AbsoluteConstraints(457, 326, 90, 90));

        lbljmlhkamar.setFont(new java.awt.Font("Microsoft Tai Le", 1, 71)); // NOI18N
        lbljmlhkamar.setForeground(new java.awt.Color(44, 141, 141));
        lbljmlhkamar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbljmlhkamar.setText("1");
        lbljmlhkamar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbljmlhkamar, new org.netbeans.lib.awtextra.AbsoluteConstraints(693, 326, 90, 90));

        lbljmlhkomplain.setFont(new java.awt.Font("Microsoft Tai Le", 1, 71)); // NOI18N
        lbljmlhkomplain.setForeground(new java.awt.Color(44, 141, 141));
        lbljmlhkomplain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbljmlhkomplain.setText("1");
        lbljmlhkomplain.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbljmlhkomplain.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbljmlhkomplain, new org.netbeans.lib.awtextra.AbsoluteConstraints(927, 326, 90, 90));

        lbljmlhtagihan.setFont(new java.awt.Font("Microsoft Tai Le", 1, 71)); // NOI18N
        lbljmlhtagihan.setForeground(new java.awt.Color(44, 141, 141));
        lbljmlhtagihan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbljmlhtagihan.setText("1");
        lbljmlhtagihan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbljmlhtagihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 326, 90, 90));

        lbltotalpendapatan.setFont(new java.awt.Font("Microsoft Tai Le", 1, 48)); // NOI18N
        lbltotalpendapatan.setForeground(new java.awt.Color(44, 141, 141));
        lbltotalpendapatan.setText("89");
        lbltotalpendapatan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbltotalpendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 350, 300, 90));

        lbl_tanggal.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        lbl_tanggal.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lbl_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1420, 40, 230, 40));

        lbl_jam.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        lbl_jam.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lbl_jam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 40, 110, 40));

        jam_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clock icon.png"))); // NOI18N
        getContentPane().add(jam_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 40, -1, -1));

        tanggal_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/calendar  icon.png"))); // NOI18N
        getContentPane().add(tanggal_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 40, 40, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/Rectangle 53.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 220, 300, 130));

        logout1.setBackground(new java.awt.Color(0, 0, 0));
        logout1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnX1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnX1MouseClicked(evt);
            }
        });
        logout1.add(btnX1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 431, 40, 30));

        btn_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_keluarMouseClicked(evt);
            }
        });
        logout1.add(btn_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 575, 130, 40));

        btn_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_logoutMouseClicked(evt);
            }
        });
        logout1.add(btn_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 575, 110, 40));

        bg_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pop up keluar2.png"))); // NOI18N
        logout1.add(bg_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 418, -1, -1));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/form_beranda.png"))); // NOI18N
        logout1.add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        getContentPane().add(logout1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
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
    
    public void jumlahpenghuni(){
        try{
            java.sql.Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery("select count(*) as jumlah from tb_penghuni");
            
            while (result.next()) {
     
                lbljmlhpenghuni.setText(result.getString("jumlah"));
            }
             
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    public void jumlahkamar(){
        try{
            java.sql.Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery("select count(*) as jumlah from tb_kamar");
            
            while (result.next()) {
     
                lbljmlhkamar.setText(result.getString("jumlah"));
            }
             
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    public void jumlahkomplain(){
        try{
            java.sql.Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery("select count(*) as jumlah from tb_komplain");
            
            while (result.next()) {
     
                lbljmlhkomplain.setText(result.getString("jumlah"));
            }
             
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    public void jumlahtagihan(){
        try{
            java.sql.Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery("select count(*) as jumlah from tb_tagihan");
            
            while (result.next()) {
     
                lbljmlhtagihan.setText(result.getString("jumlah"));
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
            ResultSet result = stm.executeQuery("select sum(bayar) as jumlah from tb_tagihan");
            
            while (result.next()) {
     
//                lbltotalpendapatan.setText(result.getString("jumlah"));
lbltotalpendapatan.setText("Rp "+formatDec(result.getInt("jumlah")));
            }
             
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    
    public void getData2(){
        try{
            java.sql.Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery("select count(*) as jumlah from tb_komplain where status = 'Belum terbaca' having jumlah > 0");
            if (result.next()) { 
                lbl_belum_terbaca.setText(result.getString("jumlah"));
                circle.setVisible(true);
                lbl_belum_terbaca.setVisible(true);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(beranda.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void link_komplainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_komplainMouseClicked
        this.setVisible(false);
        new form_komplain().setVisible(true);
    }//GEN-LAST:event_link_komplainMouseClicked

    private void link_kamarKostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_kamarKostMouseClicked
        this.setVisible(false);
        new form_kamar().setVisible(true);
    }//GEN-LAST:event_link_kamarKostMouseClicked

    private void link_penghuniKostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_penghuniKostMouseClicked
       this.setVisible(false);
        new form_penghuni().setVisible(true);
    }//GEN-LAST:event_link_penghuniKostMouseClicked

    private void link_tagihanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_tagihanMouseClicked
        this.setVisible(false);
        new form_tagihan().setVisible(true);
    }//GEN-LAST:event_link_tagihanMouseClicked

    private void link_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_laporanMouseClicked
        this.setVisible(false);
        new form_laporan().setVisible(true);
    }//GEN-LAST:event_link_laporanMouseClicked

    private void link_akunPenghuniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_akunPenghuniMouseClicked
        this.setVisible(false);
        new form_akunPenghuni().setVisible(true);
    }//GEN-LAST:event_link_akunPenghuniMouseClicked

    private void lbl_berandaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_berandaMouseEntered
        lbl_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab beranda entered.png")));
    }//GEN-LAST:event_lbl_berandaMouseEntered

    private void lbl_berandaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_berandaMouseExited
        lbl_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab beranda normal.png")));
    }//GEN-LAST:event_lbl_berandaMouseExited

    private void lbl_berandaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_berandaMousePressed
        lbl_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab beranda press.png")));
    }//GEN-LAST:event_lbl_berandaMousePressed

    private void lbl_berandaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_berandaMouseReleased
        lbl_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab beranda entered.png")));
    }//GEN-LAST:event_lbl_berandaMouseReleased

    private void link_kamarKostMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_kamarKostMouseEntered
        // TODO add your handling code here:
        link_kamarKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab kamar kos entered.png")));
    }//GEN-LAST:event_link_kamarKostMouseEntered

    private void link_kamarKostMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_kamarKostMouseExited
        // TODO add your handling code here:
         link_kamarKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab kamar kos none.png")));
    }//GEN-LAST:event_link_kamarKostMouseExited

    private void link_kamarKostMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_kamarKostMousePressed
        // TODO add your handling code here:
         link_kamarKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab kamar kos press.png")));
    }//GEN-LAST:event_link_kamarKostMousePressed

    private void link_kamarKostMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_kamarKostMouseReleased
        // TODO add your handling code here:
         link_kamarKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab kamar kos entered.png")));
    }//GEN-LAST:event_link_kamarKostMouseReleased

    private void link_penghuniKostMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_penghuniKostMouseEntered
        // TODO add your handling code here:
        link_penghuniKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab penghuni kost entered.png")));
    }//GEN-LAST:event_link_penghuniKostMouseEntered

    private void link_penghuniKostMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_penghuniKostMouseExited
        // TODO add your handling code here:
        link_penghuniKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab penghuni kost none.png")));
    }//GEN-LAST:event_link_penghuniKostMouseExited

    private void link_penghuniKostMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_penghuniKostMousePressed
        // TODO add your handling code here:
        link_penghuniKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab penghuni kost press.png")));
    }//GEN-LAST:event_link_penghuniKostMousePressed

    private void link_penghuniKostMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_penghuniKostMouseReleased
        // TODO add your handling code here:
        link_penghuniKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab penghuni kost none.png")));
    }//GEN-LAST:event_link_penghuniKostMouseReleased

    private void link_tagihanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_tagihanMouseEntered
        // TODO add your handling code here:
        link_tagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan entered.png")));
    }//GEN-LAST:event_link_tagihanMouseEntered

    private void link_tagihanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_tagihanMouseExited
        // TODO add your handling code here:
        link_tagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan none.png")));
    }//GEN-LAST:event_link_tagihanMouseExited

    private void link_tagihanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_tagihanMousePressed
        // TODO add your handling code here:
        link_tagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan press.png")));
    }//GEN-LAST:event_link_tagihanMousePressed

    private void link_tagihanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_tagihanMouseReleased
        // TODO add your handling code here:
        link_tagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan entered.png")));
    }//GEN-LAST:event_link_tagihanMouseReleased

    private void link_laporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_laporanMouseEntered
        // TODO add your handling code here:
        link_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan entered.png")));
    }//GEN-LAST:event_link_laporanMouseEntered

    private void link_laporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_laporanMouseExited
        // TODO add your handling code here:
        link_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan none.png")));
    }//GEN-LAST:event_link_laporanMouseExited

    private void link_laporanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_laporanMousePressed
        // TODO add your handling code here:
        link_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan press.png")));
    }//GEN-LAST:event_link_laporanMousePressed

    private void link_laporanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_laporanMouseReleased
        // TODO add your handling code here:
        link_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan entered.png")));
    }//GEN-LAST:event_link_laporanMouseReleased

    private void link_komplainMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_komplainMouseEntered
        // TODO add your handling code here:
        link_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain entered.png")));
    }//GEN-LAST:event_link_komplainMouseEntered

    private void link_komplainMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_komplainMouseExited
        // TODO add your handling code here:
        link_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain none.png")));
    }//GEN-LAST:event_link_komplainMouseExited

    private void link_komplainMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_komplainMouseReleased
        // TODO add your handling code here:
        link_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain entered.png")));
    }//GEN-LAST:event_link_komplainMouseReleased

    private void link_komplainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_komplainMousePressed
        // TODO add your handling code here:
        link_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain press.png")));
    }//GEN-LAST:event_link_komplainMousePressed

    private void link_akunPenghuniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_akunPenghuniMouseEntered
        // TODO add your handling code here:
        link_akunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni entered.png")));
    }//GEN-LAST:event_link_akunPenghuniMouseEntered

    private void link_akunPenghuniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_akunPenghuniMouseExited
        // TODO add your handling code here:
        link_akunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni none.png")));
    }//GEN-LAST:event_link_akunPenghuniMouseExited

    private void link_akunPenghuniMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_akunPenghuniMousePressed
        // TODO add your handling code here:
        link_akunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni press.png")));
    }//GEN-LAST:event_link_akunPenghuniMousePressed

    private void link_akunPenghuniMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_akunPenghuniMouseReleased
        // TODO add your handling code here:
        link_akunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni none.png")));
    }//GEN-LAST:event_link_akunPenghuniMouseReleased

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

    private void link_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_keluarMouseClicked
        logout1.setVisible(true);
    }//GEN-LAST:event_link_keluarMouseClicked

    private void btnX1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnX1MouseClicked
        // TODO add your handling code here:
        logout1.setVisible(false);
    }//GEN-LAST:event_btnX1MouseClicked

    private void btn_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_keluarMouseClicked

    private void btn_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMouseClicked
        // TODO add your handling code here:
//        this.dispose();
        new frmlogin1().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_logoutMouseClicked

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
            java.util.logging.Logger.getLogger(beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new beranda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_logout;
    private javax.swing.JLabel btnX1;
    private javax.swing.JLabel btn_keluar;
    private javax.swing.JLabel btn_logout;
    private javax.swing.JLabel circle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jam_icon;
    private javax.swing.JLabel lbl_belum_terbaca;
    private javax.swing.JLabel lbl_beranda;
    private javax.swing.JLabel lbl_jam;
    private javax.swing.JLabel lbl_tanggal;
    private javax.swing.JLabel lbljmlhkamar;
    private javax.swing.JLabel lbljmlhkomplain;
    private javax.swing.JLabel lbljmlhpenghuni;
    private javax.swing.JLabel lbljmlhtagihan;
    public static final javax.swing.JLabel lblnama = new javax.swing.JLabel();
    public static final javax.swing.JLabel lblnama1 = new javax.swing.JLabel();
    private javax.swing.JLabel lbltotalpendapatan;
    private javax.swing.JLabel link_akunPenghuni;
    private javax.swing.JLabel link_kamarKost;
    private javax.swing.JLabel link_keluar;
    private javax.swing.JLabel link_komplain;
    private javax.swing.JLabel link_laporan;
    private javax.swing.JLabel link_penghuniKost;
    private javax.swing.JLabel link_tagihan;
    private javax.swing.JPanel logout1;
    private javax.swing.JLabel tanggal_icon;
    // End of variables declaration//GEN-END:variables
}
