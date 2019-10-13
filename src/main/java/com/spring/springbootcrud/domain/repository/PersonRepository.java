package com.spring.springbootcrud.domain.repository;

import com.spring.springbootcrud.domain.entity.Person;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, UUID> {

  public List<Person> findAllByEnabledTrue();
}
