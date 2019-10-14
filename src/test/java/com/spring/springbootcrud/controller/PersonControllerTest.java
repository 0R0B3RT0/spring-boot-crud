package com.spring.springbootcrud.controller;

import static org.assertj.core.util.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.springbootcrud.BaseUnitTest;
import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.service.DocumentService;
import com.spring.springbootcrud.service.PersonService;
import java.lang.reflect.Method;
import java.util.List;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

public class PersonControllerTest extends BaseUnitTest {

  @InjectMocks private PersonController personController;
  @Mock protected PersonService personService;
  @Mock private DocumentService documentService;

  public void setup() {
    super.setup();
    personDTO = PersonDTO.builder().cpf(VALID_CPF).name(NAME).address(ADDRESS).build();
    when(personService.save(personDTO)).thenReturn(expectedPersonDTO);
    when(documentService.cleanDocument(any(String.class))).thenCallRealMethod();
    when(personService.findByFilter(personDTO)).thenReturn(newArrayList(expectedPersonDTO));
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
    final ResponseEntity<List<PersonDTO>> actualPersonDTO =
        personController.findByFilter(INVALID_CPF, NAME, ADDRESS);

    verify(personService, only()).findByFilter(personDTO);
    assertThat(actualPersonDTO.getStatusCode(), equalTo(OK));
    assertAllAttributesOfPersonDTO(actualPersonDTO.getBody().get(0));
  }

  @Test
  public void findEnabledPeopleMustBeAnnotatedWithHystrixCommand() {
    final Method method = getMethod(PersonController.class, "findByFilter");

    final HystrixCommand annotation = getAnnotation(method, HystrixCommand.class);

    assertThat(annotation, notNullValue());
    assertThat(annotation.commandKey(), equalTo("find-by-filter"));
  }
}
