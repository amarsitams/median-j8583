package com.rumango.median.iso;

import java.util.LinkedHashMap;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class JasyptDemo {
	private static final String username = "testuser";
	private static final String userpass = "testpass";
	private static LinkedHashMap<String, String> database = new LinkedHashMap<>();

	public String encryptPassword() {
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(userpass);
		storePasswordForUser(username, encryptedPassword);
		return encryptedPassword;
	}

	public boolean checkPassword(String username, String submittedPassword) {
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		String encryptedPassword = database.get(username);
		return passwordEncryptor.checkPassword(submittedPassword, encryptedPassword);
	}

	public void storePasswordForUser(String username, String hashedPassword) {
		// use jdbc to store the username and hashed password in the database.
		database.put(username, hashedPassword);
	}

	public String getPasswordForUser(String username) {
		// use jdbc to retrieve the password for this username,
		return database.get(username);
	}

	public static void main(String[] args) {
		JasyptDemo encrypter = new JasyptDemo();
		// encrypter.encryptPassword();
		System.out.println(encrypter.encryptPassword());
		JasyptDemo checker = new JasyptDemo();
		System.out.println("Passwords Matched " + (checker.checkPassword(username, userpass)));
	}
}