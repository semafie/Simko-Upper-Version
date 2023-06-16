package simko_fiks;

import java.util.Date;

import com.sun.org.apache.xalan.internal.lib.ExsltDatetime;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;
import java.awt.Color;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

//import untuk fungsi tanggal dan waktu realtime
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.Timer;

public class form_editTagihan extends javax.swing.JFrame {
    public static String kd_tagihan;
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    
  private int harga;
  private int sisa;
  private String status;
    
    public form_editTagihan() {
        initComponents();
        tampil_id_otomatis();
        tampil_id();
        Tampil_jam();
        Tampil_tanggal();
        selectdata();
        
        Date tgl = new Date();
        txt_tanggalbyr.setMinSelectableDate(tgl);
        
        logout.setVisible(false);
        logout.setBackground(new Color(0,0,0,200));
        
        pop_up_berhasilSimpan.setVisible(false);
        pop_up_berhasilSimpan.setBackground(new Color(0,0,0,0));
    }
    
    //format decimal
     public static String formatDec(int val) {
        return String.format("%,d", val).replace(',', '.');
    }
     
     //untuk menghilangkan titik 
     public static String reFormat(String val){
         return val.replaceAll("\\.", "");
     }
     
    public void selectdata(){
        try{
            String sql = "SELECT * FROM tb_tagihan JOIN tb_penghuni ON tb_tagihan.id_penghuni = tb_penghuni.id_penghuni JOIN tb_kamar ON tb_tagihan.noKamar = tb_kamar.noKamar WHERE "
                    + "id_tagihan = '"+kd_tagihan+"'";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);
            
                if(rs.next()){
                    txt_id_transaksi.setText(rs.getString("id_tagihan"));
                    combo_idPeng.setSelectedItem(rs.getString("id_penghuni"));
                    txt_nama.setText(rs.getString("nama_penghuni"));
                    txt_nokamar.setText(rs.getString("noKamar"));
                    txt_tanggalbyr.setDate(rs.getDate("tgl_bayar"));
//                    txt_bayar.setText(rs.getString("bayar"));
                    txt_bayar.setText(formatDec(rs.getInt("bayar")));
//                    txt_sisa.setText(rs.getString("sisa"));
                    txt_sisa.setText(formatDec(rs.getInt("sisa")));
                    txt_status.setText(rs.getString("status"));
                    txt_keterangan.setText(rs.getString("keterangan"));
                }
    
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane,e);
        }
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
    
    private void clear(){
        txt_nama.setText(null);
        txt_nokamar.setText(null);
        txt_hargaKamar.setText(null);
        txt_tanggalbyr.setDate(null);
        txt_bayar.setText(null);
        txt_sisa.setText(null);
        txt_status.setText(null);
        txt_keterangan.setText(null);
    }
    
    public void koneksi(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simko", "root", "");
            stat = con.createStatement();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     //tampil id transaksi otomatis
    private void tampil_id_otomatis(){
        try{
            koneksi();
            String sql = "select id_bantu as a from tb_tagihan order by id_bantu desc";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()){
                String no_urut = rs.getString("a");
                int a = Integer.parseInt(no_urut);
                int panjang = no_urut.length();
                for (int i = 0; i < 2 - panjang; i++) {
                    a = a;
                }
                txt_id_transaksi.setText("TR00"+Integer.toString(a+1));
                txt_id_bantu.setText(Integer.toString(a+1));
            }else{
                txt_id_transaksi.setText("TR001");
                txt_id_bantu.setText("1");
            }
            
            rs.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //memanggil id penghuni
     public void tampil_id(){
        try {
            String sql = "SELECT id_penghuni FROM tb_penghuni";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet res = pst.executeQuery(sql);
            
            while(res.next()){
                combo_idPeng.addItem(res.getString("id_penghuni"));
            }
            
            res.last();
            int jumlah_data = res.getRow();
            
            res.first();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
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

        txt_id_transaksi = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbl_jam = new javax.swing.JLabel();
        lbl_tanggal = new javax.swing.JLabel();
        calendar_icon = new javax.swing.JLabel();
        jam_icon = new javax.swing.JLabel();
        btn_beranda = new javax.swing.JLabel();
        btn_kamarKost = new javax.swing.JLabel();
        btn_penghuniKost = new javax.swing.JLabel();
        btn_tagihan = new javax.swing.JLabel();
        btn_laporan = new javax.swing.JLabel();
        btn_komplain = new javax.swing.JLabel();
        btn_akunPenghuni = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_keluar = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JLabel();
        btn_batal = new javax.swing.JLabel();
        txt_id_bantu = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pop_up_berhasilSimpan = new javax.swing.JPanel();
        btn_close = new javax.swing.JLabel();
        btn_oke = new javax.swing.JLabel();
        bg_simpan = new javax.swing.JLabel();
        logout = new javax.swing.JPanel();
        btnX = new javax.swing.JLabel();
        btn_keluar1 = new javax.swing.JLabel();
        btn_logout = new javax.swing.JLabel();
        bg_logout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        txt_id_transaksi.setEditable(false);
        txt_id_transaksi.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_id_transaksi.setBorder(null);
        txt_id_transaksi.setOpaque(false);
        getContentPane().add(txt_id_transaksi);
        txt_id_transaksi.setBounds(540, 363, 310, 50);

        combo_idPeng.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        combo_idPeng.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih--" }));
        combo_idPeng.setBorder(null);
        combo_idPeng.setEnabled(false);
        combo_idPeng.setOpaque(false);
        combo_idPeng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_idPengActionPerformed(evt);
            }
        });
        getContentPane().add(combo_idPeng);
        combo_idPeng.setBounds(950, 363, 330, 60);

        txt_nokamar.setEditable(false);
        txt_nokamar.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_nokamar.setBorder(null);
        txt_nokamar.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_nokamar.setEnabled(false);
        txt_nokamar.setOpaque(false);
        txt_nokamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nokamarActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nokamar);
        txt_nokamar.setBounds(540, 470, 310, 50);

        txt_bayar.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_bayar.setBorder(null);
        txt_bayar.setOpaque(false);
        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
        });
        getContentPane().add(txt_bayar);
        txt_bayar.setBounds(540, 580, 320, 50);

        txt_hargaKamar.setEditable(false);
        txt_hargaKamar.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_hargaKamar.setBorder(null);
        txt_hargaKamar.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_hargaKamar.setEnabled(false);
        txt_hargaKamar.setOpaque(false);
        getContentPane().add(txt_hargaKamar);
        txt_hargaKamar.setBounds(970, 470, 310, 60);

        txt_status.setEditable(false);
        txt_status.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_status.setBorder(null);
        txt_status.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_status.setOpaque(false);
        getContentPane().add(txt_status);
        txt_status.setBounds(1390, 580, 330, 50);

        txt_nama.setEditable(false);
        txt_nama.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_nama.setBorder(null);
        txt_nama.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_nama.setEnabled(false);
        txt_nama.setOpaque(false);
        getContentPane().add(txt_nama);
        txt_nama.setBounds(1400, 363, 320, 60);

        txt_sisa.setEditable(false);
        txt_sisa.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_sisa.setBorder(null);
        txt_sisa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_sisa.setOpaque(false);
        txt_sisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_sisaKeyReleased(evt);
            }
        });
        getContentPane().add(txt_sisa);
        txt_sisa.setBounds(970, 580, 310, 50);

        txt_keterangan.setColumns(20);
        txt_keterangan.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        txt_keterangan.setRows(5);
        txt_keterangan.setBorder(null);
        txt_keterangan.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_keterangan.setOpaque(false);
        jScrollPane1.setViewportView(txt_keterangan);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(890, 720, 480, 110);

        lbl_jam.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        lbl_jam.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lbl_jam);
        lbl_jam.setBounds(1210, 40, 110, 40);

        lbl_tanggal.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        lbl_tanggal.setForeground(new java.awt.Color(255, 255, 255));
        lbl_tanggal.setFocusCycleRoot(true);
        getContentPane().add(lbl_tanggal);
        lbl_tanggal.setBounds(1420, 40, 220, 40);

        calendar_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/calendar  icon.png"))); // NOI18N
        getContentPane().add(calendar_icon);
        calendar_icon.setBounds(1368, 40, 40, 40);

        jam_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clock icon.png"))); // NOI18N
        getContentPane().add(jam_icon);
        jam_icon.setBounds(1160, 38, 40, 40);

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

        btn_tagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan normal.png"))); // NOI18N
        btn_tagihan.addMouseListener(new java.awt.event.MouseAdapter() {
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

        btn_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan none.png"))); // NOI18N
        btn_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_laporanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_laporanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_laporanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_laporanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_laporanMouseReleased(evt);
            }
        });
        getContentPane().add(btn_laporan);
        btn_laporan.setBounds(-35, 554, 353, 69);

        btn_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain none.png"))); // NOI18N
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
        btn_komplain.setBounds(-35, 625, 353, 69);

        btn_akunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni none.png"))); // NOI18N
        btn_akunPenghuni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_akunPenghuniMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_akunPenghuniMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_akunPenghuniMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_akunPenghuniMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_akunPenghuniMouseReleased(evt);
            }
        });
        getContentPane().add(btn_akunPenghuni);
        btn_akunPenghuni.setBounds(-35, 696, 353, 69);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tutupan.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(248, 951, 126, 96);

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

        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_tagihan/editTagihan/btn simpan normal.png"))); // NOI18N
        btn_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_simpanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_simpanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_simpanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_simpanMouseReleased(evt);
            }
        });
        getContentPane().add(btn_simpan);
        btn_simpan.setBounds(1136, 870, 230, 70);
        getContentPane().add(btn_batal);
        btn_batal.setBounds(890, 880, 230, 60);
        getContentPane().add(txt_id_bantu);
        txt_id_bantu.setBounds(1710, 260, 0, 0);

        lblnama4_1.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N
        lblnama4_1.setForeground(new java.awt.Color(153, 153, 153));
        lblnama4_1.setText(".");
        getContentPane().add(lblnama4_1);
        lblnama4_1.setBounds(1750, 40, 130, 40);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/form_tagihanEdit.png"))); // NOI18N
        getContentPane().add(bg);
        bg.setBounds(0, 0, 1920, 1080);

        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(1470, 370, 34, 14);

        pop_up_berhasilSimpan.setBackground(new java.awt.Color(0, 0, 0));
        pop_up_berhasilSimpan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/x normal.png"))); // NOI18N
        btn_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_closeMouseExited(evt);
            }
        });
        pop_up_berhasilSimpan.add(btn_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1162, 429, 31, 31));

        btn_oke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/btn oke normal.png"))); // NOI18N
        btn_oke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_okeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_okeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_okeMouseExited(evt);
            }
        });
        pop_up_berhasilSimpan.add(btn_oke, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 574, 120, 35));

        bg_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup_berhasilTambah.png"))); // NOI18N
        pop_up_berhasilSimpan.add(bg_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 418, 511, 243));

        getContentPane().add(pop_up_berhasilSimpan);
        pop_up_berhasilSimpan.setBounds(0, 0, 1920, 1080);

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

        setSize(new java.awt.Dimension(1920, 1080));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseClicked
        String tampilan = "yyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat (tampilan);
        Date tanggal_bayar = txt_tanggalbyr.getDate();
        
        if(txt_bayar.getText().isEmpty() && tanggal_bayar == null){
            JOptionPane.showMessageDialog(null, "Tanggal dan bayar harus diisi");            
            }else if(tanggal_bayar == null){
                JOptionPane.showMessageDialog(null, "Tanggal harus diisi");
                return;
            }else if(txt_bayar.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "TextField Bayar harus diisi");
            }else{
                String tanggal = String.valueOf(fm.format(txt_tanggalbyr.getDate()));
           
        try{
            String sql = "UPDATE tb_tagihan JOIN tb_penghuni ON tb_tagihan.id_penghuni "
                    + "= tb_penghuni.id_penghuni JOIN tb_kamar ON tb_tagihan.noKamar "
                    + "= tb_kamar.noKamar "
                    +"SET tb_tagihan.tgl_bayar = '"+tanggal+"', tb_tagihan.bayar = '"
                    +reFormat(txt_bayar.getText())+"', tb_tagihan.sisa = '"
                    +reFormat(txt_sisa.getText())+"', tb_tagihan.status = '"
                    +txt_status.getText()+"', tb_tagihan.keterangan = '"+txt_keterangan.getText()
                    +"'WHERE tb_tagihan.id_tagihan = '"+txt_id_transaksi.getText()+"'";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            pop_up_berhasilSimpan.setVisible(true);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Data gagal di edit, Mohon cek "
                    + "kembali data yang anda masukkan");
        }
    }
    }//GEN-LAST:event_btn_simpanMouseClicked

    private void combo_idPengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_idPengActionPerformed
        
        int pilih = combo_idPeng.getSelectedIndex();
        
        if(pilih == 0){
            clear();
        }
        
        try{
            String sqlAmbilHarga ="SELECT * FROM tb_kamar join tb_penghuni on tb_kamar.noKamar = tb_penghuni.noKamar WHERE tb_penghuni.id_penghuni = '"+combo_idPeng.getSelectedItem()+"'";

            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sqlAmbilHarga);

            if(res.next()){
                harga = res.getInt("tb_kamar.harga");               
                txt_nama.setText(res.getString("tb_penghuni.nama_penghuni"));
                txt_nokamar.setText(res.getString("tb_penghuni.noKamar"));
//                txt_hargaKamar.setText(res.getString("tb_kamar.harga"));
            txt_hargaKamar.setText(formatDec(res.getInt("tb_kamar.harga")));
            
            }
            System.out.println(harga);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_combo_idPengActionPerformed

    private void txt_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyReleased
        
//        int bayar = Integer.parseInt(this.txt_bayar.getText());
          int bayar = Integer.parseInt(reFormat(txt_bayar.getText()));
          txt_bayar.setText(formatDec(bayar));
          
        try{
           sisa = harga - bayar;

//            txt_sisa.setText(String.valueOf(sisa));
              txt_sisa.setText(String.valueOf(formatDec(sisa)));

            if(sisa > 0){
                status = "Belum Lunas";
                txt_keterangan.enable();
                txt_status.setText(status);
            }else if (sisa == 0){
                status = "Lunas";
                txt_keterangan.setText("");
                txt_keterangan.disable();
                txt_status.setText(status);
            }else{
                JOptionPane.showMessageDialog(null, "Uangnya yang anda masukkan melebihi batas");
                txt_bayar.setText("");
                txt_sisa.setText("");
                txt_status.setText("");
            }

            

        }catch(Exception e){

        }
    }//GEN-LAST:event_txt_bayarKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(form_editTagihan.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void txt_sisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_sisaKeyReleased
//        int bayar = Integer.parseInt(this.txt_sisa.getText());
//
//        try{
//            sisa = sisa - bayar;
//
//            txt_bayar.setText(String.valueOf(sisa));
//
//            if(sisa > 0){
//                status = "Belum Lunas";
//                txt_keterangan.enable();
//            }else{
//                status = "Lunas";
//                txt_keterangan.disable();
//            }
//
//            txt_status.setText(status);
//
//        }catch(Exception e){
//
//        }
    }//GEN-LAST:event_txt_sisaKeyReleased

    private void txt_nokamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nokamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nokamarActionPerformed

    private void btn_simpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseEntered
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_tagihan/editTagihan/btn simpan entered.png")));
    }//GEN-LAST:event_btn_simpanMouseEntered

    private void btn_simpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseExited
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_tagihan/editTagihan/btn simpan normal.png")));
    }//GEN-LAST:event_btn_simpanMouseExited

    private void btn_simpanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMousePressed
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_tagihan/editTagihan/btn simpan press.png")));
    }//GEN-LAST:event_btn_simpanMousePressed

    private void btn_simpanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseReleased
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_tagihan/editTagihan/btn simpan entered.png")));
    }//GEN-LAST:event_btn_simpanMouseReleased

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

    private void btn_tagihanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tagihanMouseEntered
        btn_tagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan entered.png")));
    }//GEN-LAST:event_btn_tagihanMouseEntered

    private void btn_tagihanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tagihanMouseExited
        btn_tagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan normal.png")));
    }//GEN-LAST:event_btn_tagihanMouseExited

    private void btn_tagihanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tagihanMousePressed
        btn_tagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan press.png")));
    }//GEN-LAST:event_btn_tagihanMousePressed

    private void btn_tagihanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tagihanMouseReleased
        btn_tagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan entered.png")));
    }//GEN-LAST:event_btn_tagihanMouseReleased

    private void btn_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseClicked
        this.setVisible(false);
        new form_laporan().setVisible(true);
    }//GEN-LAST:event_btn_laporanMouseClicked

    private void btn_laporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseEntered
        btn_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan entered.png")));
    }//GEN-LAST:event_btn_laporanMouseEntered

    private void btn_laporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseExited
        btn_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan none.png")));
    }//GEN-LAST:event_btn_laporanMouseExited

    private void btn_laporanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMousePressed
        btn_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan press.png")));
    }//GEN-LAST:event_btn_laporanMousePressed

    private void btn_laporanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseReleased
        btn_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan entered.png")));
    }//GEN-LAST:event_btn_laporanMouseReleased

    private void btn_komplainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_komplainMouseClicked
        this.setVisible(false);
        new form_komplain().setVisible(true);
    }//GEN-LAST:event_btn_komplainMouseClicked

    private void btn_komplainMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_komplainMouseEntered
        btn_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain entered.png")));
    }//GEN-LAST:event_btn_komplainMouseEntered

    private void btn_komplainMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_komplainMouseExited
        btn_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain none.png")));
    }//GEN-LAST:event_btn_komplainMouseExited

    private void btn_komplainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_komplainMousePressed
        btn_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain press.png")));
    }//GEN-LAST:event_btn_komplainMousePressed

    private void btn_komplainMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_komplainMouseReleased
        btn_komplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain entered.png")));
    }//GEN-LAST:event_btn_komplainMouseReleased

    private void btn_akunPenghuniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_akunPenghuniMouseClicked
        this.setVisible(false);
        new form_akunPenghuni().setVisible(true);
    }//GEN-LAST:event_btn_akunPenghuniMouseClicked

    private void btn_akunPenghuniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_akunPenghuniMouseEntered
        btn_akunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni entered.png")));
    }//GEN-LAST:event_btn_akunPenghuniMouseEntered

    private void btn_akunPenghuniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_akunPenghuniMouseExited
        btn_akunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni none.png")));
    }//GEN-LAST:event_btn_akunPenghuniMouseExited

    private void btn_akunPenghuniMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_akunPenghuniMousePressed
        btn_akunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni press.png")));
    }//GEN-LAST:event_btn_akunPenghuniMousePressed

    private void btn_akunPenghuniMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_akunPenghuniMouseReleased
        btn_akunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni entered.png")));
    }//GEN-LAST:event_btn_akunPenghuniMouseReleased

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

    private void btn_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_closeMouseClicked
        // TODO add your handling code here:
        pop_up_berhasilSimpan.setVisible(false);
    }//GEN-LAST:event_btn_closeMouseClicked

    private void btn_closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_closeMouseEntered
        // TODO add your handling code here:
        btn_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/x entered.png")));
    }//GEN-LAST:event_btn_closeMouseEntered

    private void btn_closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_closeMouseExited
        // TODO add your handling code here:
        btn_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/x normal.png")));
    }//GEN-LAST:event_btn_closeMouseExited

    private void btn_okeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_okeMouseClicked
        this.setVisible(false);
        new form_laporan().setVisible(true);
    }//GEN-LAST:event_btn_okeMouseClicked

    private void btn_okeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_okeMouseEntered
        // TODO add your handling code here:
        btn_oke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/btn oke entered.png")));
    }//GEN-LAST:event_btn_okeMouseEntered

    private void btn_okeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_okeMouseExited
        // TODO add your handling code here:
        btn_oke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/btn oke normal.png")));
    }//GEN-LAST:event_btn_okeMouseExited

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
            java.util.logging.Logger.getLogger(form_editTagihan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_editTagihan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_editTagihan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_editTagihan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_editTagihan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_logout;
    private javax.swing.JLabel bg_simpan;
    private javax.swing.JLabel btnX;
    private javax.swing.JLabel btn_akunPenghuni;
    private javax.swing.JLabel btn_batal;
    private javax.swing.JLabel btn_beranda;
    private javax.swing.JLabel btn_close;
    private javax.swing.JLabel btn_kamarKost;
    private javax.swing.JLabel btn_keluar;
    private javax.swing.JLabel btn_keluar1;
    private javax.swing.JLabel btn_komplain;
    private javax.swing.JLabel btn_laporan;
    private javax.swing.JLabel btn_logout;
    private javax.swing.JLabel btn_oke;
    private javax.swing.JLabel btn_penghuniKost;
    private javax.swing.JLabel btn_simpan;
    private javax.swing.JLabel btn_tagihan;
    private javax.swing.JLabel calendar_icon;
    public static final javax.swing.JComboBox<String> combo_idPeng = new javax.swing.JComboBox<>();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jam_icon;
    private javax.swing.JLabel lbl_jam;
    private javax.swing.JLabel lbl_tanggal;
    public static final javax.swing.JLabel lblnama4_1 = new javax.swing.JLabel();
    private javax.swing.JPanel logout;
    private javax.swing.JPanel pop_up_berhasilSimpan;
    public static final javax.swing.JTextField txt_bayar = new javax.swing.JTextField();
    public static final javax.swing.JTextField txt_hargaKamar = new javax.swing.JTextField();
    private javax.swing.JLabel txt_id_bantu;
    private javax.swing.JTextField txt_id_transaksi;
    public static final javax.swing.JTextArea txt_keterangan = new javax.swing.JTextArea();
    public static final javax.swing.JTextField txt_nama = new javax.swing.JTextField();
    public static final javax.swing.JTextField txt_nokamar = new javax.swing.JTextField();
    public static final javax.swing.JTextField txt_sisa = new javax.swing.JTextField();
    public static final javax.swing.JTextField txt_status = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables
}
