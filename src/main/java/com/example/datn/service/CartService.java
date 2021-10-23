package com.example.datn.service;


import com.example.datn.models.GioHang;


public interface CartService {
    public GioHang getGioHang();
	public void themSanPham(int productId);
	public void truSanPham(int productId);
	public void xoaSanPham(int productId);
	


	
}
