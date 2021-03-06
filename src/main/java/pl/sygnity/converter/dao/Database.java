package pl.sygnity.converter.dao;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.sygnity.converter.entities.Currency;
import pl.sygnity.converter.entities.Rate;
import pl.sygnity.converter.repositories.CurrencyRepository;
import pl.sygnity.converter.repositories.RateRepository;


@Service
public class Database {

	@Autowired
	private CurrencyRepository currencyRepository;
	@Autowired 
	private RateRepository rateRepository;

	public void addCurrency(Currency currency) {
		this.currencyRepository.save(currency);
	}
	
	public Currency findCurrencyInDatabase(Integer id) {
		Currency currency = this.currencyRepository.findCurrencyById(id);
		return currency;
	}
	
	public Integer findCurrencyIdInDatabase(String name) {
		Integer currencyId = this.currencyRepository.findCurrencyIdByName(name);

		if (currencyId == null) {
			return 0;
		} else {
			return currencyId;
		}
	}
	
	public void addRate(Rate rate) {
		this.rateRepository.save(rate);
	}
	
	public Double findRateValueInDatabase(Integer currencyId, LocalDate date) {
		Double value = this.rateRepository.findRateByCurrencyAndDate(currencyId, date);
		if (value == null) {
			return Double.valueOf(0);
		} else {
			return value;
		}
	}
}
