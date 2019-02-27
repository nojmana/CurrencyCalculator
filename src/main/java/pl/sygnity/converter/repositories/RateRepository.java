package pl.sygnity.converter.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.sygnity.converter.entities.Rate;

@Repository
public interface RateRepository extends CrudRepository<Rate, Long> {

}
