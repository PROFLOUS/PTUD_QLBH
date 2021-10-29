/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import dao.PanelSearch;
import dao.EventClick;
import dao.BanHangDao;
import dao.KhachHangDao;
import entity.KhachHang;
import entity.SanPham;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class FrmBanHang extends javax.swing.JPanel {
   private JPopupMenu menu;
    private PanelSearch search;
    private JPopupMenu menu2;
    private PanelSearch search2;
    BanHangDao bhDao;
    KhachHangDao khDao;
    private DefaultTableModel dfbh_model;
    private ArrayList<SanPham>dstt = null;
    private ArrayList<SanPham>dssp;
    private int soLuongTon = 0;
    private int soLuong =0;
    SanPham sp = new SanPham();
    private int tong;
    
  
    
    
    
    /**
     * Creates new form FrmBanHang
     * @param sp
     * @return 
     */
    
    
    public FrmBanHang() {
        initComponents();
        bhDao = new BanHangDao();
        khDao = new KhachHangDao();
        
        dstt = new ArrayList<SanPham>();
        dssp = new  ArrayList<SanPham>();
        
        
        
  
        
        
        menu = new JPopupMenu();
        search = new PanelSearch();
        
        menu2 = new JPopupMenu();
        search2 = new PanelSearch();
        
        menu2.add(search2);
        menu2.setFocusable(false);
        
        
        menu.add(search);
        menu.setFocusable(false);
        //even click chọn sam pham
        search.addEventClick(new EventClick() {
        int stt =0;
        Integer sl = 0;
        int vitri =0;
        Double tongtien = 0.0;
            @Override
            public void itemClick(SanPham data) {
                
                soLuongTon = data.getSoLuong();
                
                int r = tbl_BanHang.getSelectedRow();
                dfbh_model = (DefaultTableModel) tbl_BanHang.getModel();
                System.out.println("Click" +data.getTenSP());
                menu.setVisible(false);
                String maSp = (data.getMaSP());
                String tenSp = (data.getTenSP());
                String mau = (data.getMauSac());
                double donGia =( data.getDonGia());
                String size = (data.getSize());
                soLuong = 0;
                //hiện dialog nhập số lượng
//                Dialog_NhapSoLuong.setVisible(true);
//                Dialog_NhapSoLuong.setResizable(false);
//                Dialog_NhapSoLuong.setLocationRelativeTo(null);
                
//                soLuong = Integer.parseInt(txt_NhapSL.getText());
             
                int soLuong  = Integer.parseInt(  JOptionPane.showInputDialog(null,"Nhập vào số lượng","Số Lượng",JOptionPane.INFORMATION_MESSAGE));
                System.out.println(soLuong);

                SanPham sp = new SanPham(maSp, tenSp, donGia, soLuong, size, mau);
                double thanhTien = soLuong * donGia;
                
                vitri = vitriSP(sp);
                System.out.println("vitri :"+vitri);
               
                
                if(vitri>-1){
                    sl = dstt.get(vitri).getSoLuong()+sp.getSoLuong();
                    tongtien = sl * sp.getDonGia();
                    
                    dstt.get(vitri).setSoLuong(sl);
                    dfbh_model.setValueAt(sl, vitri, 3);
                    dfbh_model.setValueAt(tongtien, vitri, 5);
                    
                }else{
                    try {
                        dstt.add(sp);
                    } catch (Exception e) {
                         System.out.println("loi add");
                        e.printStackTrace();
                       
                    }
                    dfbh_model.addRow(new Object[]{
                        sp.getMaSP(),sp.getTenSP(),sp.getMauSac(),soLuong,sp.getDonGia(),thanhTien
                    });
                    
                }
                btn_XoaMatHang.setEnabled(true);
                TinhTong();
//                TinhTienThua();
            }
            @Override
            public void itemClick(KhachHang data)  {
                

            }
            
        });
        //enven click chon khach hang
        search2.addEventClick(new EventClick() {
            @Override
            public void itemClick(SanPham data) {
            
            }

            @Override
            public void itemClick(KhachHang data) {
               String maKh = data.getMaKH();
               String tenKh = data.getTenKH();
               String sDT = data.getSdt();
               
               lbl_TenKh.setText(tenKh);
               lbl_maKh.setText(maKh);
               lbl_SDT.setText(sDT);
               menu2.setVisible(false);
               lbl_XoaKh.setEnabled(true);
//               txt_Search_KH.setEnabled(false);
               txt_Search_KH.setVisible(false);
               jButton7.setVisible(false);
               
               
               
            }
        });
        
        
        
    }
    
    public int vitriSP(SanPham sp){
        
        int i =-1;
        try {
            
        if(dstt.contains(sp)){
            return dstt.indexOf(sp);
        }
        
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("loi");
        }
        
         return  i;
    }
    
    public void huy(int r){
        int i = 1;
        String masp = dstt.get(r).getMaSP();
        int index = -1;
        for(SanPham sp : dssp)
        {
            if(sp.getMaSP() == masp){
            break;
            }
        }
        
        
        dfbh_model.removeRow(r);
        dstt.remove(r);
    }
    public void TinhTong(){
        DecimalFormat x = new DecimalFormat("###,###,###");
        tong =0;
        for( int i = 0;i<tbl_BanHang.getRowCount();i++){
            tong += Double.parseDouble( tbl_BanHang.getValueAt(i, 5).toString());
        }
        System.out.println("tong"+tong);
        lbl_TongTien.setText(x.format(tong));
        lbl_TienPhaiTra.setText(x.format(tong));
//        lbl_TongTien.setText(String.valueOf(tong));
//        lbl_TienPhaiTra.setText(String.valueOf(tong));
        
        
    }
    public void TinhTienThua(){
        DecimalFormat x = new DecimalFormat("###,###,###");
        double d = 3.76628729;
        double tienThua = 0;
        double tienTra = tong;
        double tienDua = Double.valueOf(txt_TienDua.getText().toString());
        
        tienThua = tienDua-tienTra;
        System.out.println("tienthua"+tienThua);
        lbl_TienThua.setText(x.format(tienThua));
    }
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Dialog_NhapSoLuong = new javax.swing.JDialog();
        btn_Cancel = new javax.swing.JButton();
        lbl_IconSL = new javax.swing.JLabel();
        lbl_NhapSL = new javax.swing.JLabel();
        btn_OK = new javax.swing.JButton();
        txt_NhapSL = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        pnl_FormBanHang = new javax.swing.JPanel();
        lbl_text_BanHang = new javax.swing.JLabel();
        pnl_tab_Form_BangHang = new javax.swing.JPanel();
        pnl_ThanhToan = new javax.swing.JPanel();
        txt_Search_KH = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        lbl_TenKh = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_TongTien = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_TienPhaiTra = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbl_TienThua = new javax.swing.JLabel();
        txt_Ghichu = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        txt_TienDua = new javax.swing.JTextField();
        lbl_SDT = new javax.swing.JLabel();
        lbl_Ket = new javax.swing.JLabel();
        lbl_XoaKh = new javax.swing.JLabel();
        lbl_maKh = new javax.swing.JLabel();
        lbl_Ket1 = new javax.swing.JLabel();
        lbl_TenKh1 = new javax.swing.JLabel();
        lbl_TenKh2 = new javax.swing.JLabel();
        pnl_textSearch = new javax.swing.JPanel();
        txt_Search_SP = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        btn_XoaMatHang = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_BanHang = new javax.swing.JTable();

        Dialog_NhapSoLuong.setAlwaysOnTop(true);
        Dialog_NhapSoLuong.setAutoRequestFocus(false);
        Dialog_NhapSoLuong.setBackground(new java.awt.Color(255, 255, 255));
        Dialog_NhapSoLuong.setSize(new java.awt.Dimension(325, 160));
        Dialog_NhapSoLuong.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_Cancel.setBackground(new java.awt.Color(153, 153, 153));
        btn_Cancel.setForeground(new java.awt.Color(0, 0, 0));
        btn_Cancel.setText("Cancel");
        Dialog_NhapSoLuong.getContentPane().add(btn_Cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        lbl_IconSL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgVSicon/density (1).png"))); // NOI18N
        Dialog_NhapSoLuong.getContentPane().add(lbl_IconSL, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, 70));

        lbl_NhapSL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_NhapSL.setForeground(new java.awt.Color(0, 0, 0));
        lbl_NhapSL.setText("Nhập vào số lượng:");
        Dialog_NhapSoLuong.getContentPane().add(lbl_NhapSL, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 166, 20));

        btn_OK.setBackground(new java.awt.Color(102, 153, 255));
        btn_OK.setForeground(new java.awt.Color(0, 0, 0));
        btn_OK.setText("OK");
        btn_OK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_OKMouseClicked(evt);
            }
        });
        Dialog_NhapSoLuong.getContentPane().add(btn_OK, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 60, -1));

        txt_NhapSL.setBackground(new java.awt.Color(255, 255, 255));
        txt_NhapSL.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_NhapSL.setForeground(new java.awt.Color(0, 0, 0));
        txt_NhapSL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_NhapSLKeyTyped(evt);
            }
        });
        Dialog_NhapSoLuong.getContentPane().add(txt_NhapSL, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 210, 30));

        jLabel1.setText("jLabel1");

        setPreferredSize(new java.awt.Dimension(1090, 700));

        pnl_FormBanHang.setBackground(new java.awt.Color(255, 255, 255));
        pnl_FormBanHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_text_BanHang.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        lbl_text_BanHang.setForeground(new java.awt.Color(0, 153, 204));
        lbl_text_BanHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_text_BanHang.setText("Bán Hàng");
        pnl_FormBanHang.add(lbl_text_BanHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 138, 30));

        pnl_tab_Form_BangHang.setBackground(new java.awt.Color(102, 153, 255));
        pnl_tab_Form_BangHang.setPreferredSize(new java.awt.Dimension(1090, 720));
        pnl_tab_Form_BangHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_ThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        pnl_ThanhToan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 3, 0, 0, new java.awt.Color(102, 153, 255)));

        txt_Search_KH.setBackground(new java.awt.Color(255, 255, 255));
        txt_Search_KH.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        txt_Search_KH.setForeground(new java.awt.Color(153, 153, 153));
        txt_Search_KH.setText("Thêm Khách Hàng Vào Đơn");
        txt_Search_KH.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 153, 255)));
        txt_Search_KH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_Search_KHFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_Search_KHFocusLost(evt);
            }
        });
        txt_Search_KH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_Search_KHMouseClicked(evt);
            }
        });
        txt_Search_KH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_Search_KHKeyReleased(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgVSicon/magnifier.png"))); // NOI18N
        jButton7.setBorder(null);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        lbl_TenKh.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lbl_TenKh.setForeground(new java.awt.Color(0, 0, 0));
        lbl_TenKh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tổng Tiền:");

        lbl_TongTien.setBackground(new java.awt.Color(255, 255, 255));
        lbl_TongTien.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lbl_TongTien.setForeground(new java.awt.Color(0, 0, 0));
        lbl_TongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_TongTien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 153, 255)));

        jLabel9.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Khách Hàng Phải Trả:");

        lbl_TienPhaiTra.setBackground(new java.awt.Color(255, 255, 255));
        lbl_TienPhaiTra.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lbl_TienPhaiTra.setForeground(new java.awt.Color(0, 102, 204));
        lbl_TienPhaiTra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel11.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tiền Khách Đưa:");

        jLabel13.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tiền Thừa:");

        lbl_TienThua.setBackground(new java.awt.Color(255, 255, 255));
        lbl_TienThua.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lbl_TienThua.setForeground(new java.awt.Color(0, 0, 0));
        lbl_TienThua.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_TienThua.setText("0");
        lbl_TienThua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lbl_TienThuaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lbl_TienThuaKeyTyped(evt);
            }
        });

        txt_Ghichu.setBackground(new java.awt.Color(255, 255, 255));
        txt_Ghichu.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        txt_Ghichu.setForeground(new java.awt.Color(153, 153, 153));
        txt_Ghichu.setText("Nhập ghi chú đơn hàng");
        txt_Ghichu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 153, 255)));
        txt_Ghichu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_GhichuFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_GhichuFocusLost(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgVSicon/pencil1.png"))); // NOI18N
        jButton9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 153, 255)));
        jButton9.setContentAreaFilled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(102, 153, 255));
        jButton10.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Thanh Toán");
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        txt_TienDua.setBackground(new java.awt.Color(255, 255, 255));
        txt_TienDua.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        txt_TienDua.setForeground(new java.awt.Color(0, 0, 0));
        txt_TienDua.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_TienDua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 153, 255)));
        txt_TienDua.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_TienDuaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_TienDuaFocusLost(evt);
            }
        });
        txt_TienDua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_TienDuaMouseClicked(evt);
            }
        });
        txt_TienDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TienDuaActionPerformed(evt);
            }
        });
        txt_TienDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_TienDuaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_TienDuaKeyTyped(evt);
            }
        });

        lbl_SDT.setFont(new java.awt.Font("Consolas", 2, 18)); // NOI18N
        lbl_SDT.setForeground(new java.awt.Color(0, 0, 0));

        lbl_Ket.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lbl_Ket.setForeground(new java.awt.Color(0, 0, 0));
        lbl_Ket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Ket.setText("-");

        lbl_XoaKh.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        lbl_XoaKh.setForeground(new java.awt.Color(0, 0, 0));
        lbl_XoaKh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_XoaKh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgVSicon/close.png"))); // NOI18N
        lbl_XoaKh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_XoaKh.setEnabled(false);
        lbl_XoaKh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_XoaKhMouseClicked(evt);
            }
        });

        lbl_maKh.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbl_maKh.setForeground(new java.awt.Color(255, 255, 255));
        lbl_maKh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lbl_Ket1.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lbl_Ket1.setForeground(new java.awt.Color(0, 0, 0));
        lbl_Ket1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Ket1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 153, 255)));

        lbl_TenKh1.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lbl_TenKh1.setForeground(new java.awt.Color(0, 0, 0));
        lbl_TenKh1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_TenKh1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 153, 255)));

        lbl_TenKh2.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lbl_TenKh2.setForeground(new java.awt.Color(0, 0, 0));
        lbl_TenKh2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_TenKh2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 153, 255)));

        javax.swing.GroupLayout pnl_ThanhToanLayout = new javax.swing.GroupLayout(pnl_ThanhToan);
        pnl_ThanhToan.setLayout(pnl_ThanhToanLayout);
        pnl_ThanhToanLayout.setHorizontalGroup(
            pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ThanhToanLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnl_ThanhToanLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(116, 116, 116)
                        .addComponent(lbl_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150)
                        .addComponent(txt_Search_KH, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_ThanhToanLayout.createSequentialGroup()
                        .addGroup(pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_ThanhToanLayout.createSequentialGroup()
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txt_Ghichu, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(pnl_ThanhToanLayout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(12, 12, 12)
                                    .addComponent(lbl_TienPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ThanhToanLayout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(99, 99, 99)
                                    .addComponent(lbl_TienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnl_ThanhToanLayout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_TienDua, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(159, 159, 159)
                        .addComponent(lbl_maKh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_ThanhToanLayout.createSequentialGroup()
                                .addGroup(pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_TenKh1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_TenKh, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_Ket1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_Ket, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_TenKh2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_XoaKh)
                        .addGap(31, 31, 31))))
        );
        pnl_ThanhToanLayout.setVerticalGroup(
            pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ThanhToanLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Search_KH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnl_ThanhToanLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ThanhToanLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_ThanhToanLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnl_ThanhToanLayout.createSequentialGroup()
                                .addComponent(lbl_Ket, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_Ket1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_TenKh2)
                            .addGroup(pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbl_SDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_TienPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_XoaKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_maKh, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ThanhToanLayout.createSequentialGroup()
                                    .addComponent(lbl_TenKh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(4, 4, 4)
                                    .addComponent(lbl_TenKh1))))))
                .addGap(24, 24, 24)
                .addGroup(pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnl_ThanhToanLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_TienDua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ThanhToanLayout.createSequentialGroup()
                        .addGroup(pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_TienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnl_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Ghichu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 48, Short.MAX_VALUE))
        );

        pnl_tab_Form_BangHang.add(pnl_ThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 350, 1080, 310));

        pnl_textSearch.setBackground(new java.awt.Color(102, 153, 255));
        pnl_textSearch.setPreferredSize(new java.awt.Dimension(1090, 100));

        txt_Search_SP.setBackground(new java.awt.Color(102, 153, 255));
        txt_Search_SP.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        txt_Search_SP.setForeground(new java.awt.Color(153, 204, 255));
        txt_Search_SP.setText("Thêm Sản Phẩm Vào Đơn Hàng");
        txt_Search_SP.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_Search_SP.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_Search_SP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_Search_SPFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_Search_SPFocusLost(evt);
            }
        });
        txt_Search_SP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_Search_SPMouseClicked(evt);
            }
        });
        txt_Search_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Search_SPActionPerformed(evt);
            }
        });
        txt_Search_SP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_Search_SPKeyReleased(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgVSicon/search3.png"))); // NOI18N
        jButton8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        btn_XoaMatHang.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        btn_XoaMatHang.setForeground(new java.awt.Color(255, 255, 255));
        btn_XoaMatHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgVSicon/delete.png"))); // NOI18N
        btn_XoaMatHang.setMnemonic('D');
        btn_XoaMatHang.setToolTipText("Xóa sản phẩm khỏi đơn");
        btn_XoaMatHang.setBorder(null);
        btn_XoaMatHang.setContentAreaFilled(false);
        btn_XoaMatHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_XoaMatHang.setEnabled(false);
        btn_XoaMatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_XoaMatHangMouseClicked(evt);
            }
        });
        btn_XoaMatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaMatHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_textSearchLayout = new javax.swing.GroupLayout(pnl_textSearch);
        pnl_textSearch.setLayout(pnl_textSearchLayout);
        pnl_textSearchLayout.setHorizontalGroup(
            pnl_textSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_textSearchLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jButton8)
                .addGap(0, 0, 0)
                .addComponent(txt_Search_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 478, Short.MAX_VALUE)
                .addComponent(btn_XoaMatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        pnl_textSearchLayout.setVerticalGroup(
            pnl_textSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_textSearchLayout.createSequentialGroup()
                .addGroup(pnl_textSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnl_textSearchLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txt_Search_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_textSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_XoaMatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnl_tab_Form_BangHang.add(pnl_textSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 50));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 5, 0, new java.awt.Color(102, 153, 255)));

        jScrollPane2.setBackground(new java.awt.Color(102, 153, 255));
        jScrollPane2.setForeground(new java.awt.Color(51, 153, 255));

        tbl_BanHang.setBackground(new java.awt.Color(51, 51, 51));
        tbl_BanHang.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        tbl_BanHang.setForeground(new java.awt.Color(0, 0, 0));
        tbl_BanHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Màu", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_BanHang.setFocusable(false);
        tbl_BanHang.setGridColor(new java.awt.Color(255, 255, 255));
        tbl_BanHang.setRowHeight(30);
        tbl_BanHang.setSelectionBackground(new java.awt.Color(102, 204, 255));
        tbl_BanHang.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbl_BanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_BanHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_BanHang);
        if (tbl_BanHang.getColumnModel().getColumnCount() > 0) {
            tbl_BanHang.getColumnModel().getColumn(0).setMaxWidth(150);
            tbl_BanHang.getColumnModel().getColumn(1).setMaxWidth(400);
            tbl_BanHang.getColumnModel().getColumn(2).setMaxWidth(150);
            tbl_BanHang.getColumnModel().getColumn(3).setMaxWidth(100);
            tbl_BanHang.getColumnModel().getColumn(4).setMaxWidth(150);
            tbl_BanHang.getColumnModel().getColumn(5).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
        );

        pnl_tab_Form_BangHang.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1090, 300));

        pnl_FormBanHang.add(pnl_tab_Form_BangHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1090, 760));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1090, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnl_FormBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnl_FormBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_Search_KHFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Search_KHFocusGained
        if(txt_Search_KH.getText().equals("Thêm Khách Hàng Vào Đơn")){
            txt_Search_KH.setText("");
            txt_Search_KH.setForeground(Color.black);
        }
    }//GEN-LAST:event_txt_Search_KHFocusGained

    private void txt_Search_KHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Search_KHFocusLost
        if(txt_Search_KH.getText().equals("")){
            txt_Search_KH.setText("Thêm Khách Hàng Vào Đơn");
            txt_Search_KH.setForeground(Color.black);
        }
    }//GEN-LAST:event_txt_Search_KHFocusLost

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txt_GhichuFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_GhichuFocusGained
        if(txt_Ghichu.getText().equals("Nhập ghi chú đơn hàng")){
            txt_Ghichu.setText("");
            txt_Ghichu.setForeground(Color.black);
        }
    }//GEN-LAST:event_txt_GhichuFocusGained

    private void txt_GhichuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_GhichuFocusLost
        if(txt_Ghichu.getText().equals("")){
            txt_Ghichu.setText("Nhập ghi chú đơn hàng");
            txt_Ghichu.setForeground(Color.black);
        }
    }//GEN-LAST:event_txt_GhichuFocusLost

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void txt_TienDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TienDuaActionPerformed
       TinhTienThua();
       btn_XoaMatHang.setEnabled(false);
    }//GEN-LAST:event_txt_TienDuaActionPerformed

    private void txt_Search_SPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Search_SPFocusGained
        if(txt_Search_SP.getText().equals("Thêm Sản Phẩm Vào Đơn Hàng")){
            txt_Search_SP.setText("");
            txt_Search_SP.setForeground(Color.white);
        }
    }//GEN-LAST:event_txt_Search_SPFocusGained

    private void txt_Search_SPFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_Search_SPFocusLost
        if(txt_Search_SP.getText().equals("")){
            txt_Search_SP.setText("Thêm Sản Phẩm Vào Đơn Hàng");
            txt_Search_SP.setForeground(Color.white);
        }
    }//GEN-LAST:event_txt_Search_SPFocusLost

    private void txt_Search_SPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_Search_SPMouseClicked
        if (search.getItemSize() > 0) {
            menu.show(txt_Search_SP, 0, txt_Search_SP.getHeight());
        }
    }//GEN-LAST:event_txt_Search_SPMouseClicked

    private void txt_Search_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Search_SPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_Search_SPActionPerformed

    private void txt_Search_SPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Search_SPKeyReleased
        String text = txt_Search_SP.getText().trim().toLowerCase();
        search.setDataSP(bhDao.SearchSp(text));
        if (search.getItemSize() > 0) {
            //  * 2 top and bot border
            menu.show(txt_Search_SP, 0, txt_Search_SP.getHeight());
            //            menu.setPopupSize(menu.getWidth(), (search.getItemSize() * 35) + 2);
        } else {
            menu.setVisible(false);
        }
    }//GEN-LAST:event_txt_Search_SPKeyReleased

//    private List<SanPham> search(String search) {
//        int limitData = 7;
//        List<DataSearch> list = new ArrayList<>();
//        
//        
//        return list;
//    }


    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btn_XoaMatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XoaMatHangMouseClicked
        System.out.println("ok ");
        int r = tbl_BanHang.getSelectedRow();
        if (r!=-1){
            huy(r);
            
        }else{
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần xóa!");
        }
        
    }//GEN-LAST:event_btn_XoaMatHangMouseClicked

    private void btn_XoaMatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaMatHangActionPerformed
        
    }//GEN-LAST:event_btn_XoaMatHangActionPerformed

    private void tbl_BanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_BanHangMouseClicked
        // TODO add your handling code here:
        
        //        setButtonBorder(jButton1);
    }//GEN-LAST:event_tbl_BanHangMouseClicked

    private void txt_TienDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TienDuaKeyReleased
       
        String sByr = txt_TienDua.getText().toString();
        double dblByr = Double.parseDouble(sByr);
        DecimalFormat df = new DecimalFormat("### ### ###");
        txt_TienDua.setText(df.format(dblByr));
    }//GEN-LAST:event_txt_TienDuaKeyReleased

    private void lbl_TienThuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbl_TienThuaKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            TinhTienThua();
//            btn_XoaMatHang.setEnabled(true);
//        }
    }//GEN-LAST:event_lbl_TienThuaKeyPressed

    private void txt_TienDuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TienDuaKeyTyped
        if(Character.isLetter(evt.getKeyChar()))
            evt.consume();
        else{
            try {
                Double.parseDouble(txt_TienDua.getText()+evt.getKeyChar());
            } catch (NumberFormatException e) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txt_TienDuaKeyTyped

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
        
    }//GEN-LAST:event_jButton10MouseClicked

    private void txt_NhapSLKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NhapSLKeyTyped
        if(Character.isLetter(evt.getKeyChar()))
            evt.consume();
        else{
            try {
                Double.parseDouble(txt_NhapSL.getText()+evt.getKeyChar());
            } catch (NumberFormatException e) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txt_NhapSLKeyTyped

    private void btn_OKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_OKMouseClicked
        Dialog_NhapSoLuong.setVisible(false);
         
    }//GEN-LAST:event_btn_OKMouseClicked

    private void txt_Search_KHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Search_KHKeyReleased
        String text = txt_Search_KH.getText().trim().toLowerCase();
        search2.setDataKh(khDao.SearchKh(text));
        if (search2.getItemSize() > 0) {
            //  * 2 top and bot border
            menu2.show(txt_Search_KH, 0, txt_Search_KH.getHeight());
            //            menu.setPopupSize(menu.getWidth(), (search.getItemSize() * 35) + 2);
        } else {
            menu2.setVisible(false);
        }
    }//GEN-LAST:event_txt_Search_KHKeyReleased

    private void txt_Search_KHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_Search_KHMouseClicked
        if (search2.getItemSize() > 0) {
            menu2.show(txt_Search_KH, 0, txt_Search_KH.getHeight());
        }
        if(txt_Search_KH.getText().length()<0){
            menu2.setVisible(false);
        }
    }//GEN-LAST:event_txt_Search_KHMouseClicked

    private void lbl_TienThuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbl_TienThuaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_TienThuaKeyTyped

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void lbl_XoaKhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_XoaKhMouseClicked
               lbl_TenKh.setText("");
               lbl_maKh.setText("");
               lbl_SDT.setText("");
              txt_Search_KH.setVisible(true);
               jButton7.setVisible(true);
              
    }//GEN-LAST:event_lbl_XoaKhMouseClicked

    private void txt_TienDuaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_TienDuaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TienDuaFocusLost

    private void txt_TienDuaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_TienDuaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TienDuaFocusGained

    private void txt_TienDuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_TienDuaMouseClicked
        
    }//GEN-LAST:event_txt_TienDuaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Dialog_NhapSoLuong;
    private javax.swing.JButton btn_Cancel;
    private javax.swing.JButton btn_OK;
    private javax.swing.JButton btn_XoaMatHang;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_IconSL;
    private javax.swing.JLabel lbl_Ket;
    private javax.swing.JLabel lbl_Ket1;
    private javax.swing.JLabel lbl_NhapSL;
    private javax.swing.JLabel lbl_SDT;
    private javax.swing.JLabel lbl_TenKh;
    private javax.swing.JLabel lbl_TenKh1;
    private javax.swing.JLabel lbl_TenKh2;
    private javax.swing.JLabel lbl_TienPhaiTra;
    private javax.swing.JLabel lbl_TienThua;
    private javax.swing.JLabel lbl_TongTien;
    private javax.swing.JLabel lbl_XoaKh;
    private javax.swing.JLabel lbl_maKh;
    private javax.swing.JLabel lbl_text_BanHang;
    private javax.swing.JPanel pnl_FormBanHang;
    private javax.swing.JPanel pnl_ThanhToan;
    private javax.swing.JPanel pnl_tab_Form_BangHang;
    private javax.swing.JPanel pnl_textSearch;
    private javax.swing.JTable tbl_BanHang;
    private javax.swing.JTextField txt_Ghichu;
    private javax.swing.JTextField txt_NhapSL;
    private javax.swing.JTextField txt_Search_KH;
    private javax.swing.JTextField txt_Search_SP;
    private javax.swing.JTextField txt_TienDua;
    // End of variables declaration//GEN-END:variables
}
