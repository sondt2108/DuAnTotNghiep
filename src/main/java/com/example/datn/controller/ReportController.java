package com.example.datn.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;




@Transactional
@Controller
public class ReportController {
    @Autowired
	SessionFactory factory;
	Date toDate = new Date(System.currentTimeMillis());
	SimpleDateFormat fomatTime2 = new SimpleDateFormat("yyyy-dd-MM");
	String date2 = fomatTime2.format(toDate.getTime());
	String month = date2.substring(date2.indexOf("-") + 4, date2.length());
	SimpleDateFormat fomatTime = new SimpleDateFormat("yyyy-MM-dd");
	String date = fomatTime.format(toDate.getTime());
	String year = date.substring(0, date.indexOf("-"));

	//report
	@RequestMapping("admin/dashboard")
	public String thongke(ModelMap model, HttpServletRequest request) {

		return "/dashboard/report";
	}
}
