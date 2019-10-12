package com.spring.springbootcrud.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = "person", produces = APPLICATION_JSON_VALUE)
public class PersonController {

  @Autowired private PersonService personService;

  @PostMapping
  public ResponseEntity<PersonDTO> saveNew(@RequestBody PersonDTO personDTO) {
    return ResponseEntity.ok(personService.save(personDTO));
  }
}
