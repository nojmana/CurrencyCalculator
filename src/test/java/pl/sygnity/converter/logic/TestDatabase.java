package pl.sygnity.converter.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import pl.sygnity.converter.dao.Database;
import pl.sygnity.converter.entities.Currency;
import pl.sygnity.converter.entities.Rate;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class TestDatabase {

	private DateTimeFormatter formatter;
	@Autowired
	private Database database;
	
	private Currency expectedCurrency;
	private Rate expectedRate;
	
	@Before
	public void setUp() {
		this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		this.expectedCurrency = new Currency("EUR");
		this.expectedCurrency.setId(1);
		this.expectedRate = new Rate(this.expectedCurrency, Double.valueOf("4.0132"), LocalDate.parse("2019-01-01", formatter));
		this.database.addCurrency(this.expectedCurrency);
		this.database.addRate(expectedRate);
	}
	
	@Test
	public void testFindCurrencyByName() {
		Integer currencyId = this.database.findCurrencyIdInDatabase(this.expectedCurrency.getName());
		assertEquals(this.expectedCurrency.getId(), currencyId);
	}
	
	@Test
	public void testFindCurrencyById() {
		Currency foundCurrency = database.findCurrencyInDatabase(this.expectedCurrency.getId());
		assertNotNull(foundCurrency);
		assertEquals(this.expectedCurrency.getId(), foundCurrency.getId());
		assertEquals(this.expectedCurrency.getName(), foundCurrency.getName());
	}
	
	@Test
	public void testAddAndFindRate() {
		Double foundRateValue= this.database.findRateValueInDatabase(this.expectedCurrency.getId(), 
				LocalDate.parse("2019-01-01", formatter));
		assertEquals(this.expectedRate.getValue(), foundRateValue);
	}

}
