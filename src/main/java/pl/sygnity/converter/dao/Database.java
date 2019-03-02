package pl.sygnity.converter.dao;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.sygnity.converter.entities.Currency;
import pl.sygnity.converter.entities.Rate;
import pl.sygnity.converter.repositories.CurrencyRepository;
import pl.sygnity.converter.repositories.RateRepository;
import pl.sygnity.converter.rest.ConverterController;


@Service
public class Database {

	private static final Logger logger = LoggerFactory.getLogger(ConverterController.class);

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
	
	public Double findConverterInDatabase(Integer currencyId, LocalDate date) {
		Double converter = this.rateRepository.findRateByCurrencyAndDate(currencyId, date);
		if (converter == null) {
			return Double.valueOf(0);
		} else {
			return converter;
		}
	}
}
