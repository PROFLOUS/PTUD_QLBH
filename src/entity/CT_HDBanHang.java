/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author GMT
 */

//MaSP – varchar(30) – Foreign Key
//      MaHDBanHang – varchar(30) – Foreign Key
//      SoLuong – interger – not null - Không âm ( >= 0)
// 
//       DonGia – double – not null - Không âm ( >= 0)

public class CT_HDBanHang {
   
    private String maHDBanHang;
    private String maSP;
    private int soLuong;
    private Double donGia;

    public CT_HDBanHang() {
    }

    public CT_HDBanHang(String maHDBanHang, String maSP, int soLuong, Double donGia) {
        this.maHDBanHang = maHDBanHang;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMaHDBanHang() {
        return maHDBanHang;
    }

    public void setMaHDBanHang(String maHDBanHang) {
        this.maHDBanHang = maHDBanHang;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    
}
