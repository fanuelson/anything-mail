package br.com.any.model;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;

public class Email {

	private InternetAddress fromAddress;
	private List<InternetAddress> toAddresses = new ArrayList<InternetAddress>();
	private List<InternetAddress> ccAddresses = new ArrayList<InternetAddress>();
	private List<InternetAddress> bccAddresses = new ArrayList<InternetAddress>();
	private String subject;
	private Multipart multiPart = new MimeMultipart();

	public InternetAddress getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(InternetAddress fromAddress) {
		this.fromAddress = fromAddress;
	}

	public List<InternetAddress> getToAddresses() {
		return toAddresses;
	}

	public void setToAddresses(List<InternetAddress> toAddresses) {
		this.toAddresses = toAddresses;
	}

	public List<InternetAddress> getCcAddresses() {
		return ccAddresses;
	}

	public void setCcAddresses(List<InternetAddress> ccAddresses) {
		this.ccAddresses = ccAddresses;
	}

	public List<InternetAddress> getBccAddresses() {
		return bccAddresses;
	}

	public void setBccAddresses(List<InternetAddress> bccAddresses) {
		this.bccAddresses = bccAddresses;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Multipart getMultiPart() {
		return multiPart;
	}

	public void setMultiPart(Multipart multiPart) {
		this.multiPart = multiPart;
	}

}
