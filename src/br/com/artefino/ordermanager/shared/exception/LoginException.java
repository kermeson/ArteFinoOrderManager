package br.com.artefino.ordermanager.shared.exception;

public class LoginException extends Exception {

  private static final long serialVersionUID = -6517677039932882817L;

  public LoginException() {
  }

  public LoginException(String message) {
    super(message);
  }

  public LoginException(String message, Throwable cause) {
    super(message + " (" + cause.getMessage() + ")");
  }

  public LoginException(Throwable cause) {
    super(cause.getMessage());
  }
}
