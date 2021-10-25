/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author GMT
 */

//●       MaSP – varchar(30) – Foreign Key
//●       MaHD – varchar(30) – Foreign Key
//●       SoLuong – interger – not null - Không âm ( >= 0)
//●       DonGia – double – not null - Không âm ( >= 0)


public class CT_HoaDonNhap {
    private String maSP;
    private String maHD;
    private int soLuong;
    private Double donGia;

    public CT_HoaDonNhap() {
    }

    public CT_HoaDonNhap(String maSP, String maHD, int soLuong, Double donGia) {
        this.maSP = maSP;
        this.maHD = maHD;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
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
