package com.example.datn.service;


import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MailService {
   

    @Autowired
	JavaMailSender sender;
	
private List<MimeMessage> mailList = new ArrayList<>();
	
/*
 * public void pushMail(String to) { MimeMessage message =
 * sender.createMimeMessage(); MimeMessageHelper helper = new
 * MimeMessageHelper(message); try {
 * helper.setFrom("Thanh Son <sonthanh111@outlook.com>"); helper.setTo(to);
 * helper.setSubject("Thank you"); helper.setText("Đặt hàng thành công."); }
 * catch (MessagingException e) {
 * System.out.println("Không thể thêm mail vào hàng đợi"); e.printStackTrace();
 * } mailList.add(message); }
 */
	
	
	

	public void sendMailWithResetPassword(String email, String urlRest) {

		// lay data
		

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);


        String content = "<p>Hello,</p>"
            + "<p>You have requested to reset your password.</p>"
            + "<p>Click the link below to change your password:</p>"
            + "<p><a href=\"" + urlRest + "\">Change my password</a></p>"
            + "<br>"
            + "<p>Ignore this email if you do remember your password, "
            + "or you have not made the request.</p>";
		try {
			
			helper.setFrom("Thanh Son <sonthanh111@outlook.com>");
			helper.setTo(email);
			helper.setSubject("Thank you");
			helper.setText(content, true);
		} catch (MessagingException e) {
			System.out.println("Không thể thêm mail vào hàng đợi");
			e.printStackTrace();
		}
		mailList.add(message);
		// Send Message!
		this.sender.send(message);

	}
	
	@Scheduled(fixedDelay = 2000)
	public void run() {
		while (!mailList.isEmpty()) {
			MimeMessage message = mailList.remove(0);
			try {
				sender.send(message);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.print("gui mail loi");
				e.printStackTrace();
			}
		}
	}
    
}
