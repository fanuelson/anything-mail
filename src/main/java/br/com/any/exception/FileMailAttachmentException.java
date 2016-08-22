package br.com.any.exception;

public class FileMailAttachmentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileMailAttachmentException(String message) {
		super(message);
	}

	public FileMailAttachmentException(Throwable throwable) {
		super(throwable);
	}

	public FileMailAttachmentException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
