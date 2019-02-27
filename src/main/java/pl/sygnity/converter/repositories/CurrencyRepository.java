package pl.sygnity.converter.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pl.sygnity.converter.entities.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
	
	@Query(value = "SELECT C.ID FROM currency C WHERE C.CURRENCY_NAME = :currencyName", nativeQuery = true)
	Integer findCurrencyIdByName(@Param("currencyName") String currencyName);

	@Query(value = "SELECT * FROM currency C WHERE C.CURRENCY_NAME = :currencyName", nativeQuery = true)
	Currency findCurrencyByName(@Param("currencyName") String currencyName);

}
