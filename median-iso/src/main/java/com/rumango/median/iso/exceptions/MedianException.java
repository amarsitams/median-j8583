package com.rumango.median.iso.exceptions;

public class MedianException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MedianException(String message) {
		super(message);
	}

	public MedianException(String message, Throwable cause) {
		super(message, cause);
	}
}
