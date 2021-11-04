/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Connect.connect;
import dao.CaLamDao;
import dao.ChucVuDao;
import dao.LuongDao;
import dao.NhanVienDao;
import entity.CaLam;
import entity.ChucVu;
import entity.Luong;
import entity.NhanVien;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class FrmNhanVien extends javax.swing.JPanel {
    public  Border default_border = BorderFactory.createMatteBorder(0, 0   , 3, 0, new Color(153,153,153));
   public Border active_border = BorderFactory.createMatteBorder(0, 0   , 3, 0, new Color(153,204,255));
   public JButton [] buttons;
   private DefaultTableModel dfCL_Model;
   private DefaultTableModel dfNV2_Model;
   private DefaultTableModel dfLuong_Model;
   ArrayList<CaLam> dsCa;
   ArrayList<NhanVien> dsNv;
    ArrayList<Luong> dsLuong;
    ArrayList<ChucVu> dsCv;
    CaLamDao ca_dao;
    NhanVienDao nv_dao;
    LuongDao l_dao;
    ChucVuDao cv_dao;

    /**
     * Creates new form FrmNhanVien
     */
    public FrmNhanVien() {
        initComponents();
        addBorder();
        dsCa = new ArrayList<CaLam>();
        ca_dao = new CaLamDao();
        nv_dao = new NhanVienDao();
        dsNv = new ArrayList<NhanVien>();
        dsLuong = new ArrayList<Luong>();
        dsCv = new ArrayList<ChucVu>();
        cv_dao = new ChucVuDao();
        l_dao = new LuongDao();
        upTblCaLam();
        upTblNV2();
        upCbo_NV();
        upTblLuong();
        upCbo_CV();
//        LuongNhanVien();
        
        txt_MaCa.setEnabled(false);
//        upCbo_Buoi();

    }
    //tìm tên chúc vụ trong bảng lương
    public  void TKCV(){
        l_dao = new LuongDao();
        String ten = cbo_TkCv_Luong.getSelectedItem().toString();
       
        if(ten.equals("Tất Cả")){
            dsLuong.removeAll(dsLuong);
            xoaModelLuong();
            upTblLuong();
        }else{
            ArrayList<Luong> list = l_dao.searchTenCV(ten);
        
        if(!list.isEmpty()){
            for (Luong luong : list) {
                dfLuong_Model.addRow(new Object[]{
                    luong.getMaNV().getMaNV(),luong.getMaNV().getTenNV(),luong.getMaCV().getTenCV(),
                    luong.getMaCV().getHsLuong(),luong.getSoCa(),luong.getLuong()
                });
            }
//            JOptionPane.showMessageDialog(this, "Đã tìm thấy ");
        }else{
            JOptionPane.showMessageDialog(this, " Khống có chức vụ nào ");
        }
        }
    }
    //doc du lieu len cbo chuc vu
    public  void upCbo_CV(){
        dsCv = cv_dao.getAllCV();
        for (ChucVu cv : dsCv) {
            cbo_TkCv_Luong.addItem(cv.getTenCV());
        }
    }
    //Hiển thị lương nhan viên lên bảng
    public void upTblLuong() {
        dfLuong_Model = (DefaultTableModel) tbl_Luong.getModel();
        dsLuong = l_dao.getAllLuong();
        for (Luong luong : dsLuong) {
            dfLuong_Model.addRow(new Object[]{
                luong.getMaNV().getMaNV(),luong.getMaNV().getTenNV(),luong.getMaCV().getTenCV(),
                luong.getMaCV().getHsLuong(),luong.getSoCa(),luong.getLuong()
            });
        }

    }
    //xóa model luong
    public void xoaModelLuong(){
        DefaultTableModel del = (DefaultTableModel) tbl_Luong.getModel();
        del.getDataVector().removeAllElements();
    }
    //xóa model ca làm
    public void xoaModelCa(){
        DefaultTableModel del = (DefaultTableModel) tbl_CaLam.getModel();
        del.getDataVector().removeAllElements();
    }
    //láy dữ liệu từ textfield
    public CaLam restText(){
        String maCa = txt_MaCa.getText().toString();
        String maNv = cbo_MaNv.getSelectedItem().toString();
        String buoi = cbo_Buoi.getSelectedItem().toString();
        NhanVien nv = nv_dao.getNVByMaNV(maNv);
        
        return new CaLam(maCa, nv, buoi);
    }
   
    //xoá rổng textfield ca làm
    public void xoaRongTextCa(){
        txt_MaCa.setText("");
        
    }
    
    //đọc dữ liệu lên bảg nhân viên 2
    public void upTblNV2(){
        dfNV2_Model = (DefaultTableModel) tbl_NV_2.getModel();
        dsNv = nv_dao.getAllNV();
        for (NhanVien nv : dsNv){
            dfNV2_Model.addRow(new Object[]{
                nv.getMaNV(),nv.getTenNV(),nv.getChucVu().getTenCV()
            });
        }
    }
    //đọc dữ liệu lên bảng ca làm
    public void upTblCaLam(){
        dfCL_Model = (DefaultTableModel) tbl_CaLam.getModel();
        dsCa = ca_dao.getAllCaLam();
        for (CaLam ca : dsCa) {
            dfCL_Model.addRow(new Object[]{
                ca.getMaCa(),ca.getNV().getMaNV(),ca.getNV().getTenNV(),ca.getBuoi()
            });
        }
        
    }
    //dọc dữ liêu lên cbo nhân vien
    public  void upCbo_NV(){
        
        for (NhanVien nv : dsNv) {
            cbo_MaNv.addItem(nv.getMaNV());
        }
    }
    
    public void addBorder(){
        buttons = new JButton[4];
        
          buttons[0] =btn_tab_NV;
        buttons[1] =btn_tab_CaLam;
        buttons[2] = btn_tab_Luong;
        buttons[3] = btn_tab_TkNV;
        
        setButtonBorder(btn_tab_NV);
        
   
        

        addAction();
    }
        //set border active
    public void setButtonBorder(JButton button){
//        for (JButton btn : buttons) {
//            btn.setBorder(default_border);
//            btn.setForeground(new Color(153,153,153));
//        }
        button.setBorder(active_border);
        button.setForeground(Color.black);
    }
    //add even
    public void addAction(){
        for (JButton button : buttons) {
            button.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (JButton btn : buttons) {
            btn.setBorder(default_border);
            btn.setForeground(new Color(153,153,153));
        }
        button.setBorder(active_border);
        button.setForeground(Color.black);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                   
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    
                }
            });
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_FormNhanVien = new javax.swing.JPanel();
        lbl_text_NhanVien = new javax.swing.JLabel();
        pnl_menuTab_NhanVien = new javax.swing.JPanel();
        btn_tab_NV = new javax.swing.JButton();
        btn_tab_CaLam = new javax.swing.JButton();
        btn_tab_Luong = new javax.swing.JButton();
        btn_tab_TkNV = new javax.swing.JButton();
        pnl_tab_FormTTNhanVien = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        pnl_tab_FormCaLam = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_CaLam = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btn_ThemCa = new javax.swing.JButton();
        btn_LuuCa = new javax.swing.JButton();
        btn_SuaCa = new javax.swing.JButton();
        btn_XoaCa = new javax.swing.JButton();
        txt_MaCa = new javax.swing.JTextField();
        lbl_MaCa = new javax.swing.JLabel();
        lbl_MauSac1 = new javax.swing.JLabel();
        cbo_Buoi = new javax.swing.JComboBox<>();
        lbl_DanhMuc = new javax.swing.JLabel();
        cbo_MaNv = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_NV_2 = new javax.swing.JTable();
        pnl_tab_FormLuong = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_Luong = new javax.swing.JTable();
        cbo_TkCv_Luong = new javax.swing.JComboBox<>();
        txt_TkNV_Luong = new javax.swing.JTextField();
        btn_TinhLuong = new javax.swing.JButton();
        btn_CapNhat_Luong = new javax.swing.JToggleButton();
        pnl_tab_FormTKNV = new javax.swing.JPanel();

        pnl_FormNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        pnl_FormNhanVien.setMinimumSize(new java.awt.Dimension(1090, 700));
        pnl_FormNhanVien.setPreferredSize(new java.awt.Dimension(1090, 700));
        pnl_FormNhanVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_text_NhanVien.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        lbl_text_NhanVien.setForeground(new java.awt.Color(0, 153, 204));
        lbl_text_NhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_text_NhanVien.setText("Nhân Viên");
        pnl_FormNhanVien.add(lbl_text_NhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 138, 30));

        pnl_menuTab_NhanVien.setBackground(new java.awt.Color(255, 255, 255));

        btn_tab_NV.setBackground(new java.awt.Color(255, 255, 255));
        btn_tab_NV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btn_tab_NV.setForeground(new java.awt.Color(0, 0, 0));
        btn_tab_NV.setText("Thông Tin Nhân Viên");
        btn_tab_NV.setBorder(null);
        btn_tab_NV.setContentAreaFilled(false);
        btn_tab_NV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tab_NVMouseClicked(evt);
            }
        });
        btn_tab_NV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tab_NVActionPerformed(evt);
            }
        });

        btn_tab_CaLam.setBackground(new java.awt.Color(255, 255, 255));
        btn_tab_CaLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btn_tab_CaLam.setForeground(new java.awt.Color(153, 153, 153));
        btn_tab_CaLam.setText("Ca Làm");
        btn_tab_CaLam.setBorder(null);
        btn_tab_CaLam.setContentAreaFilled(false);
        btn_tab_CaLam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tab_CaLamMouseClicked(evt);
            }
        });

        btn_tab_Luong.setBackground(new java.awt.Color(255, 255, 255));
        btn_tab_Luong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btn_tab_Luong.setForeground(new java.awt.Color(153, 153, 153));
        btn_tab_Luong.setText("Lương");
        btn_tab_Luong.setBorder(null);
        btn_tab_Luong.setContentAreaFilled(false);
        btn_tab_Luong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tab_LuongMouseClicked(evt);
            }
        });

        btn_tab_TkNV.setBackground(new java.awt.Color(255, 255, 255));
        btn_tab_TkNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btn_tab_TkNV.setForeground(new java.awt.Color(153, 153, 153));
        btn_tab_TkNV.setText("Tìm Kiếm Nhân Viên");
        btn_tab_TkNV.setBorder(null);
        btn_tab_TkNV.setContentAreaFilled(false);
        btn_tab_TkNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tab_TkNVMouseClicked(evt);
            }
        });
        btn_tab_TkNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tab_TkNVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_menuTab_NhanVienLayout = new javax.swing.GroupLayout(pnl_menuTab_NhanVien);
        pnl_menuTab_NhanVien.setLayout(pnl_menuTab_NhanVienLayout);
        pnl_menuTab_NhanVienLayout.setHorizontalGroup(
            pnl_menuTab_NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_menuTab_NhanVienLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btn_tab_NV)
                .addGap(65, 65, 65)
                .addComponent(btn_tab_TkNV)
                .addGap(65, 65, 65)
                .addComponent(btn_tab_CaLam)
                .addGap(65, 65, 65)
                .addComponent(btn_tab_Luong)
                .addContainerGap(451, Short.MAX_VALUE))
        );
        pnl_menuTab_NhanVienLayout.setVerticalGroup(
            pnl_menuTab_NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_menuTab_NhanVienLayout.createSequentialGroup()
                .addGroup(pnl_menuTab_NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_menuTab_NhanVienLayout.createSequentialGroup()
                        .addComponent(btn_tab_NV, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btn_tab_CaLam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_tab_Luong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_tab_TkNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnl_FormNhanVien.add(pnl_menuTab_NhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1090, 50));

        pnl_tab_FormTTNhanVien.setBackground(new java.awt.Color(0, 153, 153));

        jButton1.setText("jButton1");

        javax.swing.GroupLayout pnl_tab_FormTTNhanVienLayout = new javax.swing.GroupLayout(pnl_tab_FormTTNhanVien);
        pnl_tab_FormTTNhanVien.setLayout(pnl_tab_FormTTNhanVienLayout);
        pnl_tab_FormTTNhanVienLayout.setHorizontalGroup(
            pnl_tab_FormTTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_tab_FormTTNhanVienLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 599, Short.MAX_VALUE))
        );
        pnl_tab_FormTTNhanVienLayout.setVerticalGroup(
            pnl_tab_FormTTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_tab_FormTTNhanVienLayout.createSequentialGroup()
                .addGap(0, 598, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        pnl_FormNhanVien.add(pnl_tab_FormTTNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1300, 620));

        pnl_tab_FormCaLam.setBackground(new java.awt.Color(204, 204, 204));

        tbl_CaLam.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tbl_CaLam.setForeground(new java.awt.Color(0, 0, 0));
        tbl_CaLam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Ca Làm", "Mã Nhân Viên", "Tên Nhân Viên", "Buổi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_CaLam.setRowHeight(35);
        tbl_CaLam.setSelectionBackground(new java.awt.Color(102, 153, 255));
        tbl_CaLam.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbl_CaLam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_CaLamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_CaLam);
        if (tbl_CaLam.getColumnModel().getColumnCount() > 0) {
            tbl_CaLam.getColumnModel().getColumn(3).setMaxWidth(100);
            tbl_CaLam.getColumnModel().getColumn(3).setHeaderValue("Buổi");
        }

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 153, 255), null), "Tác Vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        btn_ThemCa.setBackground(new java.awt.Color(21, 151, 229));
        btn_ThemCa.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_ThemCa.setForeground(new java.awt.Color(255, 255, 255));
        btn_ThemCa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgVSicon/plus (2).png"))); // NOI18N
        btn_ThemCa.setText("Thêm ");
        btn_ThemCa.setBorder(null);
        btn_ThemCa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ThemCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ThemCaMouseClicked(evt);
            }
        });

        btn_LuuCa.setBackground(new java.awt.Color(21, 151, 229));
        btn_LuuCa.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_LuuCa.setForeground(new java.awt.Color(255, 255, 255));
        btn_LuuCa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgVSicon/download.png"))); // NOI18N
        btn_LuuCa.setText("Lưu ");
        btn_LuuCa.setBorder(null);
        btn_LuuCa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_LuuCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_LuuCaMouseClicked(evt);
            }
        });
        btn_LuuCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuCaActionPerformed(evt);
            }
        });

        btn_SuaCa.setBackground(new java.awt.Color(21, 151, 229));
        btn_SuaCa.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_SuaCa.setForeground(new java.awt.Color(255, 255, 255));
        btn_SuaCa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgVSicon/repair-tool (1).png"))); // NOI18N
        btn_SuaCa.setText("Sửa");
        btn_SuaCa.setBorder(null);
        btn_SuaCa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_SuaCa.setEnabled(false);
        btn_SuaCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_SuaCaMouseClicked(evt);
            }
        });

        btn_XoaCa.setBackground(new java.awt.Color(21, 151, 229));
        btn_XoaCa.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_XoaCa.setForeground(new java.awt.Color(255, 255, 255));
        btn_XoaCa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgVSicon/delete (1).png"))); // NOI18N
        btn_XoaCa.setText("Xóa");
        btn_XoaCa.setBorder(null);
        btn_XoaCa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_XoaCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_XoaCaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_ThemCa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LuuCa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(122, 122, 122)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_XoaCa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SuaCa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThemCa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SuaCa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_LuuCa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XoaCa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        txt_MaCa.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_MaCa.setForeground(new java.awt.Color(0, 0, 0));
        txt_MaCa.setToolTipText("");

        lbl_MaCa.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_MaCa.setForeground(new java.awt.Color(0, 0, 0));
        lbl_MaCa.setText("Mã Ca Làm");

        lbl_MauSac1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_MauSac1.setForeground(new java.awt.Color(0, 0, 0));
        lbl_MauSac1.setText("Mã Nhân Viên");

        cbo_Buoi.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbo_Buoi.setForeground(new java.awt.Color(0, 0, 0));
        cbo_Buoi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sáng", "Chiều", "Tối" }));

        lbl_DanhMuc.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_DanhMuc.setForeground(new java.awt.Color(0, 0, 0));
        lbl_DanhMuc.setText("Buổi");

        cbo_MaNv.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbo_MaNv.setForeground(new java.awt.Color(0, 0, 0));

        tbl_NV_2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tbl_NV_2.setForeground(new java.awt.Color(0, 0, 0));
        tbl_NV_2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên", "Tên Chức Vụ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_NV_2.setRowHeight(35);
        tbl_NV_2.setSelectionBackground(new java.awt.Color(102, 153, 255));
        tbl_NV_2.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbl_NV_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_NV_2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_NV_2);

        javax.swing.GroupLayout pnl_tab_FormCaLamLayout = new javax.swing.GroupLayout(pnl_tab_FormCaLam);
        pnl_tab_FormCaLam.setLayout(pnl_tab_FormCaLamLayout);
        pnl_tab_FormCaLamLayout.setHorizontalGroup(
            pnl_tab_FormCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnl_tab_FormCaLamLayout.createSequentialGroup()
                .addGroup(pnl_tab_FormCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_tab_FormCaLamLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(pnl_tab_FormCaLamLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(pnl_tab_FormCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_tab_FormCaLamLayout.createSequentialGroup()
                                .addGroup(pnl_tab_FormCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_DanhMuc)
                                    .addComponent(lbl_MauSac1))
                                .addGap(34, 34, 34)
                                .addGroup(pnl_tab_FormCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbo_Buoi, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbo_MaNv, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnl_tab_FormCaLamLayout.createSequentialGroup()
                                .addComponent(lbl_MaCa)
                                .addGap(56, 56, 56)
                                .addComponent(txt_MaCa, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)))
                .addGroup(pnl_tab_FormCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)))
        );
        pnl_tab_FormCaLamLayout.setVerticalGroup(
            pnl_tab_FormCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_tab_FormCaLamLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(pnl_tab_FormCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_tab_FormCaLamLayout.createSequentialGroup()
                        .addGroup(pnl_tab_FormCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_MaCa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_MaCa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(pnl_tab_FormCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_MauSac1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_MaNv, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(pnl_tab_FormCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_tab_FormCaLamLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lbl_DanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbo_Buoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)))
                .addGap(13, 13, 13)
                .addGroup(pnl_tab_FormCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnl_FormNhanVien.add(pnl_tab_FormCaLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1090, 620));

        pnl_tab_FormLuong.setBackground(new java.awt.Color(243, 244, 237));

        tbl_Luong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên", "Chức Vụ", "Hệ Số Lương", "Số Ca", "Lương"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_Luong.setRowHeight(35);
        tbl_Luong.setSelectionBackground(new java.awt.Color(102, 153, 255));
        tbl_Luong.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbl_Luong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_LuongMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_Luong);

        cbo_TkCv_Luong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cbo_TkCv_Luong.setForeground(new java.awt.Color(0, 0, 0));
        cbo_TkCv_Luong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả" }));
        cbo_TkCv_Luong.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_TkCv_LuongItemStateChanged(evt);
            }
        });
        cbo_TkCv_Luong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbo_TkCv_LuongMouseClicked(evt);
            }
        });
        cbo_TkCv_Luong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_TkCv_LuongActionPerformed(evt);
            }
        });

        txt_TkNV_Luong.setBackground(java.awt.Color.white);
        txt_TkNV_Luong.setText("Nhập Tên Nhân Viên");
        txt_TkNV_Luong.setToolTipText("");
        txt_TkNV_Luong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_TkNV_LuongFocusGained(evt);
            }
        });

        btn_TinhLuong.setBackground(new java.awt.Color(21, 151, 229));
        btn_TinhLuong.setForeground(java.awt.Color.white);
        btn_TinhLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgVSicon/salary.png"))); // NOI18N
        btn_TinhLuong.setText("Tính Lương");
        btn_TinhLuong.setToolTipText("");
        btn_TinhLuong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_TinhLuong.setEnabled(false);
        btn_TinhLuong.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                btn_TinhLuongMouseDragged(evt);
            }
        });
        btn_TinhLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_TinhLuongMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_TinhLuongMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_TinhLuongMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_TinhLuongMousePressed(evt);
            }
        });
        btn_TinhLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TinhLuongActionPerformed(evt);
            }
        });

        btn_CapNhat_Luong.setBackground(new java.awt.Color(21, 151, 229));
        btn_CapNhat_Luong.setForeground(java.awt.Color.white);
        btn_CapNhat_Luong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgVSicon/system-update.png"))); // NOI18N
        btn_CapNhat_Luong.setText("Cập nhật");
        btn_CapNhat_Luong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_CapNhat_Luong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_CapNhat_LuongMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnl_tab_FormLuongLayout = new javax.swing.GroupLayout(pnl_tab_FormLuong);
        pnl_tab_FormLuong.setLayout(pnl_tab_FormLuongLayout);
        pnl_tab_FormLuongLayout.setHorizontalGroup(
            pnl_tab_FormLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(pnl_tab_FormLuongLayout.createSequentialGroup()
                .addComponent(txt_TkNV_Luong, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_TkCv_Luong, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(btn_CapNhat_Luong, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_TinhLuong)
                .addContainerGap())
        );
        pnl_tab_FormLuongLayout.setVerticalGroup(
            pnl_tab_FormLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_tab_FormLuongLayout.createSequentialGroup()
                .addGap(0, 44, Short.MAX_VALUE)
                .addGroup(pnl_tab_FormLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_tab_FormLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_TinhLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_CapNhat_Luong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_tab_FormLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbo_TkCv_Luong, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txt_TkNV_Luong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnl_FormNhanVien.add(pnl_tab_FormLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1090, 620));

        pnl_tab_FormTKNV.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout pnl_tab_FormTKNVLayout = new javax.swing.GroupLayout(pnl_tab_FormTKNV);
        pnl_tab_FormTKNV.setLayout(pnl_tab_FormTKNVLayout);
        pnl_tab_FormTKNVLayout.setHorizontalGroup(
            pnl_tab_FormTKNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        pnl_tab_FormTKNVLayout.setVerticalGroup(
            pnl_tab_FormTKNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        pnl_FormNhanVien.add(pnl_tab_FormTKNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1300, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1090, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnl_FormNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnl_FormNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tab_NVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tab_NVMouseClicked
        // TODO add your handling code here:
        pnl_tab_FormTTNhanVien.setVisible(true);
        pnl_tab_FormCaLam.setVisible(false);
        pnl_tab_FormLuong.setVisible(false);
        pnl_tab_FormTKNV.setVisible(false);
    }//GEN-LAST:event_btn_tab_NVMouseClicked

    private void btn_tab_NVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tab_NVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tab_NVActionPerformed

    private void btn_tab_CaLamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tab_CaLamMouseClicked
        // TODO add your handling code here:
        pnl_tab_FormTTNhanVien.setVisible(false);
        pnl_tab_FormCaLam.setVisible(true);
        pnl_tab_FormLuong.setVisible(false);
        pnl_tab_FormTKNV.setVisible(false);
    }//GEN-LAST:event_btn_tab_CaLamMouseClicked

    private void btn_tab_LuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tab_LuongMouseClicked
        // TODO add your handling code here:
        pnl_tab_FormTTNhanVien.setVisible(false);
        pnl_tab_FormCaLam.setVisible(false);
        pnl_tab_FormLuong.setVisible(true);
        pnl_tab_FormTKNV.setVisible(false);
    }//GEN-LAST:event_btn_tab_LuongMouseClicked

    private void btn_tab_TkNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tab_TkNVMouseClicked
        pnl_tab_FormTKNV.setVisible(true);
        pnl_tab_FormTTNhanVien.setVisible(false);
        pnl_tab_FormCaLam.setVisible(false);
        pnl_tab_FormLuong.setVisible(false);
    }//GEN-LAST:event_btn_tab_TkNVMouseClicked

    private void btn_tab_TkNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tab_TkNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tab_TkNVActionPerformed

    private void btn_ThemCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemCaMouseClicked
        xoaRongTextCa();
//        btn_SuaAnh.setEnabled(true);
    }//GEN-LAST:event_btn_ThemCaMouseClicked

    private void btn_LuuCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LuuCaMouseClicked
        CaLam ca = restText();
        if(ca_dao.themCa(ca)){
            dfCL_Model.addRow(new Object[]{
                ca.getMaCa(),ca.getNV().getMaNV(),ca.getNV().getTenNV(),ca.getBuoi()
            });
            dsCa.removeAll(dsCa);
            xoaRongTextCa();
            xoaModelCa();
            upTblCaLam();
            xoaModelLuong();
            upTblLuong();
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }else{
            JOptionPane.showMessageDialog(null, " đã có vui lòng nhập lại ");
        }
        
    }//GEN-LAST:event_btn_LuuCaMouseClicked

    private void btn_LuuCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuCaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_LuuCaActionPerformed

    private void btn_SuaCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SuaCaMouseClicked
//        int r = tbl_Sp.getSelectedRow();
//        btn_SuaAnh.setEnabled(true);
//        if (r != -1) {
//            btn_SuaAnh.setVisible(true);
//            String maSP = dfSP_Model.getValueAt(r, 0).toString();
//            System.out.println(maSP);
//            String tenSP = txt_TenSp.getText().trim();
//            String tenDm = cbo_Dm.getSelectedItem().toString();
//            String mau = txt_MauSac.getText().trim();
//            String hinh = lbl_HinhAnh.getIcon().toString();
//            //System.out.println(hinh);
//            String size = txt_Size.getText().trim();
//            int sl = Integer.parseInt(txt_SlKho.getText().trim());
//            double dg = Double.parseDouble(txt_DonGia.getText().trim());
//            DanhMucSP dm = dm_dao.getDMByTen(tenDm);
//            SanPham sp = new SanPham(dm, maSP, tenSP, dg, sl, hinh, size, mau);
//            //System.out.println(sp.toString());
//            if (sp_dao.updateSP(maSP, sp)) {
//                xoaRongTextSp();
//                dfSP_Model.setRowCount(0);
//                dsSP = sp_dao.getAllSP();
//                for (SanPham it : dsSP) {
//                    dfSP_Model.addRow(new Object[]{
//                        it.getMaSP(), it.getTenSP(), it.getDmsp().getTenLoai(),
//                        it.getMauSac(), it.getSize(), it.getSoLuong(),
//                        it.getDonGia(), it.getHinhAnh()
//                    });
//                }
//                dsSP.removeAll(dsSP);
//                xoaRongTextSp();
//                xoaModelSP();
//                upTblSP();
//                xoaModelTksp();
//                upTblTkSP();
//                upCbo_DM();
//                JOptionPane.showMessageDialog(this, "Cập nhật danh sách thành công");
//            }
//
//        } else {
//            JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng nào!");
//        }
    }//GEN-LAST:event_btn_SuaCaMouseClicked

    private void btn_XoaCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XoaCaMouseClicked
        int r = tbl_CaLam.getSelectedRow();
        String id = dfCL_Model.getValueAt(r, 0).toString();
        if (r != -1) {
            int tb = JOptionPane.showConfirmDialog(null, "Bạn  chắc chắn muốn xóa dòng này không?", "Detele", JOptionPane.YES_NO_OPTION);
            if (tb == JOptionPane.YES_OPTION) {
                dfCL_Model.removeRow(r);
                ca_dao.xoaCa(id);
                dsCa.removeAll(dsCa);
                xoaRongTextCa();
                xoaModelCa();
                upTblCaLam();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần xóa!");
        }
        
    }//GEN-LAST:event_btn_XoaCaMouseClicked

    private void tbl_CaLamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_CaLamMouseClicked
        int r = tbl_CaLam.getSelectedRow();
        btn_SuaCa.setEnabled(true);
        txt_MaCa.setText(dfCL_Model.getValueAt(r, 0).toString());
        cbo_MaNv.setSelectedItem(dfCL_Model.getValueAt(r, 1).toString());
        cbo_Buoi.setSelectedItem(dfCL_Model.getValueAt(r, 3));
        
    }//GEN-LAST:event_tbl_CaLamMouseClicked

    private void tbl_NV_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NV_2MouseClicked
        int r = tbl_NV_2.getSelectedRow();
        cbo_MaNv.setSelectedItem(dfNV2_Model.getValueAt(r, 0).toString());
    }//GEN-LAST:event_tbl_NV_2MouseClicked

    private void txt_TkNV_LuongFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_TkNV_LuongFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TkNV_LuongFocusGained

    private void btn_TinhLuongMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TinhLuongMouseDragged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_btn_TinhLuongMouseDragged

    private void btn_TinhLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TinhLuongMouseClicked
       int r = tbl_Luong.getSelectedRow();
        String id = dfLuong_Model.getValueAt(r, 0).toString();
        if (r != -1) {
                dfLuong_Model.removeRow(r);
                l_dao.tinhLuong(id);
                  dsCa.removeAll(dsCa);
                  xoaModelCa();
                  upTblCaLam();
//                xoaModelCa();
//                upTblCaLam();
//                dsCa.removeAll(dsCa);
//                xoaRongTextCa();
//                xoaModelCa();
//                upTblCaLam();
                JOptionPane.showMessageDialog(null, "Nhân viên "+id+" đã nhận lương");
        } else {
            //JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần xóa!");
        }
    }//GEN-LAST:event_btn_TinhLuongMouseClicked

    private void btn_TinhLuongMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TinhLuongMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_TinhLuongMouseEntered

    private void btn_TinhLuongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TinhLuongMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_btn_TinhLuongMouseExited

    private void btn_TinhLuongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TinhLuongMousePressed
        // TODO add your handling code here:

       
    }//GEN-LAST:event_btn_TinhLuongMousePressed

    private void btn_TinhLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TinhLuongActionPerformed
        
    }//GEN-LAST:event_btn_TinhLuongActionPerformed

    private void cbo_TkCv_LuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_TkCv_LuongActionPerformed
        
    }//GEN-LAST:event_cbo_TkCv_LuongActionPerformed

    private void cbo_TkCv_LuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbo_TkCv_LuongMouseClicked
        
    }//GEN-LAST:event_cbo_TkCv_LuongMouseClicked

    private void cbo_TkCv_LuongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_TkCv_LuongItemStateChanged
         if (evt.getStateChange() == ItemEvent.SELECTED) {
             dfLuong_Model.setRowCount(0);
                  TKCV();
         }
    }//GEN-LAST:event_cbo_TkCv_LuongItemStateChanged

    private void btn_CapNhat_LuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CapNhat_LuongMouseClicked
        dsLuong.removeAll(dsLuong);
        xoaModelLuong();
        upTblLuong();
    }//GEN-LAST:event_btn_CapNhat_LuongMouseClicked

    private void tbl_LuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_LuongMouseClicked
        btn_TinhLuong.setEnabled(true);
    }//GEN-LAST:event_tbl_LuongMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_CapNhat_Luong;
    private javax.swing.JButton btn_LuuCa;
    private javax.swing.JButton btn_SuaCa;
    private javax.swing.JButton btn_ThemCa;
    private javax.swing.JButton btn_TinhLuong;
    private javax.swing.JButton btn_XoaCa;
    private javax.swing.JButton btn_tab_CaLam;
    private javax.swing.JButton btn_tab_Luong;
    private javax.swing.JButton btn_tab_NV;
    private javax.swing.JButton btn_tab_TkNV;
    private javax.swing.JComboBox<String> cbo_Buoi;
    private javax.swing.JComboBox<String> cbo_MaNv;
    private javax.swing.JComboBox<String> cbo_TkCv_Luong;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_DanhMuc;
    private javax.swing.JLabel lbl_MaCa;
    private javax.swing.JLabel lbl_MauSac1;
    private javax.swing.JLabel lbl_text_NhanVien;
    private javax.swing.JPanel pnl_FormNhanVien;
    private javax.swing.JPanel pnl_menuTab_NhanVien;
    private javax.swing.JPanel pnl_tab_FormCaLam;
    private javax.swing.JPanel pnl_tab_FormLuong;
    private javax.swing.JPanel pnl_tab_FormTKNV;
    private javax.swing.JPanel pnl_tab_FormTTNhanVien;
    private javax.swing.JTable tbl_CaLam;
    private javax.swing.JTable tbl_Luong;
    private javax.swing.JTable tbl_NV_2;
    private javax.swing.JTextField txt_MaCa;
    private javax.swing.JTextField txt_TkNV_Luong;
    // End of variables declaration//GEN-END:variables
}
