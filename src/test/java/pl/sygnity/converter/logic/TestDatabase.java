package pl.sygnity.converter.logic;

import org.junit.Test;

import pl.sygnity.converter.dao.Database;

public class TestDatabase {

	private Database database;
	
	@Test
	public void testAddAndFindCurrencyByName() {
		String currencyName = "EUR";
		Currency currency = new Currency("EUR");
		database.addCurrency(currency);
		database.findCurrencyIdInDatabase("EUR");
	}
}
