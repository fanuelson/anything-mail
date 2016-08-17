package br.com.any.configuration;

import java.util.Properties;

import javax.mail.Session;

import br.com.any.util.EmailPropertyUtils;

public class EmailConfiguration {

	static String KEY_SMTP_AUTH = "mail.smtp.auth";

	private AutenticacaoEmail autenticacaoEmail;
	private Properties properties;

	private EmailConfiguration(AutenticacaoEmail autenticacaoEmail) {
		this.autenticacaoEmail = autenticacaoEmail;
		this.properties = EmailPropertyUtils.getProperties();
	}

	private EmailConfiguration(AutenticacaoEmail autenticacaoEmail, Properties properties) {
		this.autenticacaoEmail = autenticacaoEmail;
		this.properties = properties;
	}

	public static EmailConfiguration configure(AutenticacaoEmail autenticacaoEmail) {
		return new EmailConfiguration(autenticacaoEmail);
	}

	public static EmailConfiguration configure(AutenticacaoEmail autenticacaoEmail, Properties properties) {
		return new EmailConfiguration(autenticacaoEmail, properties);
	}

	public AutenticacaoEmail getAutenticacaoEmail() {
		return autenticacaoEmail;
	}

	public void setAutenticacaoEmail(AutenticacaoEmail autenticacaoEmail) {
		this.autenticacaoEmail = autenticacaoEmail;
	}

	public Session getSession() {
		String isSmtpAuthProp = properties.getProperty(KEY_SMTP_AUTH);
		if (isSmtpAuthProp != null && isSmtpAuthProp.equalsIgnoreCase("true")) {
			return Session.getInstance(properties, getAutenticacaoEmail());
		}
		return Session.getInstance(properties);

	}

}
