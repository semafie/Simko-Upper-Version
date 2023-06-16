/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simko_fiks;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
//import pop_up.pop_upHapus;
//import pop_up.pop_upKeluar;
//import pop_up.pop_upSimpan;
/**
 *
 * @author gilan
 */
public class form_penghuni extends javax.swing.JFrame { 

    /**
     * Creates new form penghuni
     */
    
    public boolean databaru;
    public boolean klikedit;
    public boolean klikhapus;
    public boolean kosong_tanggal;
    
    /*public Connection getConnection(){
        Connection con;
        Statement stat;
        ResultSet rs;
        String sql;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simko", "root", "");
            return con;
            
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }*/
    
     public void koneksi(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simko", "root", "");
            Statement stat = (Statement) con.createStatement();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public form_penghuni() {
        initComponents();
        Tampil_jam();
        Tampil_tanggal();
        datatable();
        getData();
        tampil_id_otomatis();
        //tampil_kamar();
        //hapusOpsi();
        koneksi();
        databaru = true;
        klikedit = false;
        klikhapus = false;
        kosong_tanggal = false;
        //=======================Setting Tabel Penghuni=======================//
        tabelPenghuni.getTableHeader().setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
        tabelPenghuni.getTableHeader().setOpaque(false);
        tabelPenghuni.setSelectionBackground(new Color (41, 141, 141));
        tabelPenghuni.setSelectionForeground(Color.white);
        //====================Disable Tanggal di Masa Lalu====================//
        Date tgl = new Date();
        txt_tambah_tanggal.setMinSelectableDate(tgl);
        txt_tambah_keluar.setMinSelectableDate(tgl);
        txt_edit_tanggal.setMinSelectableDate(tgl);
        txt_edit_keluar.setMinSelectableDate(tgl);        
        //===========================Setting Panel===========================//
        panel_tambah.setVisible(false);
        panel_edit.setVisible(false);
        pop_up_berhasilSimpan.setVisible(false);
        pop_up_berhasilHapus.setVisible(false);
        pop_up_berhasilEdit.setVisible(false);
        pop_up_konfirmasiHapus.setVisible(false);
        pop_up_batal.setVisible(false);
        logout.setVisible(false);
        
        logout.setBackground(new Color(0,0,0,200));
        panel_tambah.setBackground(new Color(0,0,0,200));
        panel_edit.setBackground(new Color(0,0,0,200));
        pop_up_berhasilSimpan.setBackground(new Color(0,0,0,0));
        pop_up_berhasilHapus.setBackground(new Color(0,0,0,0));
        pop_up_berhasilEdit.setBackground(new Color(0,0,0,0));
        pop_up_konfirmasiHapus.setBackground(new Color(0,0,0,0));
        pop_up_batal.setBackground(new Color(0,0,0,0));
        //====================================================================//
        
    }
    
    private void tampil_id_otomatis(){
        
         try{
            koneksi();
            String sql = "select id_bantu as a from tb_penghuni order by id_bantu desc";
            java.sql.Connection con = (Connection)config.configDB();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()){
                String no_urut = rs.getString("a");
                int a = Integer.parseInt(no_urut);
                int panjang = no_urut.length();
                for (int i = 0; i < 2 - panjang; i++) {
                    a = a;
                }
                txt_tambah_kode_penghuni.setText("KP00"+Integer.toString(a+1));
                txt_tambah_id_bantu.setText(Integer.toString(a+1));
            }else{
                txt_tambah_kode_penghuni.setText("KP001");
                txt_tambah_id_bantu.setText("1");
            }
            
            rs.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

//        try{   
//            koneksi();
//            String sql = "SELECT id_bantu as a FROM tb_penghuni ORDER BY id_bantu ASC";
//            java.sql.Connection con = (Connection)config.configDB();
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//           // while(rs.next()){
//                if(rs.next()==false){
//                    txt_tambah_kode_penghuni.setText("KP001");
//                    txt_tambah_id_bantu.setText("1");
//                
//                }else{
//                    
//                    String no_urut = rs.getString("a");
//                    int a = Integer.parseInt(no_urut);
//                    int panjang = no_urut.length();
//                        for (int i = 0; i < 2 - panjang; i++) {
//                            a = a;
//                        }
//                    txt_tambah_kode_penghuni.setText("KP00"+Integer.toString(a+1));
//                    txt_tambah_id_bantu.setText(Integer.toString(a+1));  
//                }
//           // }
//        }catch (Exception e){
//            JOptionPane.showMessageDialog(null, e);
//        }
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
    
    public void hapusOpsi(){
         try{
              java.sql.Connection conn=(Connection)config.configDB();
              java.sql.Statement st=conn.createStatement();
              ResultSet res = st.executeQuery("SELECT * FROM tb_kamar JOIN tb_penghuni ON tb_kamar.noKamar = tb_penghuni.noKamar where id_penghuni = '"+txt_tambah_kode_penghuni.getText()+"'");
                 while(res.next()){
                    cmb_tambah_kamar.removeItem(res.getString("tb_kamar.noKamar"));
                    cmb_edit_kamar.removeItem(res.getString("tb_kamar.noKamar"));
                }

         }catch (Exception e){
             JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void hapus_combo(){
        cmb_tambah_kamar.removeAllItems();
        cmb_edit_kamar.removeAllItems();
    }
    
     public void tampil_kamar(){
        try{
        //String sql = "SELECT * FROM tb_kamar";
        //String sqll = "SELECT * FROM tb_kamar JOIN tb_penghuni ON tb_kamar.noKamar = tb_penghuni.noKamar ";
        java.sql.Connection conn=(Connection)config.configDB();
        java.sql.Statement st=conn.createStatement();
        ResultSet res = st.executeQuery("SELECT * FROM tb_kamar where status = 'kosong'");
       cmb_tambah_kamar.addItem("-- Pilih Kamar --");
        while(res.next()){
                cmb_tambah_kamar.addItem(res.getString("tb_kamar.noKamar"));       
        }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
     
     public static String formatDec(int val) {
        return String.format("%,d", val).replace(',', '.');
    }
   
     
    public void kosongkan(){
        txt_tambah_id_bantu.setText(null);
        txt_tambah_kode_penghuni.setText(null);
        txt_tambah_nama_penghuni.setText(null);
        cmb_tambah_kamar.setSelectedIndex(0);
        txt_tambah_tanggal.setDate(null);
        txt_tambah_alamat.setText(null);
        txt_tambah_keluar.setDate(null);
        txt_tambah_no_telp.setText(null);
        txt_tambah_harga.setText(null);
    }
    
    public void datatable(){
        
         DefaultTableModel tbl = new DefaultTableModel() {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tbl.addColumn("Kode Penghuni");
        tbl.addColumn("Nama");
        tbl.addColumn("Kamar");
        tbl.addColumn("Tanggal Masuk");
        tbl.addColumn("Alamat");
        tbl.addColumn("Tanggal Keluar");
        tbl.addColumn("No Telp");
       
        
        tabelPenghuni.setModel(tbl);
        try{
            String sql = "select id_penghuni, nama_penghuni, noKamar, tggl_masuk, alamat, tggl_keluar, no_telp from tb_penghuni";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next())
            {
                tbl.addRow(new Object[]{
                    res.getString("id_penghuni"),
                    res.getString("nama_penghuni"),
                    res.getString("noKamar"),
                    res.getString("tggl_masuk"),
                    res.getString("alamat"),
                    res.getString("tggl_keluar"),
                    res.getString("no_telp"),
                   
                    
                });
                tabelPenghuni.setModel(tbl);
            }
        }catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    public void cariData(){
        
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Kode Penghuni");
        tbl.addColumn("Nama");
        tbl.addColumn("Kamar");
        tbl.addColumn("Tanggal Masuk");
        tbl.addColumn("Alamat");
        tbl.addColumn("Tanggal Keluar");
        tbl.addColumn("No Telp");
        
        
        tabelPenghuni.setModel(tbl);
        
        String cari = txtCari.getText();
        
        try{
            String sql = "select id_penghuni, nama_penghuni, noKamar, tggl_masuk, alamat, tggl_keluar, no_telp from tb_penghuni where nama_penghuni like'%"+cari+"%'";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next())
            {
                tbl.addRow(new Object[]{
                    res.getString("id_penghuni"),
                    res.getString("nama_penghuni"),
                    res.getString("noKamar"),
                    res.getString("tggl_masuk"),
                    res.getString("alamat"),
                    res.getString("tggl_keluar"),
                    res.getString("no_telp"),
                  
                    
                });
                tabelPenghuni.setModel(tbl);
            }
        }catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    public static Date getTanggalFromTable(JTable table, int kolom){
        
        JTable tabelPenghuni = table;
        String str_tgl = String.valueOf(tabelPenghuni.getValueAt(tabelPenghuni.getSelectedRow(), kolom));
        Date tanggal = null;
        
        try{
            tanggal =  new SimpleDateFormat("yyy-MM-dd").parse(str_tgl);
        }
        catch(ParseException ex){
            Logger.getLogger(form_penghuni.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tanggal;
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_edit = new javax.swing.JPanel();
        txt_edit_alamat = new javax.swing.JTextField();
        txt_edit_no_telp = new javax.swing.JTextField();
        txt_edit_kode_penghuni = new javax.swing.JTextField();
        txt_edit_nama_penghuni = new javax.swing.JTextField();
        cmb_edit_kamar = new javax.swing.JComboBox<>();
        txt_edit_harga = new javax.swing.JTextField();
        btn_simpan_update = new javax.swing.JLabel();
        btn_edit_batal = new javax.swing.JLabel();
        kosong_edit_tanggal = new javax.swing.JCheckBox();
        bg_edit = new javax.swing.JLabel();
        txt_edit_id_bantu = new javax.swing.JLabel();
        panel_tambah = new javax.swing.JPanel();
        txt_tambah_alamat = new javax.swing.JTextField();
        txt_tambah_no_telp = new javax.swing.JTextField();
        txt_tambah_kode_penghuni = new javax.swing.JTextField();
        txt_tambah_nama_penghuni = new javax.swing.JTextField();
        cmb_tambah_kamar = new javax.swing.JComboBox<>();
        txt_tambah_harga = new javax.swing.JTextField();
        btn_simpan_tambah = new javax.swing.JLabel();
        btn_tambah_batal = new javax.swing.JLabel();
        kosong_tambah_tanggal = new javax.swing.JCheckBox();
        bg_tambah = new javax.swing.JLabel();
        txt_tambah_id_bantu = new javax.swing.JLabel();
        pop_up_batal = new javax.swing.JPanel();
        btn_close4 = new javax.swing.JLabel();
        btn_oke4 = new javax.swing.JLabel();
        btn_batal1 = new javax.swing.JLabel();
        bg_simpan3 = new javax.swing.JLabel();
        logout = new javax.swing.JPanel();
        btnX = new javax.swing.JLabel();
        btn_keluar = new javax.swing.JLabel();
        btn_logout = new javax.swing.JLabel();
        bg_logout = new javax.swing.JLabel();
        pop_up_konfirmasiHapus = new javax.swing.JPanel();
        btn_close3 = new javax.swing.JLabel();
        btn_oke3 = new javax.swing.JLabel();
        btn_batal = new javax.swing.JLabel();
        bg_simpan2 = new javax.swing.JLabel();
        pop_up_berhasilEdit = new javax.swing.JPanel();
        btn_close2 = new javax.swing.JLabel();
        btn_oke2 = new javax.swing.JLabel();
        bg_simpan1 = new javax.swing.JLabel();
        pop_up_berhasilHapus = new javax.swing.JPanel();
        btn_close1 = new javax.swing.JLabel();
        btn_oke1 = new javax.swing.JLabel();
        bg_pop_up_hapus = new javax.swing.JLabel();
        pop_up_berhasilSimpan = new javax.swing.JPanel();
        btn_close = new javax.swing.JLabel();
        btn_oke = new javax.swing.JLabel();
        bg_simpan = new javax.swing.JLabel();
        btnAkun = new javax.swing.JLabel();
        btnTambah = new javax.swing.JLabel();
        btnEdit = new javax.swing.JLabel();
        btnDelete = new javax.swing.JLabel();
        btnCari = new javax.swing.JLabel();
        btnpenghuniKost = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnKeluar = new javax.swing.JLabel();
        btnBereanda = new javax.swing.JLabel();
        btnTagihan = new javax.swing.JLabel();
        btnLaporan = new javax.swing.JLabel();
        btnkamarKost = new javax.swing.JLabel();
        jam_icon = new javax.swing.JLabel();
        lbl_jam = new javax.swing.JLabel();
        tanggal_icon = new javax.swing.JLabel();
        lbl_tanggal = new javax.swing.JLabel();
        cmbFilter = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPenghuni = new javax.swing.JTable();
        txtCari = new javax.swing.JTextField();
        btnKomplain = new javax.swing.JLabel();
        btnAkunPenghuni = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        panel_edit.setBackground(new java.awt.Color(0, 0, 0));
        panel_edit.setEnabled(false);
        panel_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_editMouseClicked(evt);
            }
        });
        panel_edit.setLayout(null);

        txt_edit_alamat.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_edit_alamat.setBorder(null);
        txt_edit_alamat.setOpaque(false);
        txt_edit_alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_edit_alamatActionPerformed(evt);
            }
        });
        panel_edit.add(txt_edit_alamat);
        txt_edit_alamat.setBounds(506, 558, 420, 50);

        txt_edit_no_telp.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_edit_no_telp.setBorder(null);
        txt_edit_no_telp.setOpaque(false);
        txt_edit_no_telp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_edit_no_telpActionPerformed(evt);
            }
        });
        panel_edit.add(txt_edit_no_telp);
        txt_edit_no_telp.setBounds(506, 686, 420, 50);

        txt_edit_kode_penghuni.setEditable(false);
        txt_edit_kode_penghuni.setBackground(new java.awt.Color(255, 255, 255));
        txt_edit_kode_penghuni.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_edit_kode_penghuni.setBorder(null);
        txt_edit_kode_penghuni.setOpaque(false);
        txt_edit_kode_penghuni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_edit_kode_penghuniMouseClicked(evt);
            }
        });
        txt_edit_kode_penghuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_edit_kode_penghuniActionPerformed(evt);
            }
        });
        panel_edit.add(txt_edit_kode_penghuni);
        txt_edit_kode_penghuni.setBounds(507, 302, 420, 50);

        txt_edit_nama_penghuni.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_edit_nama_penghuni.setBorder(null);
        txt_edit_nama_penghuni.setOpaque(false);
        txt_edit_nama_penghuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_edit_nama_penghuniActionPerformed(evt);
            }
        });
        panel_edit.add(txt_edit_nama_penghuni);
        txt_edit_nama_penghuni.setBounds(995, 302, 420, 50);

        cmb_edit_kamar.setBackground(new java.awt.Color(239, 246, 246));
        cmb_edit_kamar.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        cmb_edit_kamar.setForeground(new java.awt.Color(102, 102, 102));
        cmb_edit_kamar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih No Kamar" }));
        cmb_edit_kamar.setBorder(null);
        cmb_edit_kamar.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_edit_kamarPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        panel_edit.add(cmb_edit_kamar);
        cmb_edit_kamar.setBounds(495, 430, 445, 50);

        txt_edit_harga.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_edit_harga.setBorder(null);
        txt_edit_harga.setOpaque(false);
        txt_edit_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_edit_hargaActionPerformed(evt);
            }
        });
        panel_edit.add(txt_edit_harga);
        txt_edit_harga.setBounds(995, 686, 420, 50);

        btn_simpan_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpan_updateMouseClicked(evt);
            }
        });
        panel_edit.add(btn_simpan_update);
        btn_simpan_update.setBounds(970, 842, 185, 53);

        btn_edit_batal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_edit_batalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_edit_batalMouseEntered(evt);
            }
        });
        panel_edit.add(btn_edit_batal);
        btn_edit_batal.setBounds(765, 842, 185, 53);

        kosong_edit_tanggal.setText("Kosongkan Tanggal Keluar");
        kosong_edit_tanggal.setOpaque(false);
        kosong_edit_tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kosong_edit_tanggalActionPerformed(evt);
            }
        });
        panel_edit.add(kosong_edit_tanggal);
        kosong_edit_tanggal.setBounds(981, 610, 180, 23);

        bg_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edit_penghuni.png"))); // NOI18N
        panel_edit.add(bg_edit);
        bg_edit.setBounds(405, 140, 1110, 800);

        txt_edit_id_bantu.setText("jLabel1");
        panel_edit.add(txt_edit_id_bantu);
        txt_edit_id_bantu.setBounds(410, 320, 34, 14);

        getContentPane().add(panel_edit);
        panel_edit.setBounds(0, 0, 1920, 1080);

        panel_tambah.setBackground(new java.awt.Color(0, 0, 0));
        panel_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_tambahMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel_tambahMouseEntered(evt);
            }
        });
        panel_tambah.setLayout(null);

        txt_tambah_alamat.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_tambah_alamat.setBorder(null);
        txt_tambah_alamat.setOpaque(false);
        txt_tambah_alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tambah_alamatActionPerformed(evt);
            }
        });
        panel_tambah.add(txt_tambah_alamat);
        txt_tambah_alamat.setBounds(506, 558, 420, 50);

        txt_tambah_no_telp.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_tambah_no_telp.setBorder(null);
        txt_tambah_no_telp.setOpaque(false);
        txt_tambah_no_telp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tambah_no_telpActionPerformed(evt);
            }
        });
        panel_tambah.add(txt_tambah_no_telp);
        txt_tambah_no_telp.setBounds(506, 686, 420, 50);

        txt_tambah_kode_penghuni.setEditable(false);
        txt_tambah_kode_penghuni.setBackground(new java.awt.Color(255, 255, 255));
        txt_tambah_kode_penghuni.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_tambah_kode_penghuni.setBorder(null);
        txt_tambah_kode_penghuni.setOpaque(false);
        txt_tambah_kode_penghuni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_tambah_kode_penghuniMouseClicked(evt);
            }
        });
        txt_tambah_kode_penghuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tambah_kode_penghuniActionPerformed(evt);
            }
        });
        panel_tambah.add(txt_tambah_kode_penghuni);
        txt_tambah_kode_penghuni.setBounds(507, 302, 420, 50);

        txt_tambah_nama_penghuni.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_tambah_nama_penghuni.setBorder(null);
        txt_tambah_nama_penghuni.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_tambah_nama_penghuni.setOpaque(false);
        txt_tambah_nama_penghuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tambah_nama_penghuniActionPerformed(evt);
            }
        });
        panel_tambah.add(txt_tambah_nama_penghuni);
        txt_tambah_nama_penghuni.setBounds(995, 302, 420, 50);

        cmb_tambah_kamar.setBackground(new java.awt.Color(239, 246, 246));
        cmb_tambah_kamar.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        cmb_tambah_kamar.setForeground(new java.awt.Color(102, 102, 102));
        cmb_tambah_kamar.setBorder(null);
        cmb_tambah_kamar.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_tambah_kamarPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        panel_tambah.add(cmb_tambah_kamar);
        cmb_tambah_kamar.setBounds(495, 430, 445, 50);

        txt_tambah_harga.setEditable(false);
        txt_tambah_harga.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_tambah_harga.setBorder(null);
        txt_tambah_harga.setOpaque(false);
        txt_tambah_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tambah_hargaActionPerformed(evt);
            }
        });
        panel_tambah.add(txt_tambah_harga);
        txt_tambah_harga.setBounds(995, 686, 420, 50);

        btn_simpan_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpan_tambahMouseClicked(evt);
            }
        });
        panel_tambah.add(btn_simpan_tambah);
        btn_simpan_tambah.setBounds(970, 842, 185, 53);

        btn_tambah_batal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tambah_batalMouseClicked(evt);
            }
        });
        panel_tambah.add(btn_tambah_batal);
        btn_tambah_batal.setBounds(765, 842, 185, 53);

        kosong_tambah_tanggal.setText("Kosongkan Tanggal Keluar");
        kosong_tambah_tanggal.setOpaque(false);
        kosong_tambah_tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kosong_tambah_tanggalActionPerformed(evt);
            }
        });
        panel_tambah.add(kosong_tambah_tanggal);
        kosong_tambah_tanggal.setBounds(981, 610, 180, 23);

        bg_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tambah_penghuni.png"))); // NOI18N
        panel_tambah.add(bg_tambah);
        bg_tambah.setBounds(405, 140, 1110, 800);

        txt_tambah_id_bantu.setText("jLabel1");
        panel_tambah.add(txt_tambah_id_bantu);
        txt_tambah_id_bantu.setBounds(460, 230, 34, 14);

        getContentPane().add(panel_tambah);
        panel_tambah.setBounds(0, 0, 1920, 1080);

        pop_up_batal.setBackground(new java.awt.Color(0, 0, 0));
        pop_up_batal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_close4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/x normal.png"))); // NOI18N
        btn_close4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_close4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_close4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_close4MouseExited(evt);
            }
        });
        pop_up_batal.add(btn_close4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1162, 429, 31, 31));

        btn_oke4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/btn oke normal.png"))); // NOI18N
        btn_oke4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_oke4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_oke4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_oke4MouseExited(evt);
            }
        });
        pop_up_batal.add(btn_oke4, new org.netbeans.lib.awtextra.AbsoluteConstraints(967, 596, 120, 35));

        btn_batal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/btn batal normal.png"))); // NOI18N
        btn_batal1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_batal1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_batal1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_batal1MouseExited(evt);
            }
        });
        pop_up_batal.add(btn_batal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(833, 596, 120, 35));

        bg_simpan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup_batal.png"))); // NOI18N
        pop_up_batal.add(bg_simpan3, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 418, 511, 243));

        getContentPane().add(pop_up_batal);
        pop_up_batal.setBounds(0, 0, 1920, 1080);

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
        bg_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bg_logoutMouseClicked(evt);
            }
        });
        logout.add(bg_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 418, -1, -1));

        getContentPane().add(logout);
        logout.setBounds(0, 0, 1920, 1080);

        pop_up_konfirmasiHapus.setBackground(new java.awt.Color(0, 0, 0));
        pop_up_konfirmasiHapus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_close3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/x normal.png"))); // NOI18N
        btn_close3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_close3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_close3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_close3MouseExited(evt);
            }
        });
        pop_up_konfirmasiHapus.add(btn_close3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1162, 429, 31, 31));

        btn_oke3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/btn oke normal.png"))); // NOI18N
        btn_oke3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_oke3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_oke3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_oke3MouseExited(evt);
            }
        });
        pop_up_konfirmasiHapus.add(btn_oke3, new org.netbeans.lib.awtextra.AbsoluteConstraints(967, 577, 120, 35));

        btn_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/btn batal normal.png"))); // NOI18N
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
        });
        pop_up_konfirmasiHapus.add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(833, 577, 120, 35));

        bg_simpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup_hapus.png"))); // NOI18N
        pop_up_konfirmasiHapus.add(bg_simpan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 418, 511, 243));

        getContentPane().add(pop_up_konfirmasiHapus);
        pop_up_konfirmasiHapus.setBounds(0, 0, 1920, 1080);

        pop_up_berhasilEdit.setBackground(new java.awt.Color(0, 0, 0));
        pop_up_berhasilEdit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_close2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/x normal.png"))); // NOI18N
        btn_close2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_close2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_close2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_close2MouseExited(evt);
            }
        });
        pop_up_berhasilEdit.add(btn_close2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1162, 429, 31, 31));

        btn_oke2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/btn oke normal.png"))); // NOI18N
        btn_oke2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_oke2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_oke2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_oke2MouseExited(evt);
            }
        });
        pop_up_berhasilEdit.add(btn_oke2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 574, 120, 35));

        bg_simpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup_berhasilEdit.png"))); // NOI18N
        pop_up_berhasilEdit.add(bg_simpan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 418, 511, 243));

        getContentPane().add(pop_up_berhasilEdit);
        pop_up_berhasilEdit.setBounds(0, 0, 1920, 1080);

        pop_up_berhasilHapus.setBackground(new java.awt.Color(0, 0, 0));
        pop_up_berhasilHapus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_close1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/x normal.png"))); // NOI18N
        btn_close1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_close1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_close1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_close1MouseExited(evt);
            }
        });
        pop_up_berhasilHapus.add(btn_close1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1162, 429, 31, 31));

        btn_oke1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/btn oke normal.png"))); // NOI18N
        btn_oke1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_oke1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_oke1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_oke1MouseExited(evt);
            }
        });
        pop_up_berhasilHapus.add(btn_oke1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 574, 120, 35));

        bg_pop_up_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/popup_berhasilHapus.png"))); // NOI18N
        bg_pop_up_hapus.setToolTipText("");
        pop_up_berhasilHapus.add(bg_pop_up_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 418, 511, 243));

        getContentPane().add(pop_up_berhasilHapus);
        pop_up_berhasilHapus.setBounds(0, 0, 1920, 1080);

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
        getContentPane().add(btnAkun);
        btnAkun.setBounds(1680, 30, 200, 60);

        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_penghuni/btn tambah normal.png"))); // NOI18N
        btnTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTambahMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTambahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTambahMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnTambahMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnTambahMouseReleased(evt);
            }
        });
        getContentPane().add(btnTambah);
        btnTambah.setBounds(390, 210, 200, 70);

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn edit normal.png"))); // NOI18N
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEditMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnEditMouseReleased(evt);
            }
        });
        getContentPane().add(btnEdit);
        btnEdit.setBounds(600, 210, 200, 70);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_penghuni/btn delete normal.png"))); // NOI18N
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDeleteMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDeleteMouseReleased(evt);
            }
        });
        getContentPane().add(btnDelete);
        btnDelete.setBounds(810, 210, 196, 65);

        btnCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariMouseClicked(evt);
            }
        });
        getContentPane().add(btnCari);
        btnCari.setBounds(1414, 210, 40, 40);

        btnpenghuniKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab penghuni kost normal.png"))); // NOI18N
        btnpenghuniKost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnpenghuniKostMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnpenghuniKostMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnpenghuniKostMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnpenghuniKostMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnpenghuniKostMouseReleased(evt);
            }
        });
        getContentPane().add(btnpenghuniKost);
        btnpenghuniKost.setBounds(-35, 412, 353, 69);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tutupan.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(248, 951, 126, 96);

        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab keluar none.png"))); // NOI18N
        btnKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKeluarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKeluarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnKeluarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnKeluarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnKeluarMouseReleased(evt);
            }
        });
        getContentPane().add(btnKeluar);
        btnKeluar.setBounds(-35, 960, 353, 74);

        btnBereanda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab beranda none.png"))); // NOI18N
        btnBereanda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBereandaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBereandaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBereandaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBereandaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnBereandaMouseReleased(evt);
            }
        });
        getContentPane().add(btnBereanda);
        btnBereanda.setBounds(-35, 270, 353, 69);

        btnTagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan none.png"))); // NOI18N
        btnTagihan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTagihanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTagihanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTagihanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnTagihanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnTagihanMouseReleased(evt);
            }
        });
        getContentPane().add(btnTagihan);
        btnTagihan.setBounds(-35, 483, 353, 69);

        btnLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan none.png"))); // NOI18N
        btnLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLaporanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLaporanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLaporanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLaporanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLaporanMouseReleased(evt);
            }
        });
        getContentPane().add(btnLaporan);
        btnLaporan.setBounds(-35, 554, 353, 69);

        btnkamarKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab kamar kos none.png"))); // NOI18N
        btnkamarKost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnkamarKostMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnkamarKostMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnkamarKostMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnkamarKostMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnkamarKostMouseReleased(evt);
            }
        });
        getContentPane().add(btnkamarKost);
        btnkamarKost.setBounds(-35, 341, 353, 69);

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

        cmbFilter.setFont(new java.awt.Font("Microsoft Tai Le", 0, 20)); // NOI18N
        cmbFilter.setForeground(new java.awt.Color(102, 102, 102));
        cmbFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----Pilih-----", "Tanggal Masuk", "Tanggal Keluar" }));
        cmbFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFilterActionPerformed(evt);
            }
        });
        getContentPane().add(cmbFilter);
        cmbFilter.setBounds(1230, 210, 160, 40);

        tabelPenghuni.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        tabelPenghuni.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nama", "Kamar", "Tanggal Masuk", "Tanggal Keluar"
            }
        ));
        tabelPenghuni.setGridColor(new java.awt.Color(204, 204, 204));
        tabelPenghuni.setRowHeight(40);
        tabelPenghuni.getTableHeader().setReorderingAllowed(false);
        tabelPenghuni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPenghuniMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabelPenghuniMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelPenghuniMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPenghuni);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(400, 360, 1410, 300);

        txtCari.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txtCari.setBorder(null);
        txtCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtCariMouseReleased(evt);
            }
        });
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });
        getContentPane().add(txtCari);
        txtCari.setBounds(1460, 210, 330, 40);

        btnKomplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain none.png"))); // NOI18N
        btnKomplain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKomplainMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKomplainMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnKomplainMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnKomplainMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnKomplainMouseReleased(evt);
            }
        });
        getContentPane().add(btnKomplain);
        btnKomplain.setBounds(-35, 625, 353, 69);

        btnAkunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni none.png"))); // NOI18N
        btnAkunPenghuni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAkunPenghuniMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAkunPenghuniMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAkunPenghuniMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAkunPenghuniMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAkunPenghuniMouseReleased(evt);
            }
        });
        getContentPane().add(btnAkunPenghuni);
        btnAkunPenghuni.setBounds(-35, 696, 353, 69);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/Rectangle 53.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 280, 320, 278);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/tutupan.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(800, 180, 220, 160);

        bg.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/form_penghuni.png"))); // NOI18N
        getContentPane().add(bg);
        bg.setBounds(0, 0, 1920, 1080);

        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(280, 190, 34, 14);

        setSize(new java.awt.Dimension(1920, 1080));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
        // TODO add your handling code here:
        if(klikedit == false){
            JOptionPane.showMessageDialog(this, "Pilih data yang akan diedit!");
        }else{
           
        try{
        java.sql.Connection conn=(Connection)config.configDB();
        java.sql.Statement st=conn.createStatement();
        ResultSet res = st.executeQuery("SELECT * FROM tb_kamar ");
        cmb_tambah_kamar.addItem("-- Pilih Kamar --");
        while(res.next()){ 
                cmb_edit_kamar.addItem(res.getString("tb_kamar.noKamar")); 
                
        }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    
        panel_edit.setVisible(true);
        editData2();
       
        }
    }//GEN-LAST:event_btnEditMouseClicked

    private void cmbFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFilterActionPerformed
        // TODO add your handling code here:
         try{
            databaru = false;
            
            
            int filter = cmbFilter.getSelectedIndex();
            System.out.println(filter);
            java.sql.Connection conn = (Connection)config.configDB();
            
            Statement stm = conn.createStatement();
            String msql;
            if (filter == 0) {
                msql = "SELECT * FROM tb_penghuni GROUP BY noKamar ORDER BY noKamar ASC";
            }
            else if (filter == 1){
                msql = "SELECT * FROM tb_penghuni GROUP BY id_penghuni ORDER BY tggl_masuk ASC";
            }
            else if (filter == 2){
                msql = "SELECT * FROM tb_penghuni GROUP BY id_penghuni ORDER BY tggl_keluar ASC";
            }
            else{
                msql = "SELECT * FROM tb_penghuni GROUP BY noKamar ORDER BY noKamar ASC";
                
            }
            System.out.println(msql);
            ResultSet result = stm.executeQuery(msql);
            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("Kode Penghuni");
            tbl.addColumn("Nama");
            tbl.addColumn("Kamar");
            tbl.addColumn("Tanggal Masuk");
            tbl.addColumn("Alamat");
            tbl.addColumn("Tanggal Keluar");
            tbl.addColumn("No Telp");
           
            tabelPenghuni.setModel(tbl);
            
            
            while (result.next()){
                tbl.addRow(new Object[]{
                    result.getString("id_penghuni"),
                    result.getString("nama_penghuni"),
                    result.getString("noKamar"),
                    result.getString("tggl_masuk"),
                    result.getString("alamat"),
                    result.getString("tggl_keluar"),
                    result.getString("no_telp"),
                    
                    result.getString("id_bantu")
                });
              tabelPenghuni.setModel(tbl);
            }
            
             
            
        } catch (SQLException ex) {
            Logger.getLogger(form_penghuni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmbFilterActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        setExtendedState(form_penghuni.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void btnKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKeluarMouseClicked
        // TODO add your handling code here:
        logout.setVisible(true);
    }//GEN-LAST:event_btnKeluarMouseClicked

    private void tabelPenghuniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPenghuniMouseClicked
        // TODO add your handling code here:
    
        klikedit = true;
        if(klikedit == false){
            JOptionPane.showMessageDialog(this, "Pilih data yang akan diedit!");
        }
        
        txt_edit_tanggal.setDate(getTanggalFromTable(tabelPenghuni, 3));
        txt_edit_keluar.setDate(getTanggalFromTable(tabelPenghuni, 5));
    }//GEN-LAST:event_tabelPenghuniMouseClicked

    private void txtCariMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCariMouseReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtCariMouseReleased

    private void tabelPenghuniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPenghuniMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelPenghuniMouseEntered

    private void tabelPenghuniMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPenghuniMousePressed
        klikedit = true; 
        
    }//GEN-LAST:event_tabelPenghuniMousePressed

    private void btnEditMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMousePressed
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn edit press.png")));
       
    }//GEN-LAST:event_btnEditMousePressed

    private void panel_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_tambahMouseClicked
        // TODO add your handling code here:
        this.setVisible(true);
    }//GEN-LAST:event_panel_tambahMouseClicked

    private void txt_tambah_alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tambah_alamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tambah_alamatActionPerformed

    private void txt_tambah_no_telpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tambah_no_telpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tambah_no_telpActionPerformed

    private void txt_tambah_kode_penghuniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tambah_kode_penghuniMouseClicked

    }//GEN-LAST:event_txt_tambah_kode_penghuniMouseClicked

    private void txt_tambah_kode_penghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tambah_kode_penghuniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tambah_kode_penghuniActionPerformed

    private void txt_tambah_nama_penghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tambah_nama_penghuniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tambah_nama_penghuniActionPerformed

    private void txt_tambah_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tambah_hargaActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txt_tambah_hargaActionPerformed

    private void btn_simpan_tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpan_tambahMouseClicked
        // TODO add your handling code here:
       try {
        String tampilan = "yyy-MM-dd";
        
        SimpleDateFormat fm = new SimpleDateFormat (tampilan);
        
        String tanggal = String.valueOf(fm.format(txt_tambah_tanggal.getDate()));
        
        
        if(kosong_tanggal==true){
            txt_tambah_keluar.getDateFormatString();
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement("INSERT INTO "
                    + "tb_penghuni VALUES ('"+txt_tambah_kode_penghuni.getText()+
                    "','"+txt_tambah_nama_penghuni.getText()+"','"
                    +cmb_tambah_kamar.getSelectedItem()+"',"
            +"'"+tanggal+"', NULL, '"+txt_tambah_alamat.getText()+"','"
            +txt_tambah_no_telp.getText()+"','"+txt_tambah_id_bantu.getText()+"')");
            
            pst.execute();

            panel_tambah.setVisible(false);
            pop_up_berhasilSimpan.setVisible(true);
            getData();
            hapusOpsi();
            kosongkan();
            //kosong_tambah_tanggal.setSelected(false);
            hapus_combo();

        }
        else if(kosong_tanggal==false){
         
            String keluar = String.valueOf(fm.format(txt_tambah_keluar.getDate()));
            String sql= "INSERT INTO tb_penghuni VALUES ('"+txt_tambah_kode_penghuni.getText()
            +"','"+txt_tambah_nama_penghuni.getText()+"','"+cmb_tambah_kamar.getSelectedItem()+"',"
            +"'"+tanggal+"','"+keluar+"', '"+txt_tambah_alamat.getText()+"','"
            +txt_tambah_no_telp.getText()+"','"+txt_tambah_id_bantu.getText()+"')";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();

            panel_tambah.setVisible(false);
            pop_up_berhasilSimpan.setVisible(true);
            //new pop_upSimpan().setVisible(true);
            getData();
            //tampil_kamar();
            hapusOpsi();
            kosongkan();
            //kosong_tambah_tanggal.setSelected(false);
            hapus_combo();

        }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
    }
    }//GEN-LAST:event_btn_simpan_tambahMouseClicked

    private void btn_tambah_batalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambah_batalMouseClicked
        // TODO add your handling code here:
        
        cmb_tambah_kamar.setEnabled(false);
        kosong_tambah_tanggal.setEnabled(false);
        txt_tambah_tanggal.setEnabled(false);
        txt_tambah_keluar.setEnabled(false);
        txt_tambah_alamat.setEnabled(false);
        pop_up_batal.setVisible(true);
//        int respon = JOptionPane.showConfirmDialog(this, "Apakah anda yakin ingin membuang perubahan ?", "Komfirmasi",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//        
//        if(respon == JOptionPane.YES_OPTION){
//        kosongkan();
//        
//        
//        }
//        panel_tambah.setVisible(false);
        //hapus_combo();
    }//GEN-LAST:event_btn_tambah_batalMouseClicked

    private void kosong_tambah_tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kosong_tambah_tanggalActionPerformed
        // TODO add your handling code here:
        if(kosong_tambah_tanggal.isSelected()){
            kosong_tanggal = true;
            txt_tambah_keluar.setEnabled(false);
            kosong_tambah_tanggal.setSelected(true);
            txt_tambah_keluar.setDate(null);
        }
        else{
             kosong_tanggal = false;
            txt_tambah_keluar.setEnabled(true);
            kosong_tambah_tanggal.setSelected(false);
        }
    }//GEN-LAST:event_kosong_tambah_tanggalActionPerformed

    private void txt_edit_alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_edit_alamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_edit_alamatActionPerformed

    private void txt_edit_no_telpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_edit_no_telpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_edit_no_telpActionPerformed

    private void txt_edit_kode_penghuniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_edit_kode_penghuniMouseClicked

    }//GEN-LAST:event_txt_edit_kode_penghuniMouseClicked

    private void txt_edit_kode_penghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_edit_kode_penghuniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_edit_kode_penghuniActionPerformed

    private void txt_edit_nama_penghuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_edit_nama_penghuniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_edit_nama_penghuniActionPerformed

    private void txt_edit_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_edit_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_edit_hargaActionPerformed

    private void btn_simpan_updateMouseClicked(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
        
        try {
        String tampilan = "yyy-MM-dd";
       // String tampilan2 = "yyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat (tampilan);
        //SimpleDateFormat fm2 = new SimpleDateFormat (tampilan2);
        
        String tanggal = String.valueOf(fm.format(txt_edit_tanggal.getDate()));
        

        if(kosong_tanggal==true){
            txt_edit_keluar.getDateFormatString();
            String sql = "UPDATE tb_penghuni "
                    + "SET nama_penghuni = '"+txt_edit_nama_penghuni.getText()+"'"
                    + ", noKamar = '"
                    +cmb_edit_kamar.getSelectedItem()+"', tggl_masuk = '"+tanggal
                    +"', tggl_keluar = NULL, alamat = '"
                    +txt_edit_alamat.getText()+"', no_telp = '"
                    +txt_edit_no_telp.getText()+"'WHERE tb_penghuni.id_penghuni ="
                    + " '"+txt_edit_kode_penghuni.getText()+"'";

            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            panel_edit.setVisible(false);
            pop_up_berhasilEdit.setVisible(true);
            hapusOpsi();
            getData();
            hapus_combo();
        }
        
        else{
        String keluar = String.valueOf(fm.format(txt_edit_keluar.getDate()));
        String sql = "UPDATE tb_penghuni "
                    + "SET nama_penghuni = '"+txt_edit_nama_penghuni.getText()+
                "', noKamar = '"
                    +cmb_edit_kamar.getSelectedItem()+"', tggl_masuk = '"
                    +tanggal+"', tggl_keluar = '"
                    +keluar+"', alamat = '"
                    +txt_edit_alamat.getText()+"', no_telp = '"
                    +txt_edit_no_telp.getText()+"'WHERE tb_penghuni.id_penghuni "
                + "= '"+txt_edit_kode_penghuni.getText()+"'";

            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            panel_edit.setVisible(false);
            pop_up_berhasilEdit.setVisible(true);
            hapusOpsi();
            getData();
            hapus_combo();
        }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e);
        }                                      
    }                                              

    private void btn_edit_batalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_batalMouseClicked
        // TODO add your handling code here:
        cmb_edit_kamar.setEnabled(false);
        kosong_edit_tanggal.setEnabled(false);
        txt_edit_tanggal.setEnabled(false);
        txt_edit_keluar.setEnabled(false);
        txt_edit_alamat.setEnabled(false);
        pop_up_batal.setVisible(true);
        //hapus_combo();
    }//GEN-LAST:event_btn_edit_batalMouseClicked

    private void kosong_edit_tanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kosong_edit_tanggalActionPerformed
        // TODO add your handling code here:
        if(kosong_edit_tanggal.isSelected()){
            kosong_tanggal = true;
            txt_edit_keluar.setEnabled(false);
            kosong_edit_tanggal.setSelected(true);
            txt_edit_keluar.setDate(null);
        }
        else{
             kosong_tanggal = false;
            txt_edit_keluar.setEnabled(true);
            kosong_edit_tanggal.setSelected(false);
        }
    }//GEN-LAST:event_kosong_edit_tanggalActionPerformed

    private void panel_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_editMouseClicked
        // TODO add your handling code here:
        this.setVisible(true);
    }//GEN-LAST:event_panel_editMouseClicked

    private void panel_tambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_tambahMouseEntered
       this.setVisible(true);
    }//GEN-LAST:event_panel_tambahMouseEntered

    private void cmb_edit_kamarPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_edit_kamarPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
//        String sql = "select harga from tb_kamar where noKamar ='"+cmb_edit_kamar.getSelectedItem()+"'";
//        try{
//            java.sql.Connection conn=(Connection)config.configDB();
//            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
//            ResultSet res = pst.executeQuery(sql);
//            if(res.next()){
//            txt_edit_harga.setText(res.getString("harga"));
//            }
//            
//        }catch (Exception e){
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
    }//GEN-LAST:event_cmb_edit_kamarPopupMenuWillBecomeInvisible

    private void cmb_tambah_kamarPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_tambah_kamarPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String sql = "select harga from tb_kamar where noKamar ='"+cmb_tambah_kamar.getSelectedItem()+"'";
        try{
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            ResultSet res = pst.executeQuery(sql);
            
            if(res.next()){
                
            //txt_tambah_harga.setText(res.getString("harga"));
            txt_tambah_harga.setText(formatDec(res.getInt("harga")));
            }
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_cmb_tambah_kamarPopupMenuWillBecomeInvisible

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        // TODO add your handling code here:
        cariData();
    }//GEN-LAST:event_txtCariKeyReleased

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        // TODO add your handling code here:
        klikhapus = true;
        if(klikedit == false){
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!");
        }else{
            int respon = JOptionPane.showConfirmDialog(this, "Apakah anda yakin "
                    + "ingin menghapus data ini ?", "Komfirmasi",JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if(respon == JOptionPane.YES_OPTION){
                try{
                    int row = tabelPenghuni.getSelectedRow();
                    String id_penghuni = tabelPenghuni.getModel().getValueAt (row, 0).toString();
                    String resultSet = "DELETE FROM tb_penghuni WHERE id_penghuni='"+ id_penghuni +"'";

                    java.sql.Connection conn = (Connection)config.configDB();
                    java.sql.PreparedStatement pst = conn.prepareStatement(resultSet);
                    pst.execute();
                    pop_up_berhasilHapus.setVisible(true);

                    getData();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Data gagal dihapus");
                }}

                //new pop_upHapus().setVisible(true);
            }
        
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        // TODO add your handling code here:
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_penghuni/btn delete entered.png")));
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseExited
        // TODO add your handling code here:
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_penghuni/btn delete normal.png")));
    }//GEN-LAST:event_btnDeleteMouseExited

    private void btnDeleteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMousePressed
        // TODO add your handling code here:
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_penghuni/btn delete press.png")));
    }//GEN-LAST:event_btnDeleteMousePressed

    private void btnDeleteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseReleased
        // TODO add your handling code here:
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_penghuni/btn delete entered.png")));
    }//GEN-LAST:event_btnDeleteMouseReleased

    private void btn_edit_batalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_batalMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_edit_batalMouseEntered

    private void btnEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseEntered
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn edit entered.png")));
    }//GEN-LAST:event_btnEditMouseEntered

    private void btnEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseExited
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn edit normal.png")));
    }//GEN-LAST:event_btnEditMouseExited

    private void btnEditMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseReleased
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover/btn edit press.png")));
    }//GEN-LAST:event_btnEditMouseReleased

    private void btnTambahMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseReleased
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_penghuni/btn tambah entered.png")));
    }//GEN-LAST:event_btnTambahMouseReleased

    private void btnTambahMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMousePressed
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_penghuni/btn tambah press.png")));
    }//GEN-LAST:event_btnTambahMousePressed

    private void btnTambahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseExited
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_penghuni/btn tambah normal.png")));
    }//GEN-LAST:event_btnTambahMouseExited

    private void btnTambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseEntered
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_penghuni/btn tambah entered.png")));
    }//GEN-LAST:event_btnTambahMouseEntered

    private void btnTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseClicked
        // TODO add your handling code here:
        panel_tambah.setVisible(true);
        tampil_id_otomatis();
        tampil_kamar();
    }//GEN-LAST:event_btnTambahMouseClicked

    private void btnBereandaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBereandaMouseClicked
        this.setVisible(false);
        new beranda().setVisible(true);
    }//GEN-LAST:event_btnBereandaMouseClicked

    private void btnkamarKostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnkamarKostMouseClicked
        this.setVisible(false);
        new form_kamar().setVisible(true);
    }//GEN-LAST:event_btnkamarKostMouseClicked

    private void btnpenghuniKostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpenghuniKostMouseClicked
        this.setVisible(false);
        new form_penghuni().setVisible(true);
    }//GEN-LAST:event_btnpenghuniKostMouseClicked

    private void btnTagihanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTagihanMouseClicked
        this.setVisible(false);
        new form_tagihan().setVisible(true);
    }//GEN-LAST:event_btnTagihanMouseClicked

    private void btnLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLaporanMouseClicked
        this.setVisible(false);
        new form_laporan().setVisible(true);
    }//GEN-LAST:event_btnLaporanMouseClicked

    private void btnKomplainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKomplainMouseClicked
        this.setVisible(false);
        new form_komplain().setVisible(true);
    }//GEN-LAST:event_btnKomplainMouseClicked

    private void btnAkunPenghuniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAkunPenghuniMouseClicked
        this.setVisible(false);
        new form_akunPenghuni().setVisible(true);
    }//GEN-LAST:event_btnAkunPenghuniMouseClicked

    private void btnpenghuniKostMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpenghuniKostMouseEntered
        btnpenghuniKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab penghuni kost entered.png")));
    }//GEN-LAST:event_btnpenghuniKostMouseEntered

    private void btnpenghuniKostMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpenghuniKostMouseExited
        btnpenghuniKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab penghuni kost normal.png")));
    }//GEN-LAST:event_btnpenghuniKostMouseExited

    private void btnpenghuniKostMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpenghuniKostMousePressed
        btnpenghuniKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab penghuni kost press.png")));
    }//GEN-LAST:event_btnpenghuniKostMousePressed

    private void btnpenghuniKostMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnpenghuniKostMouseReleased
        btnpenghuniKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab penghuni kost entered.png")));
    }//GEN-LAST:event_btnpenghuniKostMouseReleased

    private void btnkamarKostMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnkamarKostMouseEntered
        btnkamarKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab kamar kos entered.png")));
    }//GEN-LAST:event_btnkamarKostMouseEntered

    private void btnkamarKostMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnkamarKostMouseExited
        btnkamarKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab kamar kos none.png")));
    }//GEN-LAST:event_btnkamarKostMouseExited

    private void btnkamarKostMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnkamarKostMousePressed
        btnkamarKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab kamar kos press.png")));
    }//GEN-LAST:event_btnkamarKostMousePressed

    private void btnkamarKostMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnkamarKostMouseReleased
        btnkamarKost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab kamar kos entered.png")));
    }//GEN-LAST:event_btnkamarKostMouseReleased

    private void btnBereandaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBereandaMouseEntered
        btnBereanda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab beranda entered.png")));
    }//GEN-LAST:event_btnBereandaMouseEntered

    private void btnBereandaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBereandaMouseExited
       btnBereanda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab beranda none.png")));
    }//GEN-LAST:event_btnBereandaMouseExited

    private void btnBereandaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBereandaMousePressed
        btnBereanda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab beranda press.png")));
    }//GEN-LAST:event_btnBereandaMousePressed

    private void btnBereandaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBereandaMouseReleased
        btnBereanda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab beranda entered.png")));
    }//GEN-LAST:event_btnBereandaMouseReleased

    private void btnTagihanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTagihanMouseEntered
       btnTagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan entered.png")));
    }//GEN-LAST:event_btnTagihanMouseEntered

    private void btnTagihanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTagihanMouseExited
        btnTagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan none.png")));
    }//GEN-LAST:event_btnTagihanMouseExited

    private void btnLaporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLaporanMouseEntered
        btnLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan entered.png")));
    }//GEN-LAST:event_btnLaporanMouseEntered

    private void btnTagihanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTagihanMousePressed
        btnTagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan press.png")));
    }//GEN-LAST:event_btnTagihanMousePressed

    private void btnTagihanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTagihanMouseReleased
        btnTagihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab tagihan entered.png")));
    }//GEN-LAST:event_btnTagihanMouseReleased

    private void btnLaporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLaporanMouseExited
       btnLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan none.png")));
    }//GEN-LAST:event_btnLaporanMouseExited

    private void btnLaporanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLaporanMousePressed
        btnLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan press.png")));
    }//GEN-LAST:event_btnLaporanMousePressed

    private void btnLaporanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLaporanMouseReleased
        btnLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan entered.png")));
    }//GEN-LAST:event_btnLaporanMouseReleased

    private void btnKomplainMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKomplainMouseEntered
        btnKomplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain entered.png")));
    }//GEN-LAST:event_btnKomplainMouseEntered

    private void btnKomplainMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKomplainMouseExited
        btnKomplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain none.png")));
    }//GEN-LAST:event_btnKomplainMouseExited

    private void btnKomplainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKomplainMousePressed
        btnKomplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain press.png")));
    }//GEN-LAST:event_btnKomplainMousePressed

    private void btnKomplainMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKomplainMouseReleased
        btnKomplain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab komplain entered.png")));
    }//GEN-LAST:event_btnKomplainMouseReleased

    private void btnAkunPenghuniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAkunPenghuniMouseEntered
        btnAkunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni entered.png")));
    }//GEN-LAST:event_btnAkunPenghuniMouseEntered

    private void btnAkunPenghuniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAkunPenghuniMouseExited
        btnAkunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni none.png")));
    }//GEN-LAST:event_btnAkunPenghuniMouseExited

    private void btnAkunPenghuniMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAkunPenghuniMousePressed
        btnAkunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni press.png")));
    }//GEN-LAST:event_btnAkunPenghuniMousePressed

    private void btnAkunPenghuniMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAkunPenghuniMouseReleased
        btnAkunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni entered.png")));
    }//GEN-LAST:event_btnAkunPenghuniMouseReleased

    private void btnKeluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKeluarMouseEntered
        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab keluar entered.png")));
    }//GEN-LAST:event_btnKeluarMouseEntered

    private void btnKeluarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKeluarMouseExited
        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab keluar none.png")));
    }//GEN-LAST:event_btnKeluarMouseExited

    private void btnKeluarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKeluarMousePressed
        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab keluar press.png")));
    }//GEN-LAST:event_btnKeluarMousePressed

    private void btnKeluarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKeluarMouseReleased
        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab keluar entered.png")));
    }//GEN-LAST:event_btnKeluarMouseReleased

    private void btn_okeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_okeMouseClicked
        // TODO add your handling code here:
        pop_up_berhasilSimpan.setVisible(false);
    }//GEN-LAST:event_btn_okeMouseClicked

    private void btn_okeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_okeMouseEntered
        // TODO add your handling code here:
        btn_oke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/btn oke entered.png")));
    }//GEN-LAST:event_btn_okeMouseEntered

    private void btn_okeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_okeMouseExited
        // TODO add your handling code here:
        btn_oke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/btn oke normal.png")));
    }//GEN-LAST:event_btn_okeMouseExited

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

    private void btn_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseClicked
        // TODO add your handling code here:
        pop_up_berhasilHapus.setVisible(false);
    }//GEN-LAST:event_btn_close1MouseClicked

    private void btn_close1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseEntered
        // TODO add your handling code here:
        btn_close1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/x entered.png")));
    }//GEN-LAST:event_btn_close1MouseEntered

    private void btn_close1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close1MouseExited
        // TODO add your handling code here:
         btn_close1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/x normal.png")));
    }//GEN-LAST:event_btn_close1MouseExited

    private void btn_oke1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_oke1MouseClicked
        // TODO add your handling code here:
        pop_up_berhasilHapus.setVisible(false);
    }//GEN-LAST:event_btn_oke1MouseClicked

    private void btn_oke1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_oke1MouseEntered
        // TODO add your handling code here:
        btn_oke1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/btn oke entered.png")));
    }//GEN-LAST:event_btn_oke1MouseEntered

    private void btn_oke1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_oke1MouseExited
        // TODO add your handling code here:
        btn_oke1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_pop_up/btn oke normal.png")));
    }//GEN-LAST:event_btn_oke1MouseExited

    private void btn_close2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close2MouseClicked
        // TODO add your handling code here:
         pop_up_berhasilEdit.setVisible(false);
    }//GEN-LAST:event_btn_close2MouseClicked

    private void btn_close2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_close2MouseEntered

    private void btn_close2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_close2MouseExited

    private void btn_oke2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_oke2MouseClicked
        // TODO add your handling code here:
        pop_up_berhasilEdit.setVisible(false);
    }//GEN-LAST:event_btn_oke2MouseClicked

    private void btn_oke2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_oke2MouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btn_oke2MouseEntered

    private void btn_oke2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_oke2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_oke2MouseExited

    private void btn_close3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close3MouseClicked
        // TODO add your handling code here:
        pop_up_konfirmasiHapus.setVisible(false);
    }//GEN-LAST:event_btn_close3MouseClicked

    private void btn_close3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_close3MouseEntered

    private void btn_close3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_close3MouseExited

    private void btn_oke3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_oke3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_oke3MouseClicked

    private void btn_oke3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_oke3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_oke3MouseEntered

    private void btn_oke3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_oke3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_oke3MouseExited

    private void btn_batalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_batalMouseClicked
        // TODO add your handling code here:
        pop_up_konfirmasiHapus.setVisible(false);
    }//GEN-LAST:event_btn_batalMouseClicked

    private void btn_batalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_batalMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_batalMouseEntered

    private void btn_batalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_batalMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_batalMouseExited

    private void btn_close4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close4MouseClicked
        // TODO add your handling code here:
        kosong_tambah_tanggal.setEnabled(true);
        cmb_tambah_kamar.setEnabled(true);
        txt_tambah_tanggal.setEnabled(true);
        txt_tambah_keluar.setEnabled(true);
        txt_tambah_alamat.setEnabled(true);
        
        kosong_edit_tanggal.setEnabled(true);
        cmb_edit_kamar.setEnabled(true);
        txt_edit_tanggal.setEnabled(true);
        txt_edit_keluar.setEnabled(true);
        txt_edit_alamat.setEnabled(true);
        
        pop_up_batal.setVisible(false);
    }//GEN-LAST:event_btn_close4MouseClicked

    private void btn_close4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_close4MouseEntered

    private void btn_close4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_close4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_close4MouseExited

    private void btn_oke4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_oke4MouseClicked
        // TODO add your handling code here:
        
        kosongkan();
        hapus_combo();
        pop_up_batal.setVisible(false);
        panel_tambah.setVisible(false);
        panel_edit.setVisible(false);
        
        
        cmb_tambah_kamar.setEnabled(true);
        kosong_tambah_tanggal.setEnabled(true);
        txt_tambah_tanggal.setEnabled(true);
        txt_tambah_keluar.setEnabled(true);
        txt_tambah_alamat.setEnabled(true);
        
        cmb_edit_kamar.setEnabled(true);
        kosong_edit_tanggal.setEnabled(true);
        txt_edit_tanggal.setEnabled(true);
        txt_edit_keluar.setEnabled(true);
        txt_edit_alamat.setEnabled(true);
    }//GEN-LAST:event_btn_oke4MouseClicked

    private void btn_oke4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_oke4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_oke4MouseEntered

    private void btn_oke4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_oke4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_oke4MouseExited

    private void btn_batal1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_batal1MouseClicked
        // TODO add your handling code here:
        
        kosong_tambah_tanggal.setEnabled(true);
        cmb_tambah_kamar.setEnabled(true);
        txt_tambah_tanggal.setEnabled(true);
        txt_tambah_keluar.setEnabled(true);
        txt_tambah_alamat.setEnabled(true);
        
        kosong_edit_tanggal.setEnabled(true);
        cmb_edit_kamar.setEnabled(true);
        txt_edit_tanggal.setEnabled(true);
        txt_edit_keluar.setEnabled(true);
        txt_edit_alamat.setEnabled(true);
        
        pop_up_batal.setVisible(false);
    }//GEN-LAST:event_btn_batal1MouseClicked

    private void btn_batal1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_batal1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_batal1MouseEntered

    private void btn_batal1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_batal1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_batal1MouseExited

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
        new frmlogin1().setVisible(true);
    }//GEN-LAST:event_btn_logoutMouseClicked

    private void bg_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg_logoutMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bg_logoutMouseClicked

    private void btnCariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariMouseClicked
    
    public void editData2(){
        /*
        String tampilan = "yyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat (tampilan);
        String tanggal = String.valueOf(fm.format(txt_edit_tanggal.getDate()));
        String keluar = String.valueOf(fm.format(txt_edit_keluar.getDate()));
        */
        int i = tabelPenghuni.getSelectedRow();
        TableModel tbl = tabelPenghuni.getModel();
             
        String id_penghuni = tbl.getValueAt(i, 0).toString();
        String nama_penghuni = tbl.getValueAt(i, 1).toString();
        String noKamar = tbl.getValueAt(i, 2).toString();
        //String tggl_masuk = tbl.getValueAt(i, 3).toString();
        String alamat = tbl.getValueAt(i, 4).toString();
        //String tggl_keluar = tbl.getValueAt(i, 5).toString();
        String no_telp = tbl.getValueAt(i, 6).toString();
//        String harga = tbl.getValueAt(i, 7).toString();

        if (txt_edit_kode_penghuni == null){
            txt_edit_kode_penghuni.setText("");
        }
        else{
            txt_edit_kode_penghuni.setText(id_penghuni);
        }
        if (txt_edit_nama_penghuni == null){
            txt_edit_nama_penghuni.setText("");
        }
        else{
            txt_edit_nama_penghuni.setText(nama_penghuni);
        }
        if (cmb_edit_kamar == null){
            cmb_edit_kamar.setSelectedItem("");
        }
        else{
            cmb_edit_kamar.setSelectedItem(tabelPenghuni.getValueAt(i, 2));
        }
        
       // if (txt_edit_tanggal == null){
       //     txt_edit_tanggal.setDateFormatString("");
       // }
       // else{
          //  txt_edit_tanggal.setDateFormatString(tanggal);
        //}
        if (txt_edit_alamat == null){
            txt_edit_alamat.setText("");
        }
        else{
            txt_edit_alamat.setText(alamat);
        }
        /*if (txt_edit_keluar == null){
            txt_edit_keluar.setDateFormatString("");
        }
        else{
            txt_edit_keluar.setDateFormatString(tggl_keluar);
        }*/
        if (txt_edit_no_telp == null){
            txt_edit_no_telp.setText("");
        }
        else{
            txt_edit_no_telp.setText(no_telp);
        }
//        if (txt_edit_harga == null){
//            txt_edit_harga.setText("");
//        }
//        else{
//            txt_edit_harga.setText(harga);
//        }    
    }
     
    public void getData(){
    try{
            java.sql.Connection conn = (Connection)config.configDB();
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery("select * from tb_penghuni");
            
            DefaultTableModel model = (DefaultTableModel) tabelPenghuni.getModel();
            //reset
            model.setRowCount(0);
            while(result.next()) {
               String id_penghuni = result.getString("id_penghuni");
               String nama = result.getString("nama_penghuni");
               String Kamar = result.getString("noKamar");
               String masuk = result.getString("tggl_masuk");
               String alamat = result.getString("alamat");
               String keluar = result.getString("tggl_keluar");
               String no_telp = result.getString("no_telp");
               
               
               model.addRow(new Object[]{
                   id_penghuni,
                   nama,
                   Kamar, 
                   masuk, 
                   alamat, 
                   keluar, 
                   no_telp
                  
               });
               
            }
             
            
        } catch (Exception ex) {
            Logger.getLogger(form_penghuni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
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
            java.util.logging.Logger.getLogger(form_penghuni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_penghuni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_penghuni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_penghuni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new form_penghuni().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_edit;
    private javax.swing.JLabel bg_logout;
    private javax.swing.JLabel bg_pop_up_hapus;
    private javax.swing.JLabel bg_simpan;
    private javax.swing.JLabel bg_simpan1;
    private javax.swing.JLabel bg_simpan2;
    private javax.swing.JLabel bg_simpan3;
    private javax.swing.JLabel bg_tambah;
    private javax.swing.JLabel btnAkun;
    private javax.swing.JLabel btnAkunPenghuni;
    private javax.swing.JLabel btnBereanda;
    private javax.swing.JLabel btnCari;
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel btnKeluar;
    private javax.swing.JLabel btnKomplain;
    private javax.swing.JLabel btnLaporan;
    private javax.swing.JLabel btnTagihan;
    private javax.swing.JLabel btnTambah;
    private javax.swing.JLabel btnX;
    private javax.swing.JLabel btn_batal;
    private javax.swing.JLabel btn_batal1;
    private javax.swing.JLabel btn_close;
    private javax.swing.JLabel btn_close1;
    private javax.swing.JLabel btn_close2;
    private javax.swing.JLabel btn_close3;
    private javax.swing.JLabel btn_close4;
    private javax.swing.JLabel btn_edit_batal;
    private javax.swing.JLabel btn_keluar;
    private javax.swing.JLabel btn_logout;
    private javax.swing.JLabel btn_oke;
    private javax.swing.JLabel btn_oke1;
    private javax.swing.JLabel btn_oke2;
    private javax.swing.JLabel btn_oke3;
    private javax.swing.JLabel btn_oke4;
    private javax.swing.JLabel btn_simpan_tambah;
    private javax.swing.JLabel btn_simpan_update;
    private javax.swing.JLabel btn_tambah_batal;
    private javax.swing.JLabel btnkamarKost;
    private javax.swing.JLabel btnpenghuniKost;
    private javax.swing.JComboBox<String> cmbFilter;
    public javax.swing.JComboBox<String> cmb_edit_kamar;
    public javax.swing.JComboBox<String> cmb_tambah_kamar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jam_icon;
    private javax.swing.JCheckBox kosong_edit_tanggal;
    private javax.swing.JCheckBox kosong_tambah_tanggal;
    private javax.swing.JLabel lbl_jam;
    private javax.swing.JLabel lbl_tanggal;
    private javax.swing.JPanel logout;
    public javax.swing.JPanel panel_edit;
    public javax.swing.JPanel panel_tambah;
    private javax.swing.JPanel pop_up_batal;
    private javax.swing.JPanel pop_up_berhasilEdit;
    private javax.swing.JPanel pop_up_berhasilHapus;
    private javax.swing.JPanel pop_up_berhasilSimpan;
    private javax.swing.JPanel pop_up_konfirmasiHapus;
    public javax.swing.JTable tabelPenghuni;
    private javax.swing.JLabel tanggal_icon;
    public javax.swing.JTextField txtCari;
    public javax.swing.JTextField txt_edit_alamat;
    public javax.swing.JTextField txt_edit_harga;
    private javax.swing.JLabel txt_edit_id_bantu;
    public javax.swing.JTextField txt_edit_kode_penghuni;
    public javax.swing.JTextField txt_edit_nama_penghuni;
    public javax.swing.JTextField txt_edit_no_telp;
    public javax.swing.JTextField txt_tambah_alamat;
    public javax.swing.JTextField txt_tambah_harga;
    private javax.swing.JLabel txt_tambah_id_bantu;
    public javax.swing.JTextField txt_tambah_kode_penghuni;
    public javax.swing.JTextField txt_tambah_nama_penghuni;
    public javax.swing.JTextField txt_tambah_no_telp;
    // End of variables declaration//GEN-END:variables
}
