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
	
	public Converter(String value, String currency) {
		this.value = Double.parseDouble(value);
		this.currency = currency;
		
		this.convertedValue = this.convert();
	}
	
	public Converter(String value, String currency, String date) {
		this.value = Double.parseDouble(value);
		this.currency = currency;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
		this.date = LocalDate.parse(date, formatter);
		
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
	
	public Double convert() {
		logger.info("Converting " + this.value + " from currency " + this.currency/* + " on date " + this.date*/);
		
		double converter = Double.valueOf(4.03);
		this.convertedValue = this.value * converter;
		
		logger.info("Converted to: " + convertedValue);
		
		return this.convertedValue;
		
	}
	
	
}
