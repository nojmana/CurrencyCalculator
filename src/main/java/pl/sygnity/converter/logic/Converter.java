package pl.sygnity.converter.logic;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import pl.sygnity.converter.dao.Database;
import pl.sygnity.converter.entities.Currency;
import pl.sygnity.converter.entities.Rate;

public class Converter {
	
	private static final Logger logger = LoggerFactory.getLogger(Converter.class);
	
	@JsonProperty
	private String currencyName;
	@JsonProperty
	private Double value;
	@JsonProperty
	private LocalDate date;
	
	@JsonIgnore
	private NbpApiHandler nbpApiHandler;
	@JsonIgnore
	private Database database;
	
	@JsonProperty
	private Double convertedValue;
	
	
	public Converter(String value, String currencyName, String date, NbpApiHandler nbpApiHandler, Database database) {
		this.currencyName = currencyName;
		try {
			this.value = Double.parseDouble(value);
		} catch (NumberFormatException e) {
			MyException myException = new MyException(400, "Error while parsing value", e);
			throw myException;
		}		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		try {
			this.date = LocalDate.parse(date, formatter);
		} catch (DateTimeException e) {
			MyException myException = new MyException(400, "Error while parsing date", e);
			throw myException;
		}
		
		this.nbpApiHandler = nbpApiHandler;
		this.database = database;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public Double getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(Double convertedValue) {
		this.convertedValue = convertedValue;
	}

	public void convert() {
		this.convertedValue = this.value;
				
		if (!this.currencyName.equals("EUR")) {
			logger.info("CurrencyName != EUR");
			this.convertedValue *= this.getConverterValue(this.currencyName, this.date) / this.getConverterValue("EUR", this.date);
		}
		
		logger.info("Converted to: " + convertedValue);
	}
	
	public Double getConverterValue(String currencyName, LocalDate date) {
		Rate rate = new Rate();
		rate.setDate(date);
		Currency currency = new Currency(currencyName);
		Integer currencyId = this.database.findCurrencyIdInDatabase(currencyName);
		
		if (currencyId == 0) {
			logger.info("No " + currencyName + " currency in database");
			rate.setConverter(this.nbpApiHandler.getConverterValue(currencyName, date));
			rate.setCurrency(currency);
			
			this.database.addCurrency(currency);
			this.database.addRate(rate);
		} else {
			logger.info(currencyName + " currency in database");
			rate.setConverter(this.database.findConverterInDatabase(currencyId, date));
			rate.setCurrency(this.database.findCurrencyInDatabase(currencyId));

			if (rate.getConverter().compareTo(Double.valueOf(0)) > 0 ? false : true) {
				logger.info("No " + currencyName + "  rate in database");
				rate.setConverter(this.nbpApiHandler.getConverterValue(currencyName, date));
				this.database.addRate(rate);
			}
		}
		return rate.getConverter();
	}
	
	
}