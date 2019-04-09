package com.rumango.median.iso.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SendMail {

	private final static Logger logger = Logger.getLogger(SendMail.class);
	private String host, port, username, password;
	private Properties props = System.getProperties();
	private Session l_session = null;
	private boolean isSession;

	// orient
//	public SendMail() {
//		this.host = "mail.orient-bank.com";
//		this.port = "465";
//		this.username = "datamart@orient-bank.com";
//		this.password = "OBLKampala#2019";
//		props.put("mail.smtp.host", host);
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.debug", "false");
//		props.put("mail.smtp.port", port);
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.ssl.enable", "true");
//		this.isSession = createSession();
//	}

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