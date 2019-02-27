package pl.sygnity.converter.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.sygnity.converter.entities.Currency;

@Repository 
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
	
}
