package com.spring.springbootcrud.domain.repository;

import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.domain.entity.Person;
import java.util.List;

public interface PersonRepositoryCustom {

  List<Person> findAllByFilter(PersonDTO dto);
}
