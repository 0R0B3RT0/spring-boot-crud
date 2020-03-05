package com.spring.springbootcrud.service;

import com.spring.springbootcrud.BaseUnitTest;
import com.spring.springbootcrud.domain.entity.Person;
import com.spring.springbootcrud.domain.exception.ValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;

public class ValidationServiceTest extends BaseUnitTest {

  @InjectMocks private ValidationService<Person> validationService;
  @Rule public ExpectedException exception = ExpectedException.none();

  @Test
  public void mustPersonWhenHasPersonIsValid() {
    final Person actualPerson =
        validationService.validateAndThrow(validationService.validateAndThrow(person));

    assertAllAttributesOfPerson(actualPerson);
  }

  @Test(expected = ValidationException.class)
  public void mustValidationExceptionWhenHasPersonIsInvalid() {
    validationService.validateAndThrow(
        validationService.validateAndThrow(Person.builder().build()));
  }

  public void mustValidationExceptionWhenHasPersonWithInvalidCPF() {
    exception.expect(ValidationException.class);
    exception.expectMessage("must be valid");

    validationService.validateAndThrow(
        validationService.validateAndThrow(Person.builder().build()));
  }
}
