package com.spring.springbootcrud.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.spring.springbootcrud.BaseUnitTest;
import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.domain.entity.Person;
import com.spring.springbootcrud.domain.mapper.PersonMapper;
import com.spring.springbootcrud.domain.repository.PersonRepository;
import javax.persistence.PersistenceException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest extends BaseUnitTest {

  @InjectMocks private PersonService personService;
  @Mock private PersonMapper personMapper;
  @Mock private DocumentService documentService;
  @Mock private PersonRepository personRepository;
  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Override
  public void setup() {
    super.setup();
    when(personMapper.toEntity(any(PersonDTO.class))).thenCallRealMethod();
    when(personMapper.toDTO(any(Person.class))).thenCallRealMethod();
    when(documentService.cleanDocument(any(String.class))).thenCallRealMethod();
    when(personRepository.save(person)).thenReturn(person);
  }

  @Test
  public void mustSaveNewPersonWhenHasPersonDTOIsValid() {
    final PersonDTO actualPersonDTO = personService.save(personDTO);

    assertAllAttributesOfPerson(actualPersonDTO);
    verifyAllDependenciesOfPersonSave();
  }

  @Test
  public void mustRuntimeExceptionWhenPersonDTOIsNull() {
    expectedException.expect(RuntimeException.class);
    expectedException.expectMessage("Falha salvar a Pessoa");

    personService.save(null);
  }

  @Test
  public void mustRuntimeExceptionWhenFailToSave() {
    when(personRepository.save(person)).thenThrow(RuntimeException.class);
    expectedException.expect(PersistenceException.class);
    expectedException.expectMessage("Falha ao persistir a pessoa");

    personService.save(personDTO);
  }

  private void verifyAllDependenciesOfPersonSave() {
    verify(documentService, only()).cleanDocument(INVALID_CPF);
    verify(personRepository, only()).save(person);
    verify(personMapper).toEntity(personDTO);
    verify(personMapper).toDTO(person);
  }
}
