package br.com.any.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.any.configuration.EmailConfiguration;
import br.com.any.model.Email;

public class EmailService {
	
	private Session session;

	private EmailService(EmailConfiguration emailConfig) {
		session = emailConfig.getSession();
		session.setDebug(true);
	}
	
	private EmailService(EmailConfiguration emailConfig, boolean enableDebug) {
		session = emailConfig.getSession();
		session.setDebug(enableDebug);
	}
	
	public static EmailService create(EmailConfiguration emailConfig){
		return new EmailService(emailConfig);
	}
	
	public static EmailService create(EmailConfiguration emailConfig, boolean enableDebug){
		return new EmailService(emailConfig, enableDebug);
	}

	public void enviar(Email email) throws RuntimeException {
		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(email.getFromAddress());
			
			InternetAddress[] to =  new InternetAddress[email.getToAddresses().size()] ;
			email.getToAddresses().toArray(to);
			message.setRecipients(Message.RecipientType.TO, to);
			
			InternetAddress[] cc =  new InternetAddress[email.getCcAddresses().size()] ;
			email.getToAddresses().toArray(cc);
			message.setRecipients(Message.RecipientType.CC, cc);
			
			InternetAddress[] bcc =  new InternetAddress[email.getBccAddresses().size()] ;
			email.getToAddresses().toArray(bcc);
			message.setRecipients(Message.RecipientType.BCC, bcc);
			
			message.setSubject(email.getSubject());
			
			Multipart multipart = email.getMultiPart();

			message.setContent(multipart);
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} 
	}
	
}
