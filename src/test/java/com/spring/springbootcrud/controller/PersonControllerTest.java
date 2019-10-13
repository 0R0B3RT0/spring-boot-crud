package com.spring.springbootcrud.controller;

import static org.assertj.core.util.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.springbootcrud.BaseUnitTest;
import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.service.PersonService;

public class PersonControllerTest extends BaseUnitTest {

  @InjectMocks private PersonController personController;
  @Mock protected PersonService personService;

  public void setup() {
    super.setup();
    when(personService.save(personDTO)).thenReturn(expectedPersonDTO);
    when(personService.findEnabledPeople()).thenReturn(newArrayList(expectedPersonDTO));
  }

  @Test
  public void mustHttpStatusOKWhenSavePersonDTOValid() {
    final ResponseEntity<PersonDTO> actualPersonDTO = personController.saveNew(personDTO);

    verify(personService, only()).save(personDTO);
    assertThat(actualPersonDTO.getStatusCode(), equalTo(OK));
    assertAllAttributesOfPersonDTO(actualPersonDTO.getBody());
  }

  @Test
  public void saveNewMustBeAnnotatedWithHystrixCommand() {
    final Method method = getMethod(PersonController.class, "saveNew");

    final HystrixCommand annotation = getAnnotation(method, HystrixCommand.class);

    assertThat(annotation, notNullValue());
    assertThat(annotation.commandKey(), equalTo("save-new"));
  }

  @Test
  public void mustHttpStatusOKWhenFindEnabledPeople() {
    final ResponseEntity<List<PersonDTO>> actualPersonDTO = personController.findEnabledPeople();

    verify(personService, only()).findEnabledPeople();
    assertThat(actualPersonDTO.getStatusCode(), equalTo(OK));
    assertAllAttributesOfPersonDTO(actualPersonDTO.getBody().get(0));
  }

  @Test
  public void findEnabledPeopleMustBeAnnotatedWithHystrixCommand() {
    final Method method = getMethod(PersonController.class, "findEnabledPeople");

    final HystrixCommand annotation = getAnnotation(method, HystrixCommand.class);

    assertThat(annotation, notNullValue());
    assertThat(annotation.commandKey(), equalTo("find-enabled-people"));
  }
}
