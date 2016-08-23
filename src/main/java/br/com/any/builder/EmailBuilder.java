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

import br.com.any.exception.FileMailAttachmentException;
import br.com.any.model.Email;

public class EmailBuilder {

	private Email email;

	public EmailBuilder() {
		email = new Email();
	}

	public EmailBuilder from(String emailFrom) {
		try {
			email.setFromAddress(new InternetAddress(emailFrom));
		} catch (AddressException e) {
			throw new RuntimeException("ERRO EMAIL ADDRESS FROM: " + emailFrom, e);
		}
		return this;
	}

	public EmailBuilder from(String emailFrom, String personalName) {
		try {
			email.setFromAddress(new InternetAddress(emailFrom, personalName));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("ERRO CHARSET ENCODING ADDRESS FROM: " + emailFrom + personalName, e);
		}
		return this;
	}

	public EmailBuilder to(List<InternetAddress> to) {
		email.setToAddresses(to);
		return this;
	}

	public EmailBuilder to(String to) {
		try {
			email.getToAddresses().add(new InternetAddress(to));
		} catch (AddressException e) {
			throw new RuntimeException("ERRO EMAIL ADDRESS TO: " + to, e);
		}
		return this;
	}

	public EmailBuilder cc(List<InternetAddress> cc) {
		email.setCcAddresses(cc);
		return this;
	}

	public EmailBuilder bcc(List<InternetAddress> bcc) {
		email.setBccAddresses(bcc);
		return this;
	}

	public EmailBuilder subject(String subject) {
		email.setSubject(subject);
		return this;
	}

	public EmailBuilder textBody(String textBody) {
		return getBody(textBody, "text/plain; charset=UTF-8");
	}

	public EmailBuilder htmlBody(String textBody) {
		return getBody(textBody, "text/html; charset=UTF-8");
	}

	private EmailBuilder getBody(String textBody, String type) {
		try {
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(textBody, type);
			email.getMultiPart().addBodyPart(messageBodyPart);
		} catch (MessagingException e) {
			throw new RuntimeException("ERROR ADDING BODY: " + textBody, e);
		}
		return this;
	}

	public EmailBuilder addFile(InputStream is, String fileName, String fileType) {
		try {
			MimeBodyPart fileBodyPart = new MimeBodyPart();
			DataSource source = new ByteArrayDataSource(is, fileType);
			fileBodyPart.setDataHandler(new DataHandler(source));
			fileBodyPart.setFileName(fileName);
			email.getMultiPart().addBodyPart(fileBodyPart);
		} catch (IOException ioe) {
			throw new FileMailAttachmentException("ERROR ADDING FILE MAIL ATTACHMENT: " + fileName, ioe);
		} catch (MessagingException e) {
			throw new FileMailAttachmentException("ERROR ADDING FILE MAIL ATTACHMENT: " + fileName, e);
		}
		return this;
	}

	public EmailBuilder addFile(File file) {
		try {
			MimeBodyPart fileBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(file);
			fileBodyPart.setDataHandler(new DataHandler(source));
			fileBodyPart.setFileName(file.getName());
			email.getMultiPart().addBodyPart(fileBodyPart);
		} catch (MessagingException e) {
			throw new FileMailAttachmentException("ERROR ADDING FILE MAIL ATTACHMENT: " + file.getName(), e);
		}
		return this;
	}

	public Email build() {
		return this.email;
	}
}
