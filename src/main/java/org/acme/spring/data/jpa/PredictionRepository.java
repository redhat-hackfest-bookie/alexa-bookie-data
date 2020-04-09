package org.acme.spring.data.jpa;

import org.springframework.data.repository.CrudRepository;

public interface PredictionRepository extends CrudRepository<Prediction, Long> {

}
