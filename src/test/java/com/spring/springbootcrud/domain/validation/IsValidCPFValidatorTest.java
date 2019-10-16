package com.spring.springbootcrud.domain.validation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

public class IsValidCPFValidatorTest {

  private IsValidCPFValidator isValidCPFValidator;

  @Before
  public void setup() {
    isValidCPFValidator = new IsValidCPFValidator();
  }

  @Test
  public void mustTrueWhenHasValidCpf() {
    final boolean isValid = isValidCPFValidator.isValid("185.086.141-28", null);

    assertThat(isValid, is(true));
  }

  @Test
  public void mustFalseWhenHasInvalidCpf() {
    final boolean isValid = isValidCPFValidator.isValid("185.086.141-13", null);

    assertThat(isValid, is(false));
  }
}
