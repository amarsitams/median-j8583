package com.rumango.median.iso.test;

import java.util.LinkedHashMap;

import org.jasypt.registry.AlgorithmRegistry;
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
		// System.out.println(database);
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

//		StandardPBEStringEncryptor myFirstEncryptor = new StandardPBEStringEncryptor();
//		myFirstEncryptor.setProvider(new BouncyCastleProvider());
//		myFirstEncryptor.setAlgorithm("PBEWITHSHA256AND256BITAES-CBC-BC");// PBEWITHHMACSHA512ANDAES_256
//																			// //PBEWITHSHA256AND128BITAES-CBC-BC
//		myFirstEncryptor.setPassword("myPassword");
//		String myFirstEncryptedText = myFirstEncryptor.encrypt("myText");
//
//		System.out.println("myFirstEncryptedText:" + myFirstEncryptedText);

		// System.out.println(AlgorithmRegistry.getAllPBEAlgorithms());

//		StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
//		textEncryptor.setPassword("9tdk02NwB/V2HB0Pwa/qhfG85pcoD+bwt9jp/5+MRos=");
//
//		String myEncryptedText = textEncryptor.encrypt("myEncryptionPassword");
//		System.out.println("myEncryptedText::" + myEncryptedText);
//
//		StrongTextEncryptor textEncryptor2 = new StrongTextEncryptor();
//		textEncryptor2.setPassword("9tdk02NwB/V2HB0Pwa/qhfG85pcoD+bwt9jp/5+MRos=");
//		String plainText = textEncryptor2.decrypt(myEncryptedText);
//		System.out.println("plainText::" + plainText);

	}

//	protected void configure(HttpSecurity http) throws Exception {
//		http.antMatcher("/").authorizeRequests().anyRequest().hasRole("ADMIN").and().httpBasic();
//	}
}