package pl.sygnity.converter.logic;

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
	
	@Before
	public void setUp() {
		this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
	}
	
	@Test
	public void TestGetConverterValueConvertEURRateFromAPI() {
		String currencyName = "EUR";
		String date = "2019-01-02";
		String  value = "4.3016";
		mockNbpApiHandler = mock(NbpApiHandler.class);
//		when (mockDatabase.findCurrencyIdInDatabase(currencyName)).thenReturn(0);
		when (mockNbpApiHandler.getConverterValue(currencyName, LocalDate.parse(date, formatter))).thenReturn(Double.valueOf(value));
		converter = new Converter(value, currencyName, date, mockNbpApiHandler);
		
//		Double result = converter.getConverterValue(currencyName, date);
	}
}
