package br.com.any.configuration;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AutenticacaoEmail extends Authenticator {
	
	private String username;
	private String password;
	
	private AutenticacaoEmail(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public static AutenticacaoEmail configure(String username, String password){
		return new AutenticacaoEmail(username, password);
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}
