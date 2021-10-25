/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author GMT
 */
//●       MaHD – varchar(30) – PrimaryKey
//●       MaKH – varchar(30) - Foreign Key
//●       MaNV – varchar(30) - Foreign Key
//●       NgayLapHD – date - not null
//●       SoLuongSP – interger – not null - Không âm ( >= 0)
//●       TongTien – double – not null - Không âm ( >= 0)
//●       TienKhachDua – double – not null
//●       GhiChu – varchar(255)

public class HoaDonBanHang {
    private String maHD;
    private String maKH;
    private String maNV;
    
    private Date ngayLapHD;
    private int soLuong;
    private Double tongTien;
    private Double tienKhachDua;
    private String ghiChu;

    public HoaDonBanHang() {
    }

    public HoaDonBanHang(String maHD, String maKH, String maNV, Date ngayLapHD, int soLuong, Double tongTien, Double tienKhachDua, String ghiChu) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngayLapHD = ngayLapHD;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.tienKhachDua = tienKhachDua;
        this.ghiChu = ghiChu;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
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

    public Double getTienKhachDua() {
        return tienKhachDua;
    }

    public void setTienKhachDua(Double tienKhachDua) {
        this.tienKhachDua = tienKhachDua;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
    
}
