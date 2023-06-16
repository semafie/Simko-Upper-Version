
package simko_fiks;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import pop_up.pop_gagalSimpan;
import pop_up.popup_keluar;


public class form_akunPenghuni extends javax.swing.JFrame {

    
    public form_akunPenghuni() {
        initComponents();
        Tampil_jam();
        Tampil_tanggal();
        //removeplaceholderstyle(txt_pertanyaan);
        addplaceholderstyle(txt_pertanyaan);
        //removeplaceholderstyle(txt_pass);
        addplaceholderstyle(txt_pass);
        datatable();
        tampil_idPenghuni();
        
        tabel.getTableHeader().setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
        tabel.getTableHeader().setOpaque(false);
        tabel.setSelectionForeground(Color.white);
        tabel.setSelectionBackground(new Color(41,141,141));
        
        pop_up_berhasilSimpan.setVisible(false);
        pop_up_berhasilHapus.setVisible(false);
        pop_up_berhasilEdit.setVisible(false);
        pop_up_konfirmasiHapus.setVisible(false);
        logout.setVisible(false);
        
        logout.setBackground(new Color(0,0,0,200));
        pop_up_berhasilSimpan.setBackground(new Color(0,0,0,0));
        pop_up_berhasilHapus.setBackground(new Color(0,0,0,0));
        pop_up_berhasilEdit.setBackground(new Color(0,0,0,0));
        pop_up_konfirmasiHapus.setBackground(new Color(0,0,0,0));
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
    
    public void clear(){
        cmb_idPeng.setSelectedIndex(0);
        txt_nama.setText(null);
        txt_pass.setText(null);
        txt_pertanyaan.setText(null);
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
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tbl.addColumn("ID PENGHUNI");
        tbl.addColumn("Nama");
        tbl.addColumn("PIN");
        tbl.addColumn("Password");
        tabel.setModel(tbl);
        try{
            Statement statement = (Statement)config.configDB().createStatement();
            ResultSet res = statement.executeQuery("select * from tb_user");
            while(res.next())
            {
                tbl.addRow(new Object[]{
                    res.getString("id_penghuni"),
                    res.getString("nama_penghuni"),
                    res.getString("pin"),
                    res.getString("password")
                });
                tabel.setModel(tbl);
            }
        }catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void tampil_idPenghuni(){
        try{
        String sql = "select * from tb_penghuni order by id_penghuni ASC";
        java.sql.Connection conn=(Connection)config.configDB();
        java.sql.PreparedStatement pst=conn.prepareStatement(sql);
        ResultSet res = pst.executeQuery(sql);
        while(res.next()){
            
            cmb_idPeng.addItem(res.getString("id_penghuni"));
        }
        
        res.last(); 
        int jumlahdata = res.getRow();
        int index = cmb_idPeng.getSelectedIndex();
            cmb_idPeng.removeItem(index);
        res.first();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pop_up_konfirmasiHapus = new javax.swing.JPanel();
        btn_close3 = new javax.swing.JLabel();
        btn_oke3 = new javax.swing.JLabel();
        btn_batal = new javax.swing.JLabel();
        bg_simpan2 = new javax.swing.JLabel();
        cmb_idPeng = new javax.swing.JComboBox<>();
        txt_nama = new javax.swing.JTextField();
        txt_pass = new javax.swing.JTextField();
        txt_pertanyaan = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JLabel();
        btn_reset = new javax.swing.JLabel();
        btn_edit = new javax.swing.JLabel();
        btn_hapus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        btn_beranda = new javax.swing.JLabel();
        btn_kamarKost = new javax.swing.JLabel();
        btn_penghuniKost = new javax.swing.JLabel();
        btn_tagihan = new javax.swing.JLabel();
        link_laporan = new javax.swing.JLabel();
        btn_komplain = new javax.swing.JLabel();
        btn_akunPenghuni = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_keluar = new javax.swing.JLabel();
        jam_icon = new javax.swing.JLabel();
        lbl_jam = new javax.swing.JLabel();
        tanggal_icon = new javax.swing.JLabel();
        lbl_tanggal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();
        txt_noKamar = new javax.swing.JLabel();
        logout = new javax.swing.JPanel();
        btnX = new javax.swing.JLabel();
        btn_keluar1 = new javax.swing.JLabel();
        btn_logout = new javax.swing.JLabel();
        bg_logout = new javax.swing.JLabel();
        pop_up_berhasilSimpan = new javax.swing.JPanel();
        btn_close = new javax.swing.JLabel();
        btn_oke = new javax.swing.JLabel();
        bg_simpan = new javax.swing.JLabel();
        pop_up_berhasilHapus = new javax.swing.JPanel();
        btn_close1 = new javax.swing.JLabel();
        btn_oke1 = new javax.swing.JLabel();
        bg_pop_up_hapus = new javax.swing.JLabel();
        pop_up_berhasilEdit = new javax.swing.JPanel();
        btn_close2 = new javax.swing.JLabel();
        btn_oke2 = new javax.swing.JLabel();
        bg_simpan1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

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

        cmb_idPeng.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        cmb_idPeng.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<--PILIH-->" }));
        cmb_idPeng.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cmb_idPengPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cmb_idPeng);
        cmb_idPeng.setBounds(630, 270, 440, 50);

        txt_nama.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_nama.setBorder(null);
        txt_nama.setOpaque(false);
        getContentPane().add(txt_nama);
        txt_nama.setBounds(1140, 272, 390, 50);

        txt_pass.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_pass.setForeground(new java.awt.Color(153, 153, 153));
        txt_pass.setText("Buatlah Password");
        txt_pass.setBorder(null);
        txt_pass.setOpaque(false);
        txt_pass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_passFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_passFocusLost(evt);
            }
        });
        getContentPane().add(txt_pass);
        txt_pass.setBounds(1140, 360, 390, 50);

        txt_pertanyaan.setFont(new java.awt.Font("Microsoft Tai Le", 0, 24)); // NOI18N
        txt_pertanyaan.setForeground(new java.awt.Color(153, 153, 153));
        txt_pertanyaan.setText("Buatlah PIN");
        txt_pertanyaan.setBorder(null);
        txt_pertanyaan.setOpaque(false);
        txt_pertanyaan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_pertanyaanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_pertanyaanFocusLost(evt);
            }
        });
        txt_pertanyaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pertanyaanActionPerformed(evt);
            }
        });
        getContentPane().add(txt_pertanyaan);
        txt_pertanyaan.setBounds(650, 360, 400, 50);

        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn buat normal.png"))); // NOI18N
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
        btn_simpan.setBounds(1110, 440, 180, 60);

        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn reset normal.png"))); // NOI18N
        btn_reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_resetMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_resetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_resetMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_resetMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_resetMouseReleased(evt);
            }
        });
        getContentPane().add(btn_reset);
        btn_reset.setBounds(890, 440, 190, 60);

        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn edit normal.png"))); // NOI18N
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
        btn_edit.setBounds(520, 666, 130, 44);

        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn delete normal.png"))); // NOI18N
        btn_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hapusMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_hapusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_hapusMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_hapusMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_hapusMouseReleased(evt);
            }
        });
        getContentPane().add(btn_hapus);
        btn_hapus.setBounds(660, 666, 140, 44);

        tabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabel.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel.setRowHeight(40);
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(510, 730, 1190, 230);

        lblnama7.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        getContentPane().add(lblnama7);
        lblnama7.setBounds(1750, 40, 130, 50);

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

        btn_akunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni normal.png"))); // NOI18N
        btn_akunPenghuni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_akunPenghuniMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_akunPenghuniMouseExited(evt);
            }
        });
        getContentPane().add(btn_akunPenghuni);
        btn_akunPenghuni.setBounds(-35, 710, 353, 69);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tutupan.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(250, 950, 126, 96);

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/Rectangle 53.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 700, 270, 10);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/form_akun.png"))); // NOI18N
        getContentPane().add(bg);
        bg.setBounds(0, 0, 1920, 1080);
        getContentPane().add(txt_noKamar);
        txt_noKamar.setBounds(1570, 330, 90, 30);

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

        setSize(new java.awt.Dimension(1980, 1080));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_idPengPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cmb_idPengPopupMenuWillBecomeInvisible
        String sql = "SELECT * FROM tb_kamar join tb_penghuni on tb_kamar.noKamar = tb_penghuni.noKamar WHERE tb_penghuni.id_penghuni ='"+cmb_idPeng.getSelectedItem()+"'";
        try{
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            ResultSet res = pst.executeQuery(sql);
            if(res.next()){
            txt_nama.setText(res.getString("tb_penghuni.nama_penghuni"));
            txt_noKamar.setText(res.getString("tb_penghuni.noKamar"));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_cmb_idPengPopupMenuWillBecomeInvisible

    private void btn_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseClicked
        try {
            
            String sql= "INSERT INTO tb_user VALUES ('"+cmb_idPeng.getSelectedItem()
                    +"','"+txt_nama.getText()+"','"+txt_noKamar.getText()+"', '"
                    +txt_pertanyaan.getText()+"','"+txt_pass.getText()+"')";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            pop_up_berhasilSimpan.setVisible(true);
            
//            DefaultTableModel dtm = (DefaultTableModel)tabel.getModel();
//            dtm.setRowCount(0);
//            datatable();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btn_simpanMouseClicked

    private void txt_pertanyaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pertanyaanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pertanyaanActionPerformed

    private void btn_resetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMouseClicked
        cmb_idPeng.enable();
        cmb_idPeng.setSelectedIndex(0);
        txt_nama.setText(null);
        txt_pertanyaan.setText(null);
        txt_pass.setText(null);
    }//GEN-LAST:event_btn_resetMouseClicked

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        int baris = tabel.getSelectedRow();
        if (baris != -1 ) {
            cmb_idPeng.disable();
            cmb_idPeng.setSelectedItem(tabel.getValueAt(baris, 0).toString());
            txt_nama.setText(tabel.getValueAt(baris, 1).toString());
            txt_pertanyaan.setText(tabel.getValueAt(baris, 2).toString());
            txt_pass.setText(tabel.getValueAt(baris, 3).toString());
        }
    }//GEN-LAST:event_tabelMouseClicked

    private void btn_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseClicked
        try{
            String sql = "UPDATE tb_user "
                    +"SET nama_penghuni ='"+txt_nama.getText()+"', pertanyaan = '"
                    +txt_pertanyaan.getText()+"', password = '"+txt_pass.getText()
                    +"'WHERE tb_user.id_penghuni = '"+cmb_idPeng.getSelectedItem()+"'";
            java.sql.Connection conn = (Connection)config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            pop_up_berhasilEdit.setVisible(true);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        datatable();              
    }//GEN-LAST:event_btn_editMouseClicked

    private void btn_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMouseClicked
//        try{
//        int row = tabel.getSelectedRow();
//        String id_penghuni = tabel.getModel().getValueAt(row, 0).toString();
//        String resultSet = "DELETE FROM tb_user WHERE id_penghuni='"+id_penghuni+"'";
//        
//        java.sql.Connection conn = (Connection)config.configDB();
//        java.sql.PreparedStatement pst = conn.prepareStatement(resultSet);
//        pst.execute();
        
        pop_up_konfirmasiHapus.setVisible(true);
//        }
//        catch(Exception e){
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
    }//GEN-LAST:event_btn_hapusMouseClicked

    private void txt_pertanyaanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pertanyaanFocusGained
        if(txt_pertanyaan.getText().equals("Buatlah PIN")){
            txt_pertanyaan.setText(null);
            txt_pertanyaan.requestFocus();
            removeplaceholderstyle(txt_pertanyaan);
        }
    }//GEN-LAST:event_txt_pertanyaanFocusGained

    private void txt_pertanyaanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pertanyaanFocusLost
        if(txt_pertanyaan.getText().length()==0){

            addplaceholderstyle(txt_pertanyaan);
            txt_pertanyaan.setText("Buatlah PIN");
        }
    }//GEN-LAST:event_txt_pertanyaanFocusLost

    private void txt_passFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_passFocusGained
        if(txt_pass.getText().equals("Buatlah Password")){
            txt_pass.setText(null);
            txt_pass.requestFocus();
            removeplaceholderstyle(txt_pass);
        }
    }//GEN-LAST:event_txt_passFocusGained

    private void txt_passFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_passFocusLost
        if(txt_pass.getText().length()==0){

            addplaceholderstyle(txt_pass);
            txt_pass.setText("Buatlah Password");
        }
    }//GEN-LAST:event_txt_passFocusLost

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(form_akunPenghuni.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void btn_simpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseEntered
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn buat entered.png")));
    }//GEN-LAST:event_btn_simpanMouseEntered

    private void btn_simpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseExited
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn buat normal.png")));
    }//GEN-LAST:event_btn_simpanMouseExited

    private void btn_simpanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMousePressed
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn buat press.png")));
    }//GEN-LAST:event_btn_simpanMousePressed

    private void btn_simpanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseReleased
        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn buat entered.png")));
    }//GEN-LAST:event_btn_simpanMouseReleased

    private void btn_resetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMouseEntered
        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn reset entered.png")));
    }//GEN-LAST:event_btn_resetMouseEntered

    private void btn_resetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMouseExited
        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn reset normal.png")));
    }//GEN-LAST:event_btn_resetMouseExited

    private void btn_resetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMousePressed
        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn reset press.png")));
    }//GEN-LAST:event_btn_resetMousePressed

    private void btn_resetMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMouseReleased
        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn reset entered.png")));
    }//GEN-LAST:event_btn_resetMouseReleased

    private void btn_hapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMouseEntered
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn delete entered.png")));
    }//GEN-LAST:event_btn_hapusMouseEntered

    private void btn_hapusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMouseExited
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn delete normal.png")));
    }//GEN-LAST:event_btn_hapusMouseExited

    private void btn_hapusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMousePressed
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn delete press.png")));
    }//GEN-LAST:event_btn_hapusMousePressed

    private void btn_hapusMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMouseReleased
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn delete entered.png")));
    }//GEN-LAST:event_btn_hapusMouseReleased

    private void btn_editMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseEntered
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn edit entered.png")));
    }//GEN-LAST:event_btn_editMouseEntered

    private void btn_editMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseExited
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn edit normal.png")));
    }//GEN-LAST:event_btn_editMouseExited

    private void btn_editMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMousePressed
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn edit press.png")));
    }//GEN-LAST:event_btn_editMousePressed

    private void btn_editMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseReleased
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_akunPenghuni/btn edit entered.png")));
    }//GEN-LAST:event_btn_editMouseReleased

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

    private void btn_akunPenghuniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_akunPenghuniMouseEntered
        btn_akunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni entered.png")));
    }//GEN-LAST:event_btn_akunPenghuniMouseEntered

    private void btn_akunPenghuniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_akunPenghuniMouseExited
        btn_akunPenghuni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hover_navbarAdmin/tab akun penghuni normal.png")));
    }//GEN-LAST:event_btn_akunPenghuniMouseExited

    private void btn_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_closeMouseClicked
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel)tabel.getModel();
            dtm.setRowCount(0);
            datatable();
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
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel)tabel.getModel();
            dtm.setRowCount(0);
            datatable();
            clear();
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
        try{
        int row = tabel.getSelectedRow();
        String id_penghuni = tabel.getModel().getValueAt(row, 0).toString();
        String resultSet = "DELETE FROM tb_user WHERE id_penghuni='"+id_penghuni+"'";
        
        java.sql.Connection conn = (Connection)config.configDB();
        java.sql.PreparedStatement pst = conn.prepareStatement(resultSet);
        pst.execute();
        
        pop_up_konfirmasiHapus.setVisible(false);
        datatable();
        pop_up_berhasilHapus.setVisible(true);
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
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
        clear();
        pop_up_berhasilEdit.setVisible(false);
    }//GEN-LAST:event_btn_oke2MouseClicked

    private void btn_oke2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_oke2MouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btn_oke2MouseEntered

    private void btn_oke2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_oke2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_oke2MouseExited

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
            java.util.logging.Logger.getLogger(form_akunPenghuni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_akunPenghuni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_akunPenghuni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_akunPenghuni.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_akunPenghuni().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_logout;
    private javax.swing.JLabel bg_pop_up_hapus;
    private javax.swing.JLabel bg_simpan;
    private javax.swing.JLabel bg_simpan1;
    private javax.swing.JLabel bg_simpan2;
    private javax.swing.JLabel btnX;
    private javax.swing.JLabel btn_akunPenghuni;
    private javax.swing.JLabel btn_batal;
    private javax.swing.JLabel btn_beranda;
    private javax.swing.JLabel btn_close;
    private javax.swing.JLabel btn_close1;
    private javax.swing.JLabel btn_close2;
    private javax.swing.JLabel btn_close3;
    private javax.swing.JLabel btn_edit;
    private javax.swing.JLabel btn_hapus;
    private javax.swing.JLabel btn_kamarKost;
    private javax.swing.JLabel btn_keluar;
    private javax.swing.JLabel btn_keluar1;
    private javax.swing.JLabel btn_komplain;
    private javax.swing.JLabel btn_logout;
    private javax.swing.JLabel btn_oke;
    private javax.swing.JLabel btn_oke1;
    private javax.swing.JLabel btn_oke2;
    private javax.swing.JLabel btn_oke3;
    private javax.swing.JLabel btn_penghuniKost;
    private javax.swing.JLabel btn_reset;
    private javax.swing.JLabel btn_simpan;
    private javax.swing.JLabel btn_tagihan;
    private javax.swing.JComboBox<String> cmb_idPeng;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jam_icon;
    private javax.swing.JLabel lbl_jam;
    private javax.swing.JLabel lbl_tanggal;
    public static final javax.swing.JLabel lblnama7 = new javax.swing.JLabel();
    private javax.swing.JLabel link_laporan;
    private javax.swing.JPanel logout;
    private javax.swing.JPanel pop_up_berhasilEdit;
    private javax.swing.JPanel pop_up_berhasilHapus;
    private javax.swing.JPanel pop_up_berhasilSimpan;
    private javax.swing.JPanel pop_up_konfirmasiHapus;
    private javax.swing.JTable tabel;
    private javax.swing.JLabel tanggal_icon;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JLabel txt_noKamar;
    private javax.swing.JTextField txt_pass;
    private javax.swing.JTextField txt_pertanyaan;
    // End of variables declaration//GEN-END:variables
}
