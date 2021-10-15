package com.example.datn.models;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity(name="products")
public class Product {
    
    @Id
	@GeneratedValue
	private int productId;
	
	@NotBlank
	@Size(min = 3, max = 255)
	private String tensanpham;
	
	
	@ManyToOne
	@JoinColumn(
			name = "nhacungcap_id",
			nullable = true,
			foreignKey = @ForeignKey(name = "nhacungcap_products"))
	private NhaCungCap nhacungcap;
	
	
	
	
	@ManyToOne
	@JoinColumn(
			name = "Thuonghieu_id",
			nullable = true,
			foreignKey = @ForeignKey(name = "Thuonghieu_products"))
	private ThuongHieu thuonghieu;


	@ManyToOne
	@JoinColumn(
			name = "Loaisanpham_id",
			nullable = true,
			foreignKey = @ForeignKey(name = "LoaiSanPham_products"))
	private Loaisp loaisanpham;


	private double soluong;
	
	private double khuyenmai;
	
	private String tinhtranghang;

	@NotNull
	@Min(1000)
	@Max(1000000)
	private Long gia;
	
	@NotNull
	private Date createdDate = new Date((new java.util.Date()).getTime());
	
	@ManyToOne
	@JoinColumn(
			name = "category_id",
			nullable = true,
			foreignKey = @ForeignKey(name = "category_products"))
	private Category category;
	
	private String hinhanh;

	public Product() {
		super();
	}

	public Product(int productId, @NotBlank @Size(min = 3, max = 30) String tensanpham, NhaCungCap nhacungcap,
			ThuongHieu thuonghieu, Loaisp loaisanpham, double soluong, double khuyenmai, String tinhtranghang,
			@NotNull @Min(1000) @Max(1000000) Long gia, @NotNull Date createdDate, Category category, String hinhanh) {
		this.productId = productId;
		this.tensanpham = tensanpham;
		this.nhacungcap = nhacungcap;
		this.thuonghieu = thuonghieu;
		this.loaisanpham = loaisanpham;
		this.soluong = soluong;
		this.khuyenmai = khuyenmai;
		this.tinhtranghang = tinhtranghang;
		this.gia = gia;
		this.createdDate = createdDate;
		this.category = category;
		this.hinhanh = hinhanh;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getTensanpham() {
		return tensanpham;
	}

	public void setTensanpham(String tensanpham) {
		this.tensanpham = tensanpham;
	}

	public NhaCungCap getNhacungcap() {
		return nhacungcap;
	}

	public void setNhacungcap(NhaCungCap nhacungcap) {
		this.nhacungcap = nhacungcap;
	}

	public ThuongHieu getThuonghieu() {
		return thuonghieu;
	}

	public void setThuonghieu(ThuongHieu thuonghieu) {
		this.thuonghieu = thuonghieu;
	}

	public Loaisp getLoaisanpham() {
		return loaisanpham;
	}

	public void setLoaisanpham(Loaisp loaisanpham) {
		this.loaisanpham = loaisanpham;
	}

	public double getSoluong() {
		return soluong;
	}

	public void setSoluong(double soluong) {
		this.soluong = soluong;
	}

	public double getKhuyenmai() {
		return khuyenmai;
	}

	public void setKhuyenmai(double khuyenmai) {
		this.khuyenmai = khuyenmai;
	}

	public String getTinhtranghang() {
		return tinhtranghang;
	}

	public void setTinhtranghang(String tinhtranghang) {
		this.tinhtranghang = tinhtranghang;
	}

	public Long getGia() {
		return gia;
	}

	public void setGia(Long gia) {
		this.gia = gia;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getHinhanh() {
		return hinhanh;
	}

	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}


	

	


}