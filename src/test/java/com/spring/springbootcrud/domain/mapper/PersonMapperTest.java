package com.spring.springbootcrud.domain.mapper;

import static java.util.UUID.fromString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.spring.springbootcrud.domain.dto.PersonDTO;
import com.spring.springbootcrud.domain.entity.Person;

@RunWith(MockitoJUnitRunner.class)
public class PersonMapperTest {

	private static final UUID ID = fromString( "cabcc0d8-55bd-45a0-a409-1cb3ee292d3a" );
	private static final String NAME = "Usuário de Teste";
	private static final String INVALID_CPF = "123.456.789-10";
	private static final String VALID_CPF = "12345678910";
	private static final String ADDRESS = "Rua teste, Bairro Java, num: 53 - Apto 103, Joinville - SC";
	private LocalDate bornDate;
	private PersonDTO personDTO;
	private Person expectedPerson;

	@InjectMocks
	private PersonMapper personMapper;

	@Before
	public void setup() {
		bornDate = LocalDate.of( 1989, 11, 1 );
		personDTO = buildPersonDTO( VALID_CPF );
		expectedPerson = buildPerson();
	}

	@Test
	public void mustPersonWhenHasPersonDTO() {
		final Person actualPerson = personMapper.toEntity( personDTO );

		assertThat( actualPerson, equalTo( expectedPerson ) );
	}

	@Test
	public void mustPersonDTOWhenHasPerson() {
		final PersonDTO actualPersonDTO = personMapper.toDTO( expectedPerson );

		assertThat( actualPersonDTO, equalTo( personDTO ) );
	}

	private PersonDTO buildPersonDTO(String invalidCpf) {
		return PersonDTO.builder()
				.id( ID )
				.name( NAME )
				.cpf( invalidCpf )
				.bornDate( bornDate )
				.address( ADDRESS )
				.build();
	}

	private Person buildPerson() {
		return Person.builder()
				.id( ID )
				.name( NAME )
				.cpf( VALID_CPF )
				.bornDate( bornDate )
				.address( ADDRESS )
				.build();
	}
}
