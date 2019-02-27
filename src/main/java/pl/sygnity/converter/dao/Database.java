package pl.sygnity.converter.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.sygnity.converter.entities.Currency;
import pl.sygnity.converter.entities.Rate;
import pl.sygnity.converter.repositories.CurrencyRepository;
import pl.sygnity.converter.repositories.RateRepository;


@Component
public class Database {

	@Autowired
	private CurrencyRepository currencyRepository;
	@Autowired 
	private RateRepository rateRepository;

	public void addCurrency(Currency currency) {
		currencyRepository.save(currency);
	}
	
	public void addRate(Rate rate) {
		rateRepository.save(rate);
	}
}
