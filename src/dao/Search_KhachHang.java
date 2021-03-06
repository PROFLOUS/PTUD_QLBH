/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.KhachHang;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAVEN
 */
public class Search_KhachHang extends javax.swing.JPanel {
    private DefaultTableModel dfsp_model;
  
    public Search_KhachHang(KhachHang data) {
        initComponents();
        setData(data);
    }

    private void setData(KhachHang data) {
        addEventMouse(this);
        addEventMouse(lbl_KH);
//        addEventMouse(lbRemove);
        lbl_KH.setText(data.getTenKH());
        lbl_SDT.setText(data.getSdt());
        lbl_MaKh.setText(data.getMaKH());
        
        
    }

    private void addEventMouse(Component com) {
        com.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                //hover
                setBackground(new Color(215, 216, 216));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(Color.WHITE);
            }

        });
    }

    private ActionListener eventClick;
    

    public void addEvent(ActionListener eventClick) {
        this.eventClick = eventClick;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_KH = new javax.swing.JLabel();
        lbl_SDT = new javax.swing.JLabel();
        lbl_MaKh = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 153, 255)));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        lbl_KH.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lbl_KH.setForeground(new java.awt.Color(0, 0, 0));
        lbl_KH.setText("Text ...");
        lbl_KH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_KHMouseClicked(evt);
            }
        });

        lbl_SDT.setFont(new java.awt.Font("Consolas", 2, 18)); // NOI18N
        lbl_SDT.setForeground(new java.awt.Color(102, 153, 255));
        lbl_SDT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_SDT.setText("02152021");

        lbl_MaKh.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbl_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_MaKh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbl_KH, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_KH)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_SDT)
                    .addComponent(lbl_MaKh, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_KHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_KHMouseClicked
//         System.out.println("ok");
    }//GEN-LAST:event_lbl_KHMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
//        System.out.println("click");
        eventClick.actionPerformed(null);
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbl_KH;
    private javax.swing.JLabel lbl_MaKh;
    private javax.swing.JLabel lbl_SDT;
    // End of variables declaration//GEN-END:variables
}
