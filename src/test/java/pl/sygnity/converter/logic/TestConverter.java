package pl.sygnity.converter.logic;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import pl.sygnity.converter.dao.Database;

public class TestConverter {
	
	private Database mockDatabase;
	private NbpApiHandler mockNbpApiHandler;
	
//	@Before
//	public void setUp() {
//		
//	}
//
//	@Test
//	public void TestGetConverterValueConvertEURRateFromAPI() {
//		String currencyName = "EUR";
//		String dateString = "2019-01-02";
//		String value = "10";
//		String converterValue = "4.3016";
//		LocalDate date = LocalDate.parse(dateString, formatter);
//		when (mockNbpApiHandler.getConverterValue(currencyName, date)).thenReturn(Double.valueOf(value));
//		when (mockDatabase.findCurrencyIdInDatabase(currencyName)).thenReturn(0);
//		converter = new Converter(value, currencyName, dateString, mockNbpApiHandler, mockDatabase);
//		converter.convert();
//		Double result = converter.getConvertedValue();
//		Double expectedValue = Double.valueOf(value) / Double.valueOf(converterValue);
//		assertEquals(expectedValue, result);
//	}
}
