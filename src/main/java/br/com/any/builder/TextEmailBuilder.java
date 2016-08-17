package br.com.any.builder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import br.com.any.model.Email;

public class TextEmailBuilder implements EmailBuilder {

	private Email email;

	public TextEmailBuilder() {
		email = new Email();
	}

	public TextEmailBuilder from(String emailFrom) {
		try {
			email.setFromAddress(new InternetAddress(emailFrom));
		} catch (AddressException e) {
			throw new RuntimeException("ERRO EMAIL ADDRESS FROM: " + emailFrom, e);
		}
		return this;
	}
	
	public TextEmailBuilder from(String emailFrom, String personalName) {
		try {
			email.setFromAddress(new InternetAddress(emailFrom, personalName));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("ERRO CHARSET ENCODING ADDRESS FROM: " + emailFrom + personalName, e);
		}
		return this;
	}

	public TextEmailBuilder to(List<InternetAddress> to) {
		email.setToAddresses(to);
		return this;
	}
	
	public TextEmailBuilder to(String to) {
		try {
			email.getToAddresses().add(new InternetAddress(to));
		} catch (AddressException e) {
			throw new RuntimeException("ERRO EMAIL ADDRESS TO: " + to, e);
		}
		return this;
	}

	public TextEmailBuilder cc(List<InternetAddress> cc) {
		email.setCcAddresses(cc);
		return this;
	}

	public TextEmailBuilder bcc(List<InternetAddress> bcc) {
		email.setBccAddresses(bcc);
		return this;
	}

	public TextEmailBuilder subject(String subject) {
		email.setSubject(subject);
		return this;
	}

	public TextEmailBuilder textBody(String textBody) {
		try {
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(textBody, "text/plain; charset=UTF-8");
			email.getMultiPart().addBodyPart(messageBodyPart);
		} catch (MessagingException e) {
			throw new RuntimeException("ERRO TEXT BODY: " + textBody, e);
		}
		return this;
	}

	public TextEmailBuilder addFile(InputStream is, String fileName, String fileType) {
		try {
			MimeBodyPart fileBodyPart = new MimeBodyPart();
			DataSource source = new ByteArrayDataSource(is, fileType);
			fileBodyPart.setDataHandler(new DataHandler(source));
			fileBodyPart.setFileName(fileName);
			email.getMultiPart().addBodyPart(fileBodyPart);
		} catch (IOException ioe) {
			throw new RuntimeException("ERRO ADD FILE: " + fileName, ioe);
		} catch (MessagingException e) {
			throw new RuntimeException("ERRO ADD FILE: " + fileName, e);
		}
		return this;
	}

	public TextEmailBuilder addFile(File file) {
		try {
			MimeBodyPart fileBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(file);
			fileBodyPart.setDataHandler(new DataHandler(source));
			fileBodyPart.setFileName(file.getName());
			email.getMultiPart().addBodyPart(fileBodyPart);
		} catch (MessagingException e) {
			throw new RuntimeException("ERRO ADD FILE: " + file.getName(), e);
		}
		return this;
	}

	@Override
	public Email build() {
		return this.email;
	}
}
