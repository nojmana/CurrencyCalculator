package pl.sygnity.converter.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pl.sygnity.converter.entities.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
	
	@Query(value = "SELECT C.ID FROM currency C WHERE C.name = :name", nativeQuery = true)
	Integer findCurrencyIdByName(@Param("name") String name);

	@Query(value = "SELECT * FROM currency C WHERE C.id = :id", nativeQuery = true)
	Currency findCurrencyById(@Param("id") Integer id);

}
