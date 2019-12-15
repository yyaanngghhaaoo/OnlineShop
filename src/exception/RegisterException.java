package exception;
//ע���û�
public class RegisterException extends Exception {

	private static final long serialVersionUID = 1L;

	public RegisterException() {
		super();
	}

	public RegisterException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegisterException(String message) {
		super(message);
	}

	public RegisterException(Throwable cause) {
		super(cause);
	}

}
