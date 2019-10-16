package com.spring.springbootcrud.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.spring.springbootcrud.BaseUnitTest;
import org.junit.Test;
import org.mockito.InjectMocks;

public class DocumentServiceTest extends BaseUnitTest {

  @InjectMocks private DocumentService documentService;

  @Test
  public void mustOnlyNumberWhenHasAnyCharactersInDocument() {
    final String actual = documentService.cleanDocument(INVALID_CPF);

    assertThat(actual, equalTo(VALID_CPF));
  }

  @Test
  public void mustOnlyNumberWhenHasOnlyNumbersInDocument() {
    final String actual = documentService.cleanDocument(VALID_CPF);

    assertThat(actual, equalTo(VALID_CPF));
  }

  @Test
  public void mustTrueWhenHasValidCpf() {
    final boolean isValid = documentService.isValid("185.086.141-28");

    assertThat(isValid, is(true));
  }

  @Test
  public void mustFalseWhenHasFirstVerificationDigitIsInvalid() {
    final boolean isValid = documentService.isValid("185.086.141-38");

    assertThat(isValid, is(false));
  }

  @Test
  public void mustFalseWhenHasSecondVerificationDigitIsInvalid() {
    final boolean isValid = documentService.isValid("185.086.141-29");

    assertThat(isValid, is(false));
  }

  @Test
  public void mustTrueWhenHasValidCpfAndStartWithZero() {
    final boolean isValid = documentService.isValid("090.862.336-43");

    assertThat(isValid, is(true));
  }

  @Test
  public void mustTrueWhenHasValidCpfAndStartWithZeroAndTheSecondValidationDigitIsOver10() {
    final boolean isValid = documentService.isValid("000.000.050-70");

    assertThat(isValid, is(true));
  }
}
