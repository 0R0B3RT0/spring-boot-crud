package com.spring.springbootcrud.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.springbootcrud.BaseUnitTest;
import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.service.PersonService;
import java.lang.reflect.Method;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

public class PersonControllerTest extends BaseUnitTest {

  @InjectMocks private PersonController personController;
  @Mock protected PersonService personService;

  public void setup() {
    super.setup();
    when(personService.save(personDTO)).thenReturn(expectedPersonDTO);
  }

  @Test
  public void mustHttpStatusOKWhenHasPersonDTOValid() {
    final ResponseEntity<PersonDTO> actualPersonDTO = personController.saveNew(personDTO);

    assertThat(actualPersonDTO.getStatusCode(), equalTo(OK));
    assertAllAttributesOfPerson(actualPersonDTO.getBody());
  }

  @Test
  public void saveNewMustBeAnnotatedWithHystrixCommand() {
    final Method method = getMethod(PersonController.class, "saveNew");

    final HystrixCommand annotation = getAnnotation(method, HystrixCommand.class);

    assertThat(annotation, notNullValue());
    assertThat(annotation.commandKey(), equalTo("person_save-new"));
  }
}
