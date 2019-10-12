package com.spring.springbootcrud;

import static java.util.UUID.fromString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.domain.entity.Person;
import com.spring.springbootcrud.service.PersonService;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseUnitTest {

  protected static final UUID ID = fromString("cabcc0d8-55bd-45a0-a409-1cb3ee292d3a");
  protected static final String NAME = "Usuário de Teste";
  protected static final String INVALID_CPF = "123.456.789-10";
  protected static final String VALID_CPF = "12345678910";
  protected static final String ADDRESS =
      "Rua teste, Bairro Java, num: 53 - Apto 103, Joinville - SC";
  protected LocalDate bornDate;
  protected PersonDTO personDTO;
  protected PersonDTO expectedPersonDTO;
  protected Person person;

  @Mock protected PersonService personService;

  @Before
  public void setup() {
    bornDate = LocalDate.of(1989, 11, 1);
    personDTO = buildPersonDTO(INVALID_CPF);
    expectedPersonDTO = buildPersonDTO(VALID_CPF);
    person = buildPerson();
    when(personService.save(personDTO)).thenReturn(expectedPersonDTO);
  }

  protected void assertAllAttributesOfPerson(PersonDTO actualPersonDTO) {
    assertThat(actualPersonDTO.getId(), equalTo(expectedPersonDTO.getId()));
    assertThat(actualPersonDTO.getName(), equalTo(expectedPersonDTO.getName()));
    assertThat(actualPersonDTO.getCpf(), equalTo(expectedPersonDTO.getCpf()));
    assertThat(actualPersonDTO.getBornDate(), equalTo(expectedPersonDTO.getBornDate()));
    assertThat(actualPersonDTO.getAddress(), equalTo(expectedPersonDTO.getAddress()));
    assertThat(actualPersonDTO, equalTo(expectedPersonDTO));
  }

  protected PersonDTO buildPersonDTO(String invalidCpf) {
    return PersonDTO.builder()
        .id(ID)
        .name(NAME)
        .cpf(invalidCpf)
        .bornDate(bornDate)
        .address(ADDRESS)
        .build();
  }

  protected Person buildPerson() {
    return Person.builder()
        .id(ID)
        .name(NAME)
        .cpf(VALID_CPF)
        .bornDate(bornDate)
        .address(ADDRESS)
        .build();
  }
}
