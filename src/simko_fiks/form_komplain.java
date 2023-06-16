package simko_fiks;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import static simko_user.form_historyUser.lbl_idpenghuni;
import static simko_user.form_historyUser.tabel_history;


public class form_komplain extends javax.swing.JFrame {

   
    public form_komplain() {
        initComponents();
        Tampil_jam();
        Tampil_tanggal();
        datatable();
        addplaceholderstyle(txtf_cari);
        gantiStatus();
        tabel_komplain.getTableHeader().setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
        tabel_komplain.getTableHeader().setOpaque(false);
        tabel_komplain.setSelectionForeground(Color.white);
        tabel_komplain.setSelectionBackground(new Color(41,141,141));
        
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
    
    public void gantiStatus(){
        try{
            String sql = "UPDATE tb_komplain SET status = 'Terbaca' where status = 'Belum terbaca'";
            
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement stm = conn.prepareStatement(sql);
            stm.execute();
        }catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
    }
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
    
    public void datatable(){
        
         DefaultTableModel tbl = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tbl.addColumn("ID Penghuni");
        tbl.addColumn("Nama Penghuni");
        tbl.addColumn("Kamar");
        tbl.addColumn("Jenis Komplain");
        tbl.addColumn("Lama Keresahan");
        tbl.addColumn("permaslahan");
        
        tabel_komplain.setModel(tbl);
        try{
            String sql = "SELECT tb_penghuni.id_penghuni, tb_penghuni.nama_penghuni, tb_kamar.noKamar, tb_komplain.jenis_komplain, tb_komplain.lama_keresahan, tb_komplain.permasalahan \n" +
                    "FROM tb_komplain JOIN tb_penghuni ON tb_komplain.id_penghuni = tb_penghuni.id_penghuni JOIN tb_kamar ON tb_komplain.noKamar = tb_kamar.noKamar \n" +
                    "ORDER BY tb_penghuni.id_penghuni";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next())
            {
                tbl.addRow(new Object[]{
                    res.getString("id_penghuni"),
                    res.getString("nama_penghuni"),
                    res.getString("noKamar"),
                    res.getString("jenis_komplain"),
                    res.getString("lama_keresahan"),
                    res.getString("permasalahan")
                });
                tabel_komplain.setModel(tbl);
            }
        }catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_komplain = new javax.swing.JTable();
        txtf_cari = new javax.swing.JTextField();
        btn_beranda = new javax.swing.JLabel();
        btn_kamarKost = new javax.swing.JLabel();
        btn_penghuniKost = new javax.swing.JLabel();
        btn_tagihan = new javax.swing.JLabel();
        link_laporan = new javax.swing.JLabel();
        btn_komplain = new javax.swing.JLabel();
        link_akun_penghuni = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_keluar = new javax.swing.JLabel();
        jam_icon = new javax.swing.JLabel();
        lbl_jam = new javax.swing.JLabel();
        tanggal_icon = new javax.swing.JLabel();
        lbl_tanggal = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        logout = new javax.swing.JPanel();
        btnX = new javax.swing.JLabel();
        btn_keluar1 = new javax.swing.JLabel();
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

        tabel_komplain.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabel_komplain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_komplain.setRowHeight(40);
        tabel_komplain.getTableHeader().setResizingAllowed(false);
        tabel_komplain.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabel_komplain);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(500, 310, 1330, 580);

        lblnama6.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        getContentPane().add(lblnama6);
        lblnama6.setBounds(1750, 40, 130, 40);

        txtf_cari.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txtf_cari.setText("Cari");
        txtf_cari.setBorder(null);
        txtf_cari.setOpaque(false);
        txtf_cari.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtf_cariFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtf_cariFocusLost(evt);
            }
        });
        getContentPane().add(txtf_cari);
        txtf_cari.setBounds(1460, 230, 340, 30);

        btn_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab beranda none.png"))); // NOI18N
        btn_beranda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_berandaMouseClicked(evt);
            }
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
        btn_beranda.setBounds(-35, 270, 353, 69);

        btn_kamarKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab kamar kos none.png"))); // NOI18N
        btn_kamarKost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_kamarKostMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_kamarKostMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_kamarKostMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_kamarKostMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_kamarKostMouseReleased(evt);
            }
        });
        getContentPane().add(btn_kamarKost);
        btn_kamarKost.setBounds(-35, 340, 353, 69);

        btn_penghuniKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab penghuni kost none.png"))); // NOI18N
        btn_penghuniKost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_penghuniKostMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_penghuniKostMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_penghuniKostMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_penghuniKostMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_penghuniKostMouseReleased(evt);
            }
        });
        getContentPane().add(btn_penghuniKost);
        btn_penghuniKost.setBounds(-35, 410, 353, 69);

        btn_tagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan none.png"))); // NOI18N
        btn_tagihan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tagihanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_tagihanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_tagihanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_tagihanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_tagihanMouseReleased(evt);
            }
        });
        getContentPane().add(btn_tagihan);
        btn_tagihan.setBounds(-35, 483, 353, 69);

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
        getContentPane().add(link_laporan);
        link_laporan.setBounds(-35, 554, 353, 69);

        btn_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain normal.png"))); // NOI18N
        btn_komplain.addMouseListener(new java.awt.event.MouseAdapter() {
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
        btn_komplain.setBounds(-35, 625, 353, 69);

        link_akun_penghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni none.png"))); // NOI18N
        link_akun_penghuni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                link_akun_penghuniMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                link_akun_penghuniMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                link_akun_penghuniMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                link_akun_penghuniMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                link_akun_penghuniMouseReleased(evt);
            }
        });
        getContentPane().add(link_akun_penghuni);
        link_akun_penghuni.setBounds(-35, 696, 353, 69);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tutupan.png"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(248, 951, 126, 96);

        btn_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab keluar none.png"))); // NOI18N
        btn_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_keluarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_keluarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_keluarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_keluarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_keluarMouseReleased(evt);
            }
        });
        getContentPane().add(btn_keluar);
        btn_keluar.setBounds(-35, 960, 382, 74);

        jam_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clock icon.png"))); // NOI18N
        getContentPane().add(jam_icon);
        jam_icon.setBounds(1170, 40, 40, 40);

        lbl_jam.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        lbl_jam.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lbl_jam);
        lbl_jam.setBounds(1239, 40, 110, 40);

        tanggal_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/calendar  icon.png"))); // NOI18N
        getContentPane().add(tanggal_icon);
        tanggal_icon.setBounds(1360, 40, 40, 40);

        lbl_tanggal.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        lbl_tanggal.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lbl_tanggal);
        lbl_tanggal.setBounds(1420, 40, 230, 40);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/form_komplain.png"))); // NOI18N
        getContentPane().add(bg);
        bg.setBounds(0, 0, 1920, 1080);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tutupan.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(248, 951, 126, 96);

        logout.setBackground(new java.awt.Color(0, 0, 0));
        logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXMouseClicked(evt);
            }
        });
        logout.add(btnX, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 431, 40, 30));

        btn_keluar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_keluar1MouseClicked(evt);
            }
        });
        logout.add(btn_keluar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 575, 130, 40));

        btn_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_logoutMouseClicked(evt);
            }
        });
        logout.add(btn_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 575, 110, 40));

        bg_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pop up keluar2.png"))); // NOI18N
        bg_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bg_logoutMouseClicked(evt);
            }
        });
        logout.add(bg_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 418, -1, -1));

        getContentPane().add(logout);
        logout.setBounds(0, 0, 1920, 1080);

        setSize(new java.awt.Dimension(1980, 1080));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtf_cariFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtf_cariFocusGained
        if(txtf_cari.getText().equals("Cari")){
            txtf_cari.setText(null);
            txtf_cari.requestFocus();
            removeplaceholderstyle(txtf_cari);
        }
    }//GEN-LAST:event_txtf_cariFocusGained

    private void txtf_cariFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtf_cariFocusLost
        if(txtf_cari.getText().length()==0){

            addplaceholderstyle(txtf_cari);
            txtf_cari.setText("Cari");
        }
    }//GEN-LAST:event_txtf_cariFocusLost

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(form_komplain.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void btn_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseClicked
        logout.setVisible(true);
    }//GEN-LAST:event_btn_keluarMouseClicked

    private void btn_keluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseEntered
        btn_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab keluar entered.png")));
    }//GEN-LAST:event_btn_keluarMouseEntered

    private void btn_keluarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseExited
        btn_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab keluar none.png")));
    }//GEN-LAST:event_btn_keluarMouseExited

    private void btn_keluarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMousePressed
        btn_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab keluar press.png")));
    }//GEN-LAST:event_btn_keluarMousePressed

    private void btn_keluarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseReleased
        btn_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab keluar entered.png")));
    }//GEN-LAST:event_btn_keluarMouseReleased

    private void btn_komplainMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_komplainMouseEntered
        btn_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain entered.png")));
    }//GEN-LAST:event_btn_komplainMouseEntered

    private void btn_komplainMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_komplainMouseExited
        btn_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain normal.png")));
    }//GEN-LAST:event_btn_komplainMouseExited

    private void btn_komplainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_komplainMousePressed
        btn_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain press.png")));
    }//GEN-LAST:event_btn_komplainMousePressed

    private void btn_komplainMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_komplainMouseReleased
        btn_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain entered.png")));
    }//GEN-LAST:event_btn_komplainMouseReleased

    private void btn_berandaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_berandaMouseClicked
        this.setVisible(false);
        new beranda().setVisible(true);
    }//GEN-LAST:event_btn_berandaMouseClicked

    private void btn_berandaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_berandaMouseEntered
        btn_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab beranda entered.png")));
    }//GEN-LAST:event_btn_berandaMouseEntered

    private void btn_berandaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_berandaMouseExited
        btn_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab beranda none.png")));
    }//GEN-LAST:event_btn_berandaMouseExited

    private void btn_berandaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_berandaMousePressed
        btn_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab beranda press.png")));
    }//GEN-LAST:event_btn_berandaMousePressed

    private void btn_berandaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_berandaMouseReleased
        btn_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab beranda entered.png")));
    }//GEN-LAST:event_btn_berandaMouseReleased

    private void btn_kamarKostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kamarKostMouseClicked
        this.setVisible(false);
        new form_kamar().setVisible(true);
    }//GEN-LAST:event_btn_kamarKostMouseClicked

    private void btn_kamarKostMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kamarKostMouseEntered
        // TODO add your handling code here:
        btn_kamarKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab kamar kos entered.png")));
    }//GEN-LAST:event_btn_kamarKostMouseEntered

    private void btn_kamarKostMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kamarKostMouseExited
        // TODO add your handling code here:
        btn_kamarKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab kamar kos none.png")));
    }//GEN-LAST:event_btn_kamarKostMouseExited

    private void btn_kamarKostMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kamarKostMousePressed
        // TODO add your handling code here:
        btn_kamarKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab kamar kos press.png")));
    }//GEN-LAST:event_btn_kamarKostMousePressed

    private void btn_kamarKostMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kamarKostMouseReleased
        // TODO add your handling code here:
        btn_kamarKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab kamar kos entered.png")));
    }//GEN-LAST:event_btn_kamarKostMouseReleased

    private void btn_penghuniKostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_penghuniKostMouseClicked
        this.setVisible(false);
        new form_penghuni().setVisible(true);
    }//GEN-LAST:event_btn_penghuniKostMouseClicked

    private void btn_penghuniKostMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_penghuniKostMouseEntered
        // TODO add your handling code here:
        btn_penghuniKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab penghuni kost entered.png")));
    }//GEN-LAST:event_btn_penghuniKostMouseEntered

    private void btn_penghuniKostMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_penghuniKostMouseExited
        // TODO add your handling code here:
        btn_penghuniKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab penghuni kost none.png")));
    }//GEN-LAST:event_btn_penghuniKostMouseExited

    private void btn_penghuniKostMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_penghuniKostMousePressed
        // TODO add your handling code here:
        btn_penghuniKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab penghuni kost press.png")));
    }//GEN-LAST:event_btn_penghuniKostMousePressed

    private void btn_penghuniKostMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_penghuniKostMouseReleased
        // TODO add your handling code here:
        btn_penghuniKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab penghuni kost none.png")));
    }//GEN-LAST:event_btn_penghuniKostMouseReleased

    private void btn_tagihanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tagihanMouseClicked
        this.setVisible(false);
        new form_tagihan().setVisible(true);
    }//GEN-LAST:event_btn_tagihanMouseClicked

    private void btn_tagihanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tagihanMouseEntered
        // TODO add your handling code here:
        btn_tagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan entered.png")));
    }//GEN-LAST:event_btn_tagihanMouseEntered

    private void btn_tagihanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tagihanMouseExited
        // TODO add your handling code here:
        btn_tagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan none.png")));
    }//GEN-LAST:event_btn_tagihanMouseExited

    private void btn_tagihanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tagihanMousePressed
        // TODO add your handling code here:
        btn_tagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan press.png")));
    }//GEN-LAST:event_btn_tagihanMousePressed

    private void btn_tagihanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tagihanMouseReleased
        // TODO add your handling code here:
        btn_tagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan entered.png")));
    }//GEN-LAST:event_btn_tagihanMouseReleased

    private void link_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_laporanMouseClicked
        this.setVisible(false);
        new form_laporan().setVisible(true);
    }//GEN-LAST:event_link_laporanMouseClicked

    private void link_laporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_laporanMouseEntered
        link_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan entered.png")));
    }//GEN-LAST:event_link_laporanMouseEntered

    private void link_laporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_laporanMouseExited
        link_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan none.png")));
    }//GEN-LAST:event_link_laporanMouseExited

    private void link_laporanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_laporanMousePressed
        link_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan press.png")));
    }//GEN-LAST:event_link_laporanMousePressed

    private void link_laporanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_laporanMouseReleased
        link_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan entered.png")));
    }//GEN-LAST:event_link_laporanMouseReleased

    private void link_akun_penghuniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_akun_penghuniMouseClicked
        this.setVisible(false);
        new form_akunPenghuni().setVisible(true);
    }//GEN-LAST:event_link_akun_penghuniMouseClicked

    private void link_akun_penghuniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_akun_penghuniMouseEntered
        link_akun_penghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni entered.png")));
    }//GEN-LAST:event_link_akun_penghuniMouseEntered

    private void link_akun_penghuniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_akun_penghuniMouseExited
        link_akun_penghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni none.png")));
    }//GEN-LAST:event_link_akun_penghuniMouseExited

    private void link_akun_penghuniMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_akun_penghuniMousePressed
        link_akun_penghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni press.png")));
    }//GEN-LAST:event_link_akun_penghuniMousePressed

    private void link_akun_penghuniMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_link_akun_penghuniMouseReleased
        link_akun_penghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni entered.png")));
    }//GEN-LAST:event_link_akun_penghuniMouseReleased

    private void btnXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXMouseClicked
        // TODO add your handling code here:
        logout.setVisible(false);
    }//GEN-LAST:event_btnXMouseClicked

    private void btn_keluar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluar1MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_keluar1MouseClicked

    private void btn_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new frmlogin1().setVisible(true);
    }//GEN-LAST:event_btn_logoutMouseClicked

    private void bg_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg_logoutMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bg_logoutMouseClicked

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
            java.util.logging.Logger.getLogger(form_komplain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_komplain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_komplain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_komplain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_komplain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_logout;
    private javax.swing.JLabel btnX;
    private javax.swing.JLabel btn_beranda;
    private javax.swing.JLabel btn_kamarKost;
    private javax.swing.JLabel btn_keluar;
    private javax.swing.JLabel btn_keluar1;
    private javax.swing.JLabel btn_komplain;
    private javax.swing.JLabel btn_logout;
    private javax.swing.JLabel btn_penghuniKost;
    private javax.swing.JLabel btn_tagihan;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jam_icon;
    private javax.swing.JLabel lbl_jam;
    private javax.swing.JLabel lbl_tanggal;
    public static final javax.swing.JLabel lblnama6 = new javax.swing.JLabel();
    private javax.swing.JLabel link_akun_penghuni;
    private javax.swing.JLabel link_laporan;
    private javax.swing.JPanel logout;
    private javax.swing.JTable tabel_komplain;
    private javax.swing.JLabel tanggal_icon;
    private javax.swing.JTextField txtf_cari;
    // End of variables declaration//GEN-END:variables

}
