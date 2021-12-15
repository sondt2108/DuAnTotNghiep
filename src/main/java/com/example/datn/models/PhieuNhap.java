package com.example.datn.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.persistence.ForeignKey;

@Entity(name="phieunhap")
public class PhieuNhap {
    
    @Id
	@GeneratedValue
	private int idPN;
	
	@NotNull
	private Date ngayNhap = new Date((new java.util.Date()).getTime());
	
	
	  @ManyToOne
	  @JoinColumn( name = "nhacungcap_id", nullable = true, foreignKey
	  = @ForeignKey(name = "nhacungcap_phieunhaps")) private NhaCungCap nhacungcap;

	
	private int soLuong;
	
	private double tongTien;
	
	private String ghiChu;

    

    public PhieuNhap(int idPN, @NotNull Date ngayNhap, NhaCungCap nhacungcap, int soLuong, double tongTien,
            String ghiChu) {
        this.idPN = idPN;
        this.ngayNhap = ngayNhap;
        this.nhacungcap = nhacungcap;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
    }

    public PhieuNhap() {
    }

    public int getIdPN() {
        return idPN;
    }

    public void setIdPN(int idPN) {
        this.idPN = idPN;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public NhaCungCap getNhacungcap() {
        return nhacungcap;
    }

    public void setNhacungcap(NhaCungCap nhacungcap) {
        this.nhacungcap = nhacungcap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    
}
