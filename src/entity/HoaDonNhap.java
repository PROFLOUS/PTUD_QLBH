/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author GMT
 * 
 */

//●       MaNV – varchar(30) – Foreign Key
//●       MaNCC – varchar(30) – Foreign Key
//●       MaHDNhap – varchar(30) – Primary Key
//●       NgayLapHD – date – not null
//●       SoLuongSP – interger – not null – Không âm ( >= 0)
//●       TongTien – double – not null - Không âm ( >= 0)
//●       GhiChu – varchar(255)

public class HoaDonNhap {
    
    private String maHDNhap;
    private Date ngayLapHD;
    private int soLuong;
    private Double tongTien;
    private String ghiChu;
    
    
    private String maNV;
    private String maNCC;

    public HoaDonNhap() {
    }

    public HoaDonNhap(String maHDNhap, Date ngayLapHD, int soLuong, Double tongTien, String ghiChu, String maNV, String maNCC) {
        this.maHDNhap = maHDNhap;
        this.ngayLapHD = ngayLapHD;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
        this.maNV = maNV;
        this.maNCC = maNCC;
    }

    public String getMaHDNhap() {
        return maHDNhap;
    }

    public void setMaHDNhap(String maHDNhap) {
        this.maHDNhap = maHDNhap;
    }

    public Date getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(Date ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }
    
    
    
    
}
