package simko_fiks;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class form_laporan extends javax.swing.JFrame {

    DecimalFormat kursIndonesia;
    DecimalFormatSymbols formatRupiah;
    double nilai;
    
    public boolean databaru;
    public boolean klikEdit;
    
    
    public form_laporan() {
        initComponents();
        datatable();
        databaru = true;
        klikEdit = false;
        Tampil_jam();
        Tampil_tanggal();
        autosumTotalLunas();
        autosumTotalTakLunas();
        jumlahTagihan();
        opsi_cetak_panel.setVisible(false);
        
        daftar_penghuni_bulanan.setVisible(false);
        daftar_penghuni_bulanan.setBackground(new Color(0,0,0,200));
        
        kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        formatRupiah = new DecimalFormatSymbols();  
        formatRupiah.setCurrencySymbol("Rp. ");
        formatRupiah.setMonetaryDecimalSeparator(',');
        formatRupiah.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRupiah);
        
        tabel_uatama.getTableHeader().setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
        tabel_uatama.getTableHeader().setOpaque(false);
        tabel_uatama.setSelectionForeground(Color. white);
        
        jTable3.getTableHeader().setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
        jTable3.getTableHeader().setOpaque(false);
        
        logout.setVisible(false);
        logout.setBackground(new Color(0,0,0,200));
    }
    
    //untuk ekspor ke excel
    public void eksporexcelNew() {
    FileWriter fileWriter;
    JFileChooser chooser = new JFileChooser();
    chooser.setCurrentDirectory(new File("[B]export_output/excel[/B]"));
    int retrival = chooser.showSaveDialog(null);
    if (retrival == JFileChooser.APPROVE_OPTION) {
        try {
            if (tabel_uatama != null) {
                TableModel tModel = tabel_uatama.getModel();
                fileWriter = new FileWriter(new File(chooser.getSelectedFile() + ".xls"));
                // write header
                if (tModel != null) {
                    for (int i = 0; i < tModel.getColumnCount(); i++) {
                        fileWriter.write(tModel.getColumnName(i).toUpperCase() + "\t");
                    }
                }
                fileWriter.write("\n");
                // write record
                for (int i = 0; i < tModel.getRowCount(); i++) {
                    for (int j = 0; j < tModel.getColumnCount(); j++) {
                        fileWriter.write(tModel.getValueAt(i, j).toString() + "\t");
                    }
                    fileWriter.write("\n");
                }
                fileWriter.close();
                JOptionPane.showMessageDialog(null, "Data berhasil di ekspor");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
    
    public static String formatDec(int val) {
        return String.format("%,d", val).replace(',', '.');
    }

    
    private void clear(){
        tanggal_awal.setDate(null);
        tanggal_akhir.setDate(null);
        combo_status.setSelectedIndex(0);
        opsi_cetak_panel.setVisible(false);
        tabel_uatama.setEnabled(true);
    }
    
    //konceksi tampil tanggal
     public static Connection getConnection(){
        Connection con = null;
        
         try {

            Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306"
                     + "/simko", "root", "");
        } catch (Exception ex) {

             System.out.println(ex.getMessage());

        }
        return con;
    }
        
    public void autosumTotalLunas(){ 
        int total = 0;
        for(int i = 0;i < tabel_uatama.getRowCount(); i++){
            int amount = Integer.parseInt((String)tabel_uatama.getValueAt(i, 4));
            total += amount;
        }
          lbl_total_lunas.setText("Total Bayar = Rp. "+ formatDec(total)); 
    }
    
    public void autosumTotalTakLunas(){
        int total = 0;
        for(int i = 0;i < tabel_uatama.getRowCount(); i++){
            int amount = Integer.parseInt((String)tabel_uatama.getValueAt(i, 5));
            total += amount;
        }
        lbl_tak_lunas.setText("Belum Bayar = Rp. "+ formatDec(total));
    }
    
    public void jumlahTagihan() {
    // ambil jumlah baris pada tabel
    int rowCount = tabel_uatama.getRowCount();
    // set teks pada JLabel1 dengan jumlah baris
    lbl_jumlah_tagihan.setText("Jumlah tagihan = " + rowCount);
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
    
    public void datatable(){
        
        DefaultTableModel tbl = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false, false, false, false, false, false
        };
        
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex){
            return canEdit [columnIndex];
        }
        };
        
        //DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Id Tagihan");
        tbl.addColumn("Harga Kamar");
        tbl.addColumn("Nama");
        tbl.addColumn("Tanggal Bayar");
        tbl.addColumn("Bayar");
        tbl.addColumn("Sisa");
        tbl.addColumn("Status");
        tbl.addColumn("Keterangan");
        
        tabel_uatama.setModel(tbl);
       
        try{
            String sql = "SELECT tb_tagihan.id_tagihan, tb_kamar.harga, tb_penghuni.nama_penghuni, tb_tagihan.tgl_bayar,\n" +
                        "tb_tagihan.bayar, tb_tagihan.sisa, tb_tagihan.status, tb_tagihan.keterangan\n" +
                        "FROM tb_tagihan INNER JOIN tb_penghuni on tb_tagihan.id_penghuni = tb_penghuni.id_penghuni\n" +
                        "INNER JOIN tb_kamar on tb_tagihan.noKamar = tb_kamar.noKamar ORDER BY tb_tagihan.id_bantu DESC";
            
            Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                tbl.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3), res.getString(4), res.getString(5),
                res.getString(6), res.getString(7), res.getString(8)});
                
            }
            tabel_uatama.setModel(tbl);
        }catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
    }
    }
    
     public void datatablepanel(){
        
         DefaultTableModel tbl = new DefaultTableModel(){
        boolean[] canEdit = new boolean[]{
            false, false, false
        };
        
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex){
            return canEdit [columnIndex];
        }
        };
         
        //DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Nama Penghuni");
        tbl.addColumn("Nomor Kamar");
        tbl.addColumn("No Telpon");
        
        jTable3.setModel(tbl);
       
        try{
            String sql = "SELECT nama_penghuni, noKamar, no_telp FROM tb_penghuni_bulanan";
            
            Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                tbl.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3)});
            }
            jTable3.setModel(tbl);
        }catch (Exception e) {
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

        jMenu1 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        btn_beranda = new javax.swing.JLabel();
        btn_penghuniKost = new javax.swing.JLabel();
        btn_kamarKost = new javax.swing.JLabel();
        btn_tagihan = new javax.swing.JLabel();
        btn_laporan = new javax.swing.JLabel();
        btn_komplain = new javax.swing.JLabel();
        btn_akunPenghuni = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_keluar = new javax.swing.JLabel();
        lbl_tanggal = new javax.swing.JLabel();
        tanggal_icon = new javax.swing.JLabel();
        lbl_jam = new javax.swing.JLabel();
        jam_icon = new javax.swing.JLabel();
        btn_edit = new javax.swing.JLabel();
        btn_cetak = new javax.swing.JLabel();
        lbl_total_lunas = new javax.swing.JLabel();
        btn_cari = new javax.swing.JButton();
        lbl_tak_lunas = new javax.swing.JLabel();
        lbl_jumlah_tagihan = new javax.swing.JLabel();
        lbl_penghuni_bulanan = new javax.swing.JLabel();
        lbl_clear = new javax.swing.JLabel();
        combo_status = new javax.swing.JComboBox<>();
        bg = new javax.swing.JLabel();
        logout = new javax.swing.JPanel();
        btnX = new javax.swing.JLabel();
        btn_keluar1 = new javax.swing.JLabel();
        btn_logout = new javax.swing.JLabel();
        bg_logout = new javax.swing.JLabel();
        daftar_penghuni_bulanan = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        close_btn = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        opsi_cetak_panel = new javax.swing.JPanel();
        lbl_excel = new javax.swing.JLabel();
        lbl_pdf = new javax.swing.JLabel();
        lbl_close_cetak = new javax.swing.JLabel();
        bg_opsi = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        tabel_uatama.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        tabel_uatama.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_uatama.setRowHeight(40);
        tabel_uatama.setSelectionBackground(new java.awt.Color(44, 141, 141));
        tabel_uatama.getTableHeader().setReorderingAllowed(false);
        tabel_uatama.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_uatamaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_uatama);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(440, 370, 1390, 510);

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

        btn_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan normal.png"))); // NOI18N
        btn_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
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

        lbl_tanggal.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        lbl_tanggal.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lbl_tanggal);
        lbl_tanggal.setBounds(1420, 40, 220, 40);

        tanggal_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/calendar  icon.png"))); // NOI18N
        getContentPane().add(tanggal_icon);
        tanggal_icon.setBounds(1368, 40, 40, 40);

        lbl_jam.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        lbl_jam.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lbl_jam);
        lbl_jam.setBounds(1210, 40, 110, 40);

        jam_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clock icon.png"))); // NOI18N
        getContentPane().add(jam_icon);
        jam_icon.setBounds(1160, 38, 40, 40);

        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_laporan/btn edit normal.png"))); // NOI18N
        btn_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_editMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_editMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_editMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_editMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_editMouseReleased(evt);
            }
        });
        getContentPane().add(btn_edit);
        btn_edit.setBounds(1464, 233, 170, 55);

        btn_cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_laporan/btn cetak normal.png"))); // NOI18N
        btn_cetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cetakMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cetakMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_cetakMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_cetakMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_cetakMouseReleased(evt);
            }
        });
        getContentPane().add(btn_cetak);
        btn_cetak.setBounds(1660, 233, 166, 55);

        lbl_total_lunas.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        lbl_total_lunas.setForeground(new java.awt.Color(44, 141, 141));
        lbl_total_lunas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_total_lunas.setText(".");
        lbl_total_lunas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(lbl_total_lunas);
        lbl_total_lunas.setBounds(1510, 920, 320, 40);

        btn_cari.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cari);
        btn_cari.setBounds(1060, 230, 80, 40);

        lbl_tak_lunas.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        lbl_tak_lunas.setForeground(new java.awt.Color(44, 141, 141));
        lbl_tak_lunas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_tak_lunas.setText(".");
        getContentPane().add(lbl_tak_lunas);
        lbl_tak_lunas.setBounds(1010, 920, 320, 40);

        lbl_jumlah_tagihan.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        lbl_jumlah_tagihan.setForeground(new java.awt.Color(44, 141, 141));
        lbl_jumlah_tagihan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_jumlah_tagihan.setText(".");
        getContentPane().add(lbl_jumlah_tagihan);
        lbl_jumlah_tagihan.setBounds(530, 920, 320, 40);

        lbl_penghuni_bulanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_laporan/btn bulanan normal.png"))); // NOI18N
        lbl_penghuni_bulanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_penghuni_bulananMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_penghuni_bulananMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_penghuni_bulananMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_penghuni_bulananMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbl_penghuni_bulananMouseReleased(evt);
            }
        });
        getContentPane().add(lbl_penghuni_bulanan);
        lbl_penghuni_bulanan.setBounds(1362, 229, 72, 64);

        lbl_clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_clearMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_clear);
        lbl_clear.setBounds(1260, 229, 72, 64);

        combo_status.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        combo_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih--", "Lunas ", "Belum Lunas" }));
        getContentPane().add(combo_status);
        combo_status.setBounds(880, 230, 150, 40);

        lblnama5.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N
        lblnama5.setForeground(new java.awt.Color(153, 153, 153));
        lblnama5.setText(".");
        getContentPane().add(lblnama5);
        lblnama5.setBounds(1750, 40, 140, 40);

        bg.setForeground(new java.awt.Color(255, 255, 255));
        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/form_laporan.png"))); // NOI18N
        getContentPane().add(bg);
        bg.setBounds(0, 0, 1920, 1080);

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

        daftar_penghuni_bulanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                daftar_penghuni_bulananMouseClicked(evt);
            }
        });
        daftar_penghuni_bulanan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable3.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.setRowHeight(40);
        jTable3.setSelectionBackground(new java.awt.Color(44, 141, 141));
        jTable3.getTableHeader().setReorderingAllowed(false);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        daftar_penghuni_bulanan.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 960, -1));

        close_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                close_btnMouseClicked(evt);
            }
        });
        daftar_penghuni_bulanan.add(close_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 152, 38, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/daftar punghuni bulanan.png"))); // NOI18N
        daftar_penghuni_bulanan.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 140, 1111, 799));

        getContentPane().add(daftar_penghuni_bulanan);
        daftar_penghuni_bulanan.setBounds(0, 0, 1920, 1080);

        opsi_cetak_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_excel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_excelMouseClicked(evt);
            }
        });
        opsi_cetak_panel.add(lbl_excel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 13, 60, 62));

        lbl_pdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_pdfMouseClicked(evt);
            }
        });
        opsi_cetak_panel.add(lbl_pdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 89, 60, 62));

        lbl_close_cetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_close_cetakMouseClicked(evt);
            }
        });
        opsi_cetak_panel.add(lbl_close_cetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 166, 35, 35));

        bg_opsi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/opsi_cetak.png"))); // NOI18N
        bg_opsi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bg_opsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bg_opsiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bg_opsiMouseExited(evt);
            }
        });
        opsi_cetak_panel.add(bg_opsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(opsi_cetak_panel);
        opsi_cetak_panel.setBounds(1682, 288, 119, 213);

        setSize(new java.awt.Dimension(1920, 1080));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(form_laporan.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void btn_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseClicked
         
        if(klikEdit == true){
            this.setVisible(false);
            new form_editTagihan().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Pilih opsi data yang ingin diedit");
        }
       databaru = false;
    }//GEN-LAST:event_btn_editMouseClicked

    private void btn_cetakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cetakMouseClicked
    opsi_cetak_panel.setVisible(true);
    tabel_uatama.setEnabled(false);
    }//GEN-LAST:event_btn_cetakMouseClicked

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
       try{

            java.sql.Connection conn = (Connection)config.configDB();
            Statement stm = conn.createStatement();
            Statement stm2 = conn.createStatement();
            Statement stm3 = conn.createStatement();
            
            String format_tgl = "yyyy-MM-dd";
            SimpleDateFormat fm = new  SimpleDateFormat(format_tgl);
            
            DefaultTableModel model = (DefaultTableModel) tabel_uatama.getModel();
            model.setRowCount(0);
                     
            if(tanggal_awal.getDate() != null && tanggal_akhir.getDate() != null){
            String tanggal1 = String.valueOf(fm.format(this.tanggal_awal.getDate()));
            String tanggal2 = String.valueOf(fm.format(this.tanggal_akhir.getDate()));
            
            ResultSet result = stm.executeQuery("SELECT tb_tagihan.id_tagihan, "
                    + "tb_kamar.harga, tb_penghuni.nama_penghuni, tb_tagihan.tgl_bayar,\n" +
"                        tb_tagihan.bayar, tb_tagihan.sisa, tb_tagihan.status, "
                    + "tb_tagihan.keterangan\n" +
"                        from tb_tagihan INNER JOIN tb_penghuni on tb_tagihan.id_penghuni "
                    + "= tb_penghuni.id_penghuni\n" +
"                        INNER JOIN tb_kamar on tb_tagihan.noKamar = tb_kamar.noKamar "
                    + "where tgl_bayar between  '"+tanggal1+"' and '"+tanggal2+"' and tb_tagihan.status "
                            + "= '"+combo_status.getSelectedItem()+"' ORDER BY tb_tagihan.tgl_bayar ASC");
            
            ResultSet result2 = stm2.executeQuery("SELECT tb_tagihan.id_tagihan, "
                    + "tb_kamar.harga, tb_penghuni.nama_penghuni, tb_tagihan.tgl_bayar,\n" +
"                        tb_tagihan.bayar, tb_tagihan.sisa, tb_tagihan.status, "
                    + "tb_tagihan.keterangan\n" +
"                        from tb_tagihan INNER JOIN tb_penghuni on tb_tagihan.id_penghuni "
                    + "= tb_penghuni.id_penghuni\n" +
"                        INNER JOIN tb_kamar on tb_tagihan.noKamar = tb_kamar.noKamar "
                    + "where tgl_bayar between  '"+tanggal1+"' and '"+tanggal2+"' ORDER BY tb_tagihan.tgl_bayar ASC");
             
            if (combo_status.getSelectedIndex() == 0) {
                while(result2.next()){
               String id_tagihan = result2.getString("id_tagihan");
               String harga = result2.getString("tb_kamar.harga");
               String nama = result2.getString("tb_penghuni.nama_penghuni");
               String tgl_bayar = result2.getString("tgl_bayar");
               String bayar = result2.getString("bayar");
               String sisa = result2.getString("sisa");
               String status = result2.getString("tb_tagihan.status");
               String keterangan = result2.getString("keterangan");
                    
                model.addRow(new Object[]{
                    id_tagihan,
                    harga,
                    nama,
                    tgl_bayar,
                    bayar,
                    sisa,
                    status,
                    keterangan
                });
                }
            } else if(combo_status.getSelectedIndex() != 0){
                
                while(result.next()){
               String id_tagihan = result.getString("id_tagihan");
               String harga = result.getString("tb_kamar.harga");
               String nama = result.getString("tb_penghuni.nama_penghuni");
               String tgl_bayar = result.getString("tgl_bayar");
               String bayar = result.getString("bayar");
               String sisa = result.getString("sisa");
               String status = result.getString("tb_tagihan.status");
               String keterangan = result.getString("keterangan");
                    
                model.addRow(new Object[]{
                   id_tagihan,
                    harga,
                    nama,
                    tgl_bayar,
                    bayar,
                    sisa,
                    status,
                    keterangan
                });
            }
                
            }
          result.close();  
          result2.close();
            }else{
                ResultSet result3 = stm3.executeQuery("SELECT tb_tagihan.id_tagihan, "
                        + "tb_kamar.harga, tb_penghuni.nama_penghuni, tb_tagihan.tgl_bayar,\n" +
"                        tb_tagihan.bayar, tb_tagihan.sisa, tb_tagihan.status, "
                        + "tb_tagihan.keterangan\n" +"from tb_tagihan INNER JOIN tb_penghuni "
                        + "on tb_tagihan.id_penghuni = tb_penghuni.id_penghuni\n" +
"                        INNER JOIN tb_kamar on tb_tagihan.noKamar = tb_kamar.noKamar "
                        + "where tb_tagihan.status = '"+combo_status.getSelectedItem()
                        +"' ORDER BY tb_tagihan.tgl_bayar DESC");
                
               while(result3.next()){
               String id_tagihan = result3.getString("id_tagihan");
               String harga = result3.getString("tb_kamar.harga");
               String nama = result3.getString("tb_penghuni.nama_penghuni");
               String tgl_bayar = result3.getString("tgl_bayar");
               String bayar = result3.getString("bayar");
               String sisa = result3.getString("sisa");
               String status = result3.getString("tb_tagihan.status");
               String keterangan = result3.getString("keterangan");
                    
                model.addRow(new Object[]{
                   id_tagihan,
                    harga,
                    nama,
                    tgl_bayar,
                    bayar,
                    sisa,
                    status,
                    keterangan
                });
                }
                result3.close();
            }

            autosumTotalLunas();
            autosumTotalTakLunas();
            jumlahTagihan();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void tabel_uatamaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_uatamaMouseClicked
        klikEdit = true;
        int baris = tabel_uatama.rowAtPoint(evt.getPoint());
        String kode_tagihan = tabel_uatama.getValueAt(baris, 0).toString();
        form_editTagihan.kd_tagihan = kode_tagihan;
        form_editTagihan fp = new form_editTagihan();
    }//GEN-LAST:event_tabel_uatamaMouseClicked

    private void lbl_penghuni_bulananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_penghuni_bulananMouseClicked
        daftar_penghuni_bulanan.setVisible(true);
        tanggal_awal.setVisible(false);
        tanggal_akhir.setVisible(false);
        datatablepanel();
    }//GEN-LAST:event_lbl_penghuni_bulananMouseClicked

    private void close_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close_btnMouseClicked
        daftar_penghuni_bulanan.setVisible(false);
        tanggal_awal.setVisible(true);
        tanggal_akhir.setVisible(true);
    }//GEN-LAST:event_close_btnMouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
       
    }//GEN-LAST:event_jTable3MouseClicked

    private void daftar_penghuni_bulananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_daftar_penghuni_bulananMouseClicked
        this.setVisible(true);
    }//GEN-LAST:event_daftar_penghuni_bulananMouseClicked

    private void lbl_clearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_clearMouseClicked
        clear();
        datatable();
        autosumTotalLunas();
        autosumTotalTakLunas();
        jumlahTagihan();
    }//GEN-LAST:event_lbl_clearMouseClicked

    private void lbl_excelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_excelMouseClicked
        eksporexcelNew();
        opsi_cetak_panel.setVisible(false);
        tabel_uatama.setEnabled(true);
    }//GEN-LAST:event_lbl_excelMouseClicked

    private void lbl_pdfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_pdfMouseClicked
        MessageFormat judul = new MessageFormat ("LAPORAN KEUANGAN SIMKO");
        MessageFormat footer = new MessageFormat ("page(0,number,integer");
        try{
            tabel_uatama.print(JTable.PrintMode.FIT_WIDTH, judul, footer);
        }catch(PrinterException e) {
            System.out.println("Eror Print");
        }
        opsi_cetak_panel.setVisible(false);
        tabel_uatama.setEnabled(true);
    }//GEN-LAST:event_lbl_pdfMouseClicked

    private void lbl_close_cetakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_close_cetakMouseClicked
        opsi_cetak_panel.setVisible(false);
        tabel_uatama.setEnabled(true);
    }//GEN-LAST:event_lbl_close_cetakMouseClicked

    private void bg_opsiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg_opsiMouseEntered
        this.setVisible(true);
    }//GEN-LAST:event_bg_opsiMouseEntered

    private void bg_opsiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bg_opsiMouseExited
        this.setVisible(true);
    }//GEN-LAST:event_bg_opsiMouseExited

    private void btn_editMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseEntered
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_laporan/btn edit entered.png")));
    }//GEN-LAST:event_btn_editMouseEntered

    private void btn_editMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseExited
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_laporan/btn edit normal.png")));
    }//GEN-LAST:event_btn_editMouseExited

    private void btn_editMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMousePressed
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_laporan/btn edit press.png")));
    }//GEN-LAST:event_btn_editMousePressed

    private void btn_editMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseReleased
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_laporan/btn edit entered.png")));
    }//GEN-LAST:event_btn_editMouseReleased

    private void btn_cetakMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cetakMouseEntered
        btn_cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_laporan/btn cetak entered.png")));
    }//GEN-LAST:event_btn_cetakMouseEntered

    private void btn_cetakMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cetakMouseExited
        btn_cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_laporan/btn cetak normal.png")));
    }//GEN-LAST:event_btn_cetakMouseExited

    private void btn_cetakMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cetakMousePressed
        btn_cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_laporan/btn cetak press.png")));
    }//GEN-LAST:event_btn_cetakMousePressed

    private void btn_cetakMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cetakMouseReleased
        btn_cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_laporan/btn cetak entered.png")));
    }//GEN-LAST:event_btn_cetakMouseReleased

    private void lbl_penghuni_bulananMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_penghuni_bulananMouseEntered
        lbl_penghuni_bulanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_laporan/btn bulanan entered.png")));
    }//GEN-LAST:event_lbl_penghuni_bulananMouseEntered

    private void lbl_penghuni_bulananMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_penghuni_bulananMouseExited
        lbl_penghuni_bulanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_laporan/btn bulanan normal.png")));
    }//GEN-LAST:event_lbl_penghuni_bulananMouseExited

    private void lbl_penghuni_bulananMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_penghuni_bulananMousePressed
        lbl_penghuni_bulanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_laporan/btn bulanan press.png")));
    }//GEN-LAST:event_lbl_penghuni_bulananMousePressed

    private void lbl_penghuni_bulananMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_penghuni_bulananMouseReleased
        lbl_penghuni_bulanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_laporan/btn bulanan entered.png")));
    }//GEN-LAST:event_lbl_penghuni_bulananMouseReleased

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

    private void btn_laporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseEntered
        btn_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan entered.png")));

    }//GEN-LAST:event_btn_laporanMouseEntered

    private void btn_laporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseExited
        btn_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab laporan normal.png")));
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
            java.util.logging.Logger.getLogger(form_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_logout;
    private javax.swing.JLabel bg_opsi;
    private javax.swing.JLabel btnX;
    private javax.swing.JLabel btn_akunPenghuni;
    private javax.swing.JLabel btn_beranda;
    private javax.swing.JButton btn_cari;
    private javax.swing.JLabel btn_cetak;
    private javax.swing.JLabel btn_edit;
    private javax.swing.JLabel btn_kamarKost;
    private javax.swing.JLabel btn_keluar;
    private javax.swing.JLabel btn_keluar1;
    private javax.swing.JLabel btn_komplain;
    private javax.swing.JLabel btn_laporan;
    private javax.swing.JLabel btn_logout;
    private javax.swing.JLabel btn_penghuniKost;
    private javax.swing.JLabel btn_tagihan;
    private javax.swing.JLabel close_btn;
    private javax.swing.JComboBox<String> combo_status;
    private javax.swing.JPanel daftar_penghuni_bulanan;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel jam_icon;
    private javax.swing.JLabel lbl_clear;
    private javax.swing.JLabel lbl_close_cetak;
    private javax.swing.JLabel lbl_excel;
    private javax.swing.JLabel lbl_jam;
    private javax.swing.JLabel lbl_jumlah_tagihan;
    private javax.swing.JLabel lbl_pdf;
    private javax.swing.JLabel lbl_penghuni_bulanan;
    private javax.swing.JLabel lbl_tak_lunas;
    private javax.swing.JLabel lbl_tanggal;
    private javax.swing.JLabel lbl_total_lunas;
    public static final javax.swing.JLabel lblnama5 = new javax.swing.JLabel();
    private javax.swing.JPanel logout;
    private javax.swing.JPanel opsi_cetak_panel;
    public static final javax.swing.JTable tabel_uatama = new javax.swing.JTable();
    private javax.swing.JLabel tanggal_icon;
    // End of variables declaration//GEN-END:variables
}
