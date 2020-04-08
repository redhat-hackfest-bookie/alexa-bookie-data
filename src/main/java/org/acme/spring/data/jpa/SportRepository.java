package org.acme.spring.data.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SportRepository extends CrudRepository<Sport, Long> {

    List<Sport> findByName(String name);
}
