/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Connect.connect;
import entity.HoaDonBanHang;
import entity.SanPham;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author GMT
 */
public class SanPhamDao {
    private ArrayList<SanPham> listSP;
    private SanPham sanPham;

    public SanPhamDao() {
        listSP = new ArrayList<SanPham>();
        sanPham = new SanPham();
    }
    
    
    //tim san pham theo ma SP
    //còn danh muc san pham chua lay ra
    
    /*
        @param maSP String
        return sanPham SanPham
    */
     public SanPham findSPByMaSP(String maSP){
            SanPham sp = null;
            try {
                 
                java.sql.Connection con = connect.getInstance().getConnection();
                String sql = "select * from SanPham where MaSP = '"+maSP+"' ";
                Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maSanPham = rs.getString(1);
                                String tenSP = rs.getString(2);
                                int soLuong = rs.getInt(3);
                                Double donGia = rs.getDouble(4);
                                String hinhAnh = rs.getString(5);
                                String size = rs.getString(6);
                                String mauSac = rs.getString(7);
                                String maLoai = rs.getString(8);
                                
                                sp= new SanPham(maSP, tenSP, donGia, soLuong, hinhAnh, size, mauSac);
				
                               

                                
			}
            } catch (Exception e) {
                e.printStackTrace();
            }
               
               return sp;
             
            
            
        }
}
