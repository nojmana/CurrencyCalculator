package pl.sygnity.dimon.converter.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.sygnity.dimon.converter.dao.Currency;

@Repository 
public interface CurrencyRepository extends CrudRepository<Currency, Integer> {
	
}
