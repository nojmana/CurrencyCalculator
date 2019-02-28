package pl.sygnity.converter.rest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class MyErrorController implements ErrorController {

	private static final Logger logger = LoggerFactory.getLogger(ConverterController.class);

    public String handleError(HttpServletRequest request) {
    	logger.info("MyErrorController.handleError()");
    	Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    	
    	if (status != null) {
        	logger.info("AAA");
    		Integer statusCode = Integer.valueOf(status.toString());
    		
    		if (statusCode == HttpStatus.NOT_FOUND.value()) {
            	logger.info("BBB");
    			return "error";
    		} 
    		else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            	logger.info("CCC");
    			return "error";
    		}
    	}
    	logger.info("DDD");
        return "error";
    }
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
