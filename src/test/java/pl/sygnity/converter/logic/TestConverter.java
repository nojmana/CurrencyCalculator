package pl.sygnity.converter.logic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import pl.sygnity.converter.dao.Database;

public class TestConverter {
	
	private Database mockDatabase;
	private NbpApiHandler mockNbpApiHandler;
	private DateTimeFormatter formatter;
	
	@Before
	public void setUp() {
		mockDatabase = mock(Database.class);
		mockNbpApiHandler = mock(NbpApiHandler.class);
		this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
	}

	@Test
	public void testGetConvertedValueEURFromAPI() {
		String currencyName = "EUR";
		String dateString = "2019-01-02";
		String value = "10";
		String converterValue = "4.3016";
		LocalDate date = LocalDate.parse(dateString, formatter);
		when (mockDatabase.findCurrencyIdInDatabase(currencyName)).thenReturn(0);
		when (mockNbpApiHandler.getConverterValue(currencyName, date)).thenReturn(Double.valueOf(converterValue));
		Converter converter = new Converter(value, currencyName, dateString, mockNbpApiHandler, mockDatabase);
		converter.convert();
		Double result = converter.getConvertedValue();
		Double expectedValue = Double.valueOf(value);
		assertEquals(expectedValue, result);
	}
	
	@Test
	public void testGetConvertedValueUSDFromAPI() {
		String currencyName = "USD";
		String dateString = "2019-02-04";
		String value = "10";
		String converterValueEUR = "4.2813";
		String converterValueUSD = "3.7408";
		LocalDate date = LocalDate.parse(dateString, formatter);
		
		when (mockDatabase.findCurrencyIdInDatabase("EUR")).thenReturn(0);
		when (mockNbpApiHandler.getConverterValue("EUR", date)).thenReturn(Double.valueOf(converterValueEUR));
		
		when (mockDatabase.findCurrencyIdInDatabase(currencyName)).thenReturn(0);
		when (mockNbpApiHandler.getConverterValue(currencyName, date)).thenReturn(Double.valueOf(converterValueUSD));
		
		Converter converter = new Converter(value, currencyName, dateString, mockNbpApiHandler, mockDatabase);
		converter.convert();
		Double result = converter.getConvertedValue();
		Double expectedValue = Double.valueOf(value) * Double.valueOf(converterValueUSD) / Double.valueOf(converterValueEUR);
		assertEquals(expectedValue, result);
	}

}