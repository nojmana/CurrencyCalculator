package pl.sygnity.converter.logic;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import pl.sygnity.converter.dao.Database;
import pl.sygnity.converter.entities.Currency;
import pl.sygnity.converter.entities.Rate;

@RunWith( SpringRunner.class )
public class TestDatabase {

	private DateTimeFormatter formatter;
	@Autowired
	private Database database;
	
	private Currency expectedCurrency;
	private Rate expectedRate;
	
	@Before
	public void setUp() {
//		this.database = new Database();
		this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		this.expectedCurrency = new Currency("EUR");
		
		this.expectedRate.setCurrency(this.expectedCurrency);
		expectedRate.setDate(LocalDate.parse("2019-01-01", formatter));
		Rate expectedRate = new Rate(this.expectedCurrency, Double.valueOf("4.0132"), LocalDate.parse("2019-01-01", formatter));
	}
	
	@Test
	public void testFindCurrencyByName() {
		this.database.addCurrency(this.expectedCurrency);
		this.database.addRate(expectedRate);
		Integer currencyId = this.database.findCurrencyIdInDatabase(this.expectedCurrency.getName());
		assertEquals(Integer.valueOf(1), currencyId);
	}
	
	@Test
	public void testFindCurrencyById() {
		Currency foundCurrency = database.findCurrencyInDatabase(1);
		assertEquals(Integer.valueOf(1), foundCurrency.getId());
		assertEquals(this.expectedCurrency.getName(), foundCurrency.getName());
	}
	
	@Test
	public void testAddAndFindConverter() {
		Double foundConverterValue = this.database.findConverterInDatabase(this.expectedCurrency.getId(), 
				LocalDate.parse("2019-01-01", formatter));
		assertEquals(this.expectedRate.getConverter(), foundConverterValue);
	}


}
