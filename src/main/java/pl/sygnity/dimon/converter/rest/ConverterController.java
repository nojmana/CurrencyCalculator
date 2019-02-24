package pl.sygnity.dimon.converter.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.sygnity.dimon.converter.logic.Converter;

@RestController
@RequestMapping("/convert")
public class ConverterController {
	
	private static final Logger logger = LoggerFactory.getLogger(ConverterController.class);

	@RequestMapping(path = "/{value}") 
	public Converter converter(@PathVariable String value, 
			@RequestParam(value = "currency", defaultValue = "PLN") String currency/*, String date*/) {
		logger.info("Value: " + value);
		logger.info("Currency: " + currency);
//		logger.debug("Date: ", date);
		
//		return new Converter(value, currency, date);
		return new Converter(value, currency);
	}
}
