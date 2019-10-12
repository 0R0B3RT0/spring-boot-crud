package com.spring.springbootcrud.service;

import static java.util.Optional.ofNullable;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import javax.persistence.PersistenceException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.domain.entity.Person;
import com.spring.springbootcrud.domain.mapper.PersonMapper;
import com.spring.springbootcrud.domain.repository.PersonRepository;
import com.spring.springbootcrud.service.validation.Validation;

@Slf4j
@Service
public class PersonService {

  @Autowired private DocumentService documentService;
  @Autowired private PersonMapper personMapper;
  @Autowired private PersonRepository personRepository;

  public PersonDTO save(PersonDTO personDTO) {
    return ofNullable(personDTO)
        .map(personToEntity().andThen(validate()).andThen(cleanCpf()))
        .map(persist().andThen(logPersistSuccess()))
        .map(personMapper::toDTO)
        .orElseThrow(() -> new RuntimeException("Falha salvar a Pessoa"));
  }

  private UnaryOperator<Person> validate() {
    return person -> {
      final Validation<Person> validation = new Validation<>(person);
      validation.validateAndThrow();
      return person;
    };
  }

  private Function<PersonDTO, Person> personToEntity() {
    return personMapper::toEntity;
  }

  private UnaryOperator<Person> persist() {
    return person -> {
      try {
        return personRepository.save(person);
      } catch (Exception ex) {
        log.error(
            "Falha ao persistir a pessoa, id:{}, name:{}", person.getId(), person.getName(), ex);
        throw new PersistenceException("Falha ao persistir a pessoa", ex);
      }
    };
  }

  private UnaryOperator<Person> logPersistSuccess() {
    return person -> {
      log.info("Pessoa persistida com sucesso, id:{}, name:{}", person.getId(), person.getName());
      return person;
    };
  }

  private UnaryOperator<Person> cleanCpf() {
    return dto -> {
      dto.setCpf(documentService.cleanDocument(dto.getCpf()));
      return dto;
    };
  }
}
