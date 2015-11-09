package com.gestor.backend.authenticator;

import javax.mail.PasswordAuthentication;

public class Authenticator extends javax.mail.Authenticator{

	private String user;
	
	private String password;
	
	public Authenticator(String user,String password) {
		this.user = user;
		this.password = password;
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password);
	}

}
