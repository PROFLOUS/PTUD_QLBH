/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Gui;

import Connect.connect;
import dao.HoaDonDao;
import entity.HoaDonBanHang;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GMT
 */
public class FrmHoaDon extends javax.swing.JPanel{
    ArrayList<HoaDonBanHang> listHoaDon = new ArrayList<HoaDonBanHang>();
       private javax.swing.table.DefaultTableModel modelTBHoaDon;
    /**
     * Creates new form FrmHoaDon
     */
    public FrmHoaDon() throws SQLException {
        initComponents();
        try {
             connect.getInstance().connect();
        
            renderListHoaDon();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
        Hoa Don
    */
    //dua du lieu HoaDon len Table
	private void renderListHoaDon() {
		 HoaDonDao hoaDonDao = new HoaDonDao();
                 String[] title = { "MaHD", "Ngày Tạo", "Khách Hàng", "Số lượng", "Tổng Tiền", "Tiền Khách Đưa", "Nhân Viên", "Ghi Chú"};
		 listHoaDon  = hoaDonDao.getDsHoaDon();
                modelTBHoaDon = new DefaultTableModel(title,0);
		for(HoaDonBanHang s : listHoaDon) {
			String[] rowData = {
				s.getMaHD(),changeDateToString(s.getNgayLapHD()),s.getKhachHang().getTenKH(),String.valueOf(s.getSoLuong()),String.valueOf(s.getTongTien()),
                            String.valueOf(s.getTienKhachDua()),s.getNhanVien().getTenNV(),s.getGhiChu()
			};
                       
			modelTBHoaDon.addRow(rowData);
		}
               
		tbHoaDon.setModel(modelTBHoaDon);
	}
         /*
            Chuyen tu date sang String
            @param date Date
            return String
        */
         public String changeDateToString(Date date){
            
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                String dateString  = df.format(date);
                return dateString;
          }
         
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        txtSearchHD = new javax.swing.JTextField();
        btnShowHD = new javax.swing.JButton();
        btnSearchHD = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();

        setBackground(new java.awt.Color(243, 244, 237));

        jPanel1.setBackground(java.awt.Color.white);

        txtSearchHD.setBackground(java.awt.Color.white);
        txtSearchHD.setText("Nhập mã hóa đơn...");
        txtSearchHD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchHDFocusGained(evt);
            }
        });

        btnShowHD.setBackground(new java.awt.Color(21, 151, 229));
        btnShowHD.setForeground(java.awt.Color.white);
        btnShowHD.setText("Hiển thị Hóa Đơn");
        btnShowHD.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnShowHD.setEnabled(false);
        btnShowHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnShowHDMouseClicked(evt);
            }
        });

        btnSearchHD.setBackground(new java.awt.Color(21, 151, 229));
        btnSearchHD.setForeground(java.awt.Color.white);
        btnSearchHD.setText("Tìm Kiếm");
        btnSearchHD.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearchHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchHDMouseClicked(evt);
            }
        });
        btnSearchHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSearchHD, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchHD, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnShowHD, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(416, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnShowHD, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearchHD, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnSearchHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tbHoaDon.setBackground(java.awt.Color.white);
        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbHoaDon.setRowHeight(28);
        tbHoaDon.setShowGrid(true);
        tbHoaDon.setUpdateSelectionOnSort(false);
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHoaDon);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchHDActionPerformed

    private void txtSearchHDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchHDFocusGained
            txtSearchHD.setText("");
    }//GEN-LAST:event_txtSearchHDFocusGained

    
    //tim kiem theo MaHoaDon
    private void btnSearchHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchHDMouseClicked
       String text = txtSearchHD.getText();
        AtomicBoolean check = new AtomicBoolean();
       
        listHoaDon.forEach(s ->{
            if(s.getMaHD().toUpperCase().equals(text.toUpperCase())){
                check.set(true);
                 String[] title = { "MaHD", "Ngày Tạo", "Khách Hàng", "Số lượng", "Tổng Tiền", "Tiền Khách Đưa", "Nhân Viên", "Ghi Chú"};
		 modelTBHoaDon = new DefaultTableModel(title,0);
                 String[] rowData = {
				s.getMaHD(),changeDateToString(s.getNgayLapHD()),s.getKhachHang().getTenKH(),String.valueOf(s.getSoLuong()),String.valueOf(s.getTongTien()),
                            String.valueOf(s.getTienKhachDua()),s.getNhanVien().getTenNV(),s.getGhiChu()
			};
             modelTBHoaDon.addRow(rowData);
                tbHoaDon.setModel(modelTBHoaDon);
                btnShowHD.setEnabled(true);
            }
        });
        if(!check.get()){
           JOptionPane.showMessageDialog(btnSearchHD, "Không có Hóa Đơn có mã:" + text + " " );
        }
    }//GEN-LAST:event_btnSearchHDMouseClicked

    
    //click vao Hien thi lai ds HoaDon
    private void btnShowHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnShowHDMouseClicked
        btnShowHD.setEnabled(false);
        renderListHoaDon();
    }//GEN-LAST:event_btnShowHDMouseClicked

    private void jDateChoiseHDInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jDateChoiseHDInputMethodTextChanged
        System.out.println(evt.getText());
    }//GEN-LAST:event_jDateChoiseHDInputMethodTextChanged

    private void jDateChoiseHDCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jDateChoiseHDCaretPositionChanged
        // TODO add your handling code here:
         System.out.println(evt.getText());
    }//GEN-LAST:event_jDateChoiseHDCaretPositionChanged

    private void jDateChoiseHDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDateChoiseHDFocusLost
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jDateChoiseHDFocusLost

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        // lay ra row vua click
        int row = tbHoaDon.getSelectedRow();
       
        //lay ra MaHD
        String maHD = tbHoaDon.getValueAt(row, 0).toString();
        //JOptionPane.showMessageDialog(jPanel1, maHD);
        new FrmCT_HoaDon(maHD).setVisible(true);
    }//GEN-LAST:event_tbHoaDonMouseClicked


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearchHD;
    private javax.swing.JButton btnShowHD;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTextField txtSearchHD;
    // End of variables declaration//GEN-END:variables

    
}