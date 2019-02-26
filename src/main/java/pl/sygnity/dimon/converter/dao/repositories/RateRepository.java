package pl.sygnity.dimon.converter.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.sygnity.dimon.converter.dao.Rate;

@Repository
public interface RateRepository extends CrudRepository<Rate, Integer> {

}
