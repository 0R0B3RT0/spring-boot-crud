package com.spring.springbootcrud.domain.repository;

import static com.spring.springbootcrud.domain.repository.PersonRepository.getSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.spring.springbootcrud.BaseUnitTest;
import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.domain.entity.Person;
import java.util.Optional;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;

public class PersonRepositoryTest extends BaseUnitTest {

  @Test
  public void mustGetSpecificationWhenHasCompleteFilter() {
    final Optional<Specification<Person>> specification = getSpecification(expectedPersonDTO);

    assertThat(specification.isPresent(), is(true));
  }

  @Test
  public void mustEmptySpecificationWhenHasNullFilter() {
    final Optional<Specification<Person>> specification = getSpecification(null);

    assertThat(specification.isPresent(), is(false));
  }

  @Test
  public void mustEmptySpecificationWhenHasEmptyFilter() {
    final Optional<Specification<Person>> specification =
        getSpecification(PersonDTO.builder().build());

    assertThat(specification.isPresent(), is(false));
  }
}
