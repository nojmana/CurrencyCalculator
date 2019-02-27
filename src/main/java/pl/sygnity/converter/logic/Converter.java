package pl.sygnity.converter.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.sygnity.converter.dao.Database;
import pl.sygnity.converter.entities.Currency;
import pl.sygnity.converter.entities.Rate;

public class Converter {
	
	private static final Logger logger = LoggerFactory.getLogger(Converter.class);
	
	private Rate rate;	
	private JsonParser jsonParser;
	private Database database;
	
	private Double value;
	private Double convertedValue;
	
	public Converter(String value, String currencyName, String date) {
		this.rate = new Rate();
		
		this.rate.setCurrency(new Currency(currencyName));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		this.rate.setDate(LocalDate.parse(date, formatter));

		this.jsonParser = new JsonParser();
		this.value = Double.parseDouble(value);
	}
	
	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
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
		
		if (!this.rate.getCurrency().getCurrencyName().equals("EUR")) {
			Rate rateEur = new Rate();
			rateEur.setCurrency(new Currency("EUR"));
			rateEur.setConverter(this.jsonParser.getConverterValue("EUR", this.rate.getDate()));
			
			rate.getCurrency();
			this.database.toString();
			this.database.addCurrency(rateEur.getCurrency());
			this.database.addRate(rateEur);
			
			this.convertedValue /= rateEur.getConverter();
		}
		
		this.rate.setConverter(this.jsonParser.getConverterValue(this.rate.getCurrency().getCurrencyName(), this.rate.getDate()));
		this.convertedValue *= this.rate.getConverter();
		
		logger.info("Converted to: " + convertedValue);
	}
	
	
}