package br.com.artefino.ordermanager.shared.exception;

import com.gwtplatform.dispatch.shared.ActionException;

public class SessionTimeOutException extends ActionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SessionTimeOutException() {
	}

	public SessionTimeOutException(String message) {
		super(message);
	}

	public SessionTimeOutException(String message, Throwable cause) {
		super(message + " (" + cause.getMessage() + ")");
	}

	public SessionTimeOutException(Throwable cause) {
		super(cause.getMessage());
	}
}
