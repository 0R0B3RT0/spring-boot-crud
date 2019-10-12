package com.spring.springbootcrud.domain.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.mockito.InjectMocks;

import com.spring.springbootcrud.BaseUnitTest;
import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.domain.entity.Person;

public class PersonMapperTest extends BaseUnitTest {

  @InjectMocks private PersonMapper personMapper;

  @Test
  public void mustPersonWhenHasPersonDTO() {
    final Person actualPerson = personMapper.toEntity(expectedPersonDTO);

    assertThat(actualPerson, equalTo(person));
  }

  @Test
  public void mustPersonDTOWhenHasPerson() {
    final PersonDTO actualPersonDTO = personMapper.toDTO(person);

    assertThat(actualPersonDTO, equalTo(expectedPersonDTO));
  }
}
