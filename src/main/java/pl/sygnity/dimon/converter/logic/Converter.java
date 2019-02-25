package pl.sygnity.dimon.converter.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Converter {
	
	private static final Logger logger = LoggerFactory.getLogger(Converter.class);
	
	private Double value;
	private String currency;
	private LocalDate date;
	private Double convertedValue;
	private Double converter;
	
	private JsonParser jsonParser;
	
	public Converter(String value, String currency) {
		this.value = Double.parseDouble(value);
		this.currency = currency;
		this.jsonParser = new JsonParser();

		this.convertedValue = this.convert();
	}
	
	public Converter(String value, String currency, String date) {
		this.value = Double.parseDouble(value);
		this.currency = currency;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
		this.date = LocalDate.parse(date, formatter);
		this.jsonParser = new JsonParser();
		
		this.convertedValue = this.convert();
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public Double getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(Double convertedValue) {
		this.convertedValue = convertedValue;
	}

	public Double getConverter() {
		return converter;
	}

	public void setConverter(Double converter) {
		this.converter = converter;
	}

	public Double convert() {
		logger.info("Converting " + this.value + " from currency " + this.currency/* + " on date " + this.date*/);
		this.convertedValue = this.value;
		
		if (!this.currency.equals("EUR")) {
			Double converterEUR = this.jsonParser.getConverter("EUR");
			this.convertedValue /= converterEUR;
		}
		
		this.converter = this.jsonParser.getConverter(this.currency);
		this.convertedValue *= converter;
		
		logger.info("Converted to: " + convertedValue);
		
		return this.convertedValue;
		
	}
	
	
}
