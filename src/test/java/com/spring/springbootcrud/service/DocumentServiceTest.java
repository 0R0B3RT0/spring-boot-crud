package com.spring.springbootcrud.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceTest {

	private static final String INVALID_CPF = "123.456.789-10";
	private static final String VALID_CPF = "12345678910";

	@InjectMocks
	private DocumentService documentService;

	@Test
	public void mustOnlyNumberWhenHasAnyCharactersInDocument(){
		final String actual = documentService.cleanDocument( INVALID_CPF );

		assertThat(actual, equalTo(VALID_CPF));
	}

	@Test
	public void mustOnlyNumberWhenHasOnlyNumbersInDocument(){
		final String actual = documentService.cleanDocument( VALID_CPF );

		assertThat(actual, equalTo(VALID_CPF));
	}
}
