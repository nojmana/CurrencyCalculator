package pl.sygnity.converter.logic;

import java.time.Instant;

public class MyExceptionResponse {

    private int code;
    private String message;
    private Instant timestamp;
    
    
    public MyExceptionResponse(MyException e) {
        this.code = e.getCode();
        this.message = e.getMessage();
        this.timestamp = e.getTimestamp();
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
