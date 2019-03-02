package pl.sygnity.converter.logic;

import java.time.Instant;

public class MyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2514070201008093584L;
	private int code;
	private String message;
    private Instant timestamp;

	public MyException(int code, String message, Throwable cause) {
//		super();
		super(cause);
		this.code = code;
		this.message = message;
        this.timestamp = Instant.now();
	}	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
		
}
