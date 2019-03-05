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
	public void testGetEURRate() {
		String currencyName = "EUR";
		String dateString = "2019-01-02";
		Double expectedRateValue = Double.valueOf("4.3016");
		LocalDate date = LocalDate.parse(dateString, formatter);
		Double result = nbpApiHandler.getRateValue(currencyName, date);
		assertEquals(expectedRateValue, result);
	}
	
	@Test
	public void testGetUSDRate() {
		String currencyName = "USD";
		String dateString = "2019-01-02";
		Double expectedRateValue = Double.valueOf("3.7619");
		LocalDate date = LocalDate.parse(dateString, formatter);
		Double result = nbpApiHandler.getRateValue(currencyName, date);
		assertEquals(expectedRateValue, result);
	}

	@Test(expected = MyException.class)
	public void testGetRateFutureDate() {
		String currencyName = "USD";
		String dateString = "9999-01-02";
		LocalDate date = LocalDate.parse(dateString, formatter);
		nbpApiHandler.getRateValue(currencyName, date);
	}
	
	@Test(expected = MyException.class)
	public void testGetRateNotExistingCurrency() {
		String currencyName = "TestTest";
		String dateString = "2018-01-02";
		LocalDate date = LocalDate.parse(dateString, formatter);
		nbpApiHandler.getRateValue(currencyName, date);
	}
	
}
