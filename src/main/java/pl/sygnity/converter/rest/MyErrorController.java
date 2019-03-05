package pl.sygnity.converter.rest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import pl.sygnity.converter.logic.MyException;
import pl.sygnity.converter.logic.MyExceptionResponse;

@RestControllerAdvice
public class MyErrorController {

	@ExceptionHandler(MyException.class)
	public MyExceptionResponse handleMyException(MyException e) {
		return new MyExceptionResponse(e);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public MyExceptionResponse handleNoHandlerFoundException(NoHandlerFoundException e) {
		MyException myException = new MyException(400, "Handler not found", e);
		return new MyExceptionResponse(myException);
	}
	
}