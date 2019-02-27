package pl.sygnity.converter.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pl.sygnity.converter.entities.Rate;

public interface RateRepository extends CrudRepository<Rate, Long> {

	@Query(value = "SELECT R.CONVERTER FROM rate R WHERE R.CURRENCY_ID = :currencyId and R.DATE = :date", nativeQuery = true)
	Double findRateByCurrencyAndDate(@Param("currencyId") Integer currencyId, @Param("date") LocalDate date);

}
