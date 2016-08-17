package br.com.any.configuration;

import java.util.Properties;

import javax.mail.Session;

public class EmailConfiguration {

	static String KEY_SMTP_AUTH = "mail.smtp.auth";
	static String KEY_SMTP_TLS_ENABLED = "mail.smtp.starttls.enable";
	static String KEY_SMTP_HOST = "mail.smtp.host";
	static String KEY_SMTP_PORT = "mail.smtp.port";

	private AutenticacaoEmail autenticacaoEmail;
	private boolean smtpTLS = true;
	private boolean authenticationRequired = true;
	private String smtpHost = "smtp.gmail.com";
	private String smtpPort = "587";
	
	private EmailConfiguration(AutenticacaoEmail autenticacaoEmail) {
		this.autenticacaoEmail = autenticacaoEmail;
	}

	private EmailConfiguration(String host, String port, boolean authRequired, boolean smtpTLS, AutenticacaoEmail autenticacaoEmail) {
		this.smtpHost = host;
		this.smtpPort = port;
		this.authenticationRequired = authRequired;
		this.smtpTLS = smtpTLS;
		this.autenticacaoEmail = autenticacaoEmail;
	}

	public static EmailConfiguration configure(String host, String port, boolean authRequired, boolean smtpTLS, AutenticacaoEmail autenticacaoEmail) {
		return new EmailConfiguration(host, port, authRequired, smtpTLS, autenticacaoEmail);
	}
	
	public static EmailConfiguration configure(AutenticacaoEmail autenticacaoEmail) {
		return new EmailConfiguration(autenticacaoEmail);
	}

	public boolean isSmtpTLS() {
		return smtpTLS;
	}

	public void setSmtpTLS(boolean smtpTLS) {
		this.smtpTLS = smtpTLS;
	}

	public boolean isAuthenticationRequired() {
		return authenticationRequired;
	}

	public void setAuthenticationRequired(boolean authenticationRequired) {
		this.authenticationRequired = authenticationRequired;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public AutenticacaoEmail getAutenticacaoEmail() {
		return autenticacaoEmail;
	}

	public void setAutenticacaoEmail(AutenticacaoEmail autenticacaoEmail) {
		this.autenticacaoEmail = autenticacaoEmail;
	}
	
	public Session getSession(){
		Properties properties = new Properties();
		properties.put(KEY_SMTP_AUTH, "" + authenticationRequired);
		properties.put(KEY_SMTP_TLS_ENABLED, "" + smtpTLS);
		properties.put(KEY_SMTP_HOST, smtpHost);
		properties.put(KEY_SMTP_PORT, smtpPort);
		return Session.getInstance(properties, getAutenticacaoEmail());
	}
	
}
