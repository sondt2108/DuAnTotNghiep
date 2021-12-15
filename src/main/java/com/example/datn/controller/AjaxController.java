package com.example.datn.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.google.gson.Gson;


@Transactional
@Controller
@RequestMapping("/admin")
public class AjaxController {
    @Autowired
	SessionFactory factory;
	Date toDate = new Date(System.currentTimeMillis());
	SimpleDateFormat fomatTime2 = new SimpleDateFormat("yyyy-dd-MM");
	String date2 = fomatTime2.format(toDate.getTime());
	String month = date2.substring(date2.indexOf("-") + 4, date2.length());
	SimpleDateFormat fomatTime = new SimpleDateFormat("yyyy-MM-dd");
	String date = fomatTime.format(toDate.getTime());
	String year = date.substring(0, date.indexOf("-"));

	// Thống kê theo ngày
	@SuppressWarnings("unchecked")
	@RequestMapping("/LayDuLieuHienTai")
	public void GetDataTKByToDate(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Session session = factory.getCurrentSession();
		List<Object[]> thongketheongay = session
				.createQuery("SELECT sum(total), (SELECT sum(total)  FROM orders where createdDate = '" + date
						+ "' and tinhtrang_id != 6)  " + "FROM orders hd where extract (month from hd.createdDate) = '" + month + "' "
						+ "and extract (year from hd.createdDate) = '" + year + "' ")
				.list();

		Gson gson = new Gson();
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(thongketheongay));
		out.flush();
		out.close();

	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/LayDuLieuTheoNgay")
	public void GetDataTKByDate(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Session session = factory.getCurrentSession();
		String dateChoose = request.getParameter("date");
		String yearOfdateChoose = dateChoose.substring(0, 4);
		String monthOfdateChoose = dateChoose.substring(5, 7);
		List<Object[]> thongketheongay = session
				.createQuery("SELECT sum(total), (SELECT sum(total)  FROM orders where Date(createdDate) = '" + dateChoose
						+ "' and tinhtrang_id != 6)  " + "FROM orders hd where extract (month from hd.createdDate) = '" + monthOfdateChoose + "'"
						+ "and extract (year from hd.createdDate) = '" + yearOfdateChoose + "' ")
				.list();

		Gson gson = new Gson();
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(thongketheongay));
		out.flush();
		out.close();
	}

	// // End thống kê theo ngày
	// Thống kê theo tháng
	@SuppressWarnings("unchecked")
	@RequestMapping("/TkThangMd")
	public void TkThangMd(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Session session = factory.getCurrentSession();
		List<Object[]> data = session.createQuery(
				"SELECT sum(hd.total), extract(month from createdDate) FROM orders  hd where EXTRACT (year from hd.createdDate)  = '"
						+ year + "' and tinhtrang_id != 6 group by EXTRACT (month from hd.createdDate) order by EXTRACT (month from hd.createdDate)")
				.list();
		Gson gson = new Gson();
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(data));
		out.flush();
		out.close();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/TkThang")
	public void TkThang(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Session session = factory.getCurrentSession();
		String year = request.getParameter("year");
		List<Object[]> data = session.createQuery(
				"SELECT sum(hd.total), extract(month from createdDate) FROM orders  hd where EXTRACT (year from hd.createdDate)  = '"
						+ year + "' and tinhtrang_id != 04 group by EXTRACT (month from hd.createdDate) order by EXTRACT (month from hd.createdDate)")
				.list();
		Gson gson = new Gson();
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(data));
		out.flush();
		out.close();
	}
	// END Thống kê theo tháng
	// Thống kê phiếu nhập theo tháng
		@SuppressWarnings("unchecked")
		@RequestMapping("/TkNhapThangMd")
		public void TkNhapThangMd(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
			Session session = factory.getCurrentSession();
			List<Object[]> data = session.createQuery(
					"SELECT sum(hd.tongTien), extract(month from ngayNhap) FROM phieunhap  hd where EXTRACT (year from hd.ngayNhap)  = '"
							+ year + "'  group by EXTRACT (month from hd.ngayNhap) order by EXTRACT (month from hd.ngayNhap)")
					.list();
			Gson gson = new Gson();
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(data));
			out.flush();
			out.close();
		}

		@SuppressWarnings("unchecked")
		@RequestMapping("/TkNhapThang")
		public void TkNhapThang(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
			Session session = factory.getCurrentSession();
			String year = request.getParameter("year");
			List<Object[]> data = session.createQuery(
					"SELECT sum(hd.tongTien), extract(month from ngayNhap) FROM phieunhap  hd where EXTRACT (year from hd.ngayNhap)  = '"
							+ year + "'  group by EXTRACT (month from hd.ngayNhap) order by EXTRACT (month from hd.ngayNhap)")
					.list();
			Gson gson = new Gson();
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(data));
			out.flush();
			out.close();
		}
		// END Thống kê theo tháng
}
