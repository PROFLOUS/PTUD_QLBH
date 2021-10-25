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

//●       MaCa – varchar(30) – Primary Key
//●       MaNV – varchar(30) - Foreign Key
//●       NgayLam – date
//●       Buoi – varchar(25)
//      (ca sáng : 8h - 3h - ca chiều( 2h - 9h)

public class CaLam {
    private String maCa;
    private String maNV;
    private Date ngayLam;
    private String buoi;

    public CaLam() {
    }

    public CaLam(String maCa, String maNV, Date ngayLam, String buoi) {
        this.maCa = maCa;
        this.maNV = maNV;
        this.ngayLam = ngayLam;
        this.buoi = buoi;
    }

    public String getMaCa() {
        return maCa;
    }

    public void setMaCa(String maCa) {
        this.maCa = maCa;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Date getNgayLam() {
        return ngayLam;
    }

    public void setNgayLam(Date ngayLam) {
        this.ngayLam = ngayLam;
    }

    public String getBuoi() {
        return buoi;
    }

    public void setBuoi(String buoi) {
        this.buoi = buoi;
    }
    
    
    
}
