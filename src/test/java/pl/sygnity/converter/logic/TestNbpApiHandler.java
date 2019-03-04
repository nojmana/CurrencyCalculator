package pl.sygnity.converter.logic;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class TestNbpApiHandler {
	
	private DateTimeFormatter formatter;
	private NbpApiHandler nbpApiHandler;
	
	@Before
	public void setUp() {
		this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		nbpApiHandler = new NbpApiHandler();
	}
	
	@Test
	public void TestGetEURRate() {
		String currencyName = "EUR";
		String dateString = "2019-01-02";
		Double expectedConverterValue = Double.valueOf("4.3016");
		LocalDate date = LocalDate.parse(dateString, formatter);
		Double result = nbpApiHandler.getConverterValue(currencyName, date);
		assertEquals(expectedConverterValue, result);
	}
	
	@Test
	public void TestGetUSDRate() {
		String currencyName = "USD";
		String dateString = "2019-01-02";
		Double expectedConverterValue = Double.valueOf("3.7619");
		LocalDate date = LocalDate.parse(dateString, formatter);
		Double result = nbpApiHandler.getConverterValue(currencyName, date);
		assertEquals(expectedConverterValue, result);
	}

	@Test(expected = MyException.class)
	public void TestGetRateFutureDate() {
		String currencyName = "USD";
		String dateString = "9999-01-02";
		LocalDate date = LocalDate.parse(dateString, formatter);
		nbpApiHandler.getConverterValue(currencyName, date);
	}
	
	@Test(expected = MyException.class)
	public void TestGetRateNotExistingCurrency() {
		String currencyName = "TestTest";
		String dateString = "2018-01-02";
		LocalDate date = LocalDate.parse(dateString, formatter);
		nbpApiHandler.getConverterValue(currencyName, date);
	}
}
