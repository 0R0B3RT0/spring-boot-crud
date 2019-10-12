package com.spring.springbootcrud.domain.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.springbootcrud.domain.entity.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, UUID> {
}
