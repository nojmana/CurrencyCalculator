package pl.sygnity.dimon.converter.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.sygnity.dimon.converter.dao.repositories.CurrencyRepository;
import pl.sygnity.dimon.converter.dao.repositories.RateRepository;


@Service
public class Database {

	@Autowired
	private CurrencyRepository currencyRepository;
	@Autowired 
	private RateRepository rateRepository;
	
//	public Database(CurrencyRepository currencyRepository, RateRepository rateRepository) {
//		this.currencyRepository = currencyRepository;
//		this.rateRepository = rateRepository;
//	}
//
//	public void addCurrency(Currency currency) {
//		currencyRepository.save(currency);
//	}
//	
//	public void addRate(Rate rate) {
//		rateRepository.save(rate);
//	}
}
