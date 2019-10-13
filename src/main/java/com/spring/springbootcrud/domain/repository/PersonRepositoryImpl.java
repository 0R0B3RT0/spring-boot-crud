package com.spring.springbootcrud.domain.repository;

import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.domain.entity.Person;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepositoryCustom {

  @Override
  public List<Person> findAllByFilter(PersonDTO dto) {
    return null;
  }
}
