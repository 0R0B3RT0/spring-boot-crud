package com.spring.springbootcrud.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@DefaultProperties(
    groupKey = "person",
    commandProperties =
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "800"))
@Validated
@RestController
@RequestMapping(value = "person", produces = APPLICATION_JSON_VALUE)
public class PersonController {

  @Autowired private PersonService personService;

  @PostMapping
  @HystrixCommand(commandKey = "person_save-new")
  public ResponseEntity<PersonDTO> saveNew(@RequestBody PersonDTO personDTO) {
    return ResponseEntity.ok(personService.save(personDTO));
  }
}
