package com.rumango.median.iso.mail;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private SendMail sendMail;

	public void sendOtp() {
		Random r = new Random();
		int otp = r.nextInt(10000) + 100000;

		String message = "Your login OTP is: \n" +

				otp + "\n" +

				"This OTP is confidential. For security reasons, DO NOT share the OTP with anyone. ";

		sendMail.sendMessage("amarsitams@gmail.com", "OTP", message);
	}

//	public static void main(String[] args) {
//		Random r = new Random();
//		int random = r.nextInt(10000) + 100000;
//		System.err.println(random);
//
//	}

}
