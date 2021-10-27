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
}
