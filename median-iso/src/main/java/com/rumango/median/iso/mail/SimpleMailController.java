package com.rumango.median.iso.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleMailController {
	@Autowired
	private MailService mailService;

//	@RequestMapping("/sendMail")
//	public String sendMail() {
//		MimeMessage message = sender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(message);
//		try {
//			helper.setTo("amarnath.reddy@rumango.com");
//			helper.setSubject("Mail From Spring Boot");
//			helper.setText("Greetings :)");
//		} catch (MessagingException e) {
//			e.printStackTrace();
//			return "Error while sending mail ..";
//		}
//		sender.send(message);
//		return "Mail Sent Success!";
//	}

	@RequestMapping("/sendMail")
	public String sendMail() {
		System.err.println("Inside send mail 2");
		try {
			mailService.sendOtp();
		} catch (Exception e) {
			return "Error while sending mail ..";
		}
		return "Mail Sent Success!";
	}
}