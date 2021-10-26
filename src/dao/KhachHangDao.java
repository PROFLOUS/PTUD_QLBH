/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Connect.connect;
import entity.KhachHang;
import entity.NhanVien;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author GMT
 */
public class KhachHangDao {
     private ArrayList<KhachHang> listKH;
    private KhachHang khachHang;
    
     public KhachHangDao() {
       listKH = new ArrayList<KhachHang>();
       khachHang = new KhachHang();
    }
     
     
     //tim kiesm 1 khach hàng theo maKH
      public KhachHang getKHByMaKH(String maKH){
         KhachHang kh = null;
            try {
                 
                java.sql.Connection con = connect.getInstance().getConnection();
                String sql = "select * from KhachHang where MaKH = '"+maKH+"' ";
                Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maKhachHang = rs.getString(1);
                                String tenKH = rs.getString(2);
                                String sdt = rs.getString(3);
                                
                                kh = new KhachHang(maKH, tenKH, sdt);
                                
			}
            } catch (Exception e) {
                e.printStackTrace();
            }
               
               return kh;
             
        
        
     
    } 
}
