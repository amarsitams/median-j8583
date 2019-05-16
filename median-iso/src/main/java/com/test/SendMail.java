package com.test;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SendMail {

	private final static Logger logger = Logger.getLogger(SendMail.class);
	private String host, port, username, password;
	private Properties props = System.getProperties();
	private Session l_session = null;
	private boolean isSession;

	public static void main(String[] args) {
		new SendMail().sendOtp();
	}

	public void sendOtp() {
		Random r = new Random();
		int otp = r.nextInt(10000) + 100000;

		String message = "Your login OTP is: \n" +

				otp + "\n" +

				"This OTP is confidential. For security reasons, DO NOT share the OTP with anyone. ";

		sendMessage("amarsitams@gmail.com", "OTP", message);
	}

	// rumango
	public SendMail() {
		this.host = "smtpout.secureserver.net";
		this.port = "465";
		this.username = "bandita.banerjee@rumango.com";
		this.password = "pass@word";
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "false");
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.enable", "true");
		this.isSession = createSession();
	}

	private boolean createSession() {
		logger.info("Inside createSession");
		try {
			l_session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
//			 Enable the debug mode
			l_session.setDebug(true);
		} catch (Exception e) {
			logger.error("Unable to create createSession", e);
			return false;
		}
		return true;
	}

	public boolean sendMessage(String toEmail, String subject, String msg) {
		logger.info("Inside sendMessage");
		if (isSession) {
			boolean isValid = false;
			try {
				MimeMessage message = new MimeMessage(l_session);
				message.setFrom(new InternetAddress(this.username));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
				message.setSubject(subject);
				message.setContent(msg, "text/plain");
				InternetAddress internetAddress = new InternetAddress(toEmail);
				internetAddress.validate();
				isValid = true;

				// creates message part
				MimeBodyPart messageBodyPart = new MimeBodyPart();

				// creates multi-part
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);

				MimeBodyPart imagePart = new MimeBodyPart();
				imagePart.setHeader("Content-ID", "<" + "Content" + ">");
				imagePart.setDisposition(MimeBodyPart.INLINE);

				String imageFilePath = "C:\\Users\\hp1\\Pictures\\rrb.JPG";
				try {
					imagePart.attachFile(imageFilePath);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				multipart.addBodyPart(imagePart);

				message.setContent(multipart);

				Transport.send(message);
				logger.info("Message Sent");
			} catch (MessagingException mex) {
				mex.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} // end catch block
			return isValid;
		} else
			return false;
	}

}