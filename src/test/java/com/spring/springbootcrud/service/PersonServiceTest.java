package com.spring.springbootcrud.service;

import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.spring.springbootcrud.BaseUnitTest;
import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.domain.entity.Person;
import com.spring.springbootcrud.domain.mapper.PersonMapper;
import com.spring.springbootcrud.domain.repository.PersonRepository;
import java.util.List;
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
  @Mock private ValidationService<Person> validationService;
  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Override
  public void setup() {
    super.setup();
    when(personMapper.toEntity(personDTO)).thenReturn(person);
    when(personMapper.toDTO(person)).thenReturn(expectedPersonDTO);
    when(documentService.cleanDocument(any(String.class))).thenCallRealMethod();
    when(personRepository.save(person)).thenReturn(person);
    when(validationService.validateAndThrow(person)).thenReturn(person);
    when(personRepository.findAllByEnabledTrue()).thenReturn(newArrayList(person));
    when(personRepository.findAll(any())).thenReturn(newArrayList(person));
  }

  @Test
  public void mustSaveNewPersonWhenHasPersonDTOIsValid() {
    final PersonDTO actualPersonDTO = personService.save(personDTO);

    assertAllAttributesOfPersonDTO(actualPersonDTO);
    verifyAllDependenciesOfPersonSave();
  }

  @Test
  public void mustUpdatePersonWhenHasPersonDTOIsValid() {
    final PersonDTO actualPersonDTO = personService.save(personDTO);

    assertAllAttributesOfPersonDTO(actualPersonDTO);
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

  @Test
  public void mustFindAllPeopleWhenFindWithFilter() {
    final List<PersonDTO> activePeopleDTO = personService.findByFilter(expectedPersonDTO);

    assertAllAttributesOfPersonDTO(activePeopleDTO.get(0));
    verify(personRepository).findAll(any());
    verify(personRepository, never()).findAllByEnabledTrue();
    verify(personMapper, only()).toDTO(person);
  }

  @Test
  public void mustFindAllPeopleWhenFindWithoutFilter() {
    final List<PersonDTO> activePeopleDTO = personService.findByFilter(PersonDTO.builder().build());

    assertAllAttributesOfPersonDTO(activePeopleDTO.get(0));
    verify(personRepository, never()).findAll(any());
    verify(personRepository).findAllByEnabledTrue();
    verify(personMapper, only()).toDTO(person);
  }

  private void verifyAllDependenciesOfPersonSave() {
    verify(documentService, only()).cleanDocument(VALID_CPF);
    verify(personRepository).save(person);
    verify(personRepository).findById(ID);
    verify(personMapper).toEntity(personDTO);
    verify(personMapper).toDTO(person);
  }
}
