package com.example.datn.payload.request;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

public class OrderRequest {
    @NotBlank
	private String province;


    @NotBlank
	private String district;

    @NotBlank
	private String ward;

    @NotBlank
	private String address;

    @NotBlank
	private String phoneNumber;

    
    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    private BigDecimal total;


    private String note;


    public String getProvince() {
        return province;
    }


    public void setProvince(String province) {
        this.province = province;
    }


    public String getDistrict() {
        return district;
    }


    public void setDistrict(String district) {
        this.district = district;
    }


    public String getWard() {
        return ward;
    }


    public void setWard(String ward) {
        this.ward = ward;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public BigDecimal getTotal() {
        return total;
    }


    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    public String getNote() {
        return note;
    }


    public void setNote(String note) {
        this.note = note;
    }


   

    
}
