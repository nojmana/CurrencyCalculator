package pl.sygnity.converter.logic;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import pl.sygnity.converter.dao.Database;

@RunWith(MockitoJUnitRunner.class)
public class TestNpiApiHandler {
	
	private Converter converter;
	private DateTimeFormatter formatter;
	private NbpApiHandler mockNbpApiHandler;
	private Database mockDatabase;
	
	@Before
	public void setUp() {
		this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		mockNbpApiHandler = mock(NbpApiHandler.class);
		mockDatabase = mock(Database.class);
	}
	

	@Test
	public void TestGetConverterValueConvertEURRateFromAPI() {
		String currencyName = "EUR";
		String dateString = "2019-01-02";
		LocalDate date = LocalDate.parse(dateString, formatter);
		String value = "10";
		String converterValue = "4.3016";
		when (mockNbpApiHandler.getConverterValue(currencyName, date)).thenReturn(Double.valueOf(value));
		when (mockDatabase.findCurrencyIdInDatabase(currencyName)).thenReturn(0);
		converter = new Converter(value, currencyName, dateString, mockNbpApiHandler);
		
		Double result = converter.getConverterValue(currencyName, date);
		Double expectedValue = Double.valueOf(value) / Double.valueOf(converterValue);
		assertEquals(result, expectedValue);
	}
}
