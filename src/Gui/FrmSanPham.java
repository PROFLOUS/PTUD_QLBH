/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author HP
 */
public class FrmSanPham extends javax.swing.JPanel {
    public  Border default_border = BorderFactory.createMatteBorder(0, 0   , 3, 0, new Color(153,153,153));
   public Border active_border = BorderFactory.createMatteBorder(0, 0   , 3, 0, new Color(153,204,255));
   public JButton [] buttons;

    /**
     * Creates new form FrmSanPham
     */
    public FrmSanPham() {
        initComponents();
        addBorder();
    }

    public void addBorder(){
        buttons = new JButton[3];
        
        //Form san pham
        buttons[0] = btn_tab_SanPham;
        buttons[1] = btn_tab_DMSanPham;
        buttons[2] = btn_tab_NhaCungCap;
   
        setButtonBorder(btn_tab_SanPham);
        

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

        pnl_FormSanPham = new javax.swing.JPanel();
        lbl_text_SanPham = new javax.swing.JLabel();
        pnl_menuTab_SanPham = new javax.swing.JPanel();
        btn_tab_SanPham = new javax.swing.JButton();
        btn_tab_DMSanPham = new javax.swing.JButton();
        btn_tab_NhaCungCap = new javax.swing.JButton();
        pnl_tab_FormSanPham = new javax.swing.JPanel();
        pnl_tab_FormDMSanPham = new javax.swing.JPanel();
        pnl_tab_FormTKSP = new javax.swing.JPanel();

        pnl_FormSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pnl_FormSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_text_SanPham.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        lbl_text_SanPham.setForeground(new java.awt.Color(0, 153, 204));
        lbl_text_SanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_text_SanPham.setText("Sản Phẩm");
        pnl_FormSanPham.add(lbl_text_SanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 138, 30));

        pnl_menuTab_SanPham.setBackground(new java.awt.Color(255, 255, 255));

        btn_tab_SanPham.setBackground(new java.awt.Color(255, 255, 255));
        btn_tab_SanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btn_tab_SanPham.setForeground(new java.awt.Color(0, 0, 0));
        btn_tab_SanPham.setText("Sản Phẩm");
        btn_tab_SanPham.setBorder(null);
        btn_tab_SanPham.setContentAreaFilled(false);
        btn_tab_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tab_SanPhamMouseClicked(evt);
            }
        });
        btn_tab_SanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tab_SanPhamActionPerformed(evt);
            }
        });

        btn_tab_DMSanPham.setBackground(new java.awt.Color(255, 255, 255));
        btn_tab_DMSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btn_tab_DMSanPham.setForeground(new java.awt.Color(153, 153, 153));
        btn_tab_DMSanPham.setText("Danh Mục Sản Phẩm");
        btn_tab_DMSanPham.setBorder(null);
        btn_tab_DMSanPham.setContentAreaFilled(false);
        btn_tab_DMSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tab_DMSanPhamMouseClicked(evt);
            }
        });
        btn_tab_DMSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tab_DMSanPhamActionPerformed(evt);
            }
        });

        btn_tab_NhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        btn_tab_NhaCungCap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btn_tab_NhaCungCap.setForeground(new java.awt.Color(153, 153, 153));
        btn_tab_NhaCungCap.setText("Tìm Kiếm Sản Phẩm");
        btn_tab_NhaCungCap.setBorder(null);
        btn_tab_NhaCungCap.setContentAreaFilled(false);
        btn_tab_NhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tab_NhaCungCapMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnl_menuTab_SanPhamLayout = new javax.swing.GroupLayout(pnl_menuTab_SanPham);
        pnl_menuTab_SanPham.setLayout(pnl_menuTab_SanPhamLayout);
        pnl_menuTab_SanPhamLayout.setHorizontalGroup(
            pnl_menuTab_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_menuTab_SanPhamLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btn_tab_SanPham)
                .addGap(44, 44, 44)
                .addComponent(btn_tab_DMSanPham)
                .addGap(45, 45, 45)
                .addComponent(btn_tab_NhaCungCap)
                .addContainerGap(588, Short.MAX_VALUE))
        );
        pnl_menuTab_SanPhamLayout.setVerticalGroup(
            pnl_menuTab_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_menuTab_SanPhamLayout.createSequentialGroup()
                .addGroup(pnl_menuTab_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_tab_SanPham, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(btn_tab_DMSanPham, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_tab_NhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnl_FormSanPham.add(pnl_menuTab_SanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1090, 50));

        pnl_tab_FormSanPham.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout pnl_tab_FormSanPhamLayout = new javax.swing.GroupLayout(pnl_tab_FormSanPham);
        pnl_tab_FormSanPham.setLayout(pnl_tab_FormSanPhamLayout);
        pnl_tab_FormSanPhamLayout.setHorizontalGroup(
            pnl_tab_FormSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1090, Short.MAX_VALUE)
        );
        pnl_tab_FormSanPhamLayout.setVerticalGroup(
            pnl_tab_FormSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        pnl_FormSanPham.add(pnl_tab_FormSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1090, 620));

        pnl_tab_FormDMSanPham.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout pnl_tab_FormDMSanPhamLayout = new javax.swing.GroupLayout(pnl_tab_FormDMSanPham);
        pnl_tab_FormDMSanPham.setLayout(pnl_tab_FormDMSanPhamLayout);
        pnl_tab_FormDMSanPhamLayout.setHorizontalGroup(
            pnl_tab_FormDMSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        pnl_tab_FormDMSanPhamLayout.setVerticalGroup(
            pnl_tab_FormDMSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        pnl_FormSanPham.add(pnl_tab_FormDMSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1300, 620));

        pnl_tab_FormTKSP.setBackground(new java.awt.Color(153, 153, 0));

        javax.swing.GroupLayout pnl_tab_FormTKSPLayout = new javax.swing.GroupLayout(pnl_tab_FormTKSP);
        pnl_tab_FormTKSP.setLayout(pnl_tab_FormTKSPLayout);
        pnl_tab_FormTKSPLayout.setHorizontalGroup(
            pnl_tab_FormTKSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        pnl_tab_FormTKSPLayout.setVerticalGroup(
            pnl_tab_FormTKSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        pnl_FormSanPham.add(pnl_tab_FormTKSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1300, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1090, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnl_FormSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnl_FormSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tab_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tab_SanPhamMouseClicked
        // TODO add your handling code here:
        pnl_tab_FormSanPham.setVisible(true);
        pnl_tab_FormDMSanPham.setVisible(false);
        pnl_tab_FormTKSP.setVisible(false);
    }//GEN-LAST:event_btn_tab_SanPhamMouseClicked

    private void btn_tab_SanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tab_SanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tab_SanPhamActionPerformed

    private void btn_tab_DMSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tab_DMSanPhamMouseClicked
        // TODO add your handling code here:
        pnl_tab_FormSanPham.setVisible(false);
        pnl_tab_FormDMSanPham.setVisible(true);
        pnl_tab_FormTKSP.setVisible(false);
    }//GEN-LAST:event_btn_tab_DMSanPhamMouseClicked

    private void btn_tab_DMSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tab_DMSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tab_DMSanPhamActionPerformed

    private void btn_tab_NhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tab_NhaCungCapMouseClicked
        // TODO add your handling code here:
        pnl_tab_FormSanPham.setVisible(false);
        pnl_tab_FormDMSanPham.setVisible(false);
        pnl_tab_FormTKSP.setVisible(true);
    }//GEN-LAST:event_btn_tab_NhaCungCapMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_tab_DMSanPham;
    private javax.swing.JButton btn_tab_NhaCungCap;
    private javax.swing.JButton btn_tab_SanPham;
    private javax.swing.JLabel lbl_text_SanPham;
    private javax.swing.JPanel pnl_FormSanPham;
    private javax.swing.JPanel pnl_menuTab_SanPham;
    private javax.swing.JPanel pnl_tab_FormDMSanPham;
    private javax.swing.JPanel pnl_tab_FormSanPham;
    private javax.swing.JPanel pnl_tab_FormTKSP;
    // End of variables declaration//GEN-END:variables
}
