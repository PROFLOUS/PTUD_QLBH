/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author GMT
 */
public class SanPham {
    private DanhMucSP dmsp;
    
    private String maSP;  
    private String tenSP;
    private Double donGia;
    private int soLuong;
    private String hinhAnh;
    private String size;
    private String mauSac;

    public SanPham(){
        
    }

    public SanPham(String maSP, String tenSP, Double donGia, int soLuong, String hinhAnh, String size, String mauSac) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.size = size;
        this.mauSac = mauSac;
    }

    public SanPham(String maSP, String tenSP, Double donGia, String size, String mauSac) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.size = size;
        this.mauSac = mauSac;
    }

    public SanPham(String maSP, String tenSP, Double donGia, int soLuong, String size, String mauSac) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.size = size;
        this.mauSac = mauSac;
    }
    
    

    

    public DanhMucSP getDmsp() {
        return dmsp;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public Double getDonGia() {
        return donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public String getSize() {
        return size;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setDmsp(DanhMucSP dmsp) {
        this.dmsp = dmsp;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }
   
     
}
