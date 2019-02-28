package pl.sygnity.converter.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.sygnity.converter.dao.Database;
import pl.sygnity.converter.logic.Converter;

@RestController
@RequestMapping("/convert")
public class ConverterController {
	
	private static final Logger logger = LoggerFactory.getLogger(ConverterController.class);
	
	@Autowired
	private Database database;
	
	@RequestMapping(path = "/{currencyName}/{value}/{date}") 
	public Converter converter(@PathVariable String currencyName, @PathVariable String value, @PathVariable String date) {
		logger.info("Currency: " + currencyName);
		logger.info("Value: " + value);
		logger.info("Date: " + date);
		
		Converter converter = new Converter(value, currencyName, date);
		converter.setDatabase(database);
		converter.convert();
		
		return converter;
	}

}
