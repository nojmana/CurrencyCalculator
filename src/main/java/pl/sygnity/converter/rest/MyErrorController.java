package pl.sygnity.converter.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import pl.sygnity.converter.logic.MyException;
import pl.sygnity.converter.logic.MyExceptionResponse;

@RestControllerAdvice
public class MyErrorController {

	private static final Logger logger = LoggerFactory.getLogger(ConverterController.class);

	@ExceptionHandler(MyException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public MyExceptionResponse handleMyException(MyException e) {
		return new MyExceptionResponse(e);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public MyExceptionResponse handleNoHandlerFoundException(NoHandlerFoundException e) {
		MyException myException = new MyException(400, "Handler not found", e);
		return new MyExceptionResponse(myException);
	}

	
}

